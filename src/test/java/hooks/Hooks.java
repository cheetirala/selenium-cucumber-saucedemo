package hooks;

import base.BrowserManagement;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks extends BrowserManagement {
    @Before
    public void setUp() {
        openBrowser();
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
