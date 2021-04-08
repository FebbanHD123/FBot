package de.febanhd.fbot.mcnetwork.network.handshake;

import de.febanhd.fbot.mcnetwork.network.INetHandler;
import de.febanhd.fbot.mcnetwork.network.handshake.client.C00Handshake;

public interface INetHandlerHandshakeServer extends INetHandler {
	/**
	 * There are two recognized intentions for initiating a handshake: logging in
	 * and acquiring server status. The NetworkManager's protocol will be
	 * reconfigured according to the specified intention, although a login-intention
	 * must pass a versioncheck or receive a disconnect otherwise
	 */
	void processHandshake(C00Handshake packetIn);
}
