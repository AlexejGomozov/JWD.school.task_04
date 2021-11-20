package com.javacourse.task4.enity;

import java.util.List;

public class PunctuationLeaf implements  TextComponent{
        private String symbol;

        public PunctuationLeaf(String symbol){
            this.symbol = symbol;
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
            return TextCompositeType.PUNCTUATION;
        }
        @Override
        public String toString(){
            return String.valueOf(symbol);
        }
    }

