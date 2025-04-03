package com.jmeter_selenium;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("LoginTest")
public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeEach
    public void setup() {
        super.setup();  // Appel de la méthode setup() de BaseTest
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
    }

    @AfterEach
    public void tearDown() {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            // Gérer l'exception si nécessaire
        }
        super.tearDown();  // Appel de la méthode tearDown() de BaseTest
    }

    @Test
    @Tag("TC-LoginValid")
    public void SeConnecterAvecLeBonUsernameEtPassword() {
        loginPage.SaisirUsername("standard_user");
        loginPage.SaisirPassword("secret_sauce");
        loginPage.ClickOnLoginButton();
        assertTrue(driver.getCurrentUrl().contains("/inventory.html"), "Echec de connexion : URL actuelle = " + driver.getCurrentUrl());
    }

    @Test
    @Tag("TC-LoginInvalid")
    public void EchecDeConnexionMotDePasseIncorrect() {
        loginPage.SaisirUsername("standard_user");
        loginPage.SaisirPassword("sxcvcxdsfsdfsf");
        loginPage.ClickOnLoginButton();
        assertTrue(loginPage.RecuperationMessageErreurLogin().equals("Epic sadface: Username and password do not match any user in this service"));
    }
}