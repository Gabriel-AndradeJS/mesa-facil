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
public class WaiterDTO {

    public WaiterDTO(Long id, String name, Long userId, Long adminId) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.adminId = adminId;
    }

    private Long id;

    private String name;

    private String email;

    private UserRole role;

    private Long userId;

    private Long adminId;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    private boolean enabled;

    private AuthoritiesDTO authorities;

    private LocalDateTime createdAt;


    public String toString() {
        return "WaiterDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", userId=" + userId +
                ", adminId=" + adminId +
                ", accountNonExpired=" + accountNonExpired +
                ", accountNonLocked=" + accountNonLocked +
                ", credentialsNonExpired=" + credentialsNonExpired +
                ", enabled=" + enabled +
                ", authorities=" + authorities +
                ", createdAt=" + createdAt +
                '}';
    }

}
