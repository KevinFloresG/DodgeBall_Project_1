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
    private int velocidad;
    private boolean running;
    

    public Model() {
        this.running = true;
        this.balls = new ArrayList<>();
        this.field = new Field(300,320,250);
        this.racket = new Racket(100, 50, 250, 300, 0, 0);
        this.points = 0;
        this.goals = new double[]{
            field.getRadio()*Math.sin(Math.toRadians(22.5)),
            field.getRadio()*Math.cos(Math.toRadians(45))            
        };   
        this.velocidad = 5;
        reset(1,velocidad);
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }
    
    public Ball getball(){ return balls.get(0); }
    
    public void reset(int cant, int v){
        if(v>0) this.velocidad = v;
        if(cant > 30 || cant < 1 || v > 25 || v < 1){ return; }
        Random cor = new Random();
        int cuadro = field.getX() - field.getRadio()/2;
        balls.clear();
        for(int x=0; x < cant; x++){ 
            balls.add(new Ball(20,cuadro + cor.nextInt(cuadro),cuadro + cor.nextInt(cuadro),v,v));
        }
        points =0;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
    
    public void pause(){
        running = false;
    }
    
    public void seguir(){
        running = true;
        this.start();
    }
    public void start(){
        final int delay = 30;
        Runnable code = new Runnable() { 
            public void run(){
            while(running){
                step();
                setChanged();
                notifyObservers();
                try{Thread.sleep(delay);} catch (InterruptedException ex) {
                }
            }
        }
        };
        Thread thread = new Thread(code);
        thread.start();
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
        for(int x=0; x < balls.size(); x++){
            balls.get(x).move(this);
        }
        this.setChanged();
        this.notifyObservers();
    }
    public void move(int flecha){
        switch (flecha){
            case ARR: 
                racket.setBoost_y(-5); //-
                break;
            case ABA: 
                racket.setBoost_y(5);
                break;
            case IZQ: 
                racket.setBoost_x(-5); //-
                break;
            case DER: 
                racket.setBoost_x(5);
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
