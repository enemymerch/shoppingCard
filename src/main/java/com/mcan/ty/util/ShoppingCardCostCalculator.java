package com.mcan.ty.util;



import com.mcan.ty.model.Campaign;
import com.mcan.ty.model.Category;
import com.mcan.ty.model.Coupon;
import com.mcan.ty.model.Product;

import java.util.HashMap;
import java.util.Set;

public class ShoppingCardCostCalculator {


    public double calculateCost() {
        double cost = 0.0;
        int totalProductCount = 0;
        double rawCost = 0.0;
        // raw cost
        for (Product product : productQuantityMap.keySet()) {
            int quantity = productQuantityMap.get(product);
            rawCost += (product.getPrice()*quantity);
            totalProductCount +=  quantity;
        }

        // campaigns
        campaigns.forEach(campaign -> {
            int quantity = categoryQuantityMap.get(campaign.getCategory());
            if (quantity >= campaign.getMinProductQuantityCount()) {
                // apply campaign
                // TODO :
            }
        });


        return cost;
    }

    private Set<Campaign> campaigns;
    private Coupon coupon;
    private HashMap<Product, Integer> productQuantityMap;
    private final HashMap<Category, Integer> categoryQuantityMap;

    public static class Builder {


        private Set<Campaign> campaigns;
        private Coupon coupon;
        private HashMap<Product, Integer> productQuantityMap;
        private HashMap<Category, Integer> categoryQuantityMap;


        public Builder() {
        }

        public Builder withCampaigns(Set<Campaign> campaigns) {
            this.campaigns = campaigns;
            return this;
        }

        public Builder withCoupon(Coupon coupon){
            this.coupon = coupon;
            return this;
        }

        public Builder withProducts(HashMap<Product, Integer> productQuantityMap){
                this.productQuantityMap = productQuantityMap;
                return this;
        }

        public Builder withCategoryMap(HashMap<Category, Integer> categoryQuantityMap) {
            this.categoryQuantityMap = categoryQuantityMap;
            return this;
        }

        public ShoppingCardCostCalculator build(){
            return new ShoppingCardCostCalculator(this.campaigns, this.coupon, this.productQuantityMap, this.categoryQuantityMap);
        }
    }

    public ShoppingCardCostCalculator(Set<Campaign> campaigns, Coupon coupon, HashMap<Product, Integer> productQuantityMap, HashMap<Category, Integer> categoryQuantityMap) {
        this.campaigns = campaigns;
        this.coupon = coupon;
        this.productQuantityMap = productQuantityMap;
        this.categoryQuantityMap = categoryQuantityMap;
    }

    public Set<Campaign> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(Set<Campaign> campaigns) {
        this.campaigns = campaigns;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }
}
