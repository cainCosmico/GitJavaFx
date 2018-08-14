/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingJavafx;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author MANDO
 */
public class VentanaFX {
    public static void initAndShowGui() {
        JFrame frame = new JFrame("VentanJavaFX");
        final JFXPanel fxpanel = new JFXPanel();
        frame.add(fxpanel);
        frame.setSize(720, 480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                initFX(fxpanel);
            }
        });
    }
    
    public static void initFX(JFXPanel panel){
        Scene scene = createScene();
        panel.setScene(scene);
    }
    
    public static Scene createScene() {
        Group root = new Group();
        Scene scene = new Scene(root, Color.ALICEBLUE);
        Text text = new Text("Soy JavaFX");
        text.setX(10);
        text.setY(10);
        text.setFont(new Font(35));
        
        root.getChildren().add(text);
        return scene;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initAndShowGui();
            }
        });
        
        System.out.println("Estoy aquí");
    }
}
