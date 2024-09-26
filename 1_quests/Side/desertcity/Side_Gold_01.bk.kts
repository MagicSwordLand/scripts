@file:RequiredPlugins("ScriptedQuests","PlayerDataSync","MMOItems","MythicLib")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.quests.Quest
import net.brian.scriptedquests.compability.mythicmobs.KillMobsObjective
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.objectives.GiveItemObjective
import net.brian.scriptedquests.objectives.ListenNPCObjective
import net.brian.scriptedquests.objectives.ListenTalkObjective
import net.brian.scriptedquests.objectives.MoveToObjective
import net.brian.scriptedquests.rewards.*
import net.brian.scriptedquests.utils.Utils
import org.bukkit.Bukkit
import org.bukkit.Location

val questID = "side_gold_01"

class Side_Gold_01: Quest(questID,"§7[§b金牌支線§7] §f兵團訓教的風向"){


    val playerIcon = "%squests_icon%";
    val NPC1Icon = IconFonts.getNPC(54);
    val NPC2Icon = IconFonts.getNPC(247);

    private val playerName = "§c● §f%player_displayname%§r:";
    private val NPC1 = "§c● §e盧思§r:";
    private val NPC2 = "§c● §e馬萊§r:";

    private val mes1 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8沙漠傭兵團在訓練時不會偷懶的",
            "&x&d&6&d&0&b&8雖然這是非常基本的。",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8不過這就是沙漠傭兵團",
            "&x&d&6&d&0&b&8成為大陸最強士兵的原因。",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8規劃士兵訓練計畫時，",
            "&x&d&6&d&0&b&8必須要參考天氣，",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8因為不管是什麼狀況，",
            "&x&d&6&d&0&b&8都必須執行最佳作戰計畫，",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8啊，對了，",
            "&x&d&6&d&0&b&8今天還沒有觀測氣象，",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8如果你有空的話，",
            "&x&d&6&d&0&b&8可以幫我的忙嗎？",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8幫我去一趟拉克沙天文台好嗎？",
            "&x&d&6&d&0&b&8拉克沙天文台就在城內，",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8雖然規模比較小，",
            "&x&d&6&d&0&b&8不過有功能非常好的天體望遠鏡呢。",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e就交給我吧！",
        ),
    )
    private val mes2 = arrayOf(
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8今天的天氣嗎？",
            "&x&d&6&d&0&b&8晴朗吖...這不是很明顯嘛..",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8自己上樓頂看啦!",
            "&x&d&6&d&0&b&8兵團又不給我訂閱費，小氣死了!",
        ),
    )
    private val mes3 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8哦，觀察得很仔細，謝謝你！",
            "&x&d&6&d&0&b&8傭兵團不會忘記你的。",
        ),
    )
    init {
        val world = Bukkit.getWorld("2k");
        val obj1 = ListenNPCObjective(this,"obj1",177,*mes1)
            .setInstruction("§7與 §e盧思 §7對話");
        val obj2 = ListenNPCObjective(this,"obj2",247,*mes2)
            .setInstruction("§7與 §e馬萊 §7對話");
        val obj3 = ListenNPCObjective(this,"obj3",177,*mes3)
            .setInstruction("§7與 §e盧思 §7對話");
        pushObjective(obj1,obj2,obj3)

        addRewards(
            MoneyReward(500), //金牌 300~800
            QuestExpReward(100), //金牌 100~200
            ClassExpReward(350) //金牌 300~500
        )
    }
}


val quest = Side_Gold_01();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



