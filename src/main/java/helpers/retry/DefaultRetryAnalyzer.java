package helpers.retry;

import constants.Property;
import helpers.PropertyReader;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class DefaultRetryAnalyzer extends AbstractRetryAnalyzer implements IRetryAnalyzer {

    /**
     * Below method returns 'true' if the test method has to be retried else
     * 'false' and it takes the 'Result' as parameter of the test method that
     * just ran
     *
     * @see org.testng.IRetryAnalyzer#retry(org.testng.ITestResult)
     */
    @Override
    public boolean retry(ITestResult result) {
        String retries = PropertyReader.get(Property.RETRIES_NUM);
        return retryTest(Integer.parseInt(retries), result);
    }
}
