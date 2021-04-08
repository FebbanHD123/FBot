package de.febanhd.fbot.mcnetwork.network.play.server;

import de.febanhd.fbot.mcnetwork.network.Packet;
import de.febanhd.fbot.mcnetwork.network.PacketBuffer;
import de.febanhd.fbot.mcnetwork.network.play.INetHandlerPlayClient;
import de.febanhd.fbot.mcnetwork.util.BlockPos;

import java.io.IOException;

public class S05PacketSpawnPosition implements Packet<INetHandlerPlayClient> {
	private BlockPos spawnBlockPos;

	public S05PacketSpawnPosition() {
	}

	public S05PacketSpawnPosition(BlockPos spawnBlockPosIn) {
		this.spawnBlockPos = spawnBlockPosIn;
	}

	/**
	 * Reads the raw packet data from the data stream.
	 */
	public void readPacketData(PacketBuffer buf) throws IOException {
		this.spawnBlockPos = buf.readBlockPos();
	}

	/**
	 * Writes the raw packet data to the data stream.
	 */
	public void writePacketData(PacketBuffer buf) throws IOException {
		buf.writeBlockPos(this.spawnBlockPos);
	}

	/**
	 * Passes this Packet on to the NetHandler for processing.
	 */
	public void processPacket(INetHandlerPlayClient handler) {
		handler.handleSpawnPosition(this);
	}

	public BlockPos getSpawnPos() {
		return this.spawnBlockPos;
	}
}
