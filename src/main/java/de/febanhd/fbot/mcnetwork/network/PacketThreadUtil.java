package de.febanhd.fbot.mcnetwork.network;

import de.febanhd.fbot.mcnetwork.util.IThreadListener;

public class PacketThreadUtil {
	public static <T extends INetHandler> void checkThreadAndEnqueue(final Packet<T> p_180031_0_, final T p_180031_1_,
			IThreadListener p_180031_2_) throws ThreadQuickExitException {
		if (!p_180031_2_.isCallingFromMinecraftThread()) {
			p_180031_2_.addScheduledTask(new Runnable() {
				public void run() {
					try {
						p_180031_0_.processPacket(p_180031_1_);
					}catch(Exception e) {}
				}
			});
			throw ThreadQuickExitException.field_179886_a;
		}
	}
}
