@file:RequiredPlugins("ScriptedQuests")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.conversation.PlayerOption
import net.brian.scriptedquests.utils.Utils

val npcIcon = IconFonts.getNPC(171);
val npcName = "§b● §e威克斯:"

val question = NPCQuestion(*Utils.getMessage(npcIcon,
    "${npcName}:",
    "§7有什麼任務要回報阿~",
))
    .addPlayerOptions(
        PlayerOption("&7[&a對話選擇&7] &f還沒有")
            .setNpcResponse(*Utils.getMessage(npcIcon,
                npcName,
                "好的，旁邊的委託牆有各種委託喔",
            ))
    )


ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(171,question);