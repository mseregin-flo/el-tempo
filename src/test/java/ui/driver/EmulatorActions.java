package ui.driver;

import static java.time.Duration.ofMillis;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import io.appium.java_client.TouchAction;

public class EmulatorActions extends EmulatorDriver {

    private WebElement waitForElementPresent (
            By by,
            String errorMessage,
            long timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    public void verticalSwipe
            (
            double startPercentage,
            double anchorPercentage,
            double endPercentage,
            int timeOutMills
            ) {
        Dimension size = driver.manage().window().getSize();
        int x = (int) (size.width * anchorPercentage);
        int start_y = (int) (size.height * startPercentage);
        int end_y = (int) (size.height * endPercentage);
        new TouchAction(driver)
                    .press(point(x, start_y))
                    .waitAction(waitOptions(ofMillis(timeOutMills)))
                    .moveTo(point(x, end_y))
                    .release().perform();
    }
    public void verticalSwipeToFindElement
            (
            double startPercentage,
            double anchorPercentage,
            double endPercentage,
            int timeOutMills,
            SelenideElement element,
            By by,
            String errorMessage,
            int maxSwipes
            ) {
        int alreadySwiped = 0;
        while (!(element.is(Condition.visible))) {
            verticalSwipe(
                    startPercentage,
                    anchorPercentage,
                    endPercentage,
                    timeOutMills
            );
            if (alreadySwiped > maxSwipes) {
                waitForElementPresent(
                        by,
                        "Cannot find element by swiping up. \n" + errorMessage,
                        0);
                return;
            }
        }
    }
}
