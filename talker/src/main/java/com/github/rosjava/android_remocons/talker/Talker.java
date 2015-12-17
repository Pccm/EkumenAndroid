package com.github.rosjava.android_remocons.talker;


import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.github.rosjava.android_remocons.common_tools.apps.RosAppActivity;
import org.ros.android.view.RosTextView;
import org.ros.node.NodeConfiguration;
import org.ros.node.NodeMainExecutor;


import java.io.IOException;
import java.lang.String;
import java.net.InetAddress;
import java.net.Socket;


public class Talker extends RosAppActivity implements View.OnClickListener {
    private RosTextView<std_msgs.String> rosTextView;
    private Button buttonStart;
    private Button buttonPause;


    Sender sender;

    public Talker() {
        super("Talker", "Talker");
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        setDefaultMasterName(getString(R.string.default_robot));
        setDashboardResource(R.id.top_bar);
        setMainWindowResource(R.layout.main);
        super.onCreate(savedInstanceState);

        buttonStart = (Button) findViewById(R.id.button);
        buttonPause= (Button) findViewById(R.id.button2);

        buttonStart.setOnClickListener(this);
        buttonPause.setOnClickListener(this);


    }


    @Override
    protected void init(NodeMainExecutor nodeMainExecutor) {

        super.init(nodeMainExecutor);

        sender = new Sender();

        try {

            Thread.sleep(1000);
            Socket socket = new Socket(getMasterUri().getHost(), getMasterUri().getPort());
            InetAddress local_network_address = socket.getLocalAddress();
            socket.close();
            NodeConfiguration nodeConfiguration =
                    NodeConfiguration.newPublic(local_network_address.getHostAddress(), getMasterUri());

            nodeMainExecutor.execute(sender, nodeConfiguration);


        } catch (InterruptedException e) {
            // Thread interruption
            Log.e("Talker", "sleep interrupted");
        } catch (IOException e) {
            // Socket problem
            Log.e("Talker", "socket error trying to get networking information from the master uri");
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, getString(R.string.stop_app));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case 0:
                finish();
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                sender.publicar("1");
                Toast.makeText(getApplicationContext(), "Start Draw", Toast.LENGTH_SHORT).show();
                break;

            case R.id.button2:
                sender.publicar("0");
                Toast.makeText(getApplicationContext(), "Pause Draw", Toast.LENGTH_SHORT).show();
                break;

        }
    }




}



