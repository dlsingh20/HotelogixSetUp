package AdminPages;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import Utilities.ExcelUtils;
import Configuration.Constants;
import Configuration.GenericMethods;

public class AddUser 
{
	private static String hotelAddress;
	private static String hotelCountry;
	private static String hotelState;
	private static String hotelCity;
	private static String hotelZipcode;
	private static String hotelPhoneno;
	
	private static String SuccessMessage;
	private static String ErrorMessage;
	private static String alrtMsg="";
	
	
	public static void createUsers(int hotelIDRow, int rowcount) throws Exception
	{
		//getHotelInformation();
		landOnUsers();
		for(int i = 1;i<rowcount;i++)
		{
			saveUsers(hotelIDRow, i);
			continue;
		}
	}
	
	//Click on User in Admin Home Page
	public static void landOnUsers()
	{
		try {
			WebElement users = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Users")));
			users.click();
		} catch (Exception e) {
			System.out.println("Unable to Land on Users Page"+e.getMessage());
		}
	}
	//Get Hotel Information
	public static void getHotelInformation() throws IOException
	{
		//Click On Hotel Information
		try {
			WebElement hotelInformation = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.HotelInformation")));
			hotelInformation.click();
		} catch (Exception e) {
			System.out.println("Unable to Land on hotelInformation"+e.getMessage());
		}
		//Get Hotel Address
		try {
			WebElement address = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Address")));
			hotelAddress = address.getAttribute("value");
		} catch (Exception e) {
			System.out.println("Issue in get Hotel Address"+e.getMessage());
		}
		//Get Hotel Country
		try {
			WebElement country = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Country")));
			Select select = new Select(country);
			WebElement temp = select.getFirstSelectedOption();
			hotelCountry = temp.getText();
		} catch (Exception e) {
			System.out.println("Issue in Get Country"+e.getMessage());
		}
		//Get Hotel State
		try {
			WebElement state = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.State")));
			Select select = new Select(state);
			WebElement temp = select.getFirstSelectedOption();
			hotelState = temp.getText();
		} catch (Exception e) {
			System.out.println("Issue in get State"+e.getMessage());
		}
		//Get Hotel City
		try {
			WebElement city = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.City")));
			hotelCity = city.getAttribute("value");	
		} catch (Exception e) {
			System.out.println("Issue in get City Name"+e.getMessage());
		}
		
		//Get Hotel Zip Code
		try {
			WebElement zipcode = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Zipcode")));
			hotelZipcode = zipcode.getAttribute("value");
		} catch (Exception e) {
			System.out.println("Issue in get Zip Code"+e.getMessage());
		}
		//Get Hotel Phone
		try {
			WebElement phone = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Phone")));
			hotelPhoneno = phone.getAttribute("value");
		} catch (Exception e) {
			System.out.println("Issue in get Phone no"+e.getMessage());
		}
		try {
			WebElement main = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Main")));
			main.click();
		} catch (Exception e) {
			System.out.println("Issue in clicking Main on Hotel Information Page"+e.getMessage());
		}
	}
	
	//Save Users Data
	public static void saveUsers(int hotelIDRow, int userRowNo) throws Exception
	{
		//Column No For Result
		int ColumnResult = userRowNo+1;
		//Click on Add A New User
		try {
			WebElement clickAddNewUser = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Users.AddNewUser")));
			clickAddNewUser.click();
		} catch (Exception e) {
			System.out.println("Unable to Click on Add New User"+e.getMessage());
		}
		//Enter Employee ID*
		try {
			WebElement employeeID = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Users.EmployeeID")));
			String eid = ExcelUtils.getStringData(userRowNo, Constants.FirstColumn,Constants.Sheet_User);
			employeeID.sendKeys(eid);
		} catch (Exception e) {
			System.out.println("Issue in Enter Employee ID"+e.getMessage());
		}
		//Enter First Name
		try {
			WebElement firstName = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Users.FirstName")));
			String fname = ExcelUtils.getStringData(userRowNo, Constants.SecondColumn, Constants.Sheet_User);
			firstName.sendKeys(fname);
		} catch (Exception e) {
			System.out.println("Issue in Enter First name"+e.getMessage());
		}
		//Enter Last Name
		try {
			WebElement lastName =GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Users.LastName")));
			String lName = ExcelUtils.getStringData(userRowNo, Constants.ThirdColumn, Constants.Sheet_User);
			lastName.sendKeys(lName);
		} catch (Exception e) {
			System.out.println("Issue in Enter Last name"+e.getMessage());
		}
		
		//Enter Address
		try {
			WebElement address = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Users.Address")));
			//address.sendKeys(hotelAddress);
			String add= ExcelUtils.getStringData(userRowNo, Constants.FourthColumn, Constants.Sheet_User);
			address.sendKeys(add);
		} catch (Exception e) {
			System.out.println("Issue in Enter Address"+e.getMessage());
		}
		//Select Country
		try {
			WebElement country = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Users.Country")));
			Select select = new Select(country);
			//select.selectByVisibleText(hotelCountry);
			String con = ExcelUtils.getStringData(userRowNo, Constants.FifthColumn, Constants.Sheet_User);
			select.selectByVisibleText(con);
		} catch (Exception e) {
			System.out.println("Issue in Select Country"+e.getMessage());
		}
		//Select State
		try {
			Thread.sleep(2000);
			WebElement state = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Users.State")));
			Select select = new Select(state);
			//select.selectByVisibleText(hotelState);
			String stat = ExcelUtils.getStringData(userRowNo, Constants.SixthColumn, Constants.Sheet_User);
			select.selectByVisibleText(stat);
		} catch (Exception e) {
			System.out.println("Issue in Select State"+e.getMessage());
		}
		//Enter City 
		try {
			WebElement city = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Users.City")));
			//city.sendKeys(hotelCity);
			String cty = ExcelUtils.getStringData(userRowNo, Constants.SeventhColumn, Constants.Sheet_User);
			city.sendKeys(cty);
		} catch (Exception e) {
			System.out.println("Issue in Enter City"+e.getMessage());
		}
		//Enter Zip Code
		try {
			WebElement zipcode = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Users.ZipCode")));
			//zipcode.sendKeys(hotelZipcode);
			String zip = ExcelUtils.getStringData(userRowNo, Constants.EighthColumn, Constants.Sheet_User);
			zipcode.sendKeys(zip);
		} catch (Exception e) {
			System.out.println("Issue in Enter Zip Code"+e.getMessage());
		}
		//Enter Phone
		try {
			WebElement phone = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Users.PhoneNo")));
			//phone.sendKeys(hotelPhoneno);
			String phon = ExcelUtils.getStringData(userRowNo, Constants.NinethColumn, Constants.Sheet_User);
			phone.sendKeys(phon);
		} catch (Exception e) {
			System.out.println("Issue in Enter Phone No"+e.getMessage());
		}
		//Select Gender
		try {
			WebElement gender =GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Users.Gender")));
			Select select = new Select(gender);
			//select.selectByVisibleText(ExcelUtils.getStringData(userRowNo, Constants.FourthColumn, Constants.Sheet_User));
			String gen = ExcelUtils.getStringData(userRowNo, Constants.TenthColumn, Constants.Sheet_User);
			select.selectByVisibleText(gen);
		} catch (Exception e) {
			System.out.println("Issue in Select Gender"+e.getMessage());
		}
		
		//Select ID Type
		try {
			WebElement idtype = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Users.IDType")));
			Select select = new Select(idtype);
			//select.selectByVisibleText(ExcelUtils.getStringData(userRowNo, Constants.FifthColumn, Constants.Sheet_User));
			String idtyp = ExcelUtils.getStringData(userRowNo, Constants.EleventhColumn, Constants.Sheet_User);
			select.selectByVisibleText(idtyp);
			} catch (Exception e) {
				System.out.println("Issue in Select ID"+e.getMessage());
		}
		
		//Enter ID
		try {
			Thread.sleep(2000);
			WebElement id = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Users.ID")));
			//id.sendKeys(ExcelUtils.getStringData(userRowNo, Constants.SixthColumn, Constants.Sheet_User));
			String ide =ExcelUtils.getStringData(userRowNo, Constants.TwelthColumn, Constants.Sheet_User); 
			id.sendKeys(ide);
		} catch (Exception e) {
			System.out.println("Issue in Enter ID"+e.getMessage());
		}
		
		//Select Department
		try {
			WebElement department = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Users.Department")));
			Select select = new Select(department);
			//select.selectByVisibleText(ExcelUtils.getStringData(userRowNo, Constants.SeventhColumn, Constants.Sheet_User));
			String dept = ExcelUtils.getStringData(userRowNo, Constants.ThireenthColumn, Constants.Sheet_User);
			select.selectByVisibleText(dept);
		} catch (Exception e) {
			System.out.println("Issue in Select Department"+e.getMessage());
		}
				
		//Select Counter
		try {
			WebElement counter = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Users.Counter")));
			Select select = new Select(counter);
			//select.selectByVisibleText(ExcelUtils.getStringData(userRowNo, Constants.EighthColumn, Constants.Sheet_User));
			String contr = ExcelUtils.getStringData(userRowNo, Constants.FourteenthColumn, Constants.Sheet_User);
			select.selectByVisibleText(contr);
			} catch (Exception e) {
			System.out.println("Issue in Select Counter"+e.getMessage());
		}
		
		//Click On Arrow to send Counter into next listbox
		try {
			WebElement arrow = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Users.Counter.Arrow")));
			arrow.click();
		} catch (Exception e) {
			System.out.println("Issue in Clicking Arrow"+e.getMessage());
		}
		
		//Enter Designation
		try {
			WebElement designation = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Users.Designation")));
			//select.selectByVisibleText(ExcelUtils.getStringData(userRowNo, Constants.NinethColumn, Constants.Sheet_User));
			String desig = ExcelUtils.getStringData(userRowNo, Constants.FifteenthColumn, Constants.Sheet_User);
			designation.sendKeys(desig);
			
		} catch (Exception e) {
			System.out.println("Issue in Enter Designation"+e.getMessage());
		}
		//Enter Email ID
		try {
			WebElement email = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Users.EmailAddress")));
			//email.sendKeys(ExcelUtils.getStringData(userRowNo, Constants.TenthColumn, Constants.Sheet_User));
			String mail = ExcelUtils.getStringData(userRowNo, Constants.SixteenthColumn, Constants.Sheet_User);
			email.sendKeys(mail);
		} catch (Exception e) {
			System.out.println("Issue in Enter Email"+e.getMessage());
		}
		
		//Enter Password
		try {
			WebElement password = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Users.Password")));
			//password.sendKeys(ExcelUtils.getStringData(userRowNo, Constants.EleventhColumn, Constants.Sheet_User));
			String pwd = ExcelUtils.getStringData(userRowNo, Constants.SeventeenthColumn, Constants.Sheet_User);
			password.sendKeys(pwd);
		} catch (Exception e) {
			System.out.println("Issue in Enter Password"+e.getMessage());
		}
		
		//Enter Confirm Password
		try {
			WebElement confirmpassword = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Users.ConfirmPassword")));
			//confirmpassword.sendKeys(ExcelUtils.getStringData(userRowNo, Constants.TwelthColumn, Constants.Sheet_User));
			String conpwd = ExcelUtils.getStringData(userRowNo, Constants.SeventeenthColumn, Constants.Sheet_User);
			confirmpassword.sendKeys(conpwd);
		} catch (Exception e) {
			System.out.println("Issue in Enter Confirm Password"+e.getMessage());
		}
		//Select User Type
		try {
			WebElement usertype = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Users.UserType")));
			Select select = new Select(usertype);
			//select.selectByVisibleText(ExcelUtils.getStringData(userRowNo, Constants.ThireenthColumn, Constants.Sheet_User));
			String utyp = ExcelUtils.getStringData(userRowNo, Constants.EighteenthColumn, Constants.Sheet_User);
			select.selectByVisibleText(utyp);
		} catch (Exception e) {
			System.out.println("Issue in Select User Type"+e.getMessage());
		}
		
		//Logic for Click and Select View Permission
		try {
			WebElement viewEditReport = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Users.ViewEditReportPermission")));
			if (viewEditReport.isDisplayed()) 
			{	//String handle = GenericMethods.driver.getWindowHandle();
				GenericMethods.ClickAndSwitchToWindow(viewEditReport);
				try {
					// Click Save on Select All Reports Page
					WebElement selectall = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Users.SelectAll")));
					selectall.click();
					Thread.sleep(1000);
					WebElement save = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Users.Save")));
					if (selectall.isSelected()) 
					{
						save.click();
					}else {
						selectall.click();
						GenericMethods.jsClick(save);
					}
				} catch (Exception e) {
					System.out.println("Issue in Select All Reports"+e.getMessage());
				}
			}
		} catch (Exception e) {
			System.out.println("Issue in Window handle Add/View Permission"+e.getMessage());
		}
		
		//Handle Alert if Alert comes
		GenericMethods.driver.switchTo().window(AdminHome.adminWindowID);
		
		//Click Save User
		try {
			WebElement save = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Users.SaveUser")));
			save.click();
			if (GenericMethods.driver.switchTo().alert() != null) {
				Alert alert = GenericMethods.driver.switchTo().alert();
				alrtMsg = alert.getText();
				alert.dismiss();
				ExcelUtils.setCellData(Constants.KEYWORD_FAIL+" | "+alrtMsg, hotelIDRow, ColumnResult, Constants.Sheet_HotelIDs);
				ExcelUtils.setCellData(Constants.KEYWORD_FAIL+" | "+alrtMsg, userRowNo, Constants.NineteenthColumn,Constants.Sheet_User);
				WebElement UserList = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Users.UserListLink")));
				UserList.click();
			}
		} catch (Exception e) {
			System.out.println("Issue in Click Save"+e.getMessage());
		}
		
		//Success / Error Message for Save
		try {
			WebElement SaveSuccessMessage =  GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Users.SavedSuccessfully")));
			SuccessMessage = SaveSuccessMessage.getText();	
		} catch (Exception e) {
			System.out.println("Seems Error"+e.getMessage());
		}
		
		//Get Error Message
		try {
			WebElement SaveErrorMessage =  GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Users.ErrorInSave")));
			ErrorMessage = SaveErrorMessage.getText();	
		} catch (Exception e) {
			System.out.println("No Error Message"+e.getMessage());
		}
		
		//Write Condition for Pass/Fail
		try {
			//Get Success Meesage
			if (SuccessMessage.contains("successfully"))
			{
				ExcelUtils.setCellData(Constants.KEYWORD_PASS, hotelIDRow, ColumnResult, Constants.Sheet_HotelIDs);
				ExcelUtils.setCellData(Constants.KEYWORD_PASS, userRowNo, Constants.NineteenthColumn,Constants.Sheet_User);
			}else if(ErrorMessage.contains("ERRORS"))
			{
				ExcelUtils.setCellData(Constants.KEYWORD_FAIL+" | "+ErrorMessage, hotelIDRow, ColumnResult, Constants.Sheet_HotelIDs);
				ExcelUtils.setCellData(Constants.KEYWORD_FAIL+" | "+ErrorMessage, userRowNo, Constants.NineteenthColumn,Constants.Sheet_User);
				WebElement UserList = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Users.UserListLink")));
				UserList.click();
			}else
			{
				ExcelUtils.setCellData(Constants.KEYWORD_FAIL+" | "+ErrorMessage, hotelIDRow, ColumnResult, Constants.Sheet_HotelIDs);
				ExcelUtils.setCellData(Constants.KEYWORD_FAIL+" | "+ErrorMessage, userRowNo, Constants.NineteenthColumn,Constants.Sheet_User);
				WebElement UserList = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Users.UserListLink")));
				UserList.click();
			}
		} catch (Exception e) {
			System.out.println("Error in Writing Pass Fail"+e.getMessage());
			//ExcelUtils.setCellData(Constants.KEYWORD_FAIL+" | "+ErrorMessage, hotelIDRow, ColumnResult, Constants.Sheet_HotelIDs);
		}
	}
}