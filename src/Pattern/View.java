/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pattern;

import Objects.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
/**
 *
 * @author 
 * Kevin Flores
 * Fernanda González
 */
public class View extends JFrame implements Observer{
    
    private Model model;
    private Controller controller;

    public View() {
        this.setSize(850,600);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt){
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt){
                formKeyReleased(evt);
            }
        });
    }
    
    private void formKeyPressed(java.awt.event.KeyEvent evt){
        switch (evt.getKeyCode()){
            case KeyEvent.VK_UP: controller.move(Model.ARR); break;
            case KeyEvent.VK_DOWN: controller.move(Model.ABA); break;
            case KeyEvent.VK_LEFT: controller.move(Model.IZQ); break;
            case KeyEvent.VK_RIGHT: controller.move(Model.DER); break;
        }
    }
    private void formKeyReleased(java.awt.event.KeyEvent evt){
        int key = evt.getKeyCode();
        switch(key){
             case KeyEvent.VK_LEFT: case KeyEvent.VK_RIGHT: controller.stopHor(); break;
             case KeyEvent.VK_UP: case KeyEvent.VK_DOWN: controller.stopVer(); break;
        }
    }
    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addObserver(this);
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
    
    @Override
    public void update(Observable o, Object arg){
        this.repaint();
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        this.render(model, g);
    }
    void render(Model m, Graphics media){
        render(m.getBall(), media);
        render(m.getField(), media);
        renderRacket(m.getRacket(), media);
    }
    
    void render(Ball b, Graphics media){
       media.setColor(Color.red);
       media.fillOval(b.getX()-b.getRadio(), b.getY()-b.getRadio(),b.getRadio()*2, b.getRadio()*2);
    }
    
    void render(Field f, Graphics media){
        media.setColor(Color.black);
        media.drawOval(f.getX()-f.getRadio(), f.getY()-f.getRadio(), f.getRadio()*2, f.getRadio()*2);
    }
    void renderRacket(Racket r, Graphics media){
        media.setColor(Color.BLUE);
        media.fillRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
    }
}
