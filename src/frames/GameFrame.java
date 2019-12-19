package frames;

import gamestate.GameStateManager;
import gamestate.StoryPlayState;
import java.awt.*;
import javax.swing.*;

public final class GameFrame extends JFrame {

    /*
    Responsabilità:
    aprire e disegnare il frame, thread di aggiornamento
    
    Operazioni:
    1- update ciclico di Map e Entity
    2- Riferimenti di oggetti Map, Entity, KeyHandler, TileFacade
     */
    //dimensione finestra
    public static final int WIDTH = 980;
    public static final int HEIGHT = 528;
    //nome JFrame
    public static final String NAME = "STAR TREKKING";
    private GamePanel gamePanel;
    
    public GameFrame(StoryPlayState sps) {
        System.err.println("GameFrame");
        setTitle(NAME);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        gamePanel = new GamePanel(sps);
        setContentPane(gamePanel);
        setIgnoreRepaint(true);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

}
