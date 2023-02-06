package com.rp.sec03.assignment.impl02;

import com.rp.log.Log;
import reactor.core.publisher.SynchronousSink;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class FileLineReader2 {
    RandomAccessFile r;

    public static Callable<RandomAccessFile> openFile(Path path) {
        return () -> {
            Log.logLine("File opened");
            return new RandomAccessFile(path.toString(), "r");
        };
    }

    public static BiFunction<RandomAccessFile, SynchronousSink<String>, RandomAccessFile> readLine() {
        return (randomAccessFile, sink) -> {
            try {
                Log.logLine("Line read");
                String line = randomAccessFile.readLine();
                if (Objects.isNull(line)) sink.complete();
                else sink.next(line);
            } catch (IOException e) {
                sink.error(new RuntimeException("Unable to read file line"));
            }
            return randomAccessFile;
        };
    }

    public static Consumer<RandomAccessFile> closeFile() {
        return (r) -> {
            try {
                Log.logLine("File closed");
                r.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }
}
