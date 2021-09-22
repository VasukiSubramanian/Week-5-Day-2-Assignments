package week5.day2.assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class DeleteIncident extends BaseIncident {

	@Test
	public void runDeleteIncident() throws InterruptedException {

		// 4. Search for the existing incident and click on the incident
		// driver.switchTo().frame("gsft_main");
		driver.switchTo().defaultContent();
		System.out.println("Incident to be deleted : " + incidentNumber);
		driver.findElement(By.xpath("//span[@data-original-title='Search']")).click();
		WebElement search = driver.findElement(By.id("sysparm_search"));
		// search.sendKeys("INC0010019");
		search.sendKeys(incidentNumber);
		search.sendKeys(Keys.ENTER);

		// 5. Delete the incident
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.id("sysverb_delete")).click();
		driver.findElement(By.id("ok_button")).click();
		Thread.sleep(2000);

		// 6. Verify the deleted incident
		driver.switchTo().defaultContent();
		// search.sendKeys("INC0010024");
		search.sendKeys(Keys.ENTER);
		Thread.sleep(4000);
		driver.switchTo().frame("gsft_main");
		String records = driver.findElement(By.xpath("//div[@class='info-bar-left']//strong")).getText();
		if (records.equals("0"))
			System.out.println("Incident " + incidentNumber + " deleted successfully");
		else
			System.out.println("Incident " + incidentNumber + " not deleted");
	}
}
