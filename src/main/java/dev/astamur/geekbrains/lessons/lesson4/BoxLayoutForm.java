package dev.astamur.geekbrains.lessons.lesson4;

import javax.swing.*;
import java.awt.*;

public class BoxLayoutForm extends JFrame {
    public BoxLayoutForm() {
        setTitle("Simple BoxLayout Window");
        setBounds(500, 500, 500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setMinimumSize(new Dimension(100, 100));

        //setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JButton[] buttons = new JButton[9];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton("Button #" + i);
            buttons[i].setAlignmentY(Component.CENTER_ALIGNMENT);
            add(buttons[i]);
        }

        setVisible(true);
    }
}