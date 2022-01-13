package com.example.testproject.model.user;

import com.example.testproject.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    public static User toUserModel(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .build();
    }
}