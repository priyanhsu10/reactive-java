package com.pro.reacrtive_example.sec02;

import com.pro.reacrtive_example.common.Util;

public class Assignment {
    public static void main(String[] args) {
        IFileService fileService = new FileService();
        read(fileService);
//        write(fileService);
//        delete(fileService);
    }

    private static void read(IFileService fileService) {
        fileService.read("ExternalServiceClient.java")
               .subscribe(Util.subscriber());
    }

    private static void write(IFileService fileService) {
        fileService.writeFile("test.text", "this is the content of file ")
                .subscribe(Util.subscriber());
    }

    private static void delete(IFileService fileService) {
        fileService.delete("test.text")
                .subscribe(Util.subscriber());
    }
}
