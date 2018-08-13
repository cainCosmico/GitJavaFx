/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import java.awt.Component;
import java.awt.PopupMenu;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

/**
 *
 * @author MANDO
 */
public class Main extends JFrame implements KeyListener {
    private panelPong panel;
    private int keyPress = 0;
    
    public Main() {
        super("PONG");
        iniciarComponentes();
        setSize(600,500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);                
    }
    
    public void iniciarComponentes(){
        panel = new panelPong();        
        this.add(panel);  
        this.addKeyListener(this);
        System.out.println("Key: " + keyPress);
    }
    
    public static void main(String[] args) {
        new Main();
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        keyPress = ke.getKeyCode();
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }
}
