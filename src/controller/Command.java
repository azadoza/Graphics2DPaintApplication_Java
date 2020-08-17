package controller;
import java.io.IOException;


// Encapsulate actions to run jpaint
public interface Command {
	void run() throws IOException;
}
