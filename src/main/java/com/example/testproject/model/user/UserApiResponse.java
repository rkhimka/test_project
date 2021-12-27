package com.example.testproject.model.user;

import com.example.testproject.model.common.ApiResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class UserApiResponse extends ApiResponse {
    private ApiResponse data;

    public static ApiResponse buildApiResponseList(List<User> users) {
        return UserApiResponse.builder()
                .data(UsersList.builder()
                        .users(users)
                        .count(users.size())
                        .build())
                .build();
    }

    public static ApiResponse buildApiResponseObject(User user) {
        return UserApiResponse.builder()
                .data(user)
                .build();
    }

}
