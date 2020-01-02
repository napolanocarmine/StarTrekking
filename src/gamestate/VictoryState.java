/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

/**
 *
 * @author Gianluca
 */
public class VictoryState extends GameState {
    private GameStateManager gsm;
    private int level;
    
    public VictoryState(GameStateManager gsm) {
        this.gsm = gsm;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    
    @Override
    public void handleNext(int code) {
        if (code == 0) {
            if (level != 3) {
                ((StoryPlayState) gsm.getSps()).setLevel(level + 1);
            }
            gsm.setState(gsm.getSps());

        }
        if (code == 1) {
            gsm.setState(gsm.getSps());

        }
        if (code == 2) {
            gsm.setState(gsm.getMms());
        }
    }

    @Override
    public void handlePrevious(int code) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void set(){
        
    }
    
    
}
