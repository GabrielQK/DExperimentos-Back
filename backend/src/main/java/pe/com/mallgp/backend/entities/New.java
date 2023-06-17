package pe.com.mallgp.backend.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="news")
@Data
@NoArgsConstructor
public class New {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private String date_on;
    private String date_of;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "mall_id")
    private Mall mall;


    public New(String text, String date_on, String date_of, Mall mall) {
        this.text = text;
        this.date_on = date_on;
        this.date_of = date_of;
        this.mall = mall;
    }
}
