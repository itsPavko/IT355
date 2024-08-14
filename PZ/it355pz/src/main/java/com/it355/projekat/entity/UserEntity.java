package com.it355.projekat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "userr")
public class UserEntity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "user_id")
    private Integer id;

    @JoinColumn(name = "role_fk", referencedColumnName = "role_id")
    @ManyToOne
    private Role role;

    @JoinColumn(name = "wallet_fk", referencedColumnName = "wallet_id")
    @ManyToOne
    private Wallet wallet;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

}

