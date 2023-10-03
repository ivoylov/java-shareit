package ru.practicum.shareit.user.model;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public static UserDtoOutShort toShortUserDtoOut(User user) {
        return UserDtoOutShort.builder()
                .id(user.getId())
                .build();
    }

    public static UserDtoOut toUserDtoOut(User user) {
        return UserDtoOut.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public static User toUser(UserDtoIn userDtoIn) {
        return User.builder()
                .name(userDtoIn.getName())
                .email(userDtoIn.getEmail())
                .build();
    }

    public static List<UserDtoOut> toUserDtoOutList(List<User> userList) {
        List<UserDtoOut> usersDtoOutList = new ArrayList<>();
        for (User user : userList) {
            usersDtoOutList.add(toUserDtoOut(user));
        }
        return usersDtoOutList;
    }

}
