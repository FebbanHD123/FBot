package de.febanhd.fbot.mcnetwork.network;

import com.google.common.primitives.Doubles;
import com.google.common.primitives.Floats;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import de.febanhd.fbot.mcnetwork.network.play.INetHandlerPlayServer;
import de.febanhd.fbot.mcnetwork.network.play.client.*;
import de.febanhd.fbot.mcnetwork.network.play.server.*;
import de.febanhd.fbot.mcnetwork.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NetHandlerPlayServer implements INetHandlerPlayServer, ITickable {
	private static final Logger logger = LogManager.getLogger();
	public final NetworkManager netManager;
	private int networkTickCount;
	private int field_175090_f;

	/**
	 * Used to keep track of how the player is floating while gamerules should
	 * prevent that. Surpassing 80 ticks means kick
	 */
	private int floatingTickCount;
	private boolean field_147366_g;
	private int field_147378_h;
	private long lastPingTime;
	private long lastSentPingPacket;

	/**
	 * Incremented by 20 each time a user sends a chat message, decreased by one
	 * every tick. Non-ops kicked when over 200
	 */
	private int chatSpamThresholdCount;
	private int itemDropThreshold;
	private IntHashMap<Short> field_147372_n = new IntHashMap();
	private double lastPosX;
	private double lastPosY;
	private double lastPosZ;
	private boolean hasMoved = true;

	public NetHandlerPlayServer(NetworkManager networkManagerIn) {
		this.netManager = networkManagerIn;
		networkManagerIn.setNetHandler(this);
	}

	/**
	 * Like the old updateEntity(), except more generic.
	 */
	public void update() {
		this.field_147366_g = false;
		++this.networkTickCount;

		if ((long) this.networkTickCount - this.lastSentPingPacket > 40L) {
			this.lastSentPingPacket = (long) this.networkTickCount;
			this.lastPingTime = this.currentTimeMillis();
			this.field_147378_h = (int) this.lastPingTime;
			this.sendPacket(new S00PacketKeepAlive(this.field_147378_h));
		}

		if (this.chatSpamThresholdCount > 0) {
			--this.chatSpamThresholdCount;
		}

		if (this.itemDropThreshold > 0) {
			--this.itemDropThreshold;
		}
	}

	public NetworkManager getNetworkManager() {
		return this.netManager;
	}

	/**
	 * Kick a player from the server with a reason
	 */
	public void kickPlayerFromServer(String reason) {
		final ChatComponentText chatcomponenttext = new ChatComponentText(reason);
		this.netManager.sendPacket(new S40PacketDisconnect(chatcomponenttext),
				new GenericFutureListener<Future<? super Void>>() {
					public void operationComplete(Future<? super Void> p_operationComplete_1_) throws Exception {
						NetHandlerPlayServer.this.netManager.closeChannel(chatcomponenttext);
					}
				}, new GenericFutureListener[0]);
		this.netManager.disableAutoRead();
	}

	/**
	 * Processes player movement input. Includes walking, strafing, jumping,
	 * sneaking; excludes riding and toggling flying/sprinting
	 */
	public void processInput(C0CPacketInput packetIn) {

	}

	private boolean func_183006_b(C03PacketPlayer p_183006_1_) {
		return !Doubles.isFinite(p_183006_1_.getPositionX()) || !Doubles.isFinite(p_183006_1_.getPositionY())
				|| !Doubles.isFinite(p_183006_1_.getPositionZ()) || !Floats.isFinite(p_183006_1_.getPitch())
				|| !Floats.isFinite(p_183006_1_.getYaw());
	}

	/**
	 * Processes the player initiating/stopping digging on a particular spot, as
	 * well as a player dropping items?. (0: initiated, 1: reinitiated, 2? , 3-4
	 * drop item (respectively without or with player control), 5: stopped; x,y,z,
	 * side clicked on;)
	 */
	public void processPlayerDigging(C07PacketPlayerDigging packetIn) {

	}

	/**
	 * Invoked when disconnecting, the parameter is a ChatComponent describing the
	 * reason for termination
	 */
	public void onDisconnect(IChatComponent reason) {
        System.out.println("Disconnected from server lol");
	}

	public void sendPacket(final Packet packetIn) {

		try {
			this.netManager.sendPacket(packetIn);
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
	}

	/**
	 * Updates which quickbar slot is selected
	 */
	public void processHeldItemChange(C09PacketHeldItemChange packetIn) {

    }

    @Override
    public void processUpdateSign(C12PacketUpdateSign packetIn) {

    }

    @Override
    public void handleAnimation(C0APacketAnimation packetIn) {

    }

    /**
	 * Process chat messages (broadcast back to clients) and commands (executes)
	 */
	public void processChatMessage(C01PacketChatMessage packetIn) {
        System.out.println("[Chat]" + packetIn.getMessage());
	}

    @Override
    public void processTabComplete(C14PacketTabComplete packetIn) {

    }

    @Override
    public void processClientStatus(C16PacketClientStatus packetIn) {

    }

    @Override
    public void processConfirmTransaction(C0FPacketConfirmTransaction packetIn) {

    }

    @Override
    public void processEnchantItem(C11PacketEnchantItem packetIn) {

    }

    @Override
    public void processCloseWindow(C0DPacketCloseWindow packetIn) {

    }

    @Override
    public void processVanilla250Packet(C17PacketCustomPayload packetIn) {

    }

    /**
	 * Updates a players' ping statistics
	 */
	public void processKeepAlive(C00PacketKeepAlive packetIn) {
		if (packetIn.getKey() == this.field_147378_h) {
			int i = (int) (this.currentTimeMillis() - this.lastPingTime);
		}
	}

    @Override
    public void processPlayer(C03PacketPlayer packetIn) {

    }

    private long currentTimeMillis() {
		return System.nanoTime() / 1000000L;
	}

}
