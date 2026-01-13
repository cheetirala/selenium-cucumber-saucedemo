package steps;

import base.BrowserManagement;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import pages.InventoryPage;
import pages.LoginPage;
import utils.ConfigReader;

public class SauceDemoSteps extends BrowserManagement {

    LoginPage loginPage;
    InventoryPage inventoryPage;

    @Given("the user navigate to SauceDemo")
    public void navigate_to_site() {
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
    }

    @When("the user login with valid credentials")
    public void user_login() {
        loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));
    }

    @When("the user adds the highest priced item to the cart")
    public void add_highest_price_item() {
        inventoryPage.addHighestPriceItem();
    }

    @Then("the item should be added successfully")
    public void item_should_be_added() {
        Assertions.assertEquals(1, inventoryPage.getCartItemCount());
    }
}
