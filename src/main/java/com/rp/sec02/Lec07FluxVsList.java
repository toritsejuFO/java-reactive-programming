package com.rp.sec02;

import com.rp.util.Util;
import com.rp.sec02.helper.NameGenerator;

public class Lec07FluxVsList {
    public static void main(String[] args) {
//        List<String> names = NameGenerator.getNamesList(5);
//        Log.logLine(names);

        NameGenerator.getNamesFlux(5)
                .subscribe(Util.onNext());
    }
}
