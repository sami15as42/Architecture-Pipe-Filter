package project;

import javafx.application.Application;

public class Main {
	public static void main(String[] args) {

		Pipe p1 = new BlockingQueue();
		Pipe p2 = new BlockingQueue();
		Pipe p3 = new BlockingQueue();

		QueryProcessor qp = new QueryProcessor(p1, p2);
		TransactionProcessor tp = new TransactionProcessor(p2, p3);

		Thread th2 = new Thread(qp);
		Thread th3 = new Thread(tp);
		GUI gui = new GUI();
		gui.setP1(p3);
		gui.setP2(p1);

		new Thread() {
			@Override
			public void run() {
				Application.launch(GUI.class, args);
			}
		}.start();

		th2.start();
		th3.start();
	}
}