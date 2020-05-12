package com.mcan.ty.util;

public enum DiscountType {
    Rate(1), Amount(2);

    private final int value;

    DiscountType(int value) {
        this.value = value;
    }
}
