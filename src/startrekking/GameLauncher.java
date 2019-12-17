/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startrekking;

import gamestate.*;
import javax.swing.SwingUtilities;


/**
 *
 * @author Star Trekking
 */

/***
 * Class that has the goal of launch the main menu.
 */
public class GameLauncher {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               new GameStateManager();
            }
        });
    }
}
