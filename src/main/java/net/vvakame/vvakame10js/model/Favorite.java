package net.vvakame.vvakame10js.model;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import net.vvakame.vvakame10js.util.KeyIdEncoder;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.datastore.json.Json;

import com.google.appengine.api.datastore.Key;

/**
 * ツイート1つ1つを表すEntity.<br>
 * Key name={当該ツイートのID}
 * 
 * @author vvakame
 */
@Model
public class Favorite {

	@Attribute(primaryKey = true)
	@Json(alias = "id", coder = KeyIdEncoder.class)
	Key key;

	Set<String> categories = new LinkedHashSet<String>();

	@Attribute(unindexed = true)
	boolean favorited;

	@Attribute(unindexed = true)
	@Json(alias = "created_at")
	Date createdAt;

	@Attribute(unindexed = true)
	String text;

	@Attribute(unindexed = true)
	@Json(alias = "user_name")
	String userName;

	@Attribute(unindexed = true)
	@Json(alias = "screen_name")
	String screenName;

	@Attribute(unindexed = true)
	@Json(alias = "user_id")
	long userId;

	Date includeAt = new Date();

	/**
	 * @return the key
	 */
	public Key getKey() {
		return key;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(Key key) {
		this.key = key;
	}

	/**
	 * @return the categories
	 */
	public Set<String> getCategories() {
		return categories;
	}

	/**
	 * @param categories
	 *            the categories to set
	 */
	public void setCategories(Set<String> categories) {
		this.categories = categories;
	}

	/**
	 * @return the favorited
	 */
	public boolean isFavorited() {
		return favorited;
	}

	/**
	 * @param favorited
	 *            the favorited to set
	 */
	public void setFavorited(boolean favorited) {
		this.favorited = favorited;
	}

	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt
	 *            the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the screenName
	 */
	public String getScreenName() {
		return screenName;
	}

	/**
	 * @param screenName
	 *            the screenName to set
	 */
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * @return the includeAt
	 */
	public Date getIncludeAt() {
		return includeAt;
	}

	/**
	 * @param includeAt
	 *            the includeAt to set
	 */
	public void setIncludeAt(Date includeAt) {
		this.includeAt = includeAt;
	}
}
