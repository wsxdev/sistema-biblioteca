package com.biblioteca.vista.paneles;

import com.biblioteca.modelo.enumeracion.TipoTarjeta;
import com.biblioteca.modelo.implementacion.TarjetaDAO;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PanelTarjetas extends JPanel {
    private final TarjetaDAO dao;
    private final JComboBox<TipoTarjeta> combo;
    private final JTextArea area;

    public PanelTarjetas(){
        dao = new TarjetaDAO();
        setLayout(new BorderLayout());
        combo = new JComboBox<>(TipoTarjeta.values());
        area = new JTextArea(); area.setEditable(false);
        add(combo, BorderLayout.NORTH);
        add(new JLabel("Información de tarjeta:"), BorderLayout.WEST);
        add(area, BorderLayout.CENTER);

        combo.addActionListener(a -> mostrarInfo((TipoTarjeta)combo.getSelectedItem()));
        if(combo.getItemCount()>0) mostrarInfo((TipoTarjeta)combo.getItemAt(0));
    }

    private void mostrarInfo(TipoTarjeta t){
        if(t==null) return;
        StringBuilder sb = new StringBuilder();
        sb.append("TIPO: ").append(t.name()).append("\n");
        sb.append("PREFIJO: ").append(t.getPrefijo()).append("\n");
        sb.append("ZONAS: ").append(String.join(", ", t.getZonasPermitidas())).append("\n");
        sb.append("LIMITE PRESTAMOS: ").append(t.getLimitePrestamos()).append("\n");
        area.setText(sb.toString());
    }
}
