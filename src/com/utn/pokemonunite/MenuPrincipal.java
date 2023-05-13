package com.utn.pokemonunite;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameScene;

import java.io.IOException;

import com.almasb.fxgl.animation.Animation;
import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.core.util.EmptyRunnable;
import com.almasb.fxgl.dsl.FXGL;
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
import javafx.scene.image.ImageView;

import static com.almasb.fxgl.dsl.FXGL.*;
public class MenuPrincipal extends FXGLMenu {
	private static final int SIZE = 120;
	
    private Animation<?> animation;
	public MenuPrincipal(MenuType type) {
		super(type);
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

         shapeIniciar.setOnMouseClicked(e -> fireNewGame());
         
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
         
         /*
         

         var shape3 = new Rectangle(SIZE*2, SIZE / 2);
         shape3.setStrokeWidth(2.5);
         shape3.strokeProperty().bind(
                 Bindings.when(shape3.hoverProperty()).then(Color.YELLOW).otherwise(Color.BLACK)
         );

         shape3.fillProperty().bind(
                 Bindings.when(shape3.pressedProperty()).then(Color.YELLOW).otherwise(Color.color(0.1, 0.05, 0.0, 0.75))
         );

         shape3.setTranslateY(SIZE);
		
*/

         Text textResume = FXGL.getUIFactoryService().newText("SIMULAR", Color.WHITE, FontType.GAME, 24.0);
         textResume.setTranslateX(470);
         textResume.setTranslateY(55);
         textResume.setMouseTransparent(true);

         Text textOptions = FXGL.getUIFactoryService().newText("ALGORITMO: COST. UNIF.", Color.WHITE, FontType.GAME, 24.0);
         textOptions.setTranslateX(410);
         textOptions.setTranslateY(120);
         textOptions.setMouseTransparent(true);

         Text textExit = FXGL.getUIFactoryService().newText("SALIR", Color.WHITE, FontType.GAME, 24.0);
         textExit.setTranslateX(482);
         textExit.setTranslateY(188);
         textExit.setMouseTransparent(true);

         getContentRoot().getChildren().addAll(shapeIniciar, shapeAlgoritmo, shapeSalir, textResume, textExit, textOptions);

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
