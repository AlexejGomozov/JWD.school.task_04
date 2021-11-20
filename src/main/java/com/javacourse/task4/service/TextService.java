package com.javacourse.task4.service;

import com.javacourse.task4.enity.TextComponent;
import com.javacourse.task4.exception.TextException;

import java.util.List;
import java.util.Map;

public interface TextService {

    List<TextComponent> paragraphSort(String document) throws TextException;

    String findSentenceLongestWord(TextComponent textComponent) throws TextException;

    String deleteSentenceCountOfSmallWords(TextComponent document, int count) throws TextException;

    Map<String, Integer> findAmountSameWords(TextComponent textComponent) throws TextException;

    long countVowel(TextComponent textComponent) throws TextException;
}
