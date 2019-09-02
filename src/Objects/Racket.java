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
   
    public boolean field_collision(Model model, int x, int y){
        int x_dif = model.getField().getX() - x;
        int y_dif = model.getField().getY() - y;
        double distance = sqrt(pow(x_dif,2)+pow(y_dif,2));
        return distance >= model.getField().getRadio();
    }
    
    @Override
    public void move(Model model){
        int bx = getX()+getBoost_x();
        int by = getY()+getBoost_y();
            if(field_collision(model, bx,by) || field_collision(model, bx+width,by)
                    || field_collision(model, bx,by+height) || field_collision(model, bx+width,by+height)){
                setBoost_x(0);
                setBoost_y(0);
            }
        this.setX(getX()+getBoost_x());
        this.setY(getY()+getBoost_y());
    }
    
}
