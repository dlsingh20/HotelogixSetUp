package AdminPages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Utilities.ExcelUtils;
import Configuration.Constants;
import Configuration.GenericMethods;

public class AdminHome 
{
	public static String adminWindowID;
		
	//Switching on Admin Console window
	public static void switchToAdminWindow()
	{
		GenericMethods.switchToNewWindow(HMSAdmin.parentWindowID);
		adminWindowID = GenericMethods.driver.getWindowHandle();
	}
	
	public static void LogoutAdmin() throws IOException
	{
		try {
			WebElement logout = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Logout")));
			logout.click();
			//GenericMethods.jsClick(logout);
			
		} catch (Exception e) {
			System.out.println("Error in Admin Logout "+e.getMessage());
		}
	}
	
	public static void SaveDataInHotel(int hotelIDRow) throws Exception
	{
		try {
			//Will get Module Name from RunFor Sheet then will pass the same name as Excel Sheet
			String DataSheetName = ExcelUtils.getStringData(1, Constants.FirstColumn, Constants.Sheet_RunFor);
			int rowCount = ExcelUtils.getRowCount(DataSheetName);
			
			switch (DataSheetName)
			{
			case "Departments": Departments.createDepartments(hotelIDRow,rowCount);
			break;
				
			case "SourceOfBusiness": SourceOfBusiness.createSourceOfBusiness(hotelIDRow, rowCount);
			break;
			
			case "CreateUser":AddUser.createUsers(hotelIDRow, rowCount);
			break;
				
			case "AddOns":
			break;
				
			case "RoomTax":AddRoomTax.addTaxes(hotelIDRow, rowCount);
			break;
			
			case "SlabTax":AddSlabTax.addTaxes(hotelIDRow, rowCount);
			break;
			
			//For Editing Slab Taxes
			case "EditSlabTax":EditSlabTax.EditSlabTaxes(hotelIDRow, rowCount);
			break;
			
			case "PayTypes":AddPayTypes.addPayTypes(hotelIDRow, rowCount);
			break;
			
			case "DeleteUser":DeleteUser.DeleteUsers(hotelIDRow, rowCount);
			break;
			
			case "RefreshAllotments":RefreshAllotments.RefreshAllotment(hotelIDRow, rowCount);
			break;
					
			}	
		} catch (Exception e) {
			System.out.println("Issue in Switching to Particular Module"+e.getMessage());
		}
		
		
	}
}