package com.upin.Project.Social.App.service;

import com.upin.Project.Social.App.dto.request.RoleRequest;
import com.upin.Project.Social.App.dto.response.RoleResponse;
import com.upin.Project.Social.App.entity.Role;
import com.upin.Project.Social.App.resitory.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;

    public Role createRole(RoleRequest request){
        Role role = Role.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();

        return roleRepository.save(role);
    }

    public List<Role> getRoles(){
        return roleRepository.findAll();
    }


    public void delete(String id){
        roleRepository.deleteById(id);
    }


}
