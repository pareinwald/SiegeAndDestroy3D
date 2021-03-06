package at.aau.gloryweapons.siegeanddestroy3d.game.activities;



import org.junit.Test;

import at.aau.gloryweapons.siegeanddestroy3d.R;
import at.aau.gloryweapons.siegeanddestroy3d.game.models.BattleArea;
import at.aau.gloryweapons.siegeanddestroy3d.game.models.BattleAreaTile;
import at.aau.gloryweapons.siegeanddestroy3d.game.views.GameBoardImageView;

import static at.aau.gloryweapons.siegeanddestroy3d.game.models.BattleAreaTile.TileType.NO_HIT;
import static at.aau.gloryweapons.siegeanddestroy3d.game.models.BattleAreaTile.TileType.SHIP_DESTROYED;
import static at.aau.gloryweapons.siegeanddestroy3d.game.models.BattleAreaTile.TileType.SHIP_END;
import static at.aau.gloryweapons.siegeanddestroy3d.game.models.BattleAreaTile.TileType.SHIP_MIDDLE;
import static at.aau.gloryweapons.siegeanddestroy3d.game.models.BattleAreaTile.TileType.SHIP_START;
import static at.aau.gloryweapons.siegeanddestroy3d.game.models.BattleAreaTile.TileType.WATER;
import static org.junit.Assert.*;

public class GameTurnsActivityTest {

    @Test
    public void onCreate() {
    }

    @Test
    public void useSensorsforCheating() {
    }

    @Test
    public void onPause() {
    }

    @Test
    public void onResume() {
    }
    @Test
    public void TestTheReturnedTileIfNO_HIT()
    {
        GameTurnsActivity gta = new GameTurnsActivity();
        assertEquals(R.drawable.no_hit,gta.getTheRightTile(NO_HIT));
    }
    @Test
    public void TestTheReturnedTileIfWater()
    {
        GameTurnsActivity gta = new GameTurnsActivity();
        assertEquals(R.drawable.water,gta.getTheRightTile(WATER));
    }
    @Test
    public void TestTheReturnedTileIfSHIP_START()
    {
        GameTurnsActivity gta = new GameTurnsActivity();
        assertEquals(R.drawable.ship_start,gta.getTheRightTile(SHIP_START));
    }
    @Test
    public void TestTheReturnedTileIfSHIP_MIDDLE()
    {
        GameTurnsActivity gta = new GameTurnsActivity();
        assertEquals(R.drawable.ship_middle,gta.getTheRightTile(SHIP_MIDDLE));
    }
    @Test
    public void TestTheReturnedTileIfSHIP_END()
    {
        GameTurnsActivity gta = new GameTurnsActivity();
        assertEquals(R.drawable.ship_end,gta.getTheRightTile(SHIP_END));
    }
    @Test
    public void TestTheReturnedTileIfSHIP_DESTROYED()
    {
        GameTurnsActivity gta = new GameTurnsActivity();
        assertEquals(R.drawable.ship_destroyed,gta.getTheRightTile(SHIP_DESTROYED));
    }

    /**
     * not possible, because of ENUM
     */
    @Test
    public void TestTheReturnedTileIfWrongTile()
    {

    }
}