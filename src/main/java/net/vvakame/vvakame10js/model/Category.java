package net.vvakame.vvakame10js.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

import com.google.appengine.api.datastore.Key;

/**
 * カテゴリを保持するEntity.<br>
 * Key=name {カテゴリ名}
 * 
 * @author vvakame
 */
@Model
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	@Attribute(primaryKey = true)
	Key key;

	@Attribute(unindexed = true)
	List<String> textList = new ArrayList<String>();

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
	 * @return the textList
	 */
	public List<String> getTextList() {
		return textList;
	}

	/**
	 * @param textList
	 *            the textList to set
	 */
	public void setTextList(List<String> textList) {
		this.textList = textList;
	}
}
