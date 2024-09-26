@file:RequiredPlugins("WorldGuard","FastAsyncWorldEdit")

import com.sk89q.worldedit.bukkit.BukkitAdapter
import com.sk89q.worldguard.WorldGuard
import com.sk89q.worldguard.bukkit.WorldGuardPlugin
import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import org.bukkit.Bukkit
import org.bukkit.util.BlockVector


val container = WorldGuard.getInstance().platform.regionContainer;


val scheduler =
    Bukkit.getScheduler().runTaskTimer(WorldGuardPlugin.inst(), Runnable {
              Bukkit.getOnlinePlayers().forEach {
                  if(it.world.name != "2k") return@forEach;
                  container.createQuery().getApplicableRegions(BukkitAdapter.adapt(it.location))
                      .forEach { region ->
                          if(region.id.equals("exp")){
                              it.sendMessage("§7[§x§b§2§d§8§d§8溫泉§7] §7溫泉治癒著身心，泡在這裡感覺時間過得挺慢， 溫泉神氣+5")
                              Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"vh add ${it.name} 溫泉神氣 5")
                          }
                      }
              }
},0L,20*60*5L);

fun onDispose(){
    scheduler.cancel();
}