/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

/**
 *
 * @author 
 * Kevin Flores
 * Fernanda González
 */
public class Field {
    
    private int x;
    private int y;
    private int radio;

    public Field(int x, int y, int radio) {
        this.x = x;
        this.y = y;
        this.radio = radio;
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

    public int getRadio() {
        return radio;
    }

    public void setRadio(int radio) {
        this.radio = radio;
    }
}
