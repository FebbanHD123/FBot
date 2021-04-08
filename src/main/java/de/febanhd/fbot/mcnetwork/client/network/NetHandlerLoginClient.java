package de.febanhd.fbot.mcnetwork.client.network;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.exceptions.AuthenticationUnavailableException;
import com.mojang.authlib.exceptions.InvalidCredentialsException;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.thealtening.auth.TheAlteningAuthentication;
import de.febanhd.fbot.bot.BotInstance;
import de.febanhd.fbot.util.SessionUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import de.febanhd.fbot.mcnetwork.network.EnumConnectionState;
import de.febanhd.fbot.mcnetwork.network.NetworkManager;
import de.febanhd.fbot.mcnetwork.network.login.INetHandlerLoginClient;
import de.febanhd.fbot.mcnetwork.network.login.client.C01PacketEncryptionResponse;
import de.febanhd.fbot.mcnetwork.network.login.server.S00PacketDisconnect;
import de.febanhd.fbot.mcnetwork.network.login.server.S01PacketEncryptionRequest;
import de.febanhd.fbot.mcnetwork.network.login.server.S02PacketLoginSuccess;
import de.febanhd.fbot.mcnetwork.network.login.server.S03PacketEnableCompression;
import de.febanhd.fbot.mcnetwork.util.ChatComponentTranslation;
import de.febanhd.fbot.mcnetwork.util.CryptManager;
import de.febanhd.fbot.mcnetwork.util.IChatComponent;
import de.febanhd.fbot.mcnetwork.util.Session;

import javax.crypto.SecretKey;
import java.math.BigInteger;
import java.security.PublicKey;

public class NetHandlerLoginClient implements INetHandlerLoginClient {
	private final NetworkManager networkManager;
	private GameProfile gameProfile;

	private boolean handleEncryptionRequest = false;
	private boolean enableCompression = false;
	private boolean loginSuccess = false;
	private boolean loginStart = false;
	private boolean sendHandshake = false;

	private Session session;
	private MinecraftSessionService sessionService;

	public NetHandlerLoginClient(NetworkManager p_i45059_1_, Session session, MinecraftSessionService sessionService) {
		this.networkManager = p_i45059_1_;
		this.session = session;
		this.sessionService = sessionService;
	}

	public void handleEncryptionRequest(S01PacketEncryptionRequest packetIn) {
		this.handleEncryptionRequest = true;
		final SecretKey secretkey = CryptManager.createNewSharedKey();
		String s = packetIn.getServerId();
		PublicKey publickey = packetIn.getPublicKey();
		String s1 = (new BigInteger(CryptManager.getServerIdHash(s, publickey, secretkey))).toString(16);

			try {
			    if(this.networkManager.getBot().getLoginType() == BotInstance.LoginType.ALTENING) {
                    SessionUtil.enableAlteningAuthentication();
			        this.sessionService.joinServer(session.getProfile(), session.getToken(), s1);
			        SessionUtil.disableAlteningAuthentication();
                }
			} catch (AuthenticationUnavailableException var7) {
				this.networkManager.closeChannel(new ChatComponentTranslation("disconnect.loginFailedInfo",
						new Object[] { new ChatComponentTranslation("disconnect.loginFailedInfo.serversUnavailable",
								new Object[0]) }));
				return;
			} catch (InvalidCredentialsException var8) {
				this.networkManager.closeChannel(new ChatComponentTranslation("disconnect.loginFailedInfo",
						new Object[] { new ChatComponentTranslation("disconnect.loginFailedInfo.invalidSession",
								new Object[0]) }));
				return;
			} catch (AuthenticationException authenticationexception) {
				this.networkManager.closeChannel(new ChatComponentTranslation("disconnect.loginFailedInfo",
						new Object[] { authenticationexception.getMessage() }));
				return;
			}catch (Exception e) {
				e.printStackTrace();
			}

		this.networkManager.sendPacket(new C01PacketEncryptionResponse(secretkey, publickey, packetIn.getVerifyToken()),
                p_operationComplete_1_ -> NetHandlerLoginClient.this.networkManager.enableEncryption(secretkey), new GenericFutureListener[0]);
	}

	public void handleLoginSuccess(S02PacketLoginSuccess packetIn) {
		this.loginSuccess = true;
		this.gameProfile = packetIn.getProfile();
		this.networkManager.setConnectionState(EnumConnectionState.PLAY);
		this.networkManager.setNetHandler(new NetHandlerPlayClient(this.networkManager, this.gameProfile));
		if(this.networkManager.connectingCallback != null)
		    this.networkManager.connectingCallback.run();
	}

	/**
	 * Invoked when disconnecting, the parameter is a ChatComponent describing the
	 * reason for termination
	 */
	public void onDisconnect(IChatComponent reason) {
        System.out.println("Diconnected from server");
	}

	public void handleDisconnect(S00PacketDisconnect packetIn) {
		this.networkManager.closeChannel(packetIn.func_149603_c());
	}

	public void handleEnableCompression(S03PacketEnableCompression packetIn) {
		this.enableCompression = true;
		if (!this.networkManager.isLocalChannel()) {
			this.networkManager.setCompressionTreshold(packetIn.getCompressionTreshold());
		}
	}

	public boolean isEnableCompression() {
		return enableCompression;
	}

	public boolean isHandleEncryptionRequest() {
		return handleEncryptionRequest;
	}

	public boolean isLoginSuccess() {
		return loginSuccess;
	}

	public boolean isLoginStart() {
		return loginStart;
	}

	public boolean isSendHandshake() {
		return sendHandshake;
	}

	public void setLoginStart(boolean loginStart) {
		this.loginStart = loginStart;
	}

	public void setSendHandshake(boolean sendHandshake) {
		this.sendHandshake = sendHandshake;
	}
}
