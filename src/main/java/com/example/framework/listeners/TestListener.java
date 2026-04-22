package com.example.framework.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.example.framework.utils.ReportManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private static final ThreadLocal<ExtentTest> TEST_LOG = new ThreadLocal<>();
    private ExtentReports extent;

    @Override
    public void onStart(ITestContext context) {
        extent = ReportManager.getInstance();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        TEST_LOG.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        TEST_LOG.get().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        TEST_LOG.get().fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        TEST_LOG.get().skip("Test skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
        }
    }
}
