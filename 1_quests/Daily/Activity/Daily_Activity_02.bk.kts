@file:RequiredPlugins("ScriptedQuests","PlayerDataSync","MMOItems","MythicLib")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.player.PlayerCompleter
import net.brian.scriptedquests.api.quests.Quest
import net.brian.scriptedquests.compability.mythicmobs.KillMobsObjective
import net.brian.scriptedquests.compability.mythicmobs.KillMultiMobsObjective
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.conversation.PlayerOption
import net.brian.scriptedquests.objectives.*
import net.brian.scriptedquests.quests.TimeLimitQuest
import net.brian.scriptedquests.rewards.*
import net.brian.scriptedquests.utils.Utils
import org.bukkit.Bukkit
import org.bukkit.Location


val questID = "daily_activity_02"

class Daily_Activity_02: TimeLimitQuest(questID,"§7[§d活動每日§7] §f美麗的聖誕花束"){

    val playerIcon = "%squests_icon%";
    val elfIcon = IconFonts.getNPC(254);

    private val playerName = "§c● §f%player_displayname%§r:";
    private val elfNPC = "§c● §e聖誕妖精§r:";

    private val mes1 = arrayOf(
        *Utils.getMessage(elfIcon,
            "$elfNPC",
            "&x&d&6&d&0&b&8好美的花",
            "&x&d&6&d&0&b&8這位傭兵！",
            "&x&d&6&d&0&b&8能幫我一件事情嗎?",
        ),
        *Utils.getMessage(elfIcon,
            "$elfNPC",
            "&x&d&6&d&0&b&8我剛剛在路上撿到了一朵花",
            "&x&d&6&d&0&b&8非常適合聖誕節的氣氛",
            "&x&d&6&d&0&b&8能幫我多採集一點嗎?",
        ),
    )
    private val mes2 = arrayOf(
        *Utils.getMessage(elfIcon,
            "$elfNPC",
            "&x&d&6&d&0&b&8太感謝你了！",
            "&x&d&6&d&0&b&8有了這些花",
            "&x&d&6&d&0&b&8這次的聖誕節一定很完美！",
        ),
    )
    init {
        val obj1 = ListenNPCObjective(this,"obj1",254,*mes1)
            .setInstruction("§7與 §e聖誕妖精 §7對話")

        val obj2 = GiveItemObjective(this,"obj2",254,"ACTIVITY:A005",16)
            .setInstruction { "採集 ${it.amount} / 16 個 聖誕的花束 並交給聖誕精靈" }

        val obj3 = ListenNPCObjective(this,"obj3",254,*mes2)
            .setInstruction("§7與 §e聖誕妖精 §7對話")

        pushObjective(obj1,obj2,obj3)

        addRewards(
            MoneyReward(80), //30錢幣
            QuestExpReward(10), //15傭兵聲
            ClassExpReward(30),
            MessageReward("&a➯ &7道具: &c聖誕氣息"),
        )
        addEndHook({
            Utils.command("get ${it.name} ACTIVITY A004 1")
            //Utils.command("lp user ${it.name} permission unset player.daily_activity_02")
        })
    }
}


val quest = Daily_Activity_02();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



