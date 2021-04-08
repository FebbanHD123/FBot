package de.febanhd.fbot.mcnetwork.network.play.server;

import de.febanhd.fbot.mcnetwork.network.Packet;
import de.febanhd.fbot.mcnetwork.network.PacketBuffer;
import de.febanhd.fbot.mcnetwork.network.play.INetHandlerPlayClient;
import de.febanhd.fbot.mcnetwork.util.BlockPos;

import java.io.IOException;

public class S36PacketSignEditorOpen implements Packet<INetHandlerPlayClient> {
	private BlockPos signPosition;

	public S36PacketSignEditorOpen() {
	}

	public S36PacketSignEditorOpen(BlockPos signPositionIn) {
		this.signPosition = signPositionIn;
	}

	/**
	 * Passes this Packet on to the NetHandler for processing.
	 */
	public void processPacket(INetHandlerPlayClient handler) {
		handler.handleSignEditorOpen(this);
	}

	/**
	 * Reads the raw packet data from the data stream.
	 */
	public void readPacketData(PacketBuffer buf) throws IOException {
		this.signPosition = buf.readBlockPos();
	}

	/**
	 * Writes the raw packet data to the data stream.
	 */
	public void writePacketData(PacketBuffer buf) throws IOException {
		buf.writeBlockPos(this.signPosition);
	}

	public BlockPos getSignPosition() {
		return this.signPosition;
	}
}
