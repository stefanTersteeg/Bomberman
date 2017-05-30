package com.stefan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by stersteeg on 29/05/2017.
 */
public class PaintPanel extends JPanel implements KeyListener {

     private List<Tile> tiles = new ArrayList<>();
     private List<Player> players = new ArrayList<>();

    public PaintPanel() {
        setPreferredSize(new Dimension(Tile.TILE_HOR_COUNT * Tile.TILE_WIDTH, Tile.TILE_VER_COUNT * Tile.TILE_HEIGHT));

        int id = 0;
        for(int x = 0; x < 15; x++) {
            for(int y = 0; y < 13; y++) {
                tiles.add(new Tile(x, y, id++));
            }
        }

        players.add(new Player(0, tiles.get(0), tiles, 37, 38, 39, 40));
        players.add(new Player(1, tiles.get(Tile.TILE_HOR_COUNT * Tile.TILE_VER_COUNT - 1), tiles, 65, 87, 68, 83));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(Tile t : tiles) {
            t.paint(g);
        }
        for(Player p : players) {
            p.paint(g);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        for(Player p : players) {
            p.processKeyEvent(e.getKeyCode(), true);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        for(Player p : players) {
            p.processKeyEvent(e.getKeyCode(), false);
        }

    }
}
