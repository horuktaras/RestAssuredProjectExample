package helpers.retry;

import org.testng.ITestResult;

public class AbstractRetryAnalyzer {

    private final ThreadLocal<Integer> count = ThreadLocal.withInitial(() -> 0);

    public synchronized boolean retryTest(int maxTry, ITestResult result) {
        if (!result.isSuccess()) {
            if (count.get() < maxTry) {
                count.set(count.get() + 1);
                result.setStatus(ITestResult.FAILURE);
                return true;
            } else {
                result.setStatus(ITestResult.FAILURE);
                count.set(0);
            }
        } else {
            result.setStatus(ITestResult.SUCCESS);
        }
        return false;
    }
}