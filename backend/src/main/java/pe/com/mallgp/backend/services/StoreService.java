package pe.com.mallgp.backend.services;

import pe.com.mallgp.backend.entities.Admin;
import pe.com.mallgp.backend.entities.Store;

import java.util.List;

public interface StoreService {

    public List<Store> listAll();

    public Store save(Store store);

    public void delete(Long id, int forced);

    public Store findById(Long id);

    public Store update(Long id, Store store);

    public List<Store> findByCategory(String category);

    public Store findByName(String name);
}
