import java.net.URL;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
 
public class JavaFxBrowser extends Application {
 
private Scene scene;
MyBrowser myBrowser;
 
public static void main(String[] args) throws Exception {
	
	FlotClusterHttpServer server = new FlotClusterHttpServer();
    launch(args);
}
 
@Override
public void start(Stage primaryStage) {
    primaryStage.setTitle("Kolibri");
    primaryStage.getIcons().add(new Image("http://iconverticons.com/img/logoo.png"));
    
    myBrowser = new MyBrowser();
    scene = new Scene(myBrowser, 640, 480);
 
    primaryStage.setScene(scene);
    primaryStage.show();
}
 
class MyBrowser extends Region{
 
    //final String hellohtml = "hello.html";
 
    WebView webView = new WebView();
    WebEngine webEngine = webView.getEngine();
         
    public MyBrowser(){
 
//        URL urlHello = getClass().getResource("hello.html");
//      webEngine.load(urlHello.toExternalForm());
       // webEngine.load("http://localhost:8181/");
        webEngine.load("http://localhost:8181");
     
        
        
        getChildren().add(webView);
    }
}
}