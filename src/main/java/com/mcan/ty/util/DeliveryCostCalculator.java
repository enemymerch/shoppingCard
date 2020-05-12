package com.mcan.ty.util;

public class DeliveryCostCalculator {

    public double calculateCost() {
        return (costPerDelivery * numberOfDelivery ) + (costPerProduct *  numberOfProduct) +  fixedCost;
    }

    public static class Builder {

        double costPerDelivery;
        double costPerProduct;
        double fixedCost;
        int numberOfDelivery;
        int numberOfProduct;

        public Builder(){
        }


        public Builder withNumberOfProducts(int numberOfProduct) {
            this.numberOfProduct = numberOfProduct;
            return this;
        }

        public Builder withNumberofDelivery(int numberOfDelivery){
            this.numberOfDelivery = numberOfDelivery;
            return this;
        }

        public Builder withCostPerDelivery(double costPerDelivery) {
            this.costPerDelivery = costPerDelivery;
            return this;
        }

        public Builder withCostPerProduct(double costPerProduct) {
            this.costPerProduct = costPerProduct;
            return this;
        }

        public Builder withFixedCost(double fixedCost){
            this.fixedCost = fixedCost;
            return this;
        }

        public DeliveryCostCalculator build(){
            DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculator();
            deliveryCostCalculator.costPerDelivery = this.costPerDelivery;
            deliveryCostCalculator.costPerProduct = this.costPerProduct;
            deliveryCostCalculator.fixedCost = this.fixedCost;
            deliveryCostCalculator.numberOfDelivery = this.numberOfDelivery;
            deliveryCostCalculator.numberOfProduct = this.numberOfProduct;
            return  deliveryCostCalculator;
        }
    }

    private double costPerDelivery;
    private double costPerProduct;
    private double fixedCost;
    int numberOfDelivery;
    int numberOfProduct;

    private DeliveryCostCalculator() {
    }
}
