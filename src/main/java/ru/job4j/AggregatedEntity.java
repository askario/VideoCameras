package ru.job4j;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.job4j.dto.UrlType;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@Builder
public class AggregatedEntity {
    int id;
    int ttl;
    String sourceDataUrl;
    String tokenDataUrl;
    String value;
    String videoUrl;
    UrlType urlType;
}