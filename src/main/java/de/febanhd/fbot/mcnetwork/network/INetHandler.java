package de.febanhd.fbot.mcnetwork.network;

import de.febanhd.fbot.mcnetwork.util.IChatComponent;

public interface INetHandler {
	/**
	 * Invoked when disconnecting, the parameter is a ChatComponent describing the
	 * reason for termination
	 */
	void onDisconnect(IChatComponent reason);
}
