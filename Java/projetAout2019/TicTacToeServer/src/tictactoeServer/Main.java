/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoeServer;

import java.io.IOException;
import tictactoeServer.model.server.Server;
import tictactoeServer.view.ServerView;

/**
 *
 * @author selim
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        
       Server server = new Server();
       ServerView view = new ServerView(server);
       server.addObserver(view);
       view.start();

    }

}
