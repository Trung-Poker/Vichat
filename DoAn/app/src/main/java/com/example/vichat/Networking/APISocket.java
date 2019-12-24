package com.example.vichat.Networking;

import android.content.SharedPreferences;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import static com.example.vichat.Activity.DangNhapActivity.xToken;
import static com.example.vichat.Networking.UrlSocket.getUrlSocket;

public class APISocket {
    public static Socket socket;
    public void conectSocket(String event, JSONObject data) {
        try
        {
            socket = IO.socket(getUrlSocket());
            socket.connect();
            socket.emit(event, data);
        } catch( URISyntaxException e)
        {
            e.printStackTrace();
        }
    }
}

