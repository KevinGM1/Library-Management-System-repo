package com.kevinguevara.library_management.dto.account;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AccountRequestDTO {
    @NotBlank(message = "name cannot be empty")
    private String name;
    @NotBlank(message = "email is required")
    private String email;
}
