package pe.com.mallgp.backend.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Entity
@Table(name="suggestions")
@Data
@NoArgsConstructor
public class Suggestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String nsugerencia;

    public String contenido;

    public String datesugerencia;

    public Suggestion(String nsugerencia, String contenido, String datesugerencia) {
        this.nsugerencia = nsugerencia;
        this.contenido = contenido;
        this.datesugerencia = datesugerencia;
    }
}
