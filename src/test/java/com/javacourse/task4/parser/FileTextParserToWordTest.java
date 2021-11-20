package com.javacourse.task4.parser;

import com.javacourse.task4.enity.TextComponent;
import com.javacourse.task4.exception.TextException;
import com.javacourse.task4.reader.TextReader;
import com.javacourse.task4.service.impl.TextServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileTextParserToWordTest {
    String expectedWord;
    TextReader fileText;
    String actualWord;
    String data;
    FileTextParser fileTextParser;
    TextComponent fileComposit;
    List<TextComponent> sentence2;

    @BeforeEach
        void prepare() throws TextException{
            expectedWord = "qualification";
            fileText = new TextReader();
            data = fileText.readFile("resources/testTextFile.txt");
            fileTextParser = new FileTextParser();
            fileComposit = fileTextParser.parse(data);
            sentence2 = new ArrayList<>();
    }
    @Test
    void parseToWord() throws TextException{
        List<TextComponent> paragraphComponentList2 = fileComposit.getList();
        for (int i = 0; i < paragraphComponentList2.size(); i++) {
            List<TextComponent> sentenceComponentList = paragraphComponentList2.get(i).getList();

            for (int y = 0; y < sentenceComponentList.size(); y++) {
                sentence2.add(sentenceComponentList.get(y));
            }
        }
        actualWord = TextServiceImpl.findLongestWord(sentence2);
        assertEquals(actualWord,expectedWord);
    }
}
