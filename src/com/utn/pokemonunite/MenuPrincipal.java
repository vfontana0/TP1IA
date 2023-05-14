package com.utn.pokemonunite;
import static java.lang.Thread.sleep;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameScene;

import java.io.IOException;

import com.almasb.fxgl.animation.Animation;
import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.audio.Audio;
import com.almasb.fxgl.audio.AudioPlayer;
import com.almasb.fxgl.audio.Music;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import static com.almasb.fxgl.dsl.FXGLForKtKt.*;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.core.util.EmptyRunnable;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.ui.FontType;
import javafx.beans.binding.Bindings;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.util.Duration;
import pokemon.search.Datos;
import javafx.scene.image.ImageView;

import static com.almasb.fxgl.dsl.FXGL.*;
public class MenuPrincipal extends FXGLMenu {
	private static final int SIZE = 120;
	private int numeroAlgoritmo = 1;
	
	ConfiguracionAlgoritmo escritorLector;
	
    private Animation<?> animation;
    
    private void cambiarAlgoritmo(Text textoAlgoritmo) {
    	numeroAlgoritmo = (numeroAlgoritmo) % 5 + 1;
    	
    	
    	switch (numeroAlgoritmo) {
    	case 1:
    		textoAlgoritmo.setText("ALGORITMO: DFS");
    		escritorLector.guardaAlgoritmo(1);
    		break;
    	case 2:
    		textoAlgoritmo.setText("ALGORITMO: BFS");
    		escritorLector.guardaAlgoritmo(2);
    		break;
    	case 3:
    		textoAlgoritmo.setText("ALGORITMO: COST. UNIF");
    		escritorLector.guardaAlgoritmo(3);
    		break;
    	case 4:
    		textoAlgoritmo.setText("ALGORITMO: Greedy");
    		escritorLector.guardaAlgoritmo(4);
    		break;
    	case 5:
    		textoAlgoritmo.setText("ALGORITMO: A*");
    		escritorLector.guardaAlgoritmo(5);
    	}
    }
    
	public MenuPrincipal(MenuType type) {
		super(type);
		escritorLector = new ConfiguracionAlgoritmo();
		FXGL.play(getClass().getResource("rolling5.mp3"));
		
		// TODO Auto-generated constructor stub
		
		 BackgroundImage backgroundImage;
		 Image bgImage = null;
		try {
			bgImage = new Image(getClass().getResource("fondocarga.png").openStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		Color transparente = Color.rgb(0, 0, 0, 0);
		
	        // Agrega la imagen de fondo a la capa
	        ImageView bgImageView = new ImageView(bgImage);
            getContentRoot().getChildren().add(bgImageView);
        
		 //getContentRoot().setTranslateX(FXGL.getAppWidth() / 2.0 - SIZE);
         //getContentRoot().setTranslateY(FXGL.getAppHeight() / 2.0 - SIZE);

         var shapeAlgoritmo = new Rectangle(SIZE*2, SIZE / 2);

         var shapeIniciar = new Rectangle(SIZE*2, SIZE / 2);
         
         var shapeSalir = new Rectangle(SIZE*2, SIZE / 2);
         
         shapeIniciar.strokeProperty().bind(
                 Bindings.when(shapeIniciar.hoverProperty()).then(Color.SKYBLUE).otherwise(transparente)
         );

         shapeIniciar.fillProperty().bind(
                 Bindings.when(shapeIniciar.pressedProperty()).then(Color.SKYBLUE).otherwise(transparente)
         );

         shapeIniciar.setTranslateY(20);
         shapeIniciar.setTranslateX(400);

         shapeIniciar.setOnMouseClicked(e -> {
        	 fireNewGame();
        	 FXGL.getAudioPlayer().stopAllMusic();
         });
         
         shapeAlgoritmo.strokeProperty().bind(
                 Bindings.when(shapeAlgoritmo.hoverProperty()).then(Color.SKYBLUE).otherwise(transparente)
         );

         shapeAlgoritmo.fillProperty().bind(
                 Bindings.when(shapeAlgoritmo.pressedProperty()).then(Color.SKYBLUE).otherwise(transparente)
         );
         //shapeAlgoritmo.setOnMouseClicked(e -> FXGL.getGameController().exit());
         
         shapeAlgoritmo.setTranslateY(85);
         shapeAlgoritmo.setTranslateX(400);
         
         shapeSalir.strokeProperty().bind(
                 Bindings.when(shapeSalir.hoverProperty()).then(Color.SKYBLUE).otherwise(transparente)
         );

         shapeSalir.fillProperty().bind(
                 Bindings.when(shapeSalir.pressedProperty()).then(Color.SKYBLUE).otherwise(transparente)
         );
         shapeSalir.setOnMouseClicked(e -> FXGL.getGameController().exit());
         
         shapeSalir.setTranslateY(150);
         shapeSalir.setTranslateX(400);

         Text textSimular = FXGL.getUIFactoryService().newText("SIMULAR", Color.WHITE, FontType.GAME, 24.0);
         textSimular.setTranslateX(470);
         textSimular.setTranslateY(55);
         textSimular.setMouseTransparent(true);

         Text textAlgoritmo = FXGL.getUIFactoryService().newText("ALGORITMO: DFS", Color.WHITE, FontType.GAME, 24.0);
         textAlgoritmo.setTranslateX(410);
         textAlgoritmo.setTranslateY(120);
         textAlgoritmo.setMouseTransparent(true);
         shapeAlgoritmo.setOnMouseClicked(e -> cambiarAlgoritmo(textAlgoritmo));
         cambiarAlgoritmo(textAlgoritmo);
         
         Text textSalir = FXGL.getUIFactoryService().newText("SALIR", Color.WHITE, FontType.GAME, 24.0);
         textSalir.setTranslateX(482);
         textSalir.setTranslateY(188);
         textSalir.setMouseTransparent(true);

         getContentRoot().getChildren().addAll(shapeIniciar, shapeAlgoritmo, shapeSalir, textSimular, textSalir, textAlgoritmo);

         getContentRoot().setScaleX(0);
         getContentRoot().setScaleY(0);

         animation = FXGL.animationBuilder()
                 .duration(Duration.seconds(0.66))
                 .interpolator(Interpolators.EXPONENTIAL.EASE_OUT())
                 .scale(getContentRoot())
                 .from(new Point2D(0, 0))
                 .to(new Point2D(1, 1))
                 .build();
	}
	
	
	@Override
    public void onCreate() {
        animation.setOnFinished(EmptyRunnable.INSTANCE);
        animation.stop();
        animation.start();
    }

    @Override
    protected void onUpdate(double tpf) {
        animation.onUpdate(tpf);
    }
	
}
