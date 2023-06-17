package pe.com.mallgp.backend.services;

import pe.com.mallgp.backend.entities.Admin;
import pe.com.mallgp.backend.entities.Mall;

import java.util.List;

public interface MallService {
    public List<Mall> listAll();

    public Mall save(Mall mall);

    public void delete(Long id, int forced);

    public Mall findById(Long id);

    public Mall update(Long id, Mall mall);
}
