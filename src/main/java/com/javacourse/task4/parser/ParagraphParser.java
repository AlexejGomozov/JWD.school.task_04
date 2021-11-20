package com.javacourse.task4.parser;

import com.javacourse.task4.enity.TextCompositeType;
import com.javacourse.task4.enity.TextComposit;
import com.javacourse.task4.exception.TextException;
import com.javacourse.task4.enity.TextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ParagraphParser implements TextParser{
    private static final Logger logger = LogManager.getLogger();
private static final String SENTENCE_PARSER_REGEX = "(?<=([.!?.{3}]\\s))";
private TextParser sentenceParser = new SentenceParser();

    @Override
    public TextComponent parse (String paragraph) throws TextException{
      if(paragraph == null || paragraph.isBlank()){
          logger.error("Text is null or blank");
          throw new TextException("Text is null or blank");
    }
    TextComposit paragraphComposit = new TextComposit(TextCompositeType.SENTENCE);

    String[] sentences = paragraph.split(SENTENCE_PARSER_REGEX);

    for(String sentence : sentences){
        TextComponent sentenceComposite = sentenceParser.parse(sentence);
        paragraphComposit.add(sentenceComposite);
    }
    return paragraphComposit;
}
}
