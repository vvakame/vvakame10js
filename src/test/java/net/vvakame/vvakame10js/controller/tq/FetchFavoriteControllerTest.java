package net.vvakame.vvakame10js.controller.tq;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.slim3.tester.ControllerTestCase;

public class FetchFavoriteControllerTest extends ControllerTestCase {

	@Test
	public void addTask() {
		FetchFavoriteController.addTask("test");

		assertThat("Task+1", tester.tasks.size(), is(1));
	}
}
