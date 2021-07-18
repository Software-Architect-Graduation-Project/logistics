package com.rbittencourt.pa.logistics.infrastructure.logistics;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "logistics_record")
public class LogisticsRecord {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column
    private long id;

    @Column(name = "order_id")
    private long orderId;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "created_on")
    @CreationTimestamp
    private LocalDateTime createdOn;

    public LogisticsRecord() {
        createdOn = LocalDateTime.now();
    }

    public LogisticsRecord(long orderId, String clientId) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.createdOn = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

}
