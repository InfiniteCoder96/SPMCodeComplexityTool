package com.example.spm.demo.util;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class FileReadUtil {

    public List<String> showResourceData(String filePath) throws IOException {


        File file = new File(filePath);

        int numberOfLines = 0;

        List<String> wordArrayList = null;
        List<String> ancestorClasses = new ArrayList<>();
        int ancestorClassCount = 0;
        int count = 0;
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;

            String filePaths = file.getPath();
            String fileExtension = filePath.substring(filePath.indexOf('.') + 1);
            System.out.println(fileExtension);
            System.out.println(filePaths);
            while (true) {
                line = reader.readLine();
                if (line == null)
                    break;
                wordArrayList = Arrays.asList(line.split("\\W+"));

                wordArrayList.replaceAll(String::trim);

                for (String word : wordArrayList){
                    if(word.equals("extends")) {

                        int c = wordArrayList.indexOf("extends");

                        ancestorClasses.add(wordArrayList.get(c + 1));

                        ancestorClassCount++;
                    }
                }

                System.out.println(wordArrayList);
                numberOfLines++;
            }
            reader.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(wordArrayList);
        return ancestorClasses;

    }
}
