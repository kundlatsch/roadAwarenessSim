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
	public static Coordinates carPosition = new Coordinates(0,0);
	public static Coordinates agentPosition = new Coordinates(6,6);
	
	// Range de visão do agente
	private static int range = 3;
	
	public static final Literal    cl = Literal.parseLiteral("car(left)");
    public static final Literal    cr = Literal.parseLiteral("car(right)");
	
    private Logger logger = Logger.getLogger("roadAwareness.mas2j" + City.class.getName());
	
	private boolean firstExec = true;
	private Parser perceptions;
	
    private GUI gui;
    @Override
    public void init(String[] args) {
		// A interface roda em uma thread separada para a arquitetura
		// do Jason rodar em background
		new Thread() {
        
        @Override
        public void run() {
				gui.startEnvironment();
			}
		}.start();
		
		
	}
    
    @Override
    synchronized public boolean executeAction(String ag, Structure action) {
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
          Thread.sleep(750);
        } catch (Exception e) {}
        return true;
    }

    private void updateAllPercepts() {
		clearPercepts();
	   if(inRage()){
		   	System.out.println("inRage worked");
		   	System.out.println("carLeft: " + carLeft + " carRight: " + carRight);
		   	if(carPosition.getY() == 5) {
				addPercept(cr);
			}
			else if(carPosition.getY() == 6) {
				addPercept(cl);
			}   	
	   }
    }
	
	private boolean inRage()  {
		if(((carPosition.getX() >= agentPosition.getX() - range) || (carPosition.getX() <= agentPosition.getX() + range)) && (carPosition.getY() == 5 || carPosition.getY() == 6))
			return true;
		return false;
	}
    
}
