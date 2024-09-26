@file:RequiredPlugins("ScriptedQuests","PlayerDataSync")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.BUKKIT_SCRIPT_DEFINITION_NAME
import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests;
import net.brian.scriptedquests.commands.SubCommand;
import net.brian.scriptedquests.data.PlayerQuestDataImpl;
import net.brian.scriptedquests.api.quests.QuestManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import java.text.SimpleDateFormat
import java.util.*


class InfoCommand(plugin: ScriptedQuests): SubCommand(plugin,"info") {

    override fun needAdmin(): Boolean {
        return true;
    }

    override fun getName(): String {
        return "info";
    }

    //  /sq info <player>

    override fun onCommand(sender: CommandSender, args: Array<String>) {
        if(args.size < 2){
            return;
        }
        val player = Bukkit.getPlayer(args[1]);
        val format = SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.TAIWAN);
        PlayerQuestDataImpl.get(player?.uniqueId).ifPresent {
            sender.sendMessage("已完成任務與最後完成時間")
            it.finishedQuests.forEach { id, time ->
                sender.sendMessage("${id}: ${format.format(Date(time))}")
            }
            sender.sendMessage("=====================================","進行中任務")
            it.onGoingQuests.forEach { t, u ->
                sender.sendMessage("[$t] 階段 $u")
            }
        }
    }
}


val cmd = InfoCommand(ScriptedQuests.getInstance());

ScriptedQuests.getInstance().commandManager.register(cmd);
