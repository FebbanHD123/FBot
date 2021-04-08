package de.febanhd.fbot.util;

import com.mojang.authlib.Agent;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import com.thealtening.api.TheAltening;
import com.thealtening.api.response.Account;
import com.thealtening.auth.TheAlteningAuthentication;
import com.thealtening.auth.service.AlteningServiceType;
import de.febanhd.fbot.mcnetwork.util.Session;

import java.net.Proxy;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SessionUtil {

    public static Session generateOnlineSession(String user, String password) {
        final YggdrasilUserAuthentication auth = (YggdrasilUserAuthentication) new YggdrasilAuthenticationService(Proxy.NO_PROXY, "")
                .createUserAuthentication(Agent.MINECRAFT);
        auth.setUsername(user);
        auth.setPassword(password);
        try {
            auth.logIn();
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return new Session(auth.getSelectedProfile().getName(), auth.getSelectedProfile().getId().toString(), auth.getAuthenticatedToken(), "mojang");
    }

    public static Session generateOfflineSession(String user) {
        return new Session(user, "-", "-", "legacy");
    }

    public static Session generateAlteningSession(String apiKey) {
        Account account = TheAltening.newBasicRetriever(apiKey).getAccount();
        return generateOnlineSession(account.getToken(), "FBotter");
    }

    public static MinecraftSessionService newSessionService() {
        return new YggdrasilAuthenticationService(Proxy.NO_PROXY, "").createMinecraftSessionService();
    }

    public static void enableAlteningAuthentication() {
        TheAlteningAuthentication.theAltening().updateService(AlteningServiceType.THEALTENING);
    }

    public static void disableAlteningAuthentication() {
        TheAlteningAuthentication.mojang().updateService(AlteningServiceType.MOJANG);
    }
}
