package wp.projekt;


import java.util.concurrent.TimeUnit;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;

public class Home extends WebDriverPage
{

	public Home(WebDriverProvider driverProvider) 
	{
		super(driverProvider);
	}
	
	public void open() 
	{
		get("http://wp.pl");
		manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
}
