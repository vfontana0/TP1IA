package com.utn.pokemonunite;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.LoadingScene;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.app.scene.SceneFactory;

public class PokemonSceneFactory extends SceneFactory {
	@Override
	public LoadingScene newLoadingScene() {
		return new PantallaCarga();
	}
	@Override
    public FXGLMenu newMainMenu() {
        return new MenuPrincipal(MenuType.MAIN_MENU);
    }

    @Override
    public FXGLMenu newGameMenu() {
        return new MenuPrincipal(MenuType.GAME_MENU);
    }
}
