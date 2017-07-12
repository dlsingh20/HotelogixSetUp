package Execution;

import java.io.IOException;

import AdminPages.HMSAdmin;
import Configuration.Constants;
import Configuration.GenericMethods;

public class ExecutionClass 
{

	public static void main(String[] args) throws IOException 
	{
		//To Launch Browser and Launch URL
		GenericMethods.lounchBrowserAndUrl("ff", Constants.URLHmsAdmin);
		GenericMethods.driver.manage().window().maximize();
		//Login to HMSAdmine
		
		//========Test Line 1==========
		//Test====Test Line 2========
		//Test====Test Line 3========
		//Test====Test Line 4========
		HMSAdmin.login();
		HMSAdmin.navigateToRegistrationList();
		
		HMSAdmin.searchAndLoginHotel();
	}
}
