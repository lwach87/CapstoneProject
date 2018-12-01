package com.example.lukaszwachowski.capstoneproject.ui.graph;

//public class Helper {
//
//  public static String place(String place) {
//
//    StringBuilder newPlace = new StringBuilder(place);
//
//    if (Character.isDigit(newPlace.charAt(2))) {
//      newPlace.delete(0, 6);
//    } else if (Character.isDigit(newPlace.charAt(1))) {
//      newPlace.delete(0, 5);
//    } else if (Character.isDigit(newPlace.charAt(0))) {
//      newPlace.delete(0, 4);
//    }
//
//    if (newPlace.toString().contains(",")) {
//      newPlace.delete(newPlace.toString().indexOf(","), newPlace.length());
//    }
//
//    String[] rose1 = {"NNW", "NNE", "ENE", "ESE", "SSE", "SSW", "WSW", "WNW"};
//
//    for (String s : rose1) {
//      if (newPlace.toString().contains(s)) {
//        newPlace.delete(0, 7);
//        break;
//      }
//    }
//
//    String[] rose2 = {"NW of", "NE of", "SE of", "SW of"};
//
//    for (String s : rose2) {
//      if (newPlace.toString().contains(s)) {
//        newPlace.delete(0, 6);
//        break;
//      }
//    }
//
//    String[] rose3 = {"N of", "E of", "S of", "W of"};
//
//    for (String s : rose3) {
//      if (newPlace.toString().contains(s)) {
//        newPlace.delete(0, 5);
//        break;
//      }
//    }
//
//    return newPlace.toString();
//  }
//}
