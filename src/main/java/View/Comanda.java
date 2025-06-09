package View;

import javax.swing.*;
import java.awt.*;


public class Comanda extends JFrame {

    private JPanel panel;

    public Comanda(){

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panel = new JPanel(new BorderLayout());
        add(panel);
        addComponentes();

    }

    public void addComponentes(){

        JLabel titulo = new JLabel("Pizzería Góndola", SwingConstants.CENTER);

        titulo.setFont(new Font("Arial", Font.BOLD,42));
        panel.add(titulo, BorderLayout.NORTH);

    }

}
