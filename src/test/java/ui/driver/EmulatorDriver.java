package ui.driver;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import ui.config.ConfigReader;
import ui.helper.ApkInfoHelper;
import javax.annotation.Nonnull;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmulatorDriver implements WebDriverProvider {

    protected static AndroidDriver driver;
    private static final String DEVICE_NAME = ConfigReader.emulatorConfig.deviceName();
    private static final String PLATFORM_NAME = ConfigReader.emulatorConfig.platformName();
    private static String APP_PACKAGE = ConfigReader.emulatorConfig.appPackage();
    private static String APP_ACTIVITY = ConfigReader.emulatorConfig.appActivity();
    private static final String APP = ConfigReader.emulatorConfig.app();
    private static final String URL = ConfigReader.emulatorConfig.remoteURL();

    /**
     * Валидация URL ссылки из пропертей
     * @return
     */
    public static java.net.URL getUrl() {
        try {
            return new URL(URL);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Получаем абсолютный путь от рутового пути
     * @param filePath путь к файлу из корня проекта
     * @return
     */
    private String getAbsolutePath(String filePath) {
        File file = new File(filePath);
        assertTrue(file.exists(), filePath + " not found"); // проверяем что файл существует
        return file.getAbsolutePath();
    }

    /**
     * Получаем AppPackage и AppActivity из чтения apk файла
     */
    private void initPackageAndActivity() {
        ApkInfoHelper helper = new ApkInfoHelper();
        //тернарное условие, если app_package не задано в пропертях, достаем из из apk
        APP_PACKAGE = APP_PACKAGE.isEmpty() ? helper.getAppPackageFromApk() : APP_PACKAGE;
        APP_ACTIVITY = APP_ACTIVITY.isEmpty() ? helper.getAppMainActivity() : APP_ACTIVITY;
    }

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);
        options.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS,true);
        options.setCapability("deviceName", DEVICE_NAME);
        options.setCapability("platformName", PLATFORM_NAME);
        options.setCapability("appPackage", APP_PACKAGE);
        options.setCapability("appActivity", APP_ACTIVITY);
        options.disableWindowAnimation();
        options.setNewCommandTimeout(Duration.ofSeconds(2));
        options.setCapability("app", getAbsolutePath(APP));
        driver = new AndroidDriver(getUrl(), options);
        return driver;
    }
}
