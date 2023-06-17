package pe.com.mallgp.backend.services;

import pe.com.mallgp.backend.entities.Admin;
import pe.com.mallgp.backend.entities.New;

import java.util.List;

public interface NewService {
    public List<New> listAll();

    public New save(New news);

    public void delete(Long id, int forced);

    public New findById(Long id);

    public New update(Long id, New news);
}
