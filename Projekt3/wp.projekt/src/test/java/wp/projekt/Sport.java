package wp.projekt;


import java.util.concurrent.TimeUnit;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;

public class Sport extends WebDriverPage
{

	public Sport(WebDriverProvider driverProvider) 
	{
		super(driverProvider);
	}
	
	public void open() 
	{
		get("http://sport.wp.pl/?ticaid=114bc0");
		manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
}
