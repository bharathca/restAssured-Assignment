package lib.restAssured;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import io.restassured.RestAssured;
import lib.utils.Reporter;

public class PreAndPost extends Reporter{
	public static int userId;
	
	@BeforeSuite
	public void startTheReport() {
		startReport();	
	}
	
	@BeforeClass
	public void startTheTestCase() {
		startTestCase(testCaseName, testDescription);
	}
	
	@BeforeMethod
	public void startTheTestModule() throws FileNotFoundException, IOException {
		svcTest = startTestModule(nodes);
		svcTest.assignAuthor(authors);

		Properties prop = new Properties();
		prop.load(new FileInputStream(new File("./src/test/resources/config.properties")));
		
		RestAssured.baseURI = "https://"+prop.getProperty("server")+"/";
	}
	
	@AfterSuite
	public void stopTheReport() {
		endReport();
	}

}
