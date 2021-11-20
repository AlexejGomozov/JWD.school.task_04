package com.javacourse.task4.enity;

import java.util.List;

public class WordLeaf implements  TextComponent{

    private String word;


    public WordLeaf(String word){
        this.word = word;
    }

    @Override
      public void add(TextComponent component){
        throw new UnsupportedOperationException("Method add is unsupported for class" + getClass());
    }
    @Override
      public void remove(TextComponent component){
        throw new UnsupportedOperationException("Method remove is unsupported for class" + getClass());
    }

    @Override
      public List<TextComponent> getList() {
        throw new UnsupportedOperationException("Method getList is unsupported for class" + getClass());
    }

    @Override
      public Object getChild(int index){
        throw new UnsupportedOperationException("Method getChild is unsupported for class" + getClass());
    }
    @Override
      public TextCompositeType getType(){
        return TextCompositeType.WORD;
    }

    @Override
      public boolean equals(Object o) {
        if (this == o) return true;
        if(!(o instanceof WordLeaf)) return false;
        WordLeaf wordLeaf1 = (WordLeaf) o;
        return word == wordLeaf1.word;
    }

    @Override
      public int hashCode() {
        int result = 1;
        return result*31 + word.hashCode();
    }

    @Override
      public String toString(){
        return String.valueOf(word);
    }
  }
