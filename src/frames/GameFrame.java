/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import entity.Player;
import gamestate.State;
import gamestate.StoryPlayState;
import graphics.Sprite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.*;
import tiles.TileFacade;
import util.KeyHandler;
import util.Position;

/**
 *
 * @author Gianluca
 */     
public class GameFrame extends JFrame {

    private final StoryPlayState state;
    private JPanel gamePanel;

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 528;
    public static final String NAME = "STAR TREKKING";

    

    public GameFrame(StoryPlayState state) {
        this.state = state;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gamePanel = new GamePanel(this);
        this.add(gamePanel);
        //this.setContentPane(gamepanel);
        this.setIgnoreRepaint(false);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        setFocusable(true);
        requestFocus();
    }
    
    
    public State getFrameState(){
        return state;
    }
    
    /*
    it's necessary to insert in the keyHandler the button for pause, to open the pauseMenu, for implementing as the next state the main menu
    */
}
