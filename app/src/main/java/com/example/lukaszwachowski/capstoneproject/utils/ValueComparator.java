package com.example.lukaszwachowski.capstoneproject.utils;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ValueComparator {

  public static Map<String, Double> sortByValue(Map<String, Double> unsortedMap) {

    List<Map.Entry<String, Double>> list = new LinkedList<>(unsortedMap.entrySet());

    Collections.sort(list, (o1, o2) -> (o1.getValue()).compareTo(o2.getValue()));

    Map<String, Double> sortedMap = new LinkedHashMap<>();
    for (Map.Entry<String, Double> entry : list) {
      sortedMap.put(entry.getKey(), entry.getValue());
    }
    return sortedMap;
  }

}
