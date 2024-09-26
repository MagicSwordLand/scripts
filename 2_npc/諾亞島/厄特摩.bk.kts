@file:RequiredPlugins("ScriptedQuests")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.conversation.PlayerOption
import org.bukkit.Bukkit
import net.brian.scriptedquests.utils.Utils
import java.util.logging.Level

Bukkit.getLogger().log(Level.INFO,"npc re")

val npcName = "§b● §e厄特摩§r"
val npcIcon = IconFonts.getNPC(54);

val question1 = NPCQuestion(*Utils.getMessage(npcIcon,
    "${npcName}:",
    "§7看什麼?",
    "§7不准鬧事！",
))
    .addPlayerOptions(
        PlayerOption("&7[&a對話選項&7] &f...")
    )
ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(54,question1)