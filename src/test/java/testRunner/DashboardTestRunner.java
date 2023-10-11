package testRunner;

import com.github.javafaker.Faker;
import config.EmpModel;
import config.Setup;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import utils.Utils;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DashboardTestRunner extends Setup {

@Test(priority = 1)
    public void createUser() throws InterruptedException, IOException, ParseException {
        LoginPage login = new LoginPage(driver);
        login.doLogin("admin","admin123");
        DashboardPage dp = new DashboardPage(driver);
        dp.pimMenu();
        Faker faker=new Faker();
        String firstName=faker.name().firstName();
        String lastName=faker.name().lastName();
        String username=faker.name().username();
        String employeeID = dp.getID();
        String password = generateRandomPassword(12);

        EmpModel model=new EmpModel();
        model.setFirstName(firstName);
        model.setLastName(lastName);
        model.setEmployeeID(employeeID);
        model.setUsername(username);
        model.setPassword(password);
        dp.createUser(model);

    String textTitleExpected= driver.findElement(By.xpath("//*[contains(text(),\"Personal Details\")]")).getText();

    Thread.sleep(5000);
    if(textTitleExpected.contains("Personal Details")){
        Utils.saveEmployeeInfo(model);
    }

    }

    @Test(priority = 2)
    public void searchUserById(){
        DashboardPage dp = new DashboardPage(driver);
        dp.searchUserByID();
    }









    public static String generateRandomPassword(int length) {
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&*()_+!]).{" + length + "}$";
        SecureRandom random = new SecureRandom();

        while (true) {
            StringBuilder password = new StringBuilder();

            for (int i = 0; i < length; i++) {
                int randomIndex = random.nextInt(94) + 33; // ASCII characters between 33 and 126
                char randomChar = (char) randomIndex;
                password.append(randomChar);
            }

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(password.toString());
            if (matcher.matches()) {
                return password.toString();
            }
        }
    }
    public static String generateID(){
        SecureRandom random = new SecureRandom();
        int randomInteger = random.nextInt(9000) + 1000;
        return String.valueOf(randomInteger);
    }
}
