/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Pattern.*;

/**
 *
 * @author 
 * Kevin Flores
 * Fernanda González
 */
public class Main {
    public static void main(String[] args) throws InterruptedException{
        Model model = new Model();
       // model.setNumberOfBalls(3);
        View view = new View();
        Controller controller = new Controller(model, view);
        
        view.setVisible(true);
        
        for(;;){
            Thread.sleep(25);
            model.step();
            System.out.println(model.getPoints()+"\n");
        }
    }
}
