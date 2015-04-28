package wp.projekt;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class WPsteps {
	private final Pages pages;
	public WPsteps(Pages pages) {
		this.pages = pages;
	}
		@Given("user is on Home page")
		public void userIsOnHomePage()
		{        
	        pages.home().open();        
	    }
		
		@When("user is on go Sport")
		public void userIsOnSportPage()
		{        
	        pages.sport().open();        
	    }
		
		@Then("change sport for email")
		public void userIsOnEmailPage()
		{        
	        pages.poczta().open();        
	    }
	
}
