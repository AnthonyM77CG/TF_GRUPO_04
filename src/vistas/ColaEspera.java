package vistas;

import entidades.Cliente;
import metodos.Traduccion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class ColaEspera extends javax.swing.JFrame {

    private Queue<Cliente> colaClientes;

    public ColaEspera() {
        initComponents();
        colaClientes = new LinkedList<>();
        actualizarIdioma(); // Asegurarse de que se llame al actualizar el idioma
    }

    private void abrirRegistroPedidos() {
        RegistroPedidos dialog = new RegistroPedidos(this, true);
        dialog.setVisible(true);
    }

    private void actualizarIdioma() {
        ResourceBundle bundle = Traduccion.getBundle();
        lblTitulo.setText(bundle.getString("atencion.titulo"));
        lblCodigo.setText(bundle.getString("atencion.subtitulo.Codigo"));
        btnAgregar.setText(bundle.getString("atencion.boton.Agregar"));
        btnAtender.setText(bundle.getString("atencion.boton.Atendido"));
        btnCerrar.setText(bundle.getString("boton.Cerrar"));
        lblAnterior.setText(bundle.getString("atencion.subtitulo.Anterior"));
        btnLimpiar.setText(bundle.getString("atencion.boton.Limpiar"));
        lblBuscar.setText(bundle.getString("atencion.subtitulo.Buscar"));
        btnRegresar.setText(bundle.getString("boton.Regresar"));
        setLocationRelativeTo(null);
    }

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblCodigo = new javax.swing.JLabel();
        txtCodigoagr = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        btnAtender = new javax.swing.JButton();
        lblAnterior = new javax.swing.JLabel();
        txtAtendido = new javax.swing.JTextField();
        lblBuscar = new javax.swing.JLabel();
        txtBusqueda = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txpClientes = new javax.swing.JTextPane();
        btnRegresar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        lblTitulo.setText("LISTA DE CLIENTES EN ESPERA:");

        lblCodigo.setText("Codigo de cliente: ");

        txtCodigoagr.setBackground(new java.awt.Color(255, 255, 204));

        btnAgregar.setText("AGREGAR");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnAtender.setText("CLIENTE ATENDIDO");
        btnAtender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtenderActionPerformed(evt);
            }
        });

        lblAnterior.setText("Codigo de cliente atendido anteriormente:");

        txtAtendido.setBackground(new java.awt.Color(255, 255, 204));

        lblBuscar.setText("Buscar cliente por codigo:");

        txtBusqueda.setBackground(new java.awt.Color(255, 255, 204));
        txtBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBusquedaActionPerformed(evt);
            }
        });

        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        txpClientes.setBackground(new java.awt.Color(255, 255, 204));
        jScrollPane2.setViewportView(txpClientes);

        btnRegresar.setText("REGRESAR");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        btnCerrar.setBackground(new java.awt.Color(255, 102, 102));
        btnCerrar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnCerrar.setText("cerrar sesion");
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(lblAnterior)
                        .addGap(15, 15, 15)
                        .addComponent(txtAtendido, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(lblBuscar)
                        .addGap(103, 103, 103)
                        .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCodigo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCodigoagr, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTitulo)
                                .addGap(118, 118, 118)
                                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(btnAtender, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitulo))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodigoagr, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCodigo)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAtender, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblAnterior))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAtendido, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(lblBuscar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private boolean codigoExiste(String codigo) {
        for (Cliente cliente : colaClientes) {
            if (cliente.getCodigo().equals(codigo)) {
                return true;
                }
            }
        return false;
    }
    
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        String codigoCliente = txtCodigoagr.getText();
        if (!codigoCliente.isEmpty()) {
            if (codigoExiste(codigoCliente)) {
            JOptionPane.showMessageDialog(this, "El código de cliente " + codigoCliente + " ya está en la cola.");
            } else {
                Cliente nuevoCliente = new Cliente(codigoCliente);
                colaClientes.add(nuevoCliente);
                actualizarListaClientes();
                actualizarMensajeAtendiendo(colaClientes.peek());
                txtCodigoagr.setText("");
            }
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnAtenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtenderActionPerformed
        if (!colaClientes.isEmpty()) {
            Cliente clienteAtendido = colaClientes.poll();
            txtAtendido.setText(clienteAtendido.getCodigo());

            actualizarListaClientes();
            if (!colaClientes.isEmpty()) {
                Cliente proximoCliente = colaClientes.peek();
                btnAtender.setText("CODIGO DE CLIENTE " + proximoCliente.getCodigo() + " ATENDIDO");
                actualizarMensajeAtendiendo(proximoCliente);
            } else {
                btnAtender.setText("No hay clientes en espera");
                actualizarMensajeAtendiendo(null);
            }

            abrirRegistroPedidos();
        } else {
            txtAtendido.setText("No hay clientes en espera");
            actualizarListaClientes();
            actualizarMensajeAtendiendo(null);
        }
    }//GEN-LAST:event_btnAtenderActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        colaClientes.clear();
        actualizarListaClientes();
        actualizarMensajeAtendiendo(null);
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void txtBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBusquedaActionPerformed
        String codigoBuscado = txtBusqueda.getText();
        if (!codigoBuscado.isEmpty()) {
            int posicion = buscarCliente(codigoBuscado);
            if (posicion != -1) {
                JOptionPane.showMessageDialog(this, "El cliente con el código " + codigoBuscado + " está en la posición " + (posicion + 1) + " en la cola.");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ningún cliente con el código " + codigoBuscado + " en la cola.");
            }
        } else {
        JOptionPane.showMessageDialog(this, "Ingrese un código de cliente válido para buscar.");
        }
        }
        private int buscarCliente(String codigo) {
        int posicion = -1;
        int contador = 0;
        for (Cliente cliente : colaClientes) {
            if (cliente.getCodigo().equals(codigo)) {
                posicion = contador;
                break;
            }
            contador++;
        }
        return posicion;
    }//GEN-LAST:event_txtBusquedaActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        Menú fc= new Menú();
        fc.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        LoginPanaderia fc= new LoginPanaderia();
        fc.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed
    
    private void actualizarListaClientes() {
        StringBuilder listaClientes = new StringBuilder();
        listaClientes.append("Lista de clientes a la espera:\n");
        for (Cliente cliente : colaClientes) {
            listaClientes.append(cliente).append("\n"); 
        }
        txpClientes.setText(listaClientes.toString());
    }
    
    private void actualizarMensajeAtendiendo(Cliente clienteAtendido) {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append(txpClientes.getText());

    if (clienteAtendido != null) {
        mensaje.append("\nEstamos atendiendo al cliente ").append(clienteAtendido);
    } else {
        mensaje.append("\nNo hay clientes siendo atendidos.");
    }

    txpClientes.setText(mensaje.toString());
    }
    
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
            java.util.logging.Logger.getLogger(ColaEspera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ColaEspera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ColaEspera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ColaEspera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ColaEspera().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAtender;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAnterior;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextPane txpClientes;
    private javax.swing.JTextField txtAtendido;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtCodigoagr;
    // End of variables declaration//GEN-END:variables
}
