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
 * @author Kevin Flores
 */
public class Model extends Observable {
    /*
    private final static int UP = 1;
    private final static int DOWN = 2;
    private final static int LEFT = 3;
    private final static int RIGHT = 4;
    private List<Ball> balls;
    */
    private Ball ball;
    private Field field;
    //private Racket racket;

    public Model() {
        this.ball = new Ball(15,300,300,5,5);
        this.field = new Field(300,300,200);
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
        //racket.move(this);
        ball.move(this);
        this.setChanged();
        this.notifyObservers();
    }
}
