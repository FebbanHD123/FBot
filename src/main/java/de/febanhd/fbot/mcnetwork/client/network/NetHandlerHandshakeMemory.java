package de.febanhd.fbot.mcnetwork.client.network;

import de.febanhd.fbot.mcnetwork.network.NetworkManager;
import de.febanhd.fbot.mcnetwork.network.handshake.INetHandlerHandshakeServer;
import de.febanhd.fbot.mcnetwork.network.handshake.client.C00Handshake;
import de.febanhd.fbot.mcnetwork.network.NetHandlerLoginServer;
import de.febanhd.fbot.mcnetwork.util.IChatComponent;

public class NetHandlerHandshakeMemory implements INetHandlerHandshakeServer {
	private final NetworkManager networkManager;

	public NetHandlerHandshakeMemory(NetworkManager p_i45287_2_) {
		this.networkManager = p_i45287_2_;
	}

	/**
	 * There are two recognized intentions for initiating a handshake: logging in
	 * and acquiring server status. The NetworkManager's protocol will be
	 * reconfigured according to the specified intention, although a login-intention
	 * must pass a versioncheck or receive a disconnect otherwise
	 */
	public void processHandshake(C00Handshake packetIn) {
		this.networkManager.setConnectionState(packetIn.getRequestedState());
		this.networkManager.setNetHandler(new NetHandlerLoginServer(this.networkManager));
	}

	/**
	 * Invoked when disconnecting, the parameter is a ChatComponent describing the
	 * reason for termination
	 */
	public void onDisconnect(IChatComponent reason) {
	}
}
