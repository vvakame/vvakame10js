package net.vvakame.vvakame10js.service;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import net.vvakame.vvakame10js.meta.FavoriteMeta;
import net.vvakame.vvakame10js.model.Category;
import net.vvakame.vvakame10js.model.Favorite;

import org.slim3.datastore.Datastore;

import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

import com.google.appengine.api.datastore.Key;

public class FavoriteService {

	static final Logger logger = Logger.getLogger(FavoriteService.class
			.getName());

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
		Favorite latest = Datastore.query(META).sort(META.key.desc).limit(1)
				.asSingle();
		if (latest == null) {
			return null;
		}

		return latest.getKey().getId();
	}

	public static List<Favorite> getRecently() {
		return Datastore.query(META).sort(META.key.desc).limit(100).asList();
	}

	public static List<Favorite> getRecentlyByCategory(String category) {
		return Datastore.query(META).filter(META.categories.equal(category))
				.sort(META.key.desc).limit(100).asList();
	}

	public static void fetchFavorite(Twitter twitter, String userHash)
			throws IOException, TwitterException {

		Long latestId = FavoriteService.getLatestFavId();

		int page = 1;
		Paging paging;
		ResponseList<Status> statusList;
		do {
			if (latestId == null) {
				paging = new Paging(page).count(50);
			} else {
				paging = new Paging(page).count(50).sinceId(latestId);
			}
			statusList = twitter.getFavorites(paging);

			logger.info("get " + statusList.size() + " favs.");

			for (Status status : statusList) {
				FavoriteService.putIfNotExists(status);
			}
			page++;
		} while (statusList.size() != 0 && page < 10);
	}

	public static void processFavorites() {
		final int LIMIT = 100;
		int offset = 0;

		final List<Category> categories = CategoryService.getAll();

		List<Favorite> list;
		do {
			list = Datastore.query(META).limit(LIMIT).offset(offset).asList();
			offset += LIMIT;

			for (Favorite favorite : list) {
				processFavorite(categories, favorite);
			}
			Datastore.put(list);

		} while (list.size() == LIMIT);
	}

	public static Favorite processFavorite(List<Category> categories,
			Favorite favorite) {

		// カテゴリが削除されることを一応考慮してあげる…
		favorite.getCategories().clear();

		final String text = favorite.getText();

		for (Category category : categories) {
			if (categoryWordContains(category, text)) {
				String categoryName = category.getKey().getName();
				favorite.getCategories().add(categoryName);
			}
		}
		Datastore.put(favorite);

		return favorite;
	}

	static boolean categoryWordContains(Category category, String text) {
		List<String> textList = category.getTextList();
		for (String keyword : textList) {
			if (text.contains(keyword)) {
				return true;
			}
		}
		return false;
	}
}
