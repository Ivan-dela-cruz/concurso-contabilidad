/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * IngresoCliente.java
 *
 * Created on 07-abr-2013, 11:16:29
 */
package view;

import conexion.Conexion;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.DateManager;

/**
 *
 * @author Administrador
 */
public class RegistroPreguntas extends javax.swing.JInternalFrame {

    DefaultTableModel model;

    /**
     * Creates new form IngresoCliente
     */
    public static Connection sql;
    public static Statement s;
    private int id_encontrado = 0, id1 = 0, id2 = 0, id3 = 0, id4 = 0;

    public RegistroPreguntas() {
        initComponents();
        bloquear();
        cargar("");
        llenarCombo();

    }

    public void llenarCombo() {
        Conexion j = new Conexion();
        j.conectar();
        cb_encuesta.removeAllItems();
        ArrayList<String> lista = new ArrayList<String>();
        lista = j.llenar_combo("encuestas", "codigo");
        for (int i = 0; i < lista.size(); i++) {
            cb_encuesta.addItem(lista.get(i));
        }
    }

    void bloquear() {
        txt_opcion1.setEnabled(true);
        txt_pregunta.setEnabled(true);
        cb_tipo_pregunta.setEnabled(true);
        btnguardar.setEnabled(true);
        btnnuevo.setEnabled(true);
        btncancelar.setEnabled(true);
        btnactualizar.setEnabled(false);

    }

    void limpiar() {

        txt_pregunta.setText("");
        text_repuesta.setText("");
        txt_codigo.setText("");
        txt_opcion1.setText("");
        txt_opcion2.setText("");
        txt_opcion3.setText("");
        txt_opcion4.setText("");

    }

    void desbloquear() {
        txt_opcion1.setEnabled(true);
        txt_pregunta.setEnabled(true);
        text_repuesta.setEnabled(true);

        cb_tipo_pregunta.setEnabled(true);
        btnguardar.setEnabled(false);
        btnnuevo.setEnabled(false);
        btncancelar.setEnabled(true);
        btnactualizar.setEnabled(true);
    }

    void cargar(String valor) {
        DefaultTableModel modelo = new DefaultTableModel();

        int id_tipo = 0;
        //SELECT id_encuesta,titulo,respuesta,id_tipo_pregunta FROM preguntas WHERE id = 1
        String mostrar = "SELECT preguntas.id_pregunta, encuestas.codigo, encuestas.nivel,encuestas.categoria, preguntas.titulo,preguntas.respuesta,preguntas.codigo AS cod, preguntas.id_tipo_pregunta FROM encuestas, preguntas WHERE preguntas.id_encuesta = encuestas.id AND  CONCAT(preguntas.titulo, preguntas.respuesta,encuestas.codigo, encuestas.nivel, encuestas.categoria) LIKE '%" + valor + "%'";

        String[] Titulos = {"CÓDIGO ENCUESTA", "CÓDIGO PREGUNTA", "CATEGORÍA", "NIVEL", "PREGUNTA", "RESPUESTA", "TIPO PREGUNTA"};
        modelo.setColumnIdentifiers(Titulos);
        this.tbclientes.setModel(modelo);

        Conexion j = new Conexion();
        j.conectar();
        sql = j.sql;

        try {

            String[] registros = new String[7];
            s = sql.createStatement();
            ResultSet rs = s.executeQuery(mostrar);
            while (rs.next()) {

                registros[0] = rs.getString("codigo");
                registros[1] = rs.getString("cod");
                registros[2] = rs.getString("categoria");
                registros[3] = rs.getString("nivel");
                registros[4] = rs.getString("titulo");
                registros[5] = rs.getString("respuesta");
                id_tipo = rs.getInt("id_tipo_pregunta");

                switch (id_tipo) {
                    case 1:
                        registros[6] = "SELECCION MULTIPLE";
                        break;
                    case 2:
                        registros[6] = "DESPLEGABLE";
                        break;
                    case 3:
                        registros[6] = "TEXTO";
                        break;
                }

                modelo.addRow(registros);
            }
            tbclientes.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(RegistroPreguntas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void codigosclientes() {
        int j;
        int cont = 1;
        String num = "";
        String c = "";
        String SQL = "select max(cod_barras) from cliente_mes";
        // String SQL="select count(*) from factura";
        //String SQL="SELECT MAX(cod_emp) AS cod_emp FROM empleado";
        //String SQL="SELECT @@identity AS ID";
        try {

            s = sql.createStatement();
            ResultSet rs = s.executeQuery(SQL);
            if (rs.next()) {
                c = rs.getString(1);
            }
            System.out.println("resultado " + c);
            if (c == null) {
                txt_opcion1.setText("0001");
            } else {
                char r1 = c.charAt(0);
                char r2 = c.charAt(1);
                char r3 = c.charAt(2);
                char r4 = c.charAt(3);
                String r = "";
                r = "" + r1 + r2 + r3 + r4;

                j = Integer.parseInt(r);

            }

        } catch (SQLException ex) {
            System.out.println(ex);

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        mnmodificar = new javax.swing.JMenuItem();
        mneliminar = new javax.swing.JMenuItem();
        jPanel2 = new javax.swing.JPanel();
        btnnuevo = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        btnactualizar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        Label_ciCliente1 = new javax.swing.JLabel();
        Label_ciCliente3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        Label_TelefonoCliente = new javax.swing.JLabel();
        Label_ciCliente4 = new javax.swing.JLabel();
        txt_opcion1 = new javax.swing.JTextField();
        txt_opcion2 = new javax.swing.JTextField();
        txt_opcion3 = new javax.swing.JTextField();
        txt_opcion4 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        Label_NombreCliente = new javax.swing.JLabel();
        txt_pregunta = new javax.swing.JTextField();
        Label_ApellidoCliente = new javax.swing.JLabel();
        Label_ciCliente2 = new javax.swing.JLabel();
        cb_tipo_pregunta = new javax.swing.JComboBox();
        Label_NombreCliente1 = new javax.swing.JLabel();
        cb_encuesta = new javax.swing.JComboBox<>();
        text_repuesta = new javax.swing.JTextField();
        Label_NombreCliente2 = new javax.swing.JLabel();
        txt_codigo = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbclientes = new javax.swing.JTable();
        btnbuscar = new javax.swing.JButton();
        txtbuscar = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        mnmodificar.setText("Modificar");
        mnmodificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnmodificarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(mnmodificar);

        mneliminar.setText("Eliminar");
        mneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mneliminarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(mneliminar);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("NUEVA PREGUNTA");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnnuevo.setText("Nuevo");
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });

        btnguardar.setText("Guardar");
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        btnactualizar.setText("Actualizar");
        btnactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactualizarActionPerformed(evt);
            }
        });

        btncancelar.setText("Cancelar");
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });

        btnsalir.setText("Salir");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnactualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnactualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(142, 142, 142))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        Label_ciCliente1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Label_ciCliente1.setForeground(new java.awt.Color(102, 102, 102));
        Label_ciCliente1.setText("Opción 2");
        Label_ciCliente1.setToolTipText("");

        Label_ciCliente3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Label_ciCliente3.setForeground(new java.awt.Color(102, 102, 102));
        Label_ciCliente3.setText("Opción 3");
        Label_ciCliente3.setToolTipText("");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(80, 80, 97));
        jLabel11.setText("Datos de las opciones");
        jLabel11.setToolTipText("");

        Label_TelefonoCliente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Label_TelefonoCliente.setForeground(new java.awt.Color(102, 102, 102));
        Label_TelefonoCliente.setText("Opción 4");

        Label_ciCliente4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Label_ciCliente4.setForeground(new java.awt.Color(102, 102, 102));
        Label_ciCliente4.setText("Opción 1");
        Label_ciCliente4.setToolTipText("");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Label_ciCliente3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Label_TelefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Label_ciCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Label_ciCliente4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_opcion2, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                            .addComponent(txt_opcion3)
                            .addComponent(txt_opcion4)
                            .addComponent(txt_opcion1)))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_ciCliente4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_opcion1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_ciCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_opcion2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_ciCliente3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_opcion3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_TelefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_opcion4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("")));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(80, 80, 97));
        jLabel4.setText("Detalles de la prergunta");
        jLabel4.setToolTipText("");

        Label_NombreCliente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Label_NombreCliente.setForeground(new java.awt.Color(102, 102, 102));
        Label_NombreCliente.setText("Pregunta");
        Label_NombreCliente.setToolTipText("");

        txt_pregunta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_preguntaKeyTyped(evt);
            }
        });

        Label_ApellidoCliente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Label_ApellidoCliente.setForeground(new java.awt.Color(102, 102, 102));
        Label_ApellidoCliente.setText("Respuesta");

        Label_ciCliente2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Label_ciCliente2.setForeground(new java.awt.Color(102, 102, 102));
        Label_ciCliente2.setText("Tipo pregunta");
        Label_ciCliente2.setToolTipText("");

        cb_tipo_pregunta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCION MULTIPLE", "DESPLEGABLE", "TEXTO" }));

        Label_NombreCliente1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Label_NombreCliente1.setForeground(new java.awt.Color(102, 102, 102));
        Label_NombreCliente1.setText("Encuesta");
        Label_NombreCliente1.setToolTipText("");

        cb_encuesta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "001", "002", "003" }));

        Label_NombreCliente2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Label_NombreCliente2.setForeground(new java.awt.Color(102, 102, 102));
        Label_NombreCliente2.setText("Código ");
        Label_NombreCliente2.setToolTipText("");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(Label_NombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_pregunta))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(Label_ApellidoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text_repuesta))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(Label_ciCliente2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_tipo_pregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(Label_NombreCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_encuesta, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(Label_NombreCliente2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_codigo)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_tipo_pregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label_ciCliente2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_NombreCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_encuesta, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_NombreCliente2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_NombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_pregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label_ApellidoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(text_repuesta, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbclientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbclientes.setComponentPopupMenu(jPopupMenu1);
        tbclientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tbclientesKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(tbclientes);

        btnbuscar.setText("Mostrar Todos");

        txtbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbuscarActionPerformed(evt);
            }
        });
        txtbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarKeyReleased(evt);
            }
        });

        jLabel10.setText("BUSCAR:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnbuscar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 392, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbuscar)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizarActionPerformed
// TODO add your handling code here:
    try {
        int id_tipo_pregunta = 0, id_encuesta = 0;

        Conexion j = new Conexion();
        j.conectar();
        String tipo = cb_tipo_pregunta.getSelectedItem().toString().toUpperCase();
        id_encuesta = j.buscarId("SELECT id FROM encuestas WHERE codigo = '" + cb_encuesta.getSelectedItem().toString().toUpperCase() + "'");

        switch (tipo) {
            case "SELECCION MULTIPLE":
                id_tipo_pregunta = 1;
                break;
            case "DESPLEGABLE":
                id_tipo_pregunta = 2;
                break;
            case "TEXTO":
                id_tipo_pregunta = 3;
                break;
        }

        //id_encuesta,titulo,respuesta,id_tipo_pregunta
        j.editar("UPDATE preguntas SET id_encuesta='" + id_encuesta
                + "',codigo='" + txt_codigo.getText().toUpperCase()
                + "',titulo='" + txt_pregunta.getText().toUpperCase()
                + "',respuesta='" + text_repuesta.getText().toUpperCase()
                + "',id_tipo_pregunta='" + id_tipo_pregunta
                + "' WHERE id_pregunta='" + id_encontrado + "'");

        j.editar("UPDATE opciones SET valor='" + txt_opcion1.getText().toUpperCase()
                + "' WHERE id_opcion='" + id1 + "'");
        j.editar("UPDATE opciones SET valor='" + txt_opcion2.getText().toUpperCase()
                + "' WHERE id_opcion='" + id2 + "'");
        j.editar("UPDATE opciones SET valor='" + txt_opcion3.getText().toUpperCase()
                + "' WHERE id_opcion='" + id3 + "'");
        j.editar("UPDATE opciones SET valor='" + txt_opcion4.getText().toUpperCase()
                + "' WHERE id_opcion='" + id4 + "'");

        cargar("");
        bloquear();
        limpiar();
    } catch (Exception e) {
        System.out.print(e.getMessage());
    }

}//GEN-LAST:event_btnactualizarActionPerformed

private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
// TODO add your handling code here:
    this.dispose();
}//GEN-LAST:event_btnsalirActionPerformed

private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
// TODO add your handling code here:
    desbloquear();
    limpiar();
    //codigosclientes();
    txt_opcion1.requestFocus();
}//GEN-LAST:event_btnnuevoActionPerformed

private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
// TODO add your handling code here:
    bloquear();
    limpiar();
}//GEN-LAST:event_btncancelarActionPerformed

    private void GuardarOpciones() {
        Conexion conexion = new Conexion();
        conexion.conectar();
        int id_pregunta = 0;
        String opcion1, opcion2, opcion3, opcion4;
        opcion1 = txt_opcion1.getText().toUpperCase();
        opcion2 = txt_opcion2.getText().toUpperCase();
        opcion3 = txt_opcion3.getText().toUpperCase();
        opcion4 = txt_opcion4.getText().toUpperCase();

        id_pregunta = conexion.buscarId("SELECT id_pregunta FROM preguntas WHERE codigo = '" + txt_codigo.getText().toString() + "'");

        String sql = "INSERT INTO opciones(id_pregunta,valor) VALUES('"
                + id_pregunta + "','"
                + opcion1 + "')";
        conexion.insertar(sql);
        sql = "INSERT INTO opciones(id_pregunta,valor) VALUES('"
                + id_pregunta + "','"
                + opcion2 + "')";
        conexion.insertar(sql);
        sql = "INSERT INTO opciones(id_pregunta,valor) VALUES('"
                + id_pregunta + "','"
                + opcion3 + "')";
        conexion.insertar(sql);
        sql = "INSERT INTO opciones(id_pregunta,valor) VALUES('"
                + id_pregunta + "','"
                + opcion4 + "')";
        conexion.insertar(sql);

    }
private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
// TODO add your handling code here:

    int id_tipo_pregunta = 0, id_encuesta = 0;
    String cod, codigo_pregunta, tipo, pregunta, respuesta;
    int estado_encuesta = 0, tiempo;
    Conexion j = new Conexion();
    j.conectar();

    cod = cb_encuesta.getSelectedItem().toString();
    codigo_pregunta = txt_codigo.getText().toString();
    tipo = cb_tipo_pregunta.getSelectedItem().toString();
    pregunta = txt_pregunta.getText().toUpperCase();
    respuesta = text_repuesta.getText().toUpperCase();

    //SELECCION MULTIPLE DESPLEGABLE TEXTO
    switch (tipo) {
        case "SELECCION MULTIPLE":
            id_tipo_pregunta = 1;
            break;
        case "DESPLEGABLE":
            id_tipo_pregunta = 2;
            break;
        case "TEXTO":
            id_tipo_pregunta = 3;
            break;
    }
    id_encuesta = j.buscarId("SELECT id FROM encuestas WHERE codigo = '" + cod + "'");

    try {

        //SELECT `id_pregunta`, `id_encuesta`, `titulo`, `respuesta`, `id_tipo_pregunta` FROM `preguntas` WHERE 1
        String sql = "INSERT INTO preguntas(id_encuesta,codigo,titulo,respuesta,id_tipo_pregunta) VALUES('"
                + id_encuesta + "','"
                + codigo_pregunta + "','"
                + pregunta + "','"
                + respuesta + "','"
                + id_tipo_pregunta + "')";
        j.insertar(sql);
        GuardarOpciones();
        JOptionPane.showMessageDialog(null, "Guardado exitosamente");
        limpiar();
        cargar("");
        bloquear();
    } catch (Exception e) {
        System.out.println("ERROR");
    }
}//GEN-LAST:event_btnguardarActionPerformed

private void txtbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscarActionPerformed
// TODO add your handling code here:

}//GEN-LAST:event_txtbuscarActionPerformed

private void txtbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyReleased
// TODO add your handling code here:
    cargar(txtbuscar.getText());
}//GEN-LAST:event_txtbuscarKeyReleased

private void txt_preguntaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_preguntaKeyTyped
// TODO add your handling code here:
    char car = evt.getKeyChar();
    if ((car < 'a' || car > 'z') && (car < 'A' || car > 'Z') && (car != (char) KeyEvent.VK_SPACE)) {
        evt.consume();
    }
}//GEN-LAST:event_txt_preguntaKeyTyped

private void mneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mneliminarActionPerformed
// TODO add your handling code here:
    int fila = tbclientes.getSelectedRow();

    String codigo = "";
    codigo = tbclientes.getValueAt(fila, 1).toString();
    if (fila >= 0) {
        try {
            Conexion j = new Conexion();
            j.conectar();
            String sql = "DELETE FROM preguntas WHERE codigo='" + codigo + "'";
            j.eliminar(sql);

            cargar("");
            bloquear();
            limpiar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    } else {
        JOptionPane.showMessageDialog(this, "No ha selecionada ninguna fila");
    }

}//GEN-LAST:event_mneliminarActionPerformed

private void mnmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnmodificarActionPerformed
// "CÓDIGO", "CÉDULA", "NOMBRES", "APELLIDOS", "DIRECCIÓN", "TELEFONO", "PLACA", "MODELO", "COLOR"    
    desbloquear();
    btnactualizar.setEnabled(true);
    int filamodificar = tbclientes.getSelectedRow();

    //"CÓDIGO ENCUESTA", "CÓDIGO PREGUNTA", "CATEGORÍA", "NIVEL", "PREGUNTA", "RESPUESTA","tipo pregunta"
    if (filamodificar >= 0) {
        cb_encuesta.setSelectedItem(tbclientes.getValueAt(filamodificar, 0).toString());
        txt_codigo.setText(tbclientes.getValueAt(filamodificar, 1).toString());
        txt_pregunta.setText(tbclientes.getValueAt(filamodificar, 4).toString());
        text_repuesta.setText(tbclientes.getValueAt(filamodificar, 5).toString());
        cb_tipo_pregunta.setSelectedItem(tbclientes.getValueAt(filamodificar, 6).toString());

        Conexion conexion = new Conexion();
        conexion.conectar();
        id_encontrado = conexion.buscarId("SELECT id_pregunta FROM preguntas WHERE codigo = '" + tbclientes.getValueAt(filamodificar, 1).toString() + "'");

        String mostrar = "SELECT * FROM opciones WHERE id_pregunta =" + id_encontrado;
        int contador = 0;

        Conexion j = new Conexion();
        j.conectar();
        sql = conexion.sql;

        try {

            s = sql.createStatement();
            ResultSet rs = s.executeQuery(mostrar);
            while (rs.next()) {
                contador++;
                switch (contador) {
                    case 1:
                        txt_opcion1.setText(rs.getString("valor"));
                        id1 = rs.getInt("id_opcion");
                        break;
                    case 2:
                        txt_opcion2.setText(rs.getString("valor"));
                        id2 = rs.getInt("id_opcion");
                        break;
                    case 3:
                        txt_opcion3.setText(rs.getString("valor"));
                        id3 = rs.getInt("id_opcion");
                        break;
                    case 4:
                        txt_opcion4.setText(rs.getString("valor"));
                        id4 = rs.getInt("id_opcion");
                        break;
                }

            }
        } catch (Exception e) {

        }

    } else {
        JOptionPane.showMessageDialog(this, "No ha seleccionado ");
    }

}//GEN-LAST:event_mnmodificarActionPerformed

    private void tbclientesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbclientesKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tbclientesKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Label_ApellidoCliente;
    private javax.swing.JLabel Label_NombreCliente;
    private javax.swing.JLabel Label_NombreCliente1;
    private javax.swing.JLabel Label_NombreCliente2;
    private javax.swing.JLabel Label_TelefonoCliente;
    private javax.swing.JLabel Label_ciCliente1;
    private javax.swing.JLabel Label_ciCliente2;
    private javax.swing.JLabel Label_ciCliente3;
    private javax.swing.JLabel Label_ciCliente4;
    private javax.swing.JButton btnactualizar;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JButton btnsalir;
    private javax.swing.JComboBox<String> cb_encuesta;
    private javax.swing.JComboBox cb_tipo_pregunta;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuItem mneliminar;
    private javax.swing.JMenuItem mnmodificar;
    private javax.swing.JTable tbclientes;
    private javax.swing.JTextField text_repuesta;
    private javax.swing.JTextField txt_codigo;
    private javax.swing.JTextField txt_opcion1;
    private javax.swing.JTextField txt_opcion2;
    private javax.swing.JTextField txt_opcion3;
    private javax.swing.JTextField txt_opcion4;
    private javax.swing.JTextField txt_pregunta;
    private javax.swing.JTextField txtbuscar;
    // End of variables declaration//GEN-END:variables

}
