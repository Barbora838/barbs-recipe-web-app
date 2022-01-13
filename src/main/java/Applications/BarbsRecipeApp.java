package Applications;

import Handlers.*;
import Server.HTTPServer;
import Server.ServerUtils;

public class BarbsRecipeApp {

    /**
     *   test by http://localhost:1024/login
     *          username-> barb
     *          password-> pass123
     *
     *   test by http://localhost:1024/login
     *          username-> sami
     *          password-> cs601
     */

    public static void main(String[] args) {

        ServerUtils.setLogger(false);
        int port = 1024;
        HTTPServer server = new HTTPServer(port);

        server.addMapping("/home", new HomeHandler());
        server.addMapping("/", new LandingHandler());
        server.addMapping("/files", new FileHandler());
        server.addMapping("/login", new LoginHandler());
        server.addMapping("/search", new SearchHandler());
        server.addMapping("/find", new FindHandler());
        server.addMapping("/logout", new LogoutHandler());
        server.addMapping("/commingSoon", new CommingSoonHandler());
        server.startup();
    }
}