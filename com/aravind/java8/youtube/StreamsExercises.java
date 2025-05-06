package com.aravind.java8.youtube;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * An exercise of the questions from the video from java techie - https://www.youtube.com/watch?v=eBDN04LlEOg
 * Find the nth highest salary from map
 */
public class StreamsExercises {

  public static void main(String[] args) {

    Map<String, Integer> map = new HashMap<>() {{
      put("Rooney",23000);
      put("Enke",15000);
      put("Ballack",18000);
      put("Drogba",21000);
      put("Essien",18000);
      put("Torres",22000);
      put("Ramires",22000);
    }};
    Map.Entry<Integer, List<String>> integerListEntry = map.entrySet().stream().collect(
        Collectors.groupingBy(Map.Entry::getValue, Collectors.mapping(Map.Entry::getKey, Collectors.toList())))
      .entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByKey())).toList().get(1);
    System.out.println(integerListEntry);
  }

  public void getNthHighestSalary(int num, Map map) {
  }

}
