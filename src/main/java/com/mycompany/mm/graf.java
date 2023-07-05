package com.mycompany.mm;

//@author Jesus
import java.awt.BasicStroke;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class graf {

    public JFreeChart grafica(ArrayList<ArrayList> d, int op) {
        XYLineAndShapeRenderer renderer;
        XYPlot plot;
        XYSeries sSeries = new XYSeries("Observado");
        XYSeries iSeries = new XYSeries("Prediccion");

        String[] mes = {
            "Febrero", "Marzo", "Abril", "Mayo", "Junio",
            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre",
            "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre",
            "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        };
        for (int x = 0; x < d.size(); x++) {
            if ((double) d.get(x).get(0) != -1.0) {
                sSeries.add(x + 1, (double) d.get(x).get(0));
            }
            iSeries.add(x + 1, (double) d.get(x).get(1));
        }
        
        XYSeriesCollection oDataset = new XYSeriesCollection();
        JFreeChart oChart = ChartFactory.createXYLineChart("Prediccion de volumen de lluvia 2020-2022", "mes", "Lluvia (mm)", oDataset, PlotOrientation.VERTICAL, true, false, false);
        plot = oChart.getXYPlot();
        renderer = new XYLineAndShapeRenderer();
        
        switch (op) {
            case 1 -> {
                oDataset.addSeries(sSeries);
                renderer.setSeriesPaint(0, Color.BLUE);
                renderer.setSeriesStroke(0, new BasicStroke(0.5f));
            }
            case 2 -> {
                oDataset.addSeries(iSeries);
                renderer.setSeriesPaint(0, Color.RED);
                renderer.setSeriesStroke(0, new BasicStroke(0.5f));
            }
            case 3 -> {
                renderer.setSeriesPaint(0, Color.GREEN);
                renderer.setSeriesStroke(0, new BasicStroke(0.5f));
            }
            default -> {
                oDataset.addSeries(sSeries);
                oDataset.addSeries(iSeries);
                renderer.setSeriesPaint(0, Color.BLUE);
                renderer.setSeriesPaint(1, Color.RED);
                renderer.setSeriesPaint(2, Color.GREEN);
                renderer.setSeriesStroke(0, new BasicStroke(0.5f));
                renderer.setSeriesStroke(1, new BasicStroke(0.5f));
                renderer.setSeriesStroke(2, new BasicStroke(0.5f));

            }
        }
        return oChart;

    }
}
