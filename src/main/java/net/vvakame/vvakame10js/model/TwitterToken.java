package net.vvakame.vvakame10js.model;

import java.util.Date;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

import com.google.appengine.api.datastore.Key;

/**
 * TwitterのOAuthTokenを保持する.<br>
 * Keyの内容 name=TwitterのUserHash
 * 
 * @author vvakame
 */
@Model
public class TwitterToken {

	@Attribute(primaryKey = true)
	Key key;

	@Attribute(unindexed = true)
	String token;

	@Attribute(unindexed = true)
	String tokenSecret;

	Date at = new Date();

	boolean lock = true;

	/**
	 * @return the key
	 * @category accessor
	 */
	public Key getKey() {
		return key;
	}

	/**
	 * @param key
	 *            the key to set
	 * @category accessor
	 */
	public void setKey(Key key) {
		this.key = key;
	}

	/**
	 * @return the token
	 * @category accessor
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token
	 *            the token to set
	 * @category accessor
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the tokenSecret
	 * @category accessor
	 */
	public String getTokenSecret() {
		return tokenSecret;
	}

	/**
	 * @param tokenSecret
	 *            the tokenSecret to set
	 * @category accessor
	 */
	public void setTokenSecret(String tokenSecret) {
		this.tokenSecret = tokenSecret;
	}

	/**
	 * @return the at
	 * @category accessor
	 */
	public Date getAt() {
		return at;
	}

	/**
	 * @param at
	 *            the at to set
	 * @category accessor
	 */
	public void setAt(Date at) {
		this.at = at;
	}

	/**
	 * @return the lock
	 * @category accessor
	 */
	public boolean isLock() {
		return lock;
	}

	/**
	 * @param lock
	 *            the lock to set
	 * @category accessor
	 */
	public void setLock(boolean lock) {
		this.lock = lock;
	}
}
