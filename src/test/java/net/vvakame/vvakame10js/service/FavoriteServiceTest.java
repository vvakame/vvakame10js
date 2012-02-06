package net.vvakame.vvakame10js.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import net.vvakame.vvakame10js.model.Category;
import net.vvakame.vvakame10js.model.Favorite;

import org.junit.Test;
import org.slim3.datastore.Datastore;
import org.slim3.tester.AppEngineTestCase;

import com.google.appengine.api.NamespaceManager;

public class FavoriteServiceTest extends AppEngineTestCase {

	@Test
	public void processFavorites() {
		NamespaceManager.set("test");
		{
			Category category = new Category();
			category.setKey(CategoryService.createKey("第十二の使徒"));
			category.getTextList().add("12");
			Datastore.put(category);
		}

		for (int i = 1; i < 250; i++) {
			Favorite favorite = new Favorite();

			favorite.setKey(FavoriteService.createKey(i));
			favorite.setFavorited(true);
			favorite.setText("なんばー" + i);
			Datastore.put(favorite);
		}

		FavoriteService.processFavorites();

		List<Favorite> list = FavoriteService.getRecentlyByCategory("第十二の使徒");

		for (Favorite favorite : list) {
			System.out.println(favorite.getText());
		}
		
		assertThat("12を含むふぁぼの数", list.size(), is(13));
	}

	@Test
	public void processFavorite() {
		List<Category> categories = new ArrayList<Category>();
		{
			Category category = new Category();
			category.setKey(CategoryService.createKey("jQuery"));
			category.getTextList().add("jQuery");
			category.getTextList().add("じぇいくえりー");
			categories.add(category);
		}
		{
			Category category = new Category();
			category.setKey(CategoryService.createKey("Android"));
			category.getTextList().add("Android");
			category.getTextList().add("あんどろ");
			categories.add(category);
		}

		Favorite favorite = new Favorite();
		favorite.setText("あんどろめだ座！！！");
		favorite.getCategories().add("jQuery");

		FavoriteService.processFavorite(categories, favorite);

		assertThat("カテゴリはクリアされる", favorite.getCategories().size(), is(1));
		assertThat("Androidのみ", favorite.getCategories().iterator().next(),
				is("Android"));
	}

	@Test
	public void categoryWordContains() {
		Category category = new Category();
		category.getTextList().add("jQuery");
		category.getTextList().add("Android");

		assertThat("jQueryはある",
				FavoriteService.categoryWordContains(category, "jQuery"),
				is(true));
		assertThat("Androidはある",
				FavoriteService.categoryWordContains(category, "Android"),
				is(true));
		assertThat("CoffeeScriptはない",
				FavoriteService.categoryWordContains(category, "CoffeeScript"),
				is(false));
	}
}
