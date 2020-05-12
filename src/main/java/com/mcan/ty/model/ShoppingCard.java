/**
 * @author Mümin Can Yılmaz
 */

package com.mcan.ty.model;


import com.mcan.ty.util.DeliveryCostCalculator;
import com.mcan.ty.util.DiscountType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class ShoppingCard {
    private HashMap<Product, Integer> productQuantityMap = new HashMap<>();
    private HashMap<Category, Integer> categoryQuantityMap;
    private double totalAmountAfterDiscounts;
    private double couponDiscount;
    private double campaignDiscount;


    private Coupon appliedCoupon;
    private Set<Campaign> appliedCampaigns;
    private double rawCost;

    public ShoppingCard() {
        this.categoryQuantityMap = new HashMap<>();
        this.appliedCampaigns = new HashSet<>();
        this.totalAmountAfterDiscounts = 0.0;
    }

    public void addItem(Product product, Integer quantity) {
        productQuantityMap.computeIfPresent(product, (k, v) -> v + quantity);
        productQuantityMap.putIfAbsent(product, quantity);
    }

    public void applyDiscounts(Campaign... campaigns) {
        for (Campaign campaign : campaigns) {
            if (!appliedCampaigns.contains(campaign)) {
                appliedCampaigns.add(campaign);
            }
        }
    }

    /**
     *
     * @param coupon tobe applied to shopping card
     */
    public void applyCoupon(Coupon coupon){
        this.appliedCoupon = coupon;
    }

    /**
     *
     * @return numberOfDeliveries : number of categories
     */
    public int getNumberOfDelivery() {
        /**
         * since we can have parent-child relation between categories .
         * if we have a parent and a child category product in our shopping card
         * we should only count the distinct parents !
         */
        Set<Category> categories = new HashSet<>();
        productQuantityMap.keySet().forEach( product -> {
            if (! isCategoryOrParentAlreadyAdded(categories, product)) {
                categories.add(product.getCategory());
            }
        });
        return categories.size();
    }

    /**
     *
     * @param categories
     * @param product
     * @return
     */
    private boolean isCategoryOrParentAlreadyAdded(Set<Category> categories, Product product) {
        for (Category category : categories) {
            if (category.isEqualOrParent(product.getCategory())) {
                return false;
            }
        }
        return true;
    }


    public int getNumberOfProduct() {
        return productQuantityMap.keySet().size();
    }

    /**
     *
     * @return
     */
    public HashMap<Category, Integer> getCategoryQuantityMap(){
        calculateCategoryQuantityMap();
        return this.categoryQuantityMap;
    }

    /**
     *
     * @return categoryQuantityMap <category,quantity> map, product numnber for every category.
     */
    private void calculateCategoryQuantityMap(){
        HashMap<Category, Integer> categoryQuantityMap = new HashMap<>();
        productQuantityMap.forEach((product, quantity) -> {
            categoryQuantityMap.computeIfPresent(product.getCategory(), (k,v) -> v + quantity);
            categoryQuantityMap.putIfAbsent(product.getCategory(), quantity);
        } );
        this.categoryQuantityMap = categoryQuantityMap;
    }

    /**
     * calculates the Total Cost without the discounts
     * and sets the rawCost property of ShoppingCard
     */
    private void calculateRawCost() {
        double rawCost = 0.0;
        for (Product product : productQuantityMap.keySet()) {
            int quantity = productQuantityMap.get(product);
            rawCost += (product.getPrice()*quantity);
        }
        this.rawCost = rawCost;
    }

    /**
     * calculates the Total Amount after Discoutns
     * then sets the totalAmountAfterDiscounts properts
     */
    private void calculateTotalAmountAfterDiscounts() {
        this.totalAmountAfterDiscounts = getRawCost() - getCouponDiscount() -  getCampaignDiscount();
    }

    /**
     *
     * @return rawCost totoal Cost of card without the discounts
     */
    public double getRawCost() {
        calculateRawCost();
        return this.rawCost;
    }

    /**
     *
     * @return totalAmountAfterDiscounts
     */
    public double getTotalAmountAfterDiscounts() {
        calculateTotalAmountAfterDiscounts();
        return this.totalAmountAfterDiscounts;
    }

    /**
     *
     * @return couponDiscount
     */
    public double getCouponDiscount() {
        calculateCouponDiscount();
        return this.couponDiscount;
    }

    /**
     * calculates the coupon discount
     * and sets the couponDiscount property
     */
    private void calculateCouponDiscount() {
        if (appliedCoupon.getMinCardCost() <= getRawCost()) {
            if (appliedCoupon.getDiscountType().equals(DiscountType.Rate)) {
                this.couponDiscount = (getRawCost() / 100.0) * appliedCoupon.getDiscountValue();
            } else if (appliedCoupon.getDiscountType().equals(DiscountType.Amount)){
                this.couponDiscount = appliedCoupon.getDiscountValue();
            }
        }
    }

    /**
     *
     * @return campaignDiscount
     */
    public double getCampaignDiscount() {
        calculateCampaignDiscount();
        return this.campaignDiscount;
    }

    /**
     * calculates campaignDiscount and sets property
     */
    private void calculateCampaignDiscount() {
        double campaignDiscount = 0.0;
        for (Campaign campaign : appliedCampaigns) {
            if (getCategoryQuantityMap().get(campaign.getCategory()) >= campaign.getMinProductQuantityCount()) {
                if (campaign.getDiscountType().equals(DiscountType.Rate)) {
                    for (Product product : productQuantityMap.keySet()) {
                        if (campaign.getCategory().isEqualOrParent(product.getCategory())) {
                            campaignDiscount += (product.getPrice()/100)*campaign.getDiscountValue()*productQuantityMap.get(product);
                        }
                    }
                } else if (campaign.getDiscountType().equals(DiscountType.Amount)) {
                    campaignDiscount = campaign.getDiscountValue();
                }
            }
        }
        this.campaignDiscount = campaignDiscount;
    }

    /**
     *
     * @return deliveryCost
     */
    public double getDeliveryCost() {
        double fixedCost = 2.99;

        DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculator.Builder()
                .withCostPerDelivery(1.25)
                .withCostPerProduct(1.25)
                .withFixedCost(fixedCost)
                .withNumberofDelivery(getNumberOfDelivery())
                .withNumberOfProducts(getNumberOfProduct())
                .build();

        return deliveryCostCalculator.calculateCost();
    }
}
