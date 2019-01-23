package com.invillia.acme.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "user_security")
@Getter
@Setter
public class UserSecurity {

    @Id
    @NotNull
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", unique = true)
    private String id;
    private String username;
    private String password;

    @Column(unique = true)
    private String email;
    private Boolean enabled;
    @Column(columnDefinition = "DATETIME")
    private LocalDate lastPasswordResetDate;

    @OneToMany(cascade= CascadeType.ALL, fetch= FetchType.EAGER)
    @JoinTable(name="users_roles",
            joinColumns={@JoinColumn(name="user_security_id",
                    referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="role_security_id",
                    referencedColumnName="id")})
    List<RoleSecurity> roleSecurityList;

}