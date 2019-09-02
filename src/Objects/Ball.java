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
    private boolean collision1;
    private boolean collision2;

    public Ball(int radio, int x, int y, int boost_x, int boost_y) {
        super(x, y, boost_x, boost_y);
        this.collision1 = false;
        this.collision2 = false;
        this.radio = radio;
    }

    public boolean isCollision1() {
        return collision1;
    }

    public void setCollision1(boolean collision1) {
        this.collision1 = collision1;
    }

    public boolean isCollision2() {
        return collision2;
    }

    public void setCollision2(boolean collision2) {
        this.collision2 = collision2;
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
        double distance = sqrt(pow(x_dif,2)+pow(y_dif,2))+radio;
        return distance >= model.getField().getRadio();
    }
    
    public int racket_collision(Model model){
        int racket_x = model.getRacket().getX();
        int racket_y = model.getRacket().getY();
        int racket_w = model.getRacket().getWidth();
        int racket_h = model.getRacket().getHeight();
        
        if(getX() > racket_x && getX() < racket_x+racket_w){
            if(getBoost_y() > 0 && getY()+radio/2 > racket_y && getY()+radio/2 < racket_y+racket_h){
                // rebote por arriba
                return 1;
            }
            else{
                if(getY()-radio/2 > racket_y && getY()-radio/2 < racket_y+racket_h){
                    // rebote por abajo
                    setY(getY()+radio/2);
                    return 2;
                }
            }
        }
        else{
            if(getY() > racket_y && getY() < racket_y+racket_h){
                if(getBoost_x() > 0 && getX()+radio/2 > racket_x && getX()+radio/2 < racket_x+racket_w){
                  // rebote por la izquierda
                    return 3;
                }
                else{
                    if(getX()-radio/2 > racket_x && getX()-radio/2 < racket_x+racket_w){
                     // rebote por la derecha
                        return 4;
                    }
                } 
            }
        }
        // no rebota
        return 0;
    }
    
    public void contador(Model model){
        int fx = model.getField().getX();
        int fy = model.getField().getY();
        double[] limits = model.getGoals();
        
        if(getY() < fy){
            if(getX() > fx){
                if(getY() >= fy-limits[0]){ 
                    model.setPoints(model.getPoints()+1); collision1 = true;
                }
                else{
                    if(getX() >= fx+limits[0] && getY() <= fy-limits[1]){
                        model.setPoints(model.getPoints()-1); collision2 = true;
                    }
                }
            }
            else{
                if(getX() >= fx-limits[0]){
                    model.setPoints(model.getPoints()+1); collision1 = true;
                }
                else{
                    if(getY() <= fy-limits[0] && getY() >= fy-limits[1]){
                        model.setPoints(model.getPoints()-1); collision2 = true;
                    }
                }
            }
        }
        else{
            if(getX() < fx){
                if(getY() <= fy+limits[0]){
                    model.setPoints(model.getPoints()+1); collision1 = true;
                }
                else{
                    if(getY() >= fy+limits[1] && getX() <= fx-limits[0]){
                        model.setPoints(model.getPoints()-1); collision2 = true;
                    }
                }
            }
            else{
                if(getX() <= fx+limits[0]){
                    model.setPoints(model.getPoints()+1); collision1 = true;
                }
                else{
                    if(getY()>= fy+limits[0] && getY() <= fy+limits[1]){
                        model.setPoints(model.getPoints()-1); collision2 = true;
                    }
                }
            }
        }
    }
    
    @Override
    public void move(Model model){
        if(field_collision(model)){
            contador(model);
            if(getY() > model.getField().getY() && getBoost_y() < 0){ 
                this.setY(getY() - radio/2);
            }
            setBoost_x(getBoost_x()*-1);
            setBoost_y(getBoost_y()*-1);
        }
        else{
            switch(racket_collision(model)){
                case 0: break;
                case 1: setBoost_y(getBoost_y()*-1); break;
                case 2: setBoost_y(getBoost_y()*-1); break;
                case 3: setBoost_x(getBoost_x()*-1); break;
                case 4: setBoost_x(getBoost_x()*-1); break;
            }
        }
        this.setX(getX()+getBoost_x());
        this.setY(getY()+getBoost_y());
    }
}
