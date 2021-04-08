package de.febanhd.fbot.bot;

import de.febanhd.fbot.mcnetwork.network.NetworkManager;

public interface FBot {

    void connect(String host, int port, Runnable callback);

    void disconnect();

    void sendChatMessage(String message);

    NetworkManager getNetworkManager();

    boolean isConnected();

    String getName();

    BotInstance.LoginType getLoginType();
}
