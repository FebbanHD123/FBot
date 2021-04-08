package de.febanhd.fbot.mcnetwork.client.network;

import java.util.Collection;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import de.febanhd.fbot.mcnetwork.network.NetworkManager;
import de.febanhd.fbot.mcnetwork.network.NetworkPlayerInfo;
import de.febanhd.fbot.mcnetwork.network.Packet;
import de.febanhd.fbot.mcnetwork.network.play.client.C00PacketKeepAlive;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.Maps;
import com.mojang.authlib.GameProfile;

import de.febanhd.fbot.mcnetwork.network.play.INetHandlerPlayClient;
import de.febanhd.fbot.mcnetwork.network.play.client.C0FPacketConfirmTransaction;
import de.febanhd.fbot.mcnetwork.network.play.server.S00PacketKeepAlive;
import de.febanhd.fbot.mcnetwork.network.play.server.S02PacketChat;
import de.febanhd.fbot.mcnetwork.network.play.server.S03PacketTimeUpdate;
import de.febanhd.fbot.mcnetwork.network.play.server.S05PacketSpawnPosition;
import de.febanhd.fbot.mcnetwork.network.play.server.S06PacketUpdateHealth;
import de.febanhd.fbot.mcnetwork.network.play.server.S08PacketPlayerPosLook;
import de.febanhd.fbot.mcnetwork.network.play.server.S09PacketHeldItemChange;
import de.febanhd.fbot.mcnetwork.network.play.server.S0DPacketCollectItem;
import de.febanhd.fbot.mcnetwork.network.play.server.S13PacketDestroyEntities;
import de.febanhd.fbot.mcnetwork.network.play.server.S1FPacketSetExperience;
import de.febanhd.fbot.mcnetwork.network.play.server.S25PacketBlockBreakAnim;
import de.febanhd.fbot.mcnetwork.network.play.server.S27PacketExplosion;
import de.febanhd.fbot.mcnetwork.network.play.server.S29PacketSoundEffect;
import de.febanhd.fbot.mcnetwork.network.play.server.S2APacketParticles;
import de.febanhd.fbot.mcnetwork.network.play.server.S2BPacketChangeGameState;
import de.febanhd.fbot.mcnetwork.network.play.server.S2DPacketOpenWindow;
import de.febanhd.fbot.mcnetwork.network.play.server.S2EPacketCloseWindow;
import de.febanhd.fbot.mcnetwork.network.play.server.S31PacketWindowProperty;
import de.febanhd.fbot.mcnetwork.network.play.server.S32PacketConfirmTransaction;
import de.febanhd.fbot.mcnetwork.network.play.server.S35PacketUpdateTileEntity;
import de.febanhd.fbot.mcnetwork.network.play.server.S36PacketSignEditorOpen;
import de.febanhd.fbot.mcnetwork.network.play.server.S3APacketTabComplete;
import de.febanhd.fbot.mcnetwork.network.play.server.S3FPacketCustomPayload;
import de.febanhd.fbot.mcnetwork.network.play.server.S40PacketDisconnect;
import de.febanhd.fbot.mcnetwork.network.play.server.S45PacketTitle;
import de.febanhd.fbot.mcnetwork.network.play.server.S46PacketSetCompressionLevel;
import de.febanhd.fbot.mcnetwork.network.play.server.S47PacketPlayerListHeaderFooter;
import de.febanhd.fbot.mcnetwork.network.play.server.S48PacketResourcePackSend;
import de.febanhd.fbot.mcnetwork.util.IChatComponent;

public class NetHandlerPlayClient implements INetHandlerPlayClient {
	private static final Logger logger = LogManager.getLogger();

	/**
	 * The NetworkManager instance used to communicate with the server (used only by
	 * handlePlayerPosLook to update positioning and handleJoinGame to inform the
	 * server of the client distribution/mods)
	 */
	public NetworkManager netManager;
	private final GameProfile profile;

	/**
	 * True if the client has finished downloading terrain and may spawn. Set upon
	 * receipt of S08PacketPlayerPosLook, reset upon respawning
	 */
	private boolean doneLoadingTerrain;
	private final Map<UUID, NetworkPlayerInfo> playerInfoMap = Maps.<UUID, NetworkPlayerInfo>newHashMap();
	public int currentServerMaxPlayers = 20;
	private boolean field_147308_k = false;

	/**
	 * Just an ordinary random number generator, used to randomize audio pitch of
	 * item/orb pickup and randomize both particlespawn offset and velocity
	 */
	private final Random avRandomizer = new Random();

	public NetHandlerPlayClient(NetworkManager p_i46300_3_,
			GameProfile p_i46300_4_) {
		this.netManager = p_i46300_3_;
		this.profile = p_i46300_4_;

	}

    @Override
    public void handleTitle(S45PacketTitle packetIn) {

    }

    @Override
    public void handleSetCompressionLevel(S46PacketSetCompressionLevel packetIn) {

    }

    @Override
    public void handlePlayerListHeaderFooter(S47PacketPlayerListHeaderFooter packetIn) {

    }

    @Override
    public void handleResourcePack(S48PacketResourcePackSend packetIn) {

    }

    /**
	 * Updates which hotbar slot of the player is currently selected
	 */
	public void handleHeldItemChange(S09PacketHeldItemChange packetIn) {

	}

	/**
	 * Locally eliminates the entities. Invoked by the server when the items are in
	 * fact destroyed, or the player is no longer registered as required to monitor
	 * them. The latter happens when distance between the player and item increases
	 * beyond a certain treshold (typically the viewing distance)
	 */
	public void handleDestroyEntities(S13PacketDestroyEntities packetIn) {

	}

    /**
	 * Handles changes in player positioning and rotation such as when travelling to
	 * a new dimension, (re)spawning, mounting horses etc. Seems to immediately
	 * reply to the server with the clients post-processing perspective on the
	 * player positioning
	 */
	public void handlePlayerPosLook(S08PacketPlayerPosLook packetIn) {

	}

    @Override
    public void handleParticles(S2APacketParticles packetIn) {

    }

    /**
	 * Closes the network channel
	 */
	public static IChatComponent lastMSG = null;
	
	public void handleDisconnect(S40PacketDisconnect packetIn) {
		this.netManager.closeChannel(packetIn.getReason());
		lastMSG = packetIn.getReason();
		
	}

	/**
	 * Invoked when disconnecting, the parameter is a ChatComponent describing the
	 * reason for termination
	 */
	public void onDisconnect(IChatComponent reason) {

	}

	public void addToSendQueue(Packet p_147297_1_) {
		this.netManager.sendPacket(p_147297_1_);
	}

	public void handleCollectItem(S0DPacketCollectItem packetIn) {

	}

	/**
	 * Prints a chatmessage in the chat GUI
	 */
	public void handleChat(S02PacketChat packetIn) {

	}

    @Override
    public void handleTabComplete(S3APacketTabComplete packetIn) {

    }


    public void handleTimeUpdate(S03PacketTimeUpdate packetIn) {

	}

	public void handleSpawnPosition(S05PacketSpawnPosition packetIn) {

	}

	public void handleUpdateHealth(S06PacketUpdateHealth packetIn) {

	}

    public void handleSetExperience(S1FPacketSetExperience packetIn) {

    }

	/**
	 * Initiates a new explosion (sound, particles, drop spawn) for the affected
	 * blocks indicated by the packet.
	 */
	public void handleExplosion(S27PacketExplosion packetIn) {

	}

    @Override
    public void handleChangeGameState(S2BPacketChangeGameState packetIn) {

    }

    @Override
    public void handleKeepAlive(S00PacketKeepAlive packetIn) {
        this.addToSendQueue(new C00PacketKeepAlive(packetIn.getId()));
    }

    /**
	 * Displays a GUI by ID. In order starting from id 0: Chest, Workbench, Furnace,
	 * Dispenser, Enchanting table, Brewing stand, Villager merchant, Beacon, Anvil,
	 * Hopper, Dropper, Horse
	 */
	public void handleOpenWindow(S2DPacketOpenWindow packetIn) {

	}

    @Override
    public void handleWindowProperty(S31PacketWindowProperty packetIn) {

    }

    @Override
    public void handleCustomPayload(S3FPacketCustomPayload packetIn) {

    }

    /**
	 * Verifies that the server and client are synchronized with respect to the
	 * inventory/container opened by the player and confirms if it is the case.
	 */
	public void handleConfirmTransaction(S32PacketConfirmTransaction packetIn) {
	    this.addToSendQueue(new C0FPacketConfirmTransaction(packetIn.getWindowId(), packetIn.getActionNumber(), true));
	}

    @Override
    public void handleCloseWindow(S2EPacketCloseWindow packetIn) {

    }

    @Override
    public void handleBlockBreakAnim(S25PacketBlockBreakAnim packetIn) {

    }

    /**
	 * Creates a sign in the specified location if it didn't exist and opens the GUI
	 * to edit its text
	 */
	public void handleSignEditorOpen(S36PacketSignEditorOpen packetIn) {

	}

    @Override
    public void handleUpdateTileEntity(S35PacketUpdateTileEntity packetIn) {

    }

    @Override
    public void handleSoundEffect(S29PacketSoundEffect packetIn) {

    }


    /**
	 * Returns this the NetworkManager instance registered with this
	 * NetworkHandlerPlayClient
	 */
	public NetworkManager getNetworkManager() {
		return this.netManager;
	}

	public Collection<NetworkPlayerInfo> getPlayerInfoMap() {
		return this.playerInfoMap.values();
	}

	public NetworkPlayerInfo getPlayerInfo(UUID p_175102_1_) {
		return (NetworkPlayerInfo) this.playerInfoMap.get(p_175102_1_);
	}

	/**
	 * Gets the client's description information about another player on the server.
	 */
	public NetworkPlayerInfo getPlayerInfo(String p_175104_1_) {
		for (NetworkPlayerInfo networkplayerinfo : this.playerInfoMap.values()) {
			if (networkplayerinfo.getGameProfile().getName().equals(p_175104_1_)) {
				return networkplayerinfo;
			}
		}

		return null;
	}

	public GameProfile getGameProfile() {
		return this.profile;
	}
}
