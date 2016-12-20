package demo;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.model.SeverityLevel;

@Title("Test Class Title")
@Description("Description: Test Class Description")
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
	
	//-----------------------------------Section 1------------------------------------------//
	/**
	 * Simple test cases which explains how we can use feature, stories, title of test cases which comes in reporting
	 */
	
	@Features("Simple Reporting for Google: 1")
	@Stories("Google Stories is for BDD reporting")
	@Severity(SeverityLevel.CRITICAL)
	@Title("This test check Google title")
	@Description("Description: We need to verify if Google page loads properly")
	@Test
	public void one(){
		System.out.println("Test 1");
		driver.get("http://www.google.co.in");
		Assert.assertTrue(driver.getTitle().equals("Google"),"Google actual title: "+driver.getTitle());
	}
	

	@Features("Simple Reporting for Yahoo: 2")
	@Stories("Yahoo Stories is for BDD reporting")
	@Severity(SeverityLevel.CRITICAL)
	@Title("This test check Yahoo title")
	@Description("Description: We need to verify if Yahoo page loads properly")
	@Test
	public void two(){
		System.out.println("Test 2");
		driver.get("https://in.yahoo.com/");
		Assert.assertTrue(driver.getTitle().equals("Yahoo"),"Yahoo actual title: "+driver.getTitle());
	}

	@Features("Simple Reporting for Bing: 3")
	@Stories("Bing Stories is for BDD reporting")
	@Severity(SeverityLevel.CRITICAL)
	@Title("This test check Bing title")
	@Description("Description: We need to verify if Bing page loads properly")
	@Test
	public void three(){
		System.out.println("Test 3");
		driver.get("https://www.bing.com/");
		Assert.assertTrue(driver.getTitle().equals("Bing"),"Bing actual title: "+driver.getTitle());
	}


	@Features("Simple Math Problem : 4")
	@Stories("Maths Stories is for BDD reporting")
	@Severity(SeverityLevel.CRITICAL)
	@Title("Maths problems")
	@Description("Description: Maths way")
	@Test
	public void four(){
		System.out.println("Test 4");
		System.out.println("Division: "+ divide(100, 2));
	}
	
	@Step("Step: Divide methods")
	public int divide(int a, int b){
		checkForZero(b);
		return a/b;
	}

	@Step("Checking for Non Zero values {0}")
	public void checkForZero(int b){
		Assert.assertTrue(b != 0);
	}
	
	@Step("{0}")
    private void log123(String value) {
        //empty method
    }
	
	
	
	//-------------------------------Section 2----------------------------------------------//

	
	/**
	 * To attach simple text file with test case
	 * Used when we want to log some requests and responses to some of your servers.
	 * 
	 * This will generate a .txt file who's content will be "I am a log file or exception"
	 * File location: /AllureTestNG/site/allure-maven-plugin/data/<some-random-name>.txt
	 */

	@Attachment(value = "{0}", type = "text/plain")
	public static String saveTextLog(String attachName, String message) {
	    return message;
	}
	
	@Features("Text File Attachment")
	@Test
	public void attachSimpleTextFile(){
		driver.get("https://en.wikipedia.org/wiki/Main_Page");
		saveTextLog("LogFile", "I am a log file or exception");
	}
	
	
	
	//------------------------------Section 3: Image Type 1-----------------------------------------------//
	
	/**
	 * To attach image file with test case
	 * usage: we can take screenshot in catch block and pick file and put in report
	 */
	
	@Attachment(value = "{0}", type = "image/png")
	public static byte[] saveImageAttach(String attachName) {
		
		byte [] res = null;
		
	    try {
	    	BufferedImage image = ImageIO.read(new File(System.getProperty("user.dir")+"/src/test/resources/spicejet.png"));
	    	ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
	    	ImageIO.write(image, "png", baos); 
	    	res=baos.toByteArray();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return res;
	}

	private static byte[] toByteArray(File file) throws IOException {
	    return Files.readAllBytes(Paths.get(file.getPath()));
	}	
	
	
	@Features("Image Attachment Type 1: Github")
	@Test
	public void attachImageFile(){
		driver.get("https://github.com/");
		saveImageAttach("Image attached");
	}
	
	
	//--------------------------------Section 3: Image Type 2---------------------------------------------//

	
	@Attachment(value = "{0}", type = "image/png")
	public static byte[] saveImageAttach2(String attachName) {
	    try {
	        URL defaultImage = TestClass.class.getResource("/bars.png");
	        File imageFile = new File(defaultImage.toURI());
	        return toByteArray2(imageFile);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return new byte[0];
	}

	private static byte[] toByteArray2(File file) throws IOException {
	    return Files.readAllBytes(Paths.get(file.getPath()));
	}
		
	
	@Step("Check calculation result")
	private void checkResult2(int actualResult, int expectedResult) {
	    saveImageAttach2("Image attach");
	    Assert.assertTrue(actualResult == expectedResult, "Actual result(" + actualResult + ") not equals to expected(" + expectedResult + ")");
	}
	
	@Features("Image Attachment Type 2: Abc")
	@Test
	public void testImageAttachmentExample() {
		driver.get("http://abc.go.com/");
	    checkResult2(4 / 2, 2);
	}
	
	
	
	//--------------------------------Section 4: Html attachment---------------------------------------------//
	
	/**
	 * To attach html with test case
	 * 
	 * This will generate .html file 
	 * /AllureTestNG/site/allure-maven-plugin/data/<some-random-name>.html
	 */
	

	@Attachment(value = "{0}", type = "text/html")
	public static byte[] saveHtmlAttach(String attachName) {
	    try {
	        URL defaultImage = TestClass.class.getResource("/htmlreport.html");
	        File imageFile = new File(defaultImage.toURI());
	        return toByteArray(imageFile);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return new byte[0];
	}
	
	@Features("Html Attachment Case")
	@Test
	public void testHtmlAttachmentExample() {
	    checkResultForHtml(4 / 2, 2);
	}

	@Step("Check of calculation")
	private void checkResultForHtml(int actualResult, int expectedResult) {
	    saveHtmlAttach("Html attach");
	    Assert.assertTrue(actualResult == expectedResult,"Actual result(" + actualResult + ") not equals to expected(" + expectedResult + ")");
	}
	

	
}
