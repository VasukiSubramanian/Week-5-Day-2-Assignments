package week5.day2.assignments;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreateIncident extends BaseIncident {

	@Test(dataProvider = "sendData")
	public void runCreateIncident(String shortDesc) {

		// 4. Click on Create new option and fill the manadatory fields
		driver.findElement(By.xpath("//div[text()='Create New']")).click();
		driver.switchTo().frame("gsft_main");
		incidentNumber = driver.findElement(By.id("incident.number")).getAttribute("value");
		driver.findElement(By.id("lookup.incident.caller_id")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowHandlesList = new ArrayList<String>(windowHandles);
		driver.switchTo().window(windowHandlesList.get(1));
		driver.findElement(By.xpath("//a[@class='glide_ref_item_link']")).click();
		driver.switchTo().window(windowHandlesList.get(0));
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.id("incident.short_description")).sendKeys(shortDesc);
		driver.findElement(By.id("sysverb_insert")).click();

		// 5. Verify the newly created incident ( copy the incident number and paste it
		// in search field and enter then verify the instance number created or not)
		driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys(incidentNumber, Keys.ENTER);
		String incidentNumber2 = driver.findElement(By.xpath("//a[@class='linked formlink']")).getText();
		if (incidentNumber.equals(incidentNumber2))
			System.out.println("Incident " + incidentNumber + " created");
		else
			System.out.println("Incident " + incidentNumber + " not created");
	}
	
	@BeforeClass
	public void setFileName() {
		fileName = "createIncident";
	}

}
