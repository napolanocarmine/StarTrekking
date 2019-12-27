package panels;

import gamestate.ExitState;
import gamestate.GameStateManager;
import gamestate.MainMenuState;
import gamestate.State;
import java.awt.*;
import java.io.IOException;
import javax.swing.*;

/**
 *
 * @author StarTrekking Class to open and draw the frame and to update the
 * thread
 */
public final class GameFrame extends JFrame {

    //window dimensions
    public static final int WIDTH = 960;
    public static final int HEIGHT = 560;
    //JFrame name
    public static final String NAME = "STAR TREKKING";
    private JPanel gamePanel;
    private GameStateManager gms;
    private State s;

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
        setLocationRelativeTo(null);
        setVisible(true);
        pack();

        this.s = new MainMenuState();
        this.gms = new GameStateManager(s);
        this.setContentPane(s.getPanel());
        gms.setListener(new GsmListener() {
            @Override
            public void stateChanged(State s) {
                gamePanel = s.getPanel();
                setContentPane(gamePanel);
                pack();
                revalidate();
                repaint();

                if (s instanceof ExitState) {
                    dispose();
                }
            }
        });

    }

}
