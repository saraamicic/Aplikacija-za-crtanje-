package command;

public class UndoCmd implements Command {

	//imamo undo stack (koristimo stek jer nam treba lifo princip
	//poslednja komanda koja je izvrsena ce se undoovati
	//execute ce biti da se obrise poslednja komanda a unexecute je zapravo redo
	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub

	}

}
