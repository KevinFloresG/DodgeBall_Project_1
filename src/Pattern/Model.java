/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pattern;

import Objects.*;
import java.util.Observable;
import java.util.Observer;

//import java.util.List;

/**
 *
 * @author 
 * Kevin Flores
 * Fernanda González
 */
public class Model extends Observable {
    /*
    private List<Ball> balls;
    */
    private Ball ball;
    private Field field;
    private Racket racket;
    public static final int ARR=1;
    public static final int ABA=2;
    public static final int IZQ=3;
    public static final int DER=4;
    

    public Model() {
        this.ball = new Ball(15,300,300,5,5);
        this.field = new Field(300,300,200);
        this.racket = new Racket(60, 15, 250, 300, 0, 0);
    }

    public Racket getRacket() {
        return racket;
    }

    public void setRacket(Racket racket) {
        this.racket = racket;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
    
    @Override
    public void addObserver(Observer observer){
        super.addObserver(observer);
        setChanged();
        notifyObservers();
    }
    
    public void step(){
        racket.move(this);
        ball.move(this);
        this.setChanged();
        this.notifyObservers();
    }
    public void move(int flecha){
        switch (flecha){
            case ARR: 
                racket.setBoost_y(-2); //-
                break;
            case ABA: 
                racket.setBoost_y(2);
                break;
            case IZQ: 
                racket.setBoost_x(-2); //-
                break;
            case DER: 
                racket.setBoost_x(2);
                break;
        }
    }
    public void stopVer(){
        racket.setBoost_y(0);
    }
    public void stopHor(){
        racket.setBoost_x(0);
    }
}
