package com.rp.sec03.assignment.impl01;

import com.rp.util.Util;
import reactor.core.publisher.Flux;

public class Demo {

    public static void main(String[] args) {

        FileLineReader fileLineReader = new FileLineReader("file.txt");

        Flux.create(fileLineReader)
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());

        while (!fileLineReader.done()) {
            fileLineReader.readLine();
        }
    }

}
