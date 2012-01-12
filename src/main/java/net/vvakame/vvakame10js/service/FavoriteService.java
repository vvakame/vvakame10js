package net.vvakame.vvakame10js.service;

import java.util.List;

import net.vvakame.vvakame10js.meta.FavoriteMeta;
import net.vvakame.vvakame10js.model.Favorite;

import org.slim3.datastore.Datastore;

import twitter4j.Status;
import twitter4j.User;

import com.google.appengine.api.datastore.Key;

public class FavoriteService {

	private static final FavoriteMeta META = FavoriteMeta.get();

	public static Key createKey(long id) {
		return Datastore.createKey(Favorite.class, id);
	}

	public static Favorite putIfNotExists(Status status) {

		Key key = createKey(status.getId());
		Favorite favorite = Datastore.getOrNull(Favorite.class, key);

		if (favorite != null) {
			return favorite;
		}

		favorite = new Favorite();

		favorite.setKey(key);
		favorite.setFavorited(status.isFavorited());
		favorite.setText(status.getText());
		favorite.setCreatedAt(status.getCreatedAt());

		User user = status.getUser();
		favorite.setScreenName(user.getScreenName());
		favorite.setUserId(user.getId());
		favorite.setUserName(user.getName());

		Datastore.put(favorite);

		return favorite;
	}

	public static Long getLatestFavId() {
		Favorite latest = Datastore.query(META).sort(META.key.desc)
				.limit(1).asSingle();
		if (latest == null) {
			return null;
		}

		return latest.getKey().getId();
	}

	public static List<Favorite> getRecently() {
		return Datastore.query(META).sort(META.key.desc).limit(100)
				.asList();
	}
}
