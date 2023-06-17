package pe.com.mallgp.backend.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="offers")
@Data
@NoArgsConstructor
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public String name_product;

    public String gender_product;

    public String price_s_desc;

    public String price_c_desc;
    private String date_on;
    private String date_of;

    public String img;
    @ManyToOne /*(fetch = FetchType.LAZY, optional = false)*/
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne /*(fetch = FetchType.LAZY, optional = false)*/
    @JoinColumn(name = "product_id")
    private Product product;


    public Offer(String name, String name_product, String gender_product, String price_s_desc,
                 String price_c_desc, String date_on, String date_of, String img, Store store,
                 Product product) {
        this.name = name;
        this.name_product = name_product;
        this.gender_product = gender_product;
        this.price_s_desc = price_s_desc;
        this.price_c_desc = price_c_desc;
        this.date_on = date_on;
        this.date_of = date_of;
        this.img = img;
        this.store = store;
        this.product = product;
    }
}
