package pacman;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage theStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("pacman.fxml"));
        theStage.setTitle( "Pacman" );

        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );

        Canvas canvas = new Canvas( 1225, 600 );
        root.getChildren().add( canvas );
        GameManager gameManager = new GameManager();

        gameManager.drawBoard(root);

        theScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> gameManager.movePacman(event));
        theScene.addEventHandler(KeyEvent.KEY_RELEASED, event -> gameManager.stopPacman(event));


//        GraphicsContext gc = canvas.getGraphicsContext2D();

//        Image earth = new Image( "file:images/ghost1.png", 50, 50, true, true);
//        Image sun   = new Image( "file:images/ghost2.png" , 50, 50, true, true);
//
//        final long startNanoTime = System.nanoTime();

//        new AnimationTimer()
//        {
//            public void handle(long currentNanoTime)
//            {
//                double t = (currentNanoTime - startNanoTime) / 1000000000.0;
//
//                double x = 232 + 128 * Math.cos(t);
//                double y = 232 + 128 * Math.sin(t);
//
//                // background image clears canvas
//                gc.drawImage( earth, x, y );
//                gc.drawImage( sun, 196, 196 );
//            }
//        }.start();

        theStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}