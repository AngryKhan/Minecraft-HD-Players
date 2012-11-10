package net.minecraft.src;

import net.minecraft.src.HDSkinHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;
import net.minecraft.src.ModLoader;
import java.util.EnumSet;

@Mod(modid = "mod_HDSkins", name = "HD Skins", version = "4.0") public class mod_HDSkins{

	@Instance("mod_HDSkins")
	public static mod_HDSkins instance;
	
	public String getVersion() {
		return "4.0";
	}


	@PostInit
	public void modsLoaded(FMLPostInitializationEvent event) {
		KeyBindingRegistry.registerKeyBinding(new HDKeyHandler());
		HDSkinHandler.initSkins();
	}
	

	@Init
	public void load(FMLInitializationEvent event) {
		System.out.println("HD Skins v4.0 for Minecraft 1.4.2 loaded");	
	}
	

	
}