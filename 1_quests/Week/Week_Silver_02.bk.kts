@file:RequiredPlugins("ScriptedQuests")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.quests.Quest
import net.brian.scriptedquests.compability.mythicmobs.KillMobsObjective
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

val questID = "week_silver_02"

class Week_Silver_02: TimeLimitQuest(questID,"§7[§e銀牌每周§7] §f學不乖的盜賊",DAY*7,T20220515){
    private val playerName = "§c● §f%player_displayname%§r:";
    private val greyNPC = "§c● §e墨里斯§r:";
    val npcIcon = IconFonts.getNPC(110);
    val playerIcon = "%squests_icon%";

    private val mes1 = arrayOf(
        *Utils.getMessage(npcIcon,
            "$greyNPC",
            "&x&d&6&d&0&b&8唉，怎麼貨物一天到晚都被搶走",
            "&x&d&6&d&0&b&8欸?",
            "&x&d&6&d&0&b&8傭兵你來得正好",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "&x&d&6&c&1&a&e怎麼了?",
        ),
        *Utils.getMessage(npcIcon,
            "$greyNPC",
            "&x&d&6&d&0&b&8我真的快受不了了",
            "&x&d&6&d&0&b&8那群盜賊一天到晚就來搶奪我的貨物",
            "&x&d&6&d&0&b&8你能去給他們一點教訓嗎?",
        ),
            *Utils.getMessage(npcIcon,
            "$greyNPC",
            "&x&d&6&d&0&b&8還有貨物也順便幫我取回來...",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "&x&d&6&c&1&a&e交給我吧！",
        ),
    )

    private val mes2 = arrayOf(
        *Utils.getMessage(npcIcon,
            "$greyNPC",
            "&x&d&6&d&0&b&8太棒了，希望他們受到足夠的教訓了",
            "&x&d&6&d&0&b&8這些是你的獎勵",
        ),
    )


    init {

        val obj1 = ListenNPCObjective(this,"obj1",110,*mes1)
            .setInstruction("§7與 §e墨里斯 §7對話");

        val obj2 = KillMobsObjective(this,"obj2","盜賊據點_",50)
            .setInstruction { "擊殺 ${it.amount} / 50 個盜賊" };

        val obj3 = GiveMultiItemObjective(this,"obj3",110,
            mapOf(
                "QUESTMATERIAL:QM014" to 16,
            ))
            .setInstruction{"§7將 §7${it.get("QUESTMATERIAL:QM014")}/16 個 灰狼商會的貨物 交給 §e墨里斯 §8(放置副手繳交)§r"}
        val obj4 = ListenNPCObjective(this,"obj4",110,*mes2)
            .setInstruction("§7與 §e墨里斯 §7對話");

        pushObjective(obj1,obj2,obj3,obj4);
        addRewards(
            MoneyReward(150), //30錢幣
            QuestExpReward(20), //15傭兵聲望
            ClassExpReward(800),
        )
    }

}
val quest = Week_Silver_02();
ScriptedQuests.getInstance().questManager.register(quest);
fun onDispose(){
    quest.unregister();
}