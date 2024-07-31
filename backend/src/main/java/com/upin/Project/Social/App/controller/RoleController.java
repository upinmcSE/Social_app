package com.upin.Project.Social.App.controller;

import com.upin.Project.Social.App.dto.request.RoleRequest;
import com.upin.Project.Social.App.dto.response.ApiResponse;
import com.upin.Project.Social.App.entity.Role;
import com.upin.Project.Social.App.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class RoleController {
    RoleService roleService;

    @GetMapping("")
    public ApiResponse<List<Role>> getRoles(){
        return ApiResponse.<List<Role>>builder()
                .message("List Roles")
                .result(roleService.getRoles())
                .build();
    }

    @PostMapping("/add")
    public ApiResponse<Role> createRole(@RequestBody RoleRequest request){
        return ApiResponse.<Role>builder()
                .message("add role success")
                .result(roleService.createRole(request))
                .build();
    }

    @DeleteMapping("/delete/{id}")
    ApiResponse<Void> delete(@PathVariable String id) {
        roleService.delete(id);
        return ApiResponse.<Void>builder().build();
    }

}
