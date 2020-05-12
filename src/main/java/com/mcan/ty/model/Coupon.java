package com.mcan.ty.model;


import com.mcan.ty.util.DiscountType;

public class Coupon extends Discount {
    double minCardCost;

    public Coupon(double minCardCost, double discountValue, DiscountType discountType) {
        super(discountValue, discountType);
        this.minCardCost = minCardCost;
    }

    public double getMinCardCost() {
        return minCardCost;
    }

    public void setMinCardCost(double minCardCost) {
        this.minCardCost = minCardCost;
    }
}
