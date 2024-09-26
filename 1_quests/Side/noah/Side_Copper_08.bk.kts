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
val NPC1Icon = IconFonts.getNPC(115);

val questID = "side_copper_08"

class Side_Copper_08: Quest(questID,"§7[§b銅牌支線§7] §f無法航行的區域"){



    private val playerName = "§c● §f%player_displayname%§r:";
    private val NPC1 = "§c● §e海斯§r:";

    private val mes1 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8這位傭兵，你來的剛好",
            "&x&d&6&d&0&b&8我這裡有個委託",
            "&x&d&6&d&0&b&8希望你能幫忙",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8諾亞鎮外有一個海岸叫彼遺古岸",
            "&x&d&6&d&0&b&8那邊有個神祕的能量正在不斷製造白骨怪物",
            "&x&d&6&d&0&b&8白骨怪物已經造成海岸的汙染了",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8導致那區域的海岸無法航行",
            "&x&d&6&d&0&b&8你能去幫我消滅大量的白骨怪物嗎?",
            "&x&d&6&d&0&b&8當然我會給你不錯的報酬",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e沒問題！",
        ),
    )
    private val mes2 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8太感謝你了！",
            "&x&d&6&d&0&b&8這樣那區域的海岸就能正常航行了",
            "&x&d&6&d&0&b&8這是委託的報酬！",
        )
    )
    init {
        val world = Bukkit.getWorld("2k");
        val obj1 = ListenNPCObjective(this,"obj1",115,*mes1)
            .setInstruction("§7與 §e海斯 §7對話");
        val obj2 = KillMobsObjective(this,"obj2","彼遺古岸_",50)
            .setInstruction { "擊殺 ${it.amount} / 50 個彼遺古岸的怪物" }
        val obj3 = ListenNPCObjective(this,"obj3",115,*mes2)
            .setInstruction("§7與 §e海斯 §7對話");

        pushObjective(obj1,obj2,obj3)

        addRewards(
            MoneyReward(40), //30錢幣
            QuestExpReward(20), //15傭兵聲
            ClassExpReward(200),
            MessageReward("&a➯ &7道具: &x&A&C&C&3&A&6技能刷新卷軸"),
        )
        addEndHook({
            Utils.command("get ${it.name} CONSUMABLE C060")
        })
    }
}


val quest = Side_Copper_08();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



