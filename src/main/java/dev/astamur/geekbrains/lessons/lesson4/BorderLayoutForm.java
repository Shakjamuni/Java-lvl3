package dev.astamur.geekbrains.lessons.lesson4;

import javax.swing.*;
import java.awt.*;

public class BorderLayoutForm extends JFrame {
    public BorderLayoutForm() {
        setTitle("Simple BorderLayout Window");
        setBounds(500, 500, 500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JButton[] buttons = new JButton[5];
        for (int i = 0; i < 5; i++) {
            buttons[i] = new JButton("Button #" + i);
        }

        add(buttons[0], BorderLayout.EAST);
        add(buttons[1], BorderLayout.WEST);
        add(buttons[2], BorderLayout.SOUTH);
        add(buttons[3], BorderLayout.NORTH);
        add(buttons[4], BorderLayout.CENTER);

        // buttons[2].setPreferredSize(new Dimension(300, 300));
        // add(buttons[0], BorderLayout.PAGE_START);
        // add(buttons[1], BorderLayout.CENTER);
        // add(buttons[2], BorderLayout.LINE_START);
        // add(buttons[3], BorderLayout.PAGE_END);
        // add(buttons[4], BorderLayout.LINE_END);

        setVisible(true);
    }
}