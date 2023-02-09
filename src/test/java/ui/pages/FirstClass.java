package ui.pages;

import static com.codeborne.selenide.Selenide.$;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import io.appium.java_client.MobileBy;
import io.qameta.allure.Step;
import ui.driver.EmulatorActions;

public class FirstClass {

    EmulatorActions emulatorActions = new EmulatorActions();

    private SelenideElement catalogMenuIcon = $(MobileBy.xpath("//android.widget.ImageView[@content-desc=\"Catalog, Tab 2 of 5\"]"));
    private SelenideElement firstCategory = $(MobileBy.xpath("//android.view.View[@content-desc=\"Обувь\"]"));
    private SelenideElement firstSubCategory = $(MobileBy.xpath("//*[@content-desc='Балетки']"));
    private SelenideElement firstItemOfFirstSubCategory = $(MobileBy.xpath("//*[@class='android.widget.ImageView' and @index='0']"));
    private SelenideElement toBasketButton = $(MobileBy.xpath("//android.widget.Button[@content-desc=\"To basket\"]"));
    private SelenideElement addToBasketButton = $(MobileBy.xpath("//android.view.View[@content-desc=\"Добавить в корзину\"]"));
    private SelenideElement itemInBasketButton = $(MobileBy.xpath("//android.widget.Button[@content-desc=\"В корзине\"]"));
    private SelenideElement placeAnOrderButton = $(MobileBy.xpath("//android.view.View[@content-desc=\"Оформить заказ\n" +
            "₽2,990\"]"));
    private SelenideElement insertContactDataFormTitle = $(MobileBy.accessibilityId("Введите контактные данные\n" +
            "Поля обязательные для заполнения"));
    @Step("Нажать иконку каталога")
    public FirstClass clickCatalogIcon () {
        catalogMenuIcon.should(Condition.visible).click();
        return this;
    }

    @Step("Выбрать первую категорию товаров")
    public FirstClass selectFirstCategory () {
        if(firstCategory.is(Condition.not(Condition.visible))) {
            clickCatalogIcon();
            firstCategory.should(Condition.visible).click();
        }
        else if(firstCategory.is(Condition.visible)) {
            firstCategory.click();
        }
        return this;
    }

    @Step("Нажать на первую подкатегорию товаров")
    public FirstClass clickFirstSubCategory () {
        firstSubCategory.should(Condition.visible).click();
        return this;
    }

    @Step("Выбрать первый товар первой подкатегории")
    public FirstClass selectFirstItemOfFirstSubCategory () {
        if(firstItemOfFirstSubCategory.is(Condition.not(Condition.visible))) {
            clickCatalogIcon();
            selectFirstCategory();
            clickFirstSubCategory();
            firstItemOfFirstSubCategory.should(Condition.visible).click();
        }
        else if(firstItemOfFirstSubCategory.is(Condition.visible)){
            firstItemOfFirstSubCategory.click();
        }
        return this;
    }

    @Step("Нажать кнопку \"To basket\" на странице товара")
    public FirstClass clickToBasketButton () {
        emulatorActions.verticalSwipeToFindElement
                (
                        0.5,
                        0.5,
                        0.3,
                        200,
                        toBasketButton,
                        By.xpath("//android.widget.Button[@content-desc=\"To basket\"]"),
                        "Button not found",
                        20
                );
        toBasketButton.click();
        return this;
    }

    @Step("Нажать кнопку \"Добавить в корзину\"")
    public FirstClass clickAddToBasketButton () {
        addToBasketButton.should(Condition.visible).click();
        return this;
    }

    @Step("Нажать кнопку \"В корзине\"")
    public FirstClass clickInBasketButton () {
        itemInBasketButton.should(Condition.visible).click();
        return this;
    }

    @Step("Нажать кнопку \"Оформить заказ\"")
    public FirstClass clickPlaceAnOrder () {
        placeAnOrderButton.should(Condition.visible).click();
        return this;
    }

    /**
     * Задержка в 2 секунды, чтобы посмотреть на форму.
     * */
    @Step("Отображение формы \"Введите контактные данные\"")
    public FirstClass contactDataFormDisplaying () throws Exception {
        insertContactDataFormTitle.should(Condition.visible);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }
}
