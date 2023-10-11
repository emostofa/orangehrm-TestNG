package testRunner;


import config.Setup;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTestRunner extends Setup {

  @Test(priority = 1)
    public void doLogin() {
      LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("admin", "admin123");
    }

    @Test(priority = 2)
    public void doLogout(){
        LoginPage loginPage = new LoginPage(driver);
      loginPage.doLogout();
    }
}
