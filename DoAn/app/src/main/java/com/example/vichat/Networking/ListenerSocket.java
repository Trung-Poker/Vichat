package com.example.vichat.Networking;

import android.app.Activity;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import static com.example.vichat.Networking.UrlSocket.getUrlSocket;

public class ListenerSocket {
    public static Socket socket;
    public static String status;

    public String ListenerSocketOn(final Activity context, String event)
    {
        try
        {
            socket = IO.socket(getUrlSocket());
            socket.connect();
            System.out.println("a");
            
            socket.on(event, new Emitter.Listener() {
                @Override
                public void call(final Object... args) {
                            JSONObject data = (JSONObject) args[0];
                            try {
                                //extract data from fired event
                                status = data.getString("Status");
                                System.out.println("1" + status);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                }
            });
        } catch( URISyntaxException e)
        {
            e.printStackTrace();
        }
        System.out.println("2" + this.status);
        return (String) this.status;
    }
}
