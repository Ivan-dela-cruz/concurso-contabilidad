/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.toedter.calendar.JDateChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author ivan
 */
public class DateManager {

    private JDateChooser fecha_inicio, fecha_fin;

    public DateManager(JDateChooser fecha_inicio, JDateChooser fecha_fin) {
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
    }

    public String getFechaFin() {
        String fecha = "";
        try {
            int dia = fecha_fin.getDate().getDate();
            int mes = fecha_fin.getDate().getMonth() + 1;
            int anio = fecha_fin.getDate().getYear() + 1900;
            fecha = anio + "-" + mes + "-" + dia;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Debe elegir una fecha");
        }
        return fecha;

    }

    public String getFechaInicio() {
        String fecha = "";
        try {
            int dia = fecha_inicio.getDate().getDate();
            int mes = fecha_inicio.getDate().getMonth() + 1;
            int anio = fecha_inicio.getDate().getYear() + 1900;
            fecha = anio + "/" + mes + "/" + dia;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Elegir una fecha de inicio");
        }
        return fecha;

    }
}
