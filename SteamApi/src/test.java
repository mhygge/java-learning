import org.junit.Test;

import java.util.*;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.partitioningBy;

/**
 * @program: j8test
 * @description: 测试
 * @author: limeng
 * @create: 2020-07-06 14:18
 **/
public class test {
    List<Dish> menu = Arrays.asList(
            new Dish("pork",false,800,Dish.Type.MEAT),
            new Dish("beef",false,700, Dish.Type.MEAT),
            new Dish("chicken",false,400, Dish.Type.MEAT),
            new Dish("french fries",true,530, Dish.Type.OTHER),
            new Dish("rice",true,350, Dish.Type.OTHER),
            new Dish("season fruit",true,120, Dish.Type.OTHER),
            new Dish("pizza",true,550, Dish.Type.OTHER),
            new Dish("prawns",false,300, Dish.Type.FISH),
            new Dish("salmon",false,450, Dish.Type.FISH)
    );
    public enum CaloricLevel { DIET, NORMAL, FAT }
    @Test
    public void streamBrief() {
        List<String> threeHighCaloricDishNames =
                menu.stream()
                .filter(d -> d.getCalories()>300)
                .map(Dish::getName)
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(threeHighCaloricDishNames);
    }

    @Test
    public void skipElement() {
        List<Dish> dishes = menu.stream()
                .filter(dish -> dish.getCalories()>300)
                .skip(2)
                .collect(Collectors.toList());
        System.out.println(dishes);
    }

    @Test
    public void dishNameLengths() {
        List<Integer> dishNameLengths = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println(dishNameLengths);
    }

    @Test
    public void flatStream() {
        String[] words = {"Hello","World"};
        Stream<String> wordsStream = Arrays.stream(words);
       List list = wordsStream.map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(list.toString());
    }

    @Test
    public void ifAnyMatch() {
        if(menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is vegetarian friendly!!");
        }
    }

    @Test
    public void ifAllAndNoneMatch() {
        System.out.println(menu.stream().allMatch(dish -> dish.getCalories() < 1000));
        System.out.println(menu.stream().noneMatch(dish -> dish.getCalories() >= 1000));
    }
    @Test
    public void findDish() {
        Optional<Dish> dish =
                menu.stream()
                .filter(Dish::isVegetarian)
                .findAny();
        System.out.println(dish.get());

        menu.stream()
                .filter(Dish::isVegetarian)
                .findAny()
                .ifPresent(d -> System.out.println(d.getName()));
        List<Integer> someNumber = Arrays.asList(1,2,3,4,5);
        Optional<Integer> firstSquareDivisibleByThree =
                someNumber.stream()
                .map(x -> x * x)
                .filter(x -> x % 3 == 0)
                .findFirst();
        System.out.println(firstSquareDivisibleByThree.get());
    }
    @Test
    public void collectDish() {
        Optional<Integer> sum =menu.stream().map(dish -> dish.getCalories()).reduce((a, b) -> (a + b));
        System.out.println(sum.get());
        int sum1 =menu.stream().map(dish -> dish.getCalories()).reduce(0,Integer::sum);
        System.out.println(sum1);

        Optional<Integer> max = menu.stream().map(dish -> dish.getCalories()).reduce(Integer::max);
        System.out.println(max.get());
    }
    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario","Milan");
    Trader alan = new Trader("Alan","Cambridge");
    Trader brian = new Trader("Brian","Cambridge");
    List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );
    @Test
    public void trader1() {
        List<Transaction> tr2011 = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println(tr2011.toString());
        List<String> cities = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(cities.toString());
        Set<String> citySet = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .collect(Collectors.toSet());
        System.out.println(citySet.toString());
        List<Trader> traders = transactions.stream()
                .map(transaction -> transaction.getTrader())
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println(traders.toString());

        //效率不高
        String traderStr = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (n1 ,n2) -> n1 + n2);
        System.out.println(traderStr);

        String traderStr1 = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining());
        System.out.println(traderStr1);

        boolean milanBased = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        System.out.println(milanBased);

        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(transaction -> transaction.getValue())
                .forEach(System.out::println);

        Optional<Integer> highestValue = transactions.stream()
                .map(transaction -> transaction.getValue())
                .reduce(Integer::max);
        System.out.println(highestValue.get());

        Optional<Transaction> smallestTranscation = transactions.stream()
                .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);
        System.out.println(smallestTranscation);

        Optional<Transaction> smallestTranscation1 = transactions.stream()
                .min(Comparator.comparing(Transaction::getValue));
        System.out.println(smallestTranscation1);
    }

    @Test
    public void baseStream() {
        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
        Stream<Integer> stream =intStream.boxed();
        System.out.println(stream);

        OptionalInt maxCalories = menu.stream()
                .mapToInt(Dish::getCalories)
                .max();
        System.out.println(maxCalories.getAsInt());
    }

    @Test
    public void pythagorean() {
        Stream<int []> pythagoreanTriples =
                IntStream.rangeClosed(1,100).boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a,100)
                        .filter(b -> Math.sqrt(a*a + b*b) % 1 ==0)
                        .mapToObj(b ->
                                new int[] {a, b, (int)Math.sqrt(a * a + b * b)})
                );
        pythagoreanTriples.limit(5)
                .forEach(t -> System.out.println(t[0]+", "+t[1]+", "+t[2]));

        Stream<double[]> pyshagoreanTriple2 =
                IntStream.rangeClosed(1,100).boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a,100)
                        .mapToObj(b ->
                                new double[] {a, b, Math.sqrt(a * a + b * b)})
                        .filter(t -> t[2] % 1 == 0)
                );
        pyshagoreanTriple2.limit(5)
                .forEach(t -> System.out.println(t[0]+", "+t[1]+", "+t[2]));

    }

    @Test
    public void createStream() {
        Stream<String> stream = Stream.of("Java8","Lambdas","In","Action");
        stream.map(String::toUpperCase).forEach(System.out::println);

        Stream<String> emptyStream = Stream.empty();
        System.out.println(emptyStream.count());

        int [] numbers = {2, 3, 5, 7, 11, 13};
        int sum = Arrays.stream(numbers).sum();
        System.out.println(sum);

        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);
        Stream.iterate(new int[]{0, 1},t -> new int[]{t[1],t[0]+t[1]})
                .limit(20)
                .forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);

        //斐波那契generate实现,实际不推荐使用
        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private  int current = 1;
            @Override
            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.current + this.previous;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };
        IntStream.generate(fib).limit(10).forEach(System.out::println);
    }

    @Test
    public void collectTest() {
//        long howManyDishes = menu.stream().collect(Collectors.counting());
//        long howManyDishes = menu.stream().count();
//        System.out.println(howManyDishes);
//
//        Comparator<Dish> dishCaloriesComparator =
//                Comparator.comparingInt(Dish::getCalories);
//        Optional<Dish> mostCaloriesDish =
//                menu.stream().collect(Collectors.maxBy(dishCaloriesComparator));
//        System.out.println(mostCaloriesDish.get());

//        int totalCalories = menu.stream().collect(Collectors.summingInt(Dish::getCalories));
//        System.out.println(totalCalories);
//
//        double avgCalories =
//                menu.stream().collect(Collectors.averagingInt(Dish::getCalories));
//        System.out.println(avgCalories);
//
//        IntSummaryStatistics menuStatistics =
//                menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
//        System.out.println(menuStatistics);

//        String shortMenu = menu.stream().map(Dish::getName).collect(Collectors.joining());
//        String shortMenu = menu.stream().map(Dish::getName).collect(Collectors.joining(", "));
//        System.out.println(shortMenu);
//
//        int totalCalories = menu.stream().collect(Collectors.reducing(0,Dish::getCalories,(i,j)->i+j));
//        System.out.println(totalCalories);
//
//        Optional<Dish> mostCaloriesDish =
//                menu.stream().collect(Collectors.reducing((d1,d2)-> d1.getCalories()>d2.getCalories()?d1:d2));
//        System.out.println(mostCaloriesDish.get());

//        int totalCalories =
//                menu.stream().collect(Collectors.reducing(0,Dish::getCalories,Integer::sum));
//        System.out.println(totalCalories);
//        Map<Dish.Type,List<Dish>> dishesType =
//                menu.stream().collect(Collectors.groupingBy(Dish::getType));
//        System.out.println(dishesType);
//        Map<CaloricLevel,List<Dish>> dishesByCaloricLevel =
//                menu.stream().collect(
//                        Collectors.groupingBy(dish -> {
//                            if(dish.getCalories() <= 400)
//                                return CaloricLevel.DIET;
//                            else if(dish.getCalories() <= 700)
//                                return CaloricLevel.NORMAL;
//                            else
//                                return CaloricLevel.FAT;
//                        })
//                );
//        System.out.println(dishesByCaloricLevel);
//        Map<Dish.Type, Map<CaloricLevel,List<Dish>>> dishesByTypeCaloricLevel =
//                menu.stream().collect(
//                        Collectors.groupingBy(Dish::getType,
//                                Collectors.groupingBy(dish -> {
//                                    if(dish.getCalories() <= 400)
//                                        return CaloricLevel.DIET;
//                                    else if(dish.getCalories() <= 700)
//                                        return CaloricLevel.NORMAL;
//                                    else
//                                        return CaloricLevel.FAT;
//                                }))
//                );
//        System.out.println(dishesByTypeCaloricLevel);
//
//        Map<Dish.Type,Long> typesCount = menu.stream().collect(
//                Collectors.groupingBy(Dish::getType, Collectors.counting())
//        );
//        System.out.println(typesCount);
//
//        Map<Dish.Type,Optional<Dish>> mostCaloricByType =
//                menu.stream().collect(Collectors.groupingBy(Dish::getType,
//                        Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))));
//        System.out.println(mostCaloricByType);

//       Map<Dish.Type, Dish> mostCaloricByType = menu.stream().collect(
//               groupingBy(Dish::getType,
//                       Collectors.collectingAndThen(
//                               Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),
//                               Optional::get
//                       ))
//       );
//        System.out.println(mostCaloricByType);
//
//        Map<Dish.Type, Integer> totalCaloriesByType =
//                menu.stream().collect(groupingBy(Dish::getType,
//                        summingInt(Dish::getCalories)));
//        System.out.println(totalCaloriesByType);
//
//        Map<Dish.Type,Set<CaloricLevel>> caloricLevelsByType =
//                menu.stream().collect(
//                        groupingBy(Dish::getType, mapping(
//                                dish -> {
//                                    if(dish.getCalories()<=400)
//                                        return CaloricLevel.DIET;
//                                    else if(dish.getCalories()<=700)
//                                        return CaloricLevel.NORMAL;
//                                    else
//                                        return CaloricLevel.FAT;
//                                },
//                                toSet()
//                        ))
//                );
//        System.out.println(caloricLevelsByType);

//        Map<Dish.Type,Set<CaloricLevel>> caloricLevelsByType =
//                menu.stream().collect(
//                        groupingBy(Dish::getType,mapping(
//                                dish -> {
//                                    if(dish.getCalories()<=400)
//                                        return CaloricLevel.DIET;
//                                    else if(dish.getCalories()<=700)
//                                        return CaloricLevel.NORMAL;
//                                    else
//                                        return CaloricLevel.FAT;
//                                },
//                                toCollection(HashSet::new)
//                        ))
//                );
//        System.out.println(caloricLevelsByType);
       Map<Boolean,List<Dish>> partitionedMenu =
       menu.stream().collect(partitioningBy(Dish::isVegetarian));
        System.out.println(partitionedMenu);
    }


}
