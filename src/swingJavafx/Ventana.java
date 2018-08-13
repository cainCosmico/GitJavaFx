/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingJavafx;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author MANDO
 */
public class Ventana extends JFrame {
    private JButton btnJavaFx;
    
    public Ventana(){
        super("Integrando JavaFX");
        
        iniciarComponentes();
        iniciarListeners();
        
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    private void iniciarComponentes(){
        btnJavaFx = new JButton("Iniciar javaFx");
        this.add(BorderLayout.SOUTH,btnJavaFx);
    }
    
    public void iniciarListeners(){
        btnJavaFx.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("Estoy aquí");
                
            }
        });
    }
    
    public static void main(String[] args) {
        new Ventana();
    }
}
