package ru.job4j;

import ru.job4j.dto.ResponseEntity;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CameraAggregator {
    private final String MAIN_URL = "http://www.mocky.io/v2/5c51b9dd3400003252129fb5";
    private final Map<Integer, AggregatedEntity> result = new ConcurrentHashMap<>();
    private final ConnectionUtils connectionUtils = new ConnectionUtils();
    private final JsonUtils jsonUtils = new JsonUtils();
    private final ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public CameraAggregator() {
    }

    public void process() {
        ResponseEntity[] responseEntities = jsonUtils.readValue(connectionUtils.connectAndGet(MAIN_URL), ResponseEntity[].class);

        Arrays.stream(responseEntities).forEach(entity -> pool.submit(new ClientThread(entity, result, connectionUtils, jsonUtils)));
    }
}
