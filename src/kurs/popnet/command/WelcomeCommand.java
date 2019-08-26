package kurs.popnet.command;

public class WelcomeCommand extends Command {

	public WelcomeCommand(String jsp) {
		super(jsp);
	}
	
	@Override
	public String execute() throws Exception {
	
		return nextPage;
	}

}
