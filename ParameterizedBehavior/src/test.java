import main.Entity.Apple;
import Formatter.AppleFormatter;
import Predicate.ApplePredicate;
import Predicate.Predicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test {





    public static List<Apple> filterGreenApples(List<Apple> inventory){
        List<Apple> result=new ArrayList<Apple>();
        for (Apple apple: inventory){
            if("green".equals(apple.getColor())){
                result.add(apple);
            }
        }
        return result;
    }
    public static List<Apple> filterApplesByColor(List<Apple> inventory,String color){
        List<Apple> result=new ArrayList<>();
        for (Apple apple:inventory){
            if(apple.getColor().equals(color)){
                result.add(apple);
            }
        }
        return result;
    }
    public static List<Apple> filterApples(List<Apple>inventory, ApplePredicate p){
        List<Apple> result=new ArrayList<>();
        for (Apple apple:inventory){
            if(p.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }
    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter formatter){
        for (Apple apple:inventory){
            String output=formatter.accpet(apple);
            System.out.println(output);
        }
    }
    public static <T>List<T> filter(List<T> list,Predicate<T> p){
        List<T> result=new ArrayList<>();
        for (T e:list){
            if(p.test(e)){
                result.add(e);
            }
        }
        return result;
    }
    public static void main(String [] args){
        List<Apple> inventory=new ArrayList<Apple>();
        String endl="\n";
        Apple a1=new Apple();
        a1.setColor("blue");
        a1.setWeight(100);
        Apple a2=new Apple();
        a2.setColor("green");
        a2.setWeight(200);
        Apple a3=new Apple();
        a3.setColor("red");
        a3.setWeight(180);
        inventory.add(a1);
        inventory.add(a2);
        inventory.add(a3);
        //List<main.Entity.Apple> result=filterGreenApples(inventory);
        //List<main.Entity.Apple> result=filterApplesByColor(inventory,"blue");
//        ApplePredicate p=new AppleHeavyWeightPredicate();
//        List<Apple> result=filterApples(inventory,p);
//        System.out.println(result.contains(a1)+"\n"+result.contains(a2));
//        AppleFormatter formatter=new AppleFancyFormatter();
//        AppleFormatter formatter=new AppleSimpleFormatter();
//        prettyPrintApple(inventory, formatter);
     /*   List<Apple> redApples=filterApples(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                 return "red".equals(apple.getColor());
            }
        });*/
        List<Apple> redApples=
                filterApples(inventory,(Apple apple)->"red".equals(apple.getColor()));
        List<Integer>numbers= Arrays.asList(1,2,3);
        List<Integer> evenNumbers=filter(numbers,(Integer i)->i%2==0);
        System.out.println(redApples.contains(a1)+endl+redApples.contains(a2)+endl+redApples.contains(a3));
        System.out.println(evenNumbers.toString());
    }
}
