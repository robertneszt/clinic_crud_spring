package com.phpteam.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Doctor {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String password;

    private Enum role;

}
