/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamestate;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Gianluca
 */
public class SelectLevelState extends State{
    private GameStateManager gsm;
    
    public SelectLevelState(GameStateManager gsm){
        this.gsm = gsm;
        this.frame = new SelectionLevelFrame("/screen/forest.png",this);
        //metodo che inizializza le componenti del JPanel;
        //initComponent();
    }
        
    
    /**
     * Called when "Level 1" or "Level 2" or "Level 3" is pressed, 
     * based on the pressed button the next state is set. 
     * @param code button code 
     */
    @Override
    public void handleNext(int code){
        if(code == 1){
            frame.dispose();
            gsm.setState(new StoryPlayState(gsm));
        }else if(code == 2){
            //avviare GamePanel2
        }else{
            //avviare GamePanl3
        }
    }
    
    //Non so se per il main menu va settato.
    @Override
    public void handlePrevious(int code){};
      
    /**
     * Selection Level Panel.
     */
    private class SelectionLevelFrame extends JFrame {
        private Image img;
        private JButton level1;
        private JButton level2;
        private JButton level3;
        private JLabel title;
        private SelectLevelState state;
        
        public SelectionLevelFrame(String img,SelectLevelState state) {
            this.state = state;
            setImage(new ImageIcon(img).getImage());
            initComponent();
            setVisible(true);
        }

        private void setImage(Image img) {
            this.img = img;
            Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
            setPreferredSize(size);
            setMinimumSize(size);
            setMaximumSize(size);
            setSize(size);
            setLayout(null);
        }

        private void initComponent() {
            level3 = new javax.swing.JButton();
            title = new javax.swing.JLabel();
            level2 = new javax.swing.JButton();
            level1 = new javax.swing.JButton();

            this.setSize(1000, 528);
            this.setResizable(false);
            this.setLocationRelativeTo(null);
            
            level3.setText("Level Three");
            level3.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    level3ActionPerformed(evt);
                }
            });

            title.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
            title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            title.setText("Levels");
            title.setToolTipText("");

            level2.setText("Level Two");
            level2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    level2ActionPerformed(evt);
                }
            });

            level1.setText("Level One");
            level1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    level1ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addContainerGap())
                            .addGroup(layout.createSequentialGroup()
                                    .addGap(147, 147, 147)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(level2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(level3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(level1, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE))
                                    .addContainerGap(160, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addContainerGap(42, Short.MAX_VALUE)
                                    .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(25, 25, 25)
                                    .addComponent(level1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(level2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(level3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(37, 37, 37))
            );

        }

            
        private void level3ActionPerformed(java.awt.event.ActionEvent evt) {
            // TODO add your handling code here:
        }

        private void level2ActionPerformed(java.awt.event.ActionEvent evt) {
            // TODO add your handling code here:
        }

        private void level1ActionPerformed(java.awt.event.ActionEvent evt) {
            // TODO add your handling code here:
           state.handleNext(1);

        }
    
        public void paintComponent(Graphics g) {
            g.drawImage(img, 0, 0, null);
        }
    }
    
    
}
