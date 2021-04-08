package de.febanhd.fbot.mcnetwork.network.login;

import de.febanhd.fbot.mcnetwork.network.INetHandler;
import de.febanhd.fbot.mcnetwork.network.login.client.C00PacketLoginStart;
import de.febanhd.fbot.mcnetwork.network.login.client.C01PacketEncryptionResponse;

public interface INetHandlerLoginServer extends INetHandler {
	void processLoginStart(C00PacketLoginStart packetIn);

	void processEncryptionResponse(C01PacketEncryptionResponse packetIn);
}
