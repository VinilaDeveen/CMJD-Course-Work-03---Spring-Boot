package com.example.pos.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class SaleItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saleItemId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;

    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private Integer qty;
    private BigDecimal subTotal;
}
