package com.example.ParseInput;

public class Customers {
    public String id;
    public String name;
    public double maxInput;
    public double overInputPenalty;
    public double lateDeliveryPenalty;
    public double earlyDeliveryPenalty;
    public String nodeType;

    public Customers (String id, String name, double maxInput, double overInputPenalty, double lateDeliveryPenalty, double earlyDeliveryPenalty, String nodeType) {
        this.id = id;
        this.name = name;
        this.maxInput = maxInput;
        this.overInputPenalty = overInputPenalty;
        this.lateDeliveryPenalty = lateDeliveryPenalty;
        this.earlyDeliveryPenalty = earlyDeliveryPenalty;
        this.nodeType = nodeType;
    }
}
