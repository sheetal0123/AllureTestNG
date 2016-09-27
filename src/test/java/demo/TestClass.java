package demo;

import org.apache.http.impl.cookie.BrowserCompatSpecFactory.SecurityLevel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

public class TestClass {

	WebDriver driver;
	
	@BeforeClass
	public void before(){
		System.out.println("Before Class");
		driver = new FirefoxDriver();
	}
	
	
	@AfterClass
	public void tearDown(){
		System.out.println("After Class");
		driver.quit();
	}
	
	@Features("Feature is for BDD reporting")
	@Stories("Stories is for BDD reporting")
	@Severity(SeverityLevel.CRITICAL)
	@Title("This test check Google title")
	@Description("Description: We need to verify if page loads proply")
	@Test
	public void one(){
		System.out.println("Test 1");
		driver.get("http://www.google.co.in");
		Assert.assertTrue(driver.getTitle().equals("Google"),"Google actual title: "+driver.getTitle());
	}
	

	@Test
	public void two(){
		System.out.println("Test 2");
		driver.get("https://in.yahoo.com/");
		Assert.assertTrue(driver.getTitle().equals("Yahoo"),"Yahoo actual title: "+driver.getTitle());
	}

	@Test
	public void three(){
		System.out.println("Test 3");
		driver.get("https://www.bing.com/");
		Assert.assertTrue(driver.getTitle().equals("Bing"),"Bing actual title: "+driver.getTitle());
	}


	@Test
	public void four(){
		System.out.println("Test 4");
		Assert.assertTrue(50 > 100);
	}
	
}
