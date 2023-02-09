package ui.tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.qameta.allure.Description;
import ui.pages.FirstClass;

public class FirstTests extends BaseTest {

    private static FirstClass firstClass;

    @BeforeAll
    public static void init() {
        firstClass = new FirstClass();
    }

    @Test
    @Description("Проверка оформления заказа в каталоге")
    @DisplayName("Первый сценарий")
    public void FirstTest() throws Exception {
        firstClass
                .clickCatalogIcon()
                .selectFirstCategory()
                .clickFirstSubCategory()
                .selectFirstItemOfFirstSubCategory()
                .clickToBasketButton()
                .clickAddToBasketButton()
                .clickInBasketButton()
                .clickPlaceAnOrder()
                .contactDataFormDisplaying();
    }
}
