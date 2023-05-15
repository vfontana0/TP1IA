package com.utn.pokemonunite;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.audio.Music;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.utn.pokemonunite.PantallaCarga;
import com.utn.pokemonunite.Posiciones;
import com.almasb.fxgl.scene.Scene;
import domain.Nodo;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Pair;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import static java.lang.Math.abs;
import static java.lang.Thread.sleep;
import pokemon.search.*;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.state.datastructure.Graph;
import pokemon.search.actions.*;

import com.almasb.fxgl.app.scene.LoadingScene;

public class GUI extends GameApplication {
    private Entity player;
    private int objectiveX;
    private int objectiveY;
    private Text textVida;
    private Text textAccion;
    private Text textNivel;
    private Music musica;
    private Text textoEspacio;
    private ArrayList<Entity> enemigos;
	private int nroGrafo = 0;
	private int nroNodo;
    private ArrayList<Pair<Action, Double>> acciones;
    private ArrayList<Point2D> posicionesA;
    private ArrayList<Pair<Entity, Integer>> utnBalls;
    private Boolean gano;
    
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
            		int nroAccion[] = {0};
            		acciones.stream().forEach(action -> {
                		final int[] nroPokemon = {0};
                		
                		if (nroAccion[0] != Datos.niveles.size()) textNivel.setText("Nivel: " + Datos.niveles.get(nroAccion[0]));
                		nroAccion[0]++;
        				if (nroGrafo != Datos.grafo.size()) Datos.grafo.get(nroGrafo).getAllVertices().stream().forEach(thisNodo -> {
            				if (thisNodo.getTienePokemon()) {
            					posicionesA.set(nroPokemon[0],  new Point2D(posiciones.getNodoN(thisNodo.getNumero()).getKey() + 30, posiciones.getNodoN(thisNodo.getNumero()).getValue()));
            					enemigos.get(nroPokemon[0]).setPosition(posicionesA.get(nroPokemon[0]));
            					nroPokemon[0]++;
            				}
            			});
            			nroGrafo++;
            			textAccion.setText("");
                		textVida.setText("Energia: " + String.format("%.2f", action.getValue()));

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
        					nroNodo = numero;
        					
        					
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
                    		textAccion.setText("¡El agente eligió juntar una UTN Ball!");
                    		for (Pair<Entity, Integer> thisBall: utnBalls) {
                    			if (thisBall.getValue().equals(nroNodo)) {
                    				thisBall.getKey().setPosition(-100, -100);
                    			}
                    		}
                    	}

                    	try {
							sleep(3500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                    	
                    });
            		if(gano) {
            			FXGL.getAudioPlayer().stopMusic(musica);
            			FXGL.getAudioPlayer().loopMusic(getAssetLoader().loadMusic(getClass().getResource("allamo.wav")));
            	        
            			textNivel.setVisible(false);
            			textVida.setVisible(false);
            			textAccion.setVisible(false);
            			BackgroundImage backgroundImage;
            			player.setVisible(false);
            			for (Pair<Entity, Integer> thisUtnBall : utnBalls) {
            				thisUtnBall.getKey().setVisible(false);
            			}
            			for (Entity thisEnemigo : enemigos) {
            				thisEnemigo.setVisible(false);
            			}
            	        try {
            	            Image image = new Image(getClass().getResource("gano.png").openStream());
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
            		} else {
            			FXGL.getAudioPlayer().stopMusic(musica);
            			FXGL.getAudioPlayer().loopMusic(getAssetLoader().loadMusic(getClass().getResource("continue.wav")));

            			textNivel.setVisible(false);
            			textVida.setVisible(false);
            			textAccion.setVisible(false);
            			BackgroundImage backgroundImage;
            			player.setVisible(false);
            			for (Pair<Entity, Integer> thisUtnBall : utnBalls) {
            				thisUtnBall.getKey().setVisible(false);
            			}
            			for (Entity thisEnemigo : enemigos) {
            				thisEnemigo.setVisible(false);
            			}
            	        try {
            	            Image image = new Image(getClass().getResource("perdio.png").openStream());
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
        settings.setMainMenuEnabled(true);
        settings.setSceneFactory(new PokemonSceneFactory());
        
    }
    
    @Override
    protected void initUI() {
    	musica = getAssetLoader().loadMusic(getClass().getResource("mountaintrails.mp3"));
        FXGL.getAudioPlayer().loopMusic(musica);
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
        
        textNivel = getUIFactoryService().newText("Nivel: 0", Color.BLACK, 22);
        textNivel.setTranslateX(500);
        textNivel.setTranslateY(30);
        
        textoEspacio = getUIFactoryService().newText("Presione espacio para comenzar", Color.BLACK, 22);
        textoEspacio.setTranslateX(350);
        textoEspacio.setTranslateY(getAppHeight() - 50);
        
        getGameScene().addUINodes(textVida, textAccion, textNivel, textoEspacio);
        

    }
    
    @Override
    protected void initGame() {
    	getGameScene().addUINode(new Text("Buscando solucion..."));
        //Ejecuto el algoritmo
        

    	nroGrafo = 0;
        Datos.niveles = new ArrayList<>();
        Datos.grafo = new ArrayList<>();
        Datos.energiaJugador = 0.0;
        Datos.energiaMaestro = 0.0;
        PokemonMain pokemonMain = new PokemonMain();
        pokemonMain.startPokemon();
       	Posiciones posiciones = new Posiciones();
    	Pair<Integer, Integer> posicionInicio = posiciones.getNodoN(Datos.nodoInicio);

    	//Creo el player
    	
    	enemigos = new ArrayList<>();

    	for (int i = 0; i < 12; i++) {
    		enemigos.add(FXGL.entityBuilder()
    						.at(-100, -100)
    						.view(getClass().getResource("pokemon.png"))
    						.buildAndAttach());
    	}
    	
        player = FXGL.entityBuilder()
                .at(posicionInicio.getKey(), posicionInicio.getValue())
                .view(getClass().getResource("trainer.png"))
                .buildAndAttach();
        player.setProperty("velocity", new Point2D(0,0));
        nroNodo = Datos.nodoInicio	;
        acciones = pokemonMain.getAccionesEjecutadas();
        System.out.println("Cantidad de acciones: " + acciones.size());
        System.out.println("Cantidad de grafos: " + Datos.grafo.size());
        
        int m = 0;
        for (datastructures.Graph thisGrafo : Datos.grafo) {
            System.out.println("Grafo: " + m);
        	thisGrafo.getAllVertices().stream().forEach(vertice -> {
        		if (vertice.getTienePokemon()) System.out.println("pokemon en vertice " + vertice.getNumero() + " " + vertice.getPokemon());
        	});   
        	m++;
        }
        
        System.out.println("Cantidad acciones" + acciones.size());
        gano = pokemonMain.getGano();
        
        posicionesA = new ArrayList<>();
        
        for (int i = 0; i < 12; i++) {
        	posicionesA.add(new Point2D(-100, -100));
        }
        
        // Agrego utn balls
		utnBalls = new ArrayList<>();
		Datos.grafo.get(nroGrafo).getAllVertices().stream().forEach(thisNodo -> {
			if (thisNodo.getTienePokebola()) {
				utnBalls.add(new Pair<>(FXGL.entityBuilder()
					.at(posiciones.getNodoN(thisNodo.getNumero()).getKey() - 10, posiciones.getNodoN(thisNodo.getNumero()).getValue())
					.view(getClass().getResource("utnball.png"))
					.buildAndAttach(), thisNodo.getNumero()));
			}
		});
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