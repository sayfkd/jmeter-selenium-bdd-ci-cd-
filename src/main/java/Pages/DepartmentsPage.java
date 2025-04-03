package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DepartmentsPage {
    @FindBy(xpath = "//button[text()='Afficher départements']")
    WebElement showDepartmentsButton;

    @FindBy(xpath = "//div[@class='col']")
    WebElement departmentsTable;

    public DepartmentsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickShowDepartmentsButton() {
        showDepartmentsButton.click();
    }

    public boolean isDepartmentsTableDisplayed() {
        return departmentsTable.isDisplayed();
    }
}
