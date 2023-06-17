package pe.com.mallgp.backend.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="admins")
@Data
@NoArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;

    public String password;

    @OneToOne(mappedBy = "admin")
    @JoinColumn(name="mall_id")
    private StoreMall storeMall;
    /*public Admin(String name){this.name=name;}*/


    public Admin(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
