package week5.day2.assignments;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AssignIncident extends BaseIncident {

	@Test(dataProvider = "sendData")
	public void runAssignIncident(String assignGroup, String workNotes) throws InterruptedException {

		// 4. Search for the existing incident and click on the incident
		// driver.switchTo().frame("gsft_main");
		driver.switchTo().defaultContent();
		System.out.println("Incident to be assigned : " + incidentNumber);
		driver.findElement(By.xpath("//span[@data-original-title='Search']")).click();
		WebElement search = driver.findElement(By.id("sysparm_search"));
		// search.sendKeys("INC0010025");
		search.sendKeys(incidentNumber);
		search.sendKeys(Keys.ENTER);

		// 5. Assign the incident to Software group
		driver.switchTo().frame("gsft_main");

		driver.findElement(By.id("lookup.incident.assignment_group")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowHandlesList = new ArrayList<String>(windowHandles);
		driver.switchTo().window(windowHandlesList.get(1));
		driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys(assignGroup, Keys.ENTER);
		driver.findElement(By.xpath("//a[text()='Software']")).click();

		// 6. Update the incident with Work Notes
		driver.switchTo().window(windowHandlesList.get(0));
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.id("activity-stream-textarea")).sendKeys(workNotes);
		driver.findElement(By.id("sysverb_update")).click();

		// 7. Verify the Assignment group and Assigned for the incident
		driver.switchTo().defaultContent();
		search.sendKeys(Keys.ENTER);
		driver.switchTo().frame("gsft_main");
		String group = driver.findElement(By.id("sys_display.incident.assignment_group")).getAttribute("value");
		if (group.equals("Software"))
			System.out.println("Assignment group of Incident " + incidentNumber + " is Software");
		else
			System.out.println("Assignment group of Incident " + incidentNumber + " is not Software");
	}
	
	@BeforeClass
	public void setFileName() {
		fileName = "AssignIncident";
	}
}
