package de.febanhd.fbot.mcnetwork.network.play.server;

import de.febanhd.fbot.mcnetwork.network.Packet;
import de.febanhd.fbot.mcnetwork.network.PacketBuffer;
import de.febanhd.fbot.mcnetwork.network.play.INetHandlerPlayClient;
import de.febanhd.fbot.mcnetwork.util.BlockPos;

import java.io.IOException;

public class S25PacketBlockBreakAnim implements Packet<INetHandlerPlayClient> {
	private int breakerId;
	private BlockPos position;
	private int progress;

	public S25PacketBlockBreakAnim() {
	}

	public S25PacketBlockBreakAnim(int breakerId, BlockPos pos, int progress) {
		this.breakerId = breakerId;
		this.position = pos;
		this.progress = progress;
	}

	/**
	 * Reads the raw packet data from the data stream.
	 */
	public void readPacketData(PacketBuffer buf) throws IOException {
		this.breakerId = buf.readVarIntFromBuffer();
		this.position = buf.readBlockPos();
		this.progress = buf.readUnsignedByte();
	}

	/**
	 * Writes the raw packet data to the data stream.
	 */
	public void writePacketData(PacketBuffer buf) throws IOException {
		buf.writeVarIntToBuffer(this.breakerId);
		buf.writeBlockPos(this.position);
		buf.writeByte(this.progress);
	}

	/**
	 * Passes this Packet on to the NetHandler for processing.
	 */
	public void processPacket(INetHandlerPlayClient handler) {
		handler.handleBlockBreakAnim(this);
	}

	public int getBreakerId() {
		return this.breakerId;
	}

	public BlockPos getPosition() {
		return this.position;
	}

	public int getProgress() {
		return this.progress;
	}
}
