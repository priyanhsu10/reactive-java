package com.pro.reacrtive_example.sec02;

import reactor.core.publisher.Mono;

public interface IFileService {
    Mono<String> read(String fileName);
    Mono<Void> writeFile(String fileName,String contents);
    Mono<Void> delete(String fileName);
}
