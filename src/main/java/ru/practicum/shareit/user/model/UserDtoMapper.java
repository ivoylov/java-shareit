package ru.practicum.shareit.user.model;

import lombok.experimental.UtilityClass;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.model.ItemDto;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class UserDtoMapper {

    public UserDto toUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public User toUser(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .build();
    }

    public List<UserDto> toUserDtoList(List<User> userList) {
        List<UserDto> usersDtoList = new ArrayList<>();
        for (User user : userList) {
            usersDtoList.add(toUserDto(user));
        }
        return usersDtoList;
    }

}
