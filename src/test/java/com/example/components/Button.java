package com.example.components;

import com.microsoft.playwright.Page;

public class Button extends BaseComponent {

    public Button(final Page page, String selector) {
        super(page, selector);
    }

    public Button(String testDataAttr, final Page page) {
        super(testDataAttr, page);
    }

}
