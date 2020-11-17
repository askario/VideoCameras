package ru.job4j;

public class ApplicationRunner {
    public static ApplicationRunner applicationRunner = new ApplicationRunner();

    private ApplicationRunner() {
    }

    public static void run() {
        CameraAggregator cameraAggregator = new CameraAggregator();
        cameraAggregator.process();
    }

    public static void main(String[] args) {
        ApplicationRunner.run();
    }
}
