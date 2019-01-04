package yahooPrg;

import org.openqa.selenium.By;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;

import yahooProp.ComposeP;
import yahooProp.LoginP;

public class Compose extends MainClass
{
	
 public void sendmail() throws Exception
 {
	driver.findElement(By.xpath(ComposeP.xcompose)).click();
	Thread.sleep(2000);
	try
	{
		l.debug("check the compose button working or not");
		if(driver.findElement(By.id(ComposeP.ito)).isDisplayed())
		{
			log=ext.createTest("PassTest");
			log.log(Status.PASS, "Compose page displayed");
			takescreenshot(imagepath+"compose.png");
			log.addScreenCaptureFromPath(imagepath+"compose.png");
			 
			driver.findElement(By.id(ComposeP.ito)).sendKeys("abcd@gmail.com");
			driver.findElement(By.id(ComposeP.isub)).sendKeys("sample mail");
			driver.findElement(By.name(ComposeP.nbody)).sendKeys("This is test mail for checking");
			driver.findElement(By.id(ComposeP.isend)).click();
		}
	}
	catch(Exception e)
	{
		 l.debug("Compose button not working validation");
		 log=ext.createTest("FailTest");
		 log.log(Status.FAIL, "Compose not working");
		 takescreenshot(imagepath+"compose.png");
		 log.addScreenCaptureFromPath(imagepath+"compose.png");
	}	
 }
 public void signout()
 {
	 l.debug("before signout from yahoo");
	 driver.findElement(By.linkText(LoginP.lsignout)).click();
	 driver.close();
 }
}
