import java.util.Random;
public class Car {
	
	// As ruas são definidas em sentido horário, começando na superior
	// esquerda, cada uma representada por um número de 0 a 5.
	
	private Coordinates location;
	private int origin;
	private int destination;
	private int moveCounter;
	private boolean moveControl;
	
	private Random generator;
	
	public Car() {
		generator = new Random();
		randomSpawn();
		moveCounter = 0;
		moveControl = false;
	}
	
	public void randomSpawn() {
		this.origin = generator.nextInt(6);
		if(origin > 2)
			this.destination = generator.nextInt(3);
		else
			this.destination = generator.nextInt(3) + 3;
		// Debug
		// destination = 2;
		// origin = 4;
		switch(origin) {
			case 0:
				location = new Coordinates(2, 0);
				break;
				
			case 1:
				location = new Coordinates(0, 6);
				break;
				
			case 2:
				location = new Coordinates(3, 11);
				break;
				
			case 3:
				location = new Coordinates(9, 11);
				break;
				
			case 4:
				location = new Coordinates(11, 5);
				break;
				
			case 5:
				location = new Coordinates(8, 0);
				break;
		}
	}
	
	public void move() {
		/// Carro que spawna na rua de cima na esquerda
		if(origin == 0) {
			if(moveCounter < 6) {
				this.addY();
				moveCounter++;
			}
			else{
				moveControl = true;
			}
		}
		
		/// Carro que spawna na rua central de cima
		if(origin == 1) {
			moveControl = true;
		}

		/// Carro que spawna na rua de cima na direita		
		if(origin == 2) {
			if(moveCounter < 5) {
				this.subY();
				moveCounter++;
			}
			else{
				moveControl = true;
			}
		}
		
		/// Carro que spawna na rua de baixo na direita
		if(origin == 3) {
			if(moveCounter < 6) {
				this.subY();
				moveCounter++;
			}
			else {
				moveControl = true;
			}
			
		}
		
		/// Carro que spawna na rua central de baixo
		if(origin == 4) {
			moveControl = true;
		}
		
		/// Carro que spawna na rua de baixo na esquerda
		if(origin == 5) {
			if(moveCounter < 5) {
				this.addY();
				moveCounter++;
			}
			else{
				moveControl = true;
			}
			
		}
		
		if(moveControl){
			switch(destination) {
				case 0:
					if(this.getX() != 3) {
						this.subX();
					}
					else if(this.getY() > 0) {
						this.subY();
					}
					else {
						moveCounter = 0;
						moveControl = false;          
						this.randomSpawn();
					}
					break;
					
				case 1:
					if(this.getX() > 0) {
						this.subX();
					}
					else{
						this.randomSpawn();
						moveCounter = 0;
						moveControl = false;
					}
					break;
					
				case 2:
					if(this.getX() != 2) {
						this.subX();
					}
					else if(this.getY() < 11) {
						this.addY();
					}
					else {
						this.randomSpawn();
						moveCounter = 0;
						moveControl = false;
					}
					break;
					
				case 3:
					if(this.getX() != 8) {
						this.addX();
					} 
					else if(this.getY() < 11) {
						this.addY();
					}
					else {
						this.randomSpawn();
						moveCounter = 0;
						moveControl = false;
					}
					break;
					
				case 4:
					if(this.getX() < 11) {
						this.addX();
					}
					else{
						this.randomSpawn();
						moveCounter = 0;
						moveControl = false;
					}
					break;
				case 5:
					if(this.getX() != 9) {
						this.addX();
					} 
					else if(this.getY() > 0) {
						this.subY();
					}
					else {
						this.randomSpawn();
						moveCounter = 0;
						moveControl = false;
					}
					break;
				}
			}
		
		
	}
	
	public void setLocation(int x, int y){
		this.location.setX(x);
		this.location.setY(y);
	}
	
	public int getX() {
		return this.location.getX();
	}
	public int getY() {
		return this.location.getY();
	}
	
	public void addX() {
		this.location.setX(this.location.getX() + 1);
	}
	
	public void addY() {
		this.location.setY(this.location.getY() + 1);
	}
	
	public void subX() {
		this.location.setX(this.location.getX() - 1);
	}
	
	public void subY() {
		this.location.setY(this.location.getY() - 1);
	}
}
