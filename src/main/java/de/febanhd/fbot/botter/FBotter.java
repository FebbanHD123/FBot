package de.febanhd.fbot.botter;

import de.febanhd.fbot.bot.FBot;

import java.util.Collection;

public interface FBotter {

    void start();

    void stop();

    Collection<FBot> getConnectedBots();
}
