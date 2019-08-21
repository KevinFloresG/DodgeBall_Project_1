/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import Pattern.Model;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 *
 * @author 
 * Kevin Flores
 * Fernanda González
 */
public class Ball extends Actor {
    
    private int radio;

    public Ball(int radio, int x, int y, int boost_x, int boost_y) {
        super(x, y, boost_x, boost_y);
        this.radio = radio;
    }

    public int getRadio() {
        return radio;
    }

    public void setRadio(int radio) {
        this.radio = radio;
    }
    
    public boolean field_collision(Model model){
        int x_dif = model.getField().getX() - this.getX();
        int y_dif = model.getField().getY() - this.getY();
        double distance = sqrt(pow(x_dif,2)+pow(y_dif,2))+2*radio;
        return distance >= model.getField().getRadio();
    }
    
    @Override
    public void move(Model model){
        if(field_collision(model)){
            setBoost_x(getBoost_x()*-1);
            setBoost_y(getBoost_y()*-1);
        }
        this.setX(getX()+getBoost_x());
        this.setY(getY()+getBoost_y());
    }
}
