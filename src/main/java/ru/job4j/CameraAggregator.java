package ru.job4j;

import ru.job4j.dto.ResponseEntity;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
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
        CompletableFuture[] futures = new CompletableFuture[responseEntities.length];

        for (int i = 0; i < responseEntities.length; i++) {
            Thread workerThread = new WorkerThread(responseEntities[i], result, connectionUtils, jsonUtils);
            futures[i] = CompletableFuture.runAsync(workerThread, pool);
        }

        CompletableFuture.allOf(futures).join();

        pool.shutdown();
    }

    public Map<Integer, AggregatedEntity> getResult() {
        return result;
    }
}
