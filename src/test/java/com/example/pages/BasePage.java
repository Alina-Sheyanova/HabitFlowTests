package com.example.pages;

import com.microsoft.playwright.Page;


public abstract class BasePage {
    protected Page page;

    public BasePage(final Page page) {
        this.page = page;
    }

    public void setAndConfigurePage(final Page page) {
        this.page = page;
    }

    public void initComponents() {
    }

    // Метод перезагрузки без параметров
    public BasePage reloadPage() {
        page.reload();
        page.waitForLoadState();
        return this; // для цепочки вызовов
    }

}
