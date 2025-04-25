package com.e_commerce.grocery_mart.service;

import com.e_commerce.grocery_mart.dto.request.RoleCreationRequest;
import com.e_commerce.grocery_mart.dto.response.RoleDTO;
import com.e_commerce.grocery_mart.entity.Role;

import java.util.List;

public interface AdminService {

    void addRole(RoleCreationRequest request);
    List<RoleDTO> getAllRole();
    void deleteRole(int roleId);
    Role getAdminRole();
    void addFeaturedProduct(int productId);
    void removeFeatureProduct(int productId);
}
