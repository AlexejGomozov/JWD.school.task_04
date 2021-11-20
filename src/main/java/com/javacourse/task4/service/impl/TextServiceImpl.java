package com.javacourse.task4.service.impl;

import com.javacourse.task4.enity.*;
import com.javacourse.task4.exception.TextException;
import com.javacourse.task4.parser.FileTextParser;
import com.javacourse.task4.service.TextService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;


public class TextServiceImpl implements TextService {
    private static final Logger logger = LogManager.getLogger();
    static final String VOWEL_REG = "[aeiouy]";

    @Override
    public List<TextComponent> paragraphSort(String document) throws TextException {

        FileTextParser fileTextParser = new FileTextParser();
        TextComponent fileComposit = fileTextParser.parse(document);
        List<TextComponent> paragraphs = fileComposit.getList();

       Comparator<TextComponent> paragraphsComparator = (o1, o2) -> {
           int count1 = o1.getList().size();
           int count2 = o2.getList().size();
           return Integer.compare(count1, count2);
       };
       paragraphs.sort(paragraphsComparator);

        return paragraphs;
    }

    @Override
    public String findSentenceLongestWord(TextComponent textComponent) throws TextException{
       if(textComponent == null){
        logger.error("Argument of  findSentenceLongestWord metod is null");
        throw new TextException("Argument of  findSentenceLongestWord metod is null");
       }else if(textComponent.getType()!= TextCompositeType.PARAGRAPH){
        logger.error("For 'findSentenceLongestWord' required TextCompositeType.PARAGRAPH, but it's not just " + textComponent.getType());
        throw new TextException("Exception in method 'findSentenceLongestWord' ");
       }

        List<TextComponent> sentence = findSentences(textComponent);
        String longestWord = findLongestWord(sentence);

        for(TextComponent sentenceWord : sentence){
            if(sentenceWord.toString().contains(longestWord)){
                logger.info("Longest word sentence:  " + sentenceWord);
                return sentenceWord.toString();
            }
        }
                return null;
        }

    @Override
    public String deleteSentenceCountOfSmallWords(TextComponent textComponent, int count) throws TextException{
        if(textComponent == null){
            logger.error("Argument of  'findSentenceLongestWord' method is null");
            throw new TextException("Argument of  'findSentenceLongestWord' method is null");
        }else if(textComponent.getType()!= TextCompositeType.PARAGRAPH){
            logger.error("For 'deleteSentenceCountOfSmallWords' required TextCompositeType.PARAGRAPH, but it's not just " + textComponent.getType());
            throw new TextException("Exception in method 'deleteSentenceCountOfSmallWords' ");
        }
        List<TextComponent> sentence2 = findSentences(textComponent);
        logger.info("Sentences into the text " + sentence2);
        String newDocument = sentence2.stream()
                .filter(o -> countWords(o) < count)
                .map(o -> o.getList().toString())
                .collect(Collectors.joining());

        logger.info("Remaining sentences with fixed word count  " + newDocument);

        return newDocument;
    }

    @Override
    public Map<String, Integer> findAmountSameWords(TextComponent textComponent) throws TextException {

        if (textComponent == null) {
            logger.error("Argument of  'findSentenceLongestWord' method is null");
            throw new TextException("Argument of  'findSentenceLongestWord' method is null");
        } else if (textComponent.getType() != TextCompositeType.PARAGRAPH) {
            logger.error("For 'deleteSentenceCountOfSmallWords' required TextCompositeType.PARAGRAPH, but it's not just " + textComponent.getType());
            throw new TextException("Exception in method 'deleteSentenceCountOfSmallWords' ");
        }
        Map<String, Integer> wordRepeated = new HashMap<>();
                int count = 0;

        List<TextComponent> sentence3 = findSentences(textComponent);
        List<String> wordString = findWords(sentence3);
        logger.info("Words for search " + wordString);

        for (int i = 0; i < wordString.size(); i++) {
                    count = 0;
            for (int j = 0; j < wordString.size(); j++) {
                if (wordString.get(i).equals(wordString.get(j))) {
                    count++;
                }
            }
            if(count!=1){
            wordRepeated.put(wordString.get(i), count); }
        }
        logger.info("Amount repeated word into the text  " + wordRepeated);

        return wordRepeated;
    }


    @Override
    public long countVowel(TextComponent textComponent) throws TextException{
        if (textComponent == null) {
            logger.error("Argument of  'findSentenceLongestWord' method is null");
            throw new TextException("Argument of  'findSentenceLongestWord' method is null");

          } else if (textComponent.getType() != TextCompositeType.PARAGRAPH) {
            logger.error("For 'deleteSentenceCountOfSmallWords' required TextCompositeType.PARAGRAPH, but it's not just " + textComponent.getType());
            throw new TextException("Exception in method 'deleteSentenceCountOfSmallWords' ");
          }
        List<TextComponent> sentence3 = findSentences(textComponent);
        List<String> wordsForCountVowelMethod = findWords(sentence3);
        logger.info("Words for count a vowel into the text  " + wordsForCountVowelMethod);

        long vowelAmount = wordsForCountVowelMethod.stream()
                .map(u -> u.matches(VOWEL_REG))
                .count();
        logger.info("Amount of vowel into the text :  " + vowelAmount);

        return vowelAmount;
    }

    private List<TextComponent> findSentences (TextComponent textComponent) {
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

    public static String findLongestWord(List<TextComponent> sentence1) throws TextException{

        List<String> string = findWords(sentence1);

        string.sort((s1,s2) -> s2.length()-s1.length()); //it's possible to use sb.sort(Comparator.comparingInt(String::length));
        logger.info("The longest word: " + string.get(0));

        return string.get(0);
    }

     private int countWords(TextComponent sentence) {
        return (int) sentence.getList().stream()
                .map(o -> o.getChild(0).equals(WordLeaf.class))
                .count();
     }
}
