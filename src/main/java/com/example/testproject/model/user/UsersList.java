package com.example.testproject.model.user;

import lombok.*;

import javax.validation.Payload;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersList implements Payload {
    private List<User> users;
    private Integer count;
}
