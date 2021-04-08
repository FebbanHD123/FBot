package de.febanhd.fbot.mcnetwork.network;

import com.google.common.base.Charsets;
import com.mojang.authlib.GameProfile;
import de.febanhd.fbot.mcnetwork.network.login.INetHandlerLoginServer;
import de.febanhd.fbot.mcnetwork.network.login.client.C00PacketLoginStart;
import de.febanhd.fbot.mcnetwork.network.login.client.C01PacketEncryptionResponse;
import de.febanhd.fbot.mcnetwork.network.login.server.S00PacketDisconnect;
import de.febanhd.fbot.mcnetwork.util.ChatComponentText;
import de.febanhd.fbot.mcnetwork.util.IChatComponent;
import de.febanhd.fbot.mcnetwork.util.ITickable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.SecretKey;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class NetHandlerLoginServer implements INetHandlerLoginServer, ITickable {
	private static final AtomicInteger AUTHENTICATOR_THREAD_ID = new AtomicInteger(0);
	private static final Logger logger = LogManager.getLogger();
	private static final Random RANDOM = new Random();
	private final byte[] verifyToken = new byte[4];
	public final NetworkManager networkManager;
	private LoginState currentLoginState = LoginState.HELLO;

	/** How long has player been trying to login into the server. */
	private int connectionTimer;
	private GameProfile loginGameProfile;
	private String serverId = "";
	private SecretKey secretKey;

	public NetHandlerLoginServer(NetworkManager p_i45298_2_) {
		this.networkManager = p_i45298_2_;
		RANDOM.nextBytes(this.verifyToken);
	}

	/**
	 * Like the old updateEntity(), except more generic.
	 */
	public void update() {
		if (this.currentLoginState == LoginState.READY_TO_ACCEPT) {
			this.tryAcceptPlayer();
		}

		if (this.connectionTimer++ == 600) {
			this.closeConnection("Took too long to log in");
		}
	}

	public void closeConnection(String reason) {
		try {
			logger.info("Disconnecting " + this.getConnectionInfo() + ": " + reason);
			ChatComponentText chatcomponenttext = new ChatComponentText(reason);
			this.networkManager.sendPacket(new S00PacketDisconnect(chatcomponenttext));
			this.networkManager.closeChannel(chatcomponenttext);
		} catch (Exception exception) {
			logger.error((String) "Error whilst disconnecting player", (Throwable) exception);
		}
	}

	public void tryAcceptPlayer() {

	}

	/**
	 * Invoked when disconnecting, the parameter is a ChatComponent describing the
	 * reason for termination
	 */
	public void onDisconnect(IChatComponent reason) {
		logger.info(this.getConnectionInfo() + " lost connection: " + reason.getUnformattedText());
	}

	public String getConnectionInfo() {
		return this.loginGameProfile != null
				? this.loginGameProfile.toString() + " (" + this.networkManager.getRemoteAddress().toString() + ")"
				: String.valueOf((Object) this.networkManager.getRemoteAddress());
	}

	public void processLoginStart(C00PacketLoginStart packetIn) {

	}

	public void processEncryptionResponse(C01PacketEncryptionResponse packetIn) {

	}

	protected GameProfile getOfflineProfile(GameProfile original) {
		UUID uuid = UUID.nameUUIDFromBytes(("OfflinePlayer:" + original.getName()).getBytes(Charsets.UTF_8));
		return new GameProfile(uuid, original.getName());
	}

	static enum LoginState {
		HELLO, KEY, AUTHENTICATING, READY_TO_ACCEPT, DELAY_ACCEPT, ACCEPTED;
	}
}
