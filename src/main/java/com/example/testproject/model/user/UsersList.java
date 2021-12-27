package com.example.testproject.model.user;

import com.example.testproject.model.common.ApiResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersList extends ApiResponse {
    private List<User> users;
    private Integer count;
}
