/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.GridLayout;
import java.lang.*;
import javax.swing.*;

public class paneltest {
    public static void main(String[] args) throws Exception {
        JPanel panel=new JPanel(new GridLayout(0,2));
        panel.add(new JLabel("Please select what type of equation(s) you want"));
        panel.add(new JLabel("                                               "));
        JCheckBox jr1=new JCheckBox("+");
        JCheckBox jr2=new JCheckBox("-");
        JCheckBox jr3=new JCheckBox("*");
        JCheckBox jr4=new JCheckBox("/");
        panel.add(jr1);
        panel.add(jr2);
        panel.add(jr3);
        panel.add(jr4);
        panel.add(new JLabel("Max"));
        JSpinner spin=new JSpinner(new SpinnerNumberModel(10,1,24,1));
        panel.add(spin);
        panel.add(new JLabel("Min"));
        JSpinner spin2=new JSpinner(new SpinnerNumberModel(10,1,24,1));
        panel.add(spin2);
        JOptionPane.showMessageDialog(null, panel);
        
        if(jr1.isSelected())System.out.println("Addition");
        System.out.println(spin.getValue());
        System.out.println(spin2.getValue());
    }

}