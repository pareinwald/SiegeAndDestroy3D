package at.aau.gloryweapons.siegeanddestroy3d.network.dto;

import java.io.Serializable;

public class InstructionDTO {

    public enum InstructionType implements Serializable{
        DO_TURN,
        YOU_LOST,
        YOU_WON,
        WAIT,
        USER_DEAD
    }

    private static final long serialVersionUID = 900825671L;

    private InstructionType type;

    private int userId;

    private int enemyUserId;

    public InstructionDTO(InstructionType type){
        this.type = type;
    }

    public InstructionDTO(){
    }

    public InstructionType getType() {
        return type;
    }

    public void setType(InstructionType type) {
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getEnemyUserId() {
        return enemyUserId;
    }

    public void setEnemyUserId(int enemyUserId) {
        this.enemyUserId = enemyUserId;
    }
}
