package ru.job4j;

import ru.job4j.dto.ResponseEntity;
import ru.job4j.dto.SourceData;
import ru.job4j.dto.TokenData;

import java.util.Map;

public class ClientThread extends Thread {
    private final ResponseEntity responseEntity;
    private final Map<Integer, AggregatedEntity> result;
    private final ConnectionUtils connectionUtils;
    private final JsonUtils jsonUtils;

    public ClientThread(ResponseEntity responseEntity, Map<Integer, AggregatedEntity> result, ConnectionUtils connectionUtils, JsonUtils jsonUtils) {
        this.responseEntity = responseEntity;
        this.result = result;
        this.connectionUtils = connectionUtils;
        this.jsonUtils = jsonUtils;
    }

    @Override
    public void run() {
        TokenData tokenData = processTokenData(responseEntity);
        SourceData sourceData = processSourceData(responseEntity);

        result.putIfAbsent(responseEntity.getId(), AggregatedEntity.builder()
                .id(responseEntity.getId())
                .sourceDataUrl(responseEntity.getSourceDataUrl())
                .tokenDataUrl(responseEntity.getTokenDataUrl())
                .urlType(sourceData.getUrlType())
                .videoUrl(sourceData.getVideoUrl())
                .value(tokenData.getValue())
                .ttl(tokenData.getTtl())
                .build());
    }

    private TokenData processTokenData(ResponseEntity entity) {
        return jsonUtils.readValue(connectionUtils.connectAndGet(entity.getTokenDataUrl()), TokenData.class);
    }

    private SourceData processSourceData(ResponseEntity entity) {
        return jsonUtils.readValue(connectionUtils.connectAndGet(entity.getSourceDataUrl()), SourceData.class);
    }
}
