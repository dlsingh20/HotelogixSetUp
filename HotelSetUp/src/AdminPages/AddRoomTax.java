package AdminPages;

import javax.servlet.jsp.tagext.TryCatchFinally;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import Utilities.ExcelUtils;
import Configuration.Constants;
import Configuration.GenericMethods;

public class AddRoomTax 
{
	public static String TaxID = "";
	public static String TaxTitle="";
	public static String Tax="";
	public static String TaxOn = "";
	public static String ErrorMessage1 = "";
	public static String ErrorMessage2 = "";
	//Will Have loop for creating all taxes
	//hotelIDRow is Hotel ID row number, rowcount is No. of Taxes available on Tax Sheet
	public static void addTaxes(int hotelIDRow, int rowcount) throws Exception
	{
		landOnRoomTaxes();
		for(int i = 1;i<rowcount;i++)
		{
			saveTaxes(hotelIDRow, i);
			continue;
		}
	}
	
	//Land On Room Tax Page
	public static void landOnRoomTaxes()
	{
		try {
			WebElement roomTaxes = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Roomtax")));
			roomTaxes.click();
		} catch (Exception e) {
			System.out.println("Not able to Click on Room Taxes | "+e.getMessage());
		}
	}
	//<<-------------------- Save Tax---------------------------------->>
	public static void saveTaxes(int hotelIDRow, int taxRowNo) throws Exception
	{
		int ColumnResult = taxRowNo+1;
		//Click on Add Tax
		try {
			System.out.println(" ");
			WebElement addTax = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Roomtax.AddTax"))); 
			addTax.click();
		} catch (Exception e) {
			System.out.println("Not able to Click on Add Tax | "+e.getMessage());
		}
		//Enter Tax Title
		try {
			WebElement taxTitle = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Roomtax.TaxTitle")));
			TaxTitle = ExcelUtils.getStringData(taxRowNo, Constants.SecondColumn, Constants.Sheet_RoomTax);
			taxTitle.sendKeys(TaxTitle);
		} catch (Exception e) {
			System.out.println("Issue in Tax Title | "+e.getMessage());
		}
		//Enter Tax ID
		try {
			WebElement taxID = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Roomtax.TaxID"))); 
			TaxID = ExcelUtils.getStringData(taxRowNo, Constants.ThirdColumn, Constants.Sheet_RoomTax);
			taxID.sendKeys(TaxID);
		} catch (Exception e) {
			System.out.println("Issue in Tax ID | "+e.getMessage());
		}
		//Click On Add Account Code
		try {
			WebElement addAccountCode = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Roomtax.AddAccountCode"))); 
			addAccountCode.click();
		} catch (Exception e) {
			System.out.println("Issue in clicking Add Account Code | "+e.getMessage());
		}
		//Enter Account Name
		try {
			//System.out.println(" ");
			WebElement accountName = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Roomtax.AccountName"))); 
			accountName.sendKeys(TaxTitle);
		} catch (Exception e) {
			System.out.println("Issue in Add Account Name | "+e.getMessage());
		}
		//Enter Account Codeggg
		try {
			Thread.sleep(2000);
			WebElement actCode = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Roomtax.AccountCode"))); 
			actCode.sendKeys(TaxID);
		} catch (Exception e) {
			System.out.println("Issue in Add Account ID | "+e.getMessage());
		}
		//Click On Save Account Code
		try {
			WebElement saveAccountCode = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Roomtax.SaveAccountCode"))); 
			saveAccountCode.click();
			
			Thread.sleep(2000);
			WebElement errorMsgAccCode = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Roomtax.ErrorMessage1")));
			if (errorMsgAccCode.isDisplayed()) 
			{
				//It will get Error Message
				ErrorMessage1 = errorMsgAccCode.getText();
				WebElement cancel = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Roomtax.CancelAccountCode")));
				cancel.click();
			}
		} catch (Exception e) {
			System.out.println("Issue in Save Account Code | "+e.getMessage());
		}
		
		//Enter Description
		try {
			WebElement desc = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Roomtax.Description"))); 
			desc.sendKeys(TaxTitle);
		} catch (Exception e) {
			System.out.println("Issue in Enter Description | "+e.getMessage());
		}
		
		//Enter Tax Value ..%age/Fixed
		try {
			Thread.sleep(2000);
			WebElement tax = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Roomtax.TaxValue")));
			Tax = ExcelUtils.getStringData(taxRowNo, Constants.FifthColumn, Constants.Sheet_RoomTax);
			tax.sendKeys(Tax);
		} catch (Exception e) {
			GenericMethods.alertHandel();
			System.out.println("Issue in Tax Percentage"+e.getMessage());
		}
		
		//Select Tax Type %age or Fixed
		try {
			String taxtype = ExcelUtils.getStringData(taxRowNo, Constants.FourthColumn, Constants.Sheet_RoomTax);
			
			if (taxtype.equalsIgnoreCase("1")) 
			{
				////-------------------Process for %age Tax-----------------------
				//Click on %age Radio Button
				try {
					WebElement taxType = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Roomtax.TaxTypePercent")));
					taxType.click();	
				} catch (Exception e) {
					System.out.println("Issue in Click On %age Radio Button"+e.getMessage());
				}
				//Select Tax On Tariff/Rack rate
				try {
					Thread.sleep(2000);
					TaxOn = ExcelUtils.getStringData(taxRowNo, Constants.SixthColumn, Constants.Sheet_RoomTax);
					WebElement selTaxTyp = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Roomtax.SelectTaxOnPercentage"))); 
					Select select = new Select(selTaxTyp);
					select.selectByValue(TaxOn);
					
				} catch (Exception e) {
					System.out.println("Issue in Select Tax On Tariff/rack rate"+e.getMessage());
					GenericMethods.alertHandel();
				}
									
			}else if (taxtype.equalsIgnoreCase("2")) 
			{
				////Process for Fix Tax
				try {
					WebElement taxType = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Roomtax.TaxTypeFixed"))); 
					taxType.click();	
				} catch (Exception e) {
					System.out.println("Issue in Click On Fixed Radio Button"+e.getMessage());
				}
				
				//Select Tax On..Per Nigh/Person...
				try {
					Thread.sleep(2000);
					TaxOn = ExcelUtils.getStringData(taxRowNo, Constants.SixthColumn, Constants.Sheet_RoomTax);
					WebElement selTaxTyp = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Roomtax.SelectTaxOnFixed"))); 
					Select select = new Select(selTaxTyp);
					select.selectByValue(TaxOn);
					
				} catch (Exception e) {
					System.out.println("Issue in Select Tax On ...Per Nigh/Person..."+e.getMessage());
				}
				
			}else
			{
				System.out.println("Getting Wrong Value From Excel");
			}
			
		} catch (Exception e) {
			System.out.println("Issue in Click Tax Type | "+e.getMessage());
		}
				
		//Click On Save tax
		try {
			WebElement save = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Roomtax.Save"))); 
			save.click();
			GenericMethods.alertHandel();
		} catch (Exception e) {
			System.out.println("Issue in Click Save"+e.getMessage());
		}
		
		//////////-----------Verify Success/Error Message
		try {
			WebElement successMessage = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Roomtax.SuccessMessage")));
			String message = successMessage.getText();
			if (message.contains("Saved Successfully")) 
			{
				ExcelUtils.setCellData(Constants.KEYWORD_PASS, taxRowNo, Constants.SeventhColumn, Constants.Sheet_RoomTax);
				ExcelUtils.setCellData(Constants.KEYWORD_PASS, hotelIDRow, ColumnResult, Constants.Sheet_HotelIDs);
			}else
			{
				
				//Write Fail in Excel with Error Message
				ExcelUtils.setCellData(Constants.KEYWORD_FAIL, taxRowNo, Constants.SeventhColumn, Constants.Sheet_RoomTax);
				ExcelUtils.setCellData(Constants.KEYWORD_FAIL, hotelIDRow, ColumnResult, Constants.Sheet_HotelIDs);
			}
			
		} catch (Exception e) 
		{
			WebElement errormsg2 = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Roomtax.ErrorMessage2")));
			ErrorMessage2 = errormsg2.getText();
			
			//Click on Room Tax Link after getting Error
			WebElement roomTaxListLink = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Roomtax.RoomTaxListLink")));
			roomTaxListLink.click();
			
			ExcelUtils.setCellData(Constants.KEYWORD_FAIL, taxRowNo, Constants.SeventhColumn, Constants.Sheet_RoomTax);
			ExcelUtils.setCellData(Constants.KEYWORD_FAIL+" | "+ErrorMessage1+" | "+ErrorMessage2, hotelIDRow, ColumnResult, Constants.Sheet_HotelIDs);
			e.getMessage();
		}
		
		/*try {
			WebElement errorMessage = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Roomtax.ErrorMessage")));
			if (errorMessage.isDisplayed()) {
				String msg = errorMessage.getText();
				ExcelUtils.setCellData(Constants.KEYWORD_FAIL, taxRowNo, Constants.SeventhColumn, Constants.Sheet_RoomTax);
				ExcelUtils.setCellData(Constants.KEYWORD_FAIL+" | "+msg, hotelIDRow, ColumnResult, Constants.Sheet_HotelIDs);
				
				WebElement roomTaxListLink = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Roomtax.RoomTaxListLink")));
				roomTaxListLink.click();
			}
		} catch (Exception e) {
			System.out.println("No Issue in Save");
		}*/
		
	}
}