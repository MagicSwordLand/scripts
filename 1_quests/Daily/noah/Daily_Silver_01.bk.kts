@file:RequiredPlugins("ScriptedQuests")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.quests.Quest
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.objectives.GiveItemObjective
import net.brian.scriptedquests.objectives.ListenNPCObjective
import net.brian.scriptedquests.objectives.ListenTalkObjective
import net.brian.scriptedquests.quests.TimeLimitQuest
import net.brian.scriptedquests.rewards.ClassExpReward
import net.brian.scriptedquests.rewards.MoneyReward
import net.brian.scriptedquests.rewards.QuestExpReward
import net.brian.scriptedquests.utils.Utils

val questID = "daily_silver_01"

class Daily_Silver_01: TimeLimitQuest(questID,"§7[§a銀牌每日§7] §f交辦事項"){
    private val playerName = "§c● §f%player_displayname%§r:";
    private val NPC2 = "§c● &e卡克拉§r:";
    private val dayNPC = "§c● §e莉亞莫§r:";
    val npcIcon = IconFonts.getNPC(16);
    val npcIcon2 = IconFonts.getNPC(60);
    val NPC2Icon = IconFonts.getNPC(114);
    val playerIcon = "%squests_icon%";

    private val mes1 = arrayOf(
        *Utils.getMessage(npcIcon,
            "$dayNPC",
            "&x&d&6&d&0&b&8諾亞鎮附近有一座礦場",
            "&x&d&6&d&0&b&8公會這邊有幾件工作要交代給礦場負責人",
            "&x&d&6&d&0&b&8這個交辦事項的紙條能幫我交給卡克拉嗎?",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "&x&d&6&c&1&a&e就交給我了",
        )
    )

    private val mes2 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "&x&d&6&c&1&a&e這是傭兵公會提供的交辦事項",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8嘖，怎麼每天都一堆事情",
            "&x&d&6&d&0&b&8拿來吧！",
            "&x&d&6&d&0&b&8收到了，快滾快滾",
        )
    )
    private val mes3 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "&x&d&6&c&1&a&e我已經將交辦事項交給卡克拉了",
        ),
        *Utils.getMessage(npcIcon,
            "$dayNPC",
            "&x&d&6&d&0&b&8收到了，這是你這是委託的獎勵",
        )
    )


    init {

        val obj1  = ListenNPCObjective(this,"obj1",16,*mes1)
            .setInstruction("§7與 §e莉亞莫 §7對話")

        val obj2 = ListenNPCObjective(this,"obj2",124,*mes2)
        obj2.setInstruction("§7與 §7卡克拉 §7對話")

        val obj3 = ListenNPCObjective(this,"obj3",16,*mes3)
            .setInstruction("§7與 §e莉亞莫 §7對話")

        pushObjective(obj1,obj2,obj3);
        addRewards(
            MoneyReward(20), //30錢幣
            ClassExpReward(150),
            QuestExpReward(15) //15傭兵聲望
        )
        addEndHook({
            Utils.command("sq limit set ${it.name} 20")
        })
    }

}
val quest = Daily_Silver_01();
ScriptedQuests.getInstance().questManager.register(quest);
fun onDispose(){
    quest.unregister();
}