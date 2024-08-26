package com.aravind.java8.streams;


/*
Terminal operations - these can be performed with any intermediate operations but not the other way around.
The reduction is a special kind of terminal operation, where all the contents of a stream are reduced to a
primitive or a collection object.
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class TerminalOperations {

    public static void main(String[] args) {

        /*
        Min(Comparator), Max(Comparator) - The optional was introduced in java 8 to replace null. If the stream is empty then the Optional will
        will be empty.
        */

        Optional<String> min = Stream.of("asd","das","das").min((s1,s2) -> s1.length() -s2.length());
        min.ifPresent(System.out::println);

        Optional<String> max = Stream.of("asd","das","daadss").max(Comparator.comparingInt(String::length));
        max.ifPresent(System.out::println);

        long count = Stream.of("asd","das","daadss").count();
        System.out.println(count);

        /*
        Optional<T> findAny()
        Optional<T> findFirst()
        These are terminal operations but not reductions as they do not process all the elements before returning.
        Reductions reduce entire stream into one value and they return Optional.
         */

        Optional<String> findAny = Stream.of("asd","das","daadss").findAny();
        findAny.ifPresent(System.out::println);

        Optional<String> findFirst = Stream.of("asd","das","daadss").findFirst();
        findAny.ifPresent(System.out::println);

        /*
        FindAny is a terminal and you need to restream the source which is the contents of the stream
        to use findFirst again.
         */

        /*
        boolean anyMatch(Predicate), allMatch(Predicate), noneMatch(Predicate)
        these are all predicates as they return boolean.
         */

        List<String> list = Arrays.asList("Paul","Allen","Fischer");
        Predicate<String> p = s->s.startsWith("P");
        System.out.println(list.stream().anyMatch(p));
        System.out.println(list.stream().allMatch(p));
        System.out.println(list.stream().noneMatch(p));

        /*
        void forEach(Consumer)
        No return value and forEach is not a reduction.
        If there is something to happen, then it must happen inside a consumer(side effect).
         */

        Stream<String> str = Stream.of("Paul","Allen","Fischer");
        str.forEach(System.out::println);

        /*
        Streams cannot be the source of the foreach loop as Streams do not implement the Iterable interace.
        ForEach is a method in the collection interface.
         */
        Stream<Integer> s = Stream.of(1);
//        for(Integer i : s) {} //error : required array or an iterable.
    }

}
