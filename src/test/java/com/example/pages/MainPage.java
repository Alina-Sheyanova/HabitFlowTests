package com.example.pages;

import com.example.components.AddHabitFormComponent;
import com.example.components.Button;
import com.example.components.HabitCardComponent;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.List;
import java.time.LocalDate;

public class MainPage extends BasePage {

    public final AddHabitFormComponent addHabitForm;

    private final Button addHabitBtn;
    private final Button addFirstHabitBtn;
    private final Button backToTodayBtn;

    public MainPage(Page page) {
        super(page);
        this.addHabitForm = new AddHabitFormComponent(page);
        this.addHabitBtn = new Button("add-habit-btn", page);
        this.addFirstHabitBtn = new Button("add-first-habit-btn", page);
        this.backToTodayBtn = new Button("back-to-today-btn", page);
    }

    public void openAddHabitForm() {
        addHabitBtn.click(false);
    }

    public void openAddHabitFormFromEmptyState() {
        addFirstHabitBtn.click(false);
    }

    public void backToToday() {
        backToTodayBtn.click(false);
    }

    public List<HabitCardComponent> getHabitCards() {
        return page.getByTestId("habit-card").all()
                .stream()
                .map(HabitCardComponent::new)
                .toList();
    }

    public HabitCardComponent getHabitCardByName(String name) {
        Locator card = page.getByTestId("habit-card")
                .filter(new Locator.FilterOptions().setHasText(name))
                .first();
        return new HabitCardComponent(card);
    }

    public int getHabitCount() {
        return page.getByTestId("habit-card").count();
    }

    public void scrollToLastHabitCard() {
        page.getByTestId("habit-card").last().scrollIntoViewIfNeeded();
    }

    public boolean isEmptyStateVisible() {
        return page.getByTestId("empty-state").isVisible();
    }

    public boolean isPastDateBannerVisible() {
        return page.getByTestId("past-date-banner").isVisible();
    }

    public void selectDate(LocalDate date) {
        page.locator("[data-qa='activity-cell'][data-qa-date='" + date + "']").click();
    }

    public void selectYesterday() {
        selectDate(LocalDate.now().minusDays(1));
    }

    public String getAppTitle() {
        return page.getByTestId("app-title").textContent().trim();
    }

    public String getCompletionCount() {
        return page.getByTestId("completion-count").textContent().trim();
    }
}
