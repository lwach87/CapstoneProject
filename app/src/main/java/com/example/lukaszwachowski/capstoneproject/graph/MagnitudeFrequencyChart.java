package com.example.lukaszwachowski.capstoneproject.graph;

//public class MagnitudeFrequencyChart {
//
//  private BarChart barChart;
//
//  public MagnitudeFrequencyChart(BarChart barChart) {
//    this.barChart = barChart;
//  }
//
//  public void getMagnitudeFrequencyChart(List<Feature> features) {
//
//    String xValues[] = {"2.0+", "3.0+", "4.0+", "5.0+", "6.0+", "7.0+"};
//
//    ArrayList<String> xData = new ArrayList<>();
//    Collections.addAll(xData, xValues);
//
//    ArrayList<BarEntry> yData = getYValues(features);
//
//    BarDataSet barDataSet = new BarDataSet(yData, "");
//    barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
//
//    BarData data = new BarData(xData, barDataSet);
//    barChart.setData(data);
//    barChart.setDescription("");
//    barChart.getAxisRight().setDrawLabels(false);
//    barChart.getLegend().setEnabled(false);
//    barChart.animateY(2000);
//  }
//
//  private ArrayList<BarEntry> getYValues(List<Feature> features) {
//
//    int mag2 = 0;
//    int mag3 = 0;
//    int mag4 = 0;
//    int mag5 = 0;
//    int mag6 = 0;
//    int mag7 = 0;
//
//    for (int i = 0; i < features.size(); i++) {
//
//      double magnitude = features.get(i).getProperties().getMag();
//
//      if (magnitude >= 2 && magnitude <= 2.99) {
//        mag2++;
//      } else if (magnitude >= 3 && magnitude <= 3.99) {
//        mag3++;
//      } else if (magnitude >= 4 && magnitude <= 4.99) {
//        mag4++;
//      } else if (magnitude >= 5 && magnitude <= 5.99) {
//        mag5++;
//      } else if (magnitude >= 6 && magnitude <= 6.99) {
//        mag6++;
//      } else if (magnitude >= 7 && magnitude <= 7.99) {
//        mag7++;
//      }
//    }
//
//    ArrayList<BarEntry> yData = new ArrayList<>();
//    yData.add(new BarEntry(mag2, 0));
//    yData.add(new BarEntry(mag3, 1));
//    yData.add(new BarEntry(mag4, 2));
//    yData.add(new BarEntry(mag5, 3));
//    yData.add(new BarEntry(mag6, 4));
//    yData.add(new BarEntry(mag7, 5));
//
//    return yData;
//  }
//}
