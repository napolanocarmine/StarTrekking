package entity;

import graphics.EntitySprite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import frames.GameFrame;
import frames.GamePanel;
import frames.MainMenuFrame;
import frames.SelectionLevelFrame;
import static frames.SelectionLevelFrame.clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import sun.audio.AudioPlayer;
import tiles.TileFacade;
import util.AABB;
import util.KeyHandler;
import util.Position;
import util.EntityState;

public class Player extends Entity implements Observer {

    private final int MAXHEALTHPOINTS = 3;
    private final float H = 100;
    private final float DIST = 150;
    private int hp;
    
    private KeyHandler khdl;
    int action;
    private ArrayList<Shot> shots = new ArrayList<Shot>();
    
    public Clip clipGameOver;
    
    DecimalFormat df = new DecimalFormat();
    private boolean invincible;
    private float invStartTime;
    private boolean visible;
    private boolean falling = false;

    public Player(EntitySprite sprite, Position origin, int size, KeyHandler khdl) {
        super(sprite, origin, size, EntityState.RUN);
        this.hp = MAXHEALTHPOINTS;
        this.bounds = new AABB(pos, 16, 32, 40, 32);
        this.khdl = khdl;
        df.setMaximumFractionDigits(2);
        this.initialSpeed = 0.3f;
        this.vx = initialSpeed;
        this.acc = 0.00001f;
        this.visible = true;
        this.invincible = false;
    }

    public void move() {
        //PLAYER HORIZONTAL MOTION
        if(timex == 0) vx = initialSpeed;
        dx = (float)((0.5*acc*Math.pow(timex, 2) + vx*timex)) + dx0;
               
        //dx = (float)((vx*timex));
        if(tc.collisionTile(dx-previousX, 0)){
            //System.err.println("collision front");
            dx = previousX;
        } else {
            if (state != EntityState.DEAD) {
                timex += 1f;
            }
            previousX = dx;
        }

        //PLAYER VERTICAL MOTION
        
        if(state == EntityState.JUMP){
            if(timey == 0){
                float vx0 = vx + acc*(timex);
                if(!falling) vy = -(float)((4*H*vx0)/DIST);
                gravity = -(float)((H*8*Math.pow(vx0, 2))/Math.pow(DIST, 2));
                
//                System.err.println("dy: " + dy);
//                System.err.println("gravity: " + gravity);
            try {
                Clip clipJump;
                AudioInputStream audio;
                audio = AudioSystem.getAudioInputStream(AudioPlayer.class.getResourceAsStream("/sounds/JumpMusic.wav"));
                clipJump = AudioSystem.getClip();
                clipJump.open(audio);
                clipJump.start();
                //clipJump.loop(Clip.LOOP_CONTINUOUSLY);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            }
            if (ani.playingLastFrame()) {
                ani.setDelay(-1);
            }
        } else {
            vy = 0;
            gravity = -0.01f;
        }
        
        dy = (float)((-0.5*gravity*Math.pow(timey, 2)+vy*timey)+dy0);
        if(tc.collisionTileDown(0, dy-previousY)){
            //System.err.println("collision down");
            dy = previousY;
            timey = 0;
            dy0 = dy;
            falling = false;
            if(state != EntityState.ATTACK && state != EntityState.DEAD) state = EntityState.RUN;
        }else if(tc.collisionTileUp(0, dy-previousY)){
            dy = previousY;
            dy0 = previousY;
            vy = 0;
            timey = 0;
            falling = true;
        } else {
            timey += 1f;
            previousY = dy;
        }

        if (state == EntityState.ATTACK) {
            try {
                AudioInputStream audio;
                audio = AudioSystem.getAudioInputStream(AudioPlayer.class.getResourceAsStream("/sounds/ShotMusic.wav"));
                clip = AudioSystem.getClip();
                clip.open(audio);
                clip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (ani.playingLastFrame()) {
                attack();
                state = EntityState.RUN;
            }
        }

        if (state == EntityState.DEAD) {
            isDead();
            try {
                Clip clipJump;
                AudioInputStream audio;
                audio = AudioSystem.getAudioInputStream(AudioPlayer.class.getResourceAsStream("/sounds/GameOverMusic.wav"));
                clipJump = AudioSystem.getClip();
                clipJump.open(audio);
                clipJump.start();
                //clipJump.loop(Clip.LOOP_CONTINUOUSLY);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    
    public void isDead(){
        if(ani.playingLastFrame()){
            dead = true;
            SelectionLevelFrame.clip.stop();
            try {
                AudioInputStream audio;
                audio = AudioSystem.getAudioInputStream(AudioPlayer.class.getResourceAsStream("/sounds/GameOverMusic.wav"));
                clipGameOver = AudioSystem.getClip();
                clipGameOver.open(audio);
                clipGameOver.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void hitted() {
        invincible = true;
        visible = false;
        invStartTime = System.nanoTime();
        if(--hp==0){
            isDead();
            state = EntityState.DEAD;
        }
    }

    private void attack() {
        shots.add(new Shot(new EntitySprite("Entity/shot", 32, 32), new Position(dx - 15, pos.getY() + 24), 48, vx + acc * (timex)));
    }
    
//    private void restart(){
//        System.err.println("restart");
//        state = EntityState.RUN;
//        timex = 0;
//        timey = 0;
//        vx = initialSpeed;
//        acc = initialAcc;
//        //previousX = initialX;
//        y0 = initialY;
//        invincible = false;
//        visible = true;
//        this.hp = 3;
//        pos.setPos(originPos.getX(), originPos.getY());
//        GamePanel.getMapPos().setPos(0, 0);
//    }
    
    public ArrayList<Shot> getShots(){
        return shots;
    }

    public void deleteShot(Shot s) {
        shots.remove(s);
    }
    
    public void updateGame(){
        super.updateGame(state);
        if(invincible){
            if(System.nanoTime()%9000 < 100 || System.nanoTime()%9000 > 100) visible = !visible;
            if(System.nanoTime() - invStartTime>= GamePanel.unitTime){
                invincible = false;
                visible = true;
            }
        }
        move();
        pos.setX(dx);    //update x position
        if (GamePanel.getMapPos().getX() + GameFrame.WIDTH < TileFacade.mapWidth * 16) {
            GamePanel.getMapPos().setX(dx);
        }
        pos.setY(dy);
        if(!shots.isEmpty()){
            for(int i=0; i<shots.size(); i++){
                if(shots.get(i).pos.getWorldVar().getX() - pos.getWorldVar().getX() > GameFrame.WIDTH || shots.get(i).collides()){
                    deleteShot(shots.get(i));
                } else {
                    shots.get(i).updateGame();
                }
            }
        }
        if(pos.getY() > GameFrame.HEIGHT){
                dead = true;
        }
    }

    @Override
    public void render(Graphics2D g) {  //draw the player in the panel
        if (visible == true) {
            g.drawImage(ani.getImage(), (int) pos.getWorldVar().getX(), (int) pos.getWorldVar().getY(), size, size, null);
            g.setColor(Color.blue);
            g.drawRect((int) (pos.getWorldVar().getX() + bounds.getXOffset()), (int) (pos.getWorldVar().getY() + bounds.getYOffset()), (int) bounds.getWidth(), (int) bounds.getHeight());
        }

        if (!shots.isEmpty()) {
            for (int i = 0; i < shots.size(); i++) {
                shots.get(i).render(g);
            }
        }
    }

    private void mapValueAction(int key, boolean b) {
        if (true) { //in case the player is alive
            if ((key == 4) && (state != EntityState.JUMP)
                    && (!tc.collisionTileDown(0, dy - previousY)) && currentState == EntityState.RUN && b) {
                state = EntityState.JUMP;
                timey = 0;
            } else if (key == 3 && currentState == EntityState.RUN) {
                state = EntityState.ATTACK;
            }
        } else {
            state = EntityState.DEAD;
        }

    }

    @Override
    public void update(Observable o, Object s) {
        if (o == this.khdl) {
            int key = this.khdl.getValue();
            boolean b = khdl.isPressed();
            mapValueAction(key, b);
        }
    }

    public int getHP() {
        return this.hp;
    }
}
