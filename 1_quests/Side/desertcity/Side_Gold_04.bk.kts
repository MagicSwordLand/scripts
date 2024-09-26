@file:RequiredPlugins("ScriptedQuests","PlayerDataSync","MMOItems","MythicLib")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.quests.Quest
import net.brian.scriptedquests.compability.classcore.ReachLevelObjective
import net.brian.scriptedquests.compability.mythicmobs.KillMobsObjective
import net.brian.scriptedquests.compability.mythicmobs.KillMultiMobsObjective
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.objectives.*
import net.brian.scriptedquests.rewards.*
import net.brian.scriptedquests.utils.Utils
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player

val questID = "side_gold_04"

class Side_Gold_04: Quest(questID,"§7[§b金牌支線§7] §f聆聽瞌睡老人的夢話"){


    val playerIcon = "%squests_icon%";
    val NPC1Icon = IconFonts.getNPC(230);
    val NPC2Icon = IconFonts.getNPC(175);

    private val playerName = "§c● §f%player_displayname%§r:";
    private val NPC1 = "§c● §e以撒§r:";
    private val NPC2 = "§c● §e布谷§r:";

    private val mes1 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8突然有一天",
            "&x&d&6&d&0&b&8劍與魔法消失了。",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8從這天開始",
            "&x&d&6&d&0&b&8劍與魔法的持有者們",
            "&x&d&6&d&0&b&8也慢慢消失",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8夢也不復存在",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8終於有一天",
            "&x&d&6&d&0&b&8最後一個持有者也消失了 ",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8劍與魔法成為了真正的傳說",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e你說的大陸就是這個大陸嗎？",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8呼呼呼zzz...",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e這是真的嗎？...",
            "§x§d§6§c§1§a§e我也應該活不到這麼久吧？",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e還是去打聽一下這個老人",
            "§x§d§6§c§1§a§e為什麼會睡在這裡吧？",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e也許傭兵所會有消息",
        ),
    )
    private val mes2 = arrayOf(
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8瞌睡的老人以撒嗎？",
            "&x&d&6&d&0&b&8據說是最近才來到城鎮的",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8城鎮的居民都說他在妖言惑眾呢",
            "&x&d&6&d&0&b&8但萬物又怎麼可能永恆阿？",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8你說是吧？",
            "&x&d&6&d&0&b&8也許有一天你也不會再出現",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e這樣嗎...",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8哈拉，別太灰心啦",
            "&x&d&6&d&0&b&8怎麼可以這麼容易就被影響呢",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8今天不成功的話還有明天",
            "&x&d&6&d&0&b&8明天不成功的話還有後天",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8後天不成功的話還有大後天",
            "&x&d&6&d&0&b&8無論走到什麼地方，都會有明天",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8在有限的時間裡珍惜每分每秒",
            "&x&d&6&d&0&b&8就不會再有遺憾的事了",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8諾瓦拉德大陸...",
            "&x&d&6&d&0&b&8等著你我去守護呢！",
        ),
    )
    init {
        val world = Bukkit.getWorld("2k");
        val obj1 = ListenNPCObjective(this,"obj1",230,*mes1)
            .setInstruction("§7與 §e以撒 §7對話");
        val obj2 = MoveToObjective(this,"obj2",Location(world,441.43, 76.00, -68.28),5.0)
            .setInstruction("§7前往阿納斯塔城的傭兵所");
        val obj3 = ListenNPCObjective(this,"obj3",175,*mes2)
            .setInstruction("§7與 §e布谷 §7對話");

        pushObjective(obj1,obj2,obj3)

        addRewards(
            MoneyReward(300), //金牌 300~800
            QuestExpReward(120), //金牌 100~200
            ClassExpReward(300) //金牌 300~500
        )
    }
}


val quest = Side_Gold_04();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



