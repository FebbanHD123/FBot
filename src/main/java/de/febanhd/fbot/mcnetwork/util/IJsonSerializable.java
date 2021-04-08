package de.febanhd.fbot.mcnetwork.util;

import com.google.gson.JsonElement;

public interface IJsonSerializable {
	void fromJson(JsonElement json);

	/**
	 * Gets the JsonElement that can be serialized.
	 */
	JsonElement getSerializableElement();
}
