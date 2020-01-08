package panels;

import gamestate.GameState;
import gamestate.GameStateManager;
import gamestate.MainMenuState;
import gamestate.PauseState;
import State.State;
import gamestate.StoryPlayState;
import java.awt.*;
import java.io.IOException;
import javax.swing.*;

/**
 *
 * @author StarTrekking 
 * Class to open and draw the frame and to update the
 * thread.
 */
public final class GameFrame extends JFrame implements GsmListener {

    //window dimensions

    /**
     *
     */
    public static final int WIDTH = 960;

    /**
     *
     */
    public static final int HEIGHT = 560;
    //JFrame name

    /**
     *
     */
    public static final String NAME = "STAR TREKKING";
    private JPanel gamePanel;
    private GameStateManager gsm;

    /**
     *Create the Frame where the thread game is update.
     * @throws IOException if game state manager Constructor raises IOException
     */
    public GameFrame() throws IOException   {
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
    
    /**
     *It is used to set the right panel on the Frame, based on the changed state.
     * @param s is the game state corresponding to the panel to be set.
     */
    @Override
    public synchronized void stateChanged(GameState s){        
        gamePanel = s.getPanel();
        System.out.println(s);
        setContentPane(gamePanel);
        pack();
        revalidate();
        repaint();
    }

    /**
     *It is used to exit the game and close the frame.
     */
    @Override
    public void exit() {
        this.dispose();
    }

    /**
     *It is used to return the Panel corresponding to a state.
     * @return the right Jpanel. 
     */
    public JPanel getGamePanel() {
        return gamePanel;
    }

    
}
