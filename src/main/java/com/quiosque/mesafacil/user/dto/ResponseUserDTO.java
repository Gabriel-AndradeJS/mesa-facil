package com.quiosque.mesafacil.user.dto;

import com.quiosque.mesafacil.user.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseUserDTO {

    private Long id;

    private String name;

    private String email;

    private UserRole role;

    private LocalDateTime createdAt;
}
