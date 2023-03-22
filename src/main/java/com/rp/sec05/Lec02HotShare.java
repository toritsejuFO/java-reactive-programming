package com.rp.sec05;

import com.rp.log.Log;
import com.rp.util.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class Lec02HotShare {

    public static void main(String[] args) {
        // share - publish().refCount(1)
        Flux<String> movieStream = Flux.fromStream(Lec02HotShare::getMovie)
                .delayElements(Duration.ofSeconds(2))
                .share();

        movieStream
                .subscribe(Util.subscriber("sam"));

        Util.sleepSeconds(5);

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
