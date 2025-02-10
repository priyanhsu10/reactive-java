package com.pro.reacrtive_example.sec02;

import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileService implements IFileService{
    private String FileBasepath ="/Users/priyanshuparate/practice/reacrtive-example/src/test/java/com/pro/reacrtive_example/sec02";
    @Override
    public Mono<String> read(String fileName) {

        return  Mono.fromSupplier(()->{
            Path path = Paths.get(FileBasepath+"/"+ fileName);
            List<String> strings = null;
            try {
                strings = Files.readAllLines(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return  strings.stream().collect(Collectors.joining("\n"));

        });
    }

    @Override
    public Mono<Void> writeFile(String fileName, String contents) {

        return  Mono.fromRunnable(()->{
           Path p=Paths.get(FileBasepath+"/"+fileName);
            try {
                Files.writeString(p,contents);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
    }

    @Override
    public Mono<Void> delete(String fileName) {
        return  Mono.fromRunnable(()->{
            try {
                Files.delete(Paths.get(FileBasepath+"/"+ fileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
