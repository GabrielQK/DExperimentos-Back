package pe.com.mallgp.backend.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="stores")
@Data
@NoArgsConstructor
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;

    public String category;

    public String horario;

    public String ubicacion;

    public String img;

    @OneToMany(mappedBy = "store")
    private List<StoreMall> storeMalls;



    @OneToMany(mappedBy = "store")
    private List<ProductStore> productStores;


    @OneToMany(mappedBy = "store")
    private List<Offer>offers;


    public Store(String name, String category, String horario, String ubicacion, String img) {
        this.name = name;
        this.category = category;
        this.horario = horario;
        this.ubicacion = ubicacion;
        this.img = img;
    }
}
