package net.vvakame.vvakame10js.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

/**
 * utilities for oauth of google app engine.
 * 
 * @author vvakame
 */
public class TwitterOAuthUtil {

	static final Properties PROPERTIES;

	static {
		try {
			PROPERTIES = new Properties();
			InputStream is = TwitterOAuthUtil.class
					.getResourceAsStream("/twitteroauth.properties");
			PROPERTIES.load(is);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * ConsumerKeyを取得します.
	 * 
	 * @return ConsumerKey
	 * @author vvakame
	 */
	public static String getConsumerKey() {
		return PROPERTIES.getProperty("consumerKey");
	}

	/**
	 * ConsumerSecretを取得します.
	 * 
	 * @return ConsumerSecret
	 * @author vvakame
	 */
	public static String getConsumerSecret() {
		return PROPERTIES.getProperty("consumerSecret");
	}

	/**
	 * 指定されたappIdに対応したConsumerSecretを取得します.
	 * 
	 * @param appId
	 * @return ConsumerSecret
	 * @author vvakame
	 */
	public static String getBaseUrl(HttpServletRequest req) {
		String scheme = req.getScheme();
		String serverName = req.getServerName();
		int serverPort = req.getServerPort();

		StringBuilder builder = new StringBuilder();
		builder.append(scheme).append("://").append(serverName).append(":")
				.append(serverPort).append("/");
		return builder.toString();
	}
}
