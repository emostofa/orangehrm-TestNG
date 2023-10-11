package pages;

import config.EmpModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class DashboardPage {
    @FindBy(className = "oxd-main-menu-item")
    List <WebElement> menuItems;
    @FindBy(className = "oxd-button")
    List <WebElement> buttons;
    @FindBy(className = "oxd-input")
    List <WebElement> inputs;
    @FindBy(className = "oxd-switch-input")
    WebElement tglBtn;
    public static String id;
    public DashboardPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }
    public void pimMenu(){
        menuItems.get(1).click();
    }

    public void createUser(EmpModel model) throws InterruptedException {
        buttons.get(2).click();
        inputs.get(1).sendKeys(model.getFirstName());
        inputs.get(3).sendKeys(model.getLastName());

        tglBtn.click();
        inputs.get(5).sendKeys(model.getUsername());
        inputs.get(6).sendKeys(model.getPassword());
        inputs.get(7).sendKeys(model.getPassword());
        buttons.get(1).click();
        Thread.sleep(4000);
    }
    public void searchUserByID(){
        String uId = inputs.get(4).getText();
        pimMenu();
        inputs.get(1).sendKeys(uId);
        buttons.get(1).click();



    }
    public  String getID(){
        return id;
    }

    public void searchUserByName(){

    }

}
