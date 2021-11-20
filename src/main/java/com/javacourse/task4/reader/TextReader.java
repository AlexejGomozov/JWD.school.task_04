package com.javacourse.task4.reader;

import com.javacourse.task4.exception.TextException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TextReader {
    private static final Logger logger = LogManager.getLogger();

    public String readFile(String filePathString) throws TextException{

        if(filePathString == null || !(new File(filePathString).isFile())){
            logger.info("File {}" + filePathString + " isn't valid or it's empty");
            throw new TextException("File isn't valid or it's empty");
        }
       String text;
        try{
            text = Files.readString(Paths.get(filePathString), StandardCharsets.UTF_8);
        }catch(IOException e){
            logger.info("It's some problems while read file" + filePathString);
            throw new TextException("It's some problems while read file" + filePathString);
        }
        return text;
    }
}
