package Formatter;

import main.Entity.Apple;

public class AppleSimpleFormatter implements AppleFormatter {
    @Override
    public String accpet(Apple apple) {
        return "An apple of "+apple.getWeight() + "g";
    }
}
