package AdminPages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Utilities.ExcelUtils;
import Configuration.Constants;
import Configuration.GenericMethods;

public class Departments 
{
	public static void createDepartments(int hotelIDRow,int rowcount) throws IOException
	{
		landOnDepartmentPage();
		
		for(int i = 1;i<rowcount;i++)
		{
			saveDepartment(hotelIDRow ,i);
		}
		
	}
	
	public static void landOnDepartmentPage() throws IOException
	{
		WebElement dept = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Departments")));
		dept.click();
	}
	
	public static void saveDepartment(int hotelIDRow, int i) throws IOException
	{
		try {
			
			//Click on Add Department
			WebElement clickAddDept = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Department.AddDepartment")));
			clickAddDept.click();
			
			WebElement deptTitle = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Department.DepartmentTitle")));
			deptTitle.sendKeys(ExcelUtils.getStringData(i, Constants.SecondColumn, Constants.Sheet_Departments));
			
			WebElement deptShortName = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Department.DepartmentShortName")));
			deptShortName.sendKeys(ExcelUtils.getStringData(i, Constants.ThirdColumn, Constants.Sheet_Departments));
			
			WebElement deptDesc = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Department.DepartmentDescription")));
			deptDesc.sendKeys(ExcelUtils.getStringData(i, Constants.FourthColumn, Constants.Sheet_Departments));
			
			WebElement saveDept = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Department.DepartmentSave")));
			saveDept.click();
			
			int resultColumn = i+1;
			
			String pageTitle = GenericMethods.driver.getTitle();
			if (pageTitle.equalsIgnoreCase("Department List")) 
			{
				ExcelUtils.setCellData(Constants.KEYWORD_PASS, hotelIDRow, resultColumn, Constants.Sheet_HotelIDs);
				//ExcelUtils.setCellData(Result, RowNum, ColNum, SheetName);
				System.out.println("Department Saved");				
			}else
			{
				ExcelUtils.setCellData(Constants.KEYWORD_FAIL, hotelIDRow, resultColumn, Constants.Sheet_HotelIDs);
				WebElement departmentLink = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.Department.DepartmentList")));
				departmentLink.click();
			}
			
		} catch (Exception e) {
			System.out.println("Error in Save Departments"+e.getMessage());
		}
		
		
		
	}
	

}
