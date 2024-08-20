package com.aravind.java8.streams;

import java.util.stream.Stream;

public class StreamLaziness {


    //Streams are lazy. Meaning that if there are 10000 records to be shown to a user, the principle of lazy evaluation
    //would be to retrieve 50 records and retrieve the next 50 while the user is viewing the current 50 records.

    //Eager evaluation would be to retrieve all the 10000 records.
    public static void main(String[] args) {

        /* All the elements in the chain move vertically.
        Robben
        Robben
        Alex
        Alex
        Johnson
        Johnson
        Rooney
        Rooney

        This helps in reducing the number of operations which is - instead of mapping "Robben", "Alex", "Johnson",
        "Rooney" and then anymatch on "Robben" (5 operations in total), we process elements vertically which results in
        2 operations totally.
         */
//        Stream.of("Robben","Alex","Johnson","Rooney")
//                .filter(s -> {
//                    System.out.println(s);
//                    return true;
//                }).forEach(System.out::println);

        Stream.of("Robben","Alex","Johnson","Rooney")
                .map(s -> {
                    System.out.println("map - "+s);
                    return s.toUpperCase();
                })
                .anyMatch(s -> {
                    System.out.println("anyMatch - "+s);
                    return s.startsWith("R");
                });

        // Ex - 2 - With a stateful intermediate operation(limit) which means it actually stores the state of the operation,
        // ie., to keep a track of how many items to pass through.
        Stream.of("Robben","Alex","Johnson","Rooney")
                .filter(s -> {
                    System.out.println("filter 1 - "+s);
                    return s.startsWith("R") || s.startsWith("J");
                })
                .filter(s -> {
                    System.out.println("filter 2 - "+s);
                    return s.length() > 3;
                }).limit(1)
                .forEach(System.out::println);
    }
}