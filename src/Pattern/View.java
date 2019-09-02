/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pattern;

import Objects.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 *
 * @author 
 * Kevin Flores
 * Fernanda González
 */
public class View extends JFrame implements Observer{
    
    private javax.swing.JButton acept;
    private javax.swing.JLabel balls;
    private javax.swing.JButton cancel;
    private javax.swing.JMenu edit;
    private javax.swing.JMenuItem exit;
    private javax.swing.JMenu file;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JTextField n_balls;
    private javax.swing.JTextField n_speed;
    private javax.swing.JMenuItem settings;
    private javax.swing.JLabel speed;
    private javax.swing.JDialog sub_settings;
    private javax.swing.JMenuItem ddgball;
    private javax.swing.JMenu about;
    
    private Model model;
    private Controller controller;
    Image fondo;
    Image raqueta;
    Image ballImage;
    BufferedImage buffer;
    Clip plus;
    Clip less;

    public View() throws UnsupportedAudioFileException {
        initComponents();
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        this.setContentPane(
            new JPanel(){
                @Override
                public void paint(Graphics g){
                   g.drawImage(fondo,0,0,this);
                }
            }
        );
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt){
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt){
                formKeyReleased(evt);
            }
        });
        
        try{
            plus = loadSound("../media/ding.wav");
            less = loadSound("../media/chord.wav");
            buffer = new BufferedImage(850,600,BufferedImage.TYPE_INT_RGB);
            fondo= ImageIO.read(getClass().getResourceAsStream("../media/fondo.jpg"));
            raqueta=ImageIO.read(getClass().getResourceAsStream("../media/raqueta.png"));
            ballImage = ImageIO.read(getClass().getResourceAsStream("../media/ball.png"));
        }catch(IOException ex){}
    }
    private void initComponents() {
        
        sub_settings = new javax.swing.JDialog();
        balls = new javax.swing.JLabel();
        acept = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        n_balls = new javax.swing.JTextField();
        n_speed = new javax.swing.JTextField();
        speed = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        file = new javax.swing.JMenu();
        exit = new javax.swing.JMenuItem();
        edit = new javax.swing.JMenu();
        settings = new javax.swing.JMenuItem();
        about = new javax.swing.JMenu();
        ddgball = new javax.swing.JMenuItem();

        sub_settings.setTitle("Settings");
        sub_settings.setMinimumSize(new java.awt.Dimension(359, 270));

        sub_settings.getContentPane().setLayout(null);

        balls.setText("Balls (30 max)");
        sub_settings.getContentPane().add(balls);
        balls.setBounds(64, 34, 120, 20);

        acept.setText("Acept");
        acept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptActionPerformed(evt);
            }
        });
        sub_settings.getContentPane().add(acept);
        acept.setBounds(50, 170, 70, 23);

        cancel.setText("Cancel");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });
        sub_settings.getContentPane().add(cancel);
        cancel.setBounds(190, 170, 73, 23);

        n_balls.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                n_ballsKeyTyped(evt);
            }
        });
        sub_settings.getContentPane().add(n_balls);
        n_balls.setBounds(120, 60, 80, 20);
        sub_settings.getContentPane().add(n_speed);
        n_speed.setBounds(120, 120, 80, 20);
 

        speed.setText("Speed (25 max)");
        sub_settings.getContentPane().add(speed);
        speed.setBounds(60, 100, 120, 14);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                formWindowLostFocus(evt);
            }
        });
        getContentPane().setLayout(null);

        file.setText("File");

        exit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        exit.setText("Exit");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        file.add(exit);

        menuBar.add(file);

        edit.setText("Edit");

        settings.setText("Settings");
        settings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsActionPerformed(evt);
            }
        });
        edit.add(settings);

        menuBar.add(edit);

        about.setText("About");

        ddgball.setText("Dodge Ball");
        ddgball.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ddgballActionPerformed(evt);
            }
        });
        about.add(ddgball);

        menuBar.add(about);

        setJMenuBar(menuBar);

        pack();
    }   
    
    private void exitActionPerformed(java.awt.event.ActionEvent evt) {                                     
        System.exit(0);
    } 
      
    private void ddgballActionPerformed(java.awt.event.ActionEvent evt) {   
        JOptionPane.showMessageDialog(this, "Dodgeball 1.0. Programacion III. Escuela de informatica. UNA.");
    } 

    private void settingsActionPerformed(java.awt.event.ActionEvent evt) { 
        sub_settings.setLocationRelativeTo(this);
        n_balls.setText(Integer.toString(model.getBalls().size()));
        n_speed.setText(Integer.toString(model.getVelocidad()));
        sub_settings.setVisible(true);        
    } 
    
    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {                                       
        model.seguir();
    }                                      

    private void formWindowLostFocus(java.awt.event.WindowEvent evt) {                                     
        model.pause();
    }  
    
    private void n_ballsKeyTyped(java.awt.event.KeyEvent evt) {                                 
        char c = evt.getKeyChar();
        if(Character.isLetter(c)){
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(sub_settings, "Solo puedes ingresar numeros.");
        }
    }                                
    
    private void aceptActionPerformed(java.awt.event.ActionEvent evt) {
        model.reset(Integer.parseInt(n_balls.getText()), Integer.parseInt(n_speed.getText()));
        n_balls.setText(Integer.toString(model.getBalls().size()));
        n_speed.setText(Integer.toString(model.getVelocidad()));
        sub_settings.setVisible(false);
    }                                     

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {                                       
        sub_settings.setVisible(false);
    } 
     
    private void formKeyPressed(java.awt.event.KeyEvent evt){
        switch (evt.getKeyCode()){
            case KeyEvent.VK_UP: controller.move(Model.ARR); break;
            case KeyEvent.VK_DOWN: controller.move(Model.ABA); break;
            case KeyEvent.VK_LEFT: controller.move(Model.IZQ); break;
            case KeyEvent.VK_RIGHT: controller.move(Model.DER); break;
        }
    }
    
    private void formKeyReleased(java.awt.event.KeyEvent evt){
        int key = evt.getKeyCode();
        switch(key){
             case KeyEvent.VK_LEFT: case KeyEvent.VK_RIGHT: controller.stopHor(); break;
             case KeyEvent.VK_UP: case KeyEvent.VK_DOWN: controller.stopVer(); break;
        }
    }
    
    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addObserver(this);
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
    
    @Override
    public void update(Observable o, Object arg){
        this.repaint();
    }
    
    @Override
    public void paint(Graphics graphics){
        Graphics g = buffer.getGraphics();
        super.paint(g);
        this.render(model, g);
        graphics.drawImage(buffer, 0,0,null);
    }
    
    void render(Model m, Graphics media){
        render(m.getBalls(), media);
        render(m.getPoints(), media);
        renderRacket(m.getRacket(), media);
        renderArcos(m.getField(), media);
    }
    
    void render(List<Ball> balls, Graphics media){
        for(int x=0; x < balls.size(); x++){
            render(balls.get(x), media);
        }
    }
        void renderArcos(Field f, Graphics media){
        Graphics2D circ= (Graphics2D) media;
        circ.setStroke(new BasicStroke(10.f));
        circ.setColor(Color.green);
        circ.drawArc(f.getX()-f.getRadio(), f.getY()-f.getRadio(), 2* f.getRadio(), 2* f.getRadio(), 115, -22);
        circ.setColor(Color.red);
        circ.drawArc(f.getX()-f.getRadio(), f.getY()-f.getRadio(), 2* f.getRadio(), 2* f.getRadio(), 65, -18);
        circ.setColor(Color.green);
        circ.drawArc(f.getX()-f.getRadio(), f.getY()-f.getRadio(), 2* f.getRadio(), 2* f.getRadio(), 22, -15);
        circ.setColor(Color.red);
        circ.drawArc(f.getX()-f.getRadio(), f.getY()-f.getRadio(), 2* f.getRadio(), 2* f.getRadio(), -45, 22);
        circ.setColor(Color.green);
        circ.drawArc(f.getX()-f.getRadio(), f.getY()-f.getRadio(), 2* f.getRadio(), 2* f.getRadio(), -90, 19);
        circ.setColor(Color.red);
        circ.drawArc(f.getX()-f.getRadio(), f.getY()-f.getRadio(), 2* f.getRadio(), 2* f.getRadio(), -130, 20);
        circ.setColor(Color.green);
        circ.drawArc(f.getX()-f.getRadio(), f.getY()-f.getRadio(), 2* f.getRadio(), 2* f.getRadio(), -180, 22);
        circ.setColor(Color.red);
        circ.drawArc(f.getX()-f.getRadio(), f.getY()-f.getRadio(), 2* f.getRadio(), 2* f.getRadio(), -230, 22);
        
    }
    
    void render(Ball b, Graphics media){
       media.drawImage(ballImage,b.getX()-b.getRadio(), b.getY()-b.getRadio(), this);
       if(b.isCollision1()){
            plus.setFramePosition(0);
            plus.start();
            b.setCollision1(false);
        }
        if(b.isCollision2()){
            less.setFramePosition(0);
            less.start();
            b.setCollision2(false);
        }
    }
    
    void render(Field f, Graphics media){
        media.drawImage(fondo,f.getX()-f.getRadio(), f.getY()-f.getRadio(), f.getRadio()*2, f.getRadio()*2, this);
        media.setColor(Color.black);
        media.drawOval(f.getX()-f.getRadio(), f.getY()-f.getRadio(), f.getRadio()*2, f.getRadio()*2);
    }
    
    void render(int points, Graphics media){
        media.setFont( new Font( "Arial", Font.BOLD, 46 ) );
        media.drawString(Integer.toString(points), 475, 100);
    }
    
    void renderArcos(Field f, Graphics media){
        Graphics2D circ= (Graphics2D) media;
        circ.setStroke(new BasicStroke(10.f));
        circ.setColor(Color.green);
        circ.drawArc(f.getX()-f.getRadio(), f.getY()-f.getRadio(), 2* f.getRadio(), 2* f.getRadio(), 115, -22);
        circ.setColor(Color.red);
        circ.drawArc(f.getX()-f.getRadio(), f.getY()-f.getRadio(), 2* f.getRadio(), 2* f.getRadio(), 65, -18);
        circ.setColor(Color.green);
        circ.drawArc(f.getX()-f.getRadio(), f.getY()-f.getRadio(), 2* f.getRadio(), 2* f.getRadio(), 22, -15);
        circ.setColor(Color.red);
        circ.drawArc(f.getX()-f.getRadio(), f.getY()-f.getRadio(), 2* f.getRadio(), 2* f.getRadio(), -45, 22);
        circ.setColor(Color.green);
        circ.drawArc(f.getX()-f.getRadio(), f.getY()-f.getRadio(), 2* f.getRadio(), 2* f.getRadio(), -90, 19);
        circ.setColor(Color.red);
        circ.drawArc(f.getX()-f.getRadio(), f.getY()-f.getRadio(), 2* f.getRadio(), 2* f.getRadio(), -130, 20);
        circ.setColor(Color.green);
        circ.drawArc(f.getX()-f.getRadio(), f.getY()-f.getRadio(), 2* f.getRadio(), 2* f.getRadio(), -180, 22);
        circ.setColor(Color.red);
        circ.drawArc(f.getX()-f.getRadio(), f.getY()-f.getRadio(), 2* f.getRadio(), 2* f.getRadio(), -230, 22);
        
    }
    
    void renderRacket(Racket r, Graphics media){
       media.drawImage(raqueta,r.getX(), r.getY(), r.getWidth(), r.getHeight(), this);
    }
    
    Clip loadSound(String path) throws UnsupportedAudioFileException, IOException{
        try{
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
            getClass().getResourceAsStream(path));
            AudioFormat soundFormat = audioInputStream.getFormat();
            int soundSize = (int)(soundFormat.getFrameSize()*audioInputStream.getFrameLength());
            byte[] soundData = new byte[soundSize];
            DataLine.Info soundInfo = new DataLine.Info(Clip.class, soundFormat, soundSize);
            audioInputStream.read(soundData,0,soundSize);
            Clip clip = (Clip) AudioSystem.getLine(soundInfo);
            clip.open(soundFormat, soundData,0,soundSize);
            return clip;
        }
        catch(Exception ex){ return null; }
    
    }
}
