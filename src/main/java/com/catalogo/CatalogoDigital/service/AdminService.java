package com.catalogo.CatalogoDigital.service;

import com.catalogo.CatalogoDigital.model.Admin;
import com.catalogo.CatalogoDigital.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public void addAdmin(Admin admin) {
        adminRepository.addAdmin(admin);
    }

    public boolean validateAdmin(String nombre, String contraseña) {
        Optional<Admin> admin = adminRepository.findByNombre(nombre);
        return admin.isPresent() && admin.get().getContraseña().equals(contraseña);
    }

    public List<Admin> getAdmins() {
        return adminRepository.getAdmins();
    }
}