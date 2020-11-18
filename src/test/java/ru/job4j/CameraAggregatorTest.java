package ru.job4j;

import org.hamcrest.collection.IsMapContaining;
import org.junit.jupiter.api.Test;
import ru.job4j.dto.UrlType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CameraAggregatorTest {

    @Test
    public void testCameraAggregator() {
        CameraAggregator cameraAggregator = new CameraAggregator();
        cameraAggregator.process();

        Map<Integer, AggregatedEntity> result = cameraAggregator.getResult();
        Map<Integer, AggregatedEntity> expected = expectedMap();

        assertThat(result.size(), is(4));
        assertThat(result, IsMapContaining.hasKey(20));

        assertThat(result.get(Integer.valueOf(20).toString()), is(expected.get(Integer.valueOf(20).toString())));
    }

    private Map<Integer, AggregatedEntity> expectedMap() {
        Map<Integer, AggregatedEntity> expected = new ConcurrentHashMap<>();

        expected.put(Integer.valueOf(1), AggregatedEntity.builder()
                .id(1)
                .ttl(120)
                .sourceDataUrl("http://www.mocky.io/v2/5c51b230340000094f129f5d")
                .tokenDataUrl("http://www.mocky.io/v2/5c51b5b6340000554e129f7b?mocky-delay=1s")
                .value("fa4b588e-249b-11e9-ab14-d663bd873d93")
                .videoUrl("rtsp://127.0.0.1/1")
                .urlType(UrlType.LIVE).build());

        expected.put(Integer.valueOf(2), AggregatedEntity.builder()
                .id(2)
                .ttl(180)
                .sourceDataUrl("http://www.mocky.io/v2/5c51b5023400002f4f129f70")
                .tokenDataUrl("http://www.mocky.io/v2/5c51b623340000404f129f82")
                .value("fa4b5f64-249b-11e9-ab14-d663bd873d93")
                .videoUrl("rtsp://127.0.0.1/20")
                .urlType(UrlType.LIVE).build());

        expected.put(Integer.valueOf(3), AggregatedEntity.builder()
                .id(3)
                .ttl(120)
                .sourceDataUrl("http://www.mocky.io/v2/5c51b4b1340000074f129f6c")
                .tokenDataUrl("http://www.mocky.io/v2/5c51b600340000514f129f7f?mocky-delay=2s")
                .value("fa4b5d52-249b-11e9-ab14-d663bd873d93")
                .videoUrl("rtsp://127.0.0.1/3")
                .urlType(UrlType.ARCHIVE).build());

        expected.put(Integer.valueOf(20), AggregatedEntity.builder()
                .id(20)
                .ttl(60)
                .sourceDataUrl("http://www.mocky.io/v2/5c51b2e6340000a24a129f5f?mocky-delay=100ms")
                .tokenDataUrl("http://www.mocky.io/v2/5c51b5ed340000554e129f7e")
                .value("fa4b5b22-249b-11e9-ab14-d663bd873d93")
                .videoUrl("rtsp://127.0.0.1/2")
                .urlType(UrlType.ARCHIVE).build());

        return expected;
    }

    private Map<Integer, AggregatedEntity> entry() {
        Map<Integer, AggregatedEntity> expected = new ConcurrentHashMap<>();

        expected.put(Integer.valueOf(1), AggregatedEntity.builder()
                .id(1)
                .ttl(120)
                .sourceDataUrl("http://www.mocky.io/v2/5c51b230340000094f129f5d")
                .tokenDataUrl("http://www.mocky.io/v2/5c51b5b6340000554e129f7b?mocky-delay=1s")
                .value("fa4b588e-249b-11e9-ab14-d663bd873d93")
                .videoUrl("rtsp://127.0.0.1/1")
                .urlType(UrlType.LIVE).build());

        return expected;
    }
}
