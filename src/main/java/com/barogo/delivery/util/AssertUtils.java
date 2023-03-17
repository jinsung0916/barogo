package com.barogo.delivery.util;

import org.springframework.util.Assert;

public class AssertUtils {

    public static void AssertNull(Object... array) {
        Assert.noNullElements(array, "Assertion failed.");
    }

}
