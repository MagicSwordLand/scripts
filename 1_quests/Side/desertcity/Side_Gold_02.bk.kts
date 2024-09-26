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

val questID = "side_gold_02"

class Side_Gold_02: Quest(questID,"§7[§b金牌支線§7] §f煤炭包的防潮運輸"){


    val playerIcon = "%squests_icon%";
    val NPC1Icon = IconFonts.getNPC(178);

    private val playerName = "§c● §f%player_displayname%§r:";
    private val NPC1 = "§c● §e魯福§r:";

    private val mes1 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8可以拜託你一件事嗎？",
            "&x&d&6&d&0&b&8我在智慧的古木內保存了煤炭包。",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8是從我的故鄉辛辛苦苦",
            "&x&d&6&d&0&b&8搬回來的珍貴物品。",
            "&x&d&6&d&0&b&8煤炭不耐潮，很難在野外保存。",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8我不能離開鐵匠鋪，",
            "&x&d&6&d&0&b&8所以才這樣拜託你。",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8請幫我取回幾包煤炭包。",
            "&x&d&6&d&0&b&8煤炭的重量應該不輕。",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e就交給我吧！",
        ),
    )
    private val mes2 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8謝謝你。雖然很微薄，",
            "&x&d&6&d&0&b&8但這是你接受我請託的謝禮。",
        ),
    )
    init {
        val world = Bukkit.getWorld("2k");
        val obj1 = ListenNPCObjective(this,"obj1",178,*mes1)
            .setInstruction("§7與 §e魯福 §7對話");
        val obj2 = MoveToObjective(this,"obj2",Location(world,366.00, 76.00, -52.00),10.0)
            .setInstruction("§7前往智慧的古木");
        val obj3 = GiveItemObjective(this,"obj3",178,"COLLECTMATERIAL:M035",3)
            .setInstruction{"交給 §e魯福 §7${it.amount}/3 個煤炭包 &8(位於智慧的古木附近)"};
        val obj4 = ListenNPCObjective(this,"obj4",178,*mes2)
            .setInstruction("§7與 §e魯福 §7對話");

        pushObjective(obj1,obj2,obj3,obj4)

        addRewards(
            MoneyReward(300), //金牌 300~800
            QuestExpReward(110), //金牌 100~200
            ClassExpReward(400) //金牌 300~500
        )
    }
}


val quest = Side_Gold_02();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



