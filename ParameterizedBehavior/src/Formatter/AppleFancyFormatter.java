package Formatter;

import main.Entity.Apple;

public class AppleFancyFormatter implements AppleFormatter {
    @Override
    public String accpet(Apple apple) {
        String characteristic =apple.getWeight()>150?"heavy":"light";
        return "A "+characteristic+" "+apple.getColor()+" apple";
    }
}
