package com.example.framework.pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    private final By usernameInput = By.id("username");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.cssSelector("button[type='submit']");
    private final By flashMessage = By.id("flash");
    private final By logoutButton = By.cssSelector("a.button.secondary.radius");

    public LoginPage open(String url) {
        driver.get(url);
        return this;
    }

    public LoginPage loginWithCredentials(String username, String password) {
        type(usernameInput, username);
        type(passwordInput, password);
        click(loginButton);
        return this;
    }

    public String getFlashMessage() {
        return getText(flashMessage);
    }

    public boolean isLogoutVisible() {
        return !driver.findElements(logoutButton).isEmpty();
    }
}
