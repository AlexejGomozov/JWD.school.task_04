package com.javacourse.task4.service;

import com.javacourse.task4.enity.TextComponent;
import com.javacourse.task4.exception.TextException;
import com.javacourse.task4.parser.FileTextParser;
import com.javacourse.task4.reader.TextReader;
import com.javacourse.task4.service.impl.TextServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextServiceImplTest {
   TextComponent result1;
    FileTextParser fileTextParser;
    String data;
    TextReader fileText;

String expectedParagraph ="[England will take San Marino, the world's worst national team in Fifa's rankings, 'very seriously' as the Three Lions have yet to qualify for the World Cup, says boss Gareth Southgate., England need one point from their final group game to ensure qualification., San Marino are 210th - bottom - in the world rankings and have lost all nine group games in qualifying, conceding 36 times and scoring just once.]";
String expectedSentenceLongestWord = "England need one point from their final group game to ensure qualification.";
String expectedSentences = "[England, need, one, point, from, their, final, group, game, to, ensure, qualification.]";
Integer expectedAmountSameWords = 4;
long expectedCountVowel = 68;
    @BeforeEach
          void prepared() throws TextException{
        fileText = new TextReader();
        data = fileText.readFile("resources/testTextFile.txt");
        fileTextParser = new FileTextParser();
        result1 = fileTextParser.parse(data);
  }

  @Test
    void paragraphSortTest() throws TextException {
       TextServiceImpl service = new TextServiceImpl();
     List<TextComponent> actual = service.paragraphSort(result1.toString());
    assertEquals(expectedParagraph, actual.toString());
    }

    @Test
    void findSentenceLongestWordTest() throws TextException{
          TextServiceImpl service = new TextServiceImpl();
        String actual = service.findSentenceLongestWord(result1);
      assertEquals(expectedSentenceLongestWord, actual);
    }

    @Test
    void deleteSentenceCountOfSmallWordsTest() throws TextException {
          TextServiceImpl service = new TextServiceImpl();
        String actual = service.deleteSentenceCountOfSmallWords(result1,13);
      assertEquals(expectedSentences,actual);
    }

    @Test
    void findAmountSameWords() throws TextException{
          TextServiceImpl service = new TextServiceImpl();
        Integer actual = service.findAmountSameWords(result1).get("the");
      assertEquals(expectedAmountSameWords, actual);
    }

    @Test
    void  countVowelTest() throws TextException{
           TextServiceImpl service = new TextServiceImpl();
         long actual = service.countVowel(result1);
       assertEquals(expectedCountVowel, actual);
    }
}
