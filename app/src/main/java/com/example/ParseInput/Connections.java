package com.example.ParseInput;

public class Connections {
    public String id;
    public String fromId;
    public String toId;
    public double distance;
    public double leadTime;
    public String connectionType;
    public double maxCapacity;

    public Connections (String id, String fromId, String toId, double distance, double leadTime, String connectionType, double maxCapacity) {
        this.id = id;
        this.fromId = fromId;
        this.toId = toId;
        this.distance = distance;
        this.leadTime = leadTime;
        this.connectionType = connectionType;
        this.maxCapacity = maxCapacity;
    }
}
