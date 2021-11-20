package com.javacourse.task4.enity;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class TextComposit implements TextComponent{
    private TextCompositeType textCompositeType;
    protected List<TextComponent> components = new ArrayList<>();

    public TextComposit(TextCompositeType textCompositeType){
        this.textCompositeType = textCompositeType;
    }
    @Override
    public void add(TextComponent component){
        components.add(component);
    }
    @Override
    public void remove(TextComponent component){
        components.remove(component);
    }
    @Override
    public List<TextComponent> getList(){
        return components;
    }
    @Override
    public Object getChild(int index){
        return components.get(index);
    }
    @Override
    public TextCompositeType getType(){
        return textCompositeType;
    }

    @Override
    public String toString(){
    StringJoiner stringJoiner = new StringJoiner(textCompositeType.getDelimiter(), textCompositeType.getPrefix(),"");
    for(TextComponent component : components){
        stringJoiner.add(component.toString());
    }
        return stringJoiner.toString();
    }

}
