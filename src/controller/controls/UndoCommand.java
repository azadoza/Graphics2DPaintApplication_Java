package controller.controls;

import controller.Command;
import controller.CommandHistory;

public class UndoCommand implements Command{
    @Override
    public void run() {
        CommandHistory.undo();
        System.out.println("OOPSIES. Undoing...");
    }
}
