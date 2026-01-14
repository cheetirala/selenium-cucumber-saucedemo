package steps;

import base.BrowserManagement;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import pages.BasketPage;
import pages.InventoryPage;
import pages.LoginPage;
import utils.ConfigReader;

public class SauceDemoSteps extends BrowserManagement {

    LoginPage loginPage;
    InventoryPage inventoryPage;
    BasketPage basketPage;

    @Given("the user navigate to SauceDemo")
    public void navigate_to_site() {
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        basketPage = new BasketPage(driver);
    }

    @When("the user login with valid credentials")
    public void user_login() {
        loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));
    }

    @Then("the user logged in successfully")
    public void login_success() {
        Assertions.assertTrue(loginPage.isLoginSuccessful(), "User was not logged in successfully");
    }

    @When("the user adds the highest priced product to the basket")
    public void add_highest_price_product_to_basket() {
        inventoryPage.addHighestPriceProduct();
    }

    @Then("the product should be added successfully")
    public void product_should_be_added() {
        Assertions.assertEquals(1, inventoryPage.getBasketProductCount());
        Assertions.assertTrue(inventoryPage.isRemoveButtonDisplayed());
    }

    @When("the user navigate to basket page")
    public void user_navigate_to_basket_page() {
        basketPage.openBasket();
    }

    @Then("the user can see added product in the basket")
    public void user_can_see_product_added() {
        Assertions.assertTrue(basketPage.isProductPresentInTheBasket());
    }
}
