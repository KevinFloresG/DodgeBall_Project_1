/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import Pattern.Model;

/**
 *
 * @author Kevin Flores
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
        //TODO
    }
}
