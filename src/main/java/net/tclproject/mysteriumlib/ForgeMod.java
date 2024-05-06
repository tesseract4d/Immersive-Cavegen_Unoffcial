package net.tclproject.mysteriumlib;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.util.EnumChatFormatting;
import java.util.Collections;
import java.util.Random;



@Mod(modid = ModProperties.MODID, useMetadata = true, version = ModProperties.VERSION, name = ModProperties.NAME)
public class ForgeMod {
	@Mod.Instance(ModProperties.MODID)
	public static ForgeMod instance;

	private boolean obfuscated;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		makeFancyModInfo(event);

		instance = this;
		MinecraftForge.EVENT_BUS.register(instance);
	}

	public void makeFancyModInfo(FMLPreInitializationEvent event) {
		// The following will overwrite the mcmod.info file so the info page looks good.
		// Adapted from Jabelar's Magic Beans and AstroTibs's OptionsEnforcer.

		// This will stop Forge from complaining about missing mcmod.info (just in case i forget it).
		event.getModMetadata().autogenerated = false;

		event.getModMetadata().name = ModProperties.COLORED_NAME; // Mod name
		event.getModMetadata().version = ModProperties.COLORED_VERSION; // Mod version
		event.getModMetadata().credits = ModProperties.CREDITS; // Mod credits

		// Author list
		event.getModMetadata().authorList.clear();
		Collections.addAll(event.getModMetadata().authorList, ModProperties.AUTHORS);

		event.getModMetadata().url = ModProperties.COLORED_URL; // Mod URL

		// Mod description
		event.getModMetadata().description = ModProperties.DESCRIPTION + "\n\n" + EnumChatFormatting.DARK_GRAY + EnumChatFormatting.ITALIC +
		                                     ModProperties.SPLASH_OF_THE_DAY[(new Random()).nextInt(ModProperties.SPLASH_OF_THE_DAY.length)];

		if (ModProperties.LOGO != null) event.getModMetadata().logoFile = ModProperties.LOGO; // Mod logo
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		this.obfuscated = !(Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
	}
}
