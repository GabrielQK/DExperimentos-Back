package pe.com.mallgp.backend.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="malls")
@Data
@NoArgsConstructor
public class Mall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String name;

    public String direction;
    public String location;

    public String img;

    @OneToMany(mappedBy = "mall")
    private List<StoreMall>storeMalls;

    @OneToMany(mappedBy = "mall")
    private List<New>news;


    public Mall(String name, String location, String direction, String img) {
        this.name = name;
        this.direction = direction;
        this.location = location;
        this.img = img;
    }
}
