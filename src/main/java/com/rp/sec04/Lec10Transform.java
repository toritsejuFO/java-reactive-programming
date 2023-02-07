package com.rp.sec04;

import com.rp.sec04.helper.Person;
import com.rp.util.Util;
import reactor.core.publisher.Flux;

import java.util.function.Function;

public class Lec10Transform {

    public static void main(String[] args) {

        getPerson()
                .transform(makeNameUpperCaseIfAgeGreaterThan10())
                .subscribe(Util.subscriber());

    }

    private static Flux<Person> getPerson() {
        return Flux.range(1, 10)
                .map(i -> new Person());
    }

    public static Function<Flux<Person>, Flux<Person>> makeNameUpperCaseIfAgeGreaterThan10() {
        return personFlux -> personFlux
                .doOnNext(p -> {
                    if (p.getAge() > 10) p.setName(p.getName().toUpperCase());
                });
    }

}
