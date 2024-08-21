package com.aravind.java8.streams;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class StreamCreation {

    public static void main(String[] args) {

        /*
        1. Arrays.stream() create the stream from the array of double numbers.
        The array is considered as the source of the stream and while the data is flowing through the stream, we
        have the opportunity to operate on the stream.
         */
        Double[] arr = {1.0,4.2,4.2};

        Stream<Double> stream = Arrays.stream(arr);

        /*
        Count is a terminal operation which means you cannot perform any more operations on the stream.
         */
        System.out.println(stream.count());

        /*The default collection interface method stream() is used to get the stream out of a collection.
        But this method cannot be inherited by all the classes. In case of a map, the entrySet() method
        */
        Map<String, Integer> map = new HashMap<>();
        map.put("Rooney",26); map.put("Drogba",30); map.put("Ronaldo",24);
        System.out.println("number of entries in the map "+map.entrySet().stream().count());

        /*
        2. Stream.of() is a generically typed utility method that accepts varags parameter and returns an ordered
        stream of those values.
         */

        Stream<Integer> streamI = Stream.of(1,2,3,4);
        System.out.println("Using .of operator - "+streamI.count());

        /*
        3. Infinite streams - using the generate method.
        Stream<T> generate(Supplier<T> s)
        Supplier is a functional interface
        T get();
         */
        Stream<Integer> streamInf = Stream.generate(()-> {
            return (int) (Math.random() * 10);
        });
        //keeps going until killed.
//        streamInf.forEach(System.out::println);

        //Using unary operator
        /*
        infinite stream of ordered numbers - 2,4,6,8,10...
        iterate(T seed, UnaryOperator<T> fn)
        Unary operator is-a function.
        T apply(T t)

        They can be limited using the limit operation which is a short-circuiting stateful intermediate operation.
         */

        Stream<Integer> infStream = Stream.iterate(2,n->n+2).limit(10);
        infStream.forEach(System.out::println);
    }

}
