package controller;
import java.io.IOException;


// Encapsulate actions to run jpaint
public interface ICommand {
	void run() throws IOException;
}
