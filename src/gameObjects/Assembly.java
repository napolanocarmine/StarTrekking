package gameObjects;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class Assembly implements GameObject{

    protected  ArrayList<GameObject> objs;
    
    public Assembly(){
        objs = new ArrayList<GameObject>();
    }
    
    @Override
    public void updateGame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(Graphics2D g) {
        for(GameObject obj: objs){
            obj.render(g);
        }
    }
    
    public void addObj(GameObject obj){
        objs.add(obj);
    }
    
    public void removeObj(GameObject obj){
        objs.remove(obj);
    }
    
    public ArrayList<GameObject> getObjs(){
        return objs;
    }
             
    
    
    
}
