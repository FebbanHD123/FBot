package de.febanhd.fbot.mcnetwork.network.play.server;

import com.google.common.collect.Lists;
import de.febanhd.fbot.mcnetwork.network.Packet;
import de.febanhd.fbot.mcnetwork.network.PacketBuffer;
import de.febanhd.fbot.mcnetwork.network.play.INetHandlerPlayClient;
import de.febanhd.fbot.mcnetwork.util.BlockPos;
import de.febanhd.fbot.mcnetwork.util.Vec3;

import java.io.IOException;
import java.util.List;

public class S27PacketExplosion implements Packet<INetHandlerPlayClient> {
	private double posX;
	private double posY;
	private double posZ;
	private float strength;
	private List<BlockPos> affectedBlockPositions;
	private float field_149152_f;
	private float field_149153_g;
	private float field_149159_h;

	public S27PacketExplosion() {
	}

	public S27PacketExplosion(double p_i45193_1_, double y, double z, float strengthIn, List<BlockPos> affectedBlocksIn,
			Vec3 p_i45193_9_) {
		this.posX = p_i45193_1_;
		this.posY = y;
		this.posZ = z;
		this.strength = strengthIn;
		this.affectedBlockPositions = Lists.newArrayList(affectedBlocksIn);

		if (p_i45193_9_ != null) {
			this.field_149152_f = (float) p_i45193_9_.xCoord;
			this.field_149153_g = (float) p_i45193_9_.yCoord;
			this.field_149159_h = (float) p_i45193_9_.zCoord;
		}
	}

	/**
	 * Reads the raw packet data from the data stream.
	 */
	public void readPacketData(PacketBuffer buf) throws IOException {
		this.posX = (double) buf.readFloat();
		this.posY = (double) buf.readFloat();
		this.posZ = (double) buf.readFloat();
		this.strength = buf.readFloat();
		int i = buf.readInt();
		this.affectedBlockPositions = Lists.<BlockPos>newArrayListWithCapacity(i);
		int j = (int) this.posX;
		int k = (int) this.posY;
		int l = (int) this.posZ;

		for (int i1 = 0; i1 < i; ++i1) {
			int j1 = buf.readByte() + j;
			int k1 = buf.readByte() + k;
			int l1 = buf.readByte() + l;
			this.affectedBlockPositions.add(new BlockPos(j1, k1, l1));
		}

		this.field_149152_f = buf.readFloat();
		this.field_149153_g = buf.readFloat();
		this.field_149159_h = buf.readFloat();
	}

	/**
	 * Writes the raw packet data to the data stream.
	 */
	public void writePacketData(PacketBuffer buf) throws IOException {
		buf.writeFloat((float) this.posX);
		buf.writeFloat((float) this.posY);
		buf.writeFloat((float) this.posZ);
		buf.writeFloat(this.strength);
		buf.writeInt(this.affectedBlockPositions.size());
		int i = (int) this.posX;
		int j = (int) this.posY;
		int k = (int) this.posZ;

		for (BlockPos blockpos : this.affectedBlockPositions) {
			int l = blockpos.getX() - i;
			int i1 = blockpos.getY() - j;
			int j1 = blockpos.getZ() - k;
			buf.writeByte(l);
			buf.writeByte(i1);
			buf.writeByte(j1);
		}

		buf.writeFloat(this.field_149152_f);
		buf.writeFloat(this.field_149153_g);
		buf.writeFloat(this.field_149159_h);
	}

	/**
	 * Passes this Packet on to the NetHandler for processing.
	 */
	public void processPacket(INetHandlerPlayClient handler) {
		handler.handleExplosion(this);
	}

	public float func_149149_c() {
		return this.field_149152_f;
	}

	public float func_149144_d() {
		return this.field_149153_g;
	}

	public float func_149147_e() {
		return this.field_149159_h;
	}

	public double getX() {
		return this.posX;
	}

	public double getY() {
		return this.posY;
	}

	public double getZ() {
		return this.posZ;
	}

	public float getStrength() {
		return this.strength;
	}

	public List<BlockPos> getAffectedBlockPositions() {
		return this.affectedBlockPositions;
	}
}