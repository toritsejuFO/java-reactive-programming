package com.rp.sec03.assignment.impl02;

import com.rp.util.Util;
import reactor.core.publisher.Flux;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Demo {

    public static void main(String[] args) {

        Path path = Paths.get("src/main/resources/assignment/sec03/file.txt");
        Flux.generate(
                FileLineReader2.openFile(path),
                FileLineReader2.readLine(),
                FileLineReader2.closeFile())
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());


    }
}
