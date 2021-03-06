package kenijey.harshenuniverse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kenijey.harshenuniverse.commands.HarshenCommandLoader;
import kenijey.harshenuniverse.creativetabs.HarshenTab;
import kenijey.harshenuniverse.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(
		modid = HarshenUniverse.MODID,
		name = HarshenUniverse.MODNAME,
		version = HarshenUniverse.VERSION, 
		useMetadata = true, 
		acceptedMinecraftVersions = "[1.12.2,1.13]",
		dependencies = "required-after:forge@[14.23.4.2705,)")

public class HarshenUniverse
{
    public static final String MODID = "harshenuniverse";
    public static final String MODNAME = "Harshen Universe";
    public static final String VERSION = "0.19.0";
    public static final String UPDATE_URL = "http://www.wynprice.com/moddedUpdateCheckers/harshenuniverse.json";
    
    public static boolean hasLoaded;

    @SidedProxy(clientSide = "kenijey.harshenuniverse.proxy.ClientProxy", serverSide = "kenijey.harshenuniverse.proxy.ServerProxy")
    public static CommonProxy proxy;
    
    public static final CreativeTabs harshenTab = new HarshenTab();
    
    public static final Logger LOGGER = LogManager.getLogger(MODID); 

    
    @Instance(MODID)
    public static HarshenUniverse instance;
    
	static 
	{
		FluidRegistry.enableUniversalBucket();
	}

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) 
    {
    	proxy.preInit(event);
    	proxy.regRenders(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) 
    {
    	proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) 
    {
    	proxy.postInit(event);
		hasLoaded = true;
    }
    
    @EventHandler
	public void serverLoad(FMLServerStartingEvent event)
	{
    	event.registerServerCommand(new HarshenCommandLoader());
	}
}