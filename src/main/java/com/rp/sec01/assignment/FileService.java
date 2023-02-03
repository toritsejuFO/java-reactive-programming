package com.rp.sec01.assignment;

import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;

public class FileService {

    public static Mono<String> readFile(String name) {
        return Mono.fromFuture(readFileBytes(name)).map(String::new);
    }

    public static Mono<Void> writeFile(String name, String data) {
        return Mono.fromRunnable(writeFileBytes(name, data.getBytes(StandardCharsets.UTF_8)));
    }

    public static Mono<Void> deleteFile(String name) {
        return Mono.fromRunnable(deleteFileRunnable(name));
    }

    private static CompletableFuture<byte[]> readFileBytes(String name) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return Files.readAllBytes(getBaseStoragePath().resolve(name));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static Runnable writeFileBytes(String name, byte[] data) {
        return () -> {
            try {
                Files.write(getBaseStoragePath().resolve(name), data);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }

    private static Runnable deleteFileRunnable(String name) {
        return () -> {
            try {
                Files.delete(getBaseStoragePath().resolve(name));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }

    private static Path getBaseStoragePath() {
        return Paths.get("src/main/resources/assignment/sec01");
    }
}
