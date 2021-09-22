package week5.day2.assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class UpdateIncident extends BaseIncident {

	@Test
	public void runupdateIncident() throws InterruptedException {

		// 4. Search for the existing incident and click on the incident
		// driver.switchTo().frame("gsft_main");
		driver.switchTo().defaultContent();
		System.out.println("Incident to be updated : " + incidentNumber);
		driver.findElement(By.xpath("//span[@data-original-title='Search']")).click();
		WebElement search = driver.findElement(By.id("sysparm_search"));
		// search.sendKeys("INC0010026");
		search.sendKeys(incidentNumber);
		search.sendKeys(Keys.ENTER);

		// 5. Update the incidents with Urgency as High and State as In Progress
		driver.switchTo().frame("gsft_main");
		WebElement urgency = driver.findElement(By.id("incident.urgency"));
		Select select = new Select(urgency);
		select.selectByValue("1");
		WebElement state = driver.findElement(By.id("incident.state"));
		Select select1 = new Select(state);
		select1.selectByValue("2");
		driver.findElement(By.id("sysverb_update")).click();
		Thread.sleep(2000);

		// 6. Verify the priority and state
		driver.switchTo().defaultContent();
		search.sendKeys(Keys.ENTER);

		driver.switchTo().frame("gsft_main");

		WebElement urgency1 = driver
				.findElement(By.xpath("(//select[@id='incident.urgency']/option)[@selected='SELECTED']"));
		if (urgency1.getText().equals("1 - High"))
			System.out.println("Urgency of Incident " + incidentNumber + " is set to High");
		else
			System.out.println("Urgency of Incident " + incidentNumber + " is not set to High");

		WebElement state1 = driver
				.findElement(By.xpath("(//select[@id='incident.state']/option)[@selected='SELECTED']"));
		if (state1.getText().equals("In Progress"))
			System.out.println("State of Incident " + incidentNumber + " is set to In Progress");
		else
			System.out.println("State of Incident " + incidentNumber + " is not set to In Progress");
	}

}
