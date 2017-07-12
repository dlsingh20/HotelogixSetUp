package AdminPages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Utilities.ExcelUtils;
import Configuration.Constants;
import Configuration.GenericMethods;

public class HMSAdmin 
{
	public static String parentWindowID;
	
	
	
	//It will search Hotel in HMS Admin and Login Each Hotel one by one
	public static void searchAndLoginHotel()
	{
		try {
			parentWindowID = GenericMethods.driver.getWindowHandle();
			//Set Excel Path
			ExcelUtils.setExcelFile(Constants.Path_TestData);
			
			int rowCount = ExcelUtils.getRowCount(Constants.Sheet_HotelIDs);
			
			for(int i = 1;i<rowCount;i++)
			{
				//Thread.sleep(2000);
				searchHotel(i);					
				//Click on Admin Link to Login			
				clickOnAdminLink(i);
						
				Thread.sleep(2000);
				
			}
			WebElement HMSlogout = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("HMSAdmine.Logout")));
			GenericMethods.jsClick(HMSlogout);
			//GenericMethods.driver.quit();
			
		} catch (Exception e) 
		{
			System.out.println("Error in search And Login Hotel in HMS Admine: "+e.getMessage());
			
		}
		
	}
	
	//Search Hotel in HMS Admin
	public static void searchHotel(int i) throws IOException
	{
		String hotelid = ExcelUtils.getStringData(i, Constants.Col_HotelID, Constants.Sheet_HotelIDs);
		
		WebElement hotelidtxtbox = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("HMSAdmine.EnterHotelID")));
		hotelidtxtbox.clear();
		hotelidtxtbox.sendKeys(hotelid);
		
		//Click On Go Button
		try {
			WebElement searchHotel = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("HMSAdmine.SearchHotel")));
			//searchHotel.click();
			GenericMethods.jsClick(searchHotel);
								
		} catch (Exception e) {
			System.out.println();
			throw e;
		}
	}
	
	//Click On Admin Link
	public static void clickOnAdminLink(int hotelIDRow) throws Exception
	{
		//Get Hotel name from Excel
		String expHotelName = ExcelUtils.getStringData(hotelIDRow, Constants.Col_HotelName, Constants.Sheet_HotelIDs);
		String actHotelName = "";
		
		//Get Hotel Name from HMS Admine
		try 
		{
			WebElement eleHotelName = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("HMSAdmine.HotelName")));
			actHotelName = eleHotelName.getText();
				
			} catch (Exception e) {
			System.out.println("Error in Getting Hotel Name in HMS Admine"+e.getMessage());
		}
		
		try {
			if(expHotelName.equalsIgnoreCase(actHotelName))
			{
				WebElement clickAdminLink = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("HMSAdmine.AdminLink")));
				//clickAdminLink.click();
				GenericMethods.jsClick(clickAdminLink);	
				
				AdminHome.switchToAdminWindow();
				
				//----------->>It will Create/Save all data in each Hotel as per given in Excel--------------------->> 
				AdminHome.SaveDataInHotel(hotelIDRow);
				
				//Will Logout Current Hotel Admin Console
				AdminHome.LogoutAdmin();
				GenericMethods.closeAllOtherWindows(HMSAdmin.parentWindowID);
			}else
			{
				System.out.println("Go For Another Hotel");
			}
			
		} catch (Exception e) {
			System.out.println("Error in Clicking Admin Link "+e.getMessage());
			ExcelUtils.setCellData(Constants.KEYWORD_FAIL, hotelIDRow, Constants.ThirdColumn, Constants.Sheet_HotelIDs);
		}
		
		//////////////////////////////////////////////
		
		GenericMethods.driver.navigate().refresh();
		GenericMethods.alertHandel();
	}
	public static void login()
	{
		try {
			WebElement hotelID = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("HMSAdmine.HotelID")));
			//WebElement hotelID = GenericMethods.driver.findElement(By.xpath("//input[@id='hotelCodeId']"));
			hotelID.sendKeys("1");
			
			WebElement emailID = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("HMSAdmine.EmailID")));
			//WebElement emailID = GenericMethods.driver.findElement(By.xpath("//input[@id='userNameId']"));
			emailID.sendKeys("pbonly9@gmail.com");
			
			WebElement pwd = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("HMSAdmine.Pwd")));
			pwd.clear();
			//WebElement pwd = GenericMethods.driver.findElement(By.xpath("//input[@id='passwordId']"));
			//pwd.sendKeys("!YRrajfatk45FTa2444D");
			//Thread.sleep(5000);
			pwd.sendKeys("111111");
			//WebElement captcha = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("")));
			//captcha.click();
			//Thread.sleep(15000);
			
			WebElement login = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("HMSAdmine.Login")));
			//WebElement login = GenericMethods.driver.findElement(By.xpath("//input[@name='Submit52']"));
			login.click();
			
			String actURL = GenericMethods.driver.getCurrentUrl();
			//String expURL = "https://staygrid.com/hmsadmine/login/authenticate";
			String expURL ="http://hotelogix.stayezee.com/hmsadmine/login/authenticate";
			//Wait to reEnter Captcha
			if(actURL.equalsIgnoreCase(Constants.URLHmsAdmin))
			{				
				wrongCaptchaOrPassword();
			}else{
				
			}
			
			
			
			
		} catch (Exception e) {
			System.out.println("Errorrr In HMS Admin Login"+e.getMessage());
		}
	}
	
	//Wait for 15 seconds if wrong Captcha entered
	public static void wrongCaptchaOrPassword() throws IOException
	{
		try {
			WebElement pwd1 = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("HMSAdmine.Pwd")));
			pwd1.clear();
			//WebElement pwd = GenericMethods.driver.findElement(By.xpath("//input[@id='passwordId']"));
			pwd1.sendKeys("!YRrajfatk45FTa2444D");
			Thread.sleep(10000);
			//pwd1.sendKeys("111111");
			//WebElement captcha = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("")));
			//captcha.click();
			
			
			WebElement login1 = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("HMSAdmine.Login")));
			//WebElement login = GenericMethods.driver.findElement(By.xpath("//input[@name='Submit52']"));
			login1.click();
			
		} catch (Exception e) {
			System.out.println("Error in HMS Admin Login"+e.getMessage());
		}
		
		
		
	}
	
	//Navigate on Registration List Page where we can search hotels
	public static void navigateToRegistrationList()
	{
		try {
			WebElement registrationlink = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("HMSAdmine.Registrationlist")));
			registrationlink.click();

		} catch (Exception e) {
			System.out.println("Issue in Navigate to Registration List page in HMS Admin"+e.getMessage());
		}
	}
	
}