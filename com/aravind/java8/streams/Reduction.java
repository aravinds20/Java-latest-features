package com.aravind.java8.streams;

import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

/*
The reduction is a method that will reduce the stream into an object.
It is a reduction which means it will process all the elements.
The most common way to reduce is it to have an initial value and keep merging it with the next value.
 */
public class Reduction {

    public static void main(String[] args) {

        //T reduce(T identity, BinaryOperator<T> accumulator)
        //  BinaryOperator<T> functional method;
        //      T apply(T,T);

        //The "identity" is the initial value of the reduction. Which also is the return value if the stream is empty.
        //The "accumulator" is the current result with the initial value of the stream.

        String name = Stream.of("r","o","b","b","e","n").reduce("",(a,b)-> a+b);
        System.out.println(name); //robben

        long prod = Stream.of(4,3,2).reduce(1,(a,b)->a*b);
        System.out.println(prod); //24

        //This variation uses just the accumulator in the reduction.
        //When you leave the identity, an Optional is used as there may not be any data.
        Optional<Long> prodL = Stream.of(3l,4l,5l).reduce((a, b)-> a*b);
        prodL.ifPresent(System.out::println);
        BinaryOperator<Integer> op = (a,b)-> a+b;
        Stream<Integer> emptyStream = Stream.of();
        Stream<Integer> singleElement = Stream.of(1);
        Stream<Integer> multipleElements = Stream.of(1,2,3,4);

        emptyStream.reduce(op).ifPresent(System.out::println);
        singleElement.reduce(op).ifPresent(System.out::println);
        multipleElements.reduce(op).ifPresent(System.out::println);


        /*
        U reduce(T identity, BiFunction accumulator, BinaryOperator combiner)
        We use this variation when we want to perform intermediate reductions and combine their results.
        This is useful when working with parallel streams.
         */

        Integer totalStrLength = Stream.of("robben","arnold","arthur").reduce(
                0,
                (n, str) -> n + str.length(),
                (n1,n2)-> n1+ n2);
        System.out.println(totalStrLength);
    }
}
