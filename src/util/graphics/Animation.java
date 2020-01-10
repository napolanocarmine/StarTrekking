package util.graphics;

import java.awt.image.BufferedImage;

/**
 * 
 * @author Star Trekking
 * 
 * Class which manage the set of the frames.
 */
public class Animation {

    private BufferedImage[] frames;
    private int currentFrame;
    private int numFrames;

    private int count;
    private int delay;

    private int timesPlayed;

    /**
     *
     * Constructor of an Animation object.
     * @param frames frames related with an animation.
     */
    public Animation(BufferedImage[] frames) {
        timesPlayed = 0;
        this.frames = frames;
        currentFrame = 0;
        count = 0;
        timesPlayed = 0;
        numFrames = frames.length;
        delay = 80;
    }
    
    /**
     * Constructor of an Animation object.
     */
    public Animation(){
        timesPlayed = 0;
    }

    /**
     * Setter of the frames.
     * @param frames frames related with an animation.
     */
    public void setFrames(BufferedImage[] frames) {
        this.frames = frames;
        currentFrame = 0;
        count = 0;
        timesPlayed = 0;
        numFrames = frames.length;
    }

    /**
     * Set the delay.
     * @param i ticks between a frame and the following one.
     */
    public void setDelay(int i) {
        delay = i;
    }

    /**
     * Set current frame.
     * @param i index of the current frame.
     */
    public void setFrame(int i) {
        currentFrame = i;
    }

    /**
     * Set the number of the frames.
     * @param i number of the frames.
     */
    public void setNumFrames(int i) {
        numFrames = i;
    }

    /**
     * Get the delay.
     * @return delay.
     */
    public int getDelay() {
        return delay;
    }

    /**
     * Get the index of the current frame.
     * @return index of the current frame.
     */
    public int getFrame() {
        return currentFrame;
    }

    /**
     * Get the value of the tick counter.
     * @return the value of the tick counter.
     */
    public int getCount() {
        return count;
    }
    
    /**
     * set the value of the tick counter.
     * @param count value of the tick counter.
     */
    public void setCount(int count) {
        this.count = count;
    }
    
    /**
     * Get the current frame.
     * @return current frame.
     */
    public BufferedImage getImage() {
        return frames[currentFrame];
    }
    
    /**
     * Update the frame of the animation.
     */
    public void updateGame() {
        if (delay == -1) {  // if the delay is setted at -1 the animation is stopped.
            return;
        }

        count++;  // updating the tick counter

        if (count == delay) {  // if the counter value is equal to the setted delay, update the frame and set the counter at 0
            currentFrame++;
            count = 0;
        }
        
        // if the current frame is the last one, set the next one at 0 and increase 
        //  the counter of the number of times the animation has been played
        if (currentFrame == numFrames) {  
            currentFrame = 0;
            timesPlayed++;
        }
    }
    
    /**
     * return true if the animation has already been played one time.
     * @return true if the animation has already been played one time, return false otherwise.
     */
    public boolean hasPlayedOnce() {
        return timesPlayed > 0;
    }

    /**
     * return true if the animation is playing the last frame.
     * @return true if the animation is playing the last frame, return false otherwise.
     */
    public boolean playingLastFrame() {
        return currentFrame == (numFrames - 1);
    }

    /**
     * return the number of times all the frame shave been played.
     * @return return the number of times all the frame shave been played.
     */
    public boolean hasPlayed(int i) {
        return timesPlayed == i;
    }

}
