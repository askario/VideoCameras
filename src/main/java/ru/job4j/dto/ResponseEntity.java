package ru.job4j.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseEntity {
    int id;
    String sourceDataUrl;
    String tokenDataUrl;
}
