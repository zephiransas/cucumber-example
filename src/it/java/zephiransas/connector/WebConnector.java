package zephiransas.connector;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class WebConnector {
	
    private final static long DEFAULT_TIMEOUT = 2000;

    private WebDriver driver = new FirefoxDriver();
    
    @Before
    public void initSelenium() throws Exception {
    }

    @After
    public void destroySelenium() {
    	driver.close();
    }
    
    public void clickAndWait(String selector) {
    	WebElement element = driver.findElement(By.linkText(selector));
    	element.click();
    	driver.manage().timeouts().implicitlyWait(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    public void openAndWait(String location) {
    	driver.get(location);
    	//driver.manage().timeouts().implicitlyWait(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);
    }
    
    public boolean isTextPresent(String text) {
    	WebElement content = driver.findElement(By.tagName("body")); 
    	return content.getText().contains(text);
    }
    
    public void assertTable(String className, List<String> expect) {
    	
    	WebElement table = driver.findElement(By.className(className));
    	
    	List<WebElement> rows = table.findElements(By.tagName("tr"));
    	int columnCount = rows.get(0).findElements(By.xpath("./*")).size();
    	assertThat(rows.size() * columnCount, is(expect.size()));
    	
    	int ix = 0;
    	for(WebElement row : rows) {
    		
    		List<WebElement> cells = row.findElements(By.xpath("./*"));
    		for(WebElement cell : cells) {
    			assertThat(cell.getText(), is(expect.get(ix)));
    			ix++;
    		}
    	}
    }
    
}
