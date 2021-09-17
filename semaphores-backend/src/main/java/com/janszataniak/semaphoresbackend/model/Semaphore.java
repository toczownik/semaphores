package com.janszataniak.semaphoresbackend.model;

import javax.persistence.*;

@Entity
@Table(name = "semaphores")
public class Semaphore {
    @Id
    private int id;
    private int x;
    private int y;
    private int width;
    private int height;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "warehouse_id")
    Warehouse warehouse;

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}
