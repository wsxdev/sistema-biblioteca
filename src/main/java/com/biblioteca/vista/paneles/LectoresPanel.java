/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.biblioteca.vista.paneles;

import javax.swing.JOptionPane;

/**
 *
 * @author ibarr
 */
public class LectoresPanel extends javax.swing.JPanel {

    private final com.biblioteca.controlador.RegistroLectoresControlador controlador;
    private final javax.swing.table.DefaultTableModel modeloTabla;
    private final javax.swing.JTextField txtCedula = new javax.swing.JTextField();
    private final javax.swing.JTextField txtBarrio = new javax.swing.JTextField();
    private final javax.swing.JTextField txtHora = new javax.swing.JTextField();

    /**
     * Creates new form LectoresPanel
     */
    public LectoresPanel() {
        initComponents();
        // USAR BORDERLAYOUT PARA EVITAR SOLAPAMIENTOS ENTRE TABLA Y BOTONES
        removeAll();
        setLayout(new java.awt.BorderLayout());
        controlador = new com.biblioteca.controlador.RegistroLectoresControlador();
        modeloTabla = (javax.swing.table.DefaultTableModel) TablaRLjTable.getModel();
        // REUBICAR COMPONENTES GENERADOS: TITULO NORTE, TABLA EN CENTER
        add(TitleRegistroLectoresjLabel, java.awt.BorderLayout.NORTH);
        add(TablajScrollPane, java.awt.BorderLayout.CENTER);
        add(crearPanelControles(), java.awt.BorderLayout.SOUTH);
        refrescarTabla();
    }

    private javax.swing.JPanel crearPanelControles(){
        javax.swing.JPanel panel = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        panel.setBackground(new java.awt.Color(255,255,255));
        panel.add(new javax.swing.JLabel("Cédula:")); txtCedula.setColumns(10); panel.add(txtCedula);
        panel.add(new javax.swing.JLabel("Barrio:")); txtBarrio.setColumns(10); panel.add(txtBarrio);
        panel.add(new javax.swing.JLabel("Hora (HH:mm):")); txtHora.setColumns(6); panel.add(txtHora);

        javax.swing.JButton btnAgregar = new javax.swing.JButton("Agregar");
        javax.swing.JButton btnEliminar = new javax.swing.JButton("Eliminar");
        javax.swing.JButton btnBuscar = new javax.swing.JButton("Buscar");
        javax.swing.JButton btnFiltrar = new javax.swing.JButton("Filtrar barrio");
        javax.swing.JButton btnOrdenar = new javax.swing.JButton("Ordenar por hora");
        javax.swing.JButton btnDepurar = new javax.swing.JButton("Depurar duplicados");
        javax.swing.JButton btnConteo = new javax.swing.JButton("Conteo por barrio");
        javax.swing.JButton btnListarFinal = new javax.swing.JButton("Lista final ordenada");

        panel.add(btnAgregar); panel.add(btnEliminar); panel.add(btnBuscar); panel.add(btnFiltrar);
        panel.add(btnOrdenar); panel.add(btnDepurar); panel.add(btnConteo); panel.add(btnListarFinal);

        btnAgregar.addActionListener(e -> {
            String ced = txtCedula.getText().trim();
            String barrio = txtBarrio.getText().trim();
            String hora = txtHora.getText().trim();
            if(ced.isEmpty()||barrio.isEmpty()||hora.isEmpty()){ JOptionPane.showMessageDialog(this, "Rellene todos los campos"); return; }
            com.biblioteca.modelo.entidad.Lector l = new com.biblioteca.modelo.entidad.Lector(ced,barrio,hora);
            boolean ok = controlador.registrar(l);
            if(ok){ modeloTabla.addRow(new Object[]{ced,barrio,hora}); limpiarCamposLectores(); } else { JOptionPane.showMessageDialog(this, "No se pudo agregar. Cédula posiblemente repetida."); }
        });

        btnEliminar.addActionListener(e -> {
            String ced = txtCedula.getText().trim();
            if(ced.isEmpty()) { JOptionPane.showMessageDialog(this, "Ingrese cédula a eliminar"); return; }
            boolean ok = controlador.retirarPorError(ced);
            if(ok){ refrescarTabla(); } else { JOptionPane.showMessageDialog(this, "No se encontró cédula"); }
        });

        btnBuscar.addActionListener(e -> {
            String ced = txtCedula.getText().trim();
            if(ced.isEmpty()) { JOptionPane.showMessageDialog(this, "Ingrese cédula a buscar"); return; }
            com.biblioteca.modelo.entidad.Lector l = controlador.buscar(ced);
            if(l==null) JOptionPane.showMessageDialog(this, "No encontrado"); else JOptionPane.showMessageDialog(this, "Encontrado: " + l.getCedula() + " - " + l.getBarrio());
        });

        btnFiltrar.addActionListener(e -> {
            String barrio = txtBarrio.getText().trim();
            if(barrio.isEmpty()){ JOptionPane.showMessageDialog(this, "Ingrese barrio"); return; }
            java.util.List<com.biblioteca.modelo.entidad.Lector> list = controlador.filtrarPorBarrio(barrio);
            modeloTabla.setRowCount(0);
            for(com.biblioteca.modelo.entidad.Lector lt: list) modeloTabla.addRow(new Object[]{lt.getCedula(), lt.getBarrio(), lt.getHoraLlegada()});
        });

        btnOrdenar.addActionListener(e -> {
            controlador.ordenarPorHora();
            refrescarTabla();
        });

        btnDepurar.addActionListener(e -> {
            controlador.depurarDuplicados();
            refrescarTabla();
        });

        btnConteo.addActionListener(e -> {
            java.util.Map<String,Integer> conteo = controlador.conteoPorBarrio();
            StringBuilder sb = new StringBuilder();
            for(String k: conteo.keySet()) sb.append(k).append(": ").append(conteo.get(k)).append("\n");
            JOptionPane.showMessageDialog(this, sb.toString(), "Conteo por barrio", JOptionPane.INFORMATION_MESSAGE);
        });

        btnListarFinal.addActionListener(e -> {
            java.util.List<com.biblioteca.modelo.entidad.Lector> list = controlador.listarOrdenados();
            modeloTabla.setRowCount(0);
            for(com.biblioteca.modelo.entidad.Lector lt: list) modeloTabla.addRow(new Object[]{lt.getCedula(), lt.getBarrio(), lt.getHoraLlegada()});
        });

        return panel;
    }

    private void limpiarCamposLectores(){
        txtCedula.setText(""); txtBarrio.setText(""); txtHora.setText("");
    }

    private void refrescarTabla(){
        modeloTabla.setRowCount(0);
        java.util.List<com.biblioteca.modelo.entidad.Lector> all = controlador.obtenerTodos();
        for(com.biblioteca.modelo.entidad.Lector l: all) modeloTabla.addRow(new Object[]{l.getCedula(), l.getBarrio(), l.getHoraLlegada()});
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TitleRegistroLectoresjLabel = new javax.swing.JLabel();
        TablajScrollPane = new javax.swing.JScrollPane();
        TablaRLjTable = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TitleRegistroLectoresjLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        TitleRegistroLectoresjLabel.setForeground(new java.awt.Color(29, 114, 129));
        TitleRegistroLectoresjLabel.setText("REGISTRO DE LECTORES");
        add(TitleRegistroLectoresjLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, -1, -1));

        TablajScrollPane.setPreferredSize(new java.awt.Dimension(640, 420));

        TablaRLjTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Cèdula", "Barrio", "Hora de llegada"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        TablajScrollPane.setViewportView(TablaRLjTable);

        add(TablajScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, -1, 350));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaRLjTable;
    private javax.swing.JScrollPane TablajScrollPane;
    private javax.swing.JLabel TitleRegistroLectoresjLabel;
    // End of variables declaration//GEN-END:variables
}
