package com.example.tuan.myapplication.Server;

import java.util.ArrayList;

import java.util.Arrays;


import com.example.tuan.myapplication.model.Card;
import com.example.tuan.myapplication.model.Cost;
import com.example.tuan.myapplication.model.Game;
import com.example.tuan.myapplication.model.Player;
import com.example.tuan.myapplication.model.TradeOffer;

import org.json.JSONException;
import org.json.JSONObject;


public class SendJSON 
{
	public static Integer id = 0;
	public static Integer handelsID=0;	
	public static String targetUrl= "blablabla";
	public static String phase = "Start";
	public static ArrayList<Integer> currentIds;
	public static void sendErfindungServer(int playerID, int lumber, int brick, int wool, int ore, int grain) throws JSONException {
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		obj1.put("Spieler", playerID);
		
		Cost res = new Cost();
		res.brick=brick;
		res.grain=grain;
		res.lumber=lumber;
		res.ore=ore;
		res.wool=wool;
		
		objOut.put("Erfindung", obj1);
		obj1.put("Rohstoffe", res);

		CatanServlet.broadcast(objOut);
	}
	
	public static void sendErfindungClient(int playerID, Cost res) throws JSONException {
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		
		objOut.put("Erfindung", obj1);
		obj1.put("Rohstoffe", res);
		
		Client.executePost("", objOut);
	}

	public static void sendMonopolServer(int playerID, String resource) throws JSONException {
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		objOut.put("Monopol", obj1);
		obj1.put("Spieler", playerID);
		obj1.put("Rohstoff", resource);
		
		CatanServlet.broadcast(objOut);
	}
	
	public static void sendMonopolClient(int playerID, String resource) throws JSONException {
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		objOut.put("Monopol", obj1);
		obj1.put("Rohstoff", resource);
		
		Client.executePost(targetUrl, objOut);
	}

	public static void sendStrassenbaukarteAusspielenServer(int playerID, int x1, int y1, int x2, int y2) throws JSONException {
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		JSONObject[] destinationArray = new JSONObject[2];
		JSONObject tile1 = new JSONObject();
		JSONObject tile2 = new JSONObject();
		destinationArray[0] = tile1;
		destinationArray[1] = tile2;
		tile1.put("x", x1);
		tile1.put("y", y1);
		tile2.put("x", x2);
		tile2.put("x", y2);
		
		objOut.put("Straßenbaukarte ausspielen", obj1);
		obj1.put("Spieler", playerID);
		obj1.put("Straße", destinationArray);
		
		CatanServlet.broadcast(objOut);
	}

	public static void sendNextPlayerInTurn(){
		if (Game.phase.equals("Start")) {

		}
	}
	// send by Client
	public static void sendStrassenbaukarteAusspielenClient(int playerID) 
	{
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		
		
	}

	public static void sendRitterAusspielenServer(int playerID, int x, int y, int target) throws JSONException {
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		JSONObject destinationObject = new JSONObject();
		destinationObject.put("x", x);
		destinationObject.put("y", y);
		objOut.put("Ritter ausspielen", obj1);
		obj1.put("Spieler", playerID);
		obj1.put("Ort", destinationObject);
		obj1.put("Ziel", target);
		
		CatanServlet.broadcast(objOut);
	}
	
	// send by Client
	public static void sendRitterAusspielenClient(int playerID, int x, int y, int target) throws JSONException {
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		JSONObject destinationObject = new JSONObject();
		destinationObject.put("x", x);
		destinationObject.put("y", y);
		objOut.put("Ritter ausspielen", obj1);
		obj1.put("Ort", destinationObject);
		obj1.put("Ziel", target);
		
		Client.executePost("", objOut);
	}

	public static void sendHandelsangebotAbgebrochen(int playerID, int tradeID) throws JSONException {
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		objOut.put("Handelsangebot abgebrochen", obj1);
		obj1.put("Spieler", playerID);
		obj1.put("Handel id", tradeID);
		
		CatanServlet.broadcast(objOut);
	}
	// send by Client
	public static void sendHandelAbbrechen(int playerID, int tradeID) throws JSONException {
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		objOut.put("Handelsangebot angenommen", obj1);
		obj1.put("Spieler", playerID);
		obj1.put("Handel id", tradeID);

		Client.executePost("", objOut);
	}

	public static void sendHandelAusgefuehrt(int playerID, int otherPlayerID, int tradeID) throws JSONException {
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();

		obj1.put("Mitspieler", otherPlayerID);
		obj1.put("Spieler", playerID);
		obj1.put("Handel id",tradeID);
		objOut.put("Handelsangebot angenommen", obj1);
		CatanServlet.broadcast(objOut);
	}

	// send by Client, when he/she accepted the offer from someone
	public static void sendHandelAbschliessen(int otherPlayerID, int tradeID) throws JSONException {
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		objOut.put("Handel abschließen", obj1);
		obj1.put("Mitspieler", otherPlayerID);
		obj1.put("Handel id", tradeID);
		
		Client.executePost(targetUrl, objOut);
	}

	public static void sendHandelsangebotAngenommen(int otherPlayerID, int tradeID) throws JSONException {
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		objOut.put("Handelsangebot angenommen", obj1);
		obj1.put("Mitspieler", otherPlayerID);
		obj1.put("Handel id", tradeID);
		obj1.put("Annehmen", true);
		TradeOffer tOffer = Game.getTradeOfferById(tradeID);
		CatanServlet.send(objOut,tOffer.offerId);
	}

	// send by Client
	public static void sendHandelAnnehmen(int playerID, int tradeID, boolean decision) throws JSONException {
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		objOut.put("Handel annehmen", obj1);
		obj1.put("Handel id", tradeID);
		obj1.put("Annehmen", decision);
		
		Client.executePost(targetUrl, objOut);
	}

	public static void sendHandelsangebot(int playerID, int handelsID,  JSONObject angebot, JSONObject nachfrage) throws JSONException {
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		objOut.put("Handelsangebot", obj1);
		obj1.put("Spieler", playerID);
		obj1.put("Handels id", handelsID);
		obj1.put("Angebot", angebot);
		obj1.put("Nachfrage", nachfrage);
		CatanServlet.broadcast(objOut);
	}

	// send by Client
	public static void sendHandelAnbieten(int playerID, Cost angebot, Cost nachfrage) throws JSONException {
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		objOut.put("Handel anbieten", obj1);
		
		JSONObject angebotObject = angebot.toJSONObject();
		JSONObject nachfrageObject = nachfrage.toJSONObject();
		
		//TODO wenn das nicht funktioniert, einzeln die Ints rausziehen. -_- laut protokoll
		
		obj1.put("Angebot", angebotObject);
		obj1.put("Nachfrage", nachfrageObject);
		
		Client.executePost(targetUrl, objOut);
	}

	// send by Client
	public static void sendZugBeenden(int playerID) throws JSONException {
		JSONObject objOut = new JSONObject();
		objOut.put("Zug beenden", null);
		
		Client.executePost("", objOut);
	}
	
	// send by Client
	public  static  void sendSeehandel(int playerID, Cost angebot, Cost nachfrage) throws JSONException {
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		objOut.put("Seehandel", obj1);
		
		// RESOURCEOBJECT?!?
		JSONObject angebotObject =  angebot.toJSONObject();
		JSONObject nachfrageObject = nachfrage.toJSONObject();
		
		obj1.put("Angebot", angebotObject);
		obj1.put("Nachfrage", nachfrageObject);
		
		Client.executePost(targetUrl, objOut);
	}

	// send by Client
	public static void sendEntwicklungskarteKaufen(int playerID) throws JSONException {
		JSONObject objOut = new JSONObject();
		objOut.put("Entwicklungskarte kaufen", null);
		
		Client.executePost("", objOut); 
	}

	

	// send by Client
	/**
	 * always call with all position parameter, if it's a Road set them to null!
	 */
	public static void sendBauen(int playerID, String type, Integer x1, Integer y1, Integer x2, Integer y2, Integer x3, Integer y3) throws JSONException {
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		objOut.put("Bauen", obj1);
		
		JSONObject tile1 = new JSONObject();
		tile1.put("x", x1);
		tile1.put("y", y1);
		JSONObject tile2 = new JSONObject();
		tile2.put("x", x2);
		tile2.put("y", y2);
		JSONObject tile3 = new JSONObject();
		tile3.put("x", x3);
		tile3.put("y", y3);

		JSONObject[] destinationArray = new JSONObject[3];
		destinationArray[0] = tile1;
		destinationArray[1] = tile2;
		destinationArray[2] = tile3;
		
		obj1.put("Typ", type); 
		obj1.put("Ort", destinationArray);
		
		Client.executePost("", objOut);
	}

	
	// send by Client
	public static void sendRaeuberVersetzen(int playerID, Integer destinationX, Integer destinationY, Integer target) throws JSONException {
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		objOut.put("Räuber versetzen", obj1);
		JSONObject destinationObject = new JSONObject();
		obj1.put("Ort", destinationObject);	
		destinationObject.put("x", destinationX);
		destinationObject.put("y", destinationY);
		obj1.put("Ziel", target);
		
		Client.executePost("", objOut);
	}

	// send by Client
	public static  void sendKartenAbgeben(int playerID) throws JSONException {
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		objOut.put("Karten abgeben", obj1);
		obj1.put("Rohstoffe", null);	
		
		Client.executePost("", objOut);
	}

	// send by Client
	public static  void sendWuerfeln(int playerID) throws JSONException {
		JSONObject objOut = new JSONObject();
		objOut.put("Würfeln", null);
		
		Client.executePost(targetUrl, objOut);
	}

	public static void sendGroessteRittermacht(int playerID) throws JSONException {
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		objOut.put("Gr��te Rittermacht", obj1);
		obj1.put("Spieler", playerID);
		
		CatanServlet.broadcast(objOut);
	}

	public static  void sendLaengsteHandelsstrasse(int playerID) throws JSONException {
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		objOut.put("L�ngste Handelsstra�e", obj1);
		obj1.put("Spieler", playerID);
		
		CatanServlet.broadcast(objOut);
	}

	public static void sendEntwicklungskarteGekauft(int playerID, String karte) throws JSONException {
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		objOut.put("Entwicklungskarte gekauft", obj1);
		obj1.put("Spieler", playerID);
		obj1.put("Entwicklungskarte", karte);
		
		CatanServlet.broadcast(objOut);
	}

	public static void sendBauvorgang(Integer playerID, String type, Integer x1, Integer y1, Integer x2, Integer y2, Integer x3, Integer y3) throws JSONException {
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		objOut.put("Bauvorgang", obj1);
		JSONObject buildingObject = new JSONObject();
		obj1.put("Gebäude", buildingObject);
		buildingObject.put("Eigentümer", playerID);
		buildingObject.put("Typ", type);
		
		JSONObject [] destinationArray = new JSONObject[3];
		
		JSONObject coords1 = new JSONObject();
		coords1.put("x", x1);
		coords1.put("y", y1);
		
		JSONObject coords2 = new JSONObject();
		coords2.put("x", x2);
		coords2.put("y", y2);
		
		JSONObject coords3 = new JSONObject();
		coords2.put("x", x3);
		coords2.put("y", y3);
				
		destinationArray[0] = coords1;
		destinationArray[1] = coords2;
		destinationArray[2] = coords3;

		buildingObject.put("Ort", destinationArray);
		
		CatanServlet.broadcast(objOut);
	}

	public static void sendRaeuberVersetzt(int playerID, Integer destinationX, Integer destinationY) throws JSONException {
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		objOut.put("R�uber versetzt", obj1);
		obj1.put("Spieler", playerID);
		
		JSONObject destinationObject = new JSONObject();
		obj1.put("Ort", destinationObject);
		destinationObject.put("x", destinationX);
		destinationObject.put("y", destinationY);

		CatanServlet.broadcast(objOut);
	}

	public static void sendKosten(int playerID, Cost cost) throws JSONException {
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();

		obj1.put("Spieler", playerID);
		//TODO add RoshtoffObject
		obj1.put("Rohstoffe", cost.toJSONObject());
		objOut.put("Kosten", obj1);
		CatanServlet.send(objOut, playerID);
	}

	public static void sendErtrag(int playerID) throws JSONException {
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		objOut.put("Ertrag", obj1);
		obj1.put("Spieler", playerID);
		
		//TODO add RohstoffObject
		obj1.put("Rohstoffe", null);
		
		CatanServlet.send(objOut, playerID);
	}

	public static void sendWuerfelwurf(int playerID) throws JSONException {
		//TODO wuerfeln richtig machen
		Integer wuerfel1 = (int) ((Math.random()*5)+1);
		Integer wuerfel2 = (int) ((Math.random()*5)+1);
		int [] wuerfel = {wuerfel1,wuerfel2};
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		objOut.put("Würfelwurf", obj1);
		obj1.put("Spieler", playerID);		
		obj1.put("Wurf", wuerfel);

		if (wuerfel1 + wuerfel2 == 7) {
			Game.setStatus(playerID,"Räuber versetzen");
		}
		//TODO : Update Resouces von Player for all Players
		CatanServlet.broadcast(objOut);
	}

	public static void sendStatusupdate(int playerID) throws JSONException {
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		JSONObject playerObject = new JSONObject();
		Player p = Game.getPlayerById(playerID);
		playerObject.put("id", playerID);
		playerObject.put("Farbe", Game.getPlayerById(playerID).getColor()); //kann NULL sein!
		playerObject.put("Name", Game.getPlayerById(playerID).getName());   //kann NULL sein!
		
		//TODO where is the Status from? Server dictates the rotation?
		playerObject.put("Status", Game.getPlayerById(playerID).getStatus());
		playerObject.put("Siegpunkte", Game.getPlayerById(playerID).getVicPoint());
		playerObject.put("Rohstoffe", Game.getPlayerResource(playerID).toJSONObject());
		
		//Rittermacht tells only how many Ritterkarten have been played
		playerObject.put("Rittermacht",Game.getPlayerById(playerID).getArmyNumber() );

		// TODO :
		JSONObject cardObject = new JSONObject();
		int knights = 0;
		int streetBuild = 0;
		int monopol = 0;
		int invention = 0;
		int vicPoint = 0;
		for(Card card : p.getDevCards()) {
			switch (card.type) {
				case  KNIGHT:
					knights ++;
					break;
				case STREET_BUILD:
					streetBuild++;
					break;
				case MONOPOLY:
					monopol++;
					break;
				case INVENTION:
					invention++;
					break;
				case VICTORY_POINT:
					vicPoint++;
					break;
			}
		}
		cardObject.put("Ritter", knights);
		cardObject.put("Straßenbau", streetBuild);
		cardObject.put("Monopol", monopol);
		cardObject.put("Erfindung",invention);
		cardObject.put("Siegpunkt", vicPoint);
		playerObject.put("Entwicklungskarten", cardObject);

		//entweder false oder PlayerID?
		playerObject.put("Größte Rittermacht", Game.getPlayerById(playerID).getBiggestArmy());
		playerObject.put("Längste Handelsstraße",Game.getPlayerById(playerID).hasLongestRoad());
		obj1.put("Spieler", playerObject);
		objOut.put("Statusupdate", obj1);

		CatanServlet.broadcast(objOut);
	}
	public static void sendSimpleStatusupdate(int playerID) throws JSONException {
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		JSONObject playerObject = new JSONObject();

		playerObject.put("id", playerID);
		playerObject.put("Status", Game.getPlayerById(playerID).getStatus());
		obj1.put("Spieler", playerObject);
		objOut.put("Statusupdate", obj1);
		CatanServlet.broadcast(objOut);
	}
	public static void sendSpielBeendet(int playerID, String message) throws JSONException {
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();

		obj1.put("Spieler", playerID);
		obj1.put("Nachricht", message);
		objOut.put("Spiel beendet", obj1);
		CatanServlet.broadcast(objOut);
	}

	public static void sendSpielGestartet() throws JSONException {
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		obj1.put("Karte", Game.getMapObject());
		objOut.put("Spiel gestartet", obj1);
		CatanServlet.broadcast(objOut);
	}

	public static void sendFehler( Integer playerID, String fehler) throws JSONException {
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		objOut.put("Fehler", obj1);
		obj1.put("Meldung", fehler);
		
		CatanServlet.send(objOut, playerID);
	}

	
	// send by Client
	public static void sendSpielStarten(int playerID) throws JSONException {
		JSONObject objOut = new JSONObject();
		objOut.put("Spiel starten", null);
		
		Client.executePost(targetUrl, objOut);
	}

	// send by Client
	public static void sendSpieler(int playerID) throws JSONException {
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();

		obj1.put("Name", Game.getPlayer().getName());
		obj1.put("Farbe", Game.getPlayer().getColor());
		objOut.put("Spieler", obj1);
		Client.executePost(targetUrl, objOut);
	}

	public static void sendChatnachricht(String message, int playerID) throws JSONException {
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		objOut.put("Chatnachricht", obj1);
		obj1.put("Absender", playerID);
		obj1.put("Nachricht", message);
		
		CatanServlet.broadcast(objOut);
	}

	// send by Client
	public static void sendChatnachrichtSenden(String message, int playerID) throws JSONException {
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		objOut.put("Chatnachricht senden", obj1);
		obj1.put("Nachricht", message);
		
		Client.executePost("targetURL"+"?playerID=" + playerID, objOut);
	}
	
	public static void sendServerantwortElse (int playerID, String message) throws JSONException {
		JSONObject objOut = new JSONObject();
		objOut.put("Serverantwort", message);
		
		CatanServlet.send(objOut, playerID);
	}

	public static void sendServerantwortOK(int playerID) throws JSONException {
		JSONObject objOut = new JSONObject();
		objOut.put("Serverantwort", "OK");
		
		CatanServlet.send(objOut, playerID);

	}

	public static JSONObject sendWillkommen() throws JSONException {
		if (currentIds.size()>=4) 
		{
			//TODO return JSONObject(Fehlermeldung);
		}
		id++;
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		Game.addPlayer(new Player(id));
		obj1.put("id", id);
		objOut.put("Willkommen", obj1);
		currentIds.add(id);
		CatanServlet.addMessageBuffer(id);
		SendJSON.sendSimpleStatusupdate(id);
		return objOut;
	}

	public static JSONObject sendHalloServer() throws JSONException {
		// (Hallo : (Version:...,Protokoll:0.3))
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		objOut.put("Hallo", obj1);
		obj1.put("Version", "AndroidServer 0.3 (sepgroup01)");
		obj1.put("Protokoll", "0.3");
		
		//MUSS DIREKT IN DEN RESPONSE BEI GET
		//CatanServlet.send(objOut, (Integer) null);
		return objOut;
	}
	
	// send by Client
	public static void sendHalloClient() throws JSONException {
		// (Hallo : (Version:...,Protokoll:0.3))
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		obj1.put("Version", "AndroidClient 0.3 (sepgroup01)");
		objOut.put("Hallo", obj1);
		Client.executePost("TODO", objOut);
	}

	public static void sendBuildConnection() {

		Client.executeGet(targetUrl, new JSONObject());
	}
}
