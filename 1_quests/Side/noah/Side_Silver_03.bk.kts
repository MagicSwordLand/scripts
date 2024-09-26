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

val questID = "side_silver_03"

class Side_Silver_03: Quest(questID,"§7[§b銀牌支線§7] §f釀造素材"){


    val playerIcon = "%squests_icon%";
    val NPC1Icon = IconFonts.getNPC(67);

    private val playerName = "§c● §f%player_displayname%§r:";
    private val NPC1 = "§c● §e塔克§r:";

    private val mes1 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8嘿！這位傭兵",
            "&x&d&6&d&0&b&8我這邊正在研發一個新的藥水",
            "&x&d&6&d&0&b&8需要一些靈木來做研發素材",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8能幫我負責收集一些嗎?",
            "&x&d&6&d&0&b&8我會給你獎勵的",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e就交給我吧！",
        ),
    )
    private val mes2 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8太感謝了",
            "&x&d&6&d&0&b&8這是你的獎勵",
        ),
    )
    init {
        val world = Bukkit.getWorld("2k");
        val obj1 = ListenNPCObjective(this,"obj1",67,*mes1)
            .setInstruction("§7與 §e塔克 §7對話");
        val obj2 = GiveMultiItemObjective(this,"obj2",67,
            mapOf(
                "MATERIAL:M007" to 128,
            ))
            .setInstruction{"§7收集 §7${it.get("MATERIAL:M007")}/128 個靈木(石靈森林) §7交給 §e塔克§r"}
        val obj3 = ListenNPCObjective(this,"obj3",67,*mes2)
            .setInstruction("§7與 §e塔克 §7對話");
        pushObjective(obj1,obj2,obj3)

        addRewards(
            MoneyReward(120), //30錢幣
            QuestExpReward(25), //15傭兵聲
            ClassExpReward(250)
        )
    }
}


val quest = Side_Silver_03();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



