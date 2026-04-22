package com.example.framework.core;

import com.example.framework.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public final class DriverFactory {
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverFactory() {
    }

    public static void initDriver() {
        String browser = ConfigReader.get("browser");
        boolean headless = Boolean.parseBoolean(ConfigReader.get("headless"));

        WebDriver webDriver = switch (browser == null ? "chrome" : browser.toLowerCase()) {
            case "firefox" -> createFirefox(headless);
            case "edge" -> createEdge(headless);
            default -> createChrome(headless);
        };

        int implicitWait = ConfigReader.getInt("implicit.wait.seconds", 5);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        webDriver.manage().window().maximize();

        DRIVER.set(webDriver);
    }

    public static WebDriver getDriver() {
        WebDriver webDriver = DRIVER.get();
        if (webDriver == null) {
            throw new IllegalStateException("WebDriver no esta inicializado para este hilo.");
        }
        return webDriver;
    }

    public static void quitDriver() {
        WebDriver webDriver = DRIVER.get();
        if (webDriver != null) {
            webDriver.quit();
            DRIVER.remove();
        }
    }

    private static WebDriver createChrome(boolean headless) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        if (headless) {
            options.addArguments("--headless=new");
        }
        return new ChromeDriver(options);
    }

    private static WebDriver createFirefox(boolean headless) {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        if (headless) {
            options.addArguments("-headless");
        }
        return new FirefoxDriver(options);
    }

    private static WebDriver createEdge(boolean headless) {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        if (headless) {
            options.addArguments("--headless=new");
        }
        return new EdgeDriver(options);
    }
}
