package com.javacourse.task4.parser;

import com.javacourse.task4.enity.TextComponent;
import com.javacourse.task4.enity.TextCompositeType;
import com.javacourse.task4.enity.TextComposit;
import com.javacourse.task4.exception.TextException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileTextParser implements TextParser {
    private static final Logger logger = LogManager.getLogger();

    private static final String FILE_TEXT_PARSER_REG = "\\n\\s+";
    private TextParser paragraphParser = new ParagraphParser();

    @Override
    public TextComponent parse(String file) throws TextException {
        if(file == null || file.isBlank()){
            logger.error("Text is null or blank");
            throw new TextException("Text is null or blank");
        }
        TextComposit textComposit = new TextComposit(TextCompositeType.PARAGRAPH);
        String[] paragraphs = file.trim().split(FILE_TEXT_PARSER_REG);

        for(String paragraph : paragraphs){
            TextComponent paragraphComposite = paragraphParser.parse(paragraph);
            textComposit.add(paragraphComposite);
        }
        return textComposit;
    }
}
