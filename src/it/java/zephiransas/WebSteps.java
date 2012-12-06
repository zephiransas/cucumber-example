package zephiransas;

import static org.junit.Assert.*;
import cucumber.api.java.ja.ならば;
import cucumber.api.java.ja.もし;
import cucumber.api.java.ja.前提;
import zephiransas.connector.WebConnector;

public class WebSteps {

	private WebConnector connector;
	
	public WebSteps(WebConnector connector) {
		this.connector = connector;
	}
	

	@前提("^ページを表示する$")
	public void ページを表示する() {
		connector.openAndWait("http://localhost:8080/cucumber-example/");
	}

	@ならば("^あいさつが表示されている$")
	public void あいさつが表示されている() {
		assertTrue(connector.isTextPresent("Hello World"));
	}

	@ならば("\"([^\"]*)\"と表示されていること$")
	public void と表示されていること(String pattern) {
		assertTrue(connector.isTextPresent(pattern));
	}

	@ならば("\"([^\"]*)\"と表示されていないこと$")
	public void と表示されていないこと(String pattern) {
		assertFalse(connector.isTextPresent(pattern));
	}
  
	@もし("\"([^\"]*)\"リンクをクリックする$") 
	public void リンクをクリック(String selector) {
		connector.clickAndWait(selector);
	}	

}
