package com.example.testproject.model.user;

import com.example.testproject.entity.UserEntity;
import com.example.testproject.model.common.ApiResponse;
import lombok.*;

@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends ApiResponse {
    @EqualsAndHashCode.Exclude
    private Long id;
    private String name;
    private String email;

    public static User toModel(UserEntity user) {
        return User.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}