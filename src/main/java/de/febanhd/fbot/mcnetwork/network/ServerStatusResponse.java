package de.febanhd.fbot.mcnetwork.network;

import com.google.gson.*;
import de.febanhd.fbot.mcnetwork.util.IChatComponent;
import de.febanhd.fbot.mcnetwork.util.JsonUtils;

import java.lang.reflect.Type;

public class ServerStatusResponse {
	private IChatComponent serverMotd;
	private PlayerCountData playerCount;
	private MinecraftProtocolVersionIdentifier protocolVersion;
	private String favicon;

	public IChatComponent getServerDescription() {
		return this.serverMotd;
	}

	public void setServerDescription(IChatComponent motd) {
		this.serverMotd = motd;
	}

	public PlayerCountData getPlayerCountData() {
		return this.playerCount;
	}

	public void setPlayerCountData(PlayerCountData countData) {
		this.playerCount = countData;
	}

	public MinecraftProtocolVersionIdentifier getProtocolVersionInfo() {
		return this.protocolVersion;
	}

	public void setProtocolVersionInfo(MinecraftProtocolVersionIdentifier protocolVersionData) {
		this.protocolVersion = protocolVersionData;
	}

	public void setFavicon(String faviconBlob) {
		this.favicon = faviconBlob;
	}

	public String getFavicon() {
		return this.favicon;
	}

	public static class MinecraftProtocolVersionIdentifier {
		private final String name;
		private final int protocol;

		public MinecraftProtocolVersionIdentifier(String nameIn, int protocolIn) {
			this.name = nameIn;
			this.protocol = protocolIn;
		}

		public String getName() {
			return this.name;
		}

		public int getProtocol() {
			return this.protocol;
		}

		public static class Serializer
				implements JsonDeserializer<MinecraftProtocolVersionIdentifier>,
				JsonSerializer<MinecraftProtocolVersionIdentifier> {
			public MinecraftProtocolVersionIdentifier deserialize(JsonElement p_deserialize_1_,
					Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
				JsonObject jsonobject = JsonUtils.getJsonObject(p_deserialize_1_, "version");
				return new MinecraftProtocolVersionIdentifier(
						JsonUtils.getString(jsonobject, "name"), JsonUtils.getInt(jsonobject, "protocol"));
			}

			public JsonElement serialize(MinecraftProtocolVersionIdentifier p_serialize_1_,
                                         Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
				JsonObject jsonobject = new JsonObject();
				jsonobject.addProperty("name", p_serialize_1_.getName());
				jsonobject.addProperty("protocol", (Number) Integer.valueOf(p_serialize_1_.getProtocol()));
				return jsonobject;
			}
		}
	}

	public static class PlayerCountData {
		private final int maxPlayers;
		private final int onlinePlayerCount;

		public PlayerCountData(int maxOnlinePlayers, int onlinePlayers) {
			this.maxPlayers = maxOnlinePlayers;
			this.onlinePlayerCount = onlinePlayers;
		}

		public int getMaxPlayers() {
			return this.maxPlayers;
		}

		public int getOnlinePlayerCount() {
			return this.onlinePlayerCount;
		}

		public Object[] getPlayers() {
			return new Object[1];
		}

		public void setPlayers(Object[] playersIn) {

		}

		public static class Serializer implements JsonDeserializer<PlayerCountData>,
				JsonSerializer<PlayerCountData> {
			public PlayerCountData deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_,
					JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
				JsonObject jsonobject = JsonUtils.getJsonObject(p_deserialize_1_, "players");
				PlayerCountData serverstatusresponse$playercountdata = new PlayerCountData(
						JsonUtils.getInt(jsonobject, "max"), JsonUtils.getInt(jsonobject, "online"));

				if (JsonUtils.isJsonArray(jsonobject, "sample")) {
					JsonArray jsonarray = JsonUtils.getJsonArray(jsonobject, "sample");
				}

				return serverstatusresponse$playercountdata;
			}

			public JsonElement serialize(PlayerCountData p_serialize_1_, Type p_serialize_2_,
                                         JsonSerializationContext p_serialize_3_) {
				JsonObject jsonobject = new JsonObject();
				jsonobject.addProperty("max", (Number) Integer.valueOf(p_serialize_1_.getMaxPlayers()));
				jsonobject.addProperty("online", (Number) Integer.valueOf(p_serialize_1_.getOnlinePlayerCount()));

				return jsonobject;
			}
		}
	}

	public static class Serializer
			implements JsonDeserializer<ServerStatusResponse>, JsonSerializer<ServerStatusResponse> {
		public ServerStatusResponse deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_,
				JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
			JsonObject jsonobject = JsonUtils.getJsonObject(p_deserialize_1_, "status");
			ServerStatusResponse serverstatusresponse = new ServerStatusResponse();

			if (jsonobject.has("description")) {
				serverstatusresponse.setServerDescription((IChatComponent) p_deserialize_3_
						.deserialize(jsonobject.get("description"), IChatComponent.class));
			}

			if (jsonobject.has("players")) {
				serverstatusresponse.setPlayerCountData((PlayerCountData) p_deserialize_3_
						.deserialize(jsonobject.get("players"), PlayerCountData.class));
			}

			if (jsonobject.has("version")) {
				serverstatusresponse.setProtocolVersionInfo(
						(MinecraftProtocolVersionIdentifier) p_deserialize_3_.deserialize(
								jsonobject.get("version"),
								MinecraftProtocolVersionIdentifier.class));
			}

			if (jsonobject.has("favicon")) {
				serverstatusresponse.setFavicon(JsonUtils.getString(jsonobject, "favicon"));
			}

			return serverstatusresponse;
		}

		public JsonElement serialize(ServerStatusResponse p_serialize_1_, Type p_serialize_2_,
				JsonSerializationContext p_serialize_3_) {
			JsonObject jsonobject = new JsonObject();

			if (p_serialize_1_.getServerDescription() != null) {
				jsonobject.add("description", p_serialize_3_.serialize(p_serialize_1_.getServerDescription()));
			}

			if (p_serialize_1_.getPlayerCountData() != null) {
				jsonobject.add("players", p_serialize_3_.serialize(p_serialize_1_.getPlayerCountData()));
			}

			if (p_serialize_1_.getProtocolVersionInfo() != null) {
				jsonobject.add("version", p_serialize_3_.serialize(p_serialize_1_.getProtocolVersionInfo()));
			}

			if (p_serialize_1_.getFavicon() != null) {
				jsonobject.addProperty("favicon", p_serialize_1_.getFavicon());
			}

			return jsonobject;
		}
	}
}
