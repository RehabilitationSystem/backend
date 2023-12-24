package com.example.login_authetic.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PasswordChangeRequest {
    @NotNull
    @Size(min = 8,message = "密码不能少于8个字符")
    @Size(max = 20,message = "密码不能超过20个字符")
    private String oldPassword;
    @NotNull
    @Size(min = 8,message = "密码不能少于8个字符")
    @Size(max = 20,message = "密码不能超过20个字符")
    private String newPassword;

    // getter and setter methods

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}