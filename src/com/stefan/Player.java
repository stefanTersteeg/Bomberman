package com.stefan;


import java.awt.*;
import java.util.List;

/**
 * Created by stersteeg on 29/05/2017.
 */
public class Player {
    private final int id;
    private Tile loc;
    private Tile moveLoc;
    private int[] controlKeys;
    private final List<Tile> map;
    private long startMovingTime = -1;
    private double relativeX = 0, relativeY = 0;

    private boolean moveLeft = false, moveUp = false, moveRight = false, moveDown = false;

    public Player(int id, Tile loc, List<Tile> map, int... keyCodes) {
        this.id = id;
        this.loc = loc;
        this.map = map;
        this.controlKeys = keyCodes;
    }

    public void paint(Graphics g) {
        updateLocation();
        g.fillArc((int) ((loc.getX() + 0.5) * Tile.TILE_WIDTH - 6 + (int) relativeX), (int) ((loc.getY() + 0.5) * Tile.TILE_HEIGHT - 6 + (int) relativeY), 12, 12, 0, 360);
    }

    private void updateLocation() {
        if(moveLoc != null) {
            if(startMovingTime + Util.MOVING_SPEED < System.currentTimeMillis()) {
                loc = moveLoc;
                moveLoc = null;
                relativeX = 0;
                relativeY = 0;
            }
        }
        if(moveLoc == null) {
            if(moveLeft && loc.getX() != 0) {
                Tile l = map.get(loc.getId() - Tile.TILE_VER_COUNT);
                if(l.isWalkable()) {
                    moveLoc = l;
                    startMovingTime = System.currentTimeMillis();
                }
            }
            if(moveRight && loc.getX() != Tile.TILE_HOR_COUNT * Tile.TILE_WIDTH) {
                Tile l = map.get(loc.getId() + Tile.TILE_VER_COUNT);
                if(l.isWalkable()) {
                    moveLoc = l;
                    startMovingTime = System.currentTimeMillis();
                }
            }
            if(moveUp && loc.getY() != 0) {
                Tile l = map.get(loc.getId() - 1);
                if(l.isWalkable()) {
                    moveLoc = l;
                    startMovingTime = System.currentTimeMillis();
                }
            }
            if(moveDown && loc.getX() != Tile.TILE_VER_COUNT * Tile.TILE_HEIGHT) {
                Tile l = map.get(loc.getId() + 1);
                if(l.isWalkable()) {
                    moveLoc = l;
                    startMovingTime = System.currentTimeMillis();
                }
            }
        }
        long time = System.currentTimeMillis() - startMovingTime;
        if(moveLoc != null && (time >= 0 || time <= Util.MOVING_SPEED)) {
            int xDistance = moveLoc.getX() - loc.getX();
            int yDistance = moveLoc.getY() - loc.getY();
            double percentage = (double)time / Util.MOVING_SPEED;
            relativeX = (xDistance * percentage) * Tile.TILE_WIDTH;
            relativeY = (yDistance * percentage) * Tile.TILE_HEIGHT;
        }
    }

    public void processKeyEvent(int keyCode, boolean down) {
        if(controlKeys[0] == keyCode) {
            moveLeft = down;
        } else if(controlKeys[1] == keyCode) {
            moveUp = down;
        } else if(controlKeys[2] == keyCode) {
            moveRight = down;
        } else if(controlKeys[3] == keyCode) {
            moveDown = down;
        }
    }
}
