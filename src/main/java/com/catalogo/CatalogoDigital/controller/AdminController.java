package com.catalogo.CatalogoDigital.controller;

import com.catalogo.CatalogoDigital.model.Admin;
import com.catalogo.CatalogoDigital.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/add")
    public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin) {
        adminService.addAdmin(admin);
        return ResponseEntity.ok(admin);
    }

    @PostMapping("/validate")
    public ResponseEntity<Boolean> validateAdmin(@RequestBody Admin admin) {
        boolean isValid = adminService.validateAdmin(admin.getNombre(), admin.getContraseña());
        return ResponseEntity.ok(isValid);
    }

    @GetMapping("/test")
    public ResponseEntity<List<Admin>> getAdmins() {
        List<Admin> admins = adminService.getAdmins();
        return ResponseEntity.ok(admins);
    }
}