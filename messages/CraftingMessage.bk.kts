@file:RequiredPlugins("Crafting","ScriptedQuests")


import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.crafting.api.events.PlayerUpgradeEvent
import net.brian.scriptedquests.ScriptedQuests
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener

fun command(line: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),line);
}

class CraftListener: Listener{

    @EventHandler
    fun onUpgrade(event: PlayerUpgradeEvent){
        val itemName = event.itemStack.itemMeta.displayName;
        val player = event.player.name;
        val tier = event.tierName;
        val level = event.newLevel;

        if(level <10 ){
            return
        }
        when(event.upgradeResult){
            PlayerUpgradeEvent.UpgradeResult.SUCCESS ->{
                command("cmi bbroadcast §7[§x§8§0§C§6§B§8強化§7] §f$player §7強化 §7<§r$tier§7> $itemName §7至§f+ $level")
            }
            PlayerUpgradeEvent.UpgradeResult.EVOLVE ->{
                command("cmi bbroadcast §7[§x§8§0§C§6§B§8強化§7] §f$player §7進化 §7<§r$tier§7> $itemName §7至§f+ $level")
            }
            PlayerUpgradeEvent.UpgradeResult.DOWNGRADE ->{
                command("cmi bbroadcast §7[§x§8§0§C§6§B§8強化§7] §f$player §7強化 §7<§r$tier§7> $itemName §7至§f+ $level")
            }
            PlayerUpgradeEvent.UpgradeResult.FAIL ->{
                command("cmi bbroadcast §7[§x§8§0§C§6§B§8強化§7] §f$player §7強化 §7<§r$tier§7> $itemName §7至§f+ $level")
            }
            else -> {}
        }

    }

}


val listener = CraftListener();
Bukkit.getPluginManager().registerEvents(listener,ScriptedQuests.getInstance())

fun onDispose(){
    HandlerList.unregisterAll(listener);
}