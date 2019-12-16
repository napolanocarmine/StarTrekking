/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import javax.swing.*;

/**
 *
 * @author Star Trekking
 */
/**
 * 
 * Class which represents the state in which the game shows the main menu.
 */
public class MainMenuState extends State{
    GameStateManager gsm;
    /**
     * Create the Main Menu.
     */
    public MainMenuState(GameStateManager gsm){
        this.gsm = gsm;
        this.panel = new MainMenuPanel(this);
        //metodo che inizializza le componenti del JPanel;
        initComponent();
    }
    
    /**
     * Define the main menu's components.
     */
    private void initComponent(){
        
    }
    
    /**
     * Called when either "Story-Mode" or "Rush-Mode" is pressed, 
     * based on the pressed button the next state is set. 
     * @param state button code
     */
    //@Override
     public void  handleNext(int state){
         if(state == 0){
             gsm.setState(new SelectLevelState(gsm));
         }else if(state == 1){
             gsm.setState(new ExitState(gsm));
         }
     };
    
    //Non so se per il main menu va settato.
    @Override
    public void handlePrevious(int code){};

    /**
     * Panel that must appear when the game is in MainMenuState 
     */
    private class MainMenuPanel extends JPanel{
       
        private MainMenuState state;
        private JButton storyModeButton;
        private JButton exitButton;
        private JLabel title;

        public MainMenuPanel(MainMenuState state) {
            this.state = state;

//            this.panel = new ImagePanel(new ImageIcon(getClass()
//                    .getResource("/screen/forest.png"))
//                    .getImage());
            //metodo che inizializza le componenti del JPanel;
            initComponent();
        }

        /**
         * Define the main menu's components.
         */
        private void initComponent() {
            storyModeButton = new javax.swing.JButton();
            title = new javax.swing.JLabel();
            exitButton = new javax.swing.JButton();

            storyModeButton.setText("StoryMode");
            storyModeButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    storyModeButtonActionPerformed(evt);
                }
            });

            title.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
            title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            title.setText("STAR TREKKING");
            title.setToolTipText("");

            exitButton.setText("Exit");
            exitButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    exitButtonActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
                                    .addContainerGap())
                            .addGroup(layout.createSequentialGroup()
                                    .addGap(147, 147, 147)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(exitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(storyModeButton, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE))
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addContainerGap(42, Short.MAX_VALUE)
                                    .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(storyModeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(101, 101, 101))
            );

        }
        
        
        private void storyModeButtonActionPerformed(java.awt.event.ActionEvent evt) {
            state.handleNext(0);
            //State newState = new SelectLevelState(gsm);
            //gsm.insertState(newState);
        }

        private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {
            state.handleNext(1);
        }
        
    }

}
