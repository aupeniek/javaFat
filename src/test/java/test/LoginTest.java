package test;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.timeout;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import java.awt.Robot;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.seleniumemulation.WaitForCondition;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import pageobjects.Common;
import pageobjects.FlashButtons;
import util.TestWrapper;

public class LoginTest extends TestWrapper {
	FlashButtons buttons = new FlashButtons(driver);
	
	@BeforeClass
	public static void openDashboard() {
		timeout = BASE_TIMEOUT;
		baseUrl = BASE_URL;
		open(baseUrl);
		driver.manage().window().setPosition(new Point(0, 0));
		driver.manage().window().setSize(new Dimension(1200, 850));
		Common.login();
	}

	@AfterClass
	public static void logout() {
		open(baseUrl + "/logout");
		
	}

	@After
	public void goToDashboard() {
		open(baseUrl);
	}

	@Test
	public void loginAndConfirm() throws Exception {
		// screenshot after login
		makeScreenshot("c:\\tmp\\screenshot1.png");
		
		SelenideElement but = $("#shieldButtonOverlay");
		but.click();
		
		// screenshot - opening flash
		makeScreenshot("c:\\tmp\\screenshot2.png");
		
		Thread.sleep(70000);
		
		// screenshot - check if we're loaded
		makeScreenshot("c:\\tmp\\screenshot3.png");
		
		buttons.confirmLogin();
		
		System.out.println("Title: " + driver.getTitle());
	}
	
	public static void makeScreenshot(String path) {
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void sleep(Long time) {
		((LoginTest) driver).sleep(time);
	}
}
