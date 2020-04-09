package com.example.restfulwebservice.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {
    @Autowired UserDaoService service;

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    @GetMapping("/users/{uid}")
    public User retrieveUser(@PathVariable int uid){
        User user = service.findOne(uid);
        if(user == null){
            throw new UserNotFoundException(String.format("UID[%s] not fount", uid));
        }
        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = service.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{uid}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/users/{uid}")
    public ResponseEntity<User> updateUserName(@RequestBody User user, @PathVariable int uid){
        user.setId(uid);
        User updatedUser = service.updateUserName(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(updatedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{uid}")
    public void deleteUser(@PathVariable int uid){
        User removedUser = service.deleteById(uid);

        if (removedUser == null){
            throw new UserNotFoundException(String.format("UID[%s] not fount", uid));
        }
    }
}
