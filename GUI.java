import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.shape.*;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GUI extends Application {

    static GridPane root;

    // Aumentei o tamanho
    static int SIZE = 12;
    static int length = SIZE;
    static int width = SIZE;

    public static  int rowIndex = 0;
    public static  int columnIndex = 0;

	private static Car car = new Car();	

    @Override
    public void start(Stage primaryStage) {
        root = new GridPane();
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setHgap(2);
        root.setVgap(2);
        root.getStyleClass().addAll("game-root");

        addChildrens(rowIndex, columnIndex, root);;

        Scene scene = new Scene(root, 550, 550);
        scene.getStylesheets().add("game.css");

        // Adicionei essas duas linhas
        root.getChildren().get(getButton(6,6)).getStyleClass().add("game-button-agent");
        root.getChildren().get(getButton(car.getX(),car.getY())).getStyleClass().add("game-button-car");
		
        primaryStage.setTitle("City");
        primaryStage.setScene(scene);
        primaryStage.show();
		
		new Thread() {
         
			@Override
			public void run() {
				while (true) {
					
					if(City.changeToLeft) {
						moveAgent(0);
						City.changeToLeft = false;
						System.out.println("moveu");
					}
					if(City.changeToRight) {
						System.out.println("kk eae man");
						moveAgent(1);
						City.changeToRight = false;
					}
					if(City.moveCar) {
						moveCar();
						City.moveCar = false;
					}
					
					// Percepts
					
					if(car.getX() == 4 || car.getY() == 7) {
						City.carLeft = true;
					}
					if(car.getX() == 8 || car.getY() == 6) {
						City.carRight = true;
					}
					
					try { Thread.sleep (50); } catch (InterruptedException ex) {}
				}
			}
		}.start();
		
		
	}

                                                                                                   
    // Método inteiro mudado
    private static void addChildrens(int rowIndex, int columnIndex, GridPane root) {


        for(int y = 0; y < length; y++){

            for(int x = 0; x < width; x++) {

                Button button = new Button();

                button.setPrefHeight(50);
                button.setPrefWidth(50);
                button.setAlignment(Pos.CENTER);

                // Set all as default
                button.setText("");
                button.getStyleClass().addAll("game-button");

                // Horizontal pedestrians
                if(x < 4 || x > 7) {
                    if(y == 1 || y == 4 || y == 7 || y == 10)
                        button.getStyleClass().add("game-button-pedestrian");
                }

                // Vertical pedestrians0

                if(x == 4 || x == 7) {
                    if(y != 2 && y != 3 && y != 8 && y != 9)
                        button.getStyleClass().add("game-button-pedestrian");
                }


                // Vertical streets
                if(x==5 || x==6)
                    button.getStyleClass().addAll("game-button-road");

                // Horizontal streets
                if(y == 2 || y == 3 || y == 8 || y == 9)
                    button.getStyleClass().addAll("game-button-road");





                root.setRowIndex(button,y);
                root.setColumnIndex(button,x);
                root.getChildren().add(button);
            }
        }

    }

    public  static  void startEnvironment(){
		launch();
		
    }
    
    // Pegar botão por coordenada
    public int getButton(int x, int y) {
        return x * SIZE + y;
    }
	
	// Mudar posição do agente
	public void moveAgent(int direction) {
		// 0 é para a esquerda
		if(direction == 0){
			root.getChildren().get(getButton(6,6)).getStyleClass().remove("game-button-agent");
			root.getChildren().get(getButton(6,5)).getStyleClass().add("game-button-agent");
		}
		else {
			root.getChildren().get(getButton(6,5)).getStyleClass().remove("game-button-agent");
			root.getChildren().get(getButton(6,6)).getStyleClass().add("game-button-agent");
		}
	}
	
	
	// Mover carro
	public void moveCar() {
		root.getChildren().get(getButton(car.getX(),car.getY())).getStyleClass().remove("game-button-car");
		car.move();
		root.getChildren().get(getButton(car.getX(),car.getY())).getStyleClass().add("game-button-car");
	}


}
