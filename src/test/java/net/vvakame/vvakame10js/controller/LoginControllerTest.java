package net.vvakame.vvakame10js.controller;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import javax.servlet.ServletException;

import net.sf.json.JSONObject;

import org.junit.Test;
import org.slim3.tester.ControllerTestCase;

public class LoginControllerTest extends ControllerTestCase {

	static final String PATH = "/login";

	@Test
	public void notLoggedIn() throws NullPointerException,
			IllegalArgumentException, IOException, ServletException {

		tester.request.setMethod("GET");
		tester.start(PATH);
		assertThat(tester.getController(), instanceOf(LoginController.class));

		assertThat(tester.response.getStatus(), is(200));

		String outputAsString = tester.response.getOutputAsString();

		JSONObject json = JSONObject.fromObject(outputAsString);

		assertThat(json.getString("redirectUrl"), notNullValue());
	}
}
