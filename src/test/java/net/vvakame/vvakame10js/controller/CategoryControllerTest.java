package net.vvakame.vvakame10js.controller;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import net.sf.json.JSONArray;
import net.vvakame.vvakame10js.model.Category;
import net.vvakame.vvakame10js.service.CategoryService;

import org.junit.Test;
import org.slim3.datastore.Datastore;
import org.slim3.tester.ControllerTestCase;

import com.google.appengine.api.NamespaceManager;

public class CategoryControllerTest extends ControllerTestCase {

	static final String PATH = "/category";

	private static final String USER_HASH = "kassy";

	@Test
	public void get() throws NullPointerException, IllegalArgumentException,
			IOException, ServletException {

		NamespaceManager.set(USER_HASH);

		List<String> textList = new ArrayList<String>();

		CategoryService.put("hoge", textList);
		CategoryService.put("fuga", textList);

		tester.request.setMethod("GET");
		tester.request.setParameter("userHash", USER_HASH);
		tester.start(PATH);
		assertThat(tester.getController(), instanceOf(CategoryController.class));

		assertThat(tester.response.getStatus(), is(200));

		String outputAsString = tester.response.getOutputAsString();

		JSONArray jsonArray = JSONArray.fromObject(outputAsString);
		assertThat(jsonArray.size(), is(2));
		assertThat(jsonArray.getJSONObject(0).getString("name"), notNullValue());
		assertThat(jsonArray.getJSONObject(1).getString("name"), notNullValue());
	}

	@Test
	@SuppressWarnings("unchecked")
	public void post() throws NullPointerException, IllegalArgumentException,
			IOException, ServletException {

		tester.request.setMethod("POST");
		tester.request.setParameter("userHash", USER_HASH);
		tester.request.setParameter("name", "jQuery");
		tester.request.setParameter("text0", "jQuery");
		tester.request.setParameter("text1", "ｊＱｕｅｒｙ");
		tester.start(PATH);
		assertThat(tester.getController(), instanceOf(CategoryController.class));

		assertThat(tester.response.getStatus(), is(200));

		@SuppressWarnings("unused")
		String outputAsString = tester.response.getOutputAsString();

		assertThat(Datastore.query(Category.class).count(), is(1));

		Category category = CategoryService.get("jQuery");

		assertThat(category.getTextList().size(), is(2));
		assertThat(category.getTextList().get(0),
				anyOf(is("jQuery"), is("ｊＱｕｅｒｙ")));
		assertThat(category.getTextList().get(1),
				anyOf(is("jQuery"), is("ｊＱｕｅｒｙ")));
	}
}
