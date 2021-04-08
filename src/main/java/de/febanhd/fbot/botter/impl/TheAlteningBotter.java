package de.febanhd.fbot.botter.impl;

import de.febanhd.fbot.BotFactory;
import de.febanhd.fbot.bot.FBot;

public class TheAlteningBotter extends AbstractFBotter {

    private final String apiKey;

    public TheAlteningBotter(String apiKey, String host, int port, int capacity) {
        super(host, port, capacity);
        this.apiKey = apiKey;
    }

    @Override
    public FBot createBotInstance() {
        return BotFactory.createAlteningBot(this.apiKey);
    }
}
