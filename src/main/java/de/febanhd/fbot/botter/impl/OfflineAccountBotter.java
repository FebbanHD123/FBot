package de.febanhd.fbot.botter.impl;

import de.febanhd.fbot.BotFactory;
import de.febanhd.fbot.bot.FBot;

import java.util.UUID;

public class OfflineAccountBotter extends AbstractFBotter {

    private final String name;
    private int i = 0;

    public OfflineAccountBotter(String host, int port, int capacity, String nameFormat) {
        super(host, port, capacity);
        this.name = nameFormat;
    }

    @Override
    public FBot createBotInstance() {
        ++i;
        return BotFactory.createBot(String.format(this.name, i));
    }
}
