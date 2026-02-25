package com.example.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;

public class HabitCardComponent {

    private final Locator root;

    public HabitCardComponent(Locator root) {
        this.root = root;
    }

    public String getName() {
        return root.getByTestId("habit-name").textContent().trim();
    }

    public String getDescription() {
        return root.getByTestId("habit-description").textContent().trim();
    }

    public boolean isCompleted() {
        return "Mark as not done".equals(
                root.getByTestId("habit-toggle-btn").getAttribute("title")
        );
    }

    public void toggle() {
        Locator btn = root.getByTestId("habit-toggle-btn");
        String titleBefore = btn.getAttribute("title");
        btn.click();
        String titleAfter = "Mark as done".equals(titleBefore) ? "Mark as not done" : "Mark as done";
        root.locator("[data-qa='habit-toggle-btn'][title='" + titleAfter + "']")
                .waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void delete() {
        root.getByTestId("habit-delete-btn").click();
        root.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
    }

    public boolean hasStreak() {
        return root.getByTestId("habit-streak").isVisible();
    }

    public String getStreak() {
        return root.getByTestId("habit-streak").textContent().trim();
    }

    public String getCompletions() {
        return root.getByTestId("habit-completions").textContent().trim();
    }

    public boolean isGoalReached() {
        return root.getByTestId("habit-goal-reached").isVisible();
    }

    public boolean hasGoalProgress() {
        return root.getByTestId("habit-goal-progress").isVisible();
    }

    public boolean isVisible() {
        return root.isVisible();
    }
}
