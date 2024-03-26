package com.example.webforuni;


import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static Resource convertToRes(byte[] bytes, String fileName) {
        return new ByteArrayResource(bytes) {
            @Override
            public String getFilename() {
                return fileName;
            }
        };
    }

    public static void writeToFile(Resource fileResource, String filePath) throws IOException {
        try(OutputStream outputStream = new FileOutputStream(new File(filePath))){
            FileCopyUtils.copy(fileResource.getInputStream(), outputStream);
        }
    }

    public static void main(String[] args) throws IOException {
//        Path rootPdf = Paths.get("/Users/kenny/lib/test.pdf");
//        byte[] pdf = Files.readAllBytes(rootPdf);
//        Resource res = convertToRes(pdf, "testFileRes.pdf");
//
//        try{
//            writeToFile(res, "/Users/kenny/lib/testFromRes.pdf");
//            System.out.println("WORKING!");
//        } catch (IOException e ){
//            System.out.println("PIZDA!!! + " + e.getMessage());
//        }


        List<Integer> testList = new ArrayList<>();
        testList.add(45);
        testList.add(123);
        testList.add(4);
        testList.add(10);

        System.out.println(testList);

        testList.removeIf(i -> i == 4);

        System.out.println(testList);
    }


}
