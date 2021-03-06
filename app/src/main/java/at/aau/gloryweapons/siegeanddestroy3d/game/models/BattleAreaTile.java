package at.aau.gloryweapons.siegeanddestroy3d.game.models;

import java.io.Serializable;

/**
 * Created by Alexander on 05.04.2018.
 */
public class BattleAreaTile implements Serializable {

    public enum TileType {
        WATER,
        SHIP_START,
        SHIP_MIDDLE,
        SHIP_END,
        SHIP_DESTROYED,
        NO_HIT
    }

    private boolean horizontal = true;

    // initial type is water
    private TileType type = TileType.WATER;

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    public boolean isAlive(){
        return getType() == TileType.SHIP_START
                || getType() == TileType.SHIP_MIDDLE
                || getType() == TileType.SHIP_END;
    }
}
