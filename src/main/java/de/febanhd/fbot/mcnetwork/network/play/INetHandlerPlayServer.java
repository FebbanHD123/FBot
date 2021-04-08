package de.febanhd.fbot.mcnetwork.network.play;

import de.febanhd.fbot.mcnetwork.network.INetHandler;
import de.febanhd.fbot.mcnetwork.network.play.client.*;

public interface INetHandlerPlayServer extends INetHandler {
	void handleAnimation(C0APacketAnimation packetIn);

	/**
	 * Process chat messages (broadcast back to clients) and commands (executes)
	 */
	void processChatMessage(C01PacketChatMessage packetIn);

	/**
	 * Retrieves possible tab completions for the requested command string and sends
	 * them to the client
	 */
	void processTabComplete(C14PacketTabComplete packetIn);

	/**
	 * Processes the client status updates: respawn attempt from player, opening
	 * statistics or achievements, or acquiring 'open inventory' achievement
	 */
	void processClientStatus(C16PacketClientStatus packetIn);

	/**
	 * Received in response to the server requesting to confirm that the client-side
	 * open container matches the servers' after a mismatched container-slot
	 * manipulation. It will unlock the player's ability to manipulate the container
	 * contents
	 */
	void processConfirmTransaction(C0FPacketConfirmTransaction packetIn);

	/**
	 * Enchants the item identified by the packet given some convoluted conditions
	 * (matching window, which should/shouldn't be in use?)
	 */
	void processEnchantItem(C11PacketEnchantItem packetIn);
	/**
	 * Processes the client closing windows (container)
	 */
	void processCloseWindow(C0DPacketCloseWindow packetIn);

	/**
	 * Synchronizes serverside and clientside book contents and signing
	 */
	void processVanilla250Packet(C17PacketCustomPayload packetIn);

	/**
	 * Updates a players' ping statistics
	 */
	void processKeepAlive(C00PacketKeepAlive packetIn);

	/**
	 * Processes clients perspective on player positioning and/or orientation
	 */
	void processPlayer(C03PacketPlayer packetIn);

	/**
	 * Processes the player initiating/stopping digging on a particular spot, as
	 * well as a player dropping items?. (0: initiated, 1: reinitiated, 2? , 3-4
	 * drop item (respectively without or with player control), 5: stopped; x,y,z,
	 * side clicked on;)
	 */
	void processPlayerDigging(C07PacketPlayerDigging packetIn);

	/**
	 * Processes player movement input. Includes walking, strafing, jumping,
	 * sneaking; excludes riding and toggling flying/sprinting
	 */
	void processInput(C0CPacketInput packetIn);

	/**
	 * Updates which quickbar slot is selected
	 */
	void processHeldItemChange(C09PacketHeldItemChange packetIn);

	void processUpdateSign(C12PacketUpdateSign packetIn);
}
