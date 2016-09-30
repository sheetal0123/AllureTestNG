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
	

	@Features("Google feature is for BDD reporting")
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
	

	@Features("Yahoo feature is for BDD reporting")
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

	@Features("Bing feature is for BDD reporting")
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


	@Features("Maths feature is for BDD reporting")
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
    private void log(String value) {
        //empty method
    }
	
	
	
	//-----------------------------------------------------------------------------//
	
	/**
	 * To attach simple text file with test case
	 * Used when we want to log some requests and responses to some of your servers.
	 */

	@Attachment(value = "{0}", type = "text/plain")
	public static String saveTextLog(String attachName, String message) {
	    return message;
	}
	
	@Features("Wiki feature for BDD reporting")
	@Test
	public void attachSimpleTextFile(){
		driver.get("https://en.wikipedia.org/wiki/Main_Page");
		saveTextLog("TextException", "I am a log file or exception");
	}
	
	
	
	//-----------------------------------------------------------------------------//
	
	/**
	 * To attach image file with test case
	 * usage: we can take screenshot in catch block and pick file and put in report
	 */
	
	@Attachment(value = "{0}", type = "image/png")
	public static byte[] saveImageAttach(String attachName) {
		
		byte [] res = null;
		
	    try {
	        
	    	BufferedImage image = ImageIO.read(new File(System.getProperty("user.dir")+"/src/test/resources/allure.png"));
	    	ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
	    	ImageIO.write(image, "png", baos); 
	    	res=baos.toByteArray();

//	    	URL defaultImage = TestClass.class.getResource("/allure.png");
//	        File imageFile = new File(defaultImage.toURI());
//	        return toByteArray(imageFile);
//	    	return res;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
//	    return new byte[0];
	    return res;
	}

	private static byte[] toByteArray(File file) throws IOException {
	    return Files.readAllBytes(Paths.get(file.getPath()));
	}	
	
	
	@Features("Github feature for BDD reporting")
	@Test
	public void attachImageFile(){
		driver.get("https://github.com/");
		saveImageAttach("Image attached");
	}
	
	
	//-----------------------------------------------------------------------------//
	
	/**
	 * To attach html with test case
	 */
	
	@Attachment(value = "{0}", type = "text/html")
	public static byte[] saveHtmlAttach(String attachName) {
		
		byte [] res = null;
		
	    try {
	    	BufferedImage htmlFile = ImageIO.read(new File(System.getProperty("user.dir")+"/src/test/resources/test.html"));
	    	ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
	    	ImageIO.write(htmlFile, "html", baos); 
	    	res=baos.toByteArray();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return res;
	}
	
	
	@Features("Allure feature for BDD reporting")
	@Test
	public void attachHtmlFile(){
		driver.get("http://www.allure.com/");
		saveImageAttach("HTML File");
	}
	
	
	//-----------------------------------------------------------------------------//

	
		
}
