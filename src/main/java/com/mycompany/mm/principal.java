package com.mycompany.mm;

import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

public class principal extends javax.swing.JFrame {

    private final DecimalFormat decimal = new DecimalFormat();

    double[] lluvia;

    public void modelo(double xt[], double alfa, double beta, double delta) {
        try {
            ArrayList cal = new ArrayList();
            ArrayList<ArrayList> Dcal = new ArrayList();
            ArrayList cal2 = new ArrayList();
            ArrayList cal3 = new ArrayList();
            double[] xd = new double[23];
            double Lt = 101.41;
            double[] St = {
                0.197441487,
                0.131298589,
                0.050347579,
                0.202377525,
                0.989181852,
                2.271564312,
                1.393936901,
                1.754267615,
                3.120562708,
                1.17082802,
                0.335650529,
                0.172761301
            };
            
            double Tt = 0.114076918;
            double x;
            int t;
            xt[0] = 101.29;

            for (int i = 1; i < 12; i++) {
                double lt_1 = Lt;
                double tt = Tt;
                double s = St[i];

                Lt = ((alfa * (xt[i]) / s)) + ((1 - alfa) * (lt_1 + Tt));
                Tt = (beta * (Lt - lt_1)) + ((1 - beta) * Tt);
                St[i - 1] = ((xt[i] * delta) / Lt) + ((1 - delta) * s);
                x = (lt_1 + tt) * St[i];
                cal3 = new ArrayList();
                cal3.add(xt[i]);
                cal3.add(x);
                Dcal.add(cal3);
            }

            for (int i = 12; i < 24; i++) {
                double lt_1 = Lt;
                double s = St[i - 12];
                double tt = Tt;
                Lt = ((alfa * (xt[i - 12]) / s)) + ((1 - alfa) * (lt_1 + Tt));
                Tt = (beta * (Lt - lt_1)) + ((1 - beta) * tt);
                St[i - 12] = ((xt[i] * delta) / Lt) + ((1 - delta) * s);
                xd[i - 12] = St[i - 12];
                x = (lt_1 + (1 * tt)) * s;
                //System.out.println("xd    " + Lt + "tt   " + Tt);
                //System.out.println(xd[i - 12]);
                cal = new ArrayList();
                cal.add(xt[i]);
                cal.add(x);
                Dcal.add(cal);
            }

            for (int i = 1; i < 13; i++) {
                x = (Lt + (i * Tt)) * St[i - 1];
                cal2 = new ArrayList();
                cal2.add(-1.0);
                cal2.add(x);
                Dcal.add(cal2);
            }

            graf g = new graf();
            JFreeChart oChart = g.grafica(Dcal, 0);
            ChartPanel chartPanel = new ChartPanel(oChart);
            grafica.setLayout(new java.awt.BorderLayout());
            grafica.add(chartPanel, java.awt.BorderLayout.CENTER);
            grafica.setVisible(true);
            tabla(Dcal);
            initComponents();
        } catch (NumberFormatException e) {
        }
    }

    public void tabla(ArrayList<ArrayList> arr) {
        DefaultTableModel modelo = new DefaultTableModel();
        String datos[] = new String[3];
        modelo.addColumn("Meses");
        modelo.addColumn("Lluvia mm");
        modelo.addColumn("pronostico");

        String[] mes = {
            "2020-Enero", "2020-Febrero", "2020-Marzo", "2020-Abril", "2020-Mayo", "2020-Junio",
            "2020-Julio", "2020-Agosto", "2020-Septiembre", "2020-Octubre", "2020-Noviembre", "2020-Diciembre",
            "2021-Enero", "2021-Febrero", "2021-Marzo", "2021-Abril", "2021-Mayo", "2021-Junio",
            "2021-Julio", "2021-Agosto", "2021-Septiembre", "2021-Octubre", "2021-Noviembre", "2021-Diciembre",
            "2022-Enero", "2022-Febrero", "2022-Marzo", "2022-Abril", "2022-Mayo", "2022-Junio",
            "2022-Julio", "2022-Agosto", "2022-Septiembre", "2022-Octubre", "2022-Noviembre", "2022-Diciembre"
        };

        for (int x = 0; x < arr.size(); x++) {
            datos[0] = mes[x + 1];
            datos[1] = decimal.format((Double) arr.get(x).get(0));
            datos[2] = decimal.format((Double) arr.get(x).get(1));
            modelo.addRow(datos);
        }
        tabla.setModel(modelo);
    }

    public principal() {
        this.lluvia = new double[]{26.0, 8.8, 5.0, 16.9, 122.8, 318.9, 132.1, 231.1, 249.7, 94.6, 25.0, 5.9, 20.0, 13.3, 5.1, 20.5, 100.2, 230.1, 141.2, 177.7, 316.1, 118.6, 34.0, 17.5};
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        grafica = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("xd");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(0, 153, 153));
        setForeground(new java.awt.Color(0, 204, 204));
        setMinimumSize(new java.awt.Dimension(300, 300));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Bell MT", 3, 36)); // NOI18N
        jLabel1.setText("PREDICCION DE LLUVIA");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(207, 6, 516, -1));

        jButton1.setBackground(new java.awt.Color(102, 102, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jButton1.setText("GRAFICAR");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 430, 140, 50));

        tabla.setBackground(new java.awt.Color(153, 204, 255));
        tabla.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "año", "lluvia mm", "prediccion"
            }
        ));
        jScrollPane1.setViewportView(tabla);
        if (tabla.getColumnModel().getColumnCount() > 0) {
            tabla.getColumnModel().getColumn(0).setHeaderValue("año");
            tabla.getColumnModel().getColumn(1).setHeaderValue("lluvia mm");
            tabla.getColumnModel().getColumn(2).setHeaderValue("prediccion");
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 64, 330, 334));

        grafica.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        grafica.setForeground(new java.awt.Color(204, 255, 204));
        grafica.setPreferredSize(new java.awt.Dimension(5, 5));

        javax.swing.GroupLayout graficaLayout = new javax.swing.GroupLayout(grafica);
        grafica.setLayout(graficaLayout);
        graficaLayout.setHorizontalGroup(
            graficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 494, Short.MAX_VALUE)
        );
        graficaLayout.setVerticalGroup(
            graficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 294, Short.MAX_VALUE)
        );

        getContentPane().add(grafica, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 100, 500, 300));

        jLabel2.setText("Mediante el modelo Holt-Winters");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 53, 187, 32));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        modelo(lluvia, 0.006, 0.949, 0.9003);

    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel grafica;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
