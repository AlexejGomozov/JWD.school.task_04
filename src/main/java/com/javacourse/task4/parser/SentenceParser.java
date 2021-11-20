package com.javacourse.task4.parser;

import com.javacourse.task4.enity.TextComponent;
import com.javacourse.task4.enity.TextCompositeType;
import com.javacourse.task4.enity.TextComposit;
import com.javacourse.task4.exception.TextException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class SentenceParser implements TextParser{
    private static final Logger logger = LogManager.getLogger();
    private static final String SENTENCE_REGEX = "\\s+";
    private TextParser lexemeParser = new LexemeParser();

    @Override
    public TextComponent parse(String sentence) throws TextException{

        if(sentence == null || sentence.isBlank()){
            logger.error("Text is null or blank");
            throw new TextException("Text is null or blank");
        }
        String[] lexemes = sentence.split(SENTENCE_REGEX);

        TextComposit sentenceComposit = new TextComposit(TextCompositeType.LEXEME);

        for(String lexeme : lexemes){
            TextComponent lexemeComposit = lexemeParser.parse(lexeme);
            sentenceComposit.add(lexemeComposit);
        }
        return sentenceComposit;
    }
}
