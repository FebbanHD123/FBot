package de.febanhd.fbot.botter.impl;

import com.google.common.collect.Lists;
import de.febanhd.fbot.BotFactory;
import de.febanhd.fbot.bot.FBot;
import de.febanhd.fbot.botter.FBotter;

import java.util.ArrayList;
import java.util.Collection;

public abstract class AbstractFBotter implements FBotter {

    private final ArrayList<FBot> connectedBots = Lists.newArrayList();
    protected final int capacity;
    private final String host;
    private final int port;
    private boolean running;

    public AbstractFBotter(String host, int port, int capacity) {
        this.capacity = capacity;
        this.host = host;
        this.port = port;
    }

    @Override
    public void start() {
        this.running = true;
        for(int i = 0; i < this.capacity; i++) {
            this.registerBot(this.createBotInstance());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stop() {
        synchronized (this.connectedBots) {
            this.connectedBots.forEach(FBot::disconnect);
        }
    }

    public abstract FBot createBotInstance();

    @Override
    public Collection<FBot> getConnectedBots() {
        return this.connectedBots;
    }

    protected void registerBot(final FBot bot) {
        bot.connect(this.host, this.port, () -> {
            synchronized (connectedBots) {
                if(!bot.isConnected()) return;
                this.connectedBots.add(bot);
            }
        });
    }
}
