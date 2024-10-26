package com.example.pos.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Entity
@Data
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saleId;

    private LocalDateTime saleDateTime;

    @ManyToOne
    @JoinColumn(name = "id")
    private Customer customer;

    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private List<SaleItem> saleItem = new ArrayList<>();


    public void addSaleItem(Item item, int quantity, BigDecimal subtotal) {
        SaleItem saleItem = new SaleItem();
        saleItem.setItem(item);
        saleItem.setQty(quantity);
        saleItem.setSubTotal(subtotal);
        saleItem.setSale(this);
        this.saleItem.add(saleItem);
    }

    @PrePersist
    protected void create() {
        if (this.saleDateTime == null) {
            this.saleDateTime = LocalDateTime.now();
        }
    }
}
