package com.example.framework.tests;

import com.example.framework.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test(description = "Validar login exitoso")
    public void loginExitoso() {
        LoginPage loginPage = new LoginPage();

        loginPage.loginWithCredentials("tomsmith", "SuperSecretPassword!");

        Assert.assertTrue(loginPage.getFlashMessage().contains("You logged into a secure area!"),
                "No se mostro el mensaje de login exitoso");
        Assert.assertTrue(loginPage.isLogoutVisible(), "No se mostro el boton de logout");
    }

    @Test(description = "Validar login con password invalido")
    public void loginInvalido() {
        LoginPage loginPage = new LoginPage();

        loginPage.loginWithCredentials("tomsmith", "invalidPassword");

        Assert.assertTrue(loginPage.getFlashMessage().contains("Your password is invalid!"),
                "No se mostro el mensaje de error esperado");
    }
}
