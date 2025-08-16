package com.quyentv.bookstorebackend.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationRequest {

    @NotNull(message = "USERNAME_IS_REQUIRED")
    @Size(min = 4, message = "INVALID_USERNAME")
    String username;

    @NotNull(message = "PASSWORD_IS_REQUIRED")
    @Size(min = 6, message = "INVALID_PASSWORD")
    String password;
}
