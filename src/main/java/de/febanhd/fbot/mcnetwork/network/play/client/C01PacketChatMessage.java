package de.febanhd.fbot.mcnetwork.network.play.client;

import de.febanhd.fbot.mcnetwork.network.Packet;
import de.febanhd.fbot.mcnetwork.network.PacketBuffer;
import de.febanhd.fbot.mcnetwork.network.play.INetHandlerPlayServer;

import java.io.IOException;

public class C01PacketChatMessage implements Packet<INetHandlerPlayServer> {
	private String message;

	public C01PacketChatMessage() {
	}

	public C01PacketChatMessage(String messageIn) {

		this.message = messageIn;
	}

	/**
	 * Reads the raw packet data from the data stream.
	 */
	public void readPacketData(PacketBuffer buf) throws IOException {
		this.message = buf.readStringFromBuffer(100);
	}

	/**
	 * Writes the raw packet data to the data stream.
	 */
	public void writePacketData(PacketBuffer buf) throws IOException {
		buf.writeString(this.message);
	}

	/**
	 * Passes this Packet on to the NetHandler for processing.
	 */
	public void processPacket(INetHandlerPlayServer handler) {
		handler.processChatMessage(this);
	}

	public String getMessage() {
		return this.message;
	}
}
