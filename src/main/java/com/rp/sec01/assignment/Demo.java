package com.rp.sec01.assignment;

import com.rp.util.Util;

public class Demo {
    public static void main(String[] args) {
        // Read an existing file
        Util.startTimer();
        FileService.readFile("fileIn.txt")
                .subscribe(
                        Util.onNext(),
                        Util.onError(),
                        Util.onComplete("Read operation complete")
                );
        Util.stopTimer();


        // Create a new file and write to it
        Util.startTimer();
        FileService.writeFile("fileOut.txt", "Hello mate!\n")
                .subscribe(
                        Util.onNext(),
                        Util.onError(),
                        Util.onComplete("Write operation complete")
                );
        Util.stopTimer();


        // Delete a file
        Util.startTimer();
        FileService.deleteFile("fileD.txt")
                .subscribe(
                        Util.onNext(),
                        Util.onError(),
                        Util.onComplete("Delete operation complete")
                );
        Util.stopTimer();
    }
}
