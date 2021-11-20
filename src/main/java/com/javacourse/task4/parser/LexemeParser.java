package com.javacourse.task4.parser;

import com.javacourse.task4.enity.*;
import com.javacourse.task4.exception.TextException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LexemeParser implements TextParser{
    private static final Logger logger = LogManager.getLogger();

    private static final String PUNCTUATION_REG = "\\W?";
    private static final String LEXEME_WORD = "(?=([?,!-[…]\"]|((?<!\\.)\\.(?!\\.)|(,\")|([!\"],)|([!]\",)|(\".)))$)|((?<=^\\(()|(()\"))(?![0-9]))|((?<=[a-zA-Z])(?=\\)))";

     @Override
      public TextComponent parse(String lexemeText) throws TextException{
         if(lexemeText == null || lexemeText.isBlank()){
             logger.error("Text is null or blank");
             throw new TextException("Text is null or blank");
         }
         TextComposit lexemeComposit = new TextComposit(TextCompositeType.SYMBOL);

         String[] lexemes = lexemeText.split(LEXEME_WORD);

         for(String lexeme :lexemes){
             if(lexeme.matches(PUNCTUATION_REG)){
                 lexemeComposit.add(new PunctuationLeaf(lexeme));
             } else{
                 lexemeComposit.add(new WordLeaf(lexeme));
                  }
         }
         return lexemeComposit;
     }
}
