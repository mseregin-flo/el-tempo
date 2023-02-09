package ui.tests;

import ui.helper.Constants;
import ui.helper.RunHelper;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static io.qameta.allure.Allure.step;

/**
 * Базовый тестовый класс
 */

// расширяем тестовый класс
public class BaseTest {

    @BeforeAll
    public static void setup() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()); //добавляем логирование действий для аллюр отчета в виде степов
        Configuration.reportsFolder = Constants.SCREENSHOT_TO_SAVE_FOLDER; // папка для сохранения скриншотов selenide
        Configuration.browser = RunHelper.runHelper().getDriverClass().getName(); //инициализируем андройд драйвер
        Configuration.browserSize = null;
        Configuration.timeout = 10000;
    }

    /**
     * Перед каждый тестом открываем приложение
     */
    @BeforeEach
    public void startDriver() {
        step("Открыть приложение", (Allure.ThrowableRunnableVoid) Selenide::open);
    }

    /**
     * После каждого теста закрываем AndroidDriver чтобы тест атомарным был
     */
    @AfterEach
    public void afterEach() {
        step("Закрыть приложение", Selenide::closeWebDriver);
    }
}