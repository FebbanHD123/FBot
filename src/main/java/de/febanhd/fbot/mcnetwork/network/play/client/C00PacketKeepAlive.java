package de.febanhd.fbot.mcnetwork.network.play.client;

import de.febanhd.fbot.mcnetwork.network.Packet;
import de.febanhd.fbot.mcnetwork.network.PacketBuffer;
import de.febanhd.fbot.mcnetwork.network.play.INetHandlerPlayServer;

import java.io.IOException;

public class C00PacketKeepAlive implements Packet<INetHandlerPlayServer> {
	private int key;

	public C00PacketKeepAlive() {
	}

	public C00PacketKeepAlive(int key) {
		this.key = key;
	}

	/**
	 * Passes this Packet on to the NetHandler for processing.
	 */
	public void processPacket(INetHandlerPlayServer handler) {
		handler.processKeepAlive(this);
	}

	/**
	 * Reads the raw packet data from the data stream.
	 */
	public void readPacketData(PacketBuffer buf) throws IOException {
		this.key = buf.readVarIntFromBuffer();
	}

	/**
	 * Writes the raw packet data to the data stream.
	 */
	public void writePacketData(PacketBuffer buf) throws IOException {
		buf.writeVarIntToBuffer(this.key);
	}

	public int getKey() {
		return this.key;
	}
}
