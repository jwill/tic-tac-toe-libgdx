package com.udacity.gamedev.tictactoe;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by jarrodparkes on 12/28/15.
 */
public class Constants {

    public static final Vector2 WORLD_SIZE = new Vector2(16f, 9f);

    public static final float HUD_FONT_REFERENCE_SCREEN_WIDTH = 480.0f;

    public static final Color BACKGROUND_COLOR = Color.BLUE;

    public static final Color PLAYFIELD_COLOR = Color.WHITE;
    public static final float PLAYFIELD_LINE_THICKNESS = 0.1f;
    public static final Vector2 PLAYFIELD_CENTER = new Vector2(WORLD_SIZE.x / 4, WORLD_SIZE.y / 2);
    public static final float PLAYFIELD_GRID_SIZE = WORLD_SIZE.x / 8;
    public static final float PLAYFIELD_GRID_SIZE_HALF = PLAYFIELD_GRID_SIZE / 2;

    public static final Color CROSS_COLOR = Color.WHITE;
    public static final float CROSS_THICKNESS = 0.1f;
    public static final float CROSS_OFFSET_FROM_CENTER = PLAYFIELD_GRID_SIZE * 0.3f;

    public static final Color CIRCLE_COLOR = Color.WHITE;
    public static final float CIRCLE_RADIUS = PLAYFIELD_GRID_SIZE * 0.4f;
    public static final float CIRCLE_THICKNESS = 0.1f;

    public static final CellPosition[][] WINNING_PATTERNS = new CellPosition[][]{
            { new CellPosition(0, 0), new CellPosition(0, 1), new CellPosition(0, 2) },
            { new CellPosition(1, 0), new CellPosition(1, 1), new CellPosition(1, 2) },
            { new CellPosition(2, 0), new CellPosition(2, 1), new CellPosition(2, 2) },
            { new CellPosition(0, 0), new CellPosition(1, 0), new CellPosition(2, 0) },
            { new CellPosition(0, 1), new CellPosition(1, 1), new CellPosition(2, 1) },
            { new CellPosition(0, 2), new CellPosition(1, 2), new CellPosition(2, 2) },
            { new CellPosition(0, 0), new CellPosition(1, 1), new CellPosition(2, 2) },
            { new CellPosition(2, 0), new CellPosition(1, 1), new CellPosition(0, 2) }
    };

    public static final int THREE_CROSS_BONUS = 100;
    public static final int TWO_CROSS_BONUS = 10;
    public static final int ONE_CROSS_BONUS = 1;

    public static final int THREE_NOUGHT_BONUS = -100;
    public static final int TWO_NOUGHT_BONUS = -10;
    public static final int ONE_NOUGHT_BONUS = -1;

    public enum GridPosition {
        LOWER_LEFT(PLAYFIELD_CENTER.x - PLAYFIELD_GRID_SIZE, PLAYFIELD_CENTER.y - PLAYFIELD_GRID_SIZE),
        LOWER_MIDDLE(PLAYFIELD_CENTER.x, PLAYFIELD_CENTER.y - PLAYFIELD_GRID_SIZE),
        LOWER_RIGHT(PLAYFIELD_CENTER.x + PLAYFIELD_GRID_SIZE, PLAYFIELD_CENTER.y - PLAYFIELD_GRID_SIZE),
        MIDDLE_LEFT(PLAYFIELD_CENTER.x - PLAYFIELD_GRID_SIZE, PLAYFIELD_CENTER.y),
        MIDDLE_MIDDLE(PLAYFIELD_CENTER.x, PLAYFIELD_CENTER.y),
        MIDDLE_RIGHT(PLAYFIELD_CENTER.x + PLAYFIELD_GRID_SIZE, PLAYFIELD_CENTER.y),
        UPPER_LEFT(PLAYFIELD_CENTER.x - PLAYFIELD_GRID_SIZE, PLAYFIELD_CENTER.y + PLAYFIELD_GRID_SIZE),
        UPPER_MIDDLE(PLAYFIELD_CENTER.x, PLAYFIELD_CENTER.y + PLAYFIELD_GRID_SIZE),
        UPPER_RIGHT(PLAYFIELD_CENTER.x + PLAYFIELD_GRID_SIZE, PLAYFIELD_CENTER.y + PLAYFIELD_GRID_SIZE);

        Vector2 position;

        GridPosition(float x, float y) {
            this.position = new Vector2(x, y);
        }
    }
}