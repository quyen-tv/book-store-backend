package com.quyentv.bookstorebackend.dto.request;

import com.quyentv.bookstorebackend.validator.DobConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {

    @NotNull(message = "USERNAME_IS_REQUIRED")
    @Size(min = 4, message = "INVALID_USERNAME")
    String username;

    @NotNull(message = "PASSWORD_IS_REQUIRED")
    @Size(min = 6, message = "INVALID_PASSWORD")
    String password;

    String firstName;
    String lastName;

    @DobConstraint(min = 10, message = "INVALID_DOB")
    LocalDate dob;
}
