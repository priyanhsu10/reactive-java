package com.pro.reacrtive_example.sec09.assinments;

import com.pro.reacrtive_example.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;

public class WindowAssigment {
    public static void main(String[] args) {
        logStream().window(Duration.ofSeconds(2))
                .publishOn(Schedulers.boundedElastic())
                .flatMap(FileWriter::writeFile)
                .subscribe();
        Util.sleepSeconds(10);
    }
    public  static  Flux<String> logStream(){
        return  Flux.interval(Duration.ofMillis(50))
                .map(x->"event log -"+x);
    }
}

class  FileWriter{

    private final Path path;
    private BufferedWriter writer;

    FileWriter(Path path) {
        this.path = path;
    }
    private void createFile(){
        try {
            writer= Files.newBufferedWriter(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void write(String content){

        try {
            writer.write("therad : "+Thread.currentThread().getName()+"["+LocalDateTime.now()+"]" +content);
            writer.write('\n');
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void closeFile(){
        try {
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public  static Mono<Void> writeFile(Flux<String> logStream){
        var currenttime= LocalDateTime.now().toString();
        var fileWriter=new FileWriter(Path.of("/Users/priyanshuparate/practice/reacrtive-example/src/main/resources/windowlogs/log_"+currenttime+".log"));
        return  logStream
                .doOnNext(x->fileWriter.write(x))
                .doOnComplete(()->fileWriter.closeFile())
                .doFirst(()->fileWriter.createFile())
                .then();
    }
}