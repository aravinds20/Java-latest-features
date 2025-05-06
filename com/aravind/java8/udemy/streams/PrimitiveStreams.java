package com.aravind.java8.udemy.streams;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class PrimitiveStreams {

    public static void main(String[] args) {

        //Java also provides other stream classes to work with primitives.
        //IntStream - for int, byte, short and char
        //DoubleStream - double and float.
        //LongStream - long.

        //IntStream - Stream<Integer>
        //DoubleStream - Stream<Double>
        //LongStream - Stream<Long>

        int[] ia = {1,2,3};
        double[] da = {1.1,2.2,3.3};
        long[] la = {1l,2l,3l};

        IntStream intStream = Arrays.stream(ia);
        DoubleStream doubleStream = Arrays.stream(da);
        LongStream longStream = Arrays.stream(la);

//        System.out.println(intStream.count()+" - "+doubleStream.count()+" - "+longStream.count());

        System.out.println("---------------------Reading elements from Intstream created using Arrays.stream()---------------------");
        intStream.forEach(System.out::println);

        IntStream intStream1 = IntStream.of(1,2,3);
        DoubleStream doubleStream1 = DoubleStream.of(1.1,2.2,3.3);
        LongStream longStream1 = LongStream.of(1l,2l,3l);

        System.out.println("---------------------Reading elements from Intstream created using IntStream.of()---------------------");
        doubleStream1.forEach(System.out::println);

        Stream<Integer> integerStream = Stream.of(1,2,3);
        System.out.println(integerStream.reduce(0,(n1,n2) -> n1+n2));

        System.out.println("---------------------Performing sum using IntStream.sum()---------------------");
        IntStream intStream2 = Stream.of(1,2,3).mapToInt(n->n);
        //IntStream mapToInt(ToIntFunction) -> ToIntFunction is a functional interface.
        //int applyAsInt(T value)

        IntStream intStream3 = Stream.of(1,2,3,4,5).mapToInt(n->n);
        IntSummaryStatistics summaryStatistics = intStream3.summaryStatistics();
        System.out.println(summaryStatistics.getMax());
        System.out.println(summaryStatistics.getMin());
        System.out.println(summaryStatistics.getAverage());
        System.out.println(summaryStatistics.getCount());
    }

}
