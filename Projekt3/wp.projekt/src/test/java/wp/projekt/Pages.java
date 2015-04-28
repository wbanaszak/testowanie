package wp.projekt;

import org.jbehave.web.selenium.WebDriverProvider;

public class Pages {
	private WebDriverProvider driverProvider;
	private Home home;
	private Sport sport;
	private Poczta poczta;
	
	public Pages(WebDriverProvider driverProvider) 
	{
		super();
		this.driverProvider = driverProvider;
	}
	
	public Home home() 
	{
		if (home == null) {
			home = new Home(driverProvider);
		}
		return home;
	}
		
		public Sport sport() 
		{
			if (sport == null) {
				sport = new Sport(driverProvider);
			}
			return sport;
	}
		public Poczta poczta() 
		{
			if (poczta == null) {
				poczta = new Poczta(driverProvider);
			}
			return poczta;
	}
	
}
