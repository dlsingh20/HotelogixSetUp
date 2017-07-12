package AdminPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import Utilities.ExcelUtils;
import Configuration.Constants;
import Configuration.GenericMethods;

public class EditSlabTax 
{
	public static String TaxID = "";
	public static String TaxTitle="";
	public static String Tax="";
	public static String TaxOn = "";
	public static String ErrorMessage1 = "";
	public static String ErrorMessage2 = "";
	//Will Have loop for creating all taxes
	//hotelIDRow is Hotel ID row number, row count is No. of Taxes available on Tax Sheet
	public static void EditSlabTaxes(int hotelIDRow, int rowcount) throws Exception
	{
		landOnRoomTaxes();
		for(int i = 1;i<rowcount;i++)
		{
			EditSaveTaxes(hotelIDRow, i);
			//continue;
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
		
		//<<-------------------- Edit/Save Tax---------------------------------->>
		public static void EditSaveTaxes(int hotelIDRow, int taxRowNo) throws Exception
		{
			
			System.out.println("a");
			int ColumnResult = taxRowNo+1;
			
			
			
				//Get Tax Name From Excel
			try {
				TaxTitle = ExcelUtils.getStringData(taxRowNo, Constants.SecondColumn, Constants.Sheet_EditSlabTax);
			} catch (Exception e) {
				System.out.println("Issue in Getting Tax Name from Excel"+e.getMessage());
			}	
			
			try {	
				List<WebElement> allTaxname = GenericMethods.driver.findElements(By.xpath(GenericMethods.OR().getProperty("Admin.Roomtax.FindAllTaxesName")));
				int LOT = allTaxname.size();
				//Search Tax on Tax Page
				for(int i= 2;i<LOT;i++)
				{
					WebElement TxNm = GenericMethods.driver.findElement(By.xpath("//td[@class='padingtd']/table/tbody/tr["+i+"]/td[3]"));
					String actTaxName = TxNm.getText().trim();
					if(TaxTitle.equalsIgnoreCase(actTaxName))
					{
						WebElement editTax = GenericMethods.driver.findElement(By.xpath("//td[@class='padingtd']/table/tbody/tr["+i+"]/td[6]/a"));
						editTax.click();
						break;
					}
				}
			} catch (Exception e) {
				System.out.println("Issue in getting Getting Tax Name of Verify "+e.getMessage());
			}
			//Click Edit on Slab 
			try {
				WebElement editSlab = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Roomtax.FindAllTaxesName.EditSlab")));
				editSlab.click();
			} catch (Exception e) {
				System.out.println("Issue in Click Edit Slab"+e.getMessage());
			}
			
			//Get Charge/tariff Amount from Excel and send to First Text Box
			try {
				String SlabCharge1Value = ExcelUtils.getStringData(taxRowNo, Constants.FifthColumn, Constants.Sheet_EditSlabTax);
				
				WebElement SlabCharge1 = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Roomtax.AddSlab.SlabCharge1")));
				SlabCharge1.clear();
				Thread.sleep(500);
				SlabCharge1.sendKeys(SlabCharge1Value);
			} catch (Exception e) {
				System.out.println("Issue in First Slab Value"+e.getMessage());
			}
					
			//Get Tax from Excel send to first Tax Text Box
			try {
				String SlabTax1Value = ExcelUtils.getStringData(taxRowNo, Constants.SixthColumn, Constants.Sheet_EditSlabTax);
				
				WebElement SlabTax1 = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Roomtax.AddSlab.SlabTax1")));
				SlabTax1.clear();
				Thread.sleep(500);
				SlabTax1.sendKeys(SlabTax1Value);
			} catch (Exception e) {
				System.out.println("Issue in First Slab Tax Value"+e.getMessage());
			}
		////Get second Slab Charge/tariff Amount from Excel and send to Second Text Box
			try {
				String SlabCharge2Value = ExcelUtils.getStringData(taxRowNo, Constants.SeventhColumn, Constants.Sheet_EditSlabTax);
				
				WebElement SlabCharge2 = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Roomtax.AddSlab.SlabCharge2")));
				SlabCharge2.clear();
				Thread.sleep(500);
				SlabCharge2.sendKeys(SlabCharge2Value);	
			} catch (Exception e) {
				System.out.println("Issue in Second Slab Charge Value"+e.getMessage());
			}
			//Get Tax from Excel
			try {
				String SlabTax2Value = ExcelUtils.getStringData(taxRowNo, Constants.EighthColumn, Constants.Sheet_EditSlabTax);
				
				WebElement SlabTax2 = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Roomtax.AddSlab.SlabTax2")));
				SlabTax2.clear();
				Thread.sleep(500);
				SlabTax2.sendKeys(SlabTax2Value);
			} catch (Exception e) {
				System.out.println("Issue in Second Slab Tax Value"+e.getMessage());
			}
			//Get Tax from Excel
			try {
				String SlabTax3Value = ExcelUtils.getStringData(taxRowNo, Constants.NinethColumn, Constants.Sheet_EditSlabTax);
				
				WebElement SlabTax3 = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Roomtax.AddSlab.SlabTax3")));
				SlabTax3.clear();
				Thread.sleep(500);
				SlabTax3.sendKeys(SlabTax3Value);
					
			} catch (Exception e) {
				System.out.println("Issue in Second Slab Tax Value"+e.getMessage());
			}
			
			//Select Activation Date
			String activationDate = ExcelUtils.getStringData(taxRowNo, Constants.FourthColumn, Constants.Sheet_EditSlabTax);
			
			int dateToSelect = Integer.parseInt(activationDate.substring(10,12));
			int monthToSelect = Integer.parseInt(activationDate.substring(6,8));
			String month = Integer.toString(monthToSelect-1);
			String yearToEnter =  activationDate.substring(0,4);
			
			//Click On Calendar
			try {
				WebElement calendar = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Roomtax.SlabActivationDateCalendar")));
				calendar.click();	
			} catch (Exception e) {
				System.out.println("Issue in Click Calendar"+e.getMessage());
			}
			
			//Click on Month Year on Calendar to select Month
			try {
				WebElement monthYear = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Roomtax.SlabActivationDateCalendar.MonthYear")));
				monthYear.click();
			} catch (Exception e) {
				System.out.println("Issue in Click Month Year"+e.getMessage());
			}
			//Select Month from DripDown
			try {
				WebElement chooseMonthDD = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Roomtax.SlabActivationDateCalendar.MonthYear.monthDropDown")));
				Select sel = new Select(chooseMonthDD);
				sel.selectByValue(month);
			} catch (Exception e) {
				System.out.println("Issue in select Month"+e.getMessage());
			}
			//Enter Year
			try {
				WebElement enterYear = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Roomtax.SlabActivationDateCalendar.MonthYear.yearTextBox")));
				enterYear.clear();
				Thread.sleep(1000);
				enterYear.sendKeys(yearToEnter);
			} catch (Exception e) {
				System.out.println("Issue in Enter Year"+e.getMessage());
			}
			//Click OK on Calender
			try {
				WebElement clickOk = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Roomtax.SlabActivationDateCalendar.MonthYear.OkButton")));
				clickOk.click();
			} catch (Exception e) {
				System.out.println("Issue in Click Ok on Calendar"+e.getMessage());
			}
			
			//Select Date on Cal
			try {
				GenericMethods.selectDateInOpenCal(GenericMethods.OR().getProperty("Admin.Roomtax.SlabActivationDateCalendarID"),dateToSelect);
			} catch (Exception e) {
				System.out.println(" Issue in select Date on Calendar "+e.getMessage());
			}
			//================================================
			//Click On Save tax
			try {
				WebElement save = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Roomtax.Save"))); 
				save.click();
				GenericMethods.alertHandel();
			} catch (Exception e) {
				System.out.println("Issue in Click Save"+e.getMessage());
			}
			
		////-----------Verify Success/Error Message
			try {
				WebElement successMessage = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Roomtax.SuccessMessage")));
				String message = successMessage.getText();
				if (message.contains("Saved Successfully")) 
				{
					//ExcelUtils.setCellData(Constants.KEYWORD_PASS, taxRowNo, Constants.SeventhColumn, Constants.Sheet_SlabTax);
					ExcelUtils.setCellData(Constants.KEYWORD_PASS, hotelIDRow, ColumnResult, Constants.Sheet_HotelIDs);
				}else
				{					
					//Write Fail in Excel with Error Message
					//ExcelUtils.setCellData(Constants.KEYWORD_FAIL, taxRowNo, Constants.SeventhColumn, Constants.Sheet_SlabTax);
					ExcelUtils.setCellData(Constants.KEYWORD_FAIL, hotelIDRow, ColumnResult, Constants.Sheet_HotelIDs);
				}
				
			} catch (Exception e) 
			{
				WebElement errormsg2 = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Roomtax.ErrorMessage2")));
				ErrorMessage2 = errormsg2.getText();
				
				//Click on Room Tax Link after getting Error
				WebElement roomTaxListLink = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Roomtax.RoomTaxListLink")));
				roomTaxListLink.click();
				
				//ExcelUtils.setCellData(Constants.KEYWORD_FAIL, taxRowNo, Constants.SeventhColumn, Constants.Sheet_SlabTax);
				ExcelUtils.setCellData(Constants.KEYWORD_FAIL+" | "+ErrorMessage1+" | "+ErrorMessage2, hotelIDRow, ColumnResult, Constants.Sheet_HotelIDs);
				e.getMessage();
			}
		}
}
