package de.febanhd.fbot.mcnetwork.network.status;

import de.febanhd.fbot.mcnetwork.network.INetHandler;
import de.febanhd.fbot.mcnetwork.network.status.server.S00PacketServerInfo;
import de.febanhd.fbot.mcnetwork.network.status.server.S01PacketPong;

public interface INetHandlerStatusClient extends INetHandler {
	void handleServerInfo(S00PacketServerInfo packetIn);

	void handlePong(S01PacketPong packetIn);
}
