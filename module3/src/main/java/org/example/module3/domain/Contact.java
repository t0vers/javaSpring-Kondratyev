package org.example.module3.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
    long id;
    String firstName;
    String secondName;
    String email;
    String phoneNumber;
}
