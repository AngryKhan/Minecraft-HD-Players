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
import vazkii.um.common.UpdateManagerMod;
import vazkii.um.common.ModConverter;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;
import net.minecraft.src.ModLoader;
import java.util.EnumSet;

@Mod(modid = "mod_HDSkins", name = "HD Skins", version = "3.1") public class mod_HDSkins{

	@Instance("mod_HDSkins")
	public static mod_HDSkins instance;
	
	public String getVersion() {
		return "3.1";
	}


	@PostInit
	public void modsLoaded(FMLPostInitializationEvent event) {
		KeyBindingRegistry.registerKeyBinding(new HDKeyHandler());
		HDSkinHandler.initSkins();
	}
	

	@Init
	public void load(FMLInitializationEvent event) {
		new mod_HDSkins.HDSkins_UpdateHandler(ModConverter.getMod(getClass()));
		System.out.println("HD Skins v3.1 for Minecraft 1.3.2 loaded");	
	}
	
	
	public class HDSkins_UpdateHandler extends UpdateManagerMod{
	
		public HDSkins_UpdateHandler(Mod m) {
			super(m);
			System.out.println("M.U.M. Loaded");	
		}
	
		@Override
		public String getModURL() {
			return "http://www.hdminecraftskins.com";
		}

		@Override
		public String getModName() {
			return "HD Skins & Capes";
		}

		@Override
		public String getUpdateURL() {
			return "http://www.hdminecraftskins.com/um/mc132.txt";
		}
		
		

	}
	
}