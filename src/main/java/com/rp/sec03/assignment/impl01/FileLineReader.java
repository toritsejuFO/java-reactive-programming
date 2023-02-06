package com.rp.sec03.assignment.impl01;

import lombok.SneakyThrows;
import reactor.core.publisher.FluxSink;

import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;

public class FileLineReader implements Consumer<FluxSink<String>> {

    private FluxSink<String> sink;
    private RandomAccessFile r;
    private final String fileName;
    private Boolean done = false;

    public FileLineReader(String fileName) {
        this.fileName = fileName;
    }

    private static Path getBaseStoragePath() {
        return Paths.get("src/main/resources/assignment/sec03");
    }

    @SneakyThrows
    public void readLine() {
        String line = r.readLine();
        if (line != null) {
            sink.next(line);
        } else {
            done = true;
            sink.complete();
        }
    }

    @SneakyThrows
    @Override
    public void accept(FluxSink<String> stringFluxSink) {
        sink = stringFluxSink;
        r = new RandomAccessFile(getBaseStoragePath().resolve(fileName).toString(), "r");
    }

    public Boolean done() {
        return done;
    }
}
