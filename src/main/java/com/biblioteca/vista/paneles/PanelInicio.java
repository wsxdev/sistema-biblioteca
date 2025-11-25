package com.biblioteca.vista.paneles;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelInicio extends JPanel {
    public PanelInicio(){
        setLayout(new BorderLayout());
        JLabel lbl = new JLabel("BIENVENIDO A LA BIBLIOTECA ITINERANTE DE ORELLANA", JLabel.CENTER);
        add(lbl, BorderLayout.CENTER);
    }
}
