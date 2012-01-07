package net.vvakame.vvakame10js.controller;

import java.util.Enumeration;
import java.util.Set;
import java.util.logging.Logger;

import net.sf.json.JSONObject;
import net.vvakame.vvakame10js.util.TwitterOAuthUtil;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;

import com.google.appengine.repackaged.com.google.common.collect.Sets;

public class LoginController extends BaseController {

	static final Logger logger = Logger.getLogger(LoginController.class
			.getName());

	static final TwitterFactory factory = new TwitterFactory();

	@Override
	void process() throws Exception {

		logger.finest("Login start!");

		if (isLoggedIn() && twitter != null) {
			response.getWriter().write("{}");
			response.flushBuffer();
			return;
		}

		String consumerKey = TwitterOAuthUtil.getConsumerKey();
		String consumerSecret = TwitterOAuthUtil.getConsumerSecret();
		String baseUrl = TwitterOAuthUtil.getBaseUrl();

		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(consumerKey, consumerSecret);

		{ // セッションをクリアしといてあげてみる
			@SuppressWarnings("rawtypes")
			Enumeration names = request.getSession().getAttributeNames();
			Set<String> rm = Sets.newHashSet();
			while (names.hasMoreElements()) {
				String name = (String) names.nextElement();
				rm.add(name);
			}
			for (String name : rm) {
				request.getSession().removeAttribute(name);
			}
		}

		sessionScope("twitter", twitter);
		try {
			String callbackURL = baseUrl + "twitterCallback";

			RequestToken requestToken = twitter
					.getOAuthRequestToken(callbackURL);
			sessionScope("requestToken", requestToken);

			JSONObject json = new JSONObject();
			json.put("redirectUrl", requestToken.getAuthorizationURL());
			response.getWriter().write(json.toString());

		} catch (TwitterException e) {
			throw new RuntimeException(e);
		}
	}
}
