package net.minecraft.src;

import net.minecraft.client.Minecraft;
import net.minecraft.src.ModLoader;



public class SkinUpdateThread extends Thread{
	
	Minecraft mc;
	
	public SkinUpdateThread(){
		setName("HD Skin Update Thread");
		mc = ModLoader.getMinecraftInstance();
		this.start();

	}
	
	public void run(){
		while(mc.running){
			mc = ModLoader.getMinecraftInstance();
			if(mc.theWorld != null)
				HDSkinHandler.updateSkins(mc.theWorld);
			try {
				sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
