package de.febanhd.fbot.mcnetwork.network.play.client;

import de.febanhd.fbot.mcnetwork.network.Packet;
import de.febanhd.fbot.mcnetwork.network.PacketBuffer;
import de.febanhd.fbot.mcnetwork.network.play.INetHandlerPlayServer;
import de.febanhd.fbot.mcnetwork.util.BlockPos;
import de.febanhd.fbot.mcnetwork.util.EnumFacing;

import java.io.IOException;

public class C07PacketPlayerDigging implements Packet<INetHandlerPlayServer> {
	private BlockPos position;
	private EnumFacing facing;

	/** Status of the digging (started, ongoing, broken). */
	private Action status;

	public C07PacketPlayerDigging() {
	}

	public C07PacketPlayerDigging(Action statusIn, BlockPos posIn, EnumFacing facingIn) {
		this.status = statusIn;
		this.position = posIn;
		this.facing = facingIn;
	}

	/**
	 * Reads the raw packet data from the data stream.
	 */
	public void readPacketData(PacketBuffer buf) throws IOException {
		this.status = (Action) buf.readEnumValue(Action.class);
		this.position = buf.readBlockPos();
		this.facing = EnumFacing.getFront(buf.readUnsignedByte());
	}

	/**
	 * Writes the raw packet data to the data stream.
	 */
	public void writePacketData(PacketBuffer buf) throws IOException {
		buf.writeEnumValue(this.status);
		buf.writeBlockPos(this.position);
		buf.writeByte(this.facing.getIndex());
	}

	/**
	 * Passes this Packet on to the NetHandler for processing.
	 */
	public void processPacket(INetHandlerPlayServer handler) {
		handler.processPlayerDigging(this);
	}

	public BlockPos getPosition() {
		return this.position;
	}

	public EnumFacing getFacing() {
		return this.facing;
	}

	public Action getStatus() {
		return this.status;
	}

	public static enum Action {
		START_DESTROY_BLOCK, ABORT_DESTROY_BLOCK, STOP_DESTROY_BLOCK, DROP_ALL_ITEMS, DROP_ITEM, RELEASE_USE_ITEM;
	}
}
