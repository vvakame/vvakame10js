package net.vvakame.vvakame10js.service;

import net.vvakame.vvakame10js.meta.TwitterTokenMeta;
import net.vvakame.vvakame10js.model.TwitterToken;
import net.vvakame.vvakame10js.util.TwitterOAuthUtil;

import org.slim3.datastore.Datastore;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import com.google.appengine.api.NamespaceManager;
import com.google.appengine.api.datastore.Key;

/**
 * {@link TwitterToken}を操作するためのサービス.
 * 
 * @author vvakame
 */
public class TwitterTokenService {

	static final TwitterTokenMeta META = TwitterTokenMeta.get();

	public static Key createKey(String userHash) {
		String namespace = NamespaceManager.get();
		try {
			NamespaceManager.set(null);
			return Datastore.createKey(TwitterToken.class, userHash);
		} finally {
			NamespaceManager.set(namespace);
		}
	}

	/**
	 * keyを元に {@link TwitterToken} を取得し返す
	 * 
	 * @param key
	 * @return {@link TwitterToken}
	 * @author vvakame
	 */
	public static TwitterToken getOrNull(Key key) {
		return Datastore.getOrNull(TwitterToken.class, key);
	}

	/**
	 * keyから利用可能な {@link Twitter} を作成し返す.
	 * 
	 * @param key
	 * @return 利用可能な {@link Twitter}
	 * @author vvakame
	 */
	public static Twitter getTwitterInstance(Key key) {
		TwitterToken token = getOrNull(key);
		if (token == null) {
			return null;
		}

		String consumerKey = TwitterOAuthUtil.getConsumerKey();
		String consumerSecret = TwitterOAuthUtil.getConsumerSecret();

		ConfigurationBuilder builder = new ConfigurationBuilder();
		builder.setOAuthConsumerKey(consumerKey);
		builder.setOAuthConsumerSecret(consumerSecret);
		builder.setOAuthAccessToken(token.getToken());
		builder.setOAuthAccessTokenSecret(token.getTokenSecret());

		TwitterFactory factory = new TwitterFactory(builder.build());
		Twitter twitter = factory.getInstance();

		return twitter;
	}

	/**
	 * 指定された要素を元に {@link TwitterToken} を作成し保存する.
	 * 
	 * @param userHash
	 * @param token
	 * @param tokenSecret
	 * @return 作成したインスタンス
	 * @author vvakame
	 */
	public static TwitterToken create(String userHash, String token,
			String tokenSecret) {
		TwitterToken info;
		info = new TwitterToken();
		info.setKey(createKey(userHash));
		info.setLock(false);
		info.setToken(token);
		info.setTokenSecret(tokenSecret);
		Datastore.put(info);
		return info;
	}
}
