package com.rp.sec08;

import com.rp.sec08.helper.NameGenerator;
import com.rp.util.Util;

public class Lec01StartWith {

    public static void main(String[] args) {



        NameGenerator nameGenerator = new NameGenerator();
        nameGenerator.generateNames()
                .take(2)
                .subscribe(Util.subscriber("Sam"));

        nameGenerator.generateNames()
                .take(2)
                .subscribe(Util.subscriber("Mike"));

        nameGenerator.generateNames()
                .take(3)
                .subscribe(Util.subscriber("Jay"));

        nameGenerator.generateNames()
                .filter(n -> n.startsWith("A"))
                .take(1)
                .subscribe(Util.subscriber("Jay"));

    }


}
