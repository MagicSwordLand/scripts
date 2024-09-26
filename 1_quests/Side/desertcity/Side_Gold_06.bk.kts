@file:RequiredPlugins("ScriptedQuests","PlayerDataSync","MMOItems","MythicLib")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.quests.Quest
import net.brian.scriptedquests.compability.mythicmobs.KillMultiMobsObjective
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.objectives.*
import net.brian.scriptedquests.rewards.*
import net.brian.scriptedquests.utils.Utils
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player

val questID = "side_gold_06"

class Side_Gold_06: Quest(questID,"§7[§b金牌支線§7] §f薩哈商圈的師爺"){


    val playerIcon = "%squests_icon%";
    val NPC1Icon = IconFonts.getNPC(144);
    val NPC2Icon = IconFonts.getNPC(52);

    private val playerName = "§c● §f%player_displayname%§r:";
    private val NPC1 = "§c● §e道格§r:";
    private val NPC2 = "§c● §e布思§r:";

    private val mes1 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8再這樣下去，不管是阿納斯塔城還是諾亞鎮",
            "&x&d&6&d&0&b&8我都回不去了。",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e怎麼了？",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "§x§d§6§c§1§a§e東西都沒了...我會告訴你馬車的位置，",
            "§x§d§6§c§1§a§e請幫我看是否有完整的物品。",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8就算是小東西也沒關係。請幫幫我...",
            "&x&d&6&d&0&b&8馬車在那邊。到處都是殘暴的傢伙，請小心。",
        ),
    )

    private val mes2 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8什麼？ 什麼東西也沒有？",
            "&x&d&6&d&0&b&8哎呀...我完蛋了，完了！",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8那些都是些什麼怪物呀到底！",
            "&x&d&6&d&0&b&8長相就令人心情惡劣...",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8我恨不得斬草除根，但卻做不到。",
            "&x&d&6&d&0&b&8你會把我的憤怒傳達給他們吧？",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8殺光他們吧。",
            "&x&d&6&d&0&b&8我想讓這片大地充滿豬人的血...",
        ),
    )

    private val mes3 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8現在那些怪物能感受到我的憤怒了吧...",
            "&x&d&6&d&0&b&8謝啦，傭兵冒險家。",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "§x§d§6§c§1§a§e啊啊...我現在才想到，",
            "§x§d§6§c§1§a§e我們被豬人襲擊的時候，",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8還有一台馬車跟在我們後面。",
            "&x&d&6&d&0&b&8或許那些護衛隊幸運的除掉了豬人，",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8所以馬車才沒壞掉。",
            "&x&d&6&d&0&b&8不過只要被豬人纏上，就很難甩掉。",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8我們沒事了，快去幫他們吧？",
            "&x&d&6&d&0&b&8活人就該活著啊...",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e現在該怎麼辦..",
            "§x§d§6§c§1§a§e要回去蛛菇沙原嗎？",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8在那邊發呆站著幹嗎？快去吖!",
            "&x&d&6&d&0&b&8一起去！",
        ),
    )

    private val mes4 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8豬人會再聚集過來。",
            "&x&d&6&d&0&b&8快到崗哨請求支援。",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8這些商人的性命就靠你的雙腳了。",
            "&x&d&6&d&0&b&8趕快！",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e請別退縮！ ",
            "§x§d§6§c§1§a§e那樣只會變成那些傢伙的食物！",
        ),
    )

    private val mes5 = arrayOf(
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8外地人一律要通過盤查。",
            "&x&d&6&d&0&b&8排隊...商團又被攻擊了？",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8大事不妙。",
            "&x&d&6&d&0&b&8偵察隊要和豬人對戰的裝備會不夠...",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8馬上就派人去支援。",
            "&x&d&6&d&0&b&8感謝你！冒險家！",
        ),
    )

    private val mes6 = arrayOf(
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8看起來似乎經歷過厲害的戰鬥...",
            "&x&d&6&d&0&b&8似乎對實力也有自信。",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8給你一個建議，",
            "&x&d&6&d&0&b&8豬人狂暴的理由，是因為他們是異世界的變種。",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8但令人意外的是，",
            "&x&d&6&d&0&b&8只要了解其單純特有的習性，就不難壓制，",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8有關最近開始出現的變種，",
            "&x&d&6&d&0&b&8其實資訊不是很夠...",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8傭兵你幫了很大的忙呢！謝謝。",
        ),
    )

    private val mes7 = arrayOf(
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8實際上目前監視崗哨警衛",
            "&x&d&6&d&0&b&8和通行排查就很忙了。",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8都是因為最近城境開放，",
            "&x&d&6&d&0&b&8商人或冒險家的往來變得頻繁。",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8問題是不知怎地",
            "&x&d&6&d&0&b&8豬人的數量也突然增加了。",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8要調查是否有關連性...",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8不過我們現在能做的",
            "&x&d&6&d&0&b&8就是請傭兵們稍微減少豬人的數量。",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8是急需...",
            "&x&d&6&d&0&b&8你這種有實力冒險傭兵的力量的情況。",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8可以拜託你嗎？",
            "&x&d&6&d&0&b&8變種出現..我有不祥的預感。",
        ),
    )

    private val mes8 = arrayOf(
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8謝謝。這幫了城境治安不少忙。",
            "&x&d&6&d&0&b&8有機會的話，會再見面吧。",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8願你的前程充滿魔法的護持。",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8進城的商人都需要排查！",
            "&x&d&6&d&0&b&8你！...",
        ),
    )


    init {
        val world = Bukkit.getWorld("2k");
        val obj1 = ListenNPCObjective(this,"obj1",245,*mes1)
            .setInstruction("§7與 §e道格 §7對話");
        val obj2 = MoveToObjective(this,"obj2",Location(world,651.00, 76.00, 48.00),10.0)
            .setInstruction("§7前往貨物的丟失地");
        val obj3 = KillMultiMobsObjective(this,"obj3", mapOf("蛛菇沙原_卜普人" to 16))
            .setInstruction{ "§7擊殺  ${it.get("蛛菇沙原_卜普人")}/16 個蛛菇沙原的卜普人"};
        val obj4 = ListenNPCObjective(this,"obj4",245,*mes2)
            .setInstruction("§7與 §e道格 §7對話");
        val obj5 = KillMultiMobsObjective(this,"obj5", mapOf("蛛菇沙原_蛛菇怪" to 16))
            .setInstruction{ "§7擊殺  ${it.get("蛛菇沙原_蛛菇怪")}/16 個蛛菇沙原的蛛菇怪"};
        val obj6 = ListenNPCObjective(this,"obj6",245,*mes3)
            .setInstruction("§7與 §e道格 §7對話");
        val obj7 = MoveToObjective(this,"obj7",Location(world,651.00, 76.00, 48.00),10.0)
            .setInstruction("§7再次前往貨物的丟失地");
        val obj8 = KillMultiMobsObjective(this,"obj8", mapOf("蛛菇沙原_" to 64))
            .setInstruction{ "§7擊殺  ${it.get("蛛菇沙原_")}/64 個蛛菇沙原的怪物"};
        val obj9 = ListenNPCObjective(this,"obj9",245,*mes4)
            .setInstruction("§7與 §e道格 §7對話");
        val obj10 = ListenNPCObjective(this,"obj10",169,*mes5)
            .setInstruction("§7與 §e布思 §7對話");
        val obj11 = ListenNPCObjective(this,"obj11",169,*mes6)
            .setInstruction("§7與 §e布思 §7對話");
        val obj12 = ListenNPCObjective(this,"obj12",169,*mes7)
            .setInstruction("§7與 §e布思 §7對話");
        val obj13 = KillMultiMobsObjective(this,"obj13", mapOf("蛛菇沙原_卜普王" to 1))
            .setInstruction{ "§7擊殺  ${it.get("蛛菇沙原_卜普王")}/1 個蛛菇沙原的卜普王"};
        val obj14 = ListenNPCObjective(this,"obj14",169,*mes8)
            .setInstruction("§7與 §e布思 §7對話");

        pushObjective(obj1,obj2,obj3,obj4,obj5,obj6,obj7,obj8,obj9,obj10,obj11,obj12,obj13,obj14)

        addRewards(
            MessageReward("&a➯ &7解鎖支線任務: &2奧爾多&8-&c有看到我的刀仔嗎？"),            
            ClassExpReward(320),
            MoneyReward(400), //金牌 300~800
            QuestExpReward(100), //金牌 100~200
            ClassExpReward(300) //金牌 300~500
)
        addEndHook({
            Utils.command("lp user ${it.name} permission set side_gold_08")
        })

    }
}


val quest = Side_Gold_06();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



