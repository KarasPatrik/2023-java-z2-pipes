package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.gameLogic.GameLogic;

import javax.swing.*;
import java.awt.*;


public class Game {
    public Game(){
        JFrame frame = new JFrame("PIPES");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1300,900);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame.setResizable(false);
        frame.setFocusable(true);
        frame.requestFocusInWindow();

        JButton buttonRestart = new JButton("RESTART");
        JButton buttonCheck = new JButton("CHECK");
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 8, 12, 8);

        GameLogic logic = new GameLogic(frame, buttonRestart, buttonCheck);
        frame.addKeyListener(logic);

        JPanel controls = new JPanel();
        controls.setPreferredSize(new Dimension(300,900));
        controls.setBackground(Color.GRAY);
        controls.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        buttonRestart.setBackground(Color.WHITE);
        buttonRestart.addActionListener(logic);
        buttonRestart.setFocusable(false);

        buttonCheck.setBackground(Color.WHITE);
        buttonCheck.addActionListener(logic);
        buttonCheck.setFocusable(false);

        slider.setMinorTickSpacing(2);
        slider.setMajorTickSpacing(2);
        slider.setSnapToTicks(true);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(logic);

        controls.setLayout(new GridLayout(6,1));
        controls.add(logic.getBoardSizeLabel());
        controls.add(logic.getLevelLabel());
        controls.add(buttonCheck);
        controls.add(new JLabel("CHANGE SIZE :", SwingConstants.CENTER));
        controls.add(slider);
        controls.add(buttonRestart);
        frame.add(controls, BorderLayout.EAST);



        frame.setVisible(true);
    }
}
