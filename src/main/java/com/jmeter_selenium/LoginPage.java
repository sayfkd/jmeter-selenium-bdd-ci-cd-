package com.jmeter_selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id="user-name")
    private  WebElement usernameElement;
    @FindBy(id="password")
    private WebElement passwordElement;
    @FindBy(id="login-button")
    private WebElement btnLoginElement;
    @FindBy(css ="h3[data-test='error']")
    private WebElement errorMessageLogin;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void SaisirUsername(String username){
        usernameElement.sendKeys(username);
    }

    public void SaisirPassword(String password){
        passwordElement.sendKeys(password);
    }

    public void ClickOnLoginButton(){
        btnLoginElement.click();
    }
    public String RecuperationMessageErreurLogin(){
        return errorMessageLogin.getText();
    }

    public void SeConnecterAvecLeUsernameEtPasswordValid(String username, String password){
        SaisirUsername(username);
        SaisirPassword(password);
        ClickOnLoginButton();
        
    }

    
}
