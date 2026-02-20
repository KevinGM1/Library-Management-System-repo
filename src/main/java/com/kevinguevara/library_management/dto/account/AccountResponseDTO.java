package com.kevinguevara.library_management.dto.account;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AccountResponseDTO {
    Long accountId;
    String name;
    String email;
}
