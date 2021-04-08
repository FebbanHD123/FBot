package de.febanhd.fbot.mcnetwork.fbot;

import de.febanhd.fbot.mcnetwork.client.network.NetHandlerPlayClient;
import io.netty.buffer.Unpooled;
import de.febanhd.fbot.mcnetwork.network.Packet;
import de.febanhd.fbot.mcnetwork.network.PacketBuffer;
import de.febanhd.fbot.mcnetwork.network.play.INetHandlerPlayClient;
import de.febanhd.fbot.mcnetwork.network.play.client.C17PacketCustomPayload;

import java.io.IOException;

public class PacketInJoinGame implements Packet<INetHandlerPlayClient> {

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {

    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {

    }

    @Override
    public void processPacket(INetHandlerPlayClient handler) {
        if(handler instanceof NetHandlerPlayClient) {
            ((NetHandlerPlayClient)handler).getNetworkManager().sendPacket(new C17PacketCustomPayload("MC|Brand", (new PacketBuffer(Unpooled.buffer())).writeString("vanilla")));
        }
    }
}
