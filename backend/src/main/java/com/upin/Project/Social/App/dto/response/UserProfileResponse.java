package com.upin.Project.Social.App.dto.response;

import com.upin.Project.Social.App.enums.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProfileResponse {
    String id;
    String username;
    String email;
    String phoneNumber;
    String firstName;
    String lastName;
    String address;
    LocalDate dob;
    Gender gender;
    Blob avatar;
    Boolean active;
    int error;
    List<String> favoriteCategories;
}
