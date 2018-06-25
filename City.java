import jason.asSyntax.Literal;
import jason.asSyntax.Structure;

import java.util.LinkedList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import jason.mas2j.MAS2JProject;

public class City extends jason.environment.Environment {

	public static boolean changeToLeft = false;
	public static boolean changeToRight = false;
	public static boolean moveCar = false;
	public static boolean carRight = false;
	public static boolean carLeft = false;
	public static Coordinates carPosition;
	
	public static final Literal    cl = Literal.parseLiteral("car(left)");
    public static final Literal    cr = Literal.parseLiteral("car(right)");
	
    private Logger logger = Logger.getLogger("roadAwareness.mas2j" + City.class.getName());
	
	private boolean firstExec = true;
	private Parser perceptions;
	
    private GUI gui;
    @Override
    public void init(String[] args) {
		
		new Thread() {
         
        @Override
        public void run() {
				gui.startEnvironment();
			}
		}.start();
		
		
	}
    
    @Override
    synchronized public boolean executeAction(String ag, Structure action) {
		if(firstExec) {
			firstExec = false;
		}
		
		System.out.println(ag + ".asl executing: " + action);
		if (action.getFunctor().equals("move")) {
			changeToLeft = true;
		}
		else
			changeToRight = true;
		//gui.moveAgent(0);
		moveCar = true;
		updateAllPercepts();
		try {
          Thread.sleep(500);
        } catch (Exception e) {}
        return true;
    }

    private void updateAllPercepts() {
       clearPercepts();
       if(carLeft){
		   addPercept(cl);
		   carLeft = false;
	   }
	   else if(carRight) {
		   addPercept(cr);
		   carRight = false;
	   }
    }
    
}
