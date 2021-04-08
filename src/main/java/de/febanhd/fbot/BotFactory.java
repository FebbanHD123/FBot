package de.febanhd.fbot;

import de.febanhd.fbot.bot.FBot;
import de.febanhd.fbot.bot.BotInstance;
import de.febanhd.fbot.util.SessionUtil;

public class BotFactory {

    public static FBot createBot(String name) {
        return new BotInstance(SessionUtil.generateOfflineSession(name), SessionUtil.newSessionService(), BotInstance.LoginType.MOJANG);
    }

    public static FBot createBot(String name, String password) {
        return new BotInstance(SessionUtil.generateOnlineSession(name, password), SessionUtil.newSessionService(), BotInstance.LoginType.MOJANG);
    }

    public static FBot createAlteningBot(String apiKey) {
        SessionUtil.enableAlteningAuthentication();
        FBot bot = new BotInstance(SessionUtil.generateAlteningSession(apiKey), SessionUtil.newSessionService(), BotInstance.LoginType.ALTENING);
        SessionUtil.disableAlteningAuthentication();
        return bot;
    }
}
