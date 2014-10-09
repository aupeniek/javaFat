package util;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.codeborne.selenide.WebDriverProvider;

@SuppressWarnings("deprecation")
public class LocalFirefox implements WebDriverProvider {
	@Override
	public WebDriver createDriver(DesiredCapabilities capabillities) {
		FirefoxProfile profile = new FirefoxProfile(
				new File(
						"/home/aupeniek/.mozilla/firefox/jpafxfwh.default/"));
		new DesiredCapabilities();
		
		capabillities = DesiredCapabilities.firefox();
		capabillities.setCapability(FirefoxDriver.PROFILE, profile);
		return new FirefoxDriver(profile);
	}
}