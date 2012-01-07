package net.vvakame.vvakame10js.util;

import net.vvakame.vvakame10js.model.Favorite;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.json.Default;
import org.slim3.datastore.json.JsonReader;
import org.slim3.datastore.json.JsonWriter;
import org.slim3.util.StringUtil;

import com.google.appengine.api.datastore.Key;

public class KeyIdEncoder extends Default {

	@Override
	public void encode(JsonWriter writer, Key value) {
		if (value == null) {
			writer.writeValue(-1);
			return;
		}
		writer.writeValue(value.getId());
	}

	@Override
	public Key decode(JsonReader reader, Key defaultValue) {
		String text = reader.read();
		if (StringUtil.isEmpty(text)) {
			return defaultValue;
		}
		return Datastore.createKey(Favorite.class, Long.parseLong(text));
	}
}
