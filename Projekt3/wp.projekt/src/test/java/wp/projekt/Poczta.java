package wp.projekt;


import java.util.concurrent.TimeUnit;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;

public class Poczta extends WebDriverPage
{

	public Poczta(WebDriverProvider driverProvider) 
	{
		super(driverProvider);
	}
	
	public void open() 
	{
		get("http://poczta.wp.pl/");
		manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
}
