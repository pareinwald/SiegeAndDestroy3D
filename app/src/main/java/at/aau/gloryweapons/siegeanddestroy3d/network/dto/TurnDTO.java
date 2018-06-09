package at.aau.gloryweapons.siegeanddestroy3d.network.dto;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonIgnore;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.io.Serializable;

import at.aau.gloryweapons.siegeanddestroy3d.game.models.BattleArea;
import at.aau.gloryweapons.siegeanddestroy3d.game.models.User;
import at.aau.gloryweapons.siegeanddestroy3d.network.dto.converter.TurnTypeConverter;

@JsonObject
public class TurnDTO extends RequestDTO implements Serializable {

    @JsonIgnore
    private static final long serialVersionUID = 1459083456079L;

    @JsonField(typeConverter = TurnTypeConverter.class)
    private TurnType type;

    @JsonField
    private BattleArea area;

    @JsonField
    private int yCoordinates;

    @JsonField
    private int xCoordinates;

    public enum TurnType {
        SHOT,
        POWERUP,
        HIT,
        NO_HIT,
        ERROR
    }

    public TurnDTO(TurnType type, BattleArea area) {
        this.type = type;
        this.area = area;
    }

    public TurnDTO() {
    }

    public TurnType getType() {
        return type;
    }

    public void setType(TurnType type) {
        this.type = type;
    }

    public int getyCoordinates() {
        return yCoordinates;
    }

    public void setyCoordinates(int yCoordinates) {
        this.yCoordinates = yCoordinates;
    }

    public int getxCoordinates() {
        return xCoordinates;
    }

    public void setxCoordinates(int xCoordinates) {
        this.xCoordinates = xCoordinates;
    }

    public BattleArea getArea() {
        return area;
    }

    public int getUserId() {
        return area.getUserId();
    }

    public void setArea(BattleArea area) {
        this.area = area;
    }
}

