package kenijey.harshenuniverse.config;

import java.util.HashMap;

import kenijey.harshenuniverse.HarshenItems;
import kenijey.harshenuniverse.HarshenUtils;
import kenijey.harshenuniverse.base.BaseConfig;
import net.minecraft.item.Item;

public class AccessoryConfig extends BaseConfig
{
	public static String[] blackListedXrays;
	public static int xrayAreaX, xrayAreaY, xrayAreaZ, xrayListSize;
	public static int enderPendantDistance;
	public static double reachPendantLength;

	@Override
	public String getName() {
		return "Accessories";
	}

	@Override
	public void read() 
	{
		blackListedXrays = get("xray.blacklist", HarshenItems.XRAY_PENDANT, HarshenUtils.listOf("minecraft:stone"));
		xrayAreaX = get("xray.distance.x", HarshenItems.XRAY_PENDANT, 30);
		xrayAreaY = get("xray.distance.y", HarshenItems.XRAY_PENDANT, 30);
		xrayAreaZ = get("xray.distance.z", HarshenItems.XRAY_PENDANT, 30);
		xrayListSize = get("xray.listsize", HarshenItems.XRAY_PENDANT, 20); 
		enderPendantDistance = get("enderpendant.distance", HarshenItems.ENDER_PENDANT, 66);
		reachPendantLength = get("reach.length", HarshenItems.REACH_PENDANT, 7d);
	}
	
	private HashMap<String, String> keyMap = new HashMap<>();
	
	private <T> T get(String name, Item item, T normal) 
	{
		return super.get(name, item.getRegistryName().getResourcePath(), normal);
	}
}