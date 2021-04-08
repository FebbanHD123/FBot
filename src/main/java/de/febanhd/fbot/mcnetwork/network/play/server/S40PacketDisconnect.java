package de.febanhd.fbot.mcnetwork.network.play.server;

import de.febanhd.fbot.mcnetwork.network.Packet;
import de.febanhd.fbot.mcnetwork.network.PacketBuffer;
import de.febanhd.fbot.mcnetwork.network.play.INetHandlerPlayClient;
import de.febanhd.fbot.mcnetwork.util.IChatComponent;

import java.io.IOException;

public class S40PacketDisconnect implements Packet<INetHandlerPlayClient> {
	private IChatComponent reason;

	public S40PacketDisconnect() {
	}

	public S40PacketDisconnect(IChatComponent reasonIn) {
		this.reason = reasonIn;
	}

	/**
	 * Reads the raw packet data from the data stream.
	 */
	public void readPacketData(PacketBuffer buf) throws IOException {
		this.reason = buf.readChatComponent();
	}

	/**
	 * Writes the raw packet data to the data stream.
	 */
	public void writePacketData(PacketBuffer buf) throws IOException {
		buf.writeChatComponent(this.reason);
	}

	/**
	 * Passes this Packet on to the NetHandler for processing.
	 */
	public void processPacket(INetHandlerPlayClient handler) {
		handler.handleDisconnect(this);
	}

	public IChatComponent getReason() {
		return this.reason;
	}
}
