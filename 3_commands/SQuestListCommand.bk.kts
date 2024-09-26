@file:RequiredPlugins("ScriptedQuests","PlayerDataSync")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests;
import net.brian.scriptedquests.api.quests.Quest
import net.brian.scriptedquests.commands.SubCommand;
import net.brian.scriptedquests.data.PlayerQuestDataImpl;
import net.brian.scriptedquests.api.quests.QuestManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.HashMap;


class ListCommand(plugin: ScriptedQuests) :Listener,SubCommand(plugin,"list") {

    private val pendingConfirm: MutableMap<Player,String?> = HashMap();
    private val questManager: QuestManager = plugin.questManager;

    private val categoryMap = mapOf(
        "main" to "§c§l§n主線委託",
        "daily" to "§a§l§n每日委託",
        "side" to "§b§l§n支線委託",
        "week" to "§e§l§n每周委託",
        "other" to "§d§l§n特殊委託"
    )

    init {
        plugin.server.pluginManager.registerEvents(this,plugin);
    }

    override fun needAdmin(): Boolean {
        return false;
    }


    override fun onCommand(player: CommandSender, args: Array<String>) {
        if(player is Player){
            val main = Component.text("                               §c§l§n主線委託")
                .hoverEvent(HoverEvent.showText(Component.text("點擊打開主線委託")))
                .clickEvent(ClickEvent.runCommand("/showquest main"))
            val side = Component.text("                               §b§l§n支線委託")
                .hoverEvent(HoverEvent.showText(Component.text("點擊打開支線委託")))
                .clickEvent(ClickEvent.runCommand("/showquest side"))
            val daily = Component.text("                               §a§l§n每日委託")
                .hoverEvent(HoverEvent.showText(Component.text("點擊打開每日委託")))
                .clickEvent(ClickEvent.runCommand("/showquest daily"))
            val weekly = Component.text("                               §e§l§n每周委託")
                .hoverEvent(HoverEvent.showText(Component.text("點擊打開每周委託")))
                .clickEvent(ClickEvent.runCommand("/showquest week"))
            val other = Component.text("                               §d§l§n特殊委託")
                .hoverEvent(HoverEvent.showText(Component.text("點擊打開支線委託")))
                .clickEvent(ClickEvent.runCommand("/showquest other"))
            val cancelTrack = Component.text("                               §7§l§n取消追蹤")
                .hoverEvent(HoverEvent.showText(Component.text("點擊取消任務")))
                .clickEvent(ClickEvent.runCommand("/sq tracking end"))
            player.sendMessage("§7§m━━━━━━━━━━━━━━━━━━━━━━§3 ❖ §6§l委託清單§3 ❖ §7§m━━━━━━━━━━━━━━━━━━━━━━")
            player.sendMessage(main)
            player.sendMessage("")
            player.sendMessage(side)
            player.sendMessage("")
            player.sendMessage(daily)
            player.sendMessage("")
            player.sendMessage(weekly)
            player.sendMessage("")
            player.sendMessage(other)
            player.sendMessage("")
            player.sendMessage(cancelTrack)
            player.sendMessage("§7§m━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━")
            return;
        }
    }

    @EventHandler
    fun onCommand(event: PlayerCommandPreprocessEvent){
        val args: List<String?> = event.message.split("cancelquest ");
        if(args.size == 2){
            event.isCancelled = true;
            val pending = pendingConfirm[event.player];
            val arg1 = args[1];
            if(pending != null && arg1 != null){
                if(pending.equals(arg1,false)){
                    pendingConfirm.remove(event.player);
                    plugin.questManager.getQuest(args[1]).ifPresent{quest ->
                        if(quest.isCancelAble){
                            quest.cancel(event.player);
                            event.player.sendMessage("§e任務 ${quest.display} 已取消");
                        }
                        else event.player.sendMessage("§c此任務無法取消");
                    };
                }
            }
            if(pending == null){
                event.player.sendMessage("§e你確定要放棄任務嗎？再次點擊放棄該讓務");
                pendingConfirm[event.player] = args[1];
            }
        }
    }

    @EventHandler
    fun onShowCommand(event: PlayerCommandPreprocessEvent){
        if(event.message.startsWith("/showquest")){
            event.isCancelled = true;
            val args: List<String> = event.message.split(" ");
            if(args.size == 2){
                val category = args[1];
                val display = categoryMap.getOrDefault(category, categoryMap["other"]!!);
                event.player.sendMessage("§7§m━━━━━━━━━━━━━━━━━━━━━━§3 ❖ $display §3❖ §7§m━━━━━━━━━━━━━━━━━━━━━━")
                PlayerQuestDataImpl.get(event.player.uniqueId).ifPresent { playerData->
                    playerData.onGoingQuests.forEach{(questID,objID) ->
                        if(!questID.startsWith(category)) return@forEach;
                        questManager.getQuest(questID).flatMap { it.getObjective(objID) }.ifPresent{
                            event.player.sendMessage("")
                            val quest = it.parent as Quest;
                            val trackComp = Component.text("§e✍ §f${quest.display}：${it.getInstruction(event.player)}")
                                .hoverEvent(HoverEvent.showText(Component.text("點擊開始追蹤任務")))
                                .clickEvent(ClickEvent.runCommand("/squest tracking track ${it.parent.id}"))
                            val deleteComp = Component.text(" §c§l[✘]")
                                .hoverEvent(HoverEvent.showText(Component.text("點擊兩次取消任務")))
                                .clickEvent(ClickEvent.runCommand("/cancelquest ${it.parent.id}"))
                            event.player.sendMessage(trackComp.append(deleteComp))
                        }
                    }
                }
                event.player.sendMessage("§7§m━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━")
            }
        }
    }


}

val cmd = ListCommand(ScriptedQuests.getInstance());

ScriptedQuests.getInstance().commandManager.register(cmd);

fun onDispose(){
    HandlerList.unregisterAll(cmd);
}