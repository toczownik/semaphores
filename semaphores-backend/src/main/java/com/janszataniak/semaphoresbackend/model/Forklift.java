package com.janszataniak.semaphoresbackend.model;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "forklift", fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private List<Semaphore> semaphores;

    public Forklift() {}

    public int getSerialNumber() {
        return serialNumber;
    }

    public Warehouse getWarehouse() {
        return warehouse;
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

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public void clearSemaphores() {
        this.semaphores.clear();
    }
}
