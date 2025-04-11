package com.e_commerce.grocery_mart.entity;

import com.e_commerce.grocery_mart.entity.keys.KeyWarehouseProduct;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity(name = "warehouse_inventory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WarehouseInventory {

    @EmbeddedId
    KeyWarehouseProduct keyWarehouseProduct;

    @ManyToOne
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id", insertable=false, updatable=false)
    Warehouse warehouse;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable=false, updatable=false)
    Product product;

    @ManyToOne
    @JoinColumn(name = "size_id", referencedColumnName = "id", insertable=false, updatable=false)
    Size size;

    @ManyToOne
    @JoinColumn(name = "weight_id", referencedColumnName = "id", insertable=false, updatable=false)
    Weight weight;

    @Column(name = "quantity")
    int quantity;
}
