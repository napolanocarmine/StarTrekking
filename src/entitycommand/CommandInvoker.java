/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitycommand;

/**
 *
 * @author Star Trekking
 *
 * Class to use the commend pattern invoking the execute method of the commend
 */
public class CommandInvoker {

    private Command command;

    /**
     * Command setter
     *
     * @param command command to be set
     */
    public void setCommand(Command command) {
        //System.out.println("Set new Command");
        this.command = command;
        executeCommand();
    }

    /**
     * Command getter
     *
     * @return the current command.
     */
    public Command getCommand() {
        return this.command;
    }

    /**
     * Execute the current set command.
     */
    public void executeCommand() {
        //System.out.println("Command Execution");
        this.command.execute();
    }
}
