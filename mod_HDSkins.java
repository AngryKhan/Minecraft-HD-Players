package net.minecraft.src;

import net.minecraft.src.HDSkinHandler;
import cpw.mods.fml.common.Mod;

@Mod(modid = "mod_HDSkins", name = "HD Skins", version = "3.0")
public class mod_HDSkins extends BaseMod{

	
	public String getVersion() {
		return "3.0";
	}

	public void modsLoaded(){
		HDSkinHandler.initSkins();
	}
	
	public void load(){
	System.out.println("HD Skins for Minecraft 1.3.2 loaded");
		
	}
	

}