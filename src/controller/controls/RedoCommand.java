package controller.controls;

import controller.Command;
import controller.CommandHistory;

public class RedoCommand implements Command{
    @Override
    public void run() {
        CommandHistory.redo();
        System.out.println("DOUBLE OOPSIES. Redoing...");
    }
}
