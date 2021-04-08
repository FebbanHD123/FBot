package de.febanhd.fbot.mcnetwork.network.play.server;

import de.febanhd.fbot.mcnetwork.network.Packet;
import de.febanhd.fbot.mcnetwork.network.PacketBuffer;
import de.febanhd.fbot.mcnetwork.network.play.INetHandlerPlayClient;

import java.io.IOException;

public class S2BPacketChangeGameState implements Packet<INetHandlerPlayClient> {
	public static final String[] MESSAGE_NAMES = new String[] { "tile.bed.notValid" };
	private int state;
	private float field_149141_c;

	public S2BPacketChangeGameState() {
	}

	public S2BPacketChangeGameState(int stateIn, float p_i45194_2_) {
		this.state = stateIn;
		this.field_149141_c = p_i45194_2_;
	}

	/**
	 * Reads the raw packet data from the data stream.
	 */
	public void readPacketData(PacketBuffer buf) throws IOException {
		this.state = buf.readUnsignedByte();
		this.field_149141_c = buf.readFloat();
	}

	/**
	 * Writes the raw packet data to the data stream.
	 */
	public void writePacketData(PacketBuffer buf) throws IOException {
		buf.writeByte(this.state);
		buf.writeFloat(this.field_149141_c);
	}

	/**
	 * Passes this Packet on to the NetHandler for processing.
	 */
	public void processPacket(INetHandlerPlayClient handler) {
		handler.handleChangeGameState(this);
	}

	public int getGameState() {
		return this.state;
	}

	public float func_149137_d() {
		return this.field_149141_c;
	}
}
