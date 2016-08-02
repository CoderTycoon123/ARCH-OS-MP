package Downloads;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dell Inspiron 15
 */
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import static java.awt.ComponentOrientation.*;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.GridLayout;

public class ToBinary extends JFrame implements ActionListener
{
    private JLabel label;
    private ImageIcon icon;
    private JTextField tf;
    private String str = "";
    private Float2BinaryHexConverter app;
    private JTextArea area;
    private JPanel pane = new JPanel();
    
    public ToBinary()
    {
        super ("IEEE-754 Binary-32 Floating Point Converter");
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 700);
        
        icon = new ImageIcon("src\\Downloads\\seven.gif");
        setIconImage(icon.getImage());
        settingUp();
        
        setResizable(true);
        setVisible(true);
    }
    
    public void settingUp()
    {
        app = new Float2BinaryHexConverter();
        
        int i;
        JButton btn;
        JPanel panel, panel1;
        JLabel lbl;
        
        icon = new ImageIcon("src\\Downloads\\canvas.jpg");
        
        lbl = new JLabel(icon);
        add(lbl, BorderLayout.NORTH);
        
        panel = new JPanel(new FlowLayout());
        add(panel);
        
        tf = new JTextField(30);
        panel.add(tf);
        
        panel1 = new JPanel(new GridLayout(5, 3, 5, 5));
        panel1.setComponentOrientation(RIGHT_TO_LEFT);
        
        for (i = 9; i >= 1; i --)
        {
            btn = new JButton("" + i);
            btn.addActionListener(this);
            panel1.add(btn);
        }
        
        btn = new JButton("C");
        btn.addActionListener(this);
        panel1.add(btn);
        
        btn = new JButton(".");
        btn.addActionListener(this);
        panel1.add(btn);
        
        btn = new JButton("0");
        btn.addActionListener(this);
        panel1.add(btn);
        
        btn = new JButton("");
        panel1.add(btn);
        
        btn = new JButton("Enter");
        btn.addActionListener(this);
        panel1.add(btn);
        
        btn = new JButton("");
        panel1.add(btn);
        
        btn.setPreferredSize(new Dimension(95, 50));
        panel.add(panel1);
        
        add(pane, BorderLayout.SOUTH);
        
        area = new JTextArea(8, 0);
        pane.add(area, BorderLayout.NORTH);
        area.setFont(new Font("Calibri", Font.BOLD, 15));
        
        area.setEditable(false);
        area.setOpaque(false);
        displayMantissa();
    }
    
    public void displayMantissa()
    {
        area.setText("Please enter decimal mantissa");
    }
    
    public void displayExponent()
    {
        area.setText("Please enter exponent (base-10)");
    }
    
    public void actionPerformed(ActionEvent e)
    {
        JButton btn;
        
        switch (e.getActionCommand())
        {
            case "1": str += "1";
                      break;
            case "2": str += "2";
                      break;
            case "3": str += "3";
                      break;
            case "4": str += "4";
                      break;
            case "5": str += "5";
                      break;
            case "6": str += "6";
                      break;
            case "7": str += "7";
                      break;
            case "8": str += "8";
                      break;
            case "9": str += "9";
                      break;
            case "0": str += "0";
                      break;
            case ".": str += ".";
                      break;
            case "C": str = "";
                      break;
            case "Enter": app.setDecimal(Double.parseDouble(str));
                          displayExponent();
                          str = "";
                          btn = (JButton) e.getSource();
                          btn.setText("Done");
                          break;
            case "Done": app.setExp(Integer.parseInt(str));
                         app.main();
                         display();
                         btn = (JButton) e.getSource();
                         btn.setText("Finish");
                         break;
            case "Finish": 
                           break;
        }
        tf.setText(str);
    }
    
    public void display()
    {
        String str;
        
        str = "binary whole & fraction combined: " + app.getstrBinary() + " \n" + 
                "normalized: " + app.getBinary() + " \n" + "E = " + app.getE() + " \n" +
                "mantissa: " + app.getMantissa() + " \n" + app.getSignBit() + " " + 
                Integer.toBinaryString(app.getEprime()) + " " + app.getstrMantissa();
        
        area.setText(str);
    }
}
