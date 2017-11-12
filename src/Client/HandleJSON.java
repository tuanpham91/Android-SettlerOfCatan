
import java.io.PrintWriter;
import java.util.Iterator;

import net.sf.json.JSONObject;

//Class that processes incoming JSON and calls methods to change the MODEL 
public class HandleJSON 
{
	static Integer playerStart = 0;
	
	
	public static void handle(JSONObject message, int playerID) {
		if(message.containsKey("Hallo")) {
			handleMessageHallo(message);
		} else if (message.containsKey("Willkommen")) {
			handleMessageWillkommen(message);
		} else if (message.containsKey("Serverantwort")) {
			handleMessageServerantwort(message, playerID);
		} else if (message.containsKey("Chatnachricht senden")) {
			handleMessageChatnachrichtSenden(message, playerID);
		} else if (message.containsKey("Chatnachricht")) {
			handleMessageChatnachricht(message, playerID);
		}else if (message.containsKey("Spieler")) {
			handleMessageSpieler(message, playerID);
		}else if (message.containsKey("Spiel starten")) {
			handleMessageSpielStarten(message, playerID);
		} else if (message.containsKey("Fehler")) {
			handleMessageFehler(message, playerID);
		}else if (message.containsKey("Spiel gestartet")) {
			handleMessageSpielgestartet(message, playerID);
		}else if (message.containsKey("Spiel beendet")) {
			handleMessageSpielBeendet(message, playerID);
		} else if (message.containsKey("Statusupdate")) {
			handleMessageStatusupdate(message, playerID);
		} else if (message.containsKey("W�rfelwurf")) {
			handleMessageWuerfelwurf(message, playerID);
		} else if (message.containsKey("Ertrag")) {
			handleMessageErtrag(message, playerID);
		} else if (message.containsKey("Kosten")) {
			handleMessageKosten(message, playerID);
		} else if (message.containsKey("R�uber versetzt")) {
			handleMessageRaeuberVersetzt(message, playerID);
		} else if (message.containsKey("Bauvorgang")) {
			handleMessageBauvorgang(message, playerID);
		} else if (message.containsKey("Entwicklungskarte gekauft")) {
			handleMessageEntwicklungskarteGekauft(message, playerID);
		} else if (message.containsKey("L�ngste Handelsstra�e")) {
			handleMessageLaengsteHandelsstrasse(message, playerID);
		} else if (message.containsKey("Gr��te Rittermacht")) {
			handleMessageGroessteRittermacht(message, playerID);
		} else if (message.containsKey("W�rfeln")) {
			handleMessageWuerfeln(message, playerID);
		} else if (message.containsKey("Karten abgeben")) {
			handleMessageKartenAbgeben(message, playerID);
		} else if (message.containsKey("R�uber versetzen")) {
			handleMessageRaeuberVersetzen(message, playerID);
		} else if (message.containsKey("Bauen")) {
			handleMessageBauen(message, playerID);
		} else if (message.containsKey("Entwicklungskarte kaufen")) {
			handleMessageEntwicklungskarteKaufen(message, playerID);
		} else if (message.containsKey("Seehandel")) {
			handleMessageSeehandel(message, playerID);
		} else if (message.containsKey("Zug beenden")) {
			handleMessageZugBeenden(message, playerID);
		}
		//Binnenhandel
		else if (message.containsKey("Handel anbieten")) {
			handleMessageHandelAnbieten(message, playerID);
		} else if (message.containsKey("Handelsangebot")) {
			handleMessageHandelsangebot(message, playerID);
		} else if (message.containsKey("Handel annehmen")) {
			handleMessageHandelAnnehmen(message, playerID);
		} else if (message.containsKey("Handelsangebot angenommen")) {
			handleMessageHandelsangebotAngenommen(message, playerID);
		} else if (message.containsKey("Handel abschlie�en")) {
			handleMessageHandelAbschliessen(message, playerID);
		} else if (message.containsKey("Handel ausgef�hrt")) {
			handleMessageHandelAusgefuehrt(message, playerID);
		} else if (message.containsKey("Handel abbrechen")) {
			handleMessageHandelAbbrechen(message, playerID);
		} else if (message.containsKey("Handelsangebot abgebrochen")) {
			handleMessageHandelsangebotAbgebrochen(message, playerID);
		} 
		// Karten ausspielen
		else if (message.containsKey("Ritter ausspielen")) {
			handleMessageRitterAusspielen(message, playerID);
		} else if (message.containsKey("Stra�enbaukarte ausspielen")) {
			handleMessageStrassenbaukarteAusspielen(message, playerID);
		} else if (message.containsKey("Monopol")) {
			handleMessageMonopol(message, playerID);
		} else if (message.containsKey("Erfindung")) {
			handleMessageErfindung(message, playerID);
		}
		
		
	}
	
	
	
	private static void handleMessageErfindung(JSONObject message, int playerID) {
		JSONObject innerObject = message.getJSONObject("Erfindung");

		
	}

	private static void handleMessageMonopol(JSONObject message, int playerID) {
		JSONObject innerObject = message.getJSONObject("Monopol");

		
	}

	private static void handleMessageStrassenbaukarteAusspielen(JSONObject message, int playerID) {
		JSONObject innerObject = message.getJSONObject("Strassenbaukarte ausspielen");

		
	}

	private static void handleMessageRitterAusspielen(JSONObject message, int playerID) {
		JSONObject innerObject = message.getJSONObject("Ritter ausspielen");

		
	}

	private static void handleMessageHandelsangebotAbgebrochen(JSONObject message, int playerID) {
		JSONObject innerObject = message.getJSONObject("Handelsangebot abgebrochen");

		
	}

	private static void handleMessageHandelAbbrechen(JSONObject message, int playerID) {
		JSONObject innerObject = message.getJSONObject("Handel abgebrochen");

		
	}

	private static void handleMessageHandelAusgefuehrt(JSONObject message, int playerID) {
		JSONObject innerObject = message.getJSONObject("Handel ausgef�hrt");

		
	}

	private static void handleMessageHandelAbschliessen(JSONObject message, int playerID) {
		JSONObject innerObject = message.getJSONObject("Handel abschlie�en");

		
	}

	private static void handleMessageHandelsangebotAngenommen(JSONObject message, int playerID) {
		JSONObject innerObject = message.getJSONObject("Handelsangebot angenommen");

		
	}

	private static void handleMessageHandelAnnehmen(JSONObject message, int playerID) {
		JSONObject innerObject = message.getJSONObject("Handel annehmen");
		
		
	}

	private static void handleMessageHandelsangebot(JSONObject message, int playerID) {
		JSONObject innerObject = message.getJSONObject("Handelsangebot");

		
	}

	private static void handleMessageHandelAnbieten(JSONObject message, int playerID) {
		JSONObject innerObject = message.getJSONObject("Anbieten");

		
	}

	private static void handleMessageZugBeenden(JSONObject message, int playerID) {
		JSONObject innerObject = message.getJSONObject("Zug beenden");

		
	}

	private static void handleMessageSeehandel(JSONObject message, int playerID) {
		JSONObject innerObject = message.getJSONObject("Seehandel");

		
	}

	private static void handleMessageEntwicklungskarteKaufen(JSONObject message, int playerID) {
		JSONObject innerObject = message.getJSONObject("Entwicklungskarte kaufen");

		
	}

	private static void handleMessageBauen(JSONObject buildMessage, int playerID) {
		JSONObject innerObject = buildMessage.getJSONObject("Bauen");
		String type = innerObject.getString("Typ");
		String pos = innerObject.getString("Ort");
		
		if(type == "City")
		{
			//City city = new City(pos);
			//model.buildCity(pos);
			//... create answer message here
			//CatanServlet.send();
		}
		else if (type == "Settlement")
		{
			//s.o.
		}
		else if (type == "Road")
		{
			//s.o.
		}
		else
		{
			SendJSON.sendFehler("Wrong Building Type");
		}
		
	}

	private static void handleMessageRaeuberVersetzen(JSONObject message, int playerID) {
		JSONObject innerObject = message.getJSONObject("R�uber versetzen");

		
	}

	private static void handleMessageKartenAbgeben(JSONObject message, int playerID) {
		JSONObject innerObject = message.getJSONObject("Abgeben");
		
		
	}

	private static void handleMessageWuerfeln(JSONObject message, int playerID) {
		JSONObject innerObject = message.getJSONObject("W�rfeln");
		//Server gets this message, sends Wuerfelwurf
		//check if PLayer is supposed to roll the dice??
		SendJSON.sendWuerfelwurf(playerID);
		
	}

	private static void handleMessageGroessteRittermacht(JSONObject message, int playerID) {
		JSONObject innerObject = message.getJSONObject("Gr��te Rittermacht");
		//chekc if Player has biggest Army
		//Interface.displayMessage(innerObject.getInt("Spieler") + " hat den Erfolg 'Gr��te Rittermacht' erreicht!")

		
	}

	private static void handleMessageLaengsteHandelsstrasse(JSONObject message, int playerID) {
		JSONObject innerObject = message.getJSONObject("L�ngste Handelsstra�e");
		//check if Player has longest Road
		//Interface.displayMessage(innerObject.getInt("Spieler") + " hat den Erfolg 'L�ngste Handelsstrasse' erreicht!")
	}

	private static void handleMessageEntwicklungskarteGekauft(JSONObject message, int playerID) {
		JSONObject innerObject = message.getJSONObject("Entwicklungskarte gekauft");
		if(playerID == innerObject.getInt("Spieler"))
		{
			//Interface.displayMessage(innerObject.getString("Entwicklungskarte")+" gekauft!")
		}
		else
		{
			//Interface.displayMessage(innerObject.getInt("Spieler")+" hat eine Unbekannte Karte gekauft!")

		}
		
	}

	private static void handleMessageBauvorgang(JSONObject message, int playerID) {
		JSONObject innerObject = message.getJSONObject("Bauvorgang");
		//TODO Object Geb�ude nachschauen. add Methods
		
	}

	private static void handleMessageRaeuberVersetzt(JSONObject message, int playerID) {
		JSONObject innerObject = message.getJSONObject("R�uber versetzt");
		SendJSON.sendChatnachricht(innerObject.getString("Spieler")+"robbed"+innerObject.getString("Ziel"), playerID);
		JSONObject destinationObject = innerObject.getJSONObject("Ziel");
		//Interface.setRaeuber(destinationObject.getInt("x"),destinationObject.getInt("y"));
	}

	private static void handleMessageKosten(JSONObject message, int playerID) {
		JSONObject innerObject = message.getJSONObject("Kosten");
		//TODO check if Player looses Resources
		//Interface.looseResource(innerObject.get("Rohstoffe"));
		
	}

	private static void handleMessageErtrag(JSONObject message, int playerID) {
		JSONObject innerObject = message.getJSONObject("Ertrag");
		//TODO check if Player gets Resources? Serverside or Modelside?
		//Interface.earnResources(innerObject.get("Rohstoffe"));

		
	}

	private static void handleMessageWuerfelwurf(JSONObject message, int playerID) {
		JSONObject innerObject = message.getJSONObject("W�rfelwurf");
		//Interface.Wuerfel(innerObject.get("Wurf"), innerObject.getInt("Spieler"))
		// Wurf is an IntArray!- Cast?
		
	}

	private static void handleMessageStatusupdate(JSONObject message, int playerID) {
		JSONObject innerObject = message.getJSONObject("Statusupdate");
		JSONObject playerObject = innerObject.getJSONObject("Spieler");
		
		
		
	}

	private static void handleMessageSpielBeendet(JSONObject message, int playerID) {
		JSONObject innerObject = message.getJSONObject("Spiel beendet");
		//Interface.displayMessage(innerObject.get("Nachricht"));
		//Interface.displayWinner(innerObject.get("Sieger"));
		
	}

	private static void handleMessageSpielgestartet(JSONObject message, int playerID) 
	{
		JSONObject innerObject = message.getJSONObject("Spiel gestartet");
		// Interface.setMap(innerObject.get("Karte"));

		
	}

	private static void handleMessageFehler(JSONObject message, int playerID) 
	{
		JSONObject innerObject = message.getJSONObject("Fehler");
		// Interface.displayFehler(innerObject.get("Meldung"))
		
	}

	private static void handleMessageSpielStarten(JSONObject message, int playerID) 
	{
		if(playerStart==CatanServlet.getHashMap().size())
		{
			//START GAME
			//get Infos
			SendJSON.sendSpielGestartet();
		}
		
	}

	private static void handleMessageSpieler(JSONObject message, int playerID) 
	{
		JSONObject innerObject = message.getJSONObject("Spieler");
		//Model.setName(innerObject.get("Name"))
		//Model.setFarbe(innerObject.get("Farbe"))

		//F�r jeden Spieler einzeln!
	}

	// after receiving "ChatnachrichtSenden" Server distributes to all Clients
	private static void handleMessageChatnachricht(JSONObject message, int playerID) 
	{
		JSONObject innerObject = message.getJSONObject("Chatnachricht");
		// change Chat --> Interface.addToChat(innerObject.getString("Nachricht"), innerObject.getInt("Absender"));
	}

	//if Client hits <enter> in Chat, he sends this message
	private static void handleMessageChatnachrichtSenden(JSONObject message, int playerID) {
		JSONObject innerObject = message.getJSONObject("Chatnachricht senden");
		SendJSON.sendChatnachricht(innerObject.getString("Nachricht"), playerID);
	}
	
	
	private static void handleMessageServerantwort(JSONObject message, int playerID) {
		JSONObject innerObject = message.getJSONObject("Serverantwort");
		// Interface.displayServerMessage
		// eventuell im Chat darstellen
		//TODO was passiert bei "OK"?
		
		
	}

	private static void handleMessageWillkommen(JSONObject message) {
		JSONObject innerObject = message.getJSONObject("Willkommen");
		Integer playerID = innerObject.getInt("id");
		//Interface.setPlayerID(playerID);
	}

	
	
	private static void handleMessageHallo(JSONObject halloMessage) {
		// (Hallo : (Version:...,Protokoll:0.3))
		JSONObject innerObject = halloMessage.getJSONObject("Hallo");
		if(innerObject.containsKey("Protokoll"))
		{
			String protokoll = innerObject.getString("Protokoll");
		
			if (protokoll == Interface.getProtokoll)
			{
				SendJSON.sendHalloClient();
				SendJSON.sendWillkommen();
			}
			else
			{
				SendJSON.sendFehler("Version uncompatible");
			}
		}
		else
		{
			SendJSON.sendWillkommen();
		}
		
		

	}	
	
	
	
	
	
	
	
	
	
	
	
	public JSONObject StringToJSON(String string) 
	{
		return JSONObject.fromObject(string);
	}
	
	
	public static void parse (JSONObject obj)
	{
		Iterator it = obj.keys(); //get all the keys
		String text ="";
		while (it.hasNext())
		{
			String key = (String) it.next();			//get key
			JSONObject o = (JSONObject) obj.get(key);	//get value (here its another JSONObject!!!)
			
			
			// depending on the TYPE call INTERFACE methods to change the MODEL
			switch (key)
			{
			
				//Verbindungsaufbabu
			
				case "Hallo":
					
					//TODO CALL METHOD TO SEND "HALLO"
					
					if (o.get("Version")==CatanServlet.version)
					{
						break; //continue
					}
					else 
					{
						// cut Connection
					}
					break;
				case "Willkommen":
					break;
				
				case "Serverantwort":
					break;
					
				case "Chatnachricht senden":
					
					text = (String) o.get("Nachricht");
					
					//TODO Interface.addToChat(text);
					
					/*
					PrintWriter out=response.getWriter();
					JSONObject jsonOut = new JSONObject();
					JSONObject jsonOut1 = new JSONObject();
					
					jsonOut1.put("Absender",<ID?>);
					jsonOut1.put("Nachricht",text);
					
					jsonOut.put("Chatnachricht", jsonOut1);
					out.print(jsonOut);
					*/
					
					break;
				// Chat to Clients
				case "Chatnachricht":
					
					text = (String) o.get("Absender") + " : " + (String) o.get("Nachricht");
					
					// Interface.addToChat(text);
					
					break;
					
				//Config & Gamestart
					
				case "Spieler":
					// Interface.setPlayerName(o.get("Name"));
					if (o.get("Farbe")!="Rot" || o.get("Farbe")!="Orange" || o.get("Farbe")!="Blau" || o.get("Farbe")!="Wei�" )
					{
						/*
						PrintWriter out=response.getWriter();
						JSONObject jsonOut = new JSONObject();
						JSONObject jsonOut1 = new JSONObject();
						
						jsonOut1.put("Meldung","Farbe ung�ltig");
						
						jsonOut.put("Fehler", jsonOut1);
						out.print(jsonOut);
						*/
					}
					else
					{
						// Interface.setPlayerColor(o.get("Farbe"));
					}
					
					break;
				case "Spiel starten":
					//
					
					break;
					
				//case "Fehler":
				//	break;
					
				case "Spiel gestartet":
					
					/*
					if(Interface.getPlayerList==4)
					{
						PrintWriter out=response.getWriter();
						JSONObject jsonOut = new JSONObject();
						JSONObject jsonOut1 = new JSONObject();
					
						jsonOut1.put("Karte",<Model --> Object of Map>);
					
						jsonOut.put("Spiel gestartet", jsonOut1);
						out.print(jsonOut);
					}
					*/
					
					/* Objekt Karte:
					 
					 JSONObject karte = new JSONObject();
					 karte.put ("Felder",[Array von feld])
					 karte.put ("Geb�ude",[Array von building])
					 karte.put ("H�fen",[Array von harbour])
					 karte.put ("R�uber", "") Buchstabe des Ortes
					*/
					
					break;
				case "Spiel beendet":
					
					/*
					if(Interface.getVictoryPoint==?)
					{
						PrintWriter out=response.getWriter();
						JSONObject jsonOut = new JSONObject();
						JSONObject jsonOut1 = new JSONObject();
					
						jsonOut1.put("Nachricht","Sieger ist:");
						jsonOut1.put("Sieger",<ID>);
					
						jsonOut.put("Spiel beendet", jsonOut1);
						out.print(jsonOut);
					}
					*/
					
					
					break;
					
				//TODO Statusupdate of a Player
				case "Statusupdate":
					
					/*
					PrintWriter out=response.getWriter();
					JSONObject jsonOut = new JSONObject();
					JSONObject jsonOut1 = new JSONObject();
					
					jsonOut1.put("Spieler",<PlayerObject>);
					
					jsonOut.put("Statusupdate", jsonOut1);
					out.print(jsonOut);
					*/
					
					break;
				// Diceeyes to Clients
				case "W�rfelwurf":
					
					/*
					PrintWriter out=response.getWriter();
					JSONObject jsonOut = new JSONObject();
					JSONObject jsonOut1 = new JSONObject();
					
					jsonOut1.put("Spieler",<ID>);
					jsonOut1.put("Wurf",[Math.random*5+1,Math.random*5+1])
					
					jsonOut.put("Fehler", jsonOut1);
					out.print(jsonOut);
					*/
					//TODO put in List to send when @GET
					
					break;
				//TODO Resources Player gets
				case "Ertrag":
					
					/*
					PrintWriter out=response.getWriter();
					JSONObject jsonOut = new JSONObject();
					JSONObject jsonOut1 = new JSONObject();
					
					jsonOut1.put("Spieler",<ID>);
					jsonOut1.put("Rohstoffe",<RohstoffObject>)
					
					jsonOut.put("Ertrag", jsonOut1);
					out.print(jsonOut);
					
					servlet.broadcast(jsonOut);
					*/
					
					break;
				//TODO Resources Player looses
				case "Kosten":
					
					/*
					PrintWriter out=response.getWriter();
					JSONObject jsonOut = new JSONObject();
					JSONObject jsonOut1 = new JSONObject();
					
					jsonOut1.put("Spieler",<ID>);
					jsonOut1.put("Rohstoffe",<RohstoffObject>)
					
					jsonOut.put("Kosten", jsonOut1);
					
					out.print(jsonOut);
					*/
					
					break;
				//TODO To all Players
				case "R�uber versetzt":
					
					/*
					PrintWriter out=response.getWriter();
					JSONObject jsonOut = new JSONObject();
					JSONObject jsonOut1 = new JSONObject();
					
					jsonOut1.put("Spieler",<ID>);
					jsonOut1.put("Ort","");
					jsonOut1.put("Ziel",<ID>);
					
					jsonOut.put("Ertrag", jsonOut1);
					out.print(jsonOut);
					*/
					
					break;
				//TODO to all Players
				case "Bauvorgang":
					
					/*
					PrintWriter out=response.getWriter();
					JSONObject jsonOut = new JSONObject();
					JSONObject jsonOut1 = new JSONObject();
					
					jsonOut1.put("Geb�ude",<Geb�udeObject>);
					
					jsonOut.put("Bauvorgang", jsonOut1);
					out.print(jsonOut);
					*/
					
					break;
				//TODO to all Players
				case "Entwicklungskarte gekauft":
					
					/*
					PrintWriter out=response.getWriter();
					JSONObject jsonOut = new JSONObject();
					JSONObject jsonOut1 = new JSONObject();
					
					jsonOut1.put("Spieler",<ID>);
					jsonOut1.put("Entwicklungskarte","");
					
					jsonOut.put("Entwicklungskarte gekauft", jsonOut1);
					out.print(jsonOut);
					*/
					
					break;
				//TODO to all Players
				case "L�ngste Handelstra�e":
					break;
				//TODO to all Players
				case "Gr��te Rittermacht":
					break;
					
				//Client to Server
					
				case "W�rfeln":
					break;
				case "Karten abgeben":
					break;
				case "R�uber versetzen":
					break;
				case "Bauen":
					break;
				case "Entwicklungskarten kaufen":
					break;
				case "Seehandel":
					break;
					
				//Trade with other Players
					
				//TODO Offer Trade to Server
				case "Handel anbieten":
					break;
				//TODO Server sends Trade to Players
				case "Handelsangebot":
					break;
				//TODO Player wants the Trade --> Server
				case "Handel annehmen":
					break;
				//TODO Server sends the Info who wants that Trade
				case "Handelsangebot angenommen":
					break;
				//TODO active Player decides which Player gets the deal
				case "Handel abschlie�en":
					break;
				//TODO Server sends Info
				case "Handel ausgef�hrt":
					break;
				
				//TODO
				case "Handel abbrechen":
					break;
				//TODO Server to Players
				case "Handelsangebot abgebrochen":
					break;
					
				//playing cards
					
				case "Ritter ausspielen":
					break;
				case "Stra�enkarte ausspielen":
					break;
				case "Monopol":
					break;
				case "Erfindung":
					break;
			}
			
		}
		
		
	}
}
