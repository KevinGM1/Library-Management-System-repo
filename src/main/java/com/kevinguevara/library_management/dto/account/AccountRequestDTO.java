package com.kevinguevara.library_management.dto.account;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequestDTO {
    @NotBlank(message = "name cannot be empty")
    private String name;
    @NotBlank(message = "email is required")
    private String email;
}
