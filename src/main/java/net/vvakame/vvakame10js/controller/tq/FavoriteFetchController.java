package net.vvakame.vvakame10js.controller.tq;

import net.vvakame.vvakame10js.service.FavoriteService;
import net.vvakame.vvakame10js.service.TwitterTokenService;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import twitter4j.Twitter;

import com.google.appengine.api.datastore.Key;

public class FavoriteFetchController extends Controller {

	@Override
	protected Navigation run() throws Exception {
		String userHash = asString("userHash");
		Key tokenKey = TwitterTokenService.createKey(userHash);
		Twitter twitter = TwitterTokenService.getTwitterInstance(tokenKey);

		FavoriteService.fetchFavorite(twitter, userHash);

		return null;
	}
}
