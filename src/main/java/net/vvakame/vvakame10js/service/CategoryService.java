package net.vvakame.vvakame10js.service;

import java.util.List;

import net.vvakame.vvakame10js.meta.CategoryMeta;
import net.vvakame.vvakame10js.model.Category;

import org.slim3.datastore.Datastore;
import org.slim3.memcache.Memcache;

import com.google.appengine.api.datastore.Key;

public class CategoryService {

	public static final String MEMCACHE_ALL = CategoryService.class
			.getSimpleName() + "#all";

	private static final CategoryMeta META = CategoryMeta.get();

	public static Key createKey(String name) {
		return Datastore.createKey(Category.class, name);
	}

	public static void put(String name, List<String> textList) {
		Category category = new Category();
		category.setKey(createKey(name));
		category.setTextList(textList);

		Datastore.put(category);

		Memcache.cleanAll();
	}

	public static Category get(String name) {
		return Datastore.get(Category.class, createKey(name));
	}

	public static List<Category> getAll() {
		List<Category> all = Memcache.get(MEMCACHE_ALL);
		if (all != null) {
			return all;
		}

		all = Datastore.query(META).sort(META.key.asc).asList();
		Memcache.put(MEMCACHE_ALL, all);

		return all;
	}

	public static List<Key> getAllKeys() {
		return Datastore.query(META).sort(META.key.asc).asKeyList();
	}
}
