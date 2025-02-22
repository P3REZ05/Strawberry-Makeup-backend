package com.catalogo.CatalogoDigital.controller;

import com.catalogo.CatalogoDigital.model.Admin;
import com.catalogo.CatalogoDigital.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Map<String, Object>> validateAdmin(@RequestBody Admin admin) {
        Map<String, Object> response = new HashMap<>();

        boolean isValid = adminService.validateAdmin(admin.getNombre(), admin.getContraseña());

        if (isValid) {
            response.put("valid", true);
            response.put("token", "admin-token-" + System.currentTimeMillis());
            return ResponseEntity.ok(response);
        } else {
            response.put("valid", false);
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("/test")
    public ResponseEntity<List<Admin>> getAdmins() {
        List<Admin> admins = adminService.getAdmins();
        return ResponseEntity.ok(admins);
    }
}