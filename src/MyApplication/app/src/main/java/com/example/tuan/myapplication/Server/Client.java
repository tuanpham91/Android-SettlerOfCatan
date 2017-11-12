package com.example.tuan.myapplication.Server;

import com.example.tuan.myapplication.model.Game;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;imp

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.omg.CORBA.portable.InputStream;

import org.json.JSONException;
import org.json.JSONObject;
public class Client 
{
	public static String executePost(String targetURL, JSONObject objOut)
	{
		  HttpURLConnection connection = null;
		  String output = objOut.toString();

		  try
		  {
		    //Create connection
		    URL url = new URL(targetURL + "?playerID=" + Game.getPlayer().id);//playerID vom Model
		    connection = (HttpURLConnection) url.openConnection();
		    connection.setRequestMethod("POST");
		    connection.setRequestProperty("Content-Type","application/json");

		    //connection.setRequestProperty("Content-Length", 
		    //Integer.toString(output.getBytes().length));
		    //connection.setRequestProperty("Content-Language", "en-US");  

		    connection.setUseCaches(false);
		    connection.setDoOutput(true);

		    //Send request
		   
		    DataOutputStream wr = new DataOutputStream (connection.getOutputStream());
		    //Bufferedwriter ???
		    
		    wr.writeBytes(output);
		    wr.close();

		    //Get Response  
		    BufferedReader br = new BufferedReader (new InputStreamReader(connection.getInputStream()));
			String json = new String();
			if(br != null)
			{
				json = br.readLine();
				//json = br.toString();
				JSONObject jsonToParse = new JSONObject();
				jsonToParse = jsonToParse.fromObject(json); //Hope that's the right way
				HandleJSON.handle(jsonToParse, Game.getPlayer().id);
			}
		  } catch (IOException e) {
			  e.printStackTrace();
		  } catch (JSONException e) {
			  e.printStackTrace();
		  } catch (ProtocolException e) {
			  e.printStackTrace();
		  } catch (MalformedURLException e) {
			  e.printStackTrace();
		  } finally
		{
			if (connection != null) 
			{
				connection.disconnect();
			}
		}
		  
		  
		  //Don't mind whats below
		  
		  
		   /* InputStream is = (InputStream) connection.getInputStream();
		    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		    StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
		    String line;
		    while ((line = rd.readLine()) != null) {
		      response.append(line);
		      response.append('\r');
		    }
		    rd.close();
		    return response.toString();
		  } catch (Exception e) {
		    e.printStackTrace();
		    return null;
		  }
		  */
		  
		  
		
	}

	public static String executeGet(String targetURL, JSONObject objOut)
	{
		HttpURLConnection connection = null;
		String output = objOut.toString();

		try
		{
			//Create connection
			URL url = new URL(targetURL + "?playerID=" + com.example.tuan.myapplication.model.Game.getPlayer().id);//playerID vom Model
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type","application/json");

			//connection.setRequestProperty("Content-Length",
			//Integer.toString(output.getBytes().length));
			//connection.setRequestProperty("Content-Language", "en-US");

			connection.setUseCaches(false);
			connection.setDoOutput(true);

			//Send request

			DataOutputStream wr = new DataOutputStream (connection.getOutputStream());
			//Bufferedwriter ???

			wr.writeBytes(output);
			wr.close();

			//Get Response
			BufferedReader br = new BufferedReader (new InputStreamReader(connection.getInputStream()));
			String json = new String();
			if(br != null)
			{
				json = br.readLine();
				//json = br.toString();
				JSONObject jsonToParse = new JSONObject();

				jsonToParse = jsonToParse.fromObject(json); //Hope that's the right way
				HandleJSON.handle(jsonToParse, Game.getPlayerID);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally
		{
			if (connection != null)
			{
				connection.disconnect();
			}
		}


		//Don't mind whats below


		   /* InputStream is = (InputStream) connection.getInputStream();
		    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		    StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
		    String line;
		    while ((line = rd.readLine()) != null) {
		      response.append(line);
		      response.append('\r');
		    }
		    rd.close();
		    return response.toString();
		  } catch (Exception e) {
		    e.printStackTrace();
		    return null;
		  }
		  */



	}

}
