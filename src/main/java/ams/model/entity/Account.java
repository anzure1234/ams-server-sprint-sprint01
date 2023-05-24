package ams.model.entity;

import ams.enums.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Account {

    @Id
    private String account;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

}
