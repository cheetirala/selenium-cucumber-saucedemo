package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.ConfigReader;

public class BrowserManagement {

    protected static WebDriver driver;

    public void openBrowser() {

        String browser = ConfigReader.get("browser").toLowerCase();

        switch (browser) {
            case "chrome":
                driver = createChromeDriver();
                break;

            case "firefox":
                driver = new FirefoxDriver();
                break;

            case "edge":
                driver = new EdgeDriver();
                break;

            default:
                throw new RuntimeException("Unsupported browser: " + browser + ". Use Chrome or Firefox or Edge");
        }

        driver.manage().window().maximize();
        driver.get(ConfigReader.get("base.url"));
    }

    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    private WebDriver createChromeDriver() {
        ChromeOptions options = new ChromeOptions();

        options.addArguments(
                "--disable-notifications",
                "--disable-infobars",
                "--disable-extensions",
                "--disable-save-password-bubble",
                "--disable-features=PasswordLeakDetection,AutofillServerCommunication",
                "--guest"
        );
        return new ChromeDriver(options);
    }
}
