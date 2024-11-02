package com.example.ParseInput;

public class Demands {
    public String id;
    public String customerId;
    public double quantity;
    public int postDay;
    public int startDeliveryDay;
    public int endDeliveryDay;

    public Demands(String id, String customerId, double quantity, int postDay, int startDeliveryDay, int endDeliveryDay) {
        this.id = id;
        this.customerId = customerId;
        this.quantity = quantity;
        this.postDay = postDay;
        this.startDeliveryDay = startDeliveryDay;
        this.endDeliveryDay = endDeliveryDay;
    }
}
