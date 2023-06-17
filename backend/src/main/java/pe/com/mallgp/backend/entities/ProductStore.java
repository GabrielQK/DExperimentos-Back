package pe.com.mallgp.backend.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name="products_stores")
@Data
@NoArgsConstructor
public class ProductStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name="store_id")
    private Store store;

    private Double price;

    private String restock;

    public ProductStore(Product product, Store store, Double price, String restock) {
        this.product = product;
        this.store = store;
        this.price = price;
        this.restock = restock;
    }
}
