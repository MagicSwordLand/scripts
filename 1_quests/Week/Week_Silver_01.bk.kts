@file:RequiredPlugins("ScriptedQuests")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.quests.Quest
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.objectives.GiveItemObjective
import net.brian.scriptedquests.objectives.GiveMultiItemObjective
import net.brian.scriptedquests.objectives.ListenNPCObjective
import net.brian.scriptedquests.objectives.ListenTalkObjective
import net.brian.scriptedquests.quests.TimeLimitQuest
import net.brian.scriptedquests.rewards.ClassExpReward
import net.brian.scriptedquests.rewards.MoneyReward
import net.brian.scriptedquests.rewards.QuestExpReward
import net.brian.scriptedquests.utils.Utils

val questID = "week_silver_01"

class Week_Silver_01: TimeLimitQuest(questID,"§7[§e銀牌每周§7] §f熟練傭兵的任務",DAY*7,T20220515){
    private val playerName = "§c● §f%player_displayname%§r:";
    private val greyNPC = "§c● §e焱格§r:";
    val npcIcon = IconFonts.getNPC(5);
    val playerIcon = "%squests_icon%";

    private val mes1 = arrayOf(
        *Utils.getMessage(npcIcon,
            "$greyNPC",
            "&x&d&6&d&0&b&8為了確保諾亞鎮及銅牌傭兵的安全",
            "&x&d&6&d&0&b&8傭兵公會每周都會提供銀牌傭兵委託",
            "&x&d&6&d&0&b&8負責減少附近怪物的數量",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "&x&d&6&c&1&a&e我已經有那個實力！",
        ),
        *Utils.getMessage(npcIcon,
            "$greyNPC",
            "&x&d&6&d&0&b&8沒錯！這些事要清掃的怪物",
            "&x&d&6&d&0&b&8將他們的掉落物交還回來就好",
        )
    )

    private val mes2 = arrayOf(
        *Utils.getMessage(npcIcon,
            "$greyNPC",
            "&x&d&6&d&0&b&8厲害！難得有人這麼快就完成任務了",
            "&x&d&6&d&0&b&8這些是你的獎勵",
        ),
    )


    init {

        val obj1 = ListenNPCObjective(this,"obj1",5,*mes1)
            .setInstruction("§7與 §e焱格 §7對話");

        val obj2 = GiveMultiItemObjective(this,"obj2",5,
            mapOf(
                "MATERIAL:M002" to 64,//魔化樹藤
                "MATERIAL:M003" to 64,//被汙染的骨頭
                "BOSSMATERIAL:M001" to 1,//將軍的舞扇
                "BOSSMATERIAL:M002" to 1,//毒氣酸奶
                "BOSSMATERIAL:M003" to 1,//盜賊的鎧甲
                "BOSSMATERIAL:M004" to 1,//巨石核素
            ))
            .setInstruction{"收集 ${it.get("MATERIAL:M002")} / 64 個魔化樹藤、${it.get("MATERIAL:M003")} / 64 個被汙染的骨頭、${it.get("BOSSMATERIAL:M001")} / 1 個將軍的舞扇、${it.get("BOSSMATERIAL:M002")} / 1 個毒氣酸奶、${it.get("BOSSMATERIAL:M003")} / 1 個盜賊的鎧甲、${it.get("BOSSMATERIAL:M004")} / 1個巨石核素 並交給 §e焱恪§r"}

        val obj3 = ListenNPCObjective(this,"obj3",5,*mes2)
            .setInstruction("§7與 §e焱格 §7對話");

        pushObjective(obj1,obj2,obj3);
        addRewards(
            MoneyReward(150), //30錢幣
            QuestExpReward(30), //15傭兵聲望
            ClassExpReward(1000),
        )
    }

}
val quest = Week_Silver_01();
ScriptedQuests.getInstance().questManager.register(quest);
fun onDispose(){
    quest.unregister();
}