package AdminPages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Utilities.ExcelUtils;
import Configuration.Constants;
import Configuration.GenericMethods;

public class DeleteUser 
{
	public static String email="";
	public static void DeleteUsers(int hotelIDRow, int rowcount) throws Exception
	{
		//getHotelInformation();
		AddUser.landOnUsers();
		for(int i = 1;i<rowcount;i++)
		{
			deleteUsers(hotelIDRow, i);
			continue;
		}
	}
	public static void deleteUsers(int hotelIDRow, int userRowNo) throws Exception
	{
		int ColumnResult = userRowNo+1;
		//Search User on User Page
		try {
			WebElement txtboxSearch = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Users.TxtbxSearchUser")));
			String userEmail = ExcelUtils.getStringData(userRowNo, Constants.SecondColumn, Constants.Sheet_DeleteUser);
			txtboxSearch.clear();
			txtboxSearch.sendKeys(userEmail);
			email = userEmail;
		} catch (Exception e) {
			System.out.println("Unable to find Search Text box"+e.getMessage());
		}
		//Click on Search Text Box
		try {
			WebElement btnSearch = 	GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Users.BtnSearch")));
			Thread.sleep(1500);
			btnSearch.click();
			Thread.sleep(2000);
		} catch (Exception e) {
			System.out.println("Unable To Click on Search | "+e.getMessage());
		}
		//Verify User with Mail ID
		try {
			WebElement txtEmail  = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.User.UserEmail")));
			String actEmail = txtEmail.getText();
			if (email.equalsIgnoreCase(actEmail)) 
			{
			//Click on Checkbox to delete User
			try {
				WebElement chkbxUser  = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.User.ChkbxUser")));
				chkbxUser.click();
			} catch (Exception e) {
				System.out.println("Unable to click on Check box of User | "+e.getMessage());
			}
			//Click Delete Selected Users Button to  delete User
			try {
				WebElement  btnDelete= GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.User.DeleteUser")));
				btnDelete.click();
			} catch (Exception e) {
				System.out.println("Unable to Click on Delete | "+e.getMessage());
			}
			//Accept Alert
			try {
				Alert alert = GenericMethods.driver.switchTo().alert();
				alert.accept();
			} catch (Exception e) {
				System.out.println("No Alert Found | "+e.getMessage());
			}
						
			//Verify Delete Message
			try 
			{
				System.out.println("aa");
				WebElement delMsg = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.User.DeleteMessage")));
				String message = delMsg.getText();
				if (message.contains("successfully")) 
				{
					ExcelUtils.setCellData(Constants.KEYWORD_PASS, hotelIDRow, ColumnResult, Constants.Sheet_HotelIDs);
				}
				else {
					ExcelUtils.setCellData(Constants.KEYWORD_FAIL+" | "+message, hotelIDRow, ColumnResult, Constants.Sheet_HotelIDs);
				}
			} catch (Exception e) {
				System.out.println("Unable to Find Delete Message"+e.getMessage());
			}
		//=================================
		}
		else
		{
			ExcelUtils.setCellData(Constants.KEYWORD_FAIL+" | Wrong Emai", hotelIDRow, ColumnResult, Constants.Sheet_HotelIDs);
		}
		} catch (Exception e) {
			ExcelUtils.setCellData(Constants.KEYWORD_FAIL, hotelIDRow, ColumnResult, Constants.Sheet_HotelIDs);
		}
	}
}