/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import Pattern.Model;
import static java.lang.Math.pow;

/**
 *
 * @author 
 * Kevin Flores
 * Fernanda González
 */
public class Racket extends Actor {
    
    private int width;
    private int height;

    public Racket(int width, int height, int x, int y, int boost_x, int boost_y) {
        super(x, y, boost_x, boost_y);
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    @Override
    public void move(Model model){
        int suma_x= getX()+getBoost_x();
        double square= pow(model.getField().getRadio(), 2);
        int suma_y= getY()+getBoost_y();
        /*
            Esquina superior izquierda: x 
            Esquina inferior izquierda: y + height
            Esquina superior derecha: x + width 
            Esquina inferior derecha: y + height + width
        */
        if (pow(suma_x - model.getField().getX(), 2) + pow (this.getY() - model.getField().getY(),2)>= square || pow(suma_x + this.getWidth() - model.getField().getX(), 2) + pow (this.getY() - model.getField().getY(),2)>= square){
             setBoost_x(getBoost_x()*-1);
        }
        if (pow(this.getX() - model.getField().getX(), 2) + pow (suma_y - model.getField().getY(),2)>= square || pow(this.getX() - model.getField().getX(), 2) + pow (suma_y + this.getHeight() - model.getField().getY(),2)>= square){
             setBoost_x(getBoost_y()*-1);
        }
        this.setX(getX()+getBoost_x());
        this.setY(getY()+getBoost_y());
    }
   
}
