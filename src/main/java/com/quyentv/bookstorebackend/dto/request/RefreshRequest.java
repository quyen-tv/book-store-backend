package com.quyentv.bookstorebackend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RefreshRequest {

    @NotBlank(message = "REFRESH_TOKEN_IS_REQUIRED")
    String refreshToken;
}
