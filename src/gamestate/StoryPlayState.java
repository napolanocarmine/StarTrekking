/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import frames.GameFrame;

/**
 *
 * @author Star Trekking
 */
public class StoryPlayState extends State {

    private GameStateManager gsm;

    /**
     * Create the Panel on which the Level is runned.
     */
    public StoryPlayState(GameStateManager gsm) {
        this.frame = new GameFrame();
        //metodo che inizializza le componenti del JPanel;
        initComponent();
    }

    /**
     * Define the play panel component.
     */
    private void initComponent() {
        //Dovrebbe essere il codice presente in GamePanel
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
    }

    //Non so se per il play va settato.
    @Override
    public void handlePrevious(int code) {
    }

}
