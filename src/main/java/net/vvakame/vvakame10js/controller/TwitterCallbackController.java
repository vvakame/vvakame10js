package net.vvakame.vvakame10js.controller;

import java.util.logging.Logger;

import javax.servlet.http.Cookie;

import net.vvakame.vvakame10js.service.TwitterTokenService;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.StringUtil;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class TwitterCallbackController extends Controller {

	static final Logger logger = Logger.getLogger(LoginController.class
			.getName());

	static final TwitterFactory factory = new TwitterFactory();

	@Override
	protected Navigation run() throws Exception {

		logger.finest("AddCallbackHandler");

		Twitter twitter = sessionScope("twitter");
		RequestToken requestToken = sessionScope("requestToken");
		String verifier = asString("oauth_verifier");

		if (twitter == null || requestToken == null
				|| StringUtil.isEmpty(verifier)) {
			return redirect("/login");
		}

		String id;
		try {
			AccessToken accessToken = twitter.getOAuthAccessToken(requestToken,
					verifier);
			sessionScope("requestToken", null);

			twitter.verifyCredentials();
			id = String.valueOf(twitter.getId());
			if (StringUtil.isEmpty(id) || "null".equals(id)) {
				throw new IllegalArgumentException("id was required.");
			}
			String token = accessToken.getToken();
			String tokenSecret = accessToken.getTokenSecret();
			if (StringUtil.isEmpty(token) || StringUtil.isEmpty(tokenSecret)) {
				throw new IllegalArgumentException(
						"failed to get token/tokenSecret.");
			}

			TwitterTokenService.create(id, token, tokenSecret);

		} catch (TwitterException e) {
			throw new RuntimeException(e);
		}

		Cookie cookie = new Cookie("userHash", id);
		response.addCookie(cookie);

		return redirect("/index.html");
	}
}
