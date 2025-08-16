package com.quyentv.bookstorebackend.dto.request;

import com.quyentv.bookstorebackend.validator.DobConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    String firstName;
    String lastName;

    @DobConstraint(min = 18, message = "INVALID_DOB")
    LocalDate dob;

    @NotNull(message = "ROLES_IS_REQUIRED")
    @Size(min = 1, message = "INVALID_ROLES")
    List<@NotBlank(message = "ROLE_CANNOT_BE_BLANK") String> roles;
}
