package com.Catan;

import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

//TODO Import Model Interface!!
//call methods in Interface if Client sends JSON
//Rohstoff Object = { Holz:INT , Lehm:INT} (Beispiel)
public class SendJSON 
{
	
	//Model.Interface = new Interface;
	
	static Integer id = 0;
	
	//public void send
	
	private void sendErfindung(int playerID) {
		
		
	}

	private void sendMonopol(int playerID) {

		
	}

	private void sendStrassenbaukarteAusspielen(int playerID) {
		
		
	}

	private void sendRitterAusspielen(int playerID) {
		
		
	}

	private void sendHandelsangebotAbgebrochen(int playerID) {
		
		
	}

	private void sendHandelAbbrechen(int playerID) {
		
		
	}

	private void sendHandelAusgefuehrt(int playerID) {
		
		
	}

	private void sendHandelAbschliessen(int playerID) {
		
		
	}

	private void sendHandelsangebotAngenommen(int playerID) {
		
		
	}

	private void sendHandelAnnehmen(int playerID) {
		
		
	}

	private void sendHandelsangebot(int playerID) {
		
		
	}

	private void sendHandelAnbieten(int playerID) {
		
		
	}

	private void sendZugBeenden(int playerID)
	{
		JSONObject objOut = new JSONObject();
		objOut.put("Zug beenden", null);
	}

	private void sendSeehandel(int playerID, String angebot, Integer angebotInt, String nachfrage, Integer nachfrageInt) 
	{
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		objOut.put("Seehandel", obj1);
		
		JSONObject angebotObject = new JSONObject();
		angebotObject.put(angebot, angebotInt);
		JSONObject nachfrageObject = new JSONObject();
		nachfrageObject.put(nachfrage, nachfrageInt);
		
		obj1.put("Angebot", angebotObject);
		obj1.put("Nachfrage", nachfrageObject);
		
	}

	private void sendEntwicklungskarteKaufen(int playerID) 
	{
		JSONObject objOut = new JSONObject();
		objOut.put("Entwicklungskarte kaufen", null);
		
		Client.executePost("", objOut); 
	}

	private void sendBauen(int playerID, String type, Integer x1, Integer y1, Integer x2, Integer y2, Integer x3, Integer y3)
	{
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

		JSONArray destinationArray = new JSONArray();
		destinationArray.add(0, tile1);
		destinationArray.add(1, tile2);
		destinationArray.add(2, tile3);
		
		obj1.put("Typ", type); 
		obj1.put("Ort", destinationArray);
		
		Client.executePost("", objOut);
	}

	private void sendRaeuberVersetzen(int playerID, Integer destinationX, Integer destinationY, Integer target)
	{
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

	private void sendKartenAbgeben(int playerID) 
	{
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		objOut.put("Karten abgeben", obj1);
		obj1.put("Rohstoffe", null);	
		
		Client.executePost("", objOut);
	}

	private void sendWuerfeln(int playerID) 
	{
		JSONObject objOut = new JSONObject();
		objOut.put("Würfeln", null);
		
		Client.executePost("", objOut);		
	}

	private void sendGroessteRittermacht(int playerID) 
	{
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		objOut.put("Größte Rittermacht", obj1);
		obj1.put("Spieler", playerID);
		
		CatanServlet.broadcast(objOut);
	}

	private void sendLaengsteHandelsstrasse(int playerID) 
	{
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		objOut.put("Längste Handelsstraße", obj1);
		obj1.put("Spieler", playerID);
		
		CatanServlet.broadcast(objOut);
	}

	private void sendEntwicklungskarteGekauft(int playerID, String karte) 
	{
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		objOut.put("Entwicklungskarte gekauft", obj1);
		obj1.put("Spieler", playerID);
		obj1.put("Entwicklungskarte", karte);
		
		CatanServlet.broadcast(objOut);
	}

	private void sendBauvorgang(String type) 
	{
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		objOut.put("Bauvorgang", obj1);
		obj1.put("Gebäude", type);	
		
		CatanServlet.broadcast(objOut);	
	}

	private void sendRaeuberVersetzt(int playerID, Integer destinationX, Integer destinationY) 
	{
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		objOut.put("Räuber versetzt", obj1);
		obj1.put("Spieler", playerID);
		
		JSONObject destinationObject = new JSONObject();
		obj1.put("Ort", destinationObject);
		destinationObject.put("x", destinationX);
		destinationObject.put("y", destinationY);
		
		CatanServlet.send(objOut, playerID);

	}

	private void sendKosten(int playerID) 
	{
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		objOut.put("Kosten", obj1);
		obj1.put("Spieler", playerID);	
		
		//TODO add RoshtoffObject
		obj1.put("Rohstoffe", null);
		CatanServlet.send(objOut, playerID);
	}

	private void sendErtrag(int playerID) 
	{
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		objOut.put("Ertrag", obj1);
		obj1.put("Spieler", playerID);
		
		//TODO add RohstoffObject
		obj1.put("Rohstoffe", null);	
		CatanServlet.send(objOut, playerID);
	}

	static void sendWuerfelwurf(int playerID) 
	{
		//TODO wuerfeln richtig machen
		Integer wuerfel1 = (int) ((Math.random()*5)+1);
		Integer wuerfel2 = (int) ((Math.random()*5)+1);
		int [] wuerfel = {wuerfel1,wuerfel2};
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		objOut.put("Würfelwurf", obj1);
		obj1.put("Spieler", playerID);		
		obj1.put("Wurf", wuerfel);
		
		CatanServlet.broadcast(objOut);
	}

	private void sendStatusupdate(int playerID) 
	{
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		JSONObject playerObject = new JSONObject();
		objOut.put("Statusupdate", obj1);
		obj1.put("Spieler", playerObject);
		playerObject.put("id", playerID);
		playerObject.put("Farbe", Interface.getFarbe());
		playerObject.put("Name", Interface.getName());
		
		//TODO where is the Status from? Server dictates the rotation?
		playerObject.put("Status", Interface.getStatus());
		playerObject.put("Siegpunkte", Interface.getVictoryPoints);
		playerObject.put("Rohstoffe", Interface.getResources);
		
		//Rittermacht tells only how many Ritterkarten have been played
		playerObject.put("Rittermacht", null);
		//insert EntwicklungskartenObject
		playerObject.put("Entiwcklungskarten", null);
		
		//entweder false oder PlayerID?
		playerObject.put("Größte Rittermacht", false);
		playerObject.put("Längste Handelsstraße", false);
		
		CatanServlet.send(objOut, playerID);
	}

	private void sendSpielBeendet(int playerID, String message) 
	{
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		objOut.put("Spiel beendet", obj1);
		obj1.put("Spieler", playerID);
		obj1.put("Nachricht", message);
		
		CatanServlet.broadcast(objOut);
	}

	static void sendSpielGestartet() 
	{
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		objOut.put("Spiel gestartet", obj1);
		obj1.put("Karte", Interface.getMap); //TODO connect to Interface
		
		CatanServlet.broadcast(objOut);
	}

	static void sendFehler(String fehler) 
	{
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		objOut.put("Fehler", obj1);
		obj1.put("Meldung", fehler);
		
		Client.executePost("targetURL", objOut);
		
	}

	private void sendSpielStarten(int playerID) 
	{
		
		
	}

	private void sendSpieler(int playerID) {
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		objOut.put("Spieler", obj1);
		obj1.put("Name", Interface.getName());
		obj1.put("Farbe", Interface.getColor());
		
	}

	static void sendChatnachricht(String message, int playerID) 
	{
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		objOut.put("Chatnachricht", obj1);
		obj1.put("Absender", playerID);
		obj1.put("Nachricht", message);
		
		CatanServlet.broadcast(objOut);
		//Client.executePost("targetURL"+"?playerID=" + playerID, objOut);
	}

	private void sendChatnachrichtSenden(String message, int playerID) 
	{
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		objOut.put("Chatnachricht senden", obj1);
		obj1.put("Nachricht", message);
		
		Client.executePost("targetURL"+"?playerID=" + playerID, objOut);
	}
	
	private void sendServerantwortElse (int playerID, String message)
	{
		JSONObject objOut = new JSONObject();
		objOut.put("Serverantwort", message);
		
		CatanServlet.send(objOut, playerID);
	}

	private void sendServerantwortOK(int playerID) 
	{
		JSONObject objOut = new JSONObject();
		objOut.put("Serverantwort", "OK");
		
		CatanServlet.send(objOut, playerID);

	}

	static void sendWillkommen() 
	{
		id++;
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		objOut.put("Willkommen", obj1);
		obj1.put("id", id);
		
		Client.executePost("targetURL", objOut);
	}

	static void sendHalloServer() 
	{
		// (Hallo : (Version:...,Protokoll:0.3))
		JSONObject objOut = new JSONObject();
		JSONObject obj1 = new JSONObject();
		objOut.put("Hallo", obj1);
		obj1.put("Version", "AndroidServer 0.3 (sepgroup01)");
		obj1.put("Protokoll", "0.3");
		
		CatanServlet.send(objOut, playerID);
	}
	static void sendHalloClient()
	{
		// (Hallo : (Version:...,Protokoll:0.3))
				JSONObject objOut = new JSONObject();
				JSONObject obj1 = new JSONObject();
				objOut.put("Hallo", obj1);
				obj1.put("Version", "AndroidClient 0.3 (sepgroup01)");
				Client.executePost("targetURL", objOut);
	}
}
