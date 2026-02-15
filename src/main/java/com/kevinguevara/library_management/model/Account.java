package com.kevinguevara.library_management.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(
    name = "accounts"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account{
    @Column(name = "accountId")
    @Id@GeneratedValue
    private Long accountId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;


}