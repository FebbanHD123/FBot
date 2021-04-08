package de.febanhd.fbot.mcnetwork.network.login.client;

import com.mojang.authlib.GameProfile;
import de.febanhd.fbot.mcnetwork.network.Packet;
import de.febanhd.fbot.mcnetwork.network.PacketBuffer;
import de.febanhd.fbot.mcnetwork.network.login.INetHandlerLoginServer;

import java.io.IOException;
import java.util.UUID;

public class C00PacketLoginStart implements Packet<INetHandlerLoginServer> {
	private GameProfile profile;

	public C00PacketLoginStart() {
	}

	public C00PacketLoginStart(GameProfile profileIn) {
		this.profile = profileIn;
	}

	/**
	 * Reads the raw packet data from the data stream.
	 */
	public void readPacketData(PacketBuffer buf) throws IOException {
		this.profile = new GameProfile((UUID) null, buf.readStringFromBuffer(16));
	}

	/**
	 * Writes the raw packet data to the data stream.
	 */
	public void writePacketData(PacketBuffer buf) throws IOException {
		buf.writeString(this.profile.getName());
	}

	/**
	 * Passes this Packet on to the NetHandler for processing.
	 */
	public void processPacket(INetHandlerLoginServer handler) {
		handler.processLoginStart(this);
	}

	public GameProfile getProfile() {
		return this.profile;
	}
}
