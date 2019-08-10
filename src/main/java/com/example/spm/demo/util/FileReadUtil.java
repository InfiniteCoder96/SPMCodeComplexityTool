package com.example.spm.demo.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileReadUtil {

    public List<String> showResourceData(String filePath) throws IOException {


        File file = new File(filePath);



        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);

            String filePaths = file.getPath();
            String fileExtension = filePath.substring(filePath.indexOf('.') + 1);
            System.out.println(fileExtension);
            System.out.println(filePaths);

            switch (fileExtension){
                case "java":
                    return this.processJavaCode(reader);

                case "cp":
                    return this.processCplusCode(reader);

                default:

            }



        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    private List<String> processJavaCode(BufferedReader reader) throws IOException {

        String line;
        List<String> wordArrayList;
        List<String> ancestorClasses = new ArrayList<>();;
        int numberOfLines = 0;
        int ancestorClassCount = 0;


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


            numberOfLines++;
        }

        return ancestorClasses;
    }

    private List<String> processCplusCode(BufferedReader reader) throws IOException {

        String line;
        List<String> wordArrayList;
        List<String> listWithDuplicates = new ArrayList<>();

        int numberOfLines = 0;
        int ancestorClassCount = 0;

        while (true) {
            line = reader.readLine();
            if (line == null)
                break;

            if(line.contains(":")) {
                String x = line.substring(line.indexOf(":"));

                wordArrayList = Arrays.asList(x.split("\\W+"));


                for(int i = 0; i < wordArrayList.size(); i++){
                    if(wordArrayList.get(i).trim().equals("public")){

                        listWithDuplicates.add(wordArrayList.get(i + 1));

                    }
                }

            }

            numberOfLines++;

        }


        return listWithDuplicates.stream().distinct().collect(Collectors.toList());
    }
}
