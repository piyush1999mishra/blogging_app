package com.example.bloggingApp.payload;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private int id;
    @Email
    private String email;
    @NotEmpty
    private String name;
    @NotEmpty
    //@Min(value = 4,message = "Must be greater than equal to 4 characters")
    //@Max(value = 10,message = "Must be less than equal to 10 characters")
    private String password;
    private String about;
}
