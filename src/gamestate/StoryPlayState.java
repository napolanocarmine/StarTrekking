/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import panels.GameFrame;
import panels.GamePanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Star Trekking
 */
public class StoryPlayState extends State {

    public static int level;
    /**
     * Create the Panel on which the Level is runned.
     */
    public StoryPlayState(int level){
        this.level = level;
        this.panel = new GamePanel(this);
    }

    /**
     * Method which handles the next state, that could be "Pause" or "Game-Over"
     *
     * @param code code represented the next state
     */
    @Override
    public void handleNext(int code) {
        /*
            codice che gestisce il prossimo stato,
            per questo sprint quando il maghetto muore da settare al Game-Over.
         */
         if(code == 1){
            SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    gsm.setState(new GameOverState());
                } catch (IOException ex) {
                    Logger.getLogger(StoryPlayState.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            });
             //this.frame.dispose();
//             gsm.setState(new GameOverState(gsm));
         }
     };
    
    //Non so se per il play va settato.
    @Override
    public void handlePrevious(int code) {
    }
    

}
