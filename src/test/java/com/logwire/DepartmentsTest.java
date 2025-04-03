package com.logwire;

import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Pages.DepartmentsPage;

@Tag("DepartmentTests")
public class DepartmentsTest {
    WebDriver driver;
    DepartmentsPage departmentsPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        departmentsPage = new DepartmentsPage(driver);

        driver.get("http://192.168.1.95:3000");
        
    }

    @Test
    public void testDepartmentsTableDisplayed() {
        departmentsPage.clickShowDepartmentsButton();
        
        assertTrue(departmentsPage.isDepartmentsTableDisplayed(), "Le tableau des départements doit être affiché.");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
            driver = null;
        }
    }
}
