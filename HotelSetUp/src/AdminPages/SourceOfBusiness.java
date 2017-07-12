package AdminPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Utilities.ExcelUtils;
import Configuration.Constants;
import Configuration.GenericMethods;

public class SourceOfBusiness 
{
	public static void createSourceOfBusiness(int hotelIDRow, int rowcount)
	{
		//Land on Source of Business Page
		landOnSourceOfBusiness();
		for(int i = 1;i<rowcount;i++)
		{
			saveSourceOfBusiness(hotelIDRow,i);
			//saveDepartment(i);
		}
	}
	
	//Land on Source of Business Page
	public static void landOnSourceOfBusiness()
	{
		try {
			WebElement sourceOfBusiness = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.SourceOfBusiness")));
			sourceOfBusiness.click();
		} catch (Exception e) {
			System.out.println("Unable to Land on Source Of Business"+e.getMessage());
		}
	}
	
	//Add and Save Source Of Business
	public static void saveSourceOfBusiness(int hotelIDRow, int i)
	{
		try {
			//Click on Add Source of Business
			WebElement clickAddSourceOfBusiness = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.SourceOfBusiness.AddSourceOfBusiness")));
			clickAddSourceOfBusiness.click();
			
			//Enter Title
			WebElement title = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.SourceOfBusiness.SourceOfBusinessTitle")));
			title.sendKeys(ExcelUtils.getStringData(i, Constants.SecondColumn, Constants.Sheet_SourceOfBusiness));
			
			//Enter Description
			WebElement description = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.SourceOfBusiness.Description")));
			description.sendKeys(ExcelUtils.getStringData(i, Constants.ThirdColumn, Constants.Sheet_SourceOfBusiness));
			
			//Click Save
			WebElement clickSave = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.SourceOfBusiness.Save")));
			clickSave.click();
			
			int resultColumn = i+1;

			String pageTitle = GenericMethods.driver.getTitle();
			if(pageTitle.equalsIgnoreCase("Source of Business List"))
			{
				ExcelUtils.setCellData(Constants.KEYWORD_PASS, hotelIDRow, resultColumn, Constants.Sheet_HotelIDs);
				//System.out.println("Source Of Business Saved");
			}else{
				ExcelUtils.setCellData(Constants.KEYWORD_FAIL, hotelIDRow, resultColumn, Constants.Sheet_HotelIDs);
				WebElement sourceOfBusinessList = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.SourceOfBusiness.SourceOfBusinessList")));
				sourceOfBusinessList.click();
			}
			
		} catch (Exception e) {
			System.out.println("Error in Save Source Of Business"+e.getMessage());
		}
		
	}
}
