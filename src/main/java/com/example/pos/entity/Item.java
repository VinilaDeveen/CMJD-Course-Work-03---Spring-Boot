package com.example.pos.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private Integer qty;

    @Column(nullable = false)
    private BigDecimal unitPrice;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;
}
