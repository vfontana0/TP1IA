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

public class GUI extends GameApplication {
    private Entity player;
    private int objectiveX;
    private int objectiveY;
    private Text textVida;
    private Text textAccion;
    private Text textoEspacio;
    
    private ArrayList<Pair<Action, Double>> acciones;
    
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
        FXGL.onKey(KeyCode.SPACE, () -> {
        	textoEspacio.setVisible(false);
            try {
                sleep(150);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Thread t = new Thread() {
            	@Override
            	public void run() {
            		acciones.stream().forEach(action -> {
            			textAccion.setText("");
                		textVida.setText("Energia: " + String.valueOf(action.getValue()));
                    	if (action.getKey() instanceof IrANodoN) {;
                    		System.out.println("accion detectada");
            		        Integer numero = ((IrANodoN) action.getKey()).getNumero();
            		        Pair<Integer, Integer> parNodoN = posiciones.getNodoN(numero);
            		        System.out.println("Entrando a accion de nodo: " + numero);
        					try {
								translateSmooth(parNodoN.getKey(), parNodoN.getValue());
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
        					
                    	}
                    	else if (action.getKey() instanceof ElegirPelear) {
                			textAccion.setText("¡El agente eligió pelear!");
                    	}
                    	else if (action.getKey() instanceof ElegirUsarRayoAurora) {
                    		textAccion.setText("¡El agente eligió usar el Rayo Aurora!");
                    	}
                    	else if (action.getKey() instanceof ElegirUsarRayoMeteorico) {
                    		textAccion.setText("¡El agente eligió usar el Rayo Meteórico!");
                    	}
                    	else if (action.getKey() instanceof ElegirUsarRayoSolar) {
                    		textAccion.setText("¡El agente eligió usar el Rayo Solar!");
                    	}
                    	else if (action.getKey() instanceof ElegirHuir) {
                    		textAccion.setText("¡El agente eligió huir!");
                    	}
                    	else if (action.getKey() instanceof ElegirUsarSatelite) {
                    		textAccion.setText("¡El agente eligió usar el satélite!");
                    	}
                    	else if (action.getKey() instanceof JuntarPokebola) {
                    		textAccion.setText("¡El agente eligió juntar una pokebola!");
                    	}
						try {
							sleep(3500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                    	
                    });
            	}
            };
            
            t.start();
            
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
    	//TODO: cambiar a barra de energia
        textVida = getUIFactoryService().newText("Energia: ", Color.BLACK, 22);
    	textVida.setTranslateX(getAppWidth() - 200);
        textVida.setTranslateY(30);
        
        textAccion = getUIFactoryService().newText("", Color.BLACK, 22);
        textAccion.setTranslateX(30);
        textAccion.setTranslateY(30);
        
        textoEspacio = getUIFactoryService().newText("Presione espacio para comenzar", Color.BLACK, 22);
        textoEspacio.setTranslateX(300);
        textoEspacio.setTranslateY(getAppHeight() - 50);
        
        getGameScene().addUINodes(textVida, textAccion, textoEspacio);

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