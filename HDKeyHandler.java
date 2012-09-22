package net.minecraft.src;

import java.util.EnumSet;
import net.minecraft.client.Minecraft;
import net.minecraft.src.KeyBinding;
import net.minecraft.src.ModLoader;
import org.lwjgl.input.Keyboard;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;

public class HDKeyHandler extends KeyHandler {
        
        public static KeyBinding HDBinding = new KeyBinding("Refresh Skins", Keyboard.KEY_P);

        public HDKeyHandler() {
                //the first value is an array of KeyBindings, the second is whether or not the call 
                //keyDown should repeat as long as the key is down
                super(new KeyBinding[]{HDBinding}, new boolean[]{false});
        }

        @Override
        public String getLabel() {
                return "Refresh Skins";
        }

        @Override
        public void keyDown(EnumSet<TickType> types, KeyBinding kb,
                        boolean tickEnd, boolean isRepeat) {
            Minecraft mc = ModLoader.getMinecraftInstance();
			HDSkinHandler.forceUpdateSkins(mc.theWorld);
                
        }

        @Override
        public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
			//nothing
        }

        @Override
        public EnumSet<TickType> ticks() {
                return EnumSet.of(TickType.CLIENT);
                //I am unsure if any different TickTypes have any different effects.
        }
}