package com.example.pages;

import com.example.components.Button;
import com.example.components.Input;
import com.microsoft.playwright.Page;

public class AuthPage extends BasePage {

    private final Input emailInput;
    private final Input passwordInput;
    private final Button submitBtn;
    private final Button toggleModeBtn;

    public AuthPage(Page page) {
        super(page);
        this.emailInput = new Input(page, "input[type='email']");
        this.passwordInput = new Input(page, "input[type='password']");
        this.submitBtn = new Button(page, "button[type='submit']");
        this.toggleModeBtn = new Button(page, "p.text-center button");
    }

    public void open(String url) {
        page.navigate(url);
    }

    public void login(String email, String password) {
        emailInput.fill(email);
        passwordInput.fill(password);
        submitBtn.click(false);
    }

    public void register(String email, String password) {
        toggleModeBtn.click(false);
        emailInput.fill(email);
        passwordInput.fill(password);
        submitBtn.click(false);
    }

    public String getTitle() {
        return page.locator("h2").textContent().trim();
    }

    public String getErrorMessage() {
        return page.locator("p.text-red-500").textContent().trim();
    }

    public boolean isErrorVisible() {
        return page.locator("p.text-red-500").isVisible();
    }
}
