package com.javacourse.task4.main;

import com.javacourse.task4.enity.*;
import com.javacourse.task4.exception.TextException;
import com.javacourse.task4.parser.FileTextParser;

import com.javacourse.task4.reader.TextReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;


public class Main {
    private static final Logger logger = LogManager.getLogger();
    static final String VOWEL_REGEX = "[aeiouy]";
    public static void main(String[] args) throws TextException {

        TextReader fileText = new TextReader();
        String data = fileText.readFile("resources/textFile.txt");

        FileTextParser fileTextParser = new FileTextParser();
        TextComponent result1 = fileTextParser.parse(data);

        //  ======================= 'countVowel' method check
     List<TextComponent> sentence3 = findSentences(result1);
        List<String> wordsForCountVowelMethod = findWords(sentence3);
        logger.info("Words for count a vowel into the text  " + wordsForCountVowelMethod);
        long vowelAmount = wordsForCountVowelMethod.stream()
                .map(u -> u.matches(VOWEL_REGEX))
                .count();
        logger.info("Amount of vowel into the text : " + vowelAmount);

        //  ======================= 'findAmountSameWords' method check
        Map<String, Integer> wordRepeated = new HashMap<>();
         int count = 0;
        List<String> wordString;
        List<TextComponent>sentence2 = findSentences(result1);
        wordString = findWords(sentence2);
      for(int i = 0; i <wordString.size(); i++){
          count = 0;
          for(int j = 0; j<wordString.size(); j++){
              if(wordString.get(i).equals(wordString.get(j))){
                  count++;
              }
          }
          wordRepeated.put(wordString.get(i),count);
      }
        logger.info("Word repeated ________ " + wordRepeated);


        //  ======================= 'findSentenceLongestWord' method check
        List<TextComponent>sentence1 = findSentences(result1);
        String longestWord = findLongestWord(sentence1);

       for(TextComponent sentenceWord : sentence1){
           if(sentenceWord.toString().contains(longestWord)){
               logger.info("Sentence of the longest word ________ " + sentenceWord);
           }
       }

//  ======================= 'deleteSentenceCountOfSmallWords' method check
        List<TextComponent> sentence4 = findSentences(result1);
        logger.info("Sentences into the text " + sentence4);
        String newDocument = sentence4.stream()
                .filter(o -> countWords(o) < 10)
                .map(o -> o.getList().toString())
                .collect(Collectors.joining());

       logger.info("Remaining sentences with less than the specified word count ________ " + newDocument);



    //  /* ======================= 'sortParagraph' method check
        FileTextParser fileTextParser1 = new FileTextParser();
        TextComponent fileComposit = fileTextParser1.parse(result1.toString());
        List<TextComponent> paragraphs = fileComposit.getList();


        Comparator<TextComponent> paragraphsComparator = (o1, o2) -> {
            int count1 = o1.getList().size();
            int count2 = o2.getList().size();
            return Integer.compare(count2, count1);
        };
        paragraphs.sort(paragraphsComparator);

        logger.info("Sort paragraph by method 'sortParagraph'_______" +  paragraphs);

    }



    private static List<TextComponent> findSentences (TextComponent textComponent) {
        List<TextComponent> sentence = new ArrayList<>();
        List<TextComponent> paragraphComponentList2 = textComponent.getList();
        for (int i = 0; i < paragraphComponentList2.size(); i++) {
            List<TextComponent> sentenceComponentList = paragraphComponentList2.get(i).getList();

            for (int y = 0; y < sentenceComponentList.size(); y++) {
                sentence.add(sentenceComponentList.get(y));
            }
        }
        return sentence;
    }


    private static List<String> findWords (List<TextComponent> sentences){
        List<String> string = new ArrayList<>();
        for(TextComponent sentence : sentences) {
            List<TextComponent> lexemeComponentList = sentence.getList();
            for (int j = 0; j < lexemeComponentList.size(); j++) {

                if (lexemeComponentList.get(j).getChild(0).getClass().equals(WordLeaf.class)) {
                    TextComponent wordComponent = (TextComponent) lexemeComponentList.get(j).getChild(0);
                    string.add(wordComponent.toString().toLowerCase(Locale.ROOT));
                }
            }
        }
        return string;
    }

    private static String findLongestWord(List<TextComponent> sentence1){
        List<String> string = findWords(sentence1);

        string.sort((s1,s2) -> s2.length()-s1.length()); //sb.sort(Comparator.comparingInt(String::length));
        logger.info("The longest word------- " + string.get(0));
        return string.get(0);
    }


    private static int countWords(TextComponent sentence){
        return (int) sentence.getList().stream()
                .map(o -> o.getChild(0).equals(WordLeaf.class))
                .count();
    }
}