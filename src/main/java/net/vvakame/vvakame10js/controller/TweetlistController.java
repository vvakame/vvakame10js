package net.vvakame.vvakame10js.controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import net.vvakame.vvakame10js.meta.FavoriteMeta;
import net.vvakame.vvakame10js.model.Favorite;
import net.vvakame.vvakame10js.service.FavoriteService;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TweetlistController extends BaseController {

	static final Logger logger = Logger.getLogger(TweetlistController.class
			.getName());

	static final TwitterFactory factory = new TwitterFactory();

	@Override
	void process() throws Exception {
		FavoriteService.fetchFavorite(twitter, userHash);
		FavoriteService.processFavorites();

		if (isGet()) {
			String category = asString("category");

			get(userHash, category);
		}
	}

	void get(String userHash, String category) throws IOException,
			TwitterException {

		List<Favorite> favList = FavoriteService
				.getRecentlyByCategory(category);
		String json = FavoriteMeta.get().modelsToJson(favList);
		response.getWriter().write(json);
	}
}
