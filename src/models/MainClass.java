/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import conexion.Conexion;
import view.DashBoard;

/**
 *
 * @author ivan
 */
public class MainClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Conexion con = new Conexion();
        con.conectar();
        
        DashBoard dashBoard = new DashBoard();
        dashBoard.setVisible(true);
    }
    
}
