package at.aau.gloryweapons.siegeanddestroy3d;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.peak.salut.SalutDevice;

import at.aau.gloryweapons.siegeanddestroy3d.game.activities.PlacementActivity;
import at.aau.gloryweapons.siegeanddestroy3d.game.models.GameConfiguration;
import at.aau.gloryweapons.siegeanddestroy3d.game.models.User;
import at.aau.gloryweapons.siegeanddestroy3d.network.interfaces.DummyNetworkCommunicator;
import at.aau.gloryweapons.siegeanddestroy3d.network.interfaces.NetworkCommunicator;
import at.aau.gloryweapons.siegeanddestroy3d.network.wifiDirect.ClientGameHandlerWifi;
import at.aau.gloryweapons.siegeanddestroy3d.network.interfaces.CallbackObject;
import at.aau.gloryweapons.siegeanddestroy3d.validation.ValidationHelperClass;

public class JoinGameActivity extends AppCompatActivity {

    private Button btnJoinGame;
    private EditText txtUserName;
    private TextView txtServer;
    private TextView txtError;

    private NetworkCommunicator clientCommunicator;
    private boolean connectedToServer = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_game);
        loadUiElements();

        btnJoinGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // new click, disable error information
                txtError.setVisibility(View.GONE);
                btnJoinGame.setClickable(false);

                if (!connectedToServer) {
                    // disable button
                    btnJoinGame.setText("Connecting");
                    // init server connection
                    connectToServer();

                } else {
                    // basic check for username on client side
                    Editable eUsername = txtUserName.getText();
                    String username = eUsername != null ? eUsername.toString() : null;
                    if (!ValidationHelperClass.isUserNameValid(username)) {
                        showError("Username ist ungültig");
                        btnJoinGame.setClickable(true);
                        return;
                    }

                    // check name on server too
                    clientCommunicator.sendNameToServer(username, new CallbackObject<User>() {
                        @Override
                        public void callback(User param) {
                            // server sent back response for name
                            if (param != null) {
                                // name ok
                                // save name and things
                                GlobalGameSettings.getCurrent().setLocalUser(param);

                                // move on to placement
                                startPlacementActivity();

                            } else {
                                // name invalid
                                showError("Username nicht verfügbar!");
                                btnJoinGame.setClickable(true);
                            }
                        }
                    });
                }
            }

        });
    }

    /**
     * Connects to a server and re-enables the button for the name check.
     */
    private void connectToServer() {
        if (connectedToServer)
            throw new IllegalStateException("Please dont try to connect twice");

        // init with singleton
        this.clientCommunicator = new DummyNetworkCommunicator();

        try {
            // init wifi direct
            this.clientCommunicator.initClientGameHandler(this, new CallbackObject<SalutDevice>() {
                @Override
                public void callback(SalutDevice param) {
                    // connecting finished

                    // re-enable button
                    btnJoinGame.setClickable(true);
                    btnJoinGame.setText("Name überprüfen");

                    // show server name
                    txtServer.setVisibility(View.VISIBLE);
                    txtServer.setText("Verbindung zum Server: " + param.deviceName + " " + param.readableName + " hergestellt!");

                    connectedToServer = true;
                }
            });
        } catch (Exception e) {
            // TODO @johannes return error if something bad happened (ie. some additional callback etc)

            // show and log error
            Log.e("Error", "Salut connection could not be established." + e.getMessage());
            showError("Verbindung fehlgeschlagen");

            // enable button again
            btnJoinGame.setClickable(true);
            btnJoinGame.setText("Erneut versuchen");
        }
    }

    private void showError(final String message) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txtError.setText(message);
                txtError.setVisibility(View.VISIBLE);
            }
        });
    }

    private void startPlacementActivity() {
        // start placement activity and provide the game settings
        Intent switchActivityIntent = new Intent(this, PlacementActivity.class);
        switchActivityIntent.putExtra(GameConfiguration.INTENT_KEYWORD, GlobalGameSettings.getCurrent());
        startActivity(switchActivityIntent);

        // remove this activity from the stack (back means WelcomePage)
        this.finish();
    }

    /**
     * Loads all UI Elements.
     */
    private void loadUiElements() {
        btnJoinGame = findViewById(R.id.buttonJoinGame);
        txtUserName = findViewById(R.id.editTextUserName);
        txtServer = findViewById(R.id.textViewServer);
        txtError = findViewById(R.id.textViewError);

        // disable info on start
        txtServer.setVisibility(View.GONE);
        txtError.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        if (clientCommunicator != null) {
            clientCommunicator.resetConnection();
        }
        super.onBackPressed();
    }
}
