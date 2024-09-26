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
val NPC1Icon = IconFonts.getNPC(129);

val questID = "side_copper_07"

class Side_Copper_07: Quest(questID,"§7[§b銅牌支線§7] §f學者的困惑"){



    private val playerName = "§c● §f%player_displayname%§r:";
    private val NPC1 = "§c● §e亞里士德§r:";

    private val mes1 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8恩...這個...",
            "&x&d&6&d&0&b&8誰?是傭兵嗎?",
            "&x&d&6&d&0&b&8來的正好",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8我在這本書上看到有關毒氣林中的記載",
            "&x&d&6&d&0&b&8書中提到一個操控毒氣的人形生物",
            "&x&d&6&d&0&b&8據說他會自製毒氣酸奶當作糧食",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8我對這個東西非常感興趣",
            "&x&d&6&d&0&b&8可以去蒐集毒氣酸奶給我嗎?",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e沒問題，交給我吧！",
        ),
    )
    private val mes2 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8太感謝你了！",
            "&x&d&6&d&0&b&8我現在來研究，這是酬勞！",
        )
    )
    init {
        val world = Bukkit.getWorld("2k");
        val obj1 = ListenNPCObjective(this,"obj1",129,*mes1)
            .setInstruction("§7與 §e亞里士德 §7對話");
        val obj2 = GiveMultiItemObjective(this,"obj2",129,
            mapOf(
                "BOSSMATERIAL:M002" to 1,
            ))
            .setInstruction{"§7將 §7${it.get("BOSSMATERIAL:M002")}/1 個毒氣酸奶 交給 §e亞里士德§r"}
        val obj3 = ListenNPCObjective(this,"obj3",129,*mes2)
            .setInstruction("§7與 §e亞里士德 §7對話");

        pushObjective(obj1,obj2,obj3)

        addRewards(
            MoneyReward(20), //30錢幣
            ClassExpReward(200),
            QuestExpReward(10), //15傭兵聲
            MessageReward("&a➯ &7道具: &x&9&4&A&F&F&8屬性刷新卷軸"),
        )
        addEndHook({
            Utils.command("get ${it.name} CONSUMABLE C059")
        })
    }
}


val quest = Side_Copper_07();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



