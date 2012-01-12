package net.vvakame.vvakame10js.controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import net.vvakame.vvakame10js.meta.FavoriteMeta;
import net.vvakame.vvakame10js.model.Category;
import net.vvakame.vvakame10js.model.Favorite;
import net.vvakame.vvakame10js.service.CategoryService;
import net.vvakame.vvakame10js.service.FavoriteService;

import org.slim3.datastore.Datastore;

import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TweetlistController extends BaseController {

	static final Logger logger = Logger.getLogger(TweetlistController.class
			.getName());

	static final TwitterFactory factory = new TwitterFactory();

	@Override
	void process() throws Exception {
		if (isGet()) {
			get(userHash);
		}
	}

	void get(String userHash) throws IOException, TwitterException {
		{
			List<Category> all = CategoryService.getAll();

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
					processFavorite(all, status);
				}
				page++;
			} while (statusList.size() != 0 && page < 10);
		}

		List<Favorite> favList = FavoriteService.getRecently();
		String json = FavoriteMeta.get().modelsToJson(favList);
		response.getWriter().write(json);
	}

	Favorite processFavorite(List<Category> categories, Status status) {
		Favorite favorite = FavoriteService.putIfNotExists(status);

		final String text = favorite.getText();

		for (Category category : categories) {
			final String categoryName = category.getKey().getName();

			List<String> textList = category.getTextList();
			for (String keyword : textList) {
				if (text.contains(keyword)) {
					if (!favorite.getCategories().contains(categoryName)) {
						favorite.getCategories().add(categoryName);
					}
					break;
				}
			}
		}
		Datastore.put(favorite);

		return favorite;
	}
}
