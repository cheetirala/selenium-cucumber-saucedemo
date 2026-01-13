package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class InventoryPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(className = "inventory_item")
    private List<WebElement> inventoryItems;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void addHighestPriceItem() {

        double highestPrice = 0.0;
        WebElement highestItem = null;

        for (WebElement item : inventoryItems) {
            WebElement priceElement = item.findElement(By.className("inventory_item_price"));
            double itemPrice = Double.parseDouble(
                    priceElement.getText().replace("$", "").trim()
            );

            if (itemPrice > highestPrice) {
                highestPrice = itemPrice;
                highestItem = item;
            }
        }

        if (highestItem != null) {
            WebElement addCartToButton = highestItem.findElement(By.tagName("button"));
            wait.until(ExpectedConditions.elementToBeClickable(addCartToButton));
            addCartToButton.click();
        }
    }

    public int getCartItemCount() {
        String count = wait.until(ExpectedConditions.visibilityOf(cartBadge)).getText();
        return Integer.parseInt(count);
    }
}
