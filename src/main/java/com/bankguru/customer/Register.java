package com.bankguru.customer;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.CommonPages.ManageEnviroment.Environment;
import com.CommonPages.CommonTestcases;
import com.bankguru.HomePage;
import com.bankguru.LoginPage;
import com.bankguru.RegisterPage;

import ObjectPageJson.AbstractObJectJson;

public class Register extends CommonTestcases {
	WebDriver driver;
	LoginPage loginPage;
	RegisterPage registerPage;
	HomePage homePage;
	AbstractObJectJson data;
	String pathData = "/Data/";
	Environment urlEnvironment;
	String userPath = System.getProperty("user.dir");

	String email;
	static String emailLogin, passwordLogin;

	@Parameters({ "browser", "environment", "version", "dataJson" })
	@BeforeClass
	public void beforeClass(String browser, String environment, String version, String dataJson) {

		ConfigFactory.setProperty("env", environment);
		urlEnvironment = ConfigFactory.create(Environment.class);

		String pathDataJson = userPath.concat(pathData).concat(dataJson);
		data = getDataJson(pathDataJson);
		log.info("----------OPEN BROWSER-----------");
		driver = openMultiBrowser(browser, urlEnvironment.url(), version);

		email = "vunguyen" + randomEmail() + "@gmail.com";

		log.info("----------OPEN BROWSER-----------");

	}

	@Test
	public void getAccountRegister() {
		loginPage = PageFactory.initElements(driver, LoginPage.class);
		registerPage = loginPage.clickHereLink();
		registerPage.inputEmail(email);
		registerPage.clickSubmitButton();
		emailLogin = registerPage.getUserIDInfo();
		passwordLogin = registerPage.getPasswordIDInfo();
	}

	@AfterClass
	public void afterClass() {
		closeBrowser();
	}

}
