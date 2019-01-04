package yahooPrg;

import org.openqa.selenium.By;

import yahooProp.InboxP;

public class Inbox extends MainClass
{
  public void deletemail() throws Exception
  {
	  l.debug("before delete a mail");
	  driver.findElement(By.xpath(InboxP.xmail)).click();
	  Thread.sleep(3000);
	  driver.findElement(By.id(InboxP.idelete)).click();
  }
}
