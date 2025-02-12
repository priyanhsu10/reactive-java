package com.pro.reacrtive_example.sec04.assignments;

import com.pro.reacrtive_example.common.Util;
import com.pro.reacrtive_example.sec01.subscriber.SubscriberImpl;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class Assinment02 {
    public static void main(String[] args) {

        var freader = new BFileReaderService();
        var subscriber = new SubscriberImpl();
        Path p = Paths.get("/Users/priyanshuparate/practice/reacrtive-example/src/main/java/com/pro/reacrtive_example/sec03/Assingment.java");
        freader
                .read(p)
                .subscribe(subscriber);
//        subscriber.getSubscription().request(Integer.MAX_VALUE);
        subscriber.getSubscription().request(4);
        Util.sleepSeconds(2);
        subscriber.getSubscription().request(15);
        Util.sleepSeconds(2);

        subscriber.getSubscription().cancel();
        Util.sleepSeconds(2);
        subscriber.getSubscription().request(5);
        Util.sleepSeconds(2);

    }
}


interface IFileReaderService {
    Flux<String> read(Path path);
}

class AFileReaderService implements IFileReaderService {

    @Override
    public Flux<String> read(Path path) {


        try {
            return Flux.<String>fromStream(Files.lines(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class BFileReaderService implements IFileReaderService {

    @Override
    public Flux<String> read(Path path) {
        return Flux.generate(() -> openFile(path),
                (br, sink) -> readFile(br, sink),
                (br)->closeFile(br));


    }

    public void closeFile(BufferedReader reader) {
        try {
            System.out.println("close file");
            reader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public BufferedReader readFile(BufferedReader reader, SynchronousSink<String> sink) {

        try {
            var line = reader.readLine();
            if (Objects.isNull(line)) {
                sink.complete();
            } else {
                sink.next(line);
            }
            return reader;
        } catch (Exception e) {
            sink.error(e);
        }
        return  reader;
    }

    private BufferedReader openFile(Path path) {

        try {
            return Files.newBufferedReader(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}