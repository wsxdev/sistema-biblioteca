package com.biblioteca.vista.paneles;

import com.biblioteca.controlador.EstanteriaControlador;
import com.biblioteca.modelo.entidad.Libro;
import com.biblioteca.modelo.enumeracion.EstadoLibro;
import java.awt.BorderLayout;
import java.awt.Dimension;
 
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JPanel;
 
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class PanelEstanteria extends JPanel {
    private final EstanteriaControlador controlador;
    private final DefaultTableModel modelo;
    private final JTable tabla;

    private final JTextField txtPosicion = new JTextField();
    private final JTextField txtCodigo = new JTextField();
    private final JTextField txtTitulo = new JTextField();
    private final JTextField txtAutor = new JTextField();
    private final JTextField txtGenero = new JTextField();
    private final JComboBox<EstadoLibro> comboEstado = new JComboBox<>(EstadoLibro.values());

    public PanelEstanteria(){
        controlador = new EstanteriaControlador();
        setLayout(new BorderLayout());
        modelo = new DefaultTableModel(new Object[]{"Pos","Codigo","Titulo","Autor","Genero","Estado"},0){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        tabla = new JTable(modelo);
        tabla.setPreferredScrollableViewportSize(new Dimension(640,300));

        // FORMULARIO EN LA PARTE SUPERIOR
        JPanel formPanel = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        formPanel.add(new JLabel("Posición (1-30):")); txtPosicion.setColumns(3); formPanel.add(txtPosicion);
        formPanel.add(new JLabel("Código:")); txtCodigo.setColumns(5); formPanel.add(txtCodigo);
        formPanel.add(new JLabel("Título:")); txtTitulo.setColumns(12); formPanel.add(txtTitulo);
        formPanel.add(new JLabel("Autor:")); txtAutor.setColumns(10); formPanel.add(txtAutor);
        formPanel.add(new JLabel("Género:")); txtGenero.setColumns(8); formPanel.add(txtGenero);

        // BOTONES PRINCIPALES EN PANEL SEPARADO (SOUTH)
        JPanel accionesPanel = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        JButton btnAsignar = new JButton("Asignar/Reemplazar");
        JButton btnConsultar = new JButton("Consultar");
        JButton btnInicializar = new JButton("Inicializar Estantería");
        JButton btnListarLibres = new JButton("Listar Libres");
        JButton btnListarOcupadas = new JButton("Listar Ocupadas");
        accionesPanel.add(btnAsignar); accionesPanel.add(btnConsultar); accionesPanel.add(btnInicializar);
        accionesPanel.add(btnListarLibres); accionesPanel.add(btnListarOcupadas);
        // MOVER EL SELECTOR DE ESTADO A LAS ACCIONES PARA QUE QUEDE JUNTO A LOS BOTONES
        accionesPanel.add(new JLabel("Estado:")); comboEstado.setSelectedIndex(0); accionesPanel.add(comboEstado);

        add(formPanel, BorderLayout.NORTH);
        javax.swing.JScrollPane scroll = new javax.swing.JScrollPane(tabla);
        // LIMITAR ALTO DEL SCROLL PARA QUE LOS BOTONES EN SOUTH SIEMPRE SEAN VISIBLES
        scroll.setPreferredSize(new Dimension(640, 240));
        add(scroll, BorderLayout.CENTER);
        accionesPanel.setPreferredSize(new Dimension(640, 70));
        add(accionesPanel, BorderLayout.SOUTH);

        // LISTENERS
        btnAsignar.addActionListener(e -> asignarLibroAction());
        btnConsultar.addActionListener(a -> consultarAction());
        btnInicializar.addActionListener(a -> {
            controlador.inicializar();
            refrescarTabla();
            JOptionPane.showMessageDialog(PanelEstanteria.this, "Estantería inicializada");
        });
        btnListarLibres.addActionListener(a -> {
            List<Integer> libres = controlador.listarLibres();
            JOptionPane.showMessageDialog(PanelEstanteria.this, "Posiciones libres: " + libres);
        });
        btnListarOcupadas.addActionListener(a -> {
            List<Integer> ocupadas = controlador.listarOcupadas();
            JOptionPane.showMessageDialog(PanelEstanteria.this, "Posiciones ocupadas: " + ocupadas);
        });

        refrescarTabla();
    }

    private void asignarLibroAction(){
        try{
            int pos = Integer.parseInt(txtPosicion.getText());
            int codigo = Integer.parseInt(txtCodigo.getText());
            String titulo = txtTitulo.getText();
            String autor = txtAutor.getText();
            String genero = txtGenero.getText();
            EstadoLibro estado = (EstadoLibro) comboEstado.getSelectedItem();
            if(pos < 1 || pos > 30){
                JOptionPane.showMessageDialog(this, "Posición inválida. Debe ser entre 1 y 30.");
                return;
            }
            if(titulo == null || titulo.trim().isEmpty() || autor == null || autor.trim().isEmpty()){
                JOptionPane.showMessageDialog(this, "Título y autor son obligatorios.");
                return;
            }
            Libro libro = new Libro(codigo, titulo, autor, genero, estado);
            boolean ok = controlador.asignarLibro(pos, libro);
            if(ok){
                refrescarTabla();
                limpiarCampos();
                JOptionPane.showMessageDialog(this, "Libro asignado en posición " + pos);
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo asignar el libro.");
            }
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(this, "Ingrese números válidos en posición y código.");
        }
    }

    private void limpiarCampos(){
        txtPosicion.setText("");
        txtCodigo.setText("");
        txtTitulo.setText("");
        txtAutor.setText("");
        txtGenero.setText("");
        comboEstado.setSelectedIndex(0);
    }

    private void consultarAction(){
        try{
            int pos = Integer.parseInt(txtPosicion.getText());
            Libro l = controlador.consultarPorPosicion(pos);
            if(l == null){
                JOptionPane.showMessageDialog(this, "No hay libro en la posición " + pos);
            } else {
                JOptionPane.showMessageDialog(this, "Libro: " + l.getTitulo() + " (" + l.getAutor() + ")");
            }
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(this, "Ingrese posición válida.");
        }
    }

    private void refrescarTabla(){
        modelo.setRowCount(0);
        Libro[] todos = controlador.obtenerTodos();
        for(int i=0;i<todos.length;i++){
            Libro l = todos[i];
            if(l==null){
                modelo.addRow(new Object[]{i+1, "", "", "", "", "VACIO"});
            } else {
                modelo.addRow(new Object[]{i+1, l.getCodigo(), l.getTitulo(), l.getAutor(), l.getGenero(), l.getEstado().name()});
            }
        }
    }
}
