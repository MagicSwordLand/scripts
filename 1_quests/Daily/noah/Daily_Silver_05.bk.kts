@file:RequiredPlugins("ScriptedQuests")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.quests.Quest
import net.brian.scriptedquests.compability.mythicmobs.KillMobsObjective
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.objectives.GiveItemObjective
import net.brian.scriptedquests.objectives.ListenNPCObjective
import net.brian.scriptedquests.objectives.ListenTalkObjective
import net.brian.scriptedquests.quests.TimeLimitQuest
import net.brian.scriptedquests.rewards.ClassExpReward
import net.brian.scriptedquests.rewards.MoneyReward
import net.brian.scriptedquests.rewards.QuestExpReward
import net.brian.scriptedquests.utils.Utils

val questID = "daily_silver_05"

class Daily_Silver_05: TimeLimitQuest(questID,"§7[§a銀牌每日§7] §f鱗鑌礦工"){
    private val playerName = "§c● §f%player_displayname%§r:";
    private val dayNPC = "§c● §e莉亞莫§r:";
    val npcIcon = IconFonts.getNPC(16);
    val playerIcon = "%squests_icon%";

    private val mes1 = arrayOf(
        *Utils.getMessage(npcIcon,
            "$dayNPC",
            "&x&d&6&d&0&b&8這位傭兵",
            "&x&d&6&d&0&b&8古岸礦場中有個叫做鱗鑌的礦物",
            "&x&d&6&d&0&b&8傭兵公會需要64個這種礦物",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "&x&d&6&c&1&a&e就交給我了",
        )
    )

    private val mes2 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "&x&d&6&c&1&a&e我已經完成委託了",
        ),
        *Utils.getMessage(npcIcon,
            "$dayNPC",
            "&x&d&6&d&0&b&8收到了，這是這次委託的獎勵",
        )
    )


    init {

        val obj1  = ListenNPCObjective(this,"obj1",16,*mes1)
            .setInstruction("§7與 §e莉亞莫 §7對話")
        val obj2 = GiveItemObjective(this,"obj2",16,"COLLECTMATERIAL:M002",64)
            .setInstruction{"§7採收 ${it.amount} / 64 個鱗鑌 並交給 §e莉亞莫"}
        val obj3 = ListenNPCObjective(this,"obj3",16,*mes2)
            .setInstruction("§7與 §e莉亞莫 §7對話")

        pushObjective(obj1,obj2,obj3);
        addRewards(
            MoneyReward(35), //30錢幣
            ClassExpReward(150),
            QuestExpReward(15) //15傭兵聲望
        )
    }

}
val quest = Daily_Silver_05();
ScriptedQuests.getInstance().questManager.register(quest);
fun onDispose(){
    quest.unregister();
}