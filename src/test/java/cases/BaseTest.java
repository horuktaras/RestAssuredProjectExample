package cases;

import constants.Property;
import helpers.PropertyReader;
import helpers.retry.DefaultRetryAnalyzer;
import io.restassured.RestAssured;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import utils.listeners.CustomTestNGListener;

@Listeners(CustomTestNGListener.class)
public class BaseTest {

    @BeforeSuite
    public void setUp(ITestContext context) {
        RestAssured.baseURI = PropertyReader.get(Property.BASE_URL);
        addRetryAnalyzer(context);
    }

    private void addRetryAnalyzer(ITestContext context) {
        for (ITestNGMethod method : context.getAllTestMethods()) {
            method.setRetryAnalyzerClass((DefaultRetryAnalyzer.class));
        }
    }
}