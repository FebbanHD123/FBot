package de.febanhd.fbot.mcnetwork.network;

import com.mojang.authlib.GameProfile;
import de.febanhd.fbot.mcnetwork.util.IChatComponent;
import de.febanhd.fbot.mcnetwork.util.ResourceLocation;

public class NetworkPlayerInfo {
	/**
	 * The GameProfile for the player represented by this NetworkPlayerInfo instance
	 */
	private final GameProfile gameProfile;

	/** Player response time to server in milliseconds */
	private int responseTime;
	private boolean playerTexturesLoaded = false;
	private ResourceLocation locationSkin;
	private ResourceLocation locationCape;
	private String skinType;

	/**
	 * When this is non-null, it is displayed instead of the player's real name
	 */
	private IChatComponent displayName;
	private int field_178873_i = 0;
	private int field_178870_j = 0;
	private long field_178871_k = 0L;
	private long field_178868_l = 0L;
	private long field_178869_m = 0L;

    public NetworkPlayerInfo(GameProfile gameProfile) {
        this.gameProfile = gameProfile;
    }


    /**
	 * Returns the GameProfile for the player represented by this NetworkPlayerInfo
	 * instance
	 */
	public GameProfile getGameProfile() {
		return this.gameProfile;
	}

	protected void loadPlayerTextures() {
		synchronized (this) {
			if (!this.playerTexturesLoaded) {
				this.playerTexturesLoaded = true;
			}
		}
	}

	public void setDisplayName(IChatComponent displayNameIn) {
		this.displayName = displayNameIn;
	}

	public IChatComponent getDisplayName() {
		return this.displayName;
	}

	public int func_178835_l() {
		return this.field_178873_i;
	}

	public void func_178836_b(int p_178836_1_) {
		this.field_178873_i = p_178836_1_;
	}

	public int func_178860_m() {
		return this.field_178870_j;
	}

	public void func_178857_c(int p_178857_1_) {
		this.field_178870_j = p_178857_1_;
	}

	public long func_178847_n() {
		return this.field_178871_k;
	}

	public void func_178846_a(long p_178846_1_) {
		this.field_178871_k = p_178846_1_;
	}

	public long func_178858_o() {
		return this.field_178868_l;
	}

	public void func_178844_b(long p_178844_1_) {
		this.field_178868_l = p_178844_1_;
	}

	public long func_178855_p() {
		return this.field_178869_m;
	}

	public void func_178843_c(long p_178843_1_) {
		this.field_178869_m = p_178843_1_;
	}
}
