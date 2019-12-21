package panels;

import gamestate.ExitState;
import gamestate.GameStateManager;
import gamestate.MainMenuState;
import gamestate.SelectionLevelState;
import gamestate.State;
import gamestate.StoryPlayState;
import java.awt.*;
import java.io.IOException;
import javax.swing.*;
import util.KeyHandler;

public final class GameFrame extends JFrame{

    /*
    Responsabilità:
    aprire e disegnare il frame, thread di aggiornamento
    
    Operazioni:
    1- update ciclico di Map e Entity
    2- Riferimenti di oggetti Map, Entity, KeyHandler, TileFacade
     */
    //dimensione finestra
    public static final int WIDTH = 960;
    public static final int HEIGHT = 560;
    //nome JFrame
    public static final String NAME = "STAR TREKKING";
    private JPanel gamePanel;
    private GameStateManager gms;
    private State s;
    
    
    public GameFrame() throws IOException {
        setTitle(NAME);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setIgnoreRepaint(true);
        setLocationRelativeTo(null);
        setVisible(true);
        pack();
        
        this.s = new MainMenuState();    
        this.gms = new GameStateManager(s);
        this.setContentPane(s.getPanel());
        gms.setListener(new GsmListener(){
            @Override
            public void stateChanged(State s){
                gamePanel = s.getPanel();
                setContentPane(gamePanel);
                pack();
                revalidate();
                repaint();
                
                if (s instanceof ExitState){
                    dispose();
                }
            }
        });
        
    }
    
    
    
}
