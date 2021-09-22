package week5.day2.assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EditLead extends BaseClass {

	@Test(dataProvider = "sendData")
	public void runEditLead(String phNo, String compName) throws InterruptedException {
		driver.findElement(By.linkText("Find Leads")).click();
		driver.findElement(By.xpath("//span[text()='Phone']")).click();
		driver.findElement(By.xpath("//input[@name='phoneNumber']")).sendKeys(phNo);
		driver.findElement(By.xpath("//button[text()='Find Leads']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).click();
		driver.findElement(By.linkText("Edit")).click();
		WebElement company = driver.findElement(By.id("updateLeadForm_companyName"));
		company.clear();
		company.sendKeys(compName);
		driver.findElement(By.name("submitButton")).click();
	}

	@BeforeClass
	public void setFileName() {
		fileName = "editLead";
	}
}