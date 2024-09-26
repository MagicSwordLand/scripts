@file:RequiredPlugins("ScriptedQuests","PlayerDataSync","MMOItems","MythicLib")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.quests.Quest
import net.brian.scriptedquests.compability.mythicmobs.KillMobsObjective
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.objectives.*
import net.brian.scriptedquests.rewards.*
import net.brian.scriptedquests.utils.Utils
import org.bukkit.Bukkit
import org.bukkit.Location

val playerIcon = "%squests_icon%";
val NPC1Icon = IconFonts.getNPC(66);

val questID = "side_copper_09"

class Side_Copper_09: Quest(questID,"§7[§b銅牌支線§7] §f夏日新商品"){



    private val playerName = "§c● §f%player_displayname%§r:";
    private val NPC1 = "§c● §e麵寶§r:";

    private val mes1 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8(看著手裡的食譜端詳中)",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e嘿~麵寶，妳在看什麼?",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8歐嗨~傭兵您好",
            "&x&d&6&d&0&b&8夏日來臨",
            "&x&d&6&d&0&b&8我正在思考新的夏季商品",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e夏季商品?什麼樣的商品?",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8嘿，豆花",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e奇怪，這裡不是烘培店嗎?",
            "§x§d§6§c§1§a§e怎麼會要銷售甜品?",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8我單純想看看甜品的賣向如何",
            "&x&d&6&d&0&b&8對了!我這裡剛好有委託",
            "&x&d&6&d&0&b&8傭兵您是否有沒有興趣呢?",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e好啊!要做些甚麼?",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8我需要您幫我去搜集一些材料",
            "&x&d&6&d&0&b&8等會製作豆花時會用到",
            "&x&d&6&d&0&b&8這紙條上有寫需要的材料 麻煩您幫忙搜集了~",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e(收下紙條)",
        ),
    )
    private val mes2 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8太感謝你了！",
            "&x&d&6&d&0&b&8我現在就來研發！",
        )
    )
    init {
        val world = Bukkit.getWorld("2k");
        val obj1 = ListenNPCObjective(this,"obj1",66,*mes1)
            .setInstruction("§7與 §e麵寶 §7對話");
        val obj2 = GiveMultiItemObjective(this,"obj2",66,
            mapOf(
                "COLLECTMATERIAL:M032" to 64,
                "MATERIAL:M002" to 32
            ))
            .setInstruction{"§7將 §7${it.get("COLLECTMATERIAL:M032")}/64 個稻草§7、${it.get("MATERIAL:M002")}/32 個 §7魔化樹藤  §7交給 §e麵寶§r"}
        val obj3 = ListenNPCObjective(this,"obj3",66,*mes2)
            .setInstruction("§7與 §e麵寶 §7對話");

        pushObjective(obj1,obj2,obj3)

        addRewards(
            MoneyReward(30), //30錢幣
            QuestExpReward(15), //15傭兵聲
            ClassExpReward(150),
        )
    }
}


val quest = Side_Copper_09();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



