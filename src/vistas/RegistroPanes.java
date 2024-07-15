package vistas;

import conexion.PanDao;
import entidades.Pan;
import metodos.Traduccion;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class RegistroPanes extends javax.swing.JFrame {

    private PanDao panDao;

    public RegistroPanes() {
        initComponents();
        actualizarIdioma();
        panDao = new PanDao();
        cargarPanes();
        txtid.setEditable(false); 

        tblPanes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablapanesMouseClicked(evt);
            }
        });
    }
    
    private void actualizarIdioma() {
        ResourceBundle bundle = Traduccion.getBundle();
        lblTitulo.setText(bundle.getString("registro.panes.Titulo"));
        lblId.setText(bundle.getString("registro.panes.Subtitulo.Id"));
        lblPandisponible.setText(bundle.getString("registro.panes.Subtitulo.pan"));
        lblCantidad.setText(bundle.getString("registro.panes.Subtitulo.cantidad"));
        lblPrecio.setText(bundle.getString("registro.panes.Subtitulo.precio"));
        btnCerrar.setText(bundle.getString("boton.Cerrar"));
        btnAgregar.setText(bundle.getString("registro.panes.boton.Guardar"));
        btnModificar.setText(bundle.getString("registro.panes.boton.Modificar"));
        btnEliminar.setText(bundle.getString("registro.panes.boton.Eliminar"));
        btnLimpiar.setText(bundle.getString("registro.panes.boton.Limpiar"));
        btnRegresar.setText(bundle.getString("boton.Regresar"));
        DefaultTableModel modelo = (DefaultTableModel) tblPanes.getModel();
        modelo.setColumnIdentifiers(new String[]{
            bundle.getString("registro.panes.tabla.Id"),
            bundle.getString("registro.panes.tabla.Panes"),
            bundle.getString("registro.panes.tabla.Cantidad"),
            bundle.getString("registro.panes.tabla.Precio")
        });
        setLocationRelativeTo(null);
    }
    
    private void cargarPanes() {
        List<Pan> panes = panDao.obtenerPanes();
        DefaultTableModel modelo = (DefaultTableModel) tblPanes.getModel();
        modelo.setRowCount(0);  // Limpia el modelo antes de agregar filas

        for (Pan pan : panes) {
            modelo.addRow(new Object[]{pan.getIdPanes(), pan.getNombre(), pan.getCantidad(), pan.getPrecio()});
        }
    }

    private void tablapanesMouseClicked(java.awt.event.MouseEvent evt) {
        int row = tblPanes.rowAtPoint(evt.getPoint());
        txtid.setText(tblPanes.getValueAt(row, 0).toString());
        txtNombre.setText(tblPanes.getValueAt(row, 1).toString());
        txtCantidad.setText(tblPanes.getValueAt(row, 2).toString());
        txtPrecio.setText(tblPanes.getValueAt(row, 3).toString());
    }
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPanes = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtid = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtNombre = new javax.swing.JTextPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtPrecio = new javax.swing.JTextPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtCantidad = new javax.swing.JTextPane();
        lblPandisponible = new javax.swing.JLabel();
        lblPrecio = new javax.swing.JLabel();
        lblCantidad = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        lblId = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo.setText("REGISTRO DE PANES");

        tblPanes.setBackground(new java.awt.Color(255, 255, 204));
        tblPanes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Panes Disponibles", "Cantidad Disponible", "Precio por unidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblPanes);

        txtid.setBackground(new java.awt.Color(255, 255, 204));
        jScrollPane2.setViewportView(txtid);

        txtNombre.setBackground(new java.awt.Color(255, 255, 204));
        jScrollPane3.setViewportView(txtNombre);

        txtPrecio.setBackground(new java.awt.Color(255, 255, 204));
        jScrollPane4.setViewportView(txtPrecio);

        txtCantidad.setBackground(new java.awt.Color(255, 255, 204));
        jScrollPane5.setViewportView(txtCantidad);

        lblPandisponible.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPandisponible.setText("PAN DISPONIBLE:");

        lblPrecio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPrecio.setText("PRECIO POR UNIDAD:");

        lblCantidad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCantidad.setText("CANTIDAD DISPONIBLE:");

        btnAgregar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAgregar.setText("GUARDAR");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnModificar.setText("MODIFICAR");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        lblId.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblId.setText("ID DEL PAN:");

        btnCerrar.setBackground(new java.awt.Color(255, 102, 102));
        btnCerrar.setText("jButton1");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        btnRegresar.setText("jButton2");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(35, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblPrecio)
                                    .addComponent(lblPandisponible, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblId))
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnLimpiar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(btnAgregar)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnModificar)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnEliminar))))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblPandisponible, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        String nombre = txtNombre.getText();
        int cantidad;
        double precio;

        try {
            cantidad = Integer.parseInt(txtCantidad.getText());
            precio = Double.parseDouble(txtPrecio.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese una cantidad y un precio válidos.", "Error en la entrada", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un nombre para el pan.", "Error en la entrada", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (panDao.insertarPan(nombre, cantidad, precio)) {
            JOptionPane.showMessageDialog(this, "Pan agregado con éxito.");
            cargarPanes();
        } else {
            JOptionPane.showMessageDialog(this, "Error al agregar el pan.");
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
                        int filaSeleccionada = tblPanes.getSelectedRow();
                    if (filaSeleccionada == -1) {
                        JOptionPane.showMessageDialog(this, "Seleccione un pan para modificar.");
                        return;
                    }


                    int id = ((Number) tblPanes.getValueAt(filaSeleccionada, 0)).intValue(); 
                    String nombre = txtNombre.getText();
                    int cantidad;
                    double precio;


                    try {
                        cantidad = Integer.parseInt(txtCantidad.getText());
                        precio = Double.parseDouble(txtPrecio.getText());
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Por favor, ingrese una cantidad y un precio válidos.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (nombre.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Por favor, ingrese un nombre para el pan.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (panDao.modificarPan(id, nombre, cantidad, precio)) {
                        JOptionPane.showMessageDialog(this, "Pan modificado con éxito.");
                        cargarPanes();
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al modificar el pan.");
                    }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
                int filaSeleccionada = tblPanes.getSelectedRow();
             if (filaSeleccionada == -1) {
                 JOptionPane.showMessageDialog(this, "Seleccione un pan para eliminar.");
                 return;
             }
             try {
                 int id = Integer.parseInt(tblPanes.getValueAt(filaSeleccionada, 0).toString());
                 if (panDao.eliminarPan(id)) {
                     JOptionPane.showMessageDialog(this, "Pan eliminado con éxito.");
                     cargarPanes();
                 } else {
                     JOptionPane.showMessageDialog(this, "Error al eliminar el pan.");
                 }
             } catch (NumberFormatException e) {
                 JOptionPane.showMessageDialog(this, "Error al obtener el ID del pan.", "Error en la entrada", JOptionPane.ERROR_MESSAGE);
             }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        txtNombre.setText("");
        txtCantidad.setText("");
        txtPrecio.setText("");
        txtid.setText("");
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        LoginPanaderia login = new LoginPanaderia();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        Menú menu = new Menú();
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

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
            java.util.logging.Logger.getLogger(RegistroPanes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroPanes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroPanes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroPanes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistroPanes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblPandisponible;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tblPanes;
    private javax.swing.JTextPane txtCantidad;
    private javax.swing.JTextPane txtNombre;
    private javax.swing.JTextPane txtPrecio;
    private javax.swing.JTextPane txtid;
    // End of variables declaration//GEN-END:variables
}
