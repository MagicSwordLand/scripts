@file:RequiredPlugins("ScriptedQuests")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.conversation.PlayerOption
import org.bukkit.Bukkit
import net.brian.scriptedquests.utils.Utils
import org.bukkit.entity.Player

val npcIcon = IconFonts.getNPC(114);
val npcName = "§b● §e卡克拉§r"

val question1 = NPCQuestion(*Utils.getMessage(npcIcon,
    "${npcName}:",
    "§7再找什麼礦產嗎?",
    "§7我可以幫你加工鍛造喔！",
)).addPlayerOptions(

    PlayerOption("&7[&b製造物品&7] &f想拜托你製造點東西")
        .setResult { player ->
            if (isDay(player)||player.hasPermission("npc.ignoretime"))
                command("crafting station ${player.name} 卡克拉製造")
            else {
                player.sendMessage(*Utils.getMessage(npcIcon,
                    "${npcName}:",
                    "§7我有點困了",
                    "§7早上再來吧！",
                ))
            }},

    PlayerOption("&7[&b強化物品&7] &f想拜托你幫我强化武裝")
        .setResult { player ->
            if (isDay(player)||player.hasPermission("npc.ignoretime"))
                command("crafting upgrade ${player.name}")
            else {
                player.sendMessage(*Utils.getMessage(npcIcon,
                    "${npcName}:",
                    "§7我有點困了",
                    "§7早上再來吧！",
                ))
            }},


    PlayerOption("&7[&b洗鍊物品&7] &f我想洗煉武裝")
        .setResult { player->
            if (isDay(player)||player.hasPermission("npc.ignoretime"))
                command("crafting reroll ${player.name}")
            else {
                player.sendMessage(*Utils.getMessage(npcIcon,
                    "${npcName}:",
                    "§7我有點困了",
                    "§7早上再來吧！",
                ))
            }},


    PlayerOption("&7[&b鍛造選項&7] &f能幫我鍛造武裝嗎?").setNpcResponse(
        NPCQuestion(*Utils.getMessage(npcIcon,
            "${npcName}:",
            "想要我鍛造甚麼?",
            "我的手藝可以非常的好！",
        )).addPlayerOptions(
            PlayerOption("&7[&b鍛造武器&7] &f我想要武器")
                .setResult { player -> command("crafting station ${player.name} 卡克拉鍛造武器")},

            PlayerOption("&7[&b鍛造防具&7] &f我想要防具")
                .setResult { player -> command("crafting station ${player.name} 卡克拉鍛造防具")},
            PlayerOption("&7[&b鍛造飾品&7] &f我想要飾品")
                .setResult { player -> command("crafting station ${player.name} 卡克拉鍛造飾品")}
        )),
    //.addCondition{it.hasPermission("")}
    PlayerOption("&7[&b修補物品&7] &f我有一些想要修補的裝備")
        //.addCondition{it.hasPermission("")}
        .setResult { player ->
            if (isDay(player)||player.hasPermission("npc.ignoretime"))
                command("crafting repair ${player.name}");
            else {
                player.sendMessage(*Utils.getMessage(npcIcon,
                    "${npcName}:",
                    "§7我有點困了",
                    "§7早上再來吧！",
                ))
            } },

    PlayerOption("&7[&a對話選項&7] &f沒事了")
        //.addCondition{it.hasPermission("")}
        .setResult { player ->
            if (isDay(player)||player.hasPermission("npc.ignoretime"))
                player.sendMessage(*Utils.getMessage(npcIcon,
                    "${npcName}:",
                    "§7有需要隨時來找我！",
                ))
            else {
                player.sendMessage(*Utils.getMessage(npcIcon,
                    "${npcName}:",
                    "§7祝你好運！",
                ))
            }
        })


fun isDay(player: Player): Boolean{
    return player.getWorld().getTime() < 16000 || player.getWorld().getTime() > 23000;
}

fun command(commandString: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),commandString)
}

ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(124,question1)