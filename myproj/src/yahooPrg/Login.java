package yahooPrg;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;

import yahooProp.LoginP;

public class Login extends MainClass
{
	 public void open()
	 {
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		 driver.get(LoginP.url);
		 l.debug("after yahoo url open");
	 }
	 public void login() throws Exception
	 {
		 open();
		 l.debug("before login with valid input");
		 driver.findElement(By.name(LoginP.nname)).sendKeys("venkat123456a");
		 driver.findElement(By.name(LoginP.nnext1)).click();
		 Thread.sleep(3000);
		 driver.findElement(By.name(LoginP.npwd)).sendKeys("mqq987654");
		 driver.findElement(By.name(LoginP.nnext2)).click();
		 Thread.sleep(5000);
	 }
	 public void login_validate() throws Exception
	 {
		 FileInputStream fin=new FileInputStream("d:\\sdtnov18\\data.xlsx");
		 XSSFWorkbook wb=new XSSFWorkbook(fin); //workbook in the excel file		
		 XSSFSheet ws=wb.getSheet("Sheet2");   //get sheet in the workbook
			
		 Row row;
		 for(int r=1;r<=ws.getLastRowNum();r++) //for all the rows in Sheet
		 {
				row=ws.getRow(r);
				open();
				l.debug("Login with different input values from excel");
				driver.findElement(By.name(LoginP.nname)).sendKeys(row.getCell(0).getStringCellValue());
				driver.findElement(By.name(LoginP.nnext1)).click();
				driver.findElement(By.name(LoginP.npwd)).sendKeys(row.getCell(1).getStringCellValue());
				driver.findElement(By.name(LoginP.nnext2)).click();
				try
				{
					if(driver.findElement(By.linkText(LoginP.lsignout)).isDisplayed())
					{
						row.createCell(2).setCellValue("login is success");
						driver.findElement(By.linkText(LoginP.lsignout)).click();
					}
				}
				catch(Exception obj)
				{
					l.debug("Login failed error message");
					String str=driver.findElement(By.xpath(LoginP.xerrmsg)).getText();
					row.createCell(2).setCellValue("Login is failed   :"+str);
				}				
			}
			FileOutputStream fout=new FileOutputStream("d:\\sdtnov18\\data.xlsx");
			wb.write(fout);
			fin.close();
			fout.close();
	 }
	 public void signup_validate() throws Exception
	 {
		 open();
		 Thread.sleep(5000);
		 JavascriptExecutor js=(RemoteWebDriver)driver;
		 js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		 
		 l.debug("before click signup");
		 new Actions(driver).moveToElement(driver.findElement(By.id(LoginP.isignup)))
		                    .click().perform();
		 
		 Thread.sleep(3000);
		 try
		 {
			 l.debug("validating signup and check firstname");
			 if(driver.findElement(By.name(LoginP.nfname)).isDisplayed())
			 {
				 log=ext.createTest("PassTest");
				 log.log(Status.PASS, "Registration page displayed");
				 takescreenshot(imagepath+"reg.png");
				 log.addScreenCaptureFromPath(imagepath+"reg.png");
			 }
			 					
		 }
		 catch(Exception e)
		 {
			 log=ext.createTest("FailTest");
			 log.log(Status.FAIL, "Registration NOT displayed");
			 takescreenshot(imagepath+"reg.png");
			 log.addScreenCaptureFromPath(imagepath+"reg.png");
		 }
	 }
}

