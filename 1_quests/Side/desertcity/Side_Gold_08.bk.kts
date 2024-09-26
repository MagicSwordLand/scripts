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

val questID = "side_gold_08"

class Side_Gold_08: Quest(questID,"§7[§b金牌支線§7] §f有看到我的刀仔嗎？"){


    val playerIcon = "%squests_icon%";
    val NPC1Icon = IconFonts.getNPC(244);

    private val playerName = "§c● §f%player_displayname%§r:";
    private val NPC1 = "§c● §e奧爾多§r:";

    private val mes1 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8呼，到底跑到哪去了？",
            "&x&d&6&d&0&b&8外地人...哦不，傭兵冒險家！",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e怎麼了？",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "§x§d§6§c§1§a§e你在這附近有看到我的刀仔了嗎？",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e你說的是工具嗎？",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8不，刀仔。刀！仔！",
            "&x&d&6&d&0&b&8唉...我說的是紅色的紅砂幼鳥。",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8他是我的戰鬥夥伴，叫做刀仔！",
            "&x&d&6&d&0&b&8你怎麼這麼沒見過世面...",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8馬上就要出征到比利亞達塔了，",
            "&x&d&6&d&0&b&8我的刀仔在某處消失後就沒出現了。",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8可以和我一起找找嗎？",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8我的刀仔非常喜歡這種果實，",
            "&x&d&6&d&0&b&8只要拿給牠看，就能輕易的把牠帶回來。",
        ),
    )

    private val mes2 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8這次也慢一步的話就完了",
            "&x&d&6&d&0&b&8...到底去哪了？再去找找看!",
        ),
    )

    private val mes3 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8刀仔！你怎麼跑到了這種地方...",
            "&x&d&6&d&0&b&8多虧你度過危機了。",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "§x§d§6§c§1§a§e下次見面時我一定會再報答你的！",
        ),
    )

    private val mes4 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8刀仔！你怎麼跑到了這種地方...",
            "&x&d&6&d&0&b&8多虧你度過危機了。",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "§x§d§6§c§1§a§e下次見面時我一定會再報答你的！",
        ),
    )

    init {
        val world = Bukkit.getWorld("2k");
        val obj1 = ListenNPCObjective(this,"obj1",244,*mes1)
            .setInstruction("§7與 §e奧爾多 §7對話");
        val obj2 = MoveToObjective(this,"obj2",Location(world,279.00, 74.00, 16.00),5.0)
            .setInstruction("§7前往寵物可能失蹤的地點1")
            .setEndProcess{
                it.sendMessage("§c到達目的地，但是沒有及時抓到...回去找 §e奧爾多")}
        val obj3 = ListenNPCObjective(this,"obj3",244,*mes2)
            .setInstruction("§7與 §e奧爾多 §7對話");
        val obj4 = MoveToObjective(this,"obj4",Location(world,374.00, 102.00, -47.00),5.0)
            .setInstruction("§7前往寵物可能失蹤的地點2")
            .setEndProcess{
                it.sendMessage("§a到達目的地，抓到了！")}
        val obj5 = ListenNPCObjective(this,"obj5",244,*mes3)
            .setInstruction("§7與 §e奧爾多 §7對話");

        pushObjective(obj1,obj2,obj3,obj4,obj5)

        addRewards(
            MessageReward("&a➯ &7解鎖支線任務: &2奧爾多&8-&c寵物治療劑的補給"),             
            MoneyReward(500), //金牌 300~800
            QuestExpReward(150), //金牌 100~200
            ClassExpReward(400) //金牌 300~500
            )

        addEndHook({
    Utils.command("lp user ${it.name} permission set side_gold_09")
                })

    }
}


val quest = Side_Gold_08();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



