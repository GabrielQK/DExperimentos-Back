package pe.com.mallgp.backend.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "stores_malls")
@Data
@NoArgsConstructor
public class StoreMall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne /*Se incluye el id del Store*/
    @JoinColumn(name="store_id", referencedColumnName = "id")
    private Store store;


    @ManyToOne /*Se incluye el id del Mall*/
    @JoinColumn(name="mall_id", referencedColumnName = "id")
    private Mall mall;

    private Integer capacity;

    @OneToOne
    @JoinColumn(name="admin_id")
    private Admin admin;



    public StoreMall(Store store, Mall mall, Integer capacity, Admin admin) {
        this.store = store;
        this.mall = mall;
        this.capacity = capacity;
        this.admin = admin;
    }
}
