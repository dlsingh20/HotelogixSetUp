package Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class GenericMethods 
{
	public static WebDriver driver;
	
	//Launch browser and launch URL
	public static void lounchBrowserAndUrl(String browser, String url)
	{
		try{				
			if(browser.equalsIgnoreCase("FF"))
			{
				//System.setProperty("webdriver.gecko.driver", "E:\\Software\\Firefox\\geckodriver-v0.15.0-win64\\geckodriver.exe");
				DesiredCapabilities dc=DesiredCapabilities.firefox();
				//DesiredCapabilities dc = new DesiredCapabilities();
				dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
								
				driver=new FirefoxDriver(dc);
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				driver.get(url);
			}
			else if(browser.equalsIgnoreCase("IE"))
			{				
				//driver=new InternetExplorerDriver();
			}
			else if(browser.equalsIgnoreCase("CH"))
			{				
				//driver=new ChromeDriver();
			}
			
			int implicitWaitTime=(10);
			//driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
		}catch (Exception e)
		{
			
		}
	}
	
	//Object Repository 
	public static Properties OR() throws IOException
	{
		FileInputStream fis = new FileInputStream(new File(Constants.Path_OR));
		Properties pro = new Properties();
		pro.load(fis);
		return pro;
	}
	
	//------------Get Current window ID----------------------
	public static String getWindowHandle()
	{
		return GenericMethods.driver.getWindowHandle();
	}
	
	//---------Close All windows except parent
	public static boolean closeAllOtherWindows(String parentWindowID)
	{
		Set<String> allWindowIDs = GenericMethods.driver.getWindowHandles();
		for(String windowHandle: allWindowIDs)
		{
			if(!windowHandle.equals(parentWindowID))
			{
				GenericMethods.driver.switchTo().window(windowHandle);
				GenericMethods.driver.close();
			}
		}
		
		GenericMethods.driver.switchTo().window(parentWindowID);
		if(GenericMethods.driver.getWindowHandles().size()==1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//----------Switch into new window---------------
	public static void switchToNewWindow(String oldWindowID)
	{
		Set<String> allWindows = GenericMethods.driver.getWindowHandles();
		for(String newWindow : allWindows)
		{
			if(!newWindow.equals(oldWindowID))
			{
				GenericMethods.driver.switchTo().window(newWindow);
			}
		}
	}
	//<<<<----------Switch To Window----------->>>
	public static void ClickAndSwitchToWindow(WebElement ele)
	{
		try {
			System.out.println("A");
			Set<String> OldAllWindows = GenericMethods.driver.getWindowHandles();
			ele.click();
			Set<String> NewAllWindows = GenericMethods.driver.getWindowHandles();
			for(String newWindow : NewAllWindows)
			{
				if(!OldAllWindows.contains(newWindow))
				{
					GenericMethods.driver.switchTo().window(newWindow);
				}
			}
			
			//	
		} catch (Exception e) {
			System.out.println("Issue in Switching Window"+e.getMessage());
		}
	}
	
	//<<<-------------Alert------------------>>>
	public static void alertHandel()
	{
		Alert alert = GenericMethods.driver.switchTo().alert();
		alert.accept();	
		
	}
	
	//-------------JS Click--------------------
	public static void jsClick(WebElement ele)
	{
		JavascriptExecutor js = (JavascriptExecutor)GenericMethods.driver;
		js.executeScript("arguments[0].click();", ele);
	}
	
	//-------------Select By Visible Text--------------
	public static void selectByVisibleText(WebElement ele, String text)
	{
		Select select = new Select(ele);
		select.selectByVisibleText(text);
	}
	
	//-------------Select By Value--------------
	public static void selectByValue(WebElement ele, String value)
	{
		Select select = new Select(ele);
		select.selectByValue(value);
	}
	//-------------Select By Index--------------
	public static void selectByIndex(WebElement ele, int index)
	{
		Select select = new Select(ele);
		select.selectByIndex(index);
	}
	
	//Get Current Date
	public static String getCurrentDate()
	{
		DateFormat DF = new SimpleDateFormat("mm/dd/yyyy");
		Date date = new Date();
		String currMonthDate = DF.format(date).substring(3,5);
		//String currMonth = DF.format(date).substring(0,2);
		
		return currMonthDate;
	}
	//Get Current Month
	public static String getCurrentMonth()
	{
		DateFormat DF = new SimpleDateFormat("mm/dd/yyyy");
		Date date = new Date();
		//String currMonthDate = DF.format(date).substring(0,5);
		String currMonth = DF.format(date).substring(0,2);
		return currMonth;
	}
	
	/**
	 *This method will return Background color in String Format 
	 * @param allotCell
	 * @return String
	 * @throws IOException
	 */
	public static String getBackGroundColor(WebElement allotCell) throws IOException
	{
		//WebElement allotCell = GenericMethods.driver.findElement(By.xpath(GenericMethods.OR().getProperty("Admin.OthersGDSPackages.ManageAllotments.AllotmentCellForBGColor")));
		 String BGColor = allotCell.getCssValue("background-color").toString();
		return BGColor;
	}
	
	/**
	 * Select Date In Open Canendar
	 * @param calID
	 * @param newDate
	 */
	public static void selectDateInOpenCal(String calID, int newDate) {
		String date = Integer.toString(newDate);
		
		WebElement ele = driver.findElement(By.id(calID));
		List<WebElement> dates = ele.findElements(By.tagName("a"));
		for(WebElement cell : dates)
		{
			if (cell.getText().equals(date)) 
			{
				cell.click();
				break;
			}
		}
	}
	
	
}