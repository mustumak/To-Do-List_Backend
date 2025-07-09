package com.example.mustafa.TO_DO_LIST.dto;

import com.example.mustafa.TO_DO_LIST.model.User;
import lombok.Data;

@Data
public class UserInfoDTO {
    private int Id;
    private String username;

    public UserInfoDTO(User user){
        this.Id = user.getId();
        this.username = user.getUsername();
    }
}
