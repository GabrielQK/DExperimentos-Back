package pe.com.mallgp.backend.servicesimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.com.mallgp.backend.entities.Admin;
import pe.com.mallgp.backend.exceptions.ResourceNotFoundException;
import pe.com.mallgp.backend.repositories.AdminRepository;
import pe.com.mallgp.backend.services.AdminService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository;
    public List<Admin> listAll(){
        List<Admin>admins;
        admins=adminRepository.findAll();
        for(Admin a:admins){
            a.setStoreMall(null);
        }
        return admins;
    }

    @Transactional
    public Admin save(Admin admin){

        Admin newAdmin = adminRepository.save(new Admin(admin.getName(),admin.getPassword()));
        return newAdmin;
    }
    @Transactional
    public void delete(Long id, int forced){
        if (forced==1) {
            Admin foundAdmin = adminRepository.findById(id).
                    orElseThrow(()->new ResourceNotFoundException("Not found New with id="+id));

        }
        adminRepository.deleteById(id);
    }

    public Admin findById(Long id){
        Admin admin=adminRepository.findById(id).get();
        admin.setStoreMall(null);
        return admin;
    }

    @Transactional
    public Admin update(Long id, Admin admin){
        Admin foundAdmin =adminRepository.findById(id).get();
        if(admin.getName()!=null)
            foundAdmin.setName(admin.getName());
        if(admin.getPassword()!=null)
            foundAdmin.setPassword(admin.getPassword());
        Admin updateAdmin = adminRepository.save(foundAdmin);
        updateAdmin.setStoreMall(null);
        return updateAdmin;
    }
}

