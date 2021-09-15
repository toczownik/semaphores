package com.janszataniak.semaphoresbackend.model;

import javax.persistence.*;

@Entity
@Table(name = "forklifts")
public class Forklift {
    @Id
    private int serialNumber;
    private int x;
    private int y;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    public Forklift() {}

    public Forklift(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
