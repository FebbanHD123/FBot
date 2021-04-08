package de.febanhd.fbot.mcnetwork.network.play.server;

import de.febanhd.fbot.mcnetwork.network.Packet;
import de.febanhd.fbot.mcnetwork.network.PacketBuffer;
import de.febanhd.fbot.mcnetwork.network.play.INetHandlerPlayClient;

import java.io.IOException;

public class S3APacketTabComplete implements Packet<INetHandlerPlayClient> {
	private String[] matches;

	public S3APacketTabComplete() {
	}

	public S3APacketTabComplete(String[] matchesIn) {
		this.matches = matchesIn;
	}

	/**
	 * Reads the raw packet data from the data stream.
	 */
	public void readPacketData(PacketBuffer buf) throws IOException {
		this.matches = new String[buf.readVarIntFromBuffer()];

		for (int i = 0; i < this.matches.length; ++i) {
			this.matches[i] = buf.readStringFromBuffer(32767);
		}
	}

	/**
	 * Writes the raw packet data to the data stream.
	 */
	public void writePacketData(PacketBuffer buf) throws IOException {
		buf.writeVarIntToBuffer(this.matches.length);

		for (String s : this.matches) {
			buf.writeString(s);
		}
	}

	/**
	 * Passes this Packet on to the NetHandler for processing.
	 */
	public void processPacket(INetHandlerPlayClient handler) {
		handler.handleTabComplete(this);
	}

	public String[] func_149630_c() {
		return this.matches;
	}
}
