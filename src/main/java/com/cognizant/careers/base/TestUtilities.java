package com.cognizant.careers.base;

public class TestUtilities extends BaseTest {

	// STATIC SLEEP 
	public void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
