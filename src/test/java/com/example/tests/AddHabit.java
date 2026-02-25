package com.example.tests;

import com.example.BaseTest;
import com.example.pages.AuthPage;
import com.example.pages.MainPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddHabit extends BaseTest {

    private static final String BASE_URL = "http://localhost:5173";

    private MainPage mainPage;

    @BeforeEach
    void setUp() {
        AuthPage authPage = new AuthPage(page);
        authPage.open(BASE_URL);
        authPage.login("123@yandex.ru", "123");
        mainPage = new MainPage(page);
    }

    private String uniqueName(String base) {
        return base + "_" + System.currentTimeMillis();
    }

    @Test
    void addHabitAppearsInList() {
        String name = uniqueName("Тест");

        mainPage.openAddHabitForm();
        mainPage.addHabitForm.addHabit(name);
        mainPage.scrollToLastHabitCard();

        assertEquals(name, mainPage.getHabitCardByName(name).getName());
    }

    @Test
    void addHabitWithDescriptionAndGoal() {
        String name = uniqueName("Read a book");

        mainPage.openAddHabitForm();
        mainPage.addHabitForm.addHabit(name, "30 pages a day", 30);

        assertEquals(name, mainPage.getHabitCardByName(name).getName());
        assertTrue(mainPage.getHabitCardByName(name).hasGoalProgress());
    }

    @Test
    void completeOneHabitToday() {
        String name = uniqueName("Зарядка");

        mainPage.openAddHabitForm();
        mainPage.addHabitForm.addHabit(name);
        mainPage.getHabitCardByName(name).toggle();

        assertTrue(mainPage.getHabitCardByName(name).isCompleted());
    }

    @Test
    void completeOneHabitYesterday() {
        String name = uniqueName("Вчерашняя");

        mainPage.openAddHabitForm();
        mainPage.addHabitForm.addHabit(name);

        mainPage.selectYesterday();
        mainPage.getHabitCardByName(name).toggle();

        assertTrue(mainPage.getHabitCardByName(name).isCompleted());
        assertTrue(mainPage.isPastDateBannerVisible());
    }

    @Test
    void deleteHabitRemovesItFromList() {
        String name = uniqueName("Удалить меня");

        mainPage.openAddHabitForm();
        mainPage.addHabitForm.addHabit(name);
        mainPage.scrollToLastHabitCard();

        int before = mainPage.getHabitCount();
        mainPage.getHabitCardByName(name).delete();

        assertEquals(before - 1, mainPage.getHabitCount());
        assertFalse(mainPage.getHabitCardByName(name).isVisible());
    }
}
