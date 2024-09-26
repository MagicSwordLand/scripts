@file:RequiredPlugins("ScriptedQuests","PlayerDataSync","MMOItems","MythicLib")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.quests.Quest
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.objectives.GiveItemObjective
import net.brian.scriptedquests.objectives.ListenNPCObjective
import net.brian.scriptedquests.rewards.*
import net.brian.scriptedquests.utils.Utils
import org.bukkit.Bukkit

val questID = "side_gold_12"

class Side_Gold_12: Quest(questID,"§7[§b金牌支線§7] §f魯卡的愛情煩惱"){


    val playerIcon = "%squests_icon%";
    val NPC1Icon = IconFonts.getNPC(183);
    val NPC2Icon = IconFonts.getNPC(247);
    val NPC3Icon = IconFonts.getNPC(246);

    private val playerName = "§c● §f%player_displayname%§r:";
    private val NPC1 = "§c● §e魯卡§r:";
    private val NPC2 = "§c● §e馬萊§r:";
    private val NPC3 = "§c● §e雷克倫§r:";

    private val mes1 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8我的寶貝她最近",
            "&x&d&6&d&0&b&8一直吵著要我摘星星給她，",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8啊，當然她也不是真心想要星星，",
            "&x&d&6&d&0&b&8不過，我真的很愛她，",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8我想要準備長得很像星星的東西",
            "&x&d&6&d&0&b&8讓她高興，",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8不過，不知道那個星星在哪裡？",
            "&x&d&6&d&0&b&8又長得怎麼樣？",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8你可以幫我去拉克沙天文台",
            "&x&d&6&d&0&b&8幫我解決我的疑問嗎？",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8啊，當然可以問天文學者，",
            "&x&d&6&d&0&b&8不過...",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8其實我跟他們之間",
            "&x&d&6&d&0&b&8曾發生過不好的事情...",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8可以麻煩你幫我跑一趟嗎？",
            "&x&d&6&d&0&b&8謝謝你！",
        ),
    )


    private val mes2 = arrayOf(
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8...怎麼又來問我",
            "&x&d&6&d&0&b&8讓他找工藝家打造水晶就好啦！",
        ),
    )

    private val mes3 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e請問這裡可以打造星星水晶嗎？",
        ),
        *Utils.getMessage(NPC3Icon,
            "$NPC3",
            "&x&d&6&d&0&b&8...這裡沒有這種東西",
            "&x&d&6&d&0&b&8不過我倒是有比星星更漂亮的東西！",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e我就先看看，不是我買哈...",
        ),
        *Utils.getMessage(NPC3Icon,
            "$NPC3",
            "&x&d&6&d&0&b&8如果你不想花大價錢買的話",
            "&x&d&6&d&0&b&8也可以給材料，我幫你製作喲~",
        ),
    )

    private val mes4 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8謝謝！我不太確定這個...那麼...",
            "&x&d&6&d&0&b&8我要開始找實力不錯的工藝家了，",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8不過，要是她明天也吵著",
            "&x&d&6&d&0&b&8要我幫她摘月亮的話，該怎麼辦呢...？",
        ),
    )

    init {
        val world = Bukkit.getWorld("2k");
        val obj1 = ListenNPCObjective(this,"obj1",183,*mes1)
            .setInstruction("§7與 §e魯卡 §7對話");
        val obj2 = ListenNPCObjective(this,"obj2",247,*mes2)
            .setInstruction("§7與 §e馬萊 §7對話");
        val obj3 = ListenNPCObjective(this,"obj3",246,*mes3)
            .setInstruction("§7與 §e雷克倫 §7對話");
        val obj4 = GiveItemObjective(this,"obj4",183,"QUESTMATERIAL:M028",1)
            .setInstruction{"交給 §e魯卡 §7${it.amount}/1 個鑽星項鏈 &8(位於雷克倫工藝店製作)"};
        val obj5 = ListenNPCObjective(this,"obj5",183,*mes4)
            .setInstruction("§7與 §e魯卡 §7對話");

        pushObjective(obj1,obj2,obj3,obj4,obj5)

        addRewards(
            MoneyReward(700), //金牌 300~800
            QuestExpReward(160), //金牌 100~200
            ClassExpReward(500) //金牌 300~500
        )
    }
}


val quest = Side_Gold_12();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



