package com.utn.pokemonunite;

import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.app.scene.LoadingScene;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameScene;
import static java.lang.Thread.sleep;

import java.io.IOException;

public class PantallaCarga extends LoadingScene {
	public PantallaCarga() {
		
		var bg = new Rectangle(getAppWidth(), getAppHeight(),
				Color.AZURE);
		var text = getUIFactoryService().newText("Eligiendo acciones a ejecutar...", Color.BLACK, 46.0);
		centerText(text,getAppWidth()/2,getAppHeight()/3 +25);
		
		var hbox = new HBox(5);
		for (int i = 0; i < 3; i++) {
			var textDot = getUIFactoryService().newText(".",
			Color.BLACK, 46.0);
			hbox.getChildren().add(textDot);
			animationBuilder(this)
				.autoReverse(true)
				.delay(Duration.seconds(i * 0.5))
				.repeatInfinitely()
				.fadeIn(textDot)
				.buildAndPlay();
			hbox.setTranslateX(getAppWidth() / 2 - 20);
			hbox.setTranslateY(getAppHeight() / 2);
	
		}
		getContentRoot().getChildren().setAll(bg,text,hbox);
	}
}
