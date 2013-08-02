package async;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

import org.junit.*;

import com.codeborne.selenide.Condition;

public class InstanceMaintenanceTests {
    private static final int LOADING_ANIMATION_TIMEOUT = 20000;
    private static final int HAS_STATE_TIMEOUT = 60000;

    @BeforeClass
    public static void openDashboard() {
	timeout = 10000;
	baseUrl = "http://westx.piranha.ee";
	open("/");
	login();
    }

    @Test
    public void instanceListIsVible() {
	$(byText("All Instances »")).click();
	$(byText("Instances")).shouldBe(visible);
    }

    @Test
    public void stopStart1stServer() {
	sleep(3000);
	toggleMachine(1);
	stopMachine(1);
	machineHasState("Stopped", 1);
	sleep(3000);
	startMachine(1);
	machineHasState("Running", 1);
	toggleMachine(1);
    }

    @Test
    public void stopStart1stServerRestart2ndServer() {
	sleep(3000);
	toggleMachine(1);
	toggleMachine(2);
	restartMachine(2);
	stopMachine(1);
	checkLoadingIcon(2, visible);
	checkLoadingIcon(2, hidden);
	machineHasState("Stopped", 1);
	sleep(3000);
	startMachine(1);
	machineHasState("Running", 1);
	toggleMachine(1);
	toggleMachine(2);
    }

    @Test
    public void stopStartBothServers() {
	toggleMachine(1);
	toggleMachine(2);
	stopMachine(1);
	stopMachine(2);
	machineHasState("Stopped", 1);
	machineHasState("Stopped", 2);
	sleep(3000);
	startMachine(1);
	startMachine(2);
	machineHasState("Running", 1);
	machineHasState("Running", 2);
	toggleMachine(1);
	toggleMachine(2);
    }

    @Test
    public void restartBothServers() {
	sleep(3000);
	toggleMachine(1);
	toggleMachine(2);
	restartMachine(1);
	restartMachine(2);
	checkLoadingIcon(1, visible);
	checkLoadingIcon(1, visible);
	checkLoadingIcon(2, hidden);
	checkLoadingIcon(2, hidden);
	toggleMachine(1);
	toggleMachine(2);
    }

    @AfterClass
    public static void logout() {
	open("/landing/forgetToken");
	closeWebDriver();
    }

    private static void login() {
	$(byAttribute("type", "button")).click();
	$(byAttribute("name", "username")).setValue("testija");
	$(byAttribute("name", "password")).setValue("Ivahk2ov");
	$("#login-submit").click();
    }

    private void machineHasState(String state, int machineNo) {
	$(byAttribute("data-ng-click", "toggleMachine(machine.id)"),
		machineNo - 1).waitUntil(hasText(state), HAS_STATE_TIMEOUT);
    }

    private void startMachine(int machineNo) {
	$(byAttribute("data-ng-click", "startMachine(machine.id)"),
		machineNo - 1).click();
	$(byText("OK")).click();
	checkLoadingIcon(machineNo, visible);
    }

    private void stopMachine(int machineNo) {
	$(byAttribute("data-ng-click", "stopMachine(machine.id)"),
		machineNo - 1).click();
	$(byText("OK")).click();
	checkLoadingIcon(machineNo, visible);
    }

    private void restartMachine(int machineNo) {
	$(byAttribute("data-ng-click", "rebootMachine(machine.id)"),
		machineNo - 1).click();
	$(byText("OK")).click();
    }

    private void checkLoadingIcon(int machineNo, Condition visibility) {
	$((".loading-small"), machineNo - 1).waitUntil(visibility,
		LOADING_ANIMATION_TIMEOUT);
    }

    private void toggleMachine(int machineNo) {
	$(byAttribute("data-ng-click", "toggleMachine(machine.id)"),
		machineNo - 1).click();
    }

}
