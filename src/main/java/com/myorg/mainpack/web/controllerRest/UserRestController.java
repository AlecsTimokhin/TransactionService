package com.myorg.mainpack.web.controllerRest;

import com.myorg.mainpack.dto.UserDto;
import com.myorg.mainpack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/rest/users")
public class UserRestController {

    private UserService userService;
    public UserService getUserRepository() { return userService; }
    @Autowired
    public void setUserRepository(UserService userService) { this.userService = userService; }


    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UserDto>> getAll(HttpServletRequest request){
        return ResponseEntity.status(200).body( userService.getAll() );
    }

}
