package com.example.ParseInput;

public class Refineries {
    public String id;
    public String name;
    public double capacity;
    public double maxOutput;
    public double production;
    public double overflowPenalty;
    public double underflowPenalty;
    public double overOutputPenalty;
    public double productionCost;
    public double productionCo2;
    public double initialStock;
    public String nodeType;

    public Refineries(String id, String name, double capacity, double maxOutput, double production,
                      double overflowPenalty, double underflowPenalty, double overOutputPenalty,
                      double productionCost, double productionCo2, double initialStock, String nodeType) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.maxOutput = maxOutput;
        this.production = production;
        this.overflowPenalty = overflowPenalty;
        this.underflowPenalty = underflowPenalty;
        this.overOutputPenalty = overOutputPenalty;
        this.productionCost = productionCost;
        this.productionCo2 = productionCo2;
        this.initialStock = initialStock;
        this.nodeType = nodeType;
    }

}
