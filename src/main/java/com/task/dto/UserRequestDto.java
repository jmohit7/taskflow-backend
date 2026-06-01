package com.task.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    @NotBlank(message = "Name cannot be blank.")
    @Size(message = "Name must have at least 10 characters.", min = 3, max = 25)
    private String name;

    @NotBlank(message = "Email cannot be blank.")
    @Email(message = "Enter a valid email address.")
    @Size(message = "Email length cannot be more than 50 characters.", max = 50)
    private String email;

    @NotBlank(message = "Password cannot be blank.")
    @Size(message = "Password must have at least 8 characters.", min = 8)
    private String password;
}
