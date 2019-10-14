package ecommerce.controller;

import ecommerce.domain.dto.UserDto;
import ecommerce.maper.UserMapper;
import ecommerce.service.UserDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@RestController
@RequestMapping("v1/user")
public class UserController {

    @Autowired
    private UserDBService userDBService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping(value = "getUsers")
    public List<UserDto> getUsers() {
        return userMapper.mapToUserDtoList(userDBService.getAllUsers());
    }

    @GetMapping (value = "getUser")
    public UserDto getUser( @RequestParam("userId") Long userId ) {
        return new UserDto(1L, "user1", false);
    }

    @PostMapping(value = "createUser")
    public void createUser(@RequestBody UserDto userDto){
        userDBService.saveUser(userMapper.mapToUser(userDto));
    }

    @PutMapping( value = "blockUser")
    public UserDto blockUser( @RequestParam Long userId, @RequestBody UserDto userDto) {
        return new UserDto(1L, "user2", true);
    }

    @GetMapping (value = "generateUserIdKey")
    public Long generateUserIdKey(@RequestParam Long userId) {
        Long randomKey = new Random().nextLong();
        return userId + randomKey;
    }
}
