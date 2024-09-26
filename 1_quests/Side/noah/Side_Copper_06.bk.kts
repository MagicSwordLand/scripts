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
val NPC1Icon = IconFonts.getNPC(20);

val questID = "side_copper_06"

class Side_Copper_06: Quest(questID,"§7[§b銅牌支線§7] §f喬布的煩惱"){



    private val playerName = "§c● §f%player_displayname%§r:";
    private val NPC1 = "§c● §e喬布§r:";

    private val mes1 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8唉...",
            "&x&d&6&d&0&b&8恩?這位傭兵你好...",
            "&x&d&6&d&0&b&8你是來使用倉庫的嗎?",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8不過目前可能沒辦法使用倉庫...",
            "&x&d&6&d&0&b&8前幾天我收到上級的任務",
            "&x&d&6&d&0&b&8去哥布林部落附近幫忙回收物品",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8沒想到突然出現一群哥布林",
            "&x&d&6&d&0&b&8還好有其他傭兵的保護",
            "&x&d&6&d&0&b&8但倉庫的總鑰匙卻被搶走了...",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e天啊！那怎麼辦?",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8如果被發現鑰匙不見了",
            "&x&d&6&d&0&b&8我一定會失去這份工作的！",
            "&x&d&6&d&0&b&8拜託了！能幫我去哥布林部落找回鑰匙嗎?",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e好吧！",
            "§x§d§6§c§1§a§e鑰匙不見我也沒辦法使用倉庫",
            "§x§d§6§c§1§a§e這包在我身上吧！",
        ),
    )
    private val mes2 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8太感謝你了！",
            "&x&d&6&d&0&b&8雖然不多，但這是酬勞！",
        )
    )
    init {
        val world = Bukkit.getWorld("2k");
        val obj1 = ListenNPCObjective(this,"obj1",95,*mes1)
            .setInstruction("§7與 §e喬布 §7對話");
        val obj2 = KillMobsObjective(this,"obj2","哥布林部落_",30)
            .setInstruction { "擊殺 ${it.amount} / 30 個哥布林" }
            .setEndProcess {
                it.sendMessage("§7[§c提示§7] §f已找回倉庫鑰匙")
            };
        val obj3 = ListenNPCObjective(this,"obj3",95,*mes2)
            .setInstruction("§7與 §e喬布 §7對話");

        pushObjective(obj1,obj2,obj3)

        addRewards(
            MoneyReward(40), //30錢幣
            QuestExpReward(15), //15傭兵聲
            ClassExpReward(200),
            MessageReward("&a➯ &7系統: &7倉庫解鎖6格"),
        )
        addEndHook({
            Utils.command("storage addunlocked ${it.name} 6")
        })
    }
}


val quest = Side_Copper_06();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



