package com.example.ParseInput;

public class Tanks {
    public String id;
    public String name;
    public double capacity;
    public double maxInput;
    public double maxOutput;
    public double overflowPenalty;
    public double underflowPenalty;
    public double overInputPenalty;
    public double overOutputPenalty;
    public double initialStock;
    public String nodeType;

    public Tanks(String id, String name, double capacity, double maxInput, double maxOutput,
                 double overflowPenalty, double underflowPenalty, double overInputPenalty,
                 double overOutputPenalty, double initialStock, String nodeType) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.maxInput = maxInput;
        this.maxOutput = maxOutput;
        this.overflowPenalty = overflowPenalty;
        this.underflowPenalty = underflowPenalty;
        this.overInputPenalty = overInputPenalty;
        this.overOutputPenalty = overOutputPenalty;
        this.initialStock = initialStock;
        this.nodeType = nodeType;
    }
}

