package io.github.thebusybiscuit.slimefun4.api.geo;

import org.bukkit.Chunk;
import org.bukkit.Keyed;
import org.bukkit.World.Environment;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.SlimefunPlugin;

public interface GEOResource extends Keyed {
	
	/**
	 *  Returns the default supply of this resource in that biome
	 *  
	 *  @param environment	The {@link Environment} this area is currently in (NORMAL / NETHER / THE_END)
	 *  @param biome		The {@link Biome} this area is currently in.
	 *  
	 *  @return The default supply found in a {@link Chunk} with the given {@link Biome}
	 */
	int getDefaultSupply(Environment environment, Biome biome);
	
	/**
	 * Returns how much the value may deviate from the default supply (positive only).
	 * 
	 * @return	The deviation or spread of the supply
	 */
	int getMaxDeviation();
	
	/**
	 *  Returns the name of this resource (e.g. "Oil")
	 *  
	 *  @return	The name of this Resource
	 */
	String getName();
	
	/**
	 * This {@link ItemStack} is used for display-purposes in the GEO Scanner.
	 * But will also determine the Output of the GEO Miner, if it is applicable for that.
	 * 
	 * @return The {@link ItemStack} version of this Resource.
	 */
	ItemStack getItem();
	
	/**
	 * Returns whether this Resource can be obtained using a GEO Miner.
	 * This will automatically add it to the GEO - Miner.
	 * 
	 * @return Whether you can get obtain this resource using a GEO Miner.
	 */
	boolean isObtainableFromGEOMiner();
	
	/**
	 * Registers this GEO Resource
	 */
	default void register() {
		SlimefunPlugin.getGPSNetwork().getResourceManager().register(this);
	}

	default String getName(Player p) {
		String name = SlimefunPlugin.getLocal().getResourceString(p, "resources." + getKey().getNamespace() + "." + getKey().getKey());
		return name == null ? getName(): name;
	}

}
