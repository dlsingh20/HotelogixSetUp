package Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Configuration.Constants;

public class ExcelUtils 
{
	private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static org.apache.poi.ss.usermodel.Cell Cell;
    private static XSSFRow Row;
    public static String data;
    
    public static void setExcelFile(String Path) throws Exception {
    	try {
            FileInputStream ExcelFile = new FileInputStream(Path);
            ExcelWBook = new XSSFWorkbook(ExcelFile);
    		} catch (Exception e){
    		
    		}
    	}
    public static int getRowCount(String SheetName)
    {
		int iNumber=0;
		try {
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			iNumber=ExcelWSheet.getLastRowNum()+1;
		} catch (Exception e){
			
		}
		return iNumber;
	}
    
    public static String getStringData(int rowNum,int colNum,String sheetName)
	{
		try
		{
		ExcelWSheet=ExcelWBook.getSheet(sheetName);
		Cell=ExcelWSheet.getRow(rowNum).getCell(colNum);
		Cell.setCellType(Cell.CELL_TYPE_STRING);
		data=Cell.getStringCellValue();
		//System.out.println(data);
		
		}
		catch(Exception e)
		{			 
             return"";
		}
		return data;
	}
    
    public static void setCellData(String Result,  int RowNum, int ColNum, String SheetName) throws Exception    
	{
           try
           {        	   	   
        	   ExcelWSheet = ExcelWBook.getSheet(SheetName);
               Row  = ExcelWSheet.getRow(RowNum);
               Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
               if (Cell == null) {
            	   Cell = Row.createCell(ColNum);
            	   Cell.setCellValue(Result);
                } else {
                    Cell.setCellValue(Result);
                }
                 FileOutputStream fileOut = new FileOutputStream(Constants.Path_TestData);
                 ExcelWBook.write(fileOut);
                 //fileOut.flush();
                 fileOut.close();
                 ExcelWBook = new XSSFWorkbook(new FileInputStream(Constants.Path_TestData));
             }catch(Exception e){
            	 //DriverScript.bResult = false;

            }
    }

}