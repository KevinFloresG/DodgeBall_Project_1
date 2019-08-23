/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import Pattern.Model;

/**
 *
 * @author 
 * Kevin Flores
 * Fernanda González
 */
public abstract class Actor {
    
    private int x;
    private int y;
    private int boost_x;
    public int boost_y;

    public Actor(int x, int y, int boost_x, int boost_y) {
        this.x = x;
        this.y = y;
        this.boost_x = boost_x;
        this.boost_y = boost_y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getBoost_x() {
        return boost_x;
    }

    public void setBoost_x(int boost_x) {
        this.boost_x = boost_x;
    }

    public int getBoost_y() {
        return boost_y;
    }

    public void setBoost_y(int boost_y) {
        this.boost_y = boost_y;
    }
    
    public abstract void move(Model model);
    //public abstract boolean field_collision(Model model); 
}
