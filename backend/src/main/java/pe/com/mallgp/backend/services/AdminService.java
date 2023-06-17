package pe.com.mallgp.backend.services;

import pe.com.mallgp.backend.entities.Admin;

import java.util.List;

public interface AdminService {
    public List<Admin> listAll();

    public Admin save(Admin admin);

    public void delete(Long id, int forced);

    public Admin findById(Long id);

    public Admin update(Long id, Admin admin);

}
