package com.example.tuan.myapplication.Server;


import com.example.tuan.myapplication.model.Card;
import com.example.tuan.myapplication.model.CommunicatorService;
import com.example.tuan.myapplication.model.Cost;
import com.example.tuan.myapplication.model.Game;
import com.example.tuan.myapplication.model.Player;
import com.example.tuan.myapplication.model.Position;
import com.example.tuan.myapplication.model.TradeOffer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.PipedOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;


//Class that processes incoming JSON and calls methods to change the MODEL 
public class HandleJSON 
{
	static Integer playerStart = 0;

	public static ArrayList<Integer> currentIds;
	
	public static void handle(JSONObject message, int playerID) throws JSONException {
		if(message.has("Hallo")) {
			handleMessageHallo(message);
		} else if (message.has("Willkommen")) {
			handleMessageWillkommen(message);
		} else if (message.has("Serverantwort")) {
			handleMessageServerantwort(message, playerID);
		} else if (message.has("Chatnachricht senden")) {
			handleMessageChatnachrichtSenden(message, playerID);
		} else if (message.has("Chatnachricht")) {
			handleMessageChatnachricht(message, playerID);
		}else if (message.has("Spieler")) {
			handleMessageSpieler(message, playerID);
		}else if (message.has("Spiel starten")) {
			handleMessageSpielStarten(message, playerID);
		} else if (message.has("Fehler")) {
			handleMessageFehler(message, playerID);
		}else if (message.has("Spiel gestartet")) {
			handleMessageSpielgestartet(message, playerID);
		}else if (message.has("Spiel beendet")) {
			handleMessageSpielBeendet(message, playerID);
		} else if (message.has("Statusupdate")) {
			handleMessageStatusupdate(message, playerID);
		} else if (message.has("Würfelwurf")) {
			handleMessageWuerfelwurf(message, playerID);
		} else if (message.has("Ertrag")) {
			handleMessageErtrag(message, playerID);
		} else if (message.has("Kosten")) {
			handleMessageKosten(message, playerID);
		} else if (message.has("R�uber versetzt")) {
			handleMessageRaeuberVersetzt(message, playerID);
		} else if (message.has("Bauvorgang")) {
			handleMessageBauvorgang(message, playerID);
		} else if (message.has("Entwicklungskarte gekauft")) {
			handleMessageEntwicklungskarteGekauft(message, playerID);
		} else if (message.has("Längste Handelsstraße")) {
			handleMessageLaengsteHandelsstrasse(message, playerID);
		} else if (message.has("Größte Rittermacht")) {
			handleMessageGroessteRittermacht(message, playerID);
		} else if (message.has("Würfeln")) {
			handleMessageWuerfeln(message, playerID);
		} else if (message.has("Karten abgeben")) {
			handleMessageKartenAbgeben(message, playerID);
		} else if (message.has("Räuber versetzen")) {
			handleMessageRaeuberVersetzen(message, playerID);
		} else if (message.has("Bauen")) {
			handleMessageBauen(message, playerID);
		} else if (message.has("Entwicklungskarte kaufen")) {
			handleMessageEntwicklungskarteKaufen(message, playerID);
		} else if (message.has("Seehandel")) {
			handleMessageSeehandel(message, playerID);
		} else if (message.has("Zug beenden")) {
			handleMessageZugBeenden(message, playerID);
		}
		//Binnenhandel
		else if (message.has("Handel anbieten")) {
			handleMessageHandelAnbieten(message, playerID);
		} else if (message.has("Handelsangebot")) {
			handleMessageHandelsangebot(message, playerID);
		} else if (message.has("Handel annehmen")) {
			handleMessageHandelAnnehmen(message, playerID);
		} else if (message.has("Handelsangebot angenommen")) {
			handleMessageHandelsangebotAngenommen(message, playerID);
		} else if (message.has("Handel abschlie�en")) {
			handleMessageHandelAbschliessen(message, playerID);
		} else if (message.has("Handel ausgeführt")) {
			handleMessageHandelAusgefuehrt(message, playerID);
		} else if (message.has("Handel abbrechen")) {
			handleMessageHandelAbbrechen(message, playerID);
		} else if (message.has("Handelsangebot abgebrochen")) {
			handleMessageHandelsangebotAbgebrochen(message, playerID);
		} 
		// Karten ausspielen
		else if (message.has("Ritter ausspielen")) {
			handleMessageRitterAusspielen(message, playerID);
		} else if (message.has("Straßenbaukarte ausspielen")) {
			handleMessageStrassenbaukarteAusspielen(message, playerID);
		} else if (message.has("Monopol")) {
			handleMessageMonopol(message, playerID);
		} else if (message.has("Erfindung")) {
			handleMessageErfindung(message, playerID);
		}
		
		
	}
	
	
	
	private static void handleMessageErfindung(JSONObject message, int playerID) throws JSONException {
		JSONObject innerObject = message.getJSONObject("Erfindung");
		if (innerObject.has("Spieler"))
		{
			JSONObject resObject = innerObject.getJSONObject("Rohstoffe");
			
			Cost res = new Cost();
			res.brick=resObject.getInt("Lehm");
			res.grain=resObject.getInt("Getreide");
			res.lumber=resObject.getInt("Holz");
			res.ore=resObject.getInt("Erz");
			res.wool=resObject.getInt("Wolle");
			
			Game.setResources(innerObject.getInt("Spieler"), res);
		}
		else
		{
			innerObject.put("Spieler", playerID);
			JSONObject objOut = new JSONObject();
			objOut.put("Erfindung", innerObject);
			
			CatanServlet.broadcast(objOut);
		}
		
	}

	private static void handleMessageMonopol(JSONObject message, int playerID) throws JSONException {
		JSONObject innerObject = message.getJSONObject("Monopol");
		//TODO Handeln Monopol
		// handled by client
		if (innerObject.has("Spieler")){
			// TODO
		}
		//handled by server
		else {
			// TODO : Take all resources from players and give the one who used monopoly card
			SendJSON.sendMonopolServer(playerID, innerObject.getString("Rohstoff"));
		}

		
	}

	private static void handleMessageStrassenbaukarteAusspielen(JSONObject message, int playerID) throws JSONException {
		JSONObject innerObject = message.getJSONObject("Straßenbaukarte ausspielen");
		//TODO : Create and Remove Card here.
		String type = message.getString();
		Card c = new Card(c);
		Game.getPlayerById(playerID).removeDevCard(c);
		Game.getPlayerById(playerID).longestRoad++;
		SendJSON.sendStatusupdate(playerID);
	}

	private static void handleMessageRitterAusspielen(JSONObject message, int playerID) throws JSONException {
		JSONObject innerObject = message.getJSONObject("Ritter ausspielen");
		Card c = new Card();
		Game.getPlayerById(playerID).removeDevCard(c);
		Game.getPlayerById(playerID).setArmyNumber(Game.getPlayerById(playerID).getArmyNumber()+1);
		SendJSON.sendStatusupdate(playerID);
	}

	private static void handleMessageHandelsangebotAbgebrochen(JSONObject message, int playerID) throws JSONException {
		JSONObject innerObject = message.getJSONObject("Handelsangebot abgebrochen");
		//TODO
		SendJSON.sendFehler(playerID,"Trade Request Denied");
		
	}

	private static void handleMessageHandelAbbrechen(JSONObject message, int playerID) throws JSONException {
		JSONObject innerObject = message.getJSONObject("Handel abgebrochen");

		
	}

	private static void handleMessageHandelAusgefuehrt(JSONObject message, int playerID) throws JSONException {
		JSONObject innerObject = message.getJSONObject("Handel ausgeführt");
		int tradeId = innerObject.getInt("Handel id");
		int offerId = innerObject.getInt("Spieler");
		int takerId = innerObject.getInt("Mitspieler");

		//TODO
		if (takerId == Game.getPlayer().id) {
			//TODO : I got the deal
		} else {
			//TODO : I didnt get the deal
		}

		
	}

	// handkled by server
	private static void handleMessageHandelAbschliessen(JSONObject message, int playerID) throws JSONException {
		JSONObject innerObject = message.getJSONObject("Handel abschließen");
		int tradeId = innerObject.getInt("Handel id");
		int otherPlayer = innerObject.getInt("Mitspieler");


		TradeOffer tOffer = Game.getTradeOfferById(tradeId);
		tOffer.setTakerId(otherPlayer);
		Game.commandTradeOffer(tOffer);

		SendJSON.sendStatusupdate(tOffer.offerId);
		SendJSON.sendStatusupdate(tOffer.takerId);
		SendJSON.sendHandelAusgefuehrt(tOffer.offerId,tOffer.takerId, tOffer.id);
	}

	private static void handleMessageHandelsangebotAngenommen(JSONObject message, int playerID) throws JSONException {
		JSONObject innerObject = message.getJSONObject("Handelsangebot angenommen");
		//TODO : Show All Angebotsannehmer
		
	}
	//handled by server
	private static void handleMessageHandelAnnehmen(JSONObject message , int playerId) throws JSONException {
		JSONObject innerObject = message.getJSONObject("Handel annehmen");
		boolean annehmen  = innerObject.getBoolean("Annehmen");
		int handelId = innerObject.getInt("Handel id");
		if (annehmen) {
			TradeOffer tradeOffer = Game.getTradeOfferById(handelId);
			SendJSON.sendHandelsangebotAngenommen(playerId, handelId);
		}
		//TODO : Alle Annhemer anzeigen und einen von denen auswählen.

	}
	//handled by client
	private static void handleMessageHandelsangebot(JSONObject message, int playerID) throws JSONException {
		JSONObject innerObject = message.getJSONObject("Handelsangebot");
		
		int tradeID = innerObject.getInt("Handel id");
		JSONObject angebotObject = innerObject.getJSONObject("Angebot");
		JSONObject nachfrageObject = innerObject.getJSONObject("Nachfrage");
		
		Cost angebot = new Cost();
		angebot.lumber=angebotObject.getInt("Holz");
		angebot.brick=angebotObject.getInt("Lehm");
		angebot.grain=angebotObject.getInt("Getreide");
		angebot.ore=angebotObject.getInt("Erz");
		angebot.wool=angebotObject.getInt("Wolle");

		Cost nachfrage = new Cost();
		nachfrage.brick=nachfrageObject.getInt("Lehm");
		nachfrage.lumber=nachfrageObject.getInt("Holz");
		nachfrage.grain=nachfrageObject.getInt("Getreide");
		nachfrage.ore=nachfrageObject.getInt("Erz");
		nachfrage.wool=nachfrageObject.getInt("Wolle");
		if (playerID != Game.getPlayer().id) {

		}

		//TODO : Show Handeln Angebot , check playerID first, OFfer shouldnt get the message, TradeId auch abschicken
		Game.displayTradeOffer(playerID, angebot, nachfrage);
	}
	//Handled by Server
	private static void handleMessageHandelAnbieten(JSONObject message, int playerID) throws JSONException {
		JSONObject innerObject = message.getJSONObject("Handel anbieten");
		JSONObject angebotObject = innerObject.getJSONObject("Angebot");
		Cost angebot= new Cost();
		angebot.lumber=angebotObject.getInt("Holz");
		angebot.brick=angebotObject.getInt("Lehm");
		angebot.grain=angebotObject.getInt("Getreide");
		angebot.ore=angebotObject.getInt("Erz");
		angebot.wool=angebotObject.getInt("Wolle");
		JSONObject nachfrageObject = innerObject.getJSONObject("Nachfrage");
		Cost nachfrage = new Cost();
		nachfrage.brick=nachfrageObject.getInt("Lehm");
		nachfrage.lumber=nachfrageObject.getInt("Holz");
		nachfrage.grain=nachfrageObject.getInt("Getreide");
		nachfrage.ore=nachfrageObject.getInt("Erz");
		nachfrage.wool=nachfrageObject.getInt("Wolle");
		TradeOffer tOffer = new TradeOffer(SendJSON.handelsID,playerID,angebot,nachfrage);
		Game.trades.add(tOffer);
		SendJSON.handelsID++;

		SendJSON.sendHandelsangebot(playerID, tOffer.id, innerObject.getJSONObject("Angebot"), innerObject.getJSONObject("Nachfrage"));


	}

	private static void handleMessageZugBeenden(JSONObject message, int playerID) throws JSONException {
		JSONObject innerObject = message.getJSONObject("Zug beenden");

		Game.setStatus(playerID, "Warten");
		int index = currentIds.indexOf(playerID);
		if (Game.phase.equals("Start")) {
			int nextPlayer;
			if (index > 0) {
				nextPlayer = index -1;
				Game.setStatus(currentIds.get(index),"Dorf bauen");
				SendJSON.sendStatusupdate(currentIds.get(index));
			} else {
				nextPlayer = index;
				Game.setStatus(currentIds.get(index),"Dorf bauen");
				Game.phase = "Start2";
				SendJSON.sendStatusupdate(currentIds.get(index));
			}
		} else if (Game.phase.equals("Start2")) {
			int nextPlayer;
			if (index == 3) {
				Game.phase = "Play";
				nextPlayer = (currentIds.get(index));
				Game.setStatus(nextPlayer,"Würfel");
				SendJSON.sendStatusupdate(currentIds.get(index));
			} else {
				index = index +1;
				nextPlayer = (currentIds.get(index));
				Game.setStatus(nextPlayer,"Dorf bauen");
				SendJSON.sendStatusupdate(currentIds.get(index));
			}
		} else {
			if (index -1 <0) {
				index = 3;
			} else {
				index --;
			}
			Game.setStatus(currentIds.get(index),"Dorf bauen");
			SendJSON.sendStatusupdate(currentIds.get(index));

		}

	}

	private static void handleMessageSeehandel(JSONObject message, int playerID) throws JSONException {
		JSONObject innerObject = message.getJSONObject("Seehandel");
		JSONObject angebotObject = innerObject.getJSONObject("angebotObject");
		JSONObject nachfrageObject = innerObject.getJSONObject("nachfrageObject");
		
		//TODO where are we checking for the right amount of resources?
		
		//check JSON for provided Resources and call looseResource Method
		Cost costObject = new Cost();
		costObject.lumber=angebotObject.getInt("Holz");
		costObject.brick=angebotObject.getInt("Lehm");
		costObject.grain=angebotObject.getInt("Getreide");
		costObject.wool=angebotObject.getInt("Wolle");
		costObject.ore=angebotObject.getInt("Erz");
		Game.useResourceFromPlayer(playerID, costObject);

		//check JSON for wanted Resources and call earnResource Method
		Cost costObject2 = new Cost();
		costObject2.lumber=nachfrageObject.getInt("Holz");
		costObject2.brick=nachfrageObject.getInt("Lehm");
		costObject2.grain=nachfrageObject.getInt("Getreide");
		costObject2.wool=nachfrageObject.getInt("Wolle");
		costObject2.ore=nachfrageObject.getInt("Erz");
		Game.addResourceForPlayer(playerID, costObject2);
		SendJSON.sendStatusupdate(playerID);
	}

	private static void handleMessageEntwicklungskarteKaufen(JSONObject message, int playerID) throws JSONException {
		JSONObject innerObject = message.getJSONObject("Entwicklungskarte kaufen");
		
		//TODO get an empty Card out of the Card-Pool, returns String of cardname
		String card =(String )Game.dealCard();
		SendJSON.sendEntwicklungskarteGekauft(playerID, card);
		
	}
	//Server Side
	private static void handleMessageBauen(JSONObject buildMessage, int playerID) throws JSONException {
		JSONObject innerObject = buildMessage.getJSONObject("Bauen");
		String type = innerObject.getString("Typ");
		String pos = innerObject.getString("Ort");
		boolean success = false;
		JSONObject[] destinationArray = (JSONObject[]) innerObject.get("Ort");
		
		Integer x1 = (Integer) destinationArray[0].get("x");
		Integer y1 = (Integer) destinationArray[0].get("y");
		Integer x2 = (Integer) destinationArray[1].get("x");
		Integer y2 = (Integer) destinationArray[1].get("y");
		Integer x3 = (Integer) destinationArray[2].get("x");
		Integer y3 = (Integer) destinationArray[2].get("y");

		Position pos1 = new Position(x1, y1);
		Position pos2 = new Position(x2, y2);
		ArrayList<Position> positions = new ArrayList<Position>();
		positions.add(pos1);
		positions.add(pos2);

		if (x3 != null ) {
			positions.add(new Position(x3,y3));
		}

		if(Game.getPlayerById(playerID).getStatus().equals("Dorf bauen"))
		{
			success = Game.setBuilding(playerID, type, positions);
			SendJSON.sendBauvorgang(playerID, type, x1,y1,x2,y2,x3,y3 );
			Game.getPlayerById(playerID).setStatus("Strasse bauen");
			SendJSON.sendStatusupdate(playerID);
		} else if (Game.getPlayerById(playerID).getStatus().equals("Strasse bauen")) {
			success = Game.setBuilding(playerID, type, positions);
			SendJSON.sendBauvorgang(playerID, type, x1,y1,x2,y2,x3,y3 );
			Game.getPlayerById(playerID).setStatus("Handeln und Bauen");
			SendJSON.sendStatusupdate(playerID);

			//TODO SendNewPlayerInTurn
		}
		else {
			Cost cost ;
				switch (type) {
					case "Dorf" :
						cost = Cost.getCostOfHouse();
						break;
					case "Straße" :
						cost = Cost.getCostOfStreet();
						break;
					default:
						cost = Cost.getCostOfUpgrade();
						break;
				}
			if (Game.getPlayerById(playerID).checkResource(cost)) {
				//success = Game.setBuilding(playerID,type, positions , cost);
				SendJSON.sendKosten(playerID, cost);
				SendJSON.sendBauvorgang(playerID, type, x1,y1,x2,y2,x3,y3 );
			} else {
				SendJSON.sendFehler(playerID, "You dont have enough resources!");
			}
		}


	}

	private static void handleMessageRaeuberVersetzen(JSONObject message, int playerID) throws JSONException {
		JSONObject innerObject = message.getJSONObject("Räuber versetzen");
		JSONObject destinationObject = (JSONObject) innerObject.get("Ort");


		//TODO playerID auf Hadneln und Bauen status setzen...Ziel auf Karten abgeben Status setzen;

		//Game.setBanditPostition (destinationObject.getInt("x"),destinationObject.getInt("y"));
		//TODO check if the Target has more six cards (returns Boolean) and then call earnMethod for the Thief and costMethod for Target
		// TODO Which resource do they loose?

		if(Game.getPlayerById(innerObject.getInt("Ziel")) != null )
		{
			//Random Cost from Player
			Cost cost =
			SendJSON.sendKosten(innerObject.getInt("Ziel"), cost);
			//rando
			Game.useResourceFromPlayer(innerObject.getInt("Ziel"),cost);
			Game.addResourceForPlayer(playerID,cost);
			SendJSON.sendStatusupdate(innerObject.getInt("Ziel"));
			SendJSON.sendStatusupdate(playerID);
		}
		else {Game.displayMessage("Spieler hat nicht genügend Karten");}
		for (Player player : Game.players) {
			if (player.getResources().getAllResources()>7) {
				Game.setStatus(player.id, "Karten wegen Räuber abgeben");
			}
		}

	}

	private static void handleMessageKartenAbgeben(JSONObject message, int playerID) throws JSONException {
		JSONObject innerObject = message.getJSONObject("Karten abgeben");
		JSONObject resourceObject = innerObject.getJSONObject("Abgeben");
		
		Cost costObject = new Cost();
		costObject.lumber=resourceObject.getInt("Holz");
		costObject.brick=resourceObject.getInt("Lehm");
		costObject.grain=resourceObject.getInt("Getreide");
		costObject.wool=resourceObject.getInt("Wolle");
		costObject.ore=resourceObject.getInt("Erz");
		
		Game.useResourceFromPlayer(playerID, costObject);
	}	

	private static void handleMessageWuerfeln(JSONObject message, int playerID) throws JSONException {
		JSONObject innerObject = message.getJSONObject("Würfeln");
		Game.getPlayerById(playerID).setStatus("Handeln oder Bauen");
		SendJSON.sendWuerfelwurf(playerID);
		SendJSON.sendStatusupdate(playerID);
	}

	private static void handleMessageGroessteRittermacht(JSONObject message, int playerID) throws JSONException {
		JSONObject innerObject = message.getJSONObject("Größte Rittermacht");
		Game.displayMessage(innerObject.getInt("Spieler") + " hat den Erfolg 'Gr��te Rittermacht' erreicht!");
	}

	private static void handleMessageLaengsteHandelsstrasse(JSONObject message, int playerID) throws JSONException {
		JSONObject innerObject = message.getJSONObject("Längste Handelsstraße");
		Game.displayMessage(innerObject.getInt("Spieler") + " hat den Erfolg 'Längste Handelsstraße' erreicht!");
	}

	private static void handleMessageEntwicklungskarteGekauft(JSONObject message, int playerID) throws JSONException {
		JSONObject innerObject = message.getJSONObject("Entwicklungskarte gekauft");
		if(playerID == innerObject.getInt("Spieler"))
		{
			Game.displayMessage(innerObject.getString("Entwicklungskarte")+" gekauft!");
			//earnCard should take card from cardpool and put it in PlayerObject
			String cardType = innerObject.getString("Entwicklungskarte");

			Card card = new Card(Card.fromStringDe(cardType));


			Game.boughtDevelopementCard(innerObject.getInt("Spieler"),card);
		}
		else
		{
			Game.displayMessage(innerObject.getInt("Spieler")+" hat eine Unbekannte Karte gekauft!");
			//earnCard should take card from cardpool and put it in PlayerObject - Parameter are playerID and Cardname
			//TODO String to Enum?
			Card card = new Card(Card.fromStringDe(innerObject.getString("Entwicklungskarte")));
			
			Game.boughtDevelopementCard(innerObject.getInt("Spieler"),card);
		}
	}

	private static void handleMessageBauvorgang(JSONObject message, int playerID) throws JSONException {
		JSONObject innerObject = message.getJSONObject("Bauvorgang");
		JSONObject buildingObject = innerObject.getJSONObject("Gebäude");
		
		JSONObject[] destinationArray = (JSONObject[]) buildingObject.get("Ort");
		
		Integer x1 = (Integer) destinationArray[0].get("x");
		Integer y1 = (Integer) destinationArray[0].get("y");
		Integer x2 = (Integer) destinationArray[1].get("x");
		Integer y2 = (Integer) destinationArray[1].get("y");
		Integer x3 = (Integer) destinationArray[2].get("y");
		Integer y3 = (Integer) destinationArray[2].get("y");
		//TODO Exception for Street (only 2Pos)
		Position pos1 = new Position(x1, y1);
		Position pos2 = new Position(x2, y2);

		ArrayList<Position> positions = new ArrayList<Position>();
		positions.add(pos1);
		positions.add(pos2);

		if (x3 != null ) {
			positions.add(new Position(x3,y3));
		}


		Game.setBuilding(buildingObject.getInt("Eigentümer"), buildingObject.getString("Typ"), positions);


	}

	private static void handleMessageRaeuberVersetzt(JSONObject message, int playerID) throws JSONException {
		JSONObject innerObject = message.getJSONObject("Räuber versetzt");
		SendJSON.sendChatnachricht(innerObject.getString("Spieler")+"robbed"+innerObject.getString("Ziel"), playerID);
		JSONObject destinationObject = innerObject.getJSONObject("Ziel");
		Game.setRobber(destinationObject.getInt("x"),destinationObject.getInt("y"));


	}

	private static void handleMessageKosten(JSONObject message, int playerID) throws JSONException {

		JSONObject innerObject = message.getJSONObject("Kosten");
		JSONObject resourceObject = innerObject.getJSONObject("Rohstoffe");
		
		Cost costObject = new Cost();
		costObject.lumber=resourceObject.getInt("Holz");
		costObject.brick=resourceObject.getInt("Lehm");
		costObject.grain=resourceObject.getInt("Getreide");
		costObject.wool=resourceObject.getInt("Wolle");
		costObject.ore=resourceObject.getInt("Erz");
		
		Game.useResourceFromPlayer(playerID, costObject);
	}

	private static void handleMessageErtrag(JSONObject message, int playerID) throws JSONException {
		JSONObject innerObject = message.getJSONObject("Ertrag");
		JSONObject resourceObject = innerObject.getJSONObject("Rohstoffe");
		
		Cost costObject = new Cost();
		costObject.lumber=resourceObject.getInt("Holz");
		costObject.brick=resourceObject.getInt("Lehm");
		costObject.grain=resourceObject.getInt("Getreide");
		costObject.wool=resourceObject.getInt("Wolle");
		costObject.ore=resourceObject.getInt("Erz");
		
		Game.addResourceForPlayer(playerID, costObject);
		
		//TODO how to get to the right PlayerObject?!
	}

	private static void handleMessageWuerfelwurf(JSONObject message, int playerID) throws JSONException {
		JSONObject innerObject = message.getJSONObject("Würfelwurf");
		JSONArray dices = innerObject.getJSONArray("Wurf");

		Game.setDice(dices.getInt(0), dices.getInt(1), innerObject.getInt("Spieler"));


		//TODO Model needs to check the Arraylist of Players and return PlayerID and Which Resource and How much of it the Player gets
		Interface.checkIfPlayerGetsResources();
		
		
		SendJSON.sendErtrag(playerID);		
	}

	private static void handleMessageStatusupdate(JSONObject message, int playerID) throws JSONException {
		JSONObject innerObject = message.getJSONObject("Statusupdate");
		JSONObject playerObject = innerObject.getJSONObject("Spieler");
		if (playerObject.has("Siegpunkte")) {
			Player p = Game.getPlayerById(playerID);
			p.setColor(playerObject.getString("Farbe"));
			p.setStatus(playerObject.getString("Status"));
			p.setName(playerObject.getString("Name"));
			p.setVicPoint(playerObject.getInt("Siegpunkte"));
			JSONObject resObject = playerObject.getJSONObject("Rohstoffe");
			Cost res = new Cost();
			res.brick=resObject.getInt("Lehm");
			res.grain=resObject.getInt("Getreide");
			res.lumber=resObject.getInt("Holz");
			res.ore=resObject.getInt("Erz");
			res.wool=resObject.getInt("Wolle");
			p.setResource(res);
			p.setArmyNumber(playerObject.getInt("Rittermacht"));
			p.setBiggestArmy(playerObject.getBoolean("Größte Rittermacht"));
			p.setLongestRoad(playerObject.getBoolean("Längste Handelstraße"));

			JSONObject cardObject = playerObject.getJSONObject("Entwicklungskarten");
			int knights = cardObject.getInt("Ritter");
			int streetBuild = cardObject.getInt("Straßenbau");
			int monopol = cardObject.getInt("Monopol");
			int invention = cardObject.getInt("Erfindung");
			int vicPoint = cardObject.getInt("Siegpunkt");

			for (int i = 0 ; i< knights; i++) {
				p.addDevCard(new Card(Card.CardType.KNIGHT));
			}
			for (int i = 0 ; i< streetBuild; i++) {
				p.addDevCard(new Card(Card.CardType.STREET_BUILD));
			}
			for (int i = 0 ; i< monopol; i++) {
				p.addDevCard(new Card(Card.CardType.MONOPOLY));
			}
			for (int i = 0 ; i< invention; i++) {
				p.addDevCard(new Card(Card.CardType.INVENTION));
			}
			for (int i = 0 ; i< vicPoint; i++) {
				p.addDevCard(new Card(Card.CardType.VICTORY_POINT));
			}

		} else {

			Player p = new Player(playerObject.getInt("id"));
			p.status = playerObject.getString("Status");
			Game.addPlayer(p);
		}
		// TODO how to set?!
		// Model needs Arraylist with PlayersObjects! Then search Array for PlayerID and overwrite the Object
	}

	private static void handleMessageSpielBeendet(JSONObject message, int playerID) throws JSONException {
		JSONObject innerObject = message.getJSONObject("Spiel beendet");
		Game.displayMessage((String)innerObject.get("Nachricht"));
		Game.displayWinner((String)innerObject.get("Sieger"));
	}

	private static void handleMessageSpielgestartet(JSONObject message, int playerID) throws JSONException {
		JSONObject innerObject = message.getJSONObject("Spiel gestartet");
		JSONObject mapObject = innerObject.getJSONObject("Karte");
		//TODO : Notify Android programm that game was started ?
		Game.setMap(mapObject);


	}

	private static void handleMessageFehler(JSONObject message, int playerID) throws JSONException {
		JSONObject innerObject = message.getJSONObject("Fehler");
		Game.displayError((String)innerObject.get("Meldung"));
	}

	private static void handleMessageSpielStarten(JSONObject message, int playerID) throws JSONException {
		playerStart++;
		if(playerStart==CatanServlet.getHashMap().size())
		{
			Player p = Game.getPlayerById(SendJSON.id);

			p.setStatus("Dorf bauen");

			//TODO : Notify Lobby Activity,
			SendJSON.sendSpielGestartet();
		}
	}

	private static void handleMessageSpieler(JSONObject message, int playerID) throws JSONException {
		JSONObject innerObject = message.getJSONObject("Spieler");
		Player p = Game.getPlayerById(innerObject.getInt("id"));
		p.setName(innerObject.getString("Name"));
		p.setColor("Farbe");
		SendJSON.sendStatusupdate(p.id);
	}

	// after receiving "ChatnachrichtSenden" Server distributes to all Clients
	private static void handleMessageChatnachricht(JSONObject message, int playerID) throws JSONException {
		JSONObject innerObject = message.getJSONObject("Chatnachricht");
		Game.addToChat((String)innerObject.getString("Nachricht"),(int) innerObject.getInt("Absender"));
	}

	//if Client hits <enter> in Chat, he sends this message
	private static void handleMessageChatnachrichtSenden(JSONObject message, int playerID) throws JSONException {
		JSONObject innerObject = message.getJSONObject("Chatnachricht senden");
		SendJSON.sendChatnachricht(innerObject.getString("Nachricht"), playerID);
	}
	
	
	private static void handleMessageServerantwort(JSONObject message, int playerID) throws JSONException {
		String innerObject = message.getString("Serverantwort");
		Game.displayMessage((String)innerObject);
	}

	private static void handleMessageWillkommen(JSONObject message) throws JSONException {
		JSONObject innerObject = message.getJSONObject("Willkommen");
		Integer playerID = innerObject.getInt("id");
		Game.setPlayerId(playerID);
		
	}
	
	private static void handleMessageHallo(JSONObject halloMessage) throws JSONException {
		// (Hallo : (Version:...,Protokoll:0.3))
		JSONObject innerObject = halloMessage.getJSONObject("Hallo");
		
		//handled by Client
		if(innerObject.has("Protokoll"))
		{
			String protocol = innerObject.getString("Protokoll");
			String version = innerObject.getString("Version");
			if (protocol.equals(Game.protocol) && version.equals(Game.version) )
			{
				SendJSON.sendHalloClient();
			}
			else
			{
				Game.displayError("Protokoll stimmt nicht überein.");
				//TODO in View, back to Connection Screen
			}
		}
		//handled by Server
		else
		{
			return;
		}
	}	
}
