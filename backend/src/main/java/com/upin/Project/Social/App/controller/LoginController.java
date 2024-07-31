package com.upin.Project.Social.App.controller;

import com.nimbusds.jose.JOSEException;
import com.upin.Project.Social.App.dto.request.IntrospectRequest;
import com.upin.Project.Social.App.dto.request.LoginRequest;
import com.upin.Project.Social.App.dto.request.LogoutRequest;
import com.upin.Project.Social.App.dto.response.ApiResponse;
import com.upin.Project.Social.App.dto.response.IntrospectResponse;
import com.upin.Project.Social.App.dto.response.LoginResponse;
import com.upin.Project.Social.App.service.LoginService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class LoginController {
    LoginService loginService;

    @PostMapping("/outbound/authentication")
    ApiResponse<LoginResponse> outboundAuthenticate(
            @RequestParam("code") String code
    ){
        log.info("",code);
        var result = loginService.outboundAuthenticate(code);
        return ApiResponse.<LoginResponse>builder().result(result).build();
    }


    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> login(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        return ApiResponse.<IntrospectResponse>builder()
                .result(loginService.introspect(request))
                .build();
    }
    @PostMapping("/signing")
    public ApiResponse<LoginResponse> login(@RequestBody LoginRequest request){
        return ApiResponse.<LoginResponse>builder()
                .result(loginService.authenticate(request))
                .build();
    }

    @PostMapping("/logout")
    public ApiResponse<Void> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        loginService.logout(request);
        return ApiResponse.<Void>builder()
                .message("logout successfully")
                .build();
    }
}
