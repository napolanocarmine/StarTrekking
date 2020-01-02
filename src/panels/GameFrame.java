package panels;

import gamestate.ExitState;
import gamestate.GameState;
import gamestate.GameStateManager;
import gamestate.MainMenuState;
import gamestate.PauseState;
import gamestate.State;
import gamestate.StoryPlayState;
import java.awt.*;
import java.io.IOException;
import javax.swing.*;

/**
 *
 * @author StarTrekking Class to open and draw the frame and to update the
 * thread
 */
public final class GameFrame extends JFrame implements GsmListener {

    //window dimensions
    public static final int WIDTH = 960;
    public static final int HEIGHT = 560;
    //JFrame name
    public static final String NAME = "STAR TREKKING";
    private JPanel gamePanel;
    private GameStateManager gsm;

    /**
     *
     * @throws IOException Constructor of the class Update in loop of Map and
     * Entity and it has refers to Map, Entity, KeyHandler and TileFacade
     * objects
     */
    public GameFrame() throws IOException {
        setTitle(NAME);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setIgnoreRepaint(true);
        
        setVisible(true);
        
        this.gsm = new GameStateManager();

        this.setContentPane(gsm.getCurrentState().getPanel());
        pack();
        setLocationRelativeTo(null);
        gsm.setListener(this);
        
    }

    @Override
    public synchronized void stateChanged(GameState s) {
        
        if (s instanceof ExitState) {
            this.dispose();
        } 
        else {
            gamePanel = s.getPanel();
            setContentPane(gamePanel);
            s.set();
            pack();
            revalidate();
            repaint();
        }

    }

}
