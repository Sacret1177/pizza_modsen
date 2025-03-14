package org.example.userservice;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotBlank(message = "Login is required")
    @NotNull(message = "Login can`t be null")
    private String login;
    @NotNull(message = "Password can`t be null")
    @NotBlank(message = "Password is required")
    private String password;
    @NotNull(message = "Email can`t be null")
    @NotBlank(message = "Email is required")
    private String email;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
