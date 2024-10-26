package com.example.pos.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pos.dto.SaleDto;
import com.example.pos.entity.Item;
import com.example.pos.entity.Sale;
import com.example.pos.repository.ItemRepo;
import com.example.pos.repository.SaleRepo;
import com.example.pos.service.SaleService;

import jakarta.transaction.Transactional;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleRepo saleRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Override
    @Transactional
    public String creatSale(SaleDto saleDto) {
        Sale sale = new Sale();
        sale.setSaleDateTime(LocalDateTime.now());
        sale.setCustomer(saleDto.getCustomer()); // Assuming the customer object is directly passed

        BigDecimal totalAmount = BigDecimal.ZERO;

        // Save the sale before processing items to get the saleId
        saleRepo.save(sale);

        for (SaleDto.ItemDatail itemDetail : saleDto.getItemDatails()) {
            Item item = itemRepo.findById(itemDetail.getItemId())
                .orElseThrow(() -> new RuntimeException("Item not found with ID: " + itemDetail.getItemId()));

            if (item.getQty() < itemDetail.getQty()) {
                throw new RuntimeException("Insufficient quantity for item: " + item.getName());
            }

            item.setQty(item.getQty() - itemDetail.getQty());

            BigDecimal subtotal = item.getUnitPrice().multiply(BigDecimal.valueOf(itemDetail.getQty()));
            totalAmount = totalAmount.add(subtotal);  // Correct BigDecimal addition

            sale.addSaleItem(item, itemDetail.getQty(), subtotal);
        }

        sale.setTotalPrice(totalAmount);
        saleRepo.save(sale);  // Save the sale after adding all items

        return "Sale created successfully with ID " + sale.getSaleId();
    }

    @Override
    public Sale getSaleDetailById(Long saleId) {
        return saleRepo.findById(saleId).orElseThrow(() -> new RuntimeException("Sale not found with ID: " + saleId));
    }

    @Override
    public List<Sale> getAllSales() {
        return saleRepo.findAll();
    }

    @Override
    public void deleteSale(Long saleId) {
        saleRepo.deleteById(saleId);
    }

}
