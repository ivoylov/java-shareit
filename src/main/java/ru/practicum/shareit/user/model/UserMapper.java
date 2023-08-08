package ru.practicum.shareit.user.model;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class UserMapper {

    public ShortUserDtoOut toShortUserDtoOut(User user) {
        return ShortUserDtoOut.builder()
                .id(user.getId())
                .build();
    }

    public UserDtoOut toUserDtoOut(User user) {
        return UserDtoOut.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public User toUser(UserDtoIn userDtoIn) {
        return User.builder()
                .name(userDtoIn.getName())
                .email(userDtoIn.getEmail())
                .build();
    }

    public List<UserDtoOut> toUserDtoOutList(List<User> userList) {
        List<UserDtoOut> usersDtoOutList = new ArrayList<>();
        for (User user : userList) {
            usersDtoOutList.add(toUserDtoOut(user));
        }
        return usersDtoOutList;
    }

}
