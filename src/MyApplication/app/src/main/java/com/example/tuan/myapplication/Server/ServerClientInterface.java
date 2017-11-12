package com.example.tuan.myapplication.Server;

import com.example.tuan.myapplication.model.Game;

/**
 * Created by Tuan on 03.07.2017.
 */

public class ServerClientInterface {

    public static boolean getVersionControl() {
        SendJSON.sendHalloServer();
        //
        HandleJSON.handle(answer, null);
        return true;
    }

    public static boolean getPlayerId() {
        SendJSON.sendWillkommen();
        HandleJSON.handle(answer);

    }

    public static boolean updatePlayer() {
        SendJSON.sendStatusupdate(Game.getPlayer().id);
        HandleJSON.handle(answer);
        return true;
    }

    public static boolean getMap() {
        SendJSON.se
    }

}
