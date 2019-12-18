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
import tiles.TileFacade;
import util.AABB;
import util.KeyHandler;
import util.Position;
import util.EntityState;

public class Player extends Entity implements Observer {

    private final int MAXHEALTHPOINTS = 3;
    private int hp;

    private KeyHandler khdl;
    int action;
    float landingY = pos.getY();
    private ArrayList<Shot> shots = new ArrayList<>();
    private float previousY;
    private float previousX;
    private float initialX = 32;
    private float initialY = ((GameFrame.HEIGHT) - 130);
    DecimalFormat df = new DecimalFormat();
    private float gravity = -0.5f;
    private float y0 = initialY;
    private float h = 100;
    private float dist = 150;
    private boolean falling = false;
    private boolean invincible = false;
    private float initialSpeed = 0.3f;
    private float initialAcc = 0.00003f;
    private float invStartTime;
    private boolean visible = true;

    public Player(EntitySprite sprite, Position origin, int size, KeyHandler khdl) {
        super(sprite, origin, size, EntityState.RUN);
        this.hp = MAXHEALTHPOINTS;
        this.bounds = new AABB(pos, 16, 32, 40, 32);
        this.khdl = khdl;
        previousY = (int) pos.getY();
        previousX = (int) pos.getX();
        df.setMaximumFractionDigits(2);
        this.acc = initialAcc;
        this.vx = initialSpeed;
    }

    public void move() {
        //PLAYER HORIZONTAL MOTION
        if (timex == 0) {
            vx = initialSpeed;
        }
        dx = (float) ((0.5 * acc * Math.pow(timex, 2) + vx * timex));
        if (tc.collisionTile(dx - previousX, 0)) {
            dx = previousX;
        } else {
            if (state != EntityState.DEAD) {
                timex += 1f;
            }
            previousX = dx;
        }

        //PLAYER VERTICAL MOTION
        if (state == EntityState.JUMP) {
            if (timey == 0) {
                float vx0 = vx + acc * (timex);
                if (!falling) {
                    vy = -(float) ((4 * h * vx0) / dist);
                }
                gravity = -(float) ((h * 8 * Math.pow(vx0, 2)) / Math.pow(dist, 2));
            }
            if (ani.playingLastFrame()) {
                ani.setDelay(-1);
            }
        } else {
            vy = 0;
            gravity = -0.01f;
        }

        dy = (float) ((-0.5 * gravity * Math.pow(timey, 2) + vy * timey) + y0);
        if (tc.collisionTileDown(0, dy - previousY)) {
            dy = previousY;
            falling = false;
            timey = 0;
            y0 = previousY;
            if (state != EntityState.ATTACK && state != EntityState.DEAD) {
                state = EntityState.RUN;
            }
        } else if (tc.collisionTileUp(0, dy - previousY)) {
            dy = previousY;
            y0 = previousY;
            falling = true;
            vy = 0;
        } else {
            timey += 1f;
            previousY = dy;
        }

        if (state == EntityState.ATTACK) {
            if (ani.playingLastFrame()) {
                attack();
                state = EntityState.RUN;
            }
        }

        if (state == EntityState.DEAD) {
            isDead();
        }

    }

    public void isDead() {
        if (ani.playingLastFrame()) {
            restartPlayer();
        }
    }

    public void hitted() {
        invincible = true;
        visible = false;
        invStartTime = System.nanoTime();
        if (--hp == 0) {
            state = EntityState.DEAD;
        }
    }

    private void attack() {
        shots.add(new Shot(new EntitySprite("Entity/shot", 32, 32), new Position(dx - 15, pos.getY() + 24), 48, vx + acc * (timex)));
    }

    private void restartPlayer() {
        System.err.println("restart");
        state = EntityState.RUN;
        timex = 0;
        timey = 0;
        vx = initialSpeed;
        acc = initialAcc;
        y0 = initialY;
        invincible = false;
        this.hp = 3;
        pos.setPos(0 + 32, 0 + (GameFrame.HEIGHT) - 130);
        GamePanel.getMapPos().setPos(0, 0);

    }

    public ArrayList<Shot> getShots() {
        return shots;
    }

    public void deleteShot(Shot s) {
        shots.remove(s);
    }

    @Override
    public void updateGame() {
        if (invincible) {
            if (System.nanoTime() % 9000 < 100 || System.nanoTime() % 9000 > 100) {
                visible = !visible;
            }
            if (System.nanoTime() - invStartTime >= GamePanel.unitTime) {
                invincible = false;
                visible = true;
            }
        }
        move();
        super.updateGame(state);
        pos.setX(dx);    //update x position
        if (GamePanel.getMapPos().getX() + GameFrame.WIDTH < TileFacade.mapWidth * 16) {
            GamePanel.getMapPos().setX(dx);
        }
        pos.setY(dy);
        if (!shots.isEmpty()) {
            for (int i = 0; i < shots.size(); i++) {
                System.err.println((shots.get(i).pos.getWorldVar().getX() - pos.getWorldVar().getX()));
                if (shots.get(i).pos.getWorldVar().getX() - pos.getWorldVar().getX() > GameFrame.WIDTH
                        || shots.get(i).collides()) {
                    System.err.println("ELIMINO COLPO");
                    deleteShot(shots.get(i));
                } else {
                    shots.get(i).updateGame();
                }
            }
        }
        if (pos.getY() > GameFrame.HEIGHT) {
            restartPlayer();

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
