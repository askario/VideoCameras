package ru.job4j;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class JsonUtils {
    ObjectMapper mapper = new ObjectMapper();

    @SneakyThrows
    public <T> T readValue(String jsonString, Class<T> t) {
        T mappedValue = mapper.readValue(jsonString, t);
        return mappedValue;
    }
}
