package tictactoeClient;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Profile;
import tictactoeClient.model.ClientNio;
import tictactoeClient.view.ClientAppNio;
import tictactoeClient.view.GameNio;
import tictactoeClient.view.LobbyNio;

/**
 *
 * @author selim
 */
public class Main /*extends Application */{

    public static void main(String[] args) {
        Application.launch(ClientAppNio.class);
        //launch(args);
        
    }
/*
    @Override
    public void start(Stage primaryStage) throws Exception {
        final ClientAppNio app = new ClientAppNio();
        final ClientNio client = new ClientNio("localhost", 12345);
        final Profile prof = new Profile(12, "sleim", 1, 1, 1);
        final LobbyNio lobby = new LobbyNio(app, client, prof);
        
       GameNio game = new GameNio(app, client, lobby, prof);
       primaryStage.setScene(new Scene(game));
       primaryStage.show();
    }
*/
    

}
