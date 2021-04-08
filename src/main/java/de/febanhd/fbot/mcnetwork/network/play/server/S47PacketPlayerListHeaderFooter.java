package de.febanhd.fbot.mcnetwork.network.play.server;

import de.febanhd.fbot.mcnetwork.network.Packet;
import de.febanhd.fbot.mcnetwork.network.PacketBuffer;
import de.febanhd.fbot.mcnetwork.network.play.INetHandlerPlayClient;
import de.febanhd.fbot.mcnetwork.util.IChatComponent;

import java.io.IOException;

public class S47PacketPlayerListHeaderFooter implements Packet<INetHandlerPlayClient> {
	private IChatComponent header;
	private IChatComponent footer;

	public S47PacketPlayerListHeaderFooter() {
	}

	public S47PacketPlayerListHeaderFooter(IChatComponent headerIn) {
		this.header = headerIn;
	}

	/**
	 * Reads the raw packet data from the data stream.
	 */
	public void readPacketData(PacketBuffer buf) throws IOException {
		this.header = buf.readChatComponent();
		this.footer = buf.readChatComponent();
	}

	/**
	 * Writes the raw packet data to the data stream.
	 */
	public void writePacketData(PacketBuffer buf) throws IOException {
		buf.writeChatComponent(this.header);
		buf.writeChatComponent(this.footer);
	}

	/**
	 * Passes this Packet on to the NetHandler for processing.
	 */
	public void processPacket(INetHandlerPlayClient handler) {
		handler.handlePlayerListHeaderFooter(this);
	}

	public IChatComponent getHeader() {
		return this.header;
	}

	public IChatComponent getFooter() {
		return this.footer;
	}
}
