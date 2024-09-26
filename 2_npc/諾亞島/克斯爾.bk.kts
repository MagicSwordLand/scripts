@file:RequiredPlugins("ScriptedQuests")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.conversation.PlayerOption
import org.bukkit.Bukkit
import net.brian.scriptedquests.utils.Utils

val npcIcon = IconFonts.getNPC(53);

val npcName = "§b● §e克斯爾§r"

val question1 = NPCQuestion(*Utils.getMessage(npcIcon,
        "${npcName}:",
        "§7有什麼事情嗎?",
        "§7沒有就不要來打擾我工作！",
))
        .addPlayerOptions(
                PlayerOption("&7[&a對話選項&7] &f這裡是?")
                        .setResult { player -> player.sendMessage(*Utils.getMessage(npcIcon,
                                "${npcName}:",
                                "§7這裡是傭兵公會在諾亞鎮的分會",
                                "§7你可以在這裡接受各種委託",
                        ))},
                PlayerOption("&7[&a對話選項&7] &f沒事").setResult { player -> player.sendMessage(*Utils.getMessage(npcIcon,
                        "${npcName}:",
                        "§7那就快滾",
                        "§7煩死了",
                ))}
        )
ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(53,question1)