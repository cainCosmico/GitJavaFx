/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hilos;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Camilo Valencia
 */
public class VentanaHilos extends JFrame {
    private LaminaPelota lamina;
    private JButton startGame1;
    private JButton startGame2;
    private JButton stop1;
    private JButton stop2;
    private JButton salir;
    private Thread hiloPelota1;
    private Thread hiloPelota2;
    
    public VentanaHilos() {                
        super("Pelota Rebotando");
        
        lamina = new LaminaPelota();
        JPanel panelBotones = new JPanel(); 
        startGame1 = new JButton("1");
        startGame2 = new JButton("2");
        stop1 = new JButton("Stop - 1");
        stop2 = new JButton("Stop - 2");
        salir = new JButton("Salir");   
        
        panelBotones.add(startGame1);
        panelBotones.add(startGame2);
        panelBotones.add(stop1);
        panelBotones.add(stop2);
        panelBotones.add(salir);                
        add(lamina, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        iniciarBotones();
        setSize(400, 400);
        setVisible(true);
    }        
    
    public void iniciarBotones(){
        startGame1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                comerzarJuego(ae);
            }
        });
        
        startGame2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                comerzarJuego(ae);
            }
        });
        
        stop1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                detener(ae);
            }            
        });
        
        stop2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                detener(ae);
            }            
        });
        
        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
    }
    
    public void comerzarJuego(ActionEvent ae) {
        Pelota pelota = new Pelota();
        lamina.agregarPelota(pelota);
        
        Runnable r = new HiloPelota(pelota, lamina);
        if(ae.getSource().equals(startGame1)) {
            hiloPelota1 = new Thread(r);
            hiloPelota1.start();
        }else if(ae.getSource().equals(startGame2)) {
            hiloPelota2 = new Thread(r);
            hiloPelota2.start();
        }
        
    }
    
    public void detener(ActionEvent ae){
        if(ae.getSource().equals(stop1)){
            hiloPelota1.interrupt();
        }else if(ae.getSource().equals(stop2)){
            hiloPelota2.interrupt();                
        }
    }
    
    public static void main(String[] args) {
        new VentanaHilos();
    }
}

class Pelota{
    private double corX = 0;
    private double corY = 0;
    private double dx = 1;
    private double dy = 1;
        
    public void mueve_pelota(Rectangle2D limites){
        //System.out.println("Max x:" + limites.getMaxX() + " Min x:" + limites.getMinX());
        corX += dx;
        corY += dy;
        
        if(corX < limites.getMinX()){
            corX = limites.getMinX();
            dx = -dx;
        }
        
        if(corX + 20 > limites.getMaxX()){
            corX = limites.getMaxX() - 20;
            dx = -dx;
        }
        
        if(corY < limites.getMinY()){
            corY = limites.getMinY();
            dy = -dy;
        }
        
        if(corY + 20 > limites.getMaxY()){
            corY = limites.getMaxY() - 20;
            dy = -dy;
        }
    }
    
    public Ellipse2D getPelota(){
        return new Ellipse2D.Double(corX, corY, 20, 20);
    }
}

class LaminaPelota extends JPanel{
    //private Pelota p;
    private ArrayList<Pelota> pelotas = new ArrayList<>();
    
    public void agregarPelota(Pelota p){
        pelotas.add(p);
        //this.p = p;
    }
        
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;        
        for(Pelota p: pelotas){
            try{
                g2.fill(p.getPelota());
            }catch(Exception e) {
                System.out.println("Error en paint");
            }
        }
    }
}

class HiloPelota implements Runnable {
    private Pelota pelota;
    private Component componente;
    
    public HiloPelota(Pelota pelota, Component componente) {
        this.pelota = pelota;
        this.componente = componente;
    }
    
    @Override
    public void run(){
        while(!Thread.currentThread().isInterrupted()){
            pelota.mueve_pelota(componente.getBounds());
            componente.paint(componente.getGraphics());            
            try {            
                Thread.sleep(4);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
}