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
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.logging.*;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.DateManager;
import models.Usuario;
import static view.DashBoard.jdpescritorio;
import static view.RegistroPreguntas.s;
import static view.RegistroPreguntas.sql;

/**
 *
 * @author Administrador
 */
public class SolucionPreguntas extends javax.swing.JInternalFrame {

    DefaultTableModel model;

    /*
     SELECT usuarios.nombres, usuarios.apellidos,usuarios.id_usuario,encuestas.nivel,encuestas.categoria,calificaciones.calificacion,calificaciones.aciertos, calificaciones.numero_preguntas, calificaciones.tiempo FROM calificaciones, usuarios,encuestas WHERE calificaciones.id_usuario = usuarios.id_usuario AND calificaciones.id_encuesta = encuestas.id ORDER BY tiempo 
     */
    /**
     * Creates new form IngresoCliente
     */
    public static Connection sql;
    public static Statement s;
    private int id_encontrado = 0, id1 = 0, id2 = 0, id3 = 0, id4 = 0;
    int horas, minutos, seguntos, mili;
    boolean estado = false;
    private String nivel;
    private int tiempo_cuestionario;
    private String categoria, codigo;
    private String resp_1 = "S/R", resp_2 = "S/R", resp_3 = "S/R", resp_4 = "S/R", resp_5 = "S/R";
    private Usuario usuario;
    private int id_enuesta = 0;
    Thread hilo;

    public void IniciarTemporizador(int tiempo) throws InterruptedException {

        horas = 0;
        minutos = 0;
        seguntos = 0;
        mili = 0;
        estado = true;

        hilo = new Thread() {

            public void run() {
                while (true) {

                    try {
                        sleep(10);
                        mili++;
                        if (mili > 100) {
                            mili = 0;
                            seguntos++;
                        }
                        if (seguntos > 59) {
                            minutos++;
                            seguntos = 0;
                        }
                        if (minutos > 59) {
                            horas++;
                            minutos = 0;
                        }

                        if (minutos == tiempo) {

                            if (tiempo_cuestionario < 10) {
                                lb_temporizador.setText("0" + tiempo_cuestionario + ":00:00");
                            } else {
                                lb_temporizador.setText(tiempo_cuestionario + ":00:00");
                            }

                            calificar();
                            break;

                        }
                        //System.out.println("time: " + horas + ":" + minutos + ":" + seguntos);

                        if (minutos < 10) {
                            if (seguntos < 10) {
                                if (mili < 10) {
                                    lb_temporizador.setText("0" + minutos + ":0" + seguntos + ":0" + mili);
                                } else {
                                    lb_temporizador.setText("0" + minutos + ":0" + seguntos + ":" + mili);
                                }

                            } else {
                                if (mili < 10) {
                                    lb_temporizador.setText("0" + minutos + ":" + seguntos + ":0" + mili);
                                } else {
                                    lb_temporizador.setText("0" + minutos + ":" + seguntos + ":" + mili);
                                }

                            }

                            System.out.println("corriendo reloj");
                        } else {

                            if (seguntos < 10) {
                                if (mili < 10) {
                                    lb_temporizador.setText(minutos + ":0" + seguntos + ":0" + mili);
                                } else {
                                    lb_temporizador.setText(minutos + ":0" + seguntos + ":" + mili);
                                }

                            } else {
                                if (mili < 10) {
                                    lb_temporizador.setText(minutos + ":" + seguntos + ":0" + mili);
                                } else {
                                    lb_temporizador.setText(minutos + ":" + seguntos + ":" + mili);
                                }

                            }
                            System.out.println("corriendo reloj");
                        }

                    } catch (Exception e) {
                        System.out.println(e);
                    }

                }
            }
        };
        hilo.start();

    }

    public SolucionPreguntas(String nivel, String categoria, String codigo, Usuario usuario) throws InterruptedException {
        initComponents();

        this.nivel = nivel;
        this.categoria = categoria;
        this.codigo = codigo;
        this.usuario = usuario;
        cargar(this.nivel);
        IniciarTemporizador(this.tiempo_cuestionario);

    }

    void limpiar() {

    }

    public void guardarCalificacion(double calificaion, int aciertos, int num_preguntas, String tiempo) {
        Conexion j = new Conexion();
        j.conectar();
        try {
            //SELECT `id`, `id_encuesta`, `id_usuario`, `calificacion`, `aciertos`, `numero_preguntas`, `tiempo` FROM `calificaciones` WHERE 1
            String sql = "INSERT INTO calificaciones(id_encuesta,id_usuario,calificacion,aciertos,numero_preguntas,tiempo) VALUES('"
                    + id_enuesta + "','"
                    + usuario.getCodigo() + "','"
                    + calificaion + "','"
                    + aciertos + "','"
                    + num_preguntas + "','"
                    + tiempo + "')";
            j.insertar(sql);

        } catch (Exception e) {
            System.out.println("ERROR");
        }
    }

    public void calificar() {

        int calificaicon = 0, aciertos = 0;

        String respuesta_pregunta_1 = "", respuesta_pregunta_2 = "", respuesta_pregunta_3 = "", respuesta_pregunta_4 = "", respuesta_pregunta_5 = "";
        respuesta_pregunta_1 = CheckSelected(check_p1_1, check_p1_2, check_p1_3, check_p1_4);
        respuesta_pregunta_2 = CheckSelected(check_p2_1, check_p2_2, check_p2_3, check_p2_4);
        respuesta_pregunta_3 = CheckSelected(check_p3_1, check_p3_2, check_p3_3, check_p3_4);
        respuesta_pregunta_4 = CheckSelected(check_p4_1, check_p4_2, check_p4_3, check_p4_4);
        respuesta_pregunta_5 = CheckSelected(check_p5_1, check_p5_2, check_p5_3, check_p5_4);

        if (resp_1.equalsIgnoreCase(respuesta_pregunta_1)) {
            calificaicon += 2;
            aciertos++;
        }

        if (resp_2.equalsIgnoreCase(respuesta_pregunta_2)) {
            calificaicon += 2;
            aciertos++;
        }

        if (resp_3.equalsIgnoreCase(respuesta_pregunta_3)) {
            calificaicon += 2;
            aciertos++;
        }

        if (resp_4.equalsIgnoreCase(respuesta_pregunta_4)) {
            calificaicon += 2;
            aciertos++;
        }

        if (resp_5.equalsIgnoreCase(respuesta_pregunta_5)) {
            calificaicon += 2;
            aciertos++;
        }
        guardarCalificacion(calificaicon, aciertos, 5, lb_temporizador.getText().toString());
        Calificaciones ip = new Calificaciones("" + calificaicon, lb_temporizador.getText().toString(), usuario.getNombre() + " " + usuario.getApellido(), "" + (5 - aciertos), "" + aciertos);
        jdpescritorio.add(ip);
        ip.show();
        this.dispose();

        System.out.println("calificacion : " + calificaicon + "  aciertos: " + aciertos + "  fallos:" + (5 - aciertos));

    }

    public void CheckSelectedStatus(JCheckBox check1, JCheckBox check2, JCheckBox check3) {

        if (check1.isSelected()) {
            check1.setSelected(false);
        }
        if (check2.isSelected()) {
            check2.setSelected(false);
        }
        if (check3.isSelected()) {
            check3.setSelected(false);
        }

    }

    public String CheckSelected(JCheckBox check1, JCheckBox check2, JCheckBox check3, JCheckBox check4) {

        String respuesta = "";
        if (check1.isSelected()) {
            respuesta = check1.getText().toString();
        }
        if (check2.isSelected()) {
            respuesta = check2.getText().toString();
        }
        if (check3.isSelected()) {
            respuesta = check3.getText().toString();
        }
        if (check4.isSelected()) {
            respuesta = check4.getText().toString();
        }

        return respuesta;
    }

    void cargar(String nv) {

        int id_tipo = 0;
        //SELECT id_encuesta,titulo,respuesta,id_tipo_pregunta FROM preguntas WHERE id = 1
        String mostrar = "SELECT encuestas.id,encuestas.codigo,encuestas.titulo AS encuesta,"
                + "encuestas.categoria,encuestas.nivel,encuestas.descripcion,encuestas.estado,"
                + "encuestas.tiempo,encuestas.fecha_inicio,encuestas.fecha_final, preguntas.id_pregunta, "
                + "preguntas.titulo, preguntas.respuesta, preguntas.id_tipo_pregunta,opciones.id_opcion,"
                + "opciones.valor "
                + "FROM preguntas,opciones,encuestas WHERE encuestas.id = preguntas.id_encuesta "
                + "AND preguntas.id_pregunta = opciones.id_pregunta "
                + "AND encuestas.codigo = '" + this.codigo + "' "
                + "AND encuestas.nivel = '" + nv + "' "
                + "AND encuestas.estado = 1 "
                + "AND encuestas.categoria = '" + this.categoria + "'";
        Conexion j = new Conexion();
        j.conectar();
        sql = j.sql;

        try {

            String[] registros = new String[7];
            s = sql.createStatement();
            ResultSet rs = s.executeQuery(mostrar);
            /*
               
                    id,codigo,categoria,nivel,descripcion,estado,tiempo,fecha_inicio,fecha_final,
                     id_pregunta,titulo,respuesta,id_tipo_pregunta,id_opcion,valor

             */
            int contador = 0;

            while (rs.next()) {
                contador++;
                switch (contador) {
                    case 1:
                        this.lb_nombres_participantes.setText("Participante: " + usuario.getNombre() + " " + usuario.getApellido());
                        this.id_enuesta = rs.getInt("id");
                        lb_indicaciones.setText("Inidicaciones:  " + rs.getString("descripcion"));
                        lb_tiempo_cuestionario.setText("Tiempo:  " + rs.getString("tiempo") + "  minutos");
                        lb_titulo_encuesta.setText(rs.getString("encuesta"));
                        this.tiempo_cuestionario = Integer.parseInt(rs.getString("tiempo"));
                        lb_pregunta_1.setText(rs.getString("titulo"));
                        resp_1 = rs.getString("respuesta");

                        check_p1_1.setText(rs.getString("valor"));

                        break;
                    case 2:
                        check_p1_2.setText(rs.getString("valor"));
                        break;
                    case 3:
                        check_p1_3.setText(rs.getString("valor"));
                        break;
                    case 4:
                        check_p1_4.setText(rs.getString("valor"));
                        break;

                    case 5:
                        resp_2 = rs.getString("respuesta");

                        lb_pregunta_2.setText(rs.getString("titulo"));
                        check_p2_1.setText(rs.getString("valor"));
                        break;
                    case 6:
                        check_p2_2.setText(rs.getString("valor"));
                        break;
                    case 7:
                        check_p2_3.setText(rs.getString("valor"));
                        break;
                    case 8:
                        check_p2_4.setText(rs.getString("valor"));
                        break;

                    case 9:
                        resp_3 = rs.getString("respuesta");

                        lb_pregunta_3.setText(rs.getString("titulo"));
                        check_p3_1.setText(rs.getString("valor"));
                        break;

                    case 10:
                        check_p3_2.setText(rs.getString("valor"));
                        break;
                    case 11:
                        check_p3_3.setText(rs.getString("valor"));
                        break;
                    case 12:
                        check_p3_4.setText(rs.getString("valor"));
                        break;

                    case 13:
                        resp_4 = rs.getString("respuesta");

                        lb_pregunta4.setText(rs.getString("titulo"));
                        check_p4_1.setText(rs.getString("valor"));
                        break;

                    case 14:
                        check_p4_2.setText(rs.getString("valor"));
                        break;
                    case 15:
                        check_p4_3.setText(rs.getString("valor"));
                        break;

                    case 16:
                        check_p4_4.setText(rs.getString("valor"));
                        break;
                    case 17:
                        resp_5 = rs.getString("respuesta");

                        lb_pregunta_5.setText(rs.getString("titulo"));
                        check_p5_1.setText(rs.getString("valor"));
                        break;
                    case 18:
                        check_p5_2.setText(rs.getString("valor"));
                        break;

                    case 19:
                        check_p5_3.setText(rs.getString("valor"));
                        break;

                    case 20:
                        check_p5_4.setText(rs.getString("valor"));
                        break;

                }

            }

            if (contador == 0) {
                lb_titulo_encuesta.setText("NO SE ENCONTRARON REGISTROS");
                this.js_preguntas.setVisible(false);
            }

        } catch (SQLException ex) {
            Logger.getLogger(RegistroPreguntas.class.getName()).log(Level.SEVERE, null, ex);
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
        panel_indicaciones = new javax.swing.JPanel();
        lb_indicaciones = new javax.swing.JLabel();
        lb_titulo_encuesta = new javax.swing.JLabel();
        js_preguntas = new javax.swing.JScrollPane();
        jPanel7 = new javax.swing.JPanel();
        lb_pregunta_1 = new javax.swing.JLabel();
        check_p1_1 = new javax.swing.JCheckBox();
        check_p1_2 = new javax.swing.JCheckBox();
        check_p1_3 = new javax.swing.JCheckBox();
        check_p1_4 = new javax.swing.JCheckBox();
        lb_pregunta_2 = new javax.swing.JLabel();
        check_p2_1 = new javax.swing.JCheckBox();
        check_p2_2 = new javax.swing.JCheckBox();
        check_p2_3 = new javax.swing.JCheckBox();
        check_p2_4 = new javax.swing.JCheckBox();
        lb_pregunta_3 = new javax.swing.JLabel();
        check_p3_1 = new javax.swing.JCheckBox();
        check_p3_2 = new javax.swing.JCheckBox();
        check_p3_3 = new javax.swing.JCheckBox();
        check_p3_4 = new javax.swing.JCheckBox();
        lb_pregunta4 = new javax.swing.JLabel();
        check_p4_1 = new javax.swing.JCheckBox();
        check_p4_2 = new javax.swing.JCheckBox();
        check_p4_3 = new javax.swing.JCheckBox();
        check_p4_4 = new javax.swing.JCheckBox();
        lb_pregunta_5 = new javax.swing.JLabel();
        check_p5_1 = new javax.swing.JCheckBox();
        check_p5_2 = new javax.swing.JCheckBox();
        check_p5_3 = new javax.swing.JCheckBox();
        check_p5_4 = new javax.swing.JCheckBox();
        panel_encabezado = new javax.swing.JPanel();
        lb_temporizador = new javax.swing.JLabel();
        lb_nombres_participantes = new javax.swing.JLabel();
        lb_tiempo_cuestionario = new javax.swing.JLabel();
        btn_terminar = new javax.swing.JButton();

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
        setTitle("EMPEZAR INTENTO");

        panel_indicaciones.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("")));

        lb_indicaciones.setForeground(new java.awt.Color(102, 102, 102));
        lb_indicaciones.setText("Importante leer");
        lb_indicaciones.setToolTipText("");

        lb_titulo_encuesta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_titulo_encuesta.setForeground(new java.awt.Color(80, 80, 97));
        lb_titulo_encuesta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_titulo_encuesta.setText("Preguntas sobre el concepto de Contabilidad y sus Principios");
        lb_titulo_encuesta.setToolTipText("");

        javax.swing.GroupLayout panel_indicacionesLayout = new javax.swing.GroupLayout(panel_indicaciones);
        panel_indicaciones.setLayout(panel_indicacionesLayout);
        panel_indicacionesLayout.setHorizontalGroup(
            panel_indicacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_indicacionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_indicacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_indicaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_titulo_encuesta, javax.swing.GroupLayout.DEFAULT_SIZE, 915, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel_indicacionesLayout.setVerticalGroup(
            panel_indicacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_indicacionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_titulo_encuesta, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lb_indicaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("")));

        lb_pregunta_1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_pregunta_1.setForeground(new java.awt.Color(80, 80, 97));
        lb_pregunta_1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_pregunta_1.setText("<html>¿Cuál es la primera actividad a realizar dentro del método contable?<html>");
        lb_pregunta_1.setToolTipText("");
        lb_pregunta_1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        check_p1_1.setText("Identificar los elementos patrimoniales afectados");
        check_p1_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_p1_1ActionPerformed(evt);
            }
        });

        check_p1_2.setText("Identificar los elementos patrimoniales afectados");
        check_p1_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_p1_2ActionPerformed(evt);
            }
        });

        check_p1_3.setText("Identificar los elementos patrimoniales afectados");
        check_p1_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_p1_3ActionPerformed(evt);
            }
        });

        check_p1_4.setText("Identificar los elementos patrimoniales afectados");
        check_p1_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_p1_4ActionPerformed(evt);
            }
        });

        lb_pregunta_2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_pregunta_2.setForeground(new java.awt.Color(80, 80, 97));
        lb_pregunta_2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_pregunta_2.setText("<html>¿Cuál es la primera actividad a realizar dentro del método contable?<html>");
        lb_pregunta_2.setToolTipText("");
        lb_pregunta_2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        check_p2_1.setText("Identificar los elementos patrimoniales afectados");
        check_p2_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_p2_1ActionPerformed(evt);
            }
        });

        check_p2_2.setText("Identificar los elementos patrimoniales afectados");
        check_p2_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_p2_2ActionPerformed(evt);
            }
        });

        check_p2_3.setText("Identificar los elementos patrimoniales afectados");
        check_p2_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_p2_3ActionPerformed(evt);
            }
        });

        check_p2_4.setText("Identificar los elementos patrimoniales afectados");
        check_p2_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_p2_4ActionPerformed(evt);
            }
        });

        lb_pregunta_3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_pregunta_3.setForeground(new java.awt.Color(80, 80, 97));
        lb_pregunta_3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_pregunta_3.setText("<html>¿Cuál es la primera actividad a realizar dentro del método contable?<html>");
        lb_pregunta_3.setToolTipText("");
        lb_pregunta_3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        check_p3_1.setText("Identificar los elementos patrimoniales afectados");
        check_p3_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_p3_1ActionPerformed(evt);
            }
        });

        check_p3_2.setText("Identificar los elementos patrimoniales afectados");
        check_p3_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_p3_2ActionPerformed(evt);
            }
        });

        check_p3_3.setText("Identificar los elementos patrimoniales afectados");
        check_p3_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_p3_3ActionPerformed(evt);
            }
        });

        check_p3_4.setText("Identificar los elementos patrimoniales afectados");
        check_p3_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_p3_4ActionPerformed(evt);
            }
        });

        lb_pregunta4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_pregunta4.setForeground(new java.awt.Color(80, 80, 97));
        lb_pregunta4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_pregunta4.setText("<html>¿Cuál es la primera actividad a realizar dentro del método contable?<html>");
        lb_pregunta4.setToolTipText("");
        lb_pregunta4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        check_p4_1.setText("Identificar los elementos patrimoniales afectados");
        check_p4_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_p4_1ActionPerformed(evt);
            }
        });

        check_p4_2.setText("Identificar los elementos patrimoniales afectados");
        check_p4_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_p4_2ActionPerformed(evt);
            }
        });

        check_p4_3.setText("Identificar los elementos patrimoniales afectados");
        check_p4_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_p4_3ActionPerformed(evt);
            }
        });

        check_p4_4.setText("Identificar los elementos patrimoniales afectados");
        check_p4_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_p4_4ActionPerformed(evt);
            }
        });

        lb_pregunta_5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_pregunta_5.setForeground(new java.awt.Color(80, 80, 97));
        lb_pregunta_5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lb_pregunta_5.setText("<html>¿Cuál es la primera actividad a realizar dentro del método contable?<html>");
        lb_pregunta_5.setToolTipText("");
        lb_pregunta_5.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        check_p5_1.setText("Identificar los elementos patrimoniales afectados");
        check_p5_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_p5_1ActionPerformed(evt);
            }
        });

        check_p5_2.setText("Identificar los elementos patrimoniales afectados");
        check_p5_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_p5_2ActionPerformed(evt);
            }
        });

        check_p5_3.setText("Identificar los elementos patrimoniales afectados");
        check_p5_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_p5_3ActionPerformed(evt);
            }
        });

        check_p5_4.setText("Identificar los elementos patrimoniales afectados");
        check_p5_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_p5_4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_pregunta_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_pregunta_3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_pregunta4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_pregunta_5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_pregunta_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(check_p1_1, javax.swing.GroupLayout.DEFAULT_SIZE, 908, Short.MAX_VALUE)
                    .addComponent(check_p1_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(check_p1_3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(check_p1_4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(check_p2_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(check_p2_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(check_p3_1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
                                .addComponent(check_p3_2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(check_p3_3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(check_p4_1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(check_p4_2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(check_p4_3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(check_p5_1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(check_p5_2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(check_p5_3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(check_p5_4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(check_p4_4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(check_p3_4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(check_p2_3, javax.swing.GroupLayout.PREFERRED_SIZE, 899, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(check_p2_4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(lb_pregunta_1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(check_p1_1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(check_p1_2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(check_p1_3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(check_p1_4)
                .addGap(18, 18, 18)
                .addComponent(lb_pregunta_2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(check_p2_1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(check_p2_2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(check_p2_3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(check_p2_4)
                .addGap(18, 18, 18)
                .addComponent(lb_pregunta_3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(check_p3_1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(check_p3_2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(check_p3_3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(check_p3_4)
                .addGap(18, 18, 18)
                .addComponent(lb_pregunta4, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(check_p4_1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(check_p4_2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(check_p4_3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(check_p4_4)
                .addGap(18, 18, 18)
                .addComponent(lb_pregunta_5, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(check_p5_1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(check_p5_2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(check_p5_3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(check_p5_4)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        js_preguntas.setViewportView(jPanel7);

        panel_encabezado.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("")));

        lb_temporizador.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        lb_temporizador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lb_nombres_participantes.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lb_nombres_participantes.setForeground(new java.awt.Color(102, 102, 102));
        lb_nombres_participantes.setText("Participante:");
        lb_nombres_participantes.setToolTipText("");

        lb_tiempo_cuestionario.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lb_tiempo_cuestionario.setForeground(new java.awt.Color(102, 102, 102));
        lb_tiempo_cuestionario.setText("Tiempo:");

        javax.swing.GroupLayout panel_encabezadoLayout = new javax.swing.GroupLayout(panel_encabezado);
        panel_encabezado.setLayout(panel_encabezadoLayout);
        panel_encabezadoLayout.setHorizontalGroup(
            panel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_encabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lb_nombres_participantes, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                    .addComponent(lb_tiempo_cuestionario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(40, 40, 40)
                .addComponent(lb_temporizador, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel_encabezadoLayout.setVerticalGroup(
            panel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_encabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lb_temporizador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panel_encabezadoLayout.createSequentialGroup()
                        .addComponent(lb_nombres_participantes, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lb_tiempo_cuestionario, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(57, 57, 57))
        );

        btn_terminar.setText("Finalizar");
        btn_terminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_terminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(js_preguntas)
                    .addComponent(panel_indicaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_terminar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panel_encabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(panel_indicaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(js_preguntas, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_terminar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panel_encabezado, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(545, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btn_terminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_terminarActionPerformed
    this.hilo.stop();
    calificar();


}//GEN-LAST:event_btn_terminarActionPerformed

    private void GuardarOpciones() {
        Conexion conexion = new Conexion();
        conexion.conectar();
        int id_pregunta = 0;
        String opcion1, opcion2, opcion3, opcion4;

    }
private void mneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mneliminarActionPerformed
// TODO add your handling code here:


}//GEN-LAST:event_mneliminarActionPerformed

private void mnmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnmodificarActionPerformed
// "CÓDIGO", "CÉDULA", "NOMBRES", "APELLIDOS", "DIRECCIÓN", "TELEFONO", "PLACA", "MODELO", "COLOR"    


}//GEN-LAST:event_mnmodificarActionPerformed

    private void check_p1_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_p1_1ActionPerformed
        CheckSelectedStatus(check_p1_2, check_p1_3, check_p1_4);
    }//GEN-LAST:event_check_p1_1ActionPerformed

    private void check_p1_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_p1_2ActionPerformed
        CheckSelectedStatus(check_p1_1, check_p1_3, check_p1_4);
    }//GEN-LAST:event_check_p1_2ActionPerformed

    private void check_p1_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_p1_3ActionPerformed
        CheckSelectedStatus(check_p1_2, check_p1_1, check_p1_4);
    }//GEN-LAST:event_check_p1_3ActionPerformed

    private void check_p1_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_p1_4ActionPerformed
        CheckSelectedStatus(check_p1_2, check_p1_3, check_p1_1);
    }//GEN-LAST:event_check_p1_4ActionPerformed

    private void check_p2_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_p2_1ActionPerformed
        CheckSelectedStatus(check_p2_2, check_p2_3, check_p2_4);
    }//GEN-LAST:event_check_p2_1ActionPerformed

    private void check_p2_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_p2_2ActionPerformed
        CheckSelectedStatus(check_p2_1, check_p2_3, check_p2_4);
    }//GEN-LAST:event_check_p2_2ActionPerformed

    private void check_p2_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_p2_3ActionPerformed
        CheckSelectedStatus(check_p2_2, check_p2_1, check_p2_4);
    }//GEN-LAST:event_check_p2_3ActionPerformed

    private void check_p2_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_p2_4ActionPerformed
        CheckSelectedStatus(check_p2_2, check_p2_3, check_p2_1);
    }//GEN-LAST:event_check_p2_4ActionPerformed

    private void check_p3_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_p3_1ActionPerformed
        CheckSelectedStatus(check_p3_2, check_p3_3, check_p3_4);
    }//GEN-LAST:event_check_p3_1ActionPerformed

    private void check_p3_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_p3_2ActionPerformed
        CheckSelectedStatus(check_p3_1, check_p3_3, check_p3_4);
    }//GEN-LAST:event_check_p3_2ActionPerformed

    private void check_p3_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_p3_3ActionPerformed
        CheckSelectedStatus(check_p3_2, check_p3_1, check_p3_4);
    }//GEN-LAST:event_check_p3_3ActionPerformed

    private void check_p3_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_p3_4ActionPerformed
        CheckSelectedStatus(check_p3_2, check_p3_3, check_p3_1);
    }//GEN-LAST:event_check_p3_4ActionPerformed

    private void check_p4_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_p4_1ActionPerformed
        CheckSelectedStatus(check_p4_2, check_p4_3, check_p4_4);
    }//GEN-LAST:event_check_p4_1ActionPerformed

    private void check_p4_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_p4_2ActionPerformed
        CheckSelectedStatus(check_p4_1, check_p4_3, check_p4_4);
    }//GEN-LAST:event_check_p4_2ActionPerformed

    private void check_p4_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_p4_3ActionPerformed
        CheckSelectedStatus(check_p4_2, check_p4_1, check_p4_4);
    }//GEN-LAST:event_check_p4_3ActionPerformed

    private void check_p4_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_p4_4ActionPerformed
        CheckSelectedStatus(check_p4_2, check_p4_3, check_p4_1);
    }//GEN-LAST:event_check_p4_4ActionPerformed

    private void check_p5_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_p5_1ActionPerformed
        CheckSelectedStatus(check_p5_2, check_p5_3, check_p5_4);
    }//GEN-LAST:event_check_p5_1ActionPerformed

    private void check_p5_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_p5_2ActionPerformed
        CheckSelectedStatus(check_p5_1, check_p5_3, check_p5_4);
    }//GEN-LAST:event_check_p5_2ActionPerformed

    private void check_p5_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_p5_3ActionPerformed
        CheckSelectedStatus(check_p5_2, check_p5_1, check_p5_4);
    }//GEN-LAST:event_check_p5_3ActionPerformed

    private void check_p5_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_p5_4ActionPerformed
        CheckSelectedStatus(check_p5_2, check_p5_3, check_p5_1);
    }//GEN-LAST:event_check_p5_4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_terminar;
    private javax.swing.JCheckBox check_p1_1;
    private javax.swing.JCheckBox check_p1_2;
    private javax.swing.JCheckBox check_p1_3;
    private javax.swing.JCheckBox check_p1_4;
    private javax.swing.JCheckBox check_p2_1;
    private javax.swing.JCheckBox check_p2_2;
    private javax.swing.JCheckBox check_p2_3;
    private javax.swing.JCheckBox check_p2_4;
    private javax.swing.JCheckBox check_p3_1;
    private javax.swing.JCheckBox check_p3_2;
    private javax.swing.JCheckBox check_p3_3;
    private javax.swing.JCheckBox check_p3_4;
    private javax.swing.JCheckBox check_p4_1;
    private javax.swing.JCheckBox check_p4_2;
    private javax.swing.JCheckBox check_p4_3;
    private javax.swing.JCheckBox check_p4_4;
    private javax.swing.JCheckBox check_p5_1;
    private javax.swing.JCheckBox check_p5_2;
    private javax.swing.JCheckBox check_p5_3;
    private javax.swing.JCheckBox check_p5_4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane js_preguntas;
    private javax.swing.JLabel lb_indicaciones;
    private javax.swing.JLabel lb_nombres_participantes;
    private javax.swing.JLabel lb_pregunta4;
    private javax.swing.JLabel lb_pregunta_1;
    private javax.swing.JLabel lb_pregunta_2;
    private javax.swing.JLabel lb_pregunta_3;
    private javax.swing.JLabel lb_pregunta_5;
    private javax.swing.JLabel lb_temporizador;
    private javax.swing.JLabel lb_tiempo_cuestionario;
    private javax.swing.JLabel lb_titulo_encuesta;
    private javax.swing.JMenuItem mneliminar;
    private javax.swing.JMenuItem mnmodificar;
    private javax.swing.JPanel panel_encabezado;
    private javax.swing.JPanel panel_indicaciones;
    // End of variables declaration//GEN-END:variables

}
