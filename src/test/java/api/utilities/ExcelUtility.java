package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtility {
	
	
	String path;
	public FileInputStream fis;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	
	
	
	
	public ExcelUtility(String path)
	{
		this.path= path;
		
	}
	
	public int getRowCount(String sheetName)
	{
		int rowCount=0;
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(sheetName);
			rowCount= sheet.getLastRowNum();
			workbook.close();
			fis.close();
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rowCount;
		
	}
	
	public int getCellCount(String sheetName,int RowNum)
	{
		int cellCount=0;
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(RowNum);
			cellCount = row.getLastCellNum();
			workbook.close();
			fis.close();
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cellCount;
		
	}
	
	
	public String getCellData(String sheetName,int RowNum,int column)
	{
		String data=null;
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(RowNum);
			cell = row.getCell(column);
			DataFormatter formatter= new DataFormatter();
			try {
				data= formatter.formatCellValue(cell);
			}catch(Exception e)
			{
				//e.printStackTrace();
				data="";
			}
			
			workbook.close();
			fis.close();
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
		
	}
	
	
	public void setCellData(String sheetName,int RowNum,int column,String data)
	{
		
		try {
			File file = new File(path);
			
			if(!file.exists())
			{
				workbook = new XSSFWorkbook();
				fo= new FileOutputStream(path);
				workbook.write(fo);
			}
			
			
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			if(workbook.getSheetIndex(sheetName)==-1)
			{
				sheet = workbook.createSheet(sheetName);
			}
			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(RowNum);
			cell = row.getCell(column);
			cell.setCellValue(data);
			
			workbook.close();
			fis.close();
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
