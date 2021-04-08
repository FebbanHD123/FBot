package de.febanhd.fbot.mcnetwork.network.play.server;

import de.febanhd.fbot.mcnetwork.network.Packet;
import de.febanhd.fbot.mcnetwork.network.PacketBuffer;
import de.febanhd.fbot.mcnetwork.network.play.INetHandlerPlayClient;

import java.io.IOException;

public class S03PacketTimeUpdate implements Packet<INetHandlerPlayClient> {
	private long totalWorldTime;
	private long worldTime;

	public S03PacketTimeUpdate() {
	}

	public S03PacketTimeUpdate(long totalWorldTimeIn, long totalTimeIn, boolean doDayLightCycle) {
		this.totalWorldTime = totalWorldTimeIn;
		this.worldTime = totalTimeIn;

		if (!doDayLightCycle) {
			this.worldTime = -this.worldTime;

			if (this.worldTime == 0L) {
				this.worldTime = -1L;
			}
		}
	}

	/**
	 * Reads the raw packet data from the data stream.
	 */
	public void readPacketData(PacketBuffer buf) throws IOException {
		this.totalWorldTime = buf.readLong();
		this.worldTime = buf.readLong();
	}

	/**
	 * Writes the raw packet data to the data stream.
	 */
	public void writePacketData(PacketBuffer buf) throws IOException {
		buf.writeLong(this.totalWorldTime);
		buf.writeLong(this.worldTime);
	}

	/**
	 * Passes this Packet on to the NetHandler for processing.
	 */
	public void processPacket(INetHandlerPlayClient handler) {
		handler.handleTimeUpdate(this);
	}

	public long getTotalWorldTime() {
		return this.totalWorldTime;
	}

	public long getWorldTime() {
		return this.worldTime;
	}
}