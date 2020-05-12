package com.mcan.ty.model;

import com.mcan.ty.util.DiscountType;

public abstract class Discount {
    double discountValue;
    DiscountType discountType;

    public Discount(double discountValue, DiscountType discountType) {
        this.discountValue = discountValue;
        this.discountType = discountType;
    }


    public double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(double discountValue) {
        this.discountValue = discountValue;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }
}
