package pe.com.mallgp.backend.services;

import pe.com.mallgp.backend.entities.StoreMall;

import java.util.List;

public interface StoreMallService {
    public List<StoreMall> listAll();

    public StoreMall listById(Long id);

    public void delete(Long id);
}

