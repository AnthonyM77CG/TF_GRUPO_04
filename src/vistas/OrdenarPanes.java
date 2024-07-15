package vistas;

import entidades.Pan;
import conexion.PanDao;
import metodos.MetPan;
import metodos.Traduccion;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.ResourceBundle;
import metodos.AlgoritmoSort;

public class OrdenarPanes extends JFrame {
    

    private MetPan metodoSort;

    public OrdenarPanes() {
        // Inicializa la tabla y otros componentes
        initComponents();
        actualizarIdioma();
        metodoSort = new MetPan();
        cargarDatos();
    }

    private void actualizarIdioma() {
        ResourceBundle bundle = Traduccion.getBundle();
        lblTitulo.setText(bundle.getString("stock.Titulo"));
        lblOrdenarnombre.setText(bundle.getString("stock.subtitulo.Ordenombre"));
        lblOrdenarprecio.setText(bundle.getString("stock.subtitulo.Ordenprecio"));
        lblBuscarnombre.setText(bundle.getString("stock.subtitulo.Buscnombre"));
        lblBuscarprecio.setText(bundle.getString("stock.subtitulo.Buscprecio"));
        btnCerrar.setText(bundle.getString("boton.Cerrar"));
        btnOriginal.setText(bundle.getString("stock.boton.Original"));
        btnAscendenteNombre.setText(bundle.getString("stock.boton.Ascendente"));
        btnAscendentePrecio.setText(bundle.getString("stock.boton.Ascendente"));
        btnDescendenteNombre.setText(bundle.getString("stock.boton.Descendente"));
        btnDescendenterPrecio.setText(bundle.getString("stock.boton.Descendente"));
        btnBusquedapornombre.setText(bundle.getString("stock.boton.Buscar"));
        btnBusquedaporprecio.setText(bundle.getString("stock.boton.Buscar"));
        btnRegresar.setText(bundle.getString("boton.Regresar"));
        DefaultTableModel modelo = (DefaultTableModel) tblOrdenamiento.getModel();
        modelo.setColumnIdentifiers(new String[]{
            bundle.getString("stock.tabla.Pan"),
            bundle.getString("stock.tabla.Cantidad"),
            bundle.getString("stock.tabla.Precio")
        });
        setLocationRelativeTo(null);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblOrdenamiento = new javax.swing.JTable();
        lblOrdenarprecio = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        lblOrdenarnombre = new javax.swing.JLabel();
        btnAscendenteNombre = new javax.swing.JButton();
        btnDescendenteNombre = new javax.swing.JButton();
        btnAscendentePrecio = new javax.swing.JButton();
        btnDescendenterPrecio = new javax.swing.JButton();
        btnBusquedapornombre = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        lblBuscarprecio = new javax.swing.JLabel();
        lblBuscarnombre = new javax.swing.JLabel();
        btnBusquedaporprecio = new javax.swing.JButton();
        txtnombre = new javax.swing.JTextField();
        txtprecio = new javax.swing.JTextField();
        btnOriginal = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 255));

        tblOrdenamiento.setBackground(new java.awt.Color(255, 255, 204));
        tblOrdenamiento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Pan", "Cantidad Disponible", "Precio por unidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblOrdenamiento);

        lblOrdenarprecio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblOrdenarprecio.setText("Ordenar por Precio:");

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo.setText("O R D E N A M I E N T O : ");

        lblOrdenarnombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblOrdenarnombre.setText("Ordenar por Nombre:");

        btnAscendenteNombre.setText("Ascendente");
        btnAscendenteNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAscendenteNombreActionPerformed(evt);
            }
        });

        btnDescendenteNombre.setText("Descendente");
        btnDescendenteNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescendenteNombreActionPerformed(evt);
            }
        });

        btnAscendentePrecio.setText("Ascendente");
        btnAscendentePrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAscendentePrecioActionPerformed(evt);
            }
        });

        btnDescendenterPrecio.setText("Descendente");
        btnDescendenterPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescendenterPrecioActionPerformed(evt);
            }
        });

        btnBusquedapornombre.setText("Buscar");
        btnBusquedapornombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusquedapornombreActionPerformed(evt);
            }
        });

        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        lblBuscarprecio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblBuscarprecio.setText("Buscar por Precio: ");

        lblBuscarnombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblBuscarnombre.setText("Buscar por Nombre: ");

        btnBusquedaporprecio.setText("Buscar");
        btnBusquedaporprecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusquedaporprecioActionPerformed(evt);
            }
        });

        txtnombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnombreActionPerformed(evt);
            }
        });

        txtprecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtprecioActionPerformed(evt);
            }
        });

        btnOriginal.setText("Original");
        btnOriginal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOriginalActionPerformed(evt);
            }
        });

        btnCerrar.setBackground(new java.awt.Color(255, 102, 102));
        btnCerrar.setText("jButton1");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(18, 18, 18)
                            .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(btnAscendentePrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnDescendenterPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblOrdenarnombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(btnAscendenteNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(40, 40, 40)
                                    .addComponent(btnDescendenteNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(lblOrdenarprecio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnRegresar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnOriginal, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblBuscarprecio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblBuscarnombre))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBusquedapornombre, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBusquedaporprecio, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblOrdenarnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAscendenteNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDescendenteNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblOrdenarprecio, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAscendentePrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDescendenterPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOriginal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBuscarnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBusquedapornombre, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBusquedaporprecio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBuscarprecio, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
               private void cargarDatos() {
    PanDao panDAO = new PanDao();
    List<Pan> listaPanes = panDAO.obtenerPanes();
    actualizarTabla(listaPanes);
}


    private void ordenarNombreAscendente() {
        PanDao panDAO = new PanDao();
        List<Pan> listaPanes = panDAO.obtenerPanes();
        metodoSort.sort(listaPanes, 0, listaPanes.size() - 1, true); // True para ascendente
        actualizarTabla(listaPanes);
    }

    private void ordenarNombreDescendente() {
        PanDao panDAO = new PanDao();
        List<Pan> listaPanes = panDAO.obtenerPanes();
        metodoSort.sort(listaPanes, 0, listaPanes.size() - 1, false); // False para descendente
        actualizarTabla(listaPanes);
    }

    private void ordenarPrecioAscendente() {
        PanDao panDAO = new PanDao();
        List<Pan> listaPanes = panDAO.obtenerPanes();
        metodoSort.sortPorPrecio(listaPanes, 0, listaPanes.size() - 1, true); // True para ascendente
        actualizarTabla(listaPanes);
    }

    private void ordenarPrecioDescendente() {
        PanDao panDAO = new PanDao();
        List<Pan> listaPanes = panDAO.obtenerPanes();
        metodoSort.sortPorPrecio(listaPanes, 0, listaPanes.size() - 1, false); // False para descendente
        actualizarTabla(listaPanes);
    }

    private void buscarPorNombre(String nombre) {
    if (nombre.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Ingrese un nombre válido.");
        return;
    }

    PanDao panDAO = new PanDao();
    List<Pan> listaPanes = panDAO.obtenerPanes();
    List<Pan> panesEncontrados = new ArrayList<>();

    for (Pan pan : listaPanes) {
        if (pan.getNombre().equalsIgnoreCase(nombre)) {
            panesEncontrados.add(pan);
        }
    }

    if (panesEncontrados.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Pan no encontrado.");
    } else {
        actualizarTabla(panesEncontrados);
    }
}

private void buscarPorPrecio(double precio) {
    PanDao panDAO = new PanDao();
    List<Pan> listaPanes = panDAO.obtenerPanes();
    List<Pan> panesEncontrados = new ArrayList<>();
    
    for (Pan pan : listaPanes) {
        if (pan.getPrecio() == precio) {
            panesEncontrados.add(pan);
        }
    }
    
    if (panesEncontrados.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Precio no encontrado.");
    } else {
        actualizarTabla(panesEncontrados);
    }
}


   private void actualizarTabla(List<Pan> listaPanes) {
    DefaultTableModel modelo = (DefaultTableModel) tblOrdenamiento.getModel();
    modelo.setRowCount(0);
    for (Pan pan : listaPanes) {
        modelo.addRow(new Object[]{pan.getNombre(), pan.getCantidad(), pan.getPrecio()});
    }
}




        

   
    private void btnAscendenteNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAscendenteNombreActionPerformed
        ordenarNombreAscendente();
    }//GEN-LAST:event_btnAscendenteNombreActionPerformed

    private void btnDescendenteNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescendenteNombreActionPerformed
        ordenarNombreDescendente();
    }//GEN-LAST:event_btnDescendenteNombreActionPerformed
                
    private void btnAscendentePrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAscendentePrecioActionPerformed
         ordenarPrecioAscendente();
    }//GEN-LAST:event_btnAscendentePrecioActionPerformed

    private void btnDescendenterPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescendenterPrecioActionPerformed
        ordenarPrecioDescendente();
    }//GEN-LAST:event_btnDescendenterPrecioActionPerformed

    private void btnBusquedapornombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusquedapornombreActionPerformed
       buscarPorNombre(txtnombre.getText().trim());
    txtnombre.setText("");  // Limpiar el campo de texto después de la búsqueda
    }//GEN-LAST:event_btnBusquedapornombreActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        Menú menu = new Menú();
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnBusquedaporprecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusquedaporprecioActionPerformed
        buscarPorPrecio(Double.parseDouble(txtprecio.getText().trim()));
        txtprecio.setText("");
    }//GEN-LAST:event_btnBusquedaporprecioActionPerformed

    private void txtnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombreActionPerformed

    private void txtprecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtprecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtprecioActionPerformed

    private void btnOriginalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOriginalActionPerformed
        cargarDatos();
    }//GEN-LAST:event_btnOriginalActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        LoginPanaderia login = new LoginPanaderia();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

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
            java.util.logging.Logger.getLogger(OrdenarPanes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrdenarPanes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrdenarPanes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrdenarPanes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OrdenarPanes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAscendenteNombre;
    private javax.swing.JButton btnAscendentePrecio;
    private javax.swing.JButton btnBusquedapornombre;
    private javax.swing.JButton btnBusquedaporprecio;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnDescendenteNombre;
    private javax.swing.JButton btnDescendenterPrecio;
    private javax.swing.JButton btnOriginal;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBuscarnombre;
    private javax.swing.JLabel lblBuscarprecio;
    private javax.swing.JLabel lblOrdenarnombre;
    private javax.swing.JLabel lblOrdenarprecio;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tblOrdenamiento;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtprecio;
    // End of variables declaration//GEN-END:variables
}
