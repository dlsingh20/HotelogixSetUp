package AdminPages;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import Utilities.ExcelUtils;
import Configuration.Constants;
import Configuration.GenericMethods;

public class RefreshAllotments 
{
	//public static WebElement allotCell;
	//static String BGColor=""; 
	public static void RefreshAllotment(int hotelIDRow, int rowcount) throws Exception
	{
		try {
			landOnAllotmentMatrix(hotelIDRow);
			
		} catch (Exception e) {
			System.out.println("Issue in Landing on Allotment Page |"+e.getMessage());
		}
		try {
			UpdateAllotments(hotelIDRow);
		} catch (Exception e) {
			System.out.println("Issue in Update Allotment |"+e.getMessage());
		}
	}
	
	
	/**
	 * 
	 * This method will take you on Allotment Matrix Page
	 */
	public static void landOnAllotmentMatrix(int hotelIDRow) throws Exception
	{
		//Click Other (GDS Package)
		try {
			WebElement otherGDSPackage = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.OthersGDSPackages")));
			otherGDSPackage.click();
		} catch (Exception e) {
			System.out.println("Issue in Land on Other GDS(Packages)"+e.getMessage());
		}
		
		//Click Manage Allotments
		try {
			WebElement manageAllotments = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.OthersGDSPackages.ManageAllotments")));
			manageAllotments.click();
		} catch (NoSuchElementException exception) {
			ExcelUtils.setCellData(Constants.KEYWORD_FAIL, hotelIDRow, Constants.ThirdColumn, Constants.Sheet_HotelIDs);
		}
	}
	//To Refresh Allotments
	public static void UpdateAllotments(int hotelIDRow)
	{
		String WindowID = GenericMethods.driver.getWindowHandle();
		///////////////*
		//Select All/Master Check Box
		try {
			WebElement masterCheckBox = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.OthersGDSPackages.ManageAllotments.MasterCheckBox")));
			masterCheckBox.click();
		} catch (Exception e) {
			System.out.println("Issue in Click Master Check obx"+e.getMessage());
		}
		
		//Click on Add/Edit Allotment
		try {
			WebElement addEditAllotmentBtn = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.OthersGDSPackages.ManageAllotments.AddEditAllotments")));
			addEditAllotmentBtn.click();
		} catch (Exception e) {
			System.out.println("Issue in Click Add/Edit Allotment Button");
		}
		
		//Switch to New Window
		try {
			GenericMethods.switchToNewWindow(WindowID);
		} catch (Exception e) {
			System.out.println("Issue in Switching into new Window"+e.getMessage());
		}
		
		//Click On From Calendar Icon
		try {
			WebElement calFromDate = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.OthersGDSPackages.ManageAllotments.CalFromDate")));
			calFromDate.click();
		} catch (Exception e) {
			System.out.println("Issue in Calendar From Date"+e.getMessage());
		}
		
		//Select Current Date
		try {
			String currDate = GenericMethods.getCurrentDate();
			WebElement allDates = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.OthersGDSPackages.ManageAllotments.CalFromDate.DateArea")));
			List<WebElement> dates = allDates.findElements(By.tagName("a"));
			
			for (WebElement date : dates) 
			{
				
				if (currDate.equalsIgnoreCase(date.getText())) 
				{
					date.click();
					break;
				}
			}
			
		} catch (Exception e) {
			System.out.println("Issue in Select Current Date | "+e.getMessage());
		}
		
		//Click On To Calendar Icon
		try {
			WebElement calToDate = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.OthersGDSPackages.ManageAllotments.CalToDate")));
			calToDate.click();
		} catch (Exception e) {
			System.out.println("Issue in Click To Cal Date Image | "+e.getMessage());
		}
		
		//Select To Date From Excel
		try {
			//Click on Month/Year link on Calender
			WebElement monthYear = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.OthersGDSPackages.ManageAllotments.CalToDate.MonthYearOnCal")));
			monthYear.click();
						
			//Enter Year			
			String endYear = ExcelUtils.getStringData(1, Constants.ThirdColumn, Constants.Sheet_Allotments);
			WebElement year = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.OthersGDSPackages.ManageAllotments.CalToDate.TxtBxYear")));
			year.clear();
			year.sendKeys(endYear);
			
			//Select Month From Drop-down
			String endMonth = ExcelUtils.getStringData(1, Constants.SecondColumn, Constants.Sheet_Allotments);
			String monString="";
			switch (endMonth)
			{
			case "1": monString="Jan";
			break;
			
			case "2": monString="Feb";
			break;
			
			case "3": monString="Mar";
			break;
			
			case "4": monString="Apr";
			break;
			
			case "5": monString="May";
			break;
			
			case "6": monString="Jun";
			break;
			
			case "7": monString="Jul";
			break;
			
			case "8": monString="Aug";
			break;
			
			case "9": monString="Sep";
			break;
			
			case "10": monString="Oct";
			break;
			
			case "11": monString="Nov";
			break;
			
			case "12": monString="Dec";
			break;
			}
			
			Select sel = new Select(GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.OthersGDSPackages.ManageAllotments.CalToDate.SelectMonthDrpDwn"))));
			sel.selectByVisibleText(monString);
			
			//Click OK
			WebElement ok = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.OthersGDSPackages.ManageAllotments.CalToDate.OK")));
			ok.click();	
			
			//Select Date on Cal
			String endDate = ExcelUtils.getStringData(1, Constants.FirstColumn, Constants.Sheet_Allotments);
			WebElement allDates = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.OthersGDSPackages.ManageAllotments.CalToDate.DateArea")));
			//System.out.println("----1-----");
			Thread.sleep(3000);
			List<WebElement> dates = allDates.findElements(By.tagName("a"));
			//System.out.println("----2-----");
			Thread.sleep(3000);
			for (WebElement date : dates) 
			{
				//System.out.println("----3-----");
				if (endDate.equalsIgnoreCase(date.getText())) 
				{
					//System.out.println("----4-----");
					date.click();
					break;
				}
			}
			
		} catch (Exception e) 
		{
			System.out.println("Issue In Select To Date | "+e.getMessage());
		}
		
		//Select Full Allotment from DropDown
		try {
			WebElement allotDrpDwn = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.OthersGDSPackages.ManageAllotments.AssignAllotmentDropDown")));
			Select sel = new Select(allotDrpDwn);
			int selcOpt = sel.getOptions().size();
			Thread.sleep(1500);
			sel.selectByIndex(selcOpt - 1);
			
		} catch (Exception e) {
			System.out.println("Issue in Select Allotment | "+e.getMessage());
		}
		
		//Click Save
		try {
			WebElement save = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.OthersGDSPackages.ManageAllotments.Save")));
			save.click();
			
		} catch (Exception e) {
			System.out.println("Issue in Click Save | "+e.getMessage());
		}
		//Switch to Admin Console
		try {
			GenericMethods.driver.switchTo().window(WindowID);
		} catch (Exception e) {
			System.out.println("Issue in Switch back to Admin  | "+e.getMessage());
		}
		///////////
		
		//Verify Success Message
		try {
			//System.out.println("aa");
		WebElement msg = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.OthersGDSPackages.ManageAllotments.Message")));
		String message = msg.getText();
		if (message.contains("- Allotment(s) updated")) 
		{
			WebElement allotCell = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.OthersGDSPackages.ManageAllotments.AllotmentCellForBGColor")));
			//String BGColor = GenericMethods.getBackGroundColor(allotCell);
			String BGColor = allotCell.getCssValue("background-color").toString();
			System.out.println("First Time Color |"+BGColor);
			
			
			//allotCell = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.OthersGDSPackages.ManageAllotments.AllotmentCellForBGColor")));
			 //BGColor = allotCell.getCssValue("background-color").toString();
			if (BGColor.equalsIgnoreCase(Constants.BackgroundColor_Success)) 
			{
				ExcelUtils.setCellData(Constants.KEYWORD_PASS, hotelIDRow, Constants.ThirdColumn, Constants.Sheet_HotelIDs);	
			
			}else if (BGColor.equalsIgnoreCase(Constants.BackgroundColor_InProgress)) 
			{
				Thread.sleep(20000);
				//Refresh and Check Color
				WebElement refresh = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.OthersGDSPackages.ManageAllotments.Refresh")));
				refresh.click();
				
				Thread.sleep(7000);
				WebElement allotCell1 = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.OthersGDSPackages.ManageAllotments.AllotmentCellForBGColor")));
				
				//String BGColor1 = GenericMethods.getBackGroundColor(allotCell1);
				String BGColor1 = allotCell1.getCssValue("background-color").toString();
				System.out.println("Second Time Color |"+BGColor1);
				
				if (BGColor1.equalsIgnoreCase(Constants.BackgroundColor_Success)) 
				{
					ExcelUtils.setCellData(Constants.KEYWORD_PASS, hotelIDRow, Constants.ThirdColumn, Constants.Sheet_HotelIDs);	
				
				}else if (BGColor1.equalsIgnoreCase(Constants.BackgroundColor_InProgress)) 
				{
					Thread.sleep(60000);
					//Refresh
					WebElement refresh1 = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.OthersGDSPackages.ManageAllotments.Refresh")));
					refresh1.click();
					Thread.sleep(7000);
					WebElement allotCell2 = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.OthersGDSPackages.ManageAllotments.AllotmentCellForBGColor")));
					//String BGColor2 = GenericMethods.getBackGroundColor(allotCell2);
					String BGColor2 = allotCell2.getCssValue("background-color").toString();
					System.out.println("Third Time BG Color |"+BGColor2);
					if (BGColor2.equalsIgnoreCase(Constants.BackgroundColor_Success)) 
					{
						ExcelUtils.setCellData(Constants.KEYWORD_PASS, hotelIDRow, Constants.ThirdColumn, Constants.Sheet_HotelIDs);	
					
					}else if (BGColor2.equalsIgnoreCase(Constants.BackgroundColor_InProgress)) 
					{
						Thread.sleep(120000);
						//Refresh and check allotment
						WebElement refresh2 = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.OthersGDSPackages.ManageAllotments.Refresh")));
						refresh2.click();
						Thread.sleep(1000);
						WebElement allotCell3 = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.OthersGDSPackages.ManageAllotments.AllotmentCellForBGColor")));
						
						String BGColor3 = GenericMethods.getBackGroundColor(allotCell3);
						System.out.println("Third Time BG Color"+BGColor3);
						if (BGColor3.equalsIgnoreCase(Constants.BackgroundColor_Success)) 
						{
							ExcelUtils.setCellData(Constants.KEYWORD_PASS, hotelIDRow, Constants.ThirdColumn, Constants.Sheet_HotelIDs);	
						
						}else if (BGColor.equalsIgnoreCase(Constants.BackgroundColor_InProgress)) 
						{
							ExcelUtils.setCellData("Pending For Long Time", hotelIDRow, Constants.ThirdColumn, Constants.Sheet_HotelIDs);
										
						}else 
						{
							ExcelUtils.setCellData(Constants.KEYWORD_FAIL, hotelIDRow, Constants.ThirdColumn, Constants.Sheet_HotelIDs);
						}
									
					}else 
					{
						ExcelUtils.setCellData(Constants.KEYWORD_FAIL, hotelIDRow, Constants.ThirdColumn, Constants.Sheet_HotelIDs);
					}
					
				}else 
				{
					ExcelUtils.setCellData(Constants.KEYWORD_FAIL, hotelIDRow, Constants.ThirdColumn, Constants.Sheet_HotelIDs);
				}			
			}else 
			{
				ExcelUtils.setCellData(Constants.KEYWORD_FAIL, hotelIDRow, Constants.ThirdColumn, Constants.Sheet_HotelIDs);
			}
			
			
						
		}else
		{
			ExcelUtils.setCellData(Constants.KEYWORD_FAIL, hotelIDRow, Constants.ThirdColumn, Constants.Sheet_HotelIDs);
		}
		
		} catch (Exception e) {
			System.out.println("Issue Checking Background Color"+e.getMessage());
		}
	}
	
	
}