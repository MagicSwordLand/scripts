@file:RequiredPlugins("ScriptedQuests","PlayerDataSync","MMOItems","MythicLib")

package workspace

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.quests.Quest
import net.brian.scriptedquests.compability.mythicmobs.KillMultiMobsObjective
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.objectives.*
import net.brian.scriptedquests.rewards.*
import net.brian.scriptedquests.utils.Utils
import org.bukkit.Bukkit

val questID = "side_gold_07"

class Side_Gold_07: Quest(questID,"§7[§b金牌支線§7] §f瞭解薩哈礦場的情況"){


    val playerIcon = "%squests_icon%";
    val NPC1Icon = IconFonts.getNPC(237);
    val NPC2Icon = IconFonts.getNPC(201);
    val NPC3Icon = IconFonts.getNPC(203);
    val NPC4Icon = IconFonts.getNPC(202);

    private val playerName = "§c● §f%player_displayname%§r:";
    private val NPC1 = "§c● §e約翰夫§r:";
    private val NPC2 = "§c● §e塞里茲§r:";
    private val NPC3 = "§c● §e阿麥菈§r:";
    private val NPC4 = "§c● §e伊魯阿爾§r:";

    private val mes1 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8你是..？啊，剛剛好，",
            "&x&d&6&d&0&b&8你可以幫我們的忙嗎？",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e怎麼了？",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "§x§d§6§c§1§a§e我們是為了了解在這個礦場所發生的情況，",
            "§x§d§6§c§1§a§e被派到這裡的鎮民。",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "§x§d§6§c§1§a§e",
            "&x&d&6&d&0&b&8我們也是剛剛才到，",
            "&x&d&6&d&0&b&8不太清楚發生了什麼事...",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8麻煩你跟礦場的 塞里茲 ",
            "&x&d&6&d&0&b&8打聽詳細的故事吧。",
        )
    )

    private val mes2 = arrayOf(
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8能在這種地方遇見你，",
            "&x&d&6&d&0&b&8真是太好了。",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "&x&d&6&d&0&b&8你認識我嗎？",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8擊敗巨龍的事跡傳遍了諾亞島...",
            "&x&d&6&d&0&b&8當然，這次的情況比那邊更加嚴峻...",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8我有砂礦鎮的指揮權，",
            "&x&d&6&d&0&b&8本來是由礦長進行指揮的，",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8不過現在他好像...",
            "&x&d&6&d&0&b&8還搞不懂什麼狀況，",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8我們是為了了解這裡的情況被派來的..",
            "&x&d&6&d&0&b&8實際看到這裡比想像中差，",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8來到這裡的路上還看到恐怖的情景，",
            "&x&d&6&d&0&b&8在裡面居然出現晃動的東西..",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8..所以正覺得傷腦筋，",
            "&x&d&6&d&0&b&8是要進去了解狀況呢，還是空手回去。",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8如果可以的話，",
            "&x&d&6&d&0&b&8你能幫我們的忙嗎？",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8請幫忙掃蕩這周圍的怪物後，",
            "&x&d&6&d&0&b&8再告訴我有關牠們的事就可以了。",
        ),
    )

    private val mes3 = arrayOf(
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8這麼快就回來啦！",
            "&x&d&6&d&0&b&8..原來情況是那樣，我知道了。",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8我們會根據你提供的資訊",
            "&x&d&6&d&0&b&8整理成文件...",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8可以麻煩你送去給",
            "&x&d&6&d&0&b&8礦場上的學者研究嗎？",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8他就在礦坑的崖邊",
        ),
    )

    private val mes4 = arrayOf(
        *Utils.getMessage(NPC3Icon,
            "$NPC3",
            "&x&d&6&d&0&b&8阿...謝謝你帶來的文件",
            "&x&d&6&d&0&b&8聽過你的名聲",
        ),
        *Utils.getMessage(NPC3Icon,
            "$NPC3",
            "&x&d&6&d&0&b&8很抱歉，又要請你幫忙了..",
            "&x&d&6&d&0&b&8因為停留在這個礦場的時間比較久，",
        ),
        *Utils.getMessage(NPC3Icon,
            "$NPC3",
            "&x&d&6&d&0&b&8大家都受到了這樣的痛苦，",
            "&x&d&6&d&0&b&8我的研究進展也很緩慢",
        ),
        *Utils.getMessage(NPC3Icon,
            "$NPC3",
            "&x&d&6&d&0&b&8去找那邊的 伊魯阿爾 聆聽故事後，",
            "&x&d&6&d&0&b&8再幫我們一點忙吧。",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e好的沒問題，包在我身上",
        ),
    )

    private val mes5 = arrayOf(
        *Utils.getMessage(NPC4Icon,
            "$NPC4",
            "&x&d&6&d&0&b&8停留在這裡的時間變久之後，",
            "&x&d&6&d&0&b&8糧食也快吃完了，",
        ),
        *Utils.getMessage(NPC4Icon,
            "$NPC4",
            "&x&d&6&d&0&b&8大家都是靠著堅強的意志力和體力，",
            "&x&d&6&d&0&b&8在附近打獵或採集撐下去的，",
        ),
        *Utils.getMessage(NPC4Icon,
            "$NPC4",
            "&x&d&6&d&0&b&8現在大家都覺得很累，",
            "&x&d&6&d&0&b&8所以啊..",
        ),
        *Utils.getMessage(NPC4Icon,
            "$NPC4",
            "&x&d&6&d&0&b&8你可以幫我們找來",
            "&x&d&6&d&0&b&8糖度高的水果嗎？",
        ),
    )

    private val mes6 = arrayOf(
        *Utils.getMessage(NPC4Icon,
            "$NPC4",
            "&x&d&6&d&0&b&8謝謝，真的很謝謝你..！",
            "&x&d&6&d&0&b&8託你的福，大家總算能振作起來了!",
        ),
    )


    init {
        val world = Bukkit.getWorld("2k");
        val obj1 = ListenNPCObjective(this,"obj1",216,*mes1)
            .setInstruction("§7與 §e約翰夫 §7對話");
        val obj2 = ListenNPCObjective(this,"obj2",201,*mes2)
            .setInstruction("§7與 §e塞里茲 §7對話");
        val obj3 = KillMultiMobsObjective(this,"obj3", mapOf("薩哈南海岸礦鎮_裂縫章魚" to 12))
            .setInstruction{ "§7擊殺  ${it.get("薩哈南海岸礦鎮_裂縫章魚")}/12 個薩哈南海岸礦鎮的裂縫章魚"};
        val obj4 = ListenNPCObjective(this,"obj4",201,*mes3)
            .setInstruction("§7與 §e塞里茲 §7對話");
        val obj5 = ListenNPCObjective(this,"obj5",203,*mes4)
            .setInstruction("§7與 §e阿麥菈 §7對話");
        val obj6 = ListenNPCObjective(this,"obj6",202,*mes5)
            .setInstruction("§7與 §e伊魯阿爾 §7對話");
        val obj7 = GiveItemObjective(this,"obj7",202,"COLLECTMATERIAL:M019",64)
            .setInstruction{"交給 §e伊魯阿爾 §7${it.amount}/64 個沙沙莓 &8(位於阿納斯塔城西城墻外)"};
        val obj8 = ListenNPCObjective(this,"obj8",202,*mes6)
            .setInstruction("§7與 §e伊魯阿爾 §7對話");

        pushObjective(obj1,obj2,obj3,obj4,obj5,obj6,obj7,obj8)

        addRewards(
            MoneyReward(600), //金牌 300~800
            QuestExpReward(130), //金牌 100~200
            ClassExpReward(360) //金牌 300~500
        )
    }
}


val quest = Side_Gold_07();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



