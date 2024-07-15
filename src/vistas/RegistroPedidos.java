package vistas;

import conexion.ConexionBd;
import metodos.Traduccion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.ResourceBundle;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class RegistroPedidos extends javax.swing.JDialog {

    public RegistroPedidos(java.awt.Frame parent, boolean modal) {
    super(parent, modal);
    initComponents();
    actualizarIdioma();
    cargarPanesDisponibles();
    cargarPedidos();
    txtventasID.setEditable(false);
    txtCantidadDisponible.setEditable(false);
    txtPrecioUnidad.setEditable(false);
    txtTotal.setEditable(false);

    comboboxpanes.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            comboboxpanesActionPerformed(evt);
        }
    });

    txtCantidadPedido.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            calcularTotal();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            calcularTotal();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            calcularTotal();
        }
    });

    tblPedidos.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent evt) {
            tblpedidosMouseClicked(evt);
        }
    });

    txtBusquedaCodigo.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            buscarPorCodigo();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            buscarPorCodigo();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            buscarPorCodigo();
        }
    });
}

    private void actualizarIdioma() {
        ResourceBundle bundle = Traduccion.getBundle();
        lblTitulo.setText(bundle.getString("registro.pedido.Titulo"));
        lblIdpedido.setText(bundle.getString("registro.pedido.subtitulo.Id"));
        lblPan.setText(bundle.getString("registro.pedido.subtitulo.Pan"));
        lblCantidaddisponible.setText(bundle.getString("registro.pedido.subtitulo.Cantidaddispo"));
        lblCantidadpedir.setText(bundle.getString("registro.pedido.subtitulo.Cantidadpedir"));
        btnCerrar.setText(bundle.getString("boton.Cerrar"));
        lblPrecio.setText(bundle.getString("registro.pedido.subtitulo.Precio"));
        lblTotal.setText(bundle.getString("registro.pedido.subtitulo.Total"));
        lblBuscarcodigo.setText(bundle.getString("registro.pedido.subtitulo.Buscarcodi"));
        btnGuardarpedido.setText(bundle.getString("registro.pedido.boton.Realizar"));
        btnModificarpedido.setText(bundle.getString("registro.pedido.boton.Modificar"));
        btnCancelarpedido.setText(bundle.getString("registro.pedido.boton.Cancelar"));
        btnLimpiar.setText(bundle.getString("registro.pedido.boton.Limpiar"));
        btnRegresar.setText(bundle.getString("boton.Regresar"));
        DefaultTableModel modelo = (DefaultTableModel) tblPedidos.getModel();
        modelo.setColumnIdentifiers(new String[]{
            bundle.getString("registro.pedido.tabla.Id"),
            bundle.getString("registro.pedido.tabla.Pan"),
            bundle.getString("registro.pedido.tabla.Cantidad"),
            bundle.getString("registro.pedido.tabla.Precio"),
            bundle.getString("registro.pedido.tabla.Total")
        });
        setLocationRelativeTo(null);
    }
    
   private void buscarPorCodigo() {
    String codigo = txtBusquedaCodigo.getText().trim();

    if (codigo.isEmpty()) {
        cargarPedidos();  // Cargar todos los pedidos si el campo de búsqueda está vacío
        return;
    }

    String query = "SELECT * FROM Pedido WHERE id = ?";
    try (Connection conn = ConexionBd.establecerConexion();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, Integer.parseInt(codigo));
        try (ResultSet rs = stmt.executeQuery()) {
            DefaultTableModel modeloTabla = (DefaultTableModel) tblPedidos.getModel();
            modeloTabla.setRowCount(0);  // Limpiar la tabla antes de cargar los datos

            if (rs.next()) {
                Object[] fila = {
                    rs.getInt("id"),
                    rs.getString("tipo_pan"),
                    rs.getInt("cantidad"),
                    rs.getDouble("precio_unitario"),
                    rs.getDouble("total")
                };
                modeloTabla.addRow(fila);
            } 
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error al buscar el pedido: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}


    private void cargarPanesDisponibles() {
        try (Connection conn = ConexionBd.establecerConexion();
             PreparedStatement stmt = conn.prepareStatement("SELECT nombre FROM panes_disponibles");
             ResultSet rs = stmt.executeQuery()) {

            DefaultComboBoxModel<String> modeloComboBox = new DefaultComboBoxModel<>();
            while (rs.next()) {
                modeloComboBox.addElement(rs.getString("nombre"));
            }
            comboboxpanes.setModel(modeloComboBox);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar los panes disponibles: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void comboboxpanesActionPerformed(java.awt.event.ActionEvent evt) {
        String tipoPan = (String) comboboxpanes.getSelectedItem();
        if (tipoPan != null) {
            try (Connection conn = ConexionBd.establecerConexion();
                 PreparedStatement stmt = conn.prepareStatement("SELECT precio_unidad, cantidad_disponible FROM panes_disponibles WHERE nombre = ?")) {
                stmt.setString(1, tipoPan);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        double precioUnidad = rs.getDouble("precio_unidad");
                        int cantidadDisponible = rs.getInt("cantidad_disponible");
                        txtPrecioUnidad.setText(String.valueOf(precioUnidad));
                        txtCantidadDisponible.setText(String.valueOf(cantidadDisponible));
                        calcularTotal();  // total cliente q va pagar
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al obtener los detalles del pan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void calcularTotal() {
        try {
            int cantidad = Integer.parseInt(txtCantidadPedido.getText());
            double precioUnidad = Double.parseDouble(txtPrecioUnidad.getText());
            double total = cantidad * precioUnidad;
            txtTotal.setText(String.valueOf(total));
        } catch (NumberFormatException e) {
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPedidos = new javax.swing.JTable();
        lblPan = new javax.swing.JLabel();
        lblCantidaddisponible = new javax.swing.JLabel();
        lblPrecio = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        btnGuardarpedido = new javax.swing.JButton();
        btnModificarpedido = new javax.swing.JButton();
        btnCancelarpedido = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtventasID = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtTotal = new javax.swing.JTextPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtCantidadDisponible = new javax.swing.JTextPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtPrecioUnidad = new javax.swing.JTextPane();
        comboboxpanes = new javax.swing.JComboBox<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtCantidadPedido = new javax.swing.JTextPane();
        lblCantidadpedir = new javax.swing.JLabel();
        lblIdpedido = new javax.swing.JLabel();
        lblBuscarcodigo = new javax.swing.JLabel();
        txtBusquedaCodigo = new javax.swing.JTextField();
        btnCerrar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo.setText("P E D I  D O S");

        tblPedidos.setBackground(new java.awt.Color(255, 255, 204));
        tblPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Tipo de Pan", "Cantidad ", "Precio por Unidad", "TOTAL"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblPedidos);

        lblPan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPan.setText("TIPO DE PAN:");

        lblCantidaddisponible.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCantidaddisponible.setText("CANTIDAD DISPONIBLE:");

        lblPrecio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPrecio.setText("PRECIO POR UNIDAD:");

        lblTotal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTotal.setText("TOTAL:");

        btnGuardarpedido.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnGuardarpedido.setText("REALIZAR PEDIDO");
        btnGuardarpedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarpedidoActionPerformed(evt);
            }
        });

        btnModificarpedido.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnModificarpedido.setText("MODIFICAR PEDIDO");
        btnModificarpedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarpedidoActionPerformed(evt);
            }
        });

        btnCancelarpedido.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCancelarpedido.setText("CANCELAR PEDIDO");
        btnCancelarpedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarpedidoActionPerformed(evt);
            }
        });

        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLimpiar.setText("LIMPIAR CAMPOS");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        txtventasID.setBackground(new java.awt.Color(255, 255, 204));
        jScrollPane2.setViewportView(txtventasID);

        txtTotal.setBackground(new java.awt.Color(255, 255, 204));
        jScrollPane3.setViewportView(txtTotal);

        txtCantidadDisponible.setBackground(new java.awt.Color(255, 255, 204));
        jScrollPane4.setViewportView(txtCantidadDisponible);

        txtPrecioUnidad.setBackground(new java.awt.Color(255, 255, 204));
        jScrollPane5.setViewportView(txtPrecioUnidad);

        comboboxpanes.setBackground(new java.awt.Color(255, 255, 204));
        comboboxpanes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" }));

        txtCantidadPedido.setBackground(new java.awt.Color(255, 255, 204));
        jScrollPane6.setViewportView(txtCantidadPedido);

        lblCantidadpedir.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCantidadpedir.setText("CANTIDAD A PEDIR:");

        lblIdpedido.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblIdpedido.setText("ID DE PEDIDO:");

        lblBuscarcodigo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblBuscarcodigo.setText("BUSCAR POR CODIGO DE PEDIDO:");

        txtBusquedaCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBusquedaCodigoActionPerformed(evt);
            }
        });

        btnCerrar.setBackground(new java.awt.Color(255, 102, 102));
        btnCerrar.setText("jButton1");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        btnRegresar.setText("jButton1");
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblIdpedido)
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(157, 157, 157)
                                        .addComponent(lblTitulo)))
                                .addGap(10, 10, 10))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(lblCantidadpedir)
                                                .addGap(18, 18, 18))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(lblPrecio)
                                                .addGap(18, 18, 18)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(lblTotal)
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(lblCantidaddisponible)
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(lblPan)
                                        .addGap(18, 18, 18)
                                        .addComponent(comboboxpanes, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(73, 73, 73)
                                        .addComponent(btnCancelarpedido))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnGuardarpedido)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnModificarpedido))
                                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblBuscarcodigo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtBusquedaCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblIdpedido, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboboxpanes, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCantidaddisponible, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCantidadpedir, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPrecio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardarpedido, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnModificarpedido, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelarpedido, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBuscarcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBusquedaCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarpedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarpedidoActionPerformed
         int selectedRow = tblPedidos.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (Integer) tblPedidos.getValueAt(selectedRow, 0);
            int cantidad = (Integer) tblPedidos.getValueAt(selectedRow, 2);
            String tipoPan = (String) tblPedidos.getValueAt(selectedRow, 1);

            int opcion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas cancelar el pedido seleccionado?", "Confirmar Cancelación", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                try (Connection conn = ConexionBd.establecerConexion();
                     PreparedStatement stmt = conn.prepareStatement("DELETE FROM Pedido WHERE id = ?")) {
                    stmt.setInt(1, id);
                    stmt.executeUpdate();
                    actualizarStockAlCancelar(tipoPan, cantidad);  // act del stock del pan xd
                    JOptionPane.showMessageDialog(this, "Pedido cancelado con éxito.");
                    cargarPedidos();  // actualizacion en las tablas de todo
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Error al cancelar el pedido: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un pedido para cancelar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnCancelarpedidoActionPerformed

    
         private void tblpedidosMouseClicked(MouseEvent evt) {
        int selectedRow = tblPedidos.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (Integer) tblPedidos.getValueAt(selectedRow, 0);
            String tipoPan = (String) tblPedidos.getValueAt(selectedRow, 1);
            int cantidad = (Integer) tblPedidos.getValueAt(selectedRow, 2);
            double precioUnidad = (Double) tblPedidos.getValueAt(selectedRow, 3);
            double total = (Double) tblPedidos.getValueAt(selectedRow, 4);

            txtventasID.setText(String.valueOf(id));
            comboboxpanes.setSelectedItem(tipoPan);
            txtCantidadPedido.setText(String.valueOf(cantidad));
            txtPrecioUnidad.setText(String.valueOf(precioUnidad));
            txtTotal.setText(String.valueOf(total));

            try (Connection conn = ConexionBd.establecerConexion();
                 PreparedStatement stmt = conn.prepareStatement("SELECT cantidad_disponible FROM panes_disponibles WHERE nombre = ?")) {
                stmt.setString(1, tipoPan);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        int cantidadDisponible = rs.getInt("cantidad_disponible");
                        txtCantidadDisponible.setText(String.valueOf(cantidadDisponible));
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al obtener la cantidad disponible del pan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
         
    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
         limpiarCampos();
    }//GEN-LAST:event_btnLimpiarActionPerformed
    
    
    
    private void limpiarCampos() {
        comboboxpanes.setSelectedIndex(-1);
        txtCantidadPedido.setText("");
        txtPrecioUnidad.setText("");
        txtCantidadDisponible.setText("");
        txtTotal.setText("");
        txtventasID.setText("");
    }
      private void cargarPedidos() {
                String query = "SELECT * FROM Pedido";
            try (Connection conn = ConexionBd.establecerConexion();
                 PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {

                DefaultTableModel modeloTabla = (DefaultTableModel) tblPedidos.getModel();
                modeloTabla.setRowCount(0);  // Limpiar la tabla antes de cargar los datos

                while (rs.next()) {
                    Object[] fila = {
                        rs.getInt("id"),
                        rs.getString("tipo_pan"),
                        rs.getInt("cantidad"),
                        rs.getDouble("precio_unitario"),
                        rs.getDouble("total")
                    };
                    modeloTabla.addRow(fila);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al cargar los pedidos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
    }
    private void btnGuardarpedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarpedidoActionPerformed
        String tipoPan = (String) comboboxpanes.getSelectedItem();
        int cantidad = Integer.parseInt(txtCantidadPedido.getText());
        double precioUnidad = Double.parseDouble(txtPrecioUnidad.getText());
        int cantidadDisponible = Integer.parseInt(txtCantidadDisponible.getText());
        double total = cantidad * precioUnidad;

        if (cantidad > cantidadDisponible) {
            JOptionPane.showMessageDialog(this, "La cantidad solicitada excede el stock disponible.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = ConexionBd.establecerConexion();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO Pedido (tipo_pan, cantidad, precio_unitario, total) VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, tipoPan);
            stmt.setInt(2, cantidad);
            stmt.setDouble(3, precioUnidad);
            stmt.setDouble(4, total);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Pedido realizado con éxito.");
            actualizarStock(tipoPan, cantidad);  
            limpiarCampos();
            cargarPedidos();  
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar el pedido: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarpedidoActionPerformed
    private void actualizarStockAlCancelar(String tipoPan, int cantidadPedido) {
    try (Connection conn = ConexionBd.establecerConexion();
         PreparedStatement stmt = conn.prepareStatement("UPDATE panes_disponibles SET cantidad_disponible = cantidad_disponible + ? WHERE nombre = ?")) {
        stmt.setInt(1, cantidadPedido);
        stmt.setString(2, tipoPan);
        stmt.executeUpdate();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error al actualizar el stock del pan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    private void actualizarStock(String tipoPan, int cantidadPedido) {
    try (Connection conn = ConexionBd.establecerConexion();
         PreparedStatement stmt = conn.prepareStatement("UPDATE panes_disponibles SET cantidad_disponible = cantidad_disponible - ? WHERE nombre = ?")) {
        stmt.setInt(1, cantidadPedido);
        stmt.setString(2, tipoPan);
        stmt.executeUpdate();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error al actualizar el stock del pan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    private void btnModificarpedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarpedidoActionPerformed
         int selectedRow = tblPedidos.getSelectedRow();
    if (selectedRow >= 0) {
        int id = (Integer) tblPedidos.getValueAt(selectedRow, 0);
        String tipoPanActual = (String) tblPedidos.getValueAt(selectedRow, 1);
        int cantidadActual = (Integer) tblPedidos.getValueAt(selectedRow, 2);
        double precioUnidad = Double.parseDouble(txtPrecioUnidad.getText());
        int cantidadNueva = Integer.parseInt(txtCantidadPedido.getText());
        double total = Double.parseDouble(txtTotal.getText());
        String tipoPanNuevo = (String) comboboxpanes.getSelectedItem();

        if (tipoPanNuevo == null || tipoPanNuevo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecciona un nuevo tipo de pan.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (tipoPanActual.equals(tipoPanNuevo) && cantidadNueva == cantidadActual) {
            JOptionPane.showMessageDialog(this, "No has cambiado el tipo de pan o la cantidad es la misma.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int opcion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas modificar el pedido?", "Confirmar Modificación", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            try (Connection conn = ConexionBd.establecerConexion()) {
                // Primero, cancelamos el pedido actual y actualizamos el stock
                try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM Pedido WHERE id = ?")) {
                    stmt.setInt(1, id);
                    stmt.executeUpdate();
                }
                
                // Actualizamos el stock del pan actual
                actualizarStockAlCancelar(tipoPanActual, cantidadActual);
                
                // Insertamos el nuevo pedido
                try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO Pedido (tipo_pan, cantidad, precio_unitario, total) VALUES (?, ?, ?, ?)")) {
                    stmt.setString(1, tipoPanNuevo);
                    stmt.setInt(2, cantidadNueva);
                    stmt.setDouble(3, precioUnidad);
                    stmt.setDouble(4, total);
                    stmt.executeUpdate();
                }
                
                // Actualizamos el stock del nuevo pan
                actualizarStock(tipoPanNuevo, cantidadNueva);

                JOptionPane.showMessageDialog(this, "Pedido modificado con éxito.");
                limpiarCampos();
                cargarPedidos();  
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al modificar el pedido: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } else {
        JOptionPane.showMessageDialog(this, "Selecciona un pedido para modificar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
    }
    }//GEN-LAST:event_btnModificarpedidoActionPerformed

    private void txtBusquedaCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBusquedaCodigoActionPerformed
        
    }//GEN-LAST:event_txtBusquedaCodigoActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        LoginPanaderia login = new LoginPanaderia();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
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
            java.util.logging.Logger.getLogger(RegistroPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                RegistroPedidos dialog = new RegistroPedidos(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelarpedido;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnGuardarpedido;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificarpedido;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> comboboxpanes;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lblBuscarcodigo;
    private javax.swing.JLabel lblCantidaddisponible;
    private javax.swing.JLabel lblCantidadpedir;
    private javax.swing.JLabel lblIdpedido;
    private javax.swing.JLabel lblPan;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tblPedidos;
    private javax.swing.JTextField txtBusquedaCodigo;
    private javax.swing.JTextPane txtCantidadDisponible;
    private javax.swing.JTextPane txtCantidadPedido;
    private javax.swing.JTextPane txtPrecioUnidad;
    private javax.swing.JTextPane txtTotal;
    private javax.swing.JTextPane txtventasID;
    // End of variables declaration//GEN-END:variables
}
