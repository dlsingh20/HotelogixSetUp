package AdminPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Utilities.ExcelUtils;
import Configuration.Constants;
import Configuration.GenericMethods;

public class AddPayTypes 
{
	public static String PayType = "";
	public static String ShortName = "";
	public static String ErrorMessage1 = "";
	public static String ErrorMessage2 = "";
	//Will Have loop for creating all taxes
		//hotelIDRow is Hotel ID row number, rowcount is No. of Taxes available on Tax Sheet
		public static void addPayTypes(int hotelIDRow, int rowcount) throws Exception
		{
			landOnPayTypes();
			for(int i = 1;i<rowcount;i++)
			{
				savePayTypes(hotelIDRow, i);
				continue;
			}
		}
		
		//Land On Pay Types in Admin Console 
		public static void landOnPayTypes()
		{
			try {
				WebElement roomTaxes = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.PayTypes")));
				roomTaxes.click();
			} catch (Exception e) {
				System.out.println("Not able to Click on Room Taxes | "+e.getMessage());
			}
		}
		//<<-------------------- Save PayTypes---------------------------------->>
		public static void savePayTypes(int hotelIDRow, int payTypeRowNo) throws Exception
		{
			int ColumnResult = payTypeRowNo+1;
			//Currently not working for Credit Card as discussed with Set up Team, they are not required 
			//Click Add Pay Type
			try {
				WebElement addAPayType = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.PayTypes.AddAPayType")));
				addAPayType.click();
			} catch (Exception e) {
				System.out.println("Issue in Add PayType"+e.getMessage());
			}
			
			//Enter Pay Type Name
			try {
				WebElement payType = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.PayTypes.PayType")));
				PayType = ExcelUtils.getStringData(payTypeRowNo, Constants.SecondColumn, Constants.Sheet_PayTypes);
				payType.sendKeys(PayType);
			} catch (Exception e) {
				System.out.println("Issue in Enter Pay Type Name"+e.getMessage());
			}
			
			//Enter pay Type Short Name
			try {
				WebElement payShortName = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.PayTypes.ShortName")));
				ShortName = ExcelUtils.getStringData(payTypeRowNo, Constants.ThirdColumn, Constants.Sheet_PayTypes);
				payShortName.sendKeys(ShortName);
			} catch (Exception e) {
				System.out.println("Issue in Enter Pay Type Short name"+e.getMessage());
			}
			
			//Click Add New Account Code
			try {
				WebElement addNewAccountCode = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.PayTypes.AddAccountCode")));
				addNewAccountCode.click();
			} catch (Exception e) {
				System.out.println("Issue in Click Add New Account Code"+e.getMessage());
			}
			
			//Enter Account Name
			try {
				//System.out.println(" ");
				WebElement accountName = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.PayTypes.AccountName"))); 
				accountName.sendKeys(PayType);
			} catch (Exception e) {
				System.out.println("Issue in Add Account Name | "+e.getMessage());
			}
			//Enter Account Codeggg
			try {
				Thread.sleep(2000);
				WebElement actCode = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.PayTypes.AccountCode"))); 
				actCode.sendKeys(ShortName);
			} catch (Exception e) {
				System.out.println("Issue in Add Account ID | "+e.getMessage());
			}
			//Click On Save Account Code
			try {
				WebElement saveAccountCode = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.PayTypes.SaveAccountCode"))); 
				saveAccountCode.click();
				
				Thread.sleep(2000);
				WebElement errorMsgAccCode = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.PayTypes.ErrorMessage1")));
				if (errorMsgAccCode.isDisplayed()) 
				{
					//It will get Error Message
					ErrorMessage1 = errorMsgAccCode.getText();
					WebElement cancel = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.PayTypes.CancelAccountCode")));
					cancel.click();
				}
			} catch (Exception e) {
				System.out.println("Issue in Save Account Code | "+e.getMessage());
			}
			
			//Select Payment Mode By Visible text
			try {
				WebElement payMode = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.PayTypes.PaymentMode")));
				String PayModeValue = ExcelUtils.getStringData(payTypeRowNo, Constants.FourthColumn, Constants.Sheet_PayTypes);
				GenericMethods.selectByVisibleText(payMode, PayModeValue);
			} catch (Exception e) {
				System.out.println("Issue in Select Payment Mode By Visible text"+e.getMessage());
			}
			
			//Enter Description
			try {
				WebElement desc = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.PayTypes.Description")));
				desc.sendKeys(PayType);
			} catch (Exception e) {
				System.out.println("Issue in Enter Description"+e.getMessage());
			}
			
			//Click Save
			try {
				WebElement save = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.PayTypes.Save")));
				save.click();
				GenericMethods.alertHandel();
			} catch (Exception e) {
				System.out.println("Issue in click Save");
			}
			
	//////////-----------Verify Success/Error Message
			try {
				WebElement successMessage = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.PayTypes.SaveSuccessMessage")));
				String message = successMessage.getText();
				if (message.contains("saved successfully")) 
				{
					ExcelUtils.setCellData(Constants.KEYWORD_PASS, hotelIDRow, ColumnResult, Constants.Sheet_HotelIDs);
				}else
				{
					//Write Fail in Excel with Error Message
					ExcelUtils.setCellData(Constants.KEYWORD_FAIL+" | "+message, hotelIDRow, ColumnResult, Constants.Sheet_HotelIDs);
				}
				
			} catch (Exception e) 
			{
				WebElement errormsg2 = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.PayTypes.ErrorMessage2")));
				ErrorMessage2 = errormsg2.getText();
				
				//Click on Room Tax Link after getting Error
				WebElement payTypeListLink = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.PayTypes.PayTypeListLink")));
				payTypeListLink.click();
				
				//ExcelUtils.setCellData(Constants.KEYWORD_FAIL, taxRowNo, Constants.SeventhColumn, Constants.Sheet_RoomTax);
				ExcelUtils.setCellData(Constants.KEYWORD_FAIL+" | "+ErrorMessage1+" | "+ErrorMessage2, hotelIDRow, ColumnResult, Constants.Sheet_HotelIDs);
				e.getMessage();
			}
		}
}