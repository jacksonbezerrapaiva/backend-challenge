package com.invillia.acme.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role_security")
@Getter
@Setter
@ToString
public class RoleSecurity {

    @Id
    private String id;
    private String role;
}
