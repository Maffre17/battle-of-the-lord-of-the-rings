package org.example;

import org.example.view.BatalhaGUI;

import javax.swing.*;

// CLASSE PRINCIPAL

/**
 * Ponto de entrada da aplicação.
 * Inicializa a interface gráfica da batalha usando Swing.
 */
public class Main {
    public static void main(String[] args) {
        // Garante que a GUI seja criada na Event Dispatch Thread (boa prática do Swing)
        SwingUtilities.invokeLater(BatalhaGUI::new);
    }
}