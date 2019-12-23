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
public class CommandInvoker {
    private Command command;
    
    /**
     * Command setter
     * @param command command to be setted 
     */
    public void setCommand(Command command){
        System.out.println("Set new Command");
        this.command = command;
        executeCommand();
    }
    
    /**
     * Command getter
     * @return the current command.
     */
    public Command getCommand(){
        return this.command;
    }
    
    /**
     * Execute the current setted command.
     */
    public void executeCommand(){
        System.out.println("Command Execution");
        this.command.execute();
    }
}
