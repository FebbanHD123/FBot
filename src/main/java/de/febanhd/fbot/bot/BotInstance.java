package de.febanhd.fbot.bot;

import com.mojang.authlib.minecraft.MinecraftSessionService;
import de.febanhd.fbot.mcnetwork.network.NetworkManager;
import de.febanhd.fbot.mcnetwork.network.play.client.C01PacketChatMessage;
import de.febanhd.fbot.mcnetwork.util.ChatComponentText;
import de.febanhd.fbot.mcnetwork.util.Session;

public class BotInstance implements FBot {

    private MinecraftSessionService sessionService;
    private Session session;
    private BotConnection botConnection;
    private LoginType loginType;

    public BotInstance(Session session, MinecraftSessionService sessionService, LoginType loginType) {
        this.sessionService = sessionService;
        this.session = session;
        this.loginType = loginType;
    }

    @Override
    public void connect(String host, int port, Runnable callback) {
        this.botConnection = new BotConnection(this.session, this.sessionService, this);
        this.botConnection.connect(host, port, callback);
    }

    @Override
    public void disconnect() {
        this.botConnection.getNetworkManager().closeChannel(new ChatComponentText("Diconnecting"));
    }

    @Override
    public void sendChatMessage(String message) {
        getNetworkManager().sendPacket(new C01PacketChatMessage(message));
    }

    @Override
    public NetworkManager getNetworkManager() {
        return this.botConnection.getNetworkManager();
    }

    @Override
    public boolean isConnected() {
        return this.botConnection.getNetworkManager().isChannelOpen();
    }

    @Override
    public String getName() {
        return this.session.getUsername();
    }

    @Override
    public LoginType getLoginType() {
        return loginType;
    }

    public static enum LoginType {
        ALTENING, MOJANG;
    }
}
