package de.febanhd.fbot.mcnetwork.network.status;

import de.febanhd.fbot.mcnetwork.network.INetHandler;
import de.febanhd.fbot.mcnetwork.network.status.client.C00PacketServerQuery;
import de.febanhd.fbot.mcnetwork.network.status.client.C01PacketPing;

public interface INetHandlerStatusServer extends INetHandler {
	void processPing(C01PacketPing packetIn);

	void processServerQuery(C00PacketServerQuery packetIn);
}
