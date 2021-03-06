package com.udacity.gamedev.tictactoe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.udacity.gamedev.tictactoe.Constants.GridPosition;

/**
 * Created by jarrodparkes on 12/28/15.
 */
public class AIScreen extends InputAdapter implements Screen {

    public static final String TAG = AIScreen.class.getName();

    // world viewport
    ShapeRenderer renderer;
    ExtendViewport viewport;

    // HUD viewport
    SpriteBatch batch;
    ScreenViewport textViewport;
    BitmapFont font;

    // game data
    Game game;

    @Override
    public void show () {
        // setup world
        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);
        viewport = new ExtendViewport(Constants.WORLD_SIZE.x, Constants.WORLD_SIZE.y);
        game = new Game(new MinimaxStrategy());
        // setup HUD
        batch = new SpriteBatch();
        textViewport = new ScreenViewport();
        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void resize (int width, int height) {
        viewport.update(width, height, true);
        textViewport.update(width, height, true);
        font.getData().setScale(Math.min(width, height) / Constants.HUD_FONT_REFERENCE_SCREEN_WIDTH * 2);
    }

    @Override
    public void render (float delta) {
        // clear screen
        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r, Constants.BACKGROUND_COLOR.g, Constants.BACKGROUND_COLOR.b, Constants.BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // setup HUD drawing
        batch.setProjectionMatrix(textViewport.getCamera().combined);
        // draw HUD
        batch.begin();
        batch.end();
        // setup world drawing
        viewport.apply();
        renderer.setProjectionMatrix(viewport.getCamera().combined);
        // draw world
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        // draw playfield
        renderer.setColor(Constants.PLAYFIELD_COLOR);
        renderer.rectLine(
                Constants.PLAYFIELD_CENTER.x - Constants.PLAYFIELD_GRID_SIZE * 1.5f,
                Constants.PLAYFIELD_CENTER.y + Constants.PLAYFIELD_GRID_SIZE * 0.5f,
                Constants.PLAYFIELD_CENTER.x + Constants.PLAYFIELD_GRID_SIZE * 1.5f,
                Constants.PLAYFIELD_CENTER.y + Constants.PLAYFIELD_GRID_SIZE * 0.5f,
                Constants.PLAYFIELD_LINE_THICKNESS);
        renderer.rectLine(
                Constants.PLAYFIELD_CENTER.x - Constants.PLAYFIELD_GRID_SIZE * 1.5f,
                Constants.PLAYFIELD_CENTER.y - Constants.PLAYFIELD_GRID_SIZE * 0.5f,
                Constants.PLAYFIELD_CENTER.x + Constants.PLAYFIELD_GRID_SIZE * 1.5f,
                Constants.PLAYFIELD_CENTER.y - Constants.PLAYFIELD_GRID_SIZE * 0.5f,
                Constants.PLAYFIELD_LINE_THICKNESS);
        renderer.rectLine(
                Constants.PLAYFIELD_CENTER.x - Constants.PLAYFIELD_GRID_SIZE * 0.5f,
                Constants.PLAYFIELD_CENTER.y - Constants.PLAYFIELD_GRID_SIZE * 1.5f,
                Constants.PLAYFIELD_CENTER.x - Constants.PLAYFIELD_GRID_SIZE * 0.5f,
                Constants.PLAYFIELD_CENTER.y + Constants.PLAYFIELD_GRID_SIZE * 1.5f,
                Constants.PLAYFIELD_LINE_THICKNESS);
        renderer.rectLine(
                Constants.PLAYFIELD_CENTER.x + Constants.PLAYFIELD_GRID_SIZE * 0.5f,
                Constants.PLAYFIELD_CENTER.y - Constants.PLAYFIELD_GRID_SIZE * 1.5f,
                Constants.PLAYFIELD_CENTER.x + Constants.PLAYFIELD_GRID_SIZE * 0.5f,
                Constants.PLAYFIELD_CENTER.y + Constants.PLAYFIELD_GRID_SIZE * 1.5f,
                Constants.PLAYFIELD_LINE_THICKNESS);
        game.render(delta, renderer);
        renderer.end();
    }

    @Override
    public void dispose () {
        renderer.dispose();
        batch.dispose();
        font.dispose();
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        if (game.board.gameOver() == false) {
            Vector2 worldTouch = viewport.unproject(new Vector2(screenX, screenY));

            for (GridPosition position : GridPosition.values()) {
                boolean inX = false;
                boolean inY = false;
                if (worldTouch.x > position.position.x - Constants.PLAYFIELD_GRID_SIZE_HALF &&
                        worldTouch.x < position.position.x + Constants.PLAYFIELD_GRID_SIZE_HALF) {
                    inX = true;
                }
                if (worldTouch.y > position.position.y - Constants.PLAYFIELD_GRID_SIZE_HALF &&
                        worldTouch.y < position.position.y + Constants.PLAYFIELD_GRID_SIZE_HALF) {
                    inY = true;
                }
                if (inX && inY) {
                    game.moveHumanPlayer(Player.PlayerType.Player_X, game.gridPositionToCellPosition(position));
                    break;
                }
            }

            return true;
        } else {
            return false;
        }
    }
}
