package com.example.components;

import com.microsoft.playwright.Page;

public class Input extends BaseComponent {

    public Input(String testDataAttr, Page page) {
        super(testDataAttr, page);
    }

    public Input(Page page, String selector) {
        super(page, selector);
    }

    public void fill(String value) {
        getLocator().fill(value);
    }

    public void clear() {
        getLocator().clear();
    }

    public String getValue() {
        return getLocator().inputValue();
    }
}
