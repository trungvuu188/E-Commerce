package com.e_commerce.grocery_mart.service.imp;

import com.e_commerce.grocery_mart.constant.PredefinedRole;
import com.e_commerce.grocery_mart.dto.request.RoleCreationRequest;
import com.e_commerce.grocery_mart.dto.response.RoleDTO;
import com.e_commerce.grocery_mart.entity.Role;
import com.e_commerce.grocery_mart.exception.AppException;
import com.e_commerce.grocery_mart.exception.ErrorCode;
import com.e_commerce.grocery_mart.repository.RoleRepository;
import com.e_commerce.grocery_mart.service.AdminService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AdminServiceImp implements AdminService {

    RoleRepository roleRepository;

    @Override
    public void addRole(RoleCreationRequest request) {
        if(roleRepository.existsByRoleName(request.getRoleName().toUpperCase())) {
            throw new AppException(ErrorCode.ROLE_EXISTED_EXCEPTION);
        }
        Role role = Role.builder().roleName(request.getRoleName().toUpperCase()).build();
        roleRepository.save(role);
    }

    @Override
    public List<RoleDTO> getAllRole() {
        List<Role> roles = roleRepository.findAll();
        List<RoleDTO> roleDTOS = new ArrayList<>();
        for (Role role : roles) {
            RoleDTO roleDTO = RoleDTO.builder()
                    .id(role.getId())
                    .roleName(role.getRoleName())
                    .build();
            roleDTOS.add(roleDTO);
        }
        return roleDTOS;
    }

    @Override
    @Transactional
    public void deleteRole(int roleId) {
        if(roleRepository.deleteAndGetCountById(roleId) == 0) {
            throw new AppException(ErrorCode.ROLE_NOTFOUND_EXCEPTION);
        }
    }

    @Override
    public Role getAdminRole() {
        return roleRepository.findByRoleName(PredefinedRole.ADMIN_ROLE)
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOTFOUND_EXCEPTION));
    }
}
