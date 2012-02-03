package net.vvakame.vvakame10js.controller;

import javax.servlet.http.Cookie;

import net.vvakame.vvakame10js.service.TwitterTokenService;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.StringUtil;

import twitter4j.Twitter;

import com.google.appengine.api.NamespaceManager;
import com.google.appengine.api.datastore.Key;

public abstract class BaseController extends Controller {

	String userHash;
	Key tokenKey;
	Twitter twitter;

	@Override
	protected final Navigation run() throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");

		if (isLoggedIn()) {
			userHash = getUserHash();
			tokenKey = TwitterTokenService.createKey(userHash);
			twitter = TwitterTokenService.getTwitterInstance(tokenKey);
		}

		NamespaceManager.set(userHash);

		process();

		response.flushBuffer();

		return null;
	}

	abstract void process() throws Exception;

	String getUserHash() {
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return null;
		}
		for (Cookie cookie : cookies) {
			if ("userHash".equals(cookie.getName())) {
				return cookie.getValue();
			}
		}
		return asString("userHash");
	}

	boolean isLoggedIn() {
		String userHash = getUserHash();
		return !StringUtil.isEmpty(userHash);
	}

	@Override
	protected boolean isGet() {
		String thisMethod = "get";
		String method = asString("m");

		if (method != null) {
			if (thisMethod.equals(method)) {
				return true;
			} else {
				return false;
			}
		}
		return super.isGet();
	}

	@Override
	protected boolean isPost() {
		String thisMethod = "post";
		String method = asString("m");

		if (method != null) {
			if (thisMethod.equals(method)) {
				return true;
			} else {
				return false;
			}
		}
		return super.isPost();
	}

	@Override
	protected boolean isPut() {
		String thisMethod = "put";
		String method = asString("m");

		if (method != null) {
			if (thisMethod.equals(method)) {
				return true;
			} else {
				return false;
			}
		}
		return super.isPut();
	}
}
