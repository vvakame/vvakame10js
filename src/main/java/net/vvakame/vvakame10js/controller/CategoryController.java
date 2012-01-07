package net.vvakame.vvakame10js.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.vvakame.vvakame10js.service.CategoryService;

import org.slim3.util.StringUtil;

import twitter4j.TwitterFactory;

import com.google.appengine.api.datastore.Key;

public class CategoryController extends BaseController {

	static final Logger logger = Logger.getLogger(CategoryController.class
			.getName());

	static final TwitterFactory factory = new TwitterFactory();

	@Override
	void process() throws Exception {

		if (isPost()) {
			post();
		} else if (isGet()) {
			get();
		}
	}

	void post() {

		String name = asString("name");
		List<String> textList = new ArrayList<String>();
		{
			int i = 0;
			String tmp;
			do {
				tmp = asString("text" + i);
				if (StringUtil.isEmpty(tmp)) {
					break;
				}
				textList.add(tmp);
				i++;
			} while (true);
		}

		CategoryService.put(name, textList);
	}

	void get() throws IOException {
		List<Key> keys = CategoryService.getAllKeys();

		JSONArray jsonArray = new JSONArray();
		for (Key key : keys) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("name", key.getName());
			jsonArray.add(jsonObject);
		}

		response.getWriter().write(jsonArray.toString());
	}
}
