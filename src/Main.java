/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Pattern.*;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author 
 * Kevin Flores
 * Fernanda González
 */
public class Main {
    public static void main(String[] args) throws InterruptedException, UnsupportedAudioFileException{
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);
        view.setVisible(true); 
    }
}
