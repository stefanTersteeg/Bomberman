package com.stefan;

import java.awt.*;


/**
 * Created by stersteeg on 29/05/2017.
 */
public class Tile {
    public static final int TILE_HEIGHT = 45, TILE_WIDTH = 45;
    public static final int TILE_HOR_COUNT = 15, TILE_VER_COUNT = 13;

    private final int x, y, id;

    public Tile(int x, int y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public boolean isWalkable() {
        return !(x % 2 == 1 && y % 2 == 1);
    }

    public void paint(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        int xLoc = x * TILE_WIDTH;
        int yLoc = y * TILE_HEIGHT;
        if (!isWalkable()) {
            g.fillRect(xLoc, yLoc, TILE_WIDTH, TILE_HEIGHT);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getId() {
        return id;
    }
}
