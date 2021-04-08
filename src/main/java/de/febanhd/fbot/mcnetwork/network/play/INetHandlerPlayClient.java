package de.febanhd.fbot.mcnetwork.network.play;

import de.febanhd.fbot.mcnetwork.network.INetHandler;
import de.febanhd.fbot.mcnetwork.network.play.server.*;

public interface INetHandlerPlayClient extends INetHandler {

	void handleBlockBreakAnim(S25PacketBlockBreakAnim packetIn);

	/**
	 * Creates a sign in the specified location if it didn't exist and opens the GUI
	 * to edit its text
	 */
	void handleSignEditorOpen(S36PacketSignEditorOpen packetIn);

	/**
	 * Updates the NBTTagCompound metadata of instances of the following
	 * entitytypes: Mob spawners, command blocks, beacons, skulls, flowerpot
	 */
	void handleUpdateTileEntity(S35PacketUpdateTileEntity packetIn);

	/**
	 * Triggers Block.onBlockEventReceived, which is implemented in BlockPistonBase
	 * for extension/retraction, BlockNote for setting the instrument (including
	 * audiovisual feedback) and in BlockContainer to set the number of players
	 * accessing a (Ender)Chest
	 */

	/**
	 * Prints a chatmessage in the chat GUI
	 */
	void handleChat(S02PacketChat packetIn);

	/**
	 * Displays the available command-completion options the server knows of
	 */
	void handleTabComplete(S3APacketTabComplete packetIn);

	/**
	 * Verifies that the server and client are synchronized with respect to the
	 * inventory/container opened by the player and confirms if it is the case.
	 */
	void handleConfirmTransaction(S32PacketConfirmTransaction packetIn);

	/**
	 * Resets the ItemStack held in hand and closes the window that is opened
	 */
	void handleCloseWindow(S2EPacketCloseWindow packetIn);

	/**
	 * Displays a GUI by ID. In order starting from id 0: Chest, Workbench, Furnace,
	 * Dispenser, Enchanting table, Brewing stand, Villager merchant, Beacon, Anvil,
	 * Hopper, Dropper, Horse
	 */
	void handleOpenWindow(S2DPacketOpenWindow packetIn);

	/**
	 * Sets the progressbar of the opened window to the specified value
	 */
	void handleWindowProperty(S31PacketWindowProperty packetIn);

	/**
	 * Handles packets that have room for a channel specification. Vanilla
	 * implemented channels are "MC|TrList" to acquire a MerchantRecipeList trades
	 * for a villager merchant, "MC|Brand" which sets the server brand? on the
	 * player instance and finally "MC|RPack" which the server uses to communicate
	 * the identifier of the default server resourcepack for the client to load.
	 */
	void handleCustomPayload(S3FPacketCustomPayload packetIn);

	/**
	 * Closes the network channel
	 */
	void handleDisconnect(S40PacketDisconnect packetIn);

	/**
	 * Initiates a new explosion (sound, particles, drop spawn) for the affected
	 * blocks indicated by the packet.
	 */
	void handleExplosion(S27PacketExplosion packetIn);

	void handleChangeGameState(S2BPacketChangeGameState packetIn);

	void handleKeepAlive(S00PacketKeepAlive packetIn);

	/**
	 * Handles changes in player positioning and rotation such as when travelling to
	 * a new dimension, (re)spawning, mounting horses etc. Seems to immediately
	 * reply to the server with the clients post-processing perspective on the
	 * player positioning
	 */
	void handlePlayerPosLook(S08PacketPlayerPosLook packetIn);

	/**
	 * Spawns a specified number of particles at the specified location with a
	 * randomized displacement according to specified bounds
	 */
	void handleParticles(S2APacketParticles packetIn);

	/**
	 * Locally eliminates the entities. Invoked by the server when the items are in
	 * fact destroyed, or the player is no longer registered as required to monitor
	 * them. The latter happens when distance between the player and item increases
	 * beyond a certain treshold (typically the viewing distance)
	 */
	void handleDestroyEntities(S13PacketDestroyEntities packetIn);

	/**
	 * Updates which hotbar slot of the player is currently selected
	 */
	void handleHeldItemChange(S09PacketHeldItemChange packetIn);

	void handleSetExperience(S1FPacketSetExperience packetIn);

	void handleUpdateHealth(S06PacketUpdateHealth packetIn);

	void handleSpawnPosition(S05PacketSpawnPosition packetIn);

	void handleTimeUpdate(S03PacketTimeUpdate packetIn);

	void handleSoundEffect(S29PacketSoundEffect packetIn);

	void handleCollectItem(S0DPacketCollectItem packetIn);

	void handleTitle(S45PacketTitle packetIn);

	void handleSetCompressionLevel(S46PacketSetCompressionLevel packetIn);

	void handlePlayerListHeaderFooter(S47PacketPlayerListHeaderFooter packetIn);

	void handleResourcePack(S48PacketResourcePackSend packetIn);
}
