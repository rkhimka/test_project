package com.example.testproject.model.user;

import com.example.testproject.entity.UserEntity;
import lombok.*;

import javax.validation.Payload;

@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Payload {
    @EqualsAndHashCode.Exclude
    private Long id;
    private String name;
    private String email;

    public static User toUserModel(UserEntity user) {
        return User.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}