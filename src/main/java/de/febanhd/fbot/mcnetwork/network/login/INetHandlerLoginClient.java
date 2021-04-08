package de.febanhd.fbot.mcnetwork.network.login;

import de.febanhd.fbot.mcnetwork.network.INetHandler;
import de.febanhd.fbot.mcnetwork.network.login.server.S00PacketDisconnect;
import de.febanhd.fbot.mcnetwork.network.login.server.S01PacketEncryptionRequest;
import de.febanhd.fbot.mcnetwork.network.login.server.S02PacketLoginSuccess;
import de.febanhd.fbot.mcnetwork.network.login.server.S03PacketEnableCompression;

public interface INetHandlerLoginClient extends INetHandler {
	void handleEncryptionRequest(S01PacketEncryptionRequest packetIn);

	void handleLoginSuccess(S02PacketLoginSuccess packetIn);

	void handleDisconnect(S00PacketDisconnect packetIn);

	void handleEnableCompression(S03PacketEnableCompression packetIn);
}
