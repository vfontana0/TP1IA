package com.utn.pokemonunite;
import com.almasb.fxgl.animation.Animation;
import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.audio.Music;

import static com.almasb.fxgl.dsl.FXGLForKtKt.*;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getAssetLoader;

import java.io.IOException;

import com.almasb.fxgl.core.util.EmptyRunnable;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.ui.FontType;
import javafx.beans.binding.Bindings;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import pokemon.search.Datos;
public class MenuPrincipal extends FXGLMenu {
	private static final int SIZE = 120;
	private int numeroAlgoritmo = 1;
	
	
    private Animation<?> animation;
    private Music musica;
    
    private void cambiarAlgoritmo(Text textoAlgoritmo) {
    	numeroAlgoritmo = (numeroAlgoritmo) % 5 + 1;
    	
    	
    	switch (numeroAlgoritmo) {
    	case 1:
    		textoAlgoritmo.setText("ALGORITMO: DFS");
    		Datos.nroEstrategia = 1;
    		break;
    	case 2:
    		textoAlgoritmo.setText("ALGORITMO: BFS");
    		Datos.nroEstrategia = 2;
    		break;
    	case 3:
    		textoAlgoritmo.setText("ALGORITMO: COST. UNIF");
    		Datos.nroEstrategia = 3;
    		break;
    	case 4:
    		textoAlgoritmo.setText("ALGORITMO: Greedy");
    		Datos.nroEstrategia = 4;
    		break;
    	case 5:
    		textoAlgoritmo.setText("ALGORITMO: A*");
    		Datos.nroEstrategia = 5;
    	}
    }
    
	public MenuPrincipal(MenuType type) {
		super(type);
		musica = getAssetLoader().loadMusic(getClass().getResource("rolling5.mp3"));
		FXGL.getAudioPlayer().loopMusic((musica));
        
		
		// TODO Auto-generated constructor stub
		
	    
		Color transparente = Color.rgb(0, 0, 0, 0);
		
		 Image bgImage = null;
		try {
			bgImage = new Image(getClass().getResource("fondocarga.png").openStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

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
        	 FXGL.getAudioPlayer().stopMusic(musica);
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
