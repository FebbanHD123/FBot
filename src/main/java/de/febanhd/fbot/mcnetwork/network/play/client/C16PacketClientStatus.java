package de.febanhd.fbot.mcnetwork.network.play.client;

import de.febanhd.fbot.mcnetwork.network.Packet;
import de.febanhd.fbot.mcnetwork.network.PacketBuffer;
import de.febanhd.fbot.mcnetwork.network.play.INetHandlerPlayServer;

import java.io.IOException;

public class C16PacketClientStatus implements Packet<INetHandlerPlayServer> {
	private EnumState status;

	public C16PacketClientStatus() {
	}

	public C16PacketClientStatus(EnumState statusIn) {
		this.status = statusIn;
	}

	/**
	 * Reads the raw packet data from the data stream.
	 */
	public void readPacketData(PacketBuffer buf) throws IOException {
		this.status = (EnumState) buf.readEnumValue(EnumState.class);
	}

	/**
	 * Writes the raw packet data to the data stream.
	 */
	public void writePacketData(PacketBuffer buf) throws IOException {
		buf.writeEnumValue(this.status);
	}

	/**
	 * Passes this Packet on to the NetHandler for processing.
	 */
	public void processPacket(INetHandlerPlayServer handler) {
		handler.processClientStatus(this);
	}

	public EnumState getStatus() {
		return this.status;
	}

	public static enum EnumState {
		PERFORM_RESPAWN, REQUEST_STATS, OPEN_INVENTORY_ACHIEVEMENT;
	}
}
