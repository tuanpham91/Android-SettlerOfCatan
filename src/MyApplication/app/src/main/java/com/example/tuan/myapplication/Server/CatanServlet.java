package com.example.tuan.myapplication.Server;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CatanServlet
 */
@WebServlet("/CatanServlet")
public class CatanServlet extends HttpServlet 
{
	/*
	TODO : 1. Player in Hashmap hinzufügen
	 */
	private static final long serialVersionUID = 1L;
	static String version = "Androidversion 01 (SEP Gruppe 01)";
    
	//Hashmap for storing PlayerIDs and their Array of JSONS
	private static HashMap<Integer, ArrayList<net.sf.json.JSONObject>> messageBuffer;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CatanServlet() 
    {
        super();
        messageBuffer = new HashMap<Integer, ArrayList<JSONObject>>();
    }
    
    public static Map getHashMap ()
    {
		return messageBuffer;
    }

    //UTILITY STUFF
    public static void addMessageBuffer(int playerID) 
    {
    	messageBuffer.put(playerID, new ArrayList<JSONObject>());
    }
    
    //sending to a single Client
    public static void send(JSONObject message, int playerID) 
    {
    	messageBuffer.get(playerID).add(message);
    }
    
    //sending to all Clients
    public static void broadcast(JSONObject message) 
    {
    	for(Integer i=1; i<messageBuffer.size();i++)
    	{
    		send(message, i);
    	}
    }
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //GET Call
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// case if you want to get all messages in store
		if(request.getAttribute("playerID") != null)
		{
			// looks like this:<    ...?playerID=3		>
			int playerID = (int) request.getAttribute("playerID");
			String result = "";
			ArrayList<JSONObject> array = messageBuffer.get(playerID);
			for(JSONObject msg : array)
			{
				result += "#" +  msg.toString();   
			}
		
			array.clear();
			response.getOutputStream().print(result); //Client get's JSON Array
		}
		else 
		{
			//give match Attribute if protocolls match (true/false)
		if ((boolean) request.getAttribute("match"))
		{
			
		}
		else
		{
			response.getOutputStream().print(SendJSON.sendHalloServer());
		}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//POST Call
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
				
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		response.setContentType("application/json");
		
		BufferedReader br = new BufferedReader (new InputStreamReader(request.getInputStream()));

		String line, json = "";
		while((line = br.readLine()) != null) {
			json += line;
		}
		Integer playerID = (Integer) request.getAttribute("playerID");
		JSONObject jsonToHandle = new JSONObject();
		jsonToHandle = jsonToHandle.fromObject(json);; //Sollte funktionieren!! -_-
		if (jsonToHandle.has("Hallo"))
		{
			if(!jsonToHandle.getJSONObject("Hallo").has("Protokoll"))
			{
				PrintWriter out=response.getWriter();
				net.sf.json.JSONObject jsonOut = SendJSON.sendWillkommen();
				out.print(jsonOut);
			}
		}
		else 
		{
		HandleJSON.handle(jsonToHandle, playerID);
		}
	}
}
