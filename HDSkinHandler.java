package net.minecraft.src;

import java.io.*;
import java.net.*;
import java.util.*;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.RenderEngine;

import net.minecraft.client.Minecraft;

public class HDSkinHandler {
	private static Minecraft mc1;
	private static String SkinURL;
	private static String CapeURL;
	
	private static Thread linkedThread;
	
	public static void initSkins(){
		if(linkedThread == null){
		System.out.println("HD Skins mod has started a new thread.");
		linkedThread = new SkinUpdateThread();
		}
	}
	
	
	public static void updateSkins(World world){
		List<EntityPlayer> players = world.playerEntities;
		for(EntityPlayer p : players){
			

			
		if(p.skinUrl!="http://s3.amazonaws.com/HDMinecraftSkins/" + p.username + ".png"){
				
			try{	
				
				
			SkinURL = "http://s3.amazonaws.com/HDMinecraftSkins/" + p.username + ".png";
		
			URL url = new URL(SkinURL);
		
			HttpURLConnection con = ((HttpURLConnection) url.openConnection());
			con.setConnectTimeout(1000);
			con.setRequestMethod("HEAD");
			try{
			int responseCode = con.getResponseCode();
				if  (responseCode ==200){
		
					if(!(SkinURL==p.skinUrl)){
						p.skinUrl = SkinURL;

						
						p.worldObj.obtainEntitySkin(p);
						
					
					}
				
				

				}else{
					SkinURL = "http://s3.amazonaws.com/MinecraftSkins/" + p.username + ".png";
					if(!(SkinURL==p.skinUrl)){
						p.skinUrl = SkinURL;

						p.worldObj.obtainEntitySkin(p);
						
					
					}
				}
			}catch (SocketTimeoutException ste){
				SkinURL = "http://s3.amazonaws.com/MinecraftSkins/" + p.username + ".png";
				if(!(SkinURL==p.skinUrl)){
						p.skinUrl = SkinURL;

						p.worldObj.obtainEntitySkin(p);
						
				}
			}
			}catch (IOException e){
				SkinURL = "http://s3.amazonaws.com/MinecraftSkins/" + p.username + ".png";
				if(!(SkinURL==p.skinUrl)){
						p.skinUrl = SkinURL;

						p.worldObj.obtainEntitySkin(p);
						
				}
			}
			
			
			
			p.playerCloakUrl = "http://www.hdminecraftskins.com/MinecraftCloaks/" + p.username + ".png";
			p.cloakUrl = p.playerCloakUrl;
			
			mc1 = ModLoader.getMinecraftInstance();;
			
			mc1.renderEngine.obtainImageData(p.cloakUrl, new ImageBufferDownload());
			
			
			}
		}
	}
}
