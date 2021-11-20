package com.javacourse.task4.reader;

import com.javacourse.task4.exception.TextException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextReaderTest {

    String text = "resources/testTextFile.txt";
    String textExpected ="England will take San Marino, the world's worst national team in Fifa's rankings,\n" +
            "'very seriously' as the Three Lions have yet to qualify for the World Cup, says boss Gareth Southgate.\n" +
            "\n"+
            "England need one point from their final group game to ensure qualification.\n" +
            "\n"+
            "San Marino are 210th - bottom - in the world rankings and have lost all nine group games\n" +
            "in qualifying, conceding 36 times and scoring just once.";
    @Test
    void readFile() throws TextException{
        TextReader textReader = new TextReader();
        String actual = textReader.readFile(text);
        assertEquals(textExpected,actual);
    }
}
