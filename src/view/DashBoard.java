/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * Principal.java
 *
 * Created on 05-abr-2013, 20:15:45
 */
package view;

import com.sun.javafx.geom.transform.BaseTransform;
import java.awt.Image;
import static java.lang.Thread.sleep;
import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Administrador
 */
public class DashBoard extends javax.swing.JFrame implements Runnable {

    /**
     * Creates new form Principal
     */
    public static String hora, minutos, segundos, ampm;
    Calendar calendario;
    Thread h1;

    public DashBoard() {
        initComponents();

        h1 = new Thread(this);
        h1.start();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        jMenuBar1.setVisible(true);
        
        /*
        inicializacion main
         Conexion con = new Conexion();
        con.conectar();
        
        DashBoard dashBoard = new DashBoard();
        dashBoard.setVisible(true);
        */
    }

    /* public void init() {
        ImageIcon imagen1 = new ImageIcon(getClass().getResource("/icons/costos.png"));
        Icon fondo1 = new ImageIcon(imagen1.getImage().getScaledInstance(lb_auditoria.getWidth(), lb_auditoria.getHeight(), Image.SCALE_DEFAULT));
        lb_auditoria.setIcon(fondo1);
        this.repaint();
    }
     */
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jdpescritorio = new javax.swing.JDesktopPane();
        reloj2 = new javax.swing.JLabel();
        lb_auditoria = new javax.swing.JLabel();
        text_auditoria = new javax.swing.JLabel();
        lb_contabilidad = new javax.swing.JLabel();
        texto_contabilidad = new javax.swing.JLabel();
        lb_costos = new javax.swing.JLabel();
        texto_costos = new javax.swing.JLabel();
        lb_tributacion = new javax.swing.JLabel();
        texto_tributacion = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblusu = new javax.swing.JLabel();
        ho = new javax.swing.JLabel();
        reloj = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menunuevouser = new javax.swing.JMenuItem();
        salir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        estacionamientos = new javax.swing.JMenuItem();
        registro_preguntas = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        consultasClientes = new javax.swing.JMenuItem();
        reportecliente = new javax.swing.JMenu();
        menuClientesSi = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("OLIMPIADAS CONTABLES UTC");

        jdpescritorio.setBackground(new java.awt.Color(25, 153, 153));

        reloj2.setFont(new java.awt.Font("Arial Narrow", 1, 60)); // NOI18N
        reloj2.setForeground(new java.awt.Color(255, 255, 255));
        reloj2.setText("jLabel11");
        jdpescritorio.add(reloj2);
        reloj2.setBounds(490, 470, 290, 80);

        lb_auditoria.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_auditoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/auditoria_1.png"))); // NOI18N
        lb_auditoria.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lb_auditoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_auditoriaMouseClicked(evt);
            }
        });
        jdpescritorio.add(lb_auditoria);
        lb_auditoria.setBounds(250, 220, 130, 130);

        text_auditoria.setFont(new java.awt.Font("Arial Narrow", 1, 22)); // NOI18N
        text_auditoria.setForeground(new java.awt.Color(255, 255, 255));
        text_auditoria.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        text_auditoria.setText("Auditoría");
        jdpescritorio.add(text_auditoria);
        text_auditoria.setBounds(230, 360, 170, 30);

        lb_contabilidad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_contabilidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/contabilidad.png"))); // NOI18N
        lb_contabilidad.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lb_contabilidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_contabilidadMouseClicked(evt);
            }
        });
        jdpescritorio.add(lb_contabilidad);
        lb_contabilidad.setBounds(450, 220, 130, 130);

        texto_contabilidad.setFont(new java.awt.Font("Arial Narrow", 1, 22)); // NOI18N
        texto_contabilidad.setForeground(new java.awt.Color(255, 255, 255));
        texto_contabilidad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        texto_contabilidad.setText("Contabilidad");
        jdpescritorio.add(texto_contabilidad);
        texto_contabilidad.setBounds(450, 360, 130, 30);

        lb_costos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_costos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/costos.png"))); // NOI18N
        lb_costos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lb_costos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_costosMouseClicked(evt);
            }
        });
        jdpescritorio.add(lb_costos);
        lb_costos.setBounds(650, 220, 130, 130);

        texto_costos.setFont(new java.awt.Font("Arial Narrow", 1, 22)); // NOI18N
        texto_costos.setForeground(new java.awt.Color(255, 255, 255));
        texto_costos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        texto_costos.setText("Costos");
        jdpescritorio.add(texto_costos);
        texto_costos.setBounds(650, 360, 130, 30);

        lb_tributacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_tributacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/tributacion.png"))); // NOI18N
        lb_tributacion.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lb_tributacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_tributacionMouseClicked(evt);
            }
        });
        jdpescritorio.add(lb_tributacion);
        lb_tributacion.setBounds(850, 220, 130, 130);

        texto_tributacion.setFont(new java.awt.Font("Arial Narrow", 1, 22)); // NOI18N
        texto_tributacion.setForeground(new java.awt.Color(255, 255, 255));
        texto_tributacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        texto_tributacion.setText("Tributación");
        jdpescritorio.add(texto_tributacion);
        texto_tributacion.setBounds(850, 360, 130, 30);

        jLabel2.setFont(new java.awt.Font("Arial Narrow", 1, 60)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Olimpiadas Contables \"UTC\" 2020");
        jdpescritorio.add(jLabel2);
        jLabel2.setBounds(250, 50, 980, 80);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/degradados-wallpapers-7.jpg"))); // NOI18N
        jdpescritorio.add(jLabel10);
        jLabel10.setBounds(0, 0, 1840, 1200);

        jLabel1.setText("Usuario Conectado:");

        ho.setText("Hora:");

        reloj.setText("jLabel3");

        jMenu1.setText("Archivo");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        menunuevouser.setText("Nuevo usuario");
        menunuevouser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menunuevouserActionPerformed(evt);
            }
        });
        jMenu1.add(menunuevouser);

        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });
        jMenu1.add(salir);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Mantenimiento");
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });

        estacionamientos.setText("Nuevo cuestionario");
        estacionamientos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estacionamientosActionPerformed(evt);
            }
        });
        jMenu2.add(estacionamientos);

        registro_preguntas.setText("Registro de preguntas");
        registro_preguntas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registro_preguntasActionPerformed(evt);
            }
        });
        jMenu2.add(registro_preguntas);

        jMenuBar1.add(jMenu2);

        jMenu4.setText("Consultas");
        jMenu4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu4ActionPerformed(evt);
            }
        });

        consultasClientes.setText("Participantes");
        consultasClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultasClientesActionPerformed(evt);
            }
        });
        jMenu4.add(consultasClientes);

        jMenuBar1.add(jMenu4);

        reportecliente.setText("Reportes");

        menuClientesSi.setText("Calificaciones");
        menuClientesSi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuClientesSiActionPerformed(evt);
            }
        });
        reportecliente.add(menuClientesSi);

        jMenuBar1.add(reportecliente);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblusu, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 440, Short.MAX_VALUE)
                .addComponent(ho)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(reloj)
                .addGap(71, 71, 71))
            .addComponent(jdpescritorio, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jdpescritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblusu)
                    .addComponent(ho)
                    .addComponent(reloj))
                .addGap(11, 11, 11))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
// TODO add your handling code here:

}//GEN-LAST:event_jMenu1ActionPerformed

private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
// TODO add your handling code here:
    this.dispose();
}//GEN-LAST:event_salirActionPerformed

private void estacionamientosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estacionamientosActionPerformed
// TODO add your handling code here:
    RegistroEncuesta ip = new RegistroEncuesta();

    jdpescritorio.add(ip);
    ip.show();
}//GEN-LAST:event_estacionamientosActionPerformed

private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed
// TODO add your handling code here:

}//GEN-LAST:event_jMenu2ActionPerformed

private void menuClientesSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuClientesSiActionPerformed

ReporteCalificaciones ip = new ReporteCalificaciones();

    jdpescritorio.add(ip);
    ip.show();

}//GEN-LAST:event_menuClientesSiActionPerformed

private void consultasClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultasClientesActionPerformed
    /*ConsultasClientes ip = new ConsultasClientes();

    jdpescritorio.add(ip);
    ip.show();
     */

}//GEN-LAST:event_consultasClientesActionPerformed

private void jMenu4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu4ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jMenu4ActionPerformed

private void menunuevouserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menunuevouserActionPerformed
    RegistroPersona ip = new RegistroPersona();
        jdpescritorio.add(ip);
        
        ip.show();
}//GEN-LAST:event_menunuevouserActionPerformed

    private void registro_preguntasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registro_preguntasActionPerformed
        RegistroPreguntas ip = new RegistroPreguntas();
        jdpescritorio.add(ip);
        ip.show();

    }//GEN-LAST:event_registro_preguntasActionPerformed

    private void lb_auditoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_auditoriaMouseClicked
        this.lb_auditoria.setEnabled(false);
        ChooseLevel ip = new ChooseLevel("AUDITORIA");
        jdpescritorio.add(ip);
        ip.show();
        this.lb_auditoria.setEnabled(true);
        
    }//GEN-LAST:event_lb_auditoriaMouseClicked

    private void lb_costosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_costosMouseClicked
        ChooseLevel ip = new ChooseLevel("COSTOS");

        jdpescritorio.add(ip);
        ip.show();


    }//GEN-LAST:event_lb_costosMouseClicked

    private void lb_contabilidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_contabilidadMouseClicked
        ChooseLevel ip = new ChooseLevel("CONTABILIDAD");
        jdpescritorio.add(ip);
        ip.show();
    }//GEN-LAST:event_lb_contabilidadMouseClicked

    private void lb_tributacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_tributacionMouseClicked
        ChooseLevel ip = new ChooseLevel("TRIBUTACION");
        jdpescritorio.add(ip);
        ip.show();
    }//GEN-LAST:event_lb_tributacionMouseClicked

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(DashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new DashBoard().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem consultasClientes;
    private javax.swing.JMenuItem estacionamientos;
    private javax.swing.JLabel ho;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    public static javax.swing.JDesktopPane jdpescritorio;
    private javax.swing.JLabel lb_auditoria;
    private javax.swing.JLabel lb_contabilidad;
    private javax.swing.JLabel lb_costos;
    private javax.swing.JLabel lb_tributacion;
    public static javax.swing.JLabel lblusu;
    private javax.swing.JMenuItem menuClientesSi;
    private javax.swing.JMenuItem menunuevouser;
    private javax.swing.JMenuItem registro_preguntas;
    public static javax.swing.JLabel reloj;
    private javax.swing.JLabel reloj2;
    private javax.swing.JMenu reportecliente;
    private javax.swing.JMenuItem salir;
    private javax.swing.JLabel text_auditoria;
    private javax.swing.JLabel texto_contabilidad;
    private javax.swing.JLabel texto_costos;
    private javax.swing.JLabel texto_tributacion;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        Thread ct = Thread.currentThread();
        while (ct == h1) {
            calcula();
            reloj.setText(hora + ":" + minutos + ":" + segundos + " " + ampm);
            reloj2.setText(hora + ":" + minutos + ":" + segundos + " " + ampm);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
    }

    private void calcula() {
        Calendar calendario = new GregorianCalendar();
        java.util.Date fechahoraActual = new java.util.Date();
        calendario.setTime(fechahoraActual);
        ampm = calendario.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
        if (ampm.equals("PM")) {
            int h = calendario.get(Calendar.HOUR_OF_DAY) - 12;
            hora = h > 9 ? "" + h : "0" + h;
        } else {
            hora = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario.get(Calendar.HOUR_OF_DAY) : "0" + calendario.get(Calendar.HOUR_OF_DAY);
        }
        minutos = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE) : "0" + calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND);
    }
}
