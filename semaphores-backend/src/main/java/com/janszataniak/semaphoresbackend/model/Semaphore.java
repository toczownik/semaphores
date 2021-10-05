package com.janszataniak.semaphoresbackend.model;

import org.springframework.context.annotation.PropertySource;

import javax.persistence.*;

@Entity
@Table(name = "semaphores")
public class Semaphore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int x;
    private int y;
    private int width;
    private int height;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "warehouse_id")
    Warehouse warehouse;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "forklift_serial_number")
    Forklift forklift;

    public Semaphore() {
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setForklift(Forklift forklift) {
        this.forklift = forklift;
    }

    public int moveAllowed(int x, int y, int serialNumber) {
        if ((x >= this.x && x <= (this.x + width)) && (y >= this.y && y <= (this.y + height))) {
            if (forklift == null) {
                return 1;
            } else if (forklift.getSerialNumber() == serialNumber) {
                return 2;
            } else {
                return 0;
            }
        } else return 2;
    }
}
