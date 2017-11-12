package com.example.tuan.myapplication.model;

import android.content.Intent;
import android.widget.ArrayAdapter;

import com.example.tuan.myapplication.ShowMessageFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Game {
	private static Player player;
	public static ArrayList<Player> players;

	public static ArrayList<TradeOffer> trades;
	private static Dice dices;
	public static String currentMesage;
	private static ArrayList<Card> deck;




	private int playerCount;
	private static int maxPlayers = 4;
	private static int pointsToWin = 10;
	public static Map map;
	public static String version = "AndroidClient 1.0 (sepgroup01)";
	public static final String protocol = "1.0";
	public static String phase ="";

	public static int radius = 110;
	public Game() {
		map = new Map();
		phase = "Start";
		trades = new ArrayList<>();

	}


	
	public Dice rollDice (Player player) {
		Random ran = new Random();
		int dice1 = ran.nextInt(6)+1;
		int dice2 = ran.nextInt(6)+1;
		return new Dice(dice1,dice2);		
	}
	
	public static boolean setPlayerId (int id) {
		if (player == null) {
			player = new Player(id);
			player.status = "Spiel starten";
			return true;
		}

		return false;
	}

	public static void addPlayer(Player p) {
		for (Player pl : players) {
			if (p.id == pl.id) {
				return;
			}
		}
		players.add(p);
	}

	public static void setColor(String color) {
		switch (color) {
			case "Rot" :
				player.setColor("#DC143C");
				break;
			case "Orange" :
				player.setColor("#ff7700");
				break;
			case "Blau" :
				player.setColor("#0000d6");
				break;
			case "Weiss" :
				player.setColor("#ffffff");
				break;
			case "Grün" :
				player.setColor("#1dc407");
				break;
			case "Braun" :
				player.setColor("#84532a");
				break;
			default:
				player.setColor("#DC143C");
				break;
		}
	}

	public static void setAmZug(int playerId , boolean amZ) {
		for (Player  p : players) {
			if (p.id == playerId) {
				p.setAmZug(amZ);
			}
		}
	}

	public static void setStatus (int playerId, String stat) {
		for (Player  p : players) {
			if (p.id == playerId) {
				p.setStatus(stat);
			}
		}
	}
	public static void dealCard(int playerId) {
		for (Player  p : players) {
			if (p.id == playerId) {
				int gen  = generateDevCard();
				p.addDevCard(deck.get(gen));
				deck.remove(gen);
			}
		}
	}

	//angebote Angenommen
	public static void handleTrading(int playerId ,  int playerId2, Cost p1Get, Cost p1Lose ) {
		Player p1 = findPlayerById(playerId);
		Player p2 = findPlayerById(playerId2);
		p1.addResource(p1Get);
		p2.addResource(p1Lose);
		p1.useResource(p1Lose);
		p2.useResource(p1Get);
	}

	public static void addResourceForPlayer (int playerId , Cost add) {
		for (Player  p : players) {
			if (p.id == playerId) {
				p.addResource(add);
			}
		}
	}
	public static void useResourceFromPlayer(int playerId, Cost res) {
		for (Player  p : players) {
			if (p.id == playerId) {
				p.useResource(res);
			}
		}
	}
	public static int generateDevCard() {
		int rando = (int) ((Math.random() * deck.size()));
		return rando;
	}

	public static void looseResources(int playerId, String type, int num) {
		Cost cost;
		switch (type) {
			case "Holz" :
				cost = new Cost(1,0,0,0,0);
				break;
			case "Lehm" :
				cost = new Cost(0,1,0,0,0);
				break;
			case "Wolle" :
				cost = new Cost(0,0,1,0,0);
				break;
			case "Getreide" :
				cost = new Cost(0,0,0,1,0);
				break;
			case "Erz" :
				cost = new Cost(0,0,0,0,1);
				break;
			default:
				cost = new Cost(0,0,0,0,0);
				break;
		}
		for (Player  p : players) {
			if (p.id == playerId) {
				p.useResource(cost);
			}
		}
	}

	public static void earnResources(int playerId, String type, int num) {
		Cost cost;
		switch (type) {
			case "Holz" :
				cost = new Cost(1,0,0,0,0);
				break;
			case "Lehm" :
				cost = new Cost(0,1,0,0,0);
				break;
			case "Wolle" :
				cost = new Cost(0,0,1,0,0);
				break;
			case "Getreide" :
				cost = new Cost(0,0,0,1,0);
				break;
			case "Erz" :
				cost = new Cost(0,0,0,0,1);
				break;
			default:
				cost = new Cost(0,0,0,0,0);
				break;
		}
		for (Player  p : players) {
			if (p.id == playerId) {
				p.addResource(cost);
			}
		}
	}


	public static void displayMessage(String message) {
		//TODO
		currentMesage = message;
		ShowMessageFragment dis = new ShowMessageFragment();
	}

	public static void setResources(int playerId, Cost res) {
		Player p  = findPlayerById(playerId);
		p.setResource(res);
	}

	public static boolean setBuilding(int playerId , String type , ArrayList<Position> positions) {
		// TODO : set Building not in Player but in Map
		//TODO : check if we could build it here
		switch (type) {
			case "Straße" :
				Tile tile1 = getTileByPosition(positions.get(0));
				Tile tile2 = getTileByPosition(positions.get(1));
				map.addRoad(new Road(tile1, tile2, playerId));
				/*
				for (Player  p : players) {
					if (p.id == playerId) {
						p.addRoad(new Road(tile1, tile2, playerId));
					}
				}
				*/
				break;
			case "Dorf" :
				Tile tile3 = getTileByPosition(positions.get(0));
				Tile tile4 = getTileByPosition(positions.get(1));
				Tile tile5 = getTileByPosition(positions.get(2));
				map.houses.add(new House(tile3, tile4,tile5, playerId));
				for (Player  p : players) {
					if (p.id == playerId) {
						p.addHouse(new House(tile3, tile4,tile5, playerId));
					}
				}
				break;
			case "Stadt" :
				Tile tile6 = getTileByPosition(positions.get(0));
				Tile tile7 = getTileByPosition(positions.get(1));
				Tile tile8 = getTileByPosition(positions.get(2));
				getPlayerById(playerId).updateHouseByTiles(tile6,tile7,tile8);
				break;
		}
		return true;
	}

	public static Tile getTileByPosition(Position pos) {
		for (Tile tile : Game.map.tiles) {
			if (tile.xPos == pos.column && tile.yPos == pos.row) {
				return tile;
			}
		}
		return null;
	}


	public static Player getPlayer() {
		return player;
	}

	public static void setRobber(int x , int y) {
		 map.robber.moveRobber(map.getTileByCoor(x,y));
	}

	public static void setDice (int dice1, int dice2 , int playerId) {
		dices = new Dice (dice1, dice2);

		Intent progressIntent = new Intent();
		progressIntent.setAction("updateRollDiceAction");
		progressIntent.putExtra("dice1",dice1);
		progressIntent.putExtra("dice2",dice2);
		progressIntent.addCategory(Intent.CATEGORY_DEFAULT);
	}

	public static String getPlayerNameById(int idSource) {
		String res = "";
		for (Player player : Game.players) {
			if (player.id == idSource) {
				res = player.getName();
			}
		}
		return res;
	}
	public static Tile tileFromJObject(JSONObject obj) throws JSONException {
		JSONObject ort = obj.getJSONObject("Ort");
		int xPos = ort.getInt("x");
		int yPos = ort.getInt("y");
		int num = obj.getInt("Zahl");
		TileType ttype;
		String type = obj.getString("Typ");
		switch (type) {
			case "Ackerland" :
				ttype = TileType.GRAIN;
				break;
			case "Hügelland" :
				ttype = TileType.BRICK;
				break;
			case "Weideland" :
				ttype = TileType.WOOL;
				break;
			case "Wald" :
				ttype = TileType.LUMBER;
				break;
			case "Gebirge" :
				ttype = TileType.ORE;
				break;
			case "Wüste" :
				ttype = TileType.DESERT;
				break;
			case "Meer" :
				ttype = TileType.SEA;
				break;
			default:
				ttype = TileType.SEA;
				break;
		}
		return new Tile(xPos,yPos,ttype, num);
	}

	public static void displayWinner(String message) {
		//TODO
	}


	public static void displayError( String mes) {
		//TODO
	}

	public static String getProtocol() {
		return protocol;
	}

	public static void displayTradeOffer(int playerId, Cost from , Cost to) {
		//TODO :
	}
	public static void addToChat(String mes, int senderId) {
		String name  = "";
		for (Player p : players) {
			if (p.id  == senderId) {
				name = p.getName();
			}
		}
		String message = name + mes;
		//TODO Send Message
	}
	public static void setName(int pId , String name) {
		for (Player  p : players) {
			if (p.id == pId) {
				p.setName(name);
			}
		}
	}

	public static Player findPlayerById(int pId) {
		for (Player p : Game.players) {
			if (p.id == pId) {
				return p;
			}

		}

		return null;
	}

	public static int getVictoryPoints(int playerId) {
		for (Player p : Game.players) {
			if (p.id == playerId) {
				return p.getVicPoint();
			}

		}
		return 0;
	}


	public static Player getPlayerById(int pId) {
		for (Player p : Game.players) {
			if (p.id == pId) {
				return p;
			}
		}
		return null;
	}

	public static Cost getPlayerResource (int playerId)
	{
		for (Player p : Game.players) {
			if (p.id == playerId) {
				return p.getResources();
			}
		}
		return null;
	}

	public static String getColor() {
		return getPlayer().playerColor;
	}

	public static String getStatus() {
		return getPlayer().getStatus();
	}

	public static JSONArray getBuildingJSONArray() throws JSONException {
		JSONArray buildRes = new JSONArray();
		for (House h : map.houses) {
			JSONObject r = new JSONObject();
			r.put("Eigentürmer", h.ownerId);
			if (h.getLevel() == 1) {
				r.put("Typ", "Dorf");
			} else {
				r.put("Typ","Stadt");
			}
			JSONArray ort = new JSONArray();
			JSONObject tile1J = new JSONObject();
			JSONObject tile2J = new JSONObject();
			JSONObject tile3J = new JSONObject();
			tile1J.put("x",h.tile1.xPos);
			tile1J.put("y",h.tile1.yPos);
			tile2J.put("x",h.tile2.xPos);
			tile2J.put("y",h.tile2.yPos);
			tile3J.put("x",h.tile3.xPos);
			tile3J.put("y",h.tile3.yPos);
			ort.put(tile1J);
			ort.put(tile2J);
			ort.put(tile3J);
			r.put("Ort", ort);
			buildRes.put(r);
		}

		for (Road r : map.roads) {
			JSONObject re = new JSONObject();
			re.put("Eigentürmer", r.ownerId);
			re.put("Typ","Strasse");

			JSONArray ort = new JSONArray();
			JSONObject tile1J = new JSONObject();
			JSONObject tile2J = new JSONObject();

			tile1J.put("x",r.tile1.xPos);
			tile1J.put("y",r.tile1.yPos);
			tile2J.put("x",r.tile2.xPos);
			tile2J.put("y",r.tile2.yPos);
			ort.put(tile1J);
			ort.put(tile2J);

			re.put("Ort", ort);
			buildRes.put(re);
		}
		return buildRes;
	}

	public static JSONArray harboursToJSON() throws JSONException {
		JSONArray allHabours = new JSONArray();

		for (Habour ha : map.habours) {
			JSONObject harb = new JSONObject();
			harb.put("Typ",ha.resource);
			JSONArray pos  = new JSONArray();
			JSONObject pos1 = new JSONObject();
			pos1.put("x", ha.tile1.xPos);
			pos1.put("y", ha.tile1.yPos);

			JSONObject pos2 = new JSONObject();
			pos2.put("x", ha.tile2.xPos);
			pos2.put("y", ha.tile2.yPos);
			pos.put(pos1);
			pos.put(pos2);
			harb.put("Ort",pos);
			allHabours.put(harb);
		}

		return allHabours;
	}

	public static JSONObject fromTileToJSONObject(Tile tile ) throws JSONException {
		JSONObject res = new JSONObject();
		JSONObject pos = new JSONObject();
		pos.put("x",tile.xPos);
		pos.put("y",tile.yPos);
		res.put("Ort",pos);
		res.put("Typ",tileTypeToGerman(tile.type));
		return res;
	}



	public static JSONObject getMapObject() throws JSONException {
		if (map ==null) {
			 map = new Map();
		}
		JSONObject res  = new JSONObject();
		JSONArray tilesArray = new JSONArray();
		for (Tile tile : map.tiles) {
			tilesArray.put(fromTileToJSONObject(tile));
		}
		for (Tile tile : map.seaTiles) {
			tilesArray.put(fromTileToJSONObject(tile));
		}
		res.put("Felder", tilesArray);
		res.put("Gebäude", getBuildingJSONArray());
		res.put("Häfen",harboursToJSON());
		JSONObject robber = new JSONObject();
		robber.put("x", Game.map.robber.currentsTile.xPos);
		robber.put("y", Game.map.robber.currentsTile.yPos);
		res.put("Räuber",robber);
		return res;

	}

	public static void setMap(JSONObject obj) throws JSONException {
		JSONArray tileArray = obj.getJSONArray("Felder");
		JSONArray buildingArray = obj.getJSONArray("Gebäude");
		JSONArray harbourArray = obj.getJSONArray("Häfen");

		ArrayList<Tile> landTiles = new ArrayList<>();
		ArrayList<Tile> seaTiles = new ArrayList<>();
		ArrayList<Road> roads = new ArrayList<>();
		ArrayList<House> houses = new ArrayList<>();
		ArrayList<Habour> habours = new ArrayList<>();
		Robber robber ;
 		for (int i = 0; i< tileArray.length(); i++ ) {
			JSONObject object = tileArray.getJSONObject(i);
			Tile tile = tileFromJObject(object);
			if (tile.type == TileType.SEA) {
				seaTiles.add(tile);
			} else {
				landTiles.add(tile);
			}
		}

		for (int i = 0 ; i< buildingArray.length(); i++) {
			JSONObject object = buildingArray.getJSONObject(i);
			String type = object.getString("Typ");
			int ownerId = object.getInt("Eigentürmer");
			JSONArray pos = object.getJSONArray("Ort");
			ArrayList<Position> positions = new ArrayList<>();

			for (int k = 0; k< pos.length(); k++ ) {
				JSONObject post = pos.getJSONObject(k);
				Position position = new Position(post.getInt("x"), post.getInt("y"));
				positions.add(position);
			}
			switch (type) {
				case "Straße" :
					//TODO : getTile not really right here, muss search on the Array just up there
					Tile tile1 = getTileByPosition(positions.get(0));
					Tile tile2 = getTileByPosition(positions.get(1));
					roads.add(new Road(tile1,tile2,ownerId));
					break;
				case "Dorf" :
					Tile tile3 = getTileByPosition(positions.get(0));
					Tile tile4 = getTileByPosition(positions.get(1));
					Tile tile5 = getTileByPosition(positions.get(2));

					houses.add(new House(tile3, tile4,tile5, ownerId));

					break;
				case "Stadt" :
					Tile tile6 = getTileByPosition(positions.get(0));
					Tile tile7 = getTileByPosition(positions.get(1));
					Tile tile8 = getTileByPosition(positions.get(2));
					House h = new House(tile6, tile7,tile8, ownerId);
					h.levelUp();
					houses.add(h);

					break;
			}

		}

		for (int i = 0 ; i< harbourArray.length(); i++ ){
			JSONObject object = harbourArray.getJSONObject(i);
			String type = object.getString("Typ");
			JSONArray ortArray  = object.getJSONArray("Ort");

			Position pos1 = new Position(ortArray.getJSONObject(0).getInt("x"),ortArray.getJSONObject(0).getInt("y"));
			Position pos2 = new Position(ortArray.getJSONObject(1).getInt("x"),ortArray.getJSONObject(1).getInt("y"));
			Cost.ResourceType tType ;
			switch (type) {
				case "Holz Hafen" :
					tType = Cost.ResourceType.WOOD;
					break;
				case "Lehm Hafen" :
					tType = Cost.ResourceType.BRICK;
					break;
				case "Wolle Hafen" :
					tType = Cost.ResourceType.WOOL;
					break;
				case "Getreide Hafen" :
					tType = Cost.ResourceType.GRAIN;
					break;
				case "Erz Hafen" :
					tType = Cost.ResourceType.ORE;
					break;
				default:
					tType = Cost.ResourceType.ANYTHING;
			}
			//TODO Why  does Habour has 2 Postion ?
			Habour hab = new Habour(tType , pos1, pos2);
			habours.add(hab);

		}
		JSONObject robberObj = obj.getJSONObject("Räuber");
		int robberX = robberObj.getInt("x");
		int robberY = robberObj.getInt("y");

		robber = new Robber(getTileByPosition(new Position(robberX,robberY)));

		Map nMap = new Map(seaTiles,landTiles,robber, roads, houses,habours);
		Game.map = nMap;
	}

	public static String tileTypeToGerman(TileType t) {
		switch (t) {
			case DESERT:
				return "Wüste";
			case LUMBER:
				return "Wald";
			case GRAIN:
				return "Ackerland";
			case ORE:
				return "Gebirge";
			case SEA:
				return "Meer";
			case WOOL:
				return "Weideland";
			case BRICK:
				return "Hügelland";
			default:
				return "Wüste";
		}
	}
	public static Map getMap() {
		return  map;
	}

	public static TradeOffer getTradeOfferById(int tid) {
		for (TradeOffer trad : trades) {
			if (trad.id == tid ) {
				return trad;
			}
		}
		return null;
	}

	public static void commandTradeOffer(TradeOffer offer) {
		Player pOffer = getPlayerById(offer.offerId);
		pOffer.addResource(offer.demand);
		pOffer.useResource(offer.supply);

		Player pTaker = getPlayerById(offer.takerId);
		pTaker.useResource(offer.demand);
		pTaker.addResource(offer.supply);

	}

	public static void updatePlayerResource(int dices){
		for (Player p : players) {
			for(House h : p.hou)

		}

	}

	public static Card.CardType drawCardFromDeck() {
		Random rand = new Random();
		Card c = deck.get(rand.nextInt(deck.size()));
		deck.remove(c);
		return c.type;
	}

	public static void handleMonopol(String res , int ownerId ) {
		Cost toOwner = new Cost();
		for (Player p  : players) {
			if (p.id != ownerId) {
				p.getResources().removeResource(res, toOwner);
			}
		}
		getPlayerById(ownerId).addResource(toOwner);
	}

}
