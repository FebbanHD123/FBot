package de.febanhd.fbot.mcnetwork.network.play.server;

import de.febanhd.fbot.mcnetwork.network.Packet;
import de.febanhd.fbot.mcnetwork.network.PacketBuffer;
import de.febanhd.fbot.mcnetwork.network.play.INetHandlerPlayClient;

import java.io.IOException;

public class S2EPacketCloseWindow implements Packet<INetHandlerPlayClient> {
	private int windowId;

	public S2EPacketCloseWindow() {
	}

	public S2EPacketCloseWindow(int windowIdIn) {
		this.windowId = windowIdIn;
	}

	/**
	 * Passes this Packet on to the NetHandler for processing.
	 */
	public void processPacket(INetHandlerPlayClient handler) {
		handler.handleCloseWindow(this);
	}

	/**
	 * Reads the raw packet data from the data stream.
	 */
	public void readPacketData(PacketBuffer buf) throws IOException {
		this.windowId = buf.readUnsignedByte();
	}

	/**
	 * Writes the raw packet data to the data stream.
	 */
	public void writePacketData(PacketBuffer buf) throws IOException {
		buf.writeByte(this.windowId);
	}
}
