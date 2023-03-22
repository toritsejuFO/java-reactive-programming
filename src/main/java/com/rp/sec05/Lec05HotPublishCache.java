package com.rp.sec05;

import com.rp.log.Log;
import com.rp.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class Lec05HotPublishCache {
    // Hot publishers are appropriate for broadcasting

    public static void main(String[] args) {
        // share  publish().refCount(1)
        // cache = publish().replay()
        Flux<String> movieStream = Flux.fromStream(Lec05HotPublishCache::getMovie)
                .delayElements(Duration.ofSeconds(1))
                .cache(2);
        Util.sleepSeconds(3);

        movieStream
                .subscribe(Util.subscriber("sam"));

        Util.sleepSeconds(10);

        Log.logLine("Mike is about to start streaming");

        movieStream
                .subscribe(Util.subscriber("mike"));


        Util.sleepSeconds(60);
    }

    private static Stream<String> getMovie() {
        Log.logLine("Got the movie streaming req");
        return Stream.of(
                "Scene 1",
                "Scene 2",
                "Scene 3",
                "Scene 4",
                "Scene 5",
                "Scene 6",
                "Scene 7"
        );
    }

}
