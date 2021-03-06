package at.aau.gloryweapons.siegeanddestroy3d.network.interfaces;


import android.app.Activity;

import java.util.List;

import at.aau.gloryweapons.siegeanddestroy3d.game.models.BasicShip;
import at.aau.gloryweapons.siegeanddestroy3d.game.models.BattleArea;
import at.aau.gloryweapons.siegeanddestroy3d.game.models.GameConfiguration;
import at.aau.gloryweapons.siegeanddestroy3d.game.models.User;
import at.aau.gloryweapons.siegeanddestroy3d.network.dto.HandshakeDTO;
import at.aau.gloryweapons.siegeanddestroy3d.network.dto.TurnDTO;

/**
 * The Interface for a Object used to communicate with the server.
 */
public interface NetworkCommunicatorClient {
    /**
     * Sends a name to the server asynchronously and responds with the complete User if the name is valid.
     *
     * @param username The name to use for the user.
     * @param callback The callback which is called to return the User object. Null, if the name is not valid in the context.
     */
    public void sendNameToServer(String username, CallbackObject<User> callback);

    /**
     * Sends the whole game configuration for a user to the server asynchronously. The server responds with the complete configuration for the game.
     *
     * @param user        The user for whom the configuration is created.
     * @param userBoard   The board of the user, with all placed ships.
     * @param placedShips The ships of the user.
     * @param callback    The callback which is called to return the configurations of all users, as soon as everyone finished.
     */
    public void sendGameConfigurationToServer(User user, BattleArea userBoard,
                                              List<BasicShip> placedShips, CallbackObject<GameConfiguration> callback);

    /**
     * Inits the Client.
     *
     * @param ip
     * @param activity
     * @param isConnected
     */
    public void initClientGameHandler(String ip, final Activity activity, CallbackObject<HandshakeDTO> isConnected);

    /**
     * Stop network and disable wifi direct
     */
    public void resetNetwork();

    /**
     * send shot to server
     *
     * @param area
     * @param col
     * @param row
     * @param callback
     */
    public void sendShotOnEnemyToServer(BattleArea area, int col, int row, CallbackObject<TurnDTO> callback);

    /**
     * Ends the turn.
     */
    public void sendFinish();

    /**
     * Ask for the starting user.
     * Response is consumed by registering to NetworkCommunicatorClient.registerForCurrentTurnUserUpdates().
     */
    public void sendFirstUserRequestToServer();

    /**
     * Register for updates about a winner.
     *
     * @param winnerCallback
     */
    public void registerForWinnerInfos(CallbackObject<User> winnerCallback);

    /**
     * sends a request to the server, if another player cheats
     */
    public void sendCheatingSuspicion(CallbackObject<User> callback);

    /**
     * Registration of the callback if the server is closed.
     */
    public void registerQuitInfo(CallbackObject<Boolean> callback);

    /**
     * Register for updates about the user for the current turn.
     *
     * @param currentTurnUserCallback
     */
    void registerForCurrentTurnUserUpdates(CallbackObject<User> currentTurnUserCallback);
}
