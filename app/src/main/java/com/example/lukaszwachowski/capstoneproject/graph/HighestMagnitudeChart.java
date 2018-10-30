package com.example.lukaszwachowski.capstoneproject.graph;

//public class HighestMagnitudeChart {
//
//  private HorizontalBarChart barChart;
//
//  public HighestMagnitudeChart(HorizontalBarChart barChart) {
//    this.barChart = barChart;
//  }
//
//  public void getHighestMagnitudeChart(List<Feature> features) {
//
//    Collections
//        .sort(features, (f1, f2) -> (f2.getProperties().getMag()).compareTo(f1.getProperties().getMag()));
//
//    ArrayList<String> xData = new ArrayList<>();
//
//    for (int j = 0; j < 6; j++) {
//      String place = Helper.place(features.get(j).getProperties().getPlace());
//      xData.add(place);
//    }
//
//    ArrayList<BarEntry> yData = new ArrayList<>();
//
//    for (int i = 0; i < 6; i++) {
//      double dMag = features.get(i).getProperties().getMag();
//      yData.add(new BarEntry((float) dMag, i));
//    }
//
//    BarDataSet barDataSet = new BarDataSet(yData, "");
//    barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
//
//    BarData data = new BarData(xData, barDataSet);
//    barChart.setData(data);
//    barChart.setDescription("");
//    barChart.getAxisRight().setDrawLabels(false);
//    barChart.getAxisLeft().setValueFormatter((value) -> String.valueOf((int) Math.floor(value)));
//    barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
//    barChart.getLegend().setEnabled(false);
//    barChart.animateY(2000);
//  }
//}
