@file:RequiredPlugins("ScriptedQuests","PlayerDataSync","MMOItems","MythicLib")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.quests.Quest
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.objectives.ListenNPCObjective
import net.brian.scriptedquests.rewards.*
import net.brian.scriptedquests.utils.Utils
import org.bukkit.Bukkit

val questID = "side_gold_11"

class Side_Gold_11: Quest(questID,"§7[§b金牌支線§7] §f野田的商業機密"){


    val playerIcon = "%squests_icon%";
    val NPC1Icon = IconFonts.getNPC(98);
    val NPC2Icon = IconFonts.getNPC(141);

    private val playerName = "§c● §f%player_displayname%§r:";
    private val NPC1 = "§c● §e野田§r:";
    private val NPC2 = "§c● §e瑜爾§r:";

    private val mes1 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8嘰！你知道販賣魚料理",
            "&x&d&6&d&0&b&8最重要的是什麼嗎？嘰！",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8那就是新鮮的魚，",
            "&x&d&6&d&0&b&8從產地直接供應的魚，嘰！",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8瑜爾和我！嘰！",
            "&x&d&6&d&0&b&8就像這種合作關係，嘰！",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8我專心釣魚！嘰！",
            "&x&d&6&d&0&b&8瑜爾負責料理！嘰！",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8啊！嘰！對了！嘰！",
            "&x&d&6&d&0&b&8這些魚要趕快送給瑜爾！嘰！",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8客人們正在等著！嘰！",
            "&x&d&6&d&0&b&8請幫我把這個魚箱轉交給瑜爾！嘰！",
        ),
    )


    private val mes2 = arrayOf(
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8野田的釣魚實力非常棒，",
            "&x&d&6&d&0&b&8不過他卻相當健忘，",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8明天你可以再順便去野田那裡一趟嗎？",
            "&x&d&6&d&0&b&8我真的很需要你。",
        ),
    )



    init {
        val world = Bukkit.getWorld("2k");
        val obj1 = ListenNPCObjective(this,"obj1",26,*mes1)
            .setInstruction("§7與 §e野田 §7對話");
        val obj2 = ListenNPCObjective(this,"obj2",141,*mes2)
            .setInstruction("§7與 §e瑜爾 §7對話");

        pushObjective(obj1,obj2)

        addRewards(
            MoneyReward(400), //金牌 300~800
            QuestExpReward(180), //金牌 100~200
            ClassExpReward(450) //金牌 300~500
        )
    }
}


val quest = Side_Gold_11();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



