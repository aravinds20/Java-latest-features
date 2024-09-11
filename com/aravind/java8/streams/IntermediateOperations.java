package com.aravind.java8.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/*
Unlike a terminal operation, the intermediate operation produces a stream as a result.
 */

public class IntermediateOperations {

    public static void main(String[] args) {

        //Stream<T> filter(Predicate)
        //returns a stream with the elements in a stream that match the predicate.
        System.out.println("------------------------Using filter for an intermediate operation------------------");
        Stream.of("Drogba","Lampard","Cole","Ballack").filter(s-> s.length()>6).forEach(System.out::println);

        //distinct() returns a stream with the duplicate elements removed.
        //equals() is used here, which is case sensitive.
        //It is a stateful intermediate operation where in it passes the element that it has not seen, remembers it;
        //if it has seen already, it filters it out. It also is case-sensitive.
        System.out.println("------------------------Using distinct for an intermediate operation------------------");
        Stream.of("Drogba","Lampard","Cole","Ballack","Lampard","lampard").peek(s-> System.out.println("1."+s))
                .distinct().forEach(s-> System.out.println("2."+s));

        System.out.println("------------------------Using limit for an intermediate operation------------------");
        //limit() is a short circuiting stateful intermediate operation.
        Stream.of(11,22,33,44,55,66,77,88,99).peek(s-> System.out.println("A."+s))
                .filter(n -> n > 50)
                .peek(n-> System.out.println("B."+n))
                .limit(2)
                .forEach(n -> System.out.println("C."+n));

        System.out.println("------------------------Using map as an intermediate operation------------------");
        //map() creates an one on one relationship between the elements in the stream and the elements in the next stage of the stream.
        //Functional method - R apply(T t)
        Stream.of("robben","ribery","Klose").map( s-> s.length()).forEach(System.out::println);

        System.out.println("------------------------Using flatMap as an intermediate operation------------------");
        //flatmap() takes one element in the stream of Stream<List<String>> and makes any elements it contains into top
        //level elements of a single stream.
        //flatmap(List<String>, Stream<String>)
        List<String> list1 = Arrays.asList("Ballack","Lampard");
        List<String> list2 = Arrays.asList("Lebouf","Dessaily");

        Stream<List<String>> streamOfLists = Stream.of(list1,list2);
        streamOfLists.flatMap(list-> list.stream()).forEach(System.out::println);

        System.out.println("------------------------Using sorted() as an intermediate operation------------------");
        Stream.of("Fletcher","Adrainno","Ibrahimovic","Zanetti")
                .peek(s->System.out.println("1."+s))
                .sorted(Comparator.comparing(s->s.length()))
                .peek(s->System.out.println("2."+s))
                .limit(3)
                .forEach(s -> System.out.println("3."+s));
    }
}
