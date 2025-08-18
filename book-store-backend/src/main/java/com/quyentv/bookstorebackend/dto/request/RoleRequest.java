package com.quyentv.bookstorebackend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Set;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleRequest {

    @NotBlank(message = "NAME_IS_REQUIRED")
    String name;

    @NotBlank(message = "DESCRIPTION_IS_REQUIRED")
    String description;

    @NotNull(message = "PERMISSIONS_IS_REQUIRED")
    @Size(min = 1, message = "INVALID_PERMISSIONS")
    Set<@NotBlank(message = "PERMISSION_CANNOT_BE_BLANK") String> permissions;
}
