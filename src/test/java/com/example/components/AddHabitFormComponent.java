package com.example.components;

import com.microsoft.playwright.Page;

public class AddHabitFormComponent extends BaseComponent {

    public final Button submitBtn;
    public final Button cancelBtn;
    private final Input nameInput;
    private final Input descriptionInput;
    private final Input goalDaysInput;

    public AddHabitFormComponent(Page page) {
        super("add-habit-form", page);
        this.nameInput = new Input("habit-name-input", page);
        this.descriptionInput = new Input("habit-description-input", page);
        this.goalDaysInput = new Input("habit-goal-input", page);
        this.submitBtn = new Button("habit-submit-btn", page);
        this.cancelBtn = new Button("habit-cancel-btn", page);
    }

    public void fillName(String name) {
        nameInput.fill(name);
    }

    public void fillDescription(String description) {
        descriptionInput.fill(description);
    }

    public void fillGoalDays(int days) {
        goalDaysInput.fill(String.valueOf(days));
    }

    public void submit() {
        submitBtn.click(false);
    }

    public void cancel() {
        cancelBtn.click(false);
    }

    public void addHabit(String name) {
        nameInput.fill(name);
        submitBtn.click(false);
    }

    public void addHabit(String name, String description, int goalDays) {
        nameInput.fill(name);
        descriptionInput.fill(description);
        goalDaysInput.fill(String.valueOf(goalDays));
        submitBtn.click(false);
    }

    public String getNameError() {
        return page.getByTestId("habit-name-error").textContent().trim();
    }

    public boolean isNameErrorVisible() {
        return page.getByTestId("habit-name-error").isVisible();
    }
}
