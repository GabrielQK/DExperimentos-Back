package pe.com.mallgp.backend.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="products")
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;
    public String category;

    public String description;

    public String price;

    public String gender;

    public String img;

    @OneToMany(mappedBy = "product")
    private List<ProductStore>productStores;
    public Product(String name){this.name=name;}

    @OneToMany(mappedBy = "product")
    private List<Offer>offers;

    public Product(String name, String category, String description, String price, String gender, String img) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.gender = gender;
        this.img = img;
    }
}
