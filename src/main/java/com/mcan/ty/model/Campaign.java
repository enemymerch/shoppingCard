package com.mcan.ty.model;

import com.mcan.ty.util.DiscountType;

public class Campaign extends Discount {
    private Category category;
    int minProductQuantityCount;

    public Campaign(Category category, double discountValue, int minProductQuantityCount, DiscountType discountType) {
        super(discountValue, discountType);
        this.category = category;
        this.minProductQuantityCount = minProductQuantityCount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getMinProductQuantityCount() {
        return minProductQuantityCount;
    }

    public void setMinProductQuantityCount(int minProductQuantityCount) {
        this.minProductQuantityCount = minProductQuantityCount;
    }
}
