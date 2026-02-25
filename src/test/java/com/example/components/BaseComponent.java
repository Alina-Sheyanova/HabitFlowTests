package com.example.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public abstract class BaseComponent {
    protected Page page;
    protected String selector;
    protected String testDataAttr;
    protected Locator locator;

    protected BaseComponent(final Page page, String selector) {
        this.page = page;
        this.selector = selector;
    }

    protected BaseComponent(String testDataAttr, final Page page) {
        this.page = page;
        this.testDataAttr = testDataAttr;
    }

    protected BaseComponent(final Page page, Locator locator) {
        this.page = page;
        this.locator = locator;
    }

    public void click(boolean waitForNavigation) {
        if (waitForNavigation)
            page.waitForNavigation(() -> getLocator().click());
        else getLocator().click();
    }

    public String getText() {
        return getLocator().textContent();
    }

    public String getAttribute(String attribute) {
        return getLocator().getAttribute(attribute);
    }

    public String getInputValue() {
        return getLocator().inputValue();
    }

    public boolean isEnabled() {
        return getLocator().isEnabled();
    }

    public boolean isDisabled() {
        return getLocator().isDisabled();
    }

    public boolean isEditable() {
        return getLocator().isEditable();
    }

    public boolean isVisible() {
        return getLocator().isVisible();
    }

    public boolean isHidden() {
        return getLocator().isHidden();
    }

    public void scrollIntoView() {
        getLocator().scrollIntoViewIfNeeded();
    }

    protected Locator getLocator() {
        if (testDataAttr != null) {
            return page.getByTestId(testDataAttr).first();
        }
        return page.locator(selector).first();
    }
}