/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pattern;

import Objects.*;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
/**
 *
 * @author Kevin Flores
 */
public class View extends JFrame implements Observer{
    
    private Model model;
    private Controller controller;

    public View() {
        this.setSize(850,600);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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
    }
    
    void render(Ball b, Graphics media){
       media.setColor(Color.red);
       media.fillOval(b.getX()-b.getRadio(), b.getY()-b.getRadio(),b.getRadio()*2, b.getRadio()*2);
    }
    
    void render(Field f, Graphics media){
        media.setColor(Color.black);
        media.drawOval(f.getX()-f.getRadio(), f.getY()-f.getRadio(), f.getRadio()*2, f.getRadio()*2);
    }
}
