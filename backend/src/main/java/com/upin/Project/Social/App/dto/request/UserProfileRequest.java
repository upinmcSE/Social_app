package com.upin.Project.Social.App.dto.request;


import com.upin.Project.Social.App.enums.Gender;
import com.upin.Project.Social.App.validator.DobConstraint;
import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProfileRequest {
    String userId;

    @Email(message = "INVALID_EMAIL")
    String email;


    String phoneNumber;
    String firstName;
    String lastName;
    String address;

    @DobConstraint(min = 12, message = "INVALID_DOB")
    LocalDate dob;

    Gender gender;
    Blob avatar;
    List<String> favoriteCategories;
}
