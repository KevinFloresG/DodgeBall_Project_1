/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pattern;

import Objects.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

//import java.util.List;

/**
 *
 * @author 
 * Kevin Flores
 * Fernanda González
 */
public class Model extends Observable {
    
    private List<Ball> balls;
    private Ball ball;
    private Field field;
    private Racket racket;
    private int points;
    private double[] goals;
    public static final int ARR=1;
    public static final int ABA=2;
    public static final int IZQ=3;
    public static final int DER=4;
    

    public Model() {
        this.balls = new ArrayList<>();
        this.ball = new Ball(20,300,300,5,5);
        this.field = new Field(300,300,200);
        this.racket = new Racket(100, 50, 250, 300, 0, 0);
        this.points = 0;
        this.goals = new double[]{
            field.getRadio()*Math.sin(Math.toRadians(22.5)),
            field.getRadio()*Math.cos(Math.toRadians(45))            
        };   
        reset(10,5);
    }
    
    public void reset(int cant, int v){
        if(cant > 11 || cant < 1 || v > 25 || v < 1){ return; }
        Random cor = new Random();
        int cuadro = field.getX() - field.getRadio()/2;
        balls.clear();
        for(int x=0; x < cant; x++){ 
            balls.add(new Ball(20,cuadro + cor.nextInt(cuadro),cuadro + cor.nextInt(cuadro),v,v));
        }
    }

    public Racket getRacket() {
        return racket;
    }

    public void setRacket(Racket racket) {
        this.racket = racket;
    }

    public double[] getGoals() {
        return goals;
    }

    public void setGoals(double[] goal) {
        this.goals = goal;
    }

    public Ball getBall() {
        return ball;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public Field getField() {
        return field;
    }

    public List<Ball> getBalls() {
        return balls;
    }

    public void setBalls(List<Ball> balls) {
        this.balls = balls;
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
        //ball.move(this);
        for(int x=0; x < balls.size(); x++){
            balls.get(x).move(this);
        }
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
