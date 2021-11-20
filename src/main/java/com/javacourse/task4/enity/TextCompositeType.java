package com.javacourse.task4.enity;

public enum TextCompositeType {

    PARAGRAPH("\n\t", "\t"),
    SENTENCE( " ",""),
    LEXEME( " ",""),
    WORD( "", ""),
    SYMBOL("",""),
    PUNCTUATION("","");

        private final String delimiter;
        private final String prefix;

         TextCompositeType(String delimiter, String prefix){
            this.delimiter = delimiter;
            this.prefix = prefix;
        }
        public String getDelimiter(){
             return delimiter;
        }
        public String getPrefix(){
             return prefix;
        }
    }

