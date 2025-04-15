package com.e_commerce.grocery_mart.controller;

import com.e_commerce.grocery_mart.dto.request.RoleCreationRequest;
import com.e_commerce.grocery_mart.dto.response.ApiResponse;
import com.e_commerce.grocery_mart.dto.response.RoleDTO;
import com.e_commerce.grocery_mart.service.AdminService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin")
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AdminController {

    AdminService adminService;

    @GetMapping("/role")
    public ApiResponse<List<RoleDTO>> getAllRole() {
        return ApiResponse.<List<RoleDTO>>builder()
                .result(adminService.getAllRole())
                .build();
    }

    @PostMapping("/role")
    public ApiResponse<Void> addRole(@RequestBody RoleCreationRequest request) {
        adminService.addRole(request);
        return ApiResponse.<Void>builder().build();
    }

    @DeleteMapping("/role/{id}")
    public ApiResponse<Void> deleteRole(@PathVariable(name = "id") int roleId) {
        adminService.deleteRole(roleId);
        return ApiResponse.<Void>builder().build();
    }

}
