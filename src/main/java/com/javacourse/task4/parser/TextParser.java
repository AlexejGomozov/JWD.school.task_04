package com.javacourse.task4.parser;

import com.javacourse.task4.enity.TextComponent;
import com.javacourse.task4.exception.TextException;

public interface TextParser {

    TextComponent parse (String text) throws TextException;
}
