package de.febanhd.fbot.bot;

import com.mojang.authlib.minecraft.MinecraftSessionService;
import de.febanhd.fbot.mcnetwork.client.network.NetHandlerLoginClient;
import io.netty.channel.epoll.Epoll;
import lombok.Getter;
import de.febanhd.fbot.mcnetwork.network.EnumConnectionState;
import de.febanhd.fbot.mcnetwork.network.NetworkManager;
import de.febanhd.fbot.mcnetwork.network.handshake.client.C00Handshake;
import de.febanhd.fbot.mcnetwork.network.login.client.C00PacketLoginStart;
import de.febanhd.fbot.mcnetwork.util.Session;

import java.net.InetAddress;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class BotConnection {

    private static final AtomicInteger CONNECTION_ID = new AtomicInteger(0);

    private NetworkManager networkManager;

    private final Session session;
    private final MinecraftSessionService sessionService;
    private Runnable callback;
    private final FBot bot;

    public BotConnection(Session session, MinecraftSessionService sessionService, FBot bot) {
        this.session = session;
        this.sessionService = sessionService;
        this.bot = bot;
    }

    public void connect(String ip, int port, Runnable callback) {
        this.callback = callback;
        new Thread(() -> {
            try {
                InetAddress inetAddress = InetAddress.getByName(ip);
                this.networkManager = NetworkManager.createNetworkManager(inetAddress, port, Epoll.isAvailable(), this.bot, this.callback);
                this.networkManager.setNetHandler(new NetHandlerLoginClient(this.networkManager, this.session, this.sessionService));
                this.networkManager.sendPacket(new C00Handshake(47, ip, port, EnumConnectionState.LOGIN));
                this.networkManager.sendPacket(new C00PacketLoginStart(session.getProfile()));

            }catch (Exception e) {
                e.printStackTrace();
            }
        }, "Server Connector #" + CONNECTION_ID.incrementAndGet()).start();
    }
}
