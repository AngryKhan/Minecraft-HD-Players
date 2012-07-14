package net.minecraft.src;

import java.io.*;
import java.net.*;
import java.util.*;
import net.minecraft.src.RenderEngine;
import net.minecraft.src.mod_HDSkins;
import net.minecraft.src.EntityPlayer;

public class getSkinUrl extends Thread {

private static EntityPlayer player;
private static String skinUrl;
private static String urname;
private static String msgTemp;

	public getSkinUrl(EntityPlayer player)
	{
		super ((new StringBuilder()).append("Thread: ").append(player.username).append("'s skin.").toString());
		this.player = player;
		if (player.hasSkinThread == false){
		this.start();
		}else{
		System.out.println((new StringBuilder()).append("Tried to create duplicate thread for ").append(urname).append("'s skin.").toString());
		}
	}
	public void run(){
	
	try{
		player.hasSkinThread = true;
		urname = player.username;
		msgTemp = (new StringBuilder()).append("Looking for ").append(urname).append("'s skin on S3.").toString();
		if(mod_HDSkins.debugmode == true){
			System.out.println(msgTemp);
		}
        skinUrl = (new StringBuilder()).append("https://s3.amazonaws.com/HDMinecraftSkins/").append(urname).append(".png").toString();
		URL url = new URL(skinUrl);
		
		HttpURLConnection con = ((HttpURLConnection) url.openConnection());
		con.setConnectTimeout(mod_HDSkins.timeout_S3);
		con.setRequestMethod("HEAD");try{
		int responseCode = con.getResponseCode();
		if  (responseCode ==200){
			//Got it
			
			player.skinUrl = skinUrl;
			player.updateCloak();
			player.worldObj.obtainEntitySkin(player);
			System.out.println(skinUrl);
			player.hasSkinThread = false;
			if(mod_HDSkins.debugmode == true){
				System.out.println((new StringBuilder()).append("Found ").append(urname).append("'s skin on S3.").toString());
			}
			player.checkedForHDSkin = true;
			interrupt();
		}
		else{
		//Skin not found on S3, try HDMinecraftSkins.com
		if(mod_HDSkins.debugmode == true){
			System.out.println((new StringBuilder()).append("Looking for ").append(urname).append("'s skin on HDMCS.").toString());
		}
			skinUrl = (new StringBuilder()).append("http://www.hdminecraftskins.com/MinecraftSkins/").append(urname).append(".png").toString();
			URL url2 = new URL(skinUrl);
		
			HttpURLConnection con2 = ((HttpURLConnection) url2.openConnection());
			con2.setConnectTimeout(mod_HDSkins.timeout_HDM);
			con2.setRequestMethod("HEAD");try{
			int responseCode2 = con2.getResponseCode();
			if  (responseCode2 ==200){
				//Got it, but not on S3. Put in a request to copy it.
				//This is to move skins to S3 as they are needed, rather than moving all of them at once.
				//New skins will automatically go to S3 and HDMinecraftskins.com
				
				player.skinUrl = skinUrl;
				player.updateCloak();
				player.worldObj.obtainEntitySkin(player);
				if(mod_HDSkins.debugmode == true){
					System.out.println(skinUrl);
				}
				player.hasSkinThread = false;
				if(mod_HDSkins.debugmode == true){
					System.out.println((new StringBuilder()).append("Found ").append(urname).append("'s skin on HDMCS.").toString());
				}
				try {
		
					URL skinMoveUrl = new URL((new StringBuilder()).append("http://www.hdminecraftskins.com/skinmove.php?user=").append(urname).append("&req=mv141").toString());
					URLConnection con3 = skinMoveUrl.openConnection();
					BufferedReader in1 = new BufferedReader(new InputStreamReader(con3.getInputStream()));
					String inputLine;
					System.out.println("Requesting skin tranfer to S3");
						while ((inputLine = in1.readLine()) != null) {
						System.out.println(inputLine);
						}
					in1.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				player.checkedForHDSkin = true;
				interrupt();
				}else{
				if(mod_HDSkins.debugmode == true){
					System.out.println("Didn't find any HD skin, using skin from minecraft.net");
				}
					skinUrl = (new StringBuilder()).append("http://s3.amazonaws.com/MinecraftSkins/").append(urname).append(".png").toString();
					
					player.skinUrl = skinUrl;
					player.updateCloak();
					player.worldObj.obtainEntitySkin(player);
					if(mod_HDSkins.debugmode == true){
						System.out.println(skinUrl);
					}
					player.hasSkinThread = false;
					player.checkedForHDSkin = true;
					interrupt();
					
				}
		
			}catch (SocketTimeoutException ste){
			if(mod_HDSkins.debugmode == true){
				System.out.println("The connection timed out, using skin from minecraft.net");
				}
				skinUrl = (new StringBuilder()).append("http://s3.amazonaws.com/MinecraftSkins/").append(urname).append(".png").toString();
				
				player.skinUrl = skinUrl;
				player.updateCloak();
				player.worldObj.obtainEntitySkin(player);
				if(mod_HDSkins.debugmode == true){
					System.out.println(skinUrl);
				}
				player.hasSkinThread = false;
				player.checkedForHDSkin = true;
				interrupt();
			}


		}
			}catch (SocketTimeoutException ste){
			if(mod_HDSkins.debugmode == true){
				System.out.println("The connection timed out, using skin from minecraft.net");
			}
				skinUrl = (new StringBuilder()).append("http://s3.amazonaws.com/MinecraftSkins/").append(urname).append(".png").toString();
				
				player.skinUrl = skinUrl;
				player.updateCloak();
				player.worldObj.obtainEntitySkin(player);
				if(mod_HDSkins.debugmode == true){
					System.out.println(skinUrl);
				}
				player.hasSkinThread = false;
				player.checkedForHDSkin = true;
				interrupt();
			}
			
	} catch (IOException e){
				if(mod_HDSkins.debugmode == true){
					System.out.println("Something unexpected went wrong, defaulting to minecraft.net");
				}
				skinUrl = (new StringBuilder()).append("http://s3.amazonaws.com/MinecraftSkins/").append(urname).append(".png").toString();
				player.skinUrl = skinUrl;
				player.updateCloak();
				player.worldObj.obtainEntitySkin(player);
				player.updateCloak();
				player.skinUrl = skinUrl;
				if(mod_HDSkins.debugmode == true){
					System.out.println(skinUrl);
				}
				player.hasSkinThread = false;
				player.checkedForHDSkin = true;
				interrupt();
	}
	player.checkedForHDSkin = true;
	player.hasSkinThread = false;
	}
}