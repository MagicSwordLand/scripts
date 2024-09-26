@file:RequiredPlugins("ScriptedQuests")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.quests.Quest
import net.brian.scriptedquests.compability.mythicmobs.KillMultiMobsObjective
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.objectives.GiveItemObjective
import net.brian.scriptedquests.objectives.ListenNPCObjective
import net.brian.scriptedquests.objectives.ListenTalkObjective
import net.brian.scriptedquests.quests.TimeLimitQuest
import net.brian.scriptedquests.rewards.ClassExpReward
import net.brian.scriptedquests.rewards.MessageReward
import net.brian.scriptedquests.rewards.MoneyReward
import net.brian.scriptedquests.rewards.QuestExpReward
import net.brian.scriptedquests.utils.Utils

val questID = "daily_saha_gold_05"

class Daily_SaHa_Gold_05: TimeLimitQuest(questID,"§7[§a金牌每日§7] §f紫金蘿蔔"){
    private val playerName = "§c● §f%player_displayname%§r:";
    private val dayNPC = "§c● §e威克斯§r:";
    val npcIcon = IconFonts.getNPC(171);
    val playerIcon = "%squests_icon%";

    private val mes1 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "&x&d&6&c&1&a&e收集富含礦物質的蘿蔔",
            "&x&d&6&c&1&a&e最喜歡這種收集委託了",
        ),
    )

    private val mes2 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "&x&d&6&c&1&a&e我是來回報這個委託的",
        ),
        *Utils.getMessage(npcIcon,
            "$dayNPC",
            "&x&d&6&d&0&b&8好的，已確認完成委託",
            "&x&d&6&d&0&b&8這些是這次委託的獎勵",
        )
    )


    init {

        val obj1 = ListenTalkObjective(this,"obj1",*mes1)
            .setInstruction("§7查看委託內容");

        val obj2 = GiveItemObjective(this,"obj2",171,"COLLECTMATERIAL:M018",16)
            .setInstruction { "§7收集 ${it.amount} / 16 個 紫金蘿蔔 §7並交給§e威克斯" }

        val obj3 = ListenNPCObjective(this,"obj3",171,*mes2)
            .setInstruction("§7與 §e威克斯 §7對話")

        pushObjective(obj1,obj2,obj3);
        addRewards(
            MoneyReward(80), //金牌60~120 白金150~200
            ClassExpReward(120), //金牌100~200 白金250~500
            QuestExpReward(20), //金牌20~30 白金60~100
            MessageReward("&a➯ &7古銀: &7+2"), //金牌2~3 白金4~5
        )
        addEndHook({
            Utils.command("gamepoints add ${it.name} 2")
        })
    }

}
val quest = Daily_SaHa_Gold_05();
ScriptedQuests.getInstance().questManager.register(quest);
fun onDispose(){
    quest.unregister();
}