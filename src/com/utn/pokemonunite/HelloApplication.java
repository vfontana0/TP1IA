package com.utn.pokemonunite;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import javafx.animation.*;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;
import static java.lang.Math.abs;
import static java.lang.Thread.sleep;
import pokemon.search.*;
import frsf.cidisi.faia.agent.Action;
import pokemon.search.actions.*;

import com.almasb.fxgl.app.scene.LoadingScene;

public class HelloApplication extends GameApplication {
    private Entity player;
    private int objectiveX;
    private int objectiveY;
    
    private ArrayList<Action> acciones;
    
    private void translateSmooth(Integer x, Integer y) throws InterruptedException {
        objectiveX = x;
        objectiveY = y;
        int velocityX, velocityY;
        if (x < player.getX()) velocityX = -1;
        else velocityX = 1;
        if (y < player.getY()) velocityY = -1;
        else velocityY = 1;

        player.setProperty("velocity", new Point2D(velocityX, velocityY));
    }

    @Override
    protected void initInput() {
        Posiciones posiciones = new Posiciones();
        boolean loPresiono = false;
        FXGL.onKey(KeyCode.L, () -> {
            try {
                sleep(150);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Thread t = new Thread() {
            	@Override
            	public void run() {
            		acciones.stream().forEach(action -> {
                    	if (action instanceof IrANodoN) {
                    		System.out.println("accion detectada");
            		        Integer numero = ((IrANodoN) action).getNumero();
            		        Pair<Integer, Integer> parNodoN = posiciones.getNodoN(numero);
            		        System.out.println("Entrando a accion de nodo: " + numero);
        					try {
        						translateSmooth(parNodoN.getKey(), parNodoN.getValue());
        						sleep(5000);
        					} catch (InterruptedException e) {
        						// TODO Auto-generated catch block
        						e.printStackTrace();
        					}
                    	}
                    });
            	}
            };
            
            t.start();
            
        });
        FXGL.onKey(KeyCode.D, () -> {
            player.translateX(5); // move right 5 pixels
        });
        FXGL.onKey(KeyCode.A, () -> {
            player.translateX(-5); // move left 5 pixels
        });
        FXGL.onKey(KeyCode.W, () -> {
            player.translateY(-5); // move up 5 pixels
        });
        FXGL.onKey(KeyCode.S, () -> {
            player.translateY(5); // move down 5 pixels
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(1024);
        settings.setHeight(768);
        settings.setTitle("Pokemon Unite");
        settings.setVersion("0.1");
        
        settings.setSceneFactory(new SceneFactory() {
        	@Override
        		public LoadingScene newLoadingScene() {
        			return new PantallaCarga();
        	}
        });
    }
    
    @Override
    protected void initUI() {
        BackgroundImage backgroundImage;
        try {
            Image image = new Image(getClass().getResource("worldmap.png").openStream());
            backgroundImage = new BackgroundImage(
                    image,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    new BackgroundSize(1024, 768, false, false, true, false));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        getGameScene().getRoot().setBackground(new Background(backgroundImage));

    }
    
    @Override
    protected void initGame() {
    	getGameScene().addUINode(new Text("Procesando algoritmo de busqueda..."));
        player = FXGL.entityBuilder()
                .at(998, 665)
                .view(getClass().getResource("trainer.png"))
                .buildAndAttach();
        player.setProperty("velocity", new Point2D(0,0));
        
        //Ejecuto el algoritmo
        
        PokemonMain pokemonMain = new PokemonMain();
        pokemonMain.startPokemon();
        acciones = pokemonMain.getAccionesEjecutadas();
        
        
    }
    @Override
    protected void onUpdate(double tpf) {
        Point2D velocity = (Point2D) player.getObject("velocity");
        player.translate(velocity);
        if (abs(player.getX() - objectiveX) < 3) {
            if (velocity.getX() != 0) player.setProperty("velocity", (new Point2D(0, velocity.getY())));
        }
        if (abs(player.getY() - objectiveY) < 3) {
            if (velocity.getY() != 0) player.setProperty("velocity", (new Point2D(velocity.getX(),0)));
        }
    }
    
}