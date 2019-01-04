package yahooSuite;

import java.io.FileInputStream;
import java.lang.reflect.Method;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import yahooPrg.Compose;
import yahooPrg.Inbox;
import yahooPrg.Login;
import yahooPrg.MainClass;

public class ReTest extends MainClass
{
  @Test
  @Parameters({"browser"})
  public void retesting(String str) throws Exception
  {
	  if(str.matches("firefox"))
	  {
		  
		  System.setProperty("webdriver.gecko.driver","D:\\sdtnov18\\myproj\\geckodriver.exe");
		  driver=new FirefoxDriver();
	  }
	  if(str.matches("chrome"))
	  {
		  System.setProperty("webdriver.chrome.driver","D:\\sdtnov18\\myproj\\chromedriver.exe");
		  driver=new ChromeDriver();
	  }
	  
	  FileInputStream fin=new FileInputStream("D:\\sdtnov18\\testcases.xlsx");
	  XSSFWorkbook wb=new XSSFWorkbook(fin);
	  XSSFSheet ws=wb.getSheet("ReTest");
	  Row row;
	  String classname,methodname;
	  for(int r=1;r<=ws.getLastRowNum();r++)
	  {
		  row=ws.getRow(r);
		  if(row.getCell(5).getStringCellValue().matches("yes"))
		  {
			  classname=row.getCell(3).getStringCellValue();
			  methodname=row.getCell(4).getStringCellValue();
			  Class c=Class.forName(classname); 
			  Method m=c.getMethod(methodname,null);
			  Object obj=c.newInstance(); 
			  m.invoke(obj,null);					  
		  }
	  }
	  fin.close();
  }
}
