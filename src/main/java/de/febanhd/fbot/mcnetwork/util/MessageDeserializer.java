package de.febanhd.fbot.mcnetwork.util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import de.febanhd.fbot.mcnetwork.network.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.io.IOException;
import java.util.List;

public class MessageDeserializer extends ByteToMessageDecoder {
    private static final Logger logger = LogManager.getLogger();
    private static final Marker RECEIVED_PACKET_MARKER = MarkerManager.getMarker("PACKET_RECEIVED",
            NetworkManager.logMarkerPackets);
    private final EnumPacketDirection direction;

    public MessageDeserializer(EnumPacketDirection direction) {
        this.direction = direction;
    }

    protected void decode(ChannelHandlerContext p_decode_1_, ByteBuf p_decode_2_, List<Object> p_decode_3_)
            throws IOException, InstantiationException, IllegalAccessException, Exception {
        if (p_decode_2_.readableBytes() != 0) {
            PacketBuffer packetbuffer = new PacketBuffer(p_decode_2_);
            int i = packetbuffer.readVarIntFromBuffer();
            Packet packet = ((EnumConnectionState) p_decode_1_.channel().attr(NetworkManager.attrKeyConnectionState)
                    .get()).getPacket(this.direction, i);

            if (packet == null) {
                return;
            } else {
                packet.readPacketData(packetbuffer);
                p_decode_3_.add(packet);

                if (logger.isDebugEnabled()) {
                    logger.debug(RECEIVED_PACKET_MARKER, " IN: [{}:{}] {}",
                            new Object[]{p_decode_1_.channel().attr(NetworkManager.attrKeyConnectionState).get(),
                                    Integer.valueOf(i), packet.getClass().getName()});
                }
                packetbuffer.readBytes(packetbuffer.readableBytes());
            }
        }
    }
}
