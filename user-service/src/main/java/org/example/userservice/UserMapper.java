package org.example.userservice;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    public UserDto entityToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getPassword());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    public User dtoToEntity(UserDto dto, Long id) {
        User user = new User();
        user.setId(id);
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        return user;
    }

    public List<UserDto> mapList(List<User> users){
        List<UserDto> userDtos = new ArrayList<UserDto>();
        for (User user : users) {
            userDtos.add(entityToDto(user));
        }
        return userDtos;
    }
}
