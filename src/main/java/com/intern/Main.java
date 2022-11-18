package com.intern;

import com.intern.practice22.handler.Handler;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        Handler vHandler = new Handler();
        ExecutorService service = Executors.newFixedThreadPool(2);

        Future future = service.submit(new Callable() {
            public Object call() throws Exception {
                vHandler.printOutFinesByAmount();

                return "Done";
            }
        });

        service.shutdown();
    }
}
