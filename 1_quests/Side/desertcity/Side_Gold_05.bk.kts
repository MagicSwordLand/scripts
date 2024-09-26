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

val questID = "side_gold_05"

class Side_Gold_05: Quest(questID,"§7[§b金牌支線§7] §f出征前的準備"){


    val playerIcon = "%squests_icon%";
    val NPC1Icon = IconFonts.getNPC(52);

    private val playerName = "§c● §f%player_displayname%§r:";
    private val NPC1 = "§c● §e斐格§r:";

    private val mes1 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8呼...一直在準備出征",
            "&x&d&6&d&0&b&8內心很不安。",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8出征之前...",
            "&x&d&6&d&0&b&8我總是帶著智慧的古木的葉子去，",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8今天因為比集合時間晚到",
            "&x&d&6&d&0&b&8沒有準備。",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8雖然葉子沒有特別的力量，",
            "&x&d&6&d&0&b&8但是帶著那片葉子出征時，",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8就算進行戰鬥也能平安歸來。",
            "&x&d&6&d&0&b&8在出發之前還有點時間。",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8可以幫我帶來一片長在",
            "&x&d&6&d&0&b&8智慧的古木頂端的葉子嗎？",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e就交給我吧！",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8現在就快出發了",
            "&x&d&6&d&0&b&8請你快一點。",
        ),
    )
    private val mes2 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8說是迷信也好。",
            "&x&d&6&d&0&b&8只不過是在不知道會發生什麼事的戰場上",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8需要可以慰藉心靈的地方而已。",
            "&x&d&6&d&0&b&8現在終於安心一點了。",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8希望能平安地再見到傭兵冒險家。 ",
        ),
    )
    init {
        val world = Bukkit.getWorld("2k");
        val obj1 = ListenNPCObjective(this,"obj1",249,*mes1)
            .setInstruction("§7與 §e斐格 §7對話");
        val obj2 = MoveToObjective(this,"obj2",Location(world,456.77, 99.00, 13.73),5.0)
            .setInstruction("§7前往智慧的古木");
        val obj3 = GiveItemObjective(this,"obj3",249,"COLLECTMATERIAL:M026",1)
            .setInstruction{"交給 §e斐格 §7${it.amount}/1 個古木的葉子 &8(位於智慧的古木頂端)"};
        val obj4 = ListenNPCObjective(this,"obj4",249,*mes2)
            .setInstruction("§7與 §e斐格 §7對話");

        pushObjective(obj1,obj2,obj3,obj4)

        addRewards(
            MoneyReward(400), //金牌 300~800
            QuestExpReward(130), //金牌 100~200
            ClassExpReward(320) //金牌 300~500
        )
    }
}


val quest = Side_Gold_05();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



