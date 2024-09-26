@file:RequiredPlugins("ScriptedQuests","PlayerDataSync","MMOItems","MythicLib")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.quests.Quest
import net.brian.scriptedquests.compability.mythicmobs.KillMobsObjective
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.objectives.GiveItemObjective
import net.brian.scriptedquests.objectives.ListenNPCObjective
import net.brian.scriptedquests.objectives.ListenTalkObjective
import net.brian.scriptedquests.objectives.MoveToObjective
import net.brian.scriptedquests.rewards.*
import net.brian.scriptedquests.utils.Utils
import org.bukkit.Bukkit
import org.bukkit.Location

val playerIcon = "%squests_icon%";
val NPCIcon = IconFonts.getNPC(59);

val questID = "side_copper_02"

class Side_Copper_02: Quest(questID,"§7[§b銀牌支線§7] §f艾靈長廊中的巨龍"){



    private val playerName = "§c● §f%player_displayname%§r:";
    private val NPCName = "§c● §e梅里§r:";

    private val mes1 = arrayOf(
        *Utils.getMessage(NPCIcon,
            "$NPCName",
            "&x&d&6&d&0&b&8尊貴的傭兵你好",
            "&x&d&6&d&0&b&8前陣子其他傭兵回報艾靈長廊存在一隻巨龍",
            "&x&d&6&d&0&b&8經過傭兵公會的探查",
        ),
        *Utils.getMessage(NPCIcon,
            "$NPCName",
            "&x&d&6&d&0&b&8這個巨龍的屍身",
            "&x&d&6&d&0&b&8對傭兵公會有極大的幫助",
            "&x&d&6&d&0&b&8能幫忙擊殺艾靈守衛巨龍嗎?",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e知道了",
            "§x§d§6§c§1§a§e就交給我吧！",
        )
    )
    private val mes2 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e我完成委託了",
            "§x§d§6§c§1§a§e那隻巨龍還真的有點難纏",
        ),
        *Utils.getMessage(NPCIcon,
            "$NPCName",
            "&x&d&6&d&0&b&8那這樣委託就算結束了",
            "&x&d&6&d&0&b&8這是你這次委託的獎勵",
        )
    )
    init {
        val world = Bukkit.getWorld("2k");
        val obj1 = ListenNPCObjective(this,"obj1",59,*mes1)
            .setInstruction("§7與 §e梅里 §7對話");
        val obj2 = MoveToObjective(this,"obj2",Location(world,-693.47, 87.00, 585.23),8.0)
            .setInstruction("§7前往 艾靈長廊");
        val obj3 = KillMobsObjective(this,"obj3","艾靈長廊_艾靈守衛巨龍",12)
            .setInstruction { "擊殺 ${it.amount} / 12隻 艾靈守衛巨龍" };
        val obj4 = ListenNPCObjective(this,"obj4",59,*mes2)
            .setInstruction("§7與 §e梅里 §7對話");

        pushObjective(obj1,obj2,obj3,obj4)

        addRewards(
            MoneyReward(80), //30錢幣
            QuestExpReward(25), //15傭兵聲望
            ClassExpReward(200),
            MessageReward("&a➯ &7飾品: &7迷你艾靈守衛巨龍")
        )
        addEndHook({
            Utils.command("get ${it.name} DOLL A002 1 0")
        })
    }
}


val quest = Side_Copper_02();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



