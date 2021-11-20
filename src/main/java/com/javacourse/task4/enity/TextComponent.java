package com.javacourse.task4.enity;

import java.util.List;

public interface TextComponent {

  void add(TextComponent component);

  void remove(TextComponent component);

    List<TextComponent> getList();

    Object getChild(int index);

    TextCompositeType getType();

    String toString();
    }

