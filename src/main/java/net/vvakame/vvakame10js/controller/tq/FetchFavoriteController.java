package net.vvakame.vvakame10js.controller.tq;

import net.vvakame.vvakame10js.service.FavoriteService;
import net.vvakame.vvakame10js.service.TwitterTokenService;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import twitter4j.Twitter;

import com.google.appengine.api.NamespaceManager;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.RetryOptions;
import com.google.appengine.api.taskqueue.TaskOptions;

public class FetchFavoriteController extends Controller {

	public static final String PATH = "/tq/fetchFavorite";

	@Override
	protected Navigation run() throws Exception {
		String userHash = asString("userHash");
		Key tokenKey = TwitterTokenService.createKey(userHash);
		Twitter twitter = TwitterTokenService.getTwitterInstance(tokenKey);

		if (twitter == null) {
			throw new IllegalStateException(userHash
					+ " is not have valid token!");
		}

		NamespaceManager.set(userHash);

		FavoriteService.fetchFavorite(twitter, userHash);

		FavoriteService.processFavorites();

		return null;
	}

	public static void addTask(String userHash) {
		QueueFactory.getQueue("fetch-favorite").add(
				TaskOptions.Builder
						.withUrl(PATH)
						.param("userHash", userHash)
						.retryOptions(
								RetryOptions.Builder.withTaskRetryLimit(5)));
	}
}
