package net.minecraft.src;

import net.minecraft.src.vazkii.updatemanager.*;
import net.minecraft.client.Minecraft;

public class mod_HDSkins extends BaseMod implements IUpdateManager
{
  
  @MLProp(name = "timeout_S3", info = "Skin check timeout(in milliseconds) for s3.amazon.com. max 15000, min 150, default 500", max = 15000, min = 150) public static int timeout_S3 = 500;
  @MLProp(name = "timeout_HDM", info = "Skin check timeout(in milliseconds) for hdminecraftskins.com. max 15000, min 150, default 700", max = 15000, min = 150) public static int timeout_HDM = 700;
  @MLProp(name = "mode", info = "Mode; should be \"thread\" or \"classic\". thread is the default mode as of 1.4.1, classic is the slower but more reliable mode used in 1.3 and earlier") public static String mode = "thread";
  @MLProp(name = "capes", info = "Set to true to enable HD capes, false to allow only Mojang capes.") public static boolean capes = true;
  @MLProp(name = "debug", info = "Enables debug output, true or false.") public static boolean debugmode = false;
  private static boolean hasTicked = false;
  
  
  public String getVersion()
  {
    return "by AngryKhan. Version 1.4.2";
  }

  public String getUpdateURL() {
    return "http://www.hdminecraftskins.com/um/updatemanager.txt";
  }

  public String getModURL() {
    return "http://www.hdminecraftskins.com/e107/";
  }

  public ModType getModType() {
    return ModType.UNDEFINED;
  }

  public String getModName() {
    return "HD Player Skins";
  }

  public String getChangelogURL() {
    return "http://www.hdminecraftskins.com/um/changelog.txt";
  }

  public void load() {
    UMCore.addMod(this);
  }
  
  public boolean onTickInGame(float f, Minecraft minecraft){
		if(!hasTicked){
			
				if(mode == "thread"){
				minecraft.thePlayer.addChatMessage("§1[HD Skins]§4 HD Skins v1.4.2, thread mode enabled.");
				}
				if(mode == "classic"){
				minecraft.thePlayer.addChatMessage("§1[HD Skins]§4 HD Skins v1.4.2, classic mode enabled.");
				}
				if(debugmode == true){
				minecraft.thePlayer.addChatMessage("§1[HD Skins]§4 Debug output enabled.");
				}
				
				
				hasTicked = true;
				
  }
  return true;
  }
}