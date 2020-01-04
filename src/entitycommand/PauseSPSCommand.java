/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitycommand;

/**
 *
 * @author Star Trekking
 */
public class PauseSPSCommand extends SPSCommand{

    @Override
    public void execute() {
        this.sps.nextState(sps.getGSM().getPs());
    }
    
}
