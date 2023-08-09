package utils.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import constants.Property;
import helpers.PropertyReader;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import utils.TestContext;

import java.lang.reflect.Method;

public class CustomTestNGListener implements ITestListener {

    private static ExtentReports extent;

    @Override
    public void onTestStart(ITestResult result) {
        String testName = getTestName(result);
        String testParameter = getTestParameter(result);
        ExtentTest test = extent.createTest(testName);
        if (!testParameter.isEmpty()) {
            test.assignCategory(testParameter);
        }
        TestContext.setTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        TestContext.getTest().log(Status.PASS, TestStatusMessage.PASS);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        TestContext.getTest().log(Status.FAIL, result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        TestContext.getTest().log(Status.SKIP, TestStatusMessage.SKIP);
    }

    @Override
    public void onStart(ITestContext context) {
        ExtentSparkReporter reporter =
                new ExtentSparkReporter(PropertyReader.get(Property.EXTENT_REPORT));
        extent = new ExtentReports();
        extent.attachReporter(reporter);
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    private String getTestName(ITestResult result) {
        Method method = result.getMethod().getConstructorOrMethod().getMethod();
        if (method.isAnnotationPresent(Test.class)) {
            Test annotation = method.getAnnotation(Test.class);
            return annotation.testName().isEmpty() ? method.getName() : annotation.testName();
        }
        return method.getName();
    }

    private String getTestParameter(ITestResult result) {
        Object[] parameters = result.getParameters();
        if (parameters.length > 0) {
            // Assuming the parameter you want is at index 0
            return parameters[0].toString();
        }
        return "";
    }
}