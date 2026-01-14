package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BasketPage {
    private final WebDriver driver;

    @FindBy(className = "shopping_cart_link")
    private WebElement BasketElement;

    @FindBy(className = "cart_item")
    private List<WebElement> basketProducts;

    public BasketPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openBasket() {
        BasketElement.click();
    }

    public boolean isProductPresentInTheBasket() {
        return !basketProducts.isEmpty();
    }
}
