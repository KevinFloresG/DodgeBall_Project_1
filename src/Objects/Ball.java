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
    @Override
    public void move(Model model){
        // TODO
    }
}
