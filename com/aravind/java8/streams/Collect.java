package com.aravind.java8.streams;


import com.sun.source.tree.Tree;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
This is a special type of reduction called as mutable reduction because we use the same mutable object while
accumulating.

Common mutable objects are stringbuilder and arraylist.
It helps us get data out of streams into other forms such as Sets, Maps, and Lists.

StringBuilder collect(Supplier<StringBuilder> supplier,
    BiConsumer<StringBuilder, String> accumulator,
    BiConsumer<StringBuilder, StringBuilder> combiner)

    The accumulator appends the string to the stringBuilder, adds an item to the collection.
    The combiner takes two collections and merges them. Useful in parallel processing.

This is used when you want to complete control how accumulator should work.
 */
public class Collect {

    public static void main(String[] args) {

        String[] arr = {"a","r","v","i","n","d"};
        System.out.println(Stream.of(arr).collect(
                () -> new StringBuilder(),
                (sb, str) -> sb.append(str) ,
                (sb1,sb2) -> sb1.append(sb2)
        ));
        //collect Using API Collectors
        //We access Collectors via static methods on the Collectors interface.
        //It is important to pass the Collector to the collect method.
        System.out.println("-------------- Joining strings using collect -----------------");
        String s = Stream.of("cat","dog","elephant").collect(Collectors.joining(","));
        System.out.println(s);

        //-------------- using averaging functions in collect-----------------
        System.out.println("-------------- using averaging functions in collect-----------------");
        Double l = Stream.of("cat","dog","elephant").collect(Collectors.averagingInt(str -> str.length()));
        System.out.println(l);

        //collecting into maps.
        //requires two functions - 1 tells how to create the key and the second tells how to create the value.
        System.out.println("-------------- using toMap functions in collect-----------------");
        Map<String, Integer> map = Stream.of("cat","dog","elephant").collect(Collectors.toMap(str1->str1, str1->str1.length()));
        System.out.println(map);

        //opposite of the previous example
        //if two of the animals have the same length, then we can't have duplicate keys.
        //To get around this, we use the merge function whereby we append the colliding keys together.
        System.out.println("-------------- using toMap with a merger function in collect-----------------");
        Map<Integer, String> map1 = Stream.of("cat","dog","elephant").collect(Collectors.toMap(
                str -> str.length(), //key
                str -> str, //value
                (str1,str2) -> str1+", "+str2) //merge incase there are duplicate keys
        );
        System.out.println(map1);

        //We can use a TreeMap to apply sorting to the results of the map.
        System.out.println("-------------- using toMap with a merger function in collect with a treemap-----------------");
        TreeMap<String, Integer> map2 = Stream.of("Rooney","Drogba","Lampard","Drogba").collect(Collectors.toMap(
                str->str,
                String::length,
                (n1,n2) -> n1+n2,
                TreeMap::new
        ));
        System.out.println(map2);


        //collecting into maps using groupingBy.
        //groupingBy tells collect to group all elements into a map.
        //This takes a function which is a key and each value is a value from the list that matches the key.
        System.out.println("-------------- using toMap functions in collect with groupingBy -----------------");
        Map<Integer, Set<String>> map3 = Stream.of("cat","dog","elephant","cat","dragon")
                .collect(Collectors.groupingBy(
                        String::length,
                        Collectors.toSet()
                ));
        System.out.println(map3);
        System.out.println("-------------- using toMap functions in collect with grouping by and Tree map-----------------");
        TreeMap<Integer, Set<String>> treeMap3 = Stream.of("albatross","cat","dog","elephant","cat","dragon")
                .collect(Collectors.groupingBy(
                        String::length,
                        TreeMap::new,
                        Collectors.toSet()
                ));
        System.out.println(treeMap3);

        //Partitioning is a special case of grouping where only two groups exist - true and false.
        //It takes a predicate and the elements will be filtered into the groups that satisfy the predicate into true
        //and the others to the false group.
        System.out.println("-------------- using toMap functions in collect with partitionBy-----------------");
        Map<Boolean, List<String>> map4 = Stream.of("albatross","cat","dog","elephant","cat","dragon")
                .collect(Collectors.partitioningBy(str-> str.startsWith("a")
                ));
        System.out.println(map4);

        //testing it with an empty list.
        System.out.println("-------------- using toMap functions in collect with partitionBy, testing for empty list-----------------");
        Map<Boolean, List<String>> map5 = Stream.of("albatross","cat","dog","elephant","cat","dragon")
                .collect(Collectors.partitioningBy(str-> s.length()> 100)
                );
        System.out.println(map5);

        //using to Collectors.toSet to change the type of values from list to set.
        System.out.println("-------------- using toMap functions in collect with partitionBy, testing for empty list-----------------");
        Map<Boolean, Set<String>> map6 = Stream.of("albatross","cat","dog","elephant","cat","dragon")
                .collect(Collectors.partitioningBy(str-> s.length()> 100,
                        Collectors.toSet()
                ));
        System.out.println(map6);
    }
}
