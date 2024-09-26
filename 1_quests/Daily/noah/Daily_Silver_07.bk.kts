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

val questID = "daily_silver_07"

class Daily_Silver_07: TimeLimitQuest(questID,"§7[§a銀牌每日§7] §f商會庫存"){
    private val playerName = "§c● §f%player_displayname%§r:";
    private val dayNPC = "§c● §e墨里斯§r:";
    val npcIcon = IconFonts.getNPC(110);
    val playerIcon = "%squests_icon%";

    private val mes1 = arrayOf(
        *Utils.getMessage(npcIcon,
            "$dayNPC",
            "&x&d&6&d&0&b&8這位傭兵",
            "&x&d&6&d&0&b&8商會有個任務想委託於你!",
        ),
        *Utils.getMessage(npcIcon,
            "$dayNPC",
            "&x&d&6&d&0&b&8商會目前庫存的商品所剩寥寥無幾",
            "&x&d&6&d&0&b&8可以去幫我蒐集靈木和蒼山金嗎?",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e好的!沒問題包在我身上",
        ),
    )

    private val mes2 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "&x&d&6&c&1&a&e這些是委託的物品",
        ),
        *Utils.getMessage(npcIcon,
            "$dayNPC",
            "&x&d&6&d&0&b&8太棒了，這樣庫存暫時就不用擔心了",
        )
    )


    init {

        val obj1  = ListenNPCObjective(this,"obj1",110,*mes1)
            .setInstruction("§7與 §e墨里斯 §7對話")
        val obj2 = GiveMultiItemObjective(this,"obj2",110,
            mapOf(
                "COLLECTMATERIAL:M008" to 32,//蒼山金
                "MATERIAL:M007" to 24,//靈木
            ))
            .setInstruction{"§7採收 ${it.get("COLLECTMATERIAL:M008")} / 32 個蒼山金、${it.get("MATERIAL:M007")} / 24 靈木 並交給 §e墨里斯"}
        val obj3 = ListenNPCObjective(this,"obj3",110,*mes2)
            .setInstruction("§7與 §e墨里斯 §7對話")

        pushObjective(obj1,obj2,obj3);
        addRewards(
            MoneyReward(50), //30錢幣
            ClassExpReward(160),
            QuestExpReward(20) //15傭兵聲望
        )
    }

}
val quest = Daily_Silver_07();
ScriptedQuests.getInstance().questManager.register(quest);
fun onDispose(){
    quest.unregister();
}