package api.utilities;

import org.testng.annotations.DataProvider;

public class DataProviderClass {
	
	
	@DataProvider(name="Data")
	public String[][] getAllData()
	{
		String path= System.getProperty("user.dir")+"\\testData\\userTestData.xlsx";
		System.out.print("path "+path);
		ExcelUtility excel= new ExcelUtility(path);
		
		int rowCount= excel.getRowCount("userTestDataSheet");
		int colCount = excel.getCellCount("userTestDataSheet", 1);
		
		String testData[][]= new String[rowCount][colCount];
		
		for(int i=1;i<=rowCount;i++)
		{
			for(int j= 0; j<colCount;j++)
			{
				testData[i-1][j]=excel.getCellData("userTestDataSheet", i, j);
			}
		}
		
		return testData;
		
	}
	
	
	@DataProvider(name="UserNames")
	public String[] getUserNames()
	{
		String path= System.getProperty("user.dir")+"//testData//userTestData.xlsx";
		ExcelUtility excel= new ExcelUtility(path);
		
		int rowCount= excel.getRowCount("userTestDataSheet");
		int colCount = excel.getCellCount("userTestDataSheet", 1);
		
		String testData[]= new String[rowCount];
		
		for(int i=1;i<=rowCount;i++)
		{
			testData[i-1]=excel.getCellData("userTestDataSheet", i, 1);
			
		}
		
		return testData;
		
	}

}
