package com.example.framework.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.nio.file.Files;
import java.nio.file.Path;

public final class ReportManager {
    private static ExtentReports extentReports;

    private ReportManager() {
    }

    public static synchronized ExtentReports getInstance() {
        if (extentReports == null) {
            try {
                Files.createDirectories(Path.of("reports"));
            } catch (Exception e) {
                throw new IllegalStateException("No se pudo crear la carpeta de reportes", e);
            }

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("reports/extent-report.html");
            sparkReporter.config().setDocumentTitle("Selenium POM Report");
            sparkReporter.config().setReportName("Regression - Login");

            extentReports = new ExtentReports();
            extentReports.attachReporter(sparkReporter);
            extentReports.setSystemInfo("Framework", "Selenium + TestNG + POM");
        }
        return extentReports;
    }
}
