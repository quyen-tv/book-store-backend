package com.quyentv.bookstorebackend.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
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
    String username;

    @NotNull(message = "PASSWORD_IS_REQUIRED")
    @Size(min = 6, message = "PASSWORD_MIN_SIZE_INVALID")
    String password;
}
