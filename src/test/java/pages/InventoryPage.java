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
    private List<WebElement> inventoryProducts;

    @FindBy(className = "shopping_cart_badge")
    private WebElement basketBadge;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void addHighestPriceProduct() {

        double highestPrice = 0.0;
        WebElement highestProduct = null;

        for (WebElement product : inventoryProducts) {
            WebElement priceElement = product.findElement(By.className("inventory_item_price"));
            double productPrice = Double.parseDouble(
                    priceElement.getText().replace("$", "").trim()
            );

            if (productPrice > highestPrice) {
                highestPrice = productPrice;
                highestProduct = product;
            }
        }

        if (highestProduct != null) {
            WebElement addToCartButton = highestProduct.findElement(By.tagName("button"));
            wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
        }
    }

    public int getBasketProductCount() {
        String count = wait.until(ExpectedConditions.visibilityOf(basketBadge)).getText();
        return Integer.parseInt(count);
    }

    public boolean isRemoveButtonDisplayed() {
        WebElement removeButton = driver.findElement(By.xpath("//button[text()='Remove']"));
        return wait.until(ExpectedConditions.visibilityOf(removeButton)).isDisplayed();
    }
}
