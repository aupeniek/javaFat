package pageobjects;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import util.TestWrapper;

public class FlashButtons {
	static WebDriver driv;
	
	public FlashButtons(WebDriver driver) {
		driv = driver;
	}
	
	public static void confirmLogin() throws Exception {
		Actions actor = new Actions(driv);
		actor.moveByOffset(600, 612);
		actor.wait(500);
		actor.click();
		
		actor.wait(2000);
		actor.moveByOffset(600,  590);
		actor.wait(500);
		actor.click();
		
//		Robot robot = new Robot();
//		robot.mouseMove(600, 612);
//		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
//		robot.delay(2000);
//		robot.mouseMove(600, 590);
//		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
//		robot.delay(2000);
		System.out.println("confirmLogin()");
	}
	
	/**
	 * Use Star Menu
	 */
	public static void clickStarmenu() {
		
	}
	
	public void SelectSpecialists() {
		
	}
	
	public void SelectBuffs() {
		
	}
	
	public void SelectResources() {
		
	}
}
