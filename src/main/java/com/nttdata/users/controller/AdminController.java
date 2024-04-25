package com.nttdata.users.controller;

import com.nttdata.users.dto.AdminDTO;
import com.nttdata.users.entity.AdminEntity;
import com.nttdata.users.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admins")
public class AdminController {

    private final AdminService adminService;

    @PostMapping
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<AdminEntity> createAdministrator(@RequestBody AdminDTO adminDTO) {
        AdminEntity createdAdmin = adminService.createAdministrator(adminDTO);
        return ResponseEntity.ok(createdAdmin);
    }
}
