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


val questID = "daily_activity_04"

class Daily_Activity_04: TimeLimitQuest(questID,"§7[§d活動每日§7] §f神聖的麋鹿氣息"){

    val playerIcon = "%squests_icon%";
    val elfIcon = IconFonts.getNPC(254);

    private val playerName = "§c● §f%player_displayname%§r:";
    private val elfNPC = "§c● §e聖誕妖精§r:";

    private val mes1 = arrayOf(
        *Utils.getMessage(elfIcon,
            "$elfNPC",
            "&x&d&6&d&0&b&8....",
            "&x&d&6&d&0&b&8這位傭兵！",
            "&x&d&6&d&0&b&8能幫我一件事情嗎?",
        ),
        *Utils.getMessage(elfIcon,
            "$elfNPC",
            "&x&d&6&d&0&b&8因為我不小心把麋鹿們弄丟了",
            "&x&d&6&d&0&b&8許多麋鹿適應不了外面的世界...",
            "&x&d&6&d&0&b&8那些麋鹿留下的氣息能幫我收集起來嗎?",
        ),
    )
    private val mes2 = arrayOf(
        *Utils.getMessage(elfIcon,
            "$elfNPC",
            "&x&d&6&d&0&b&8謝謝...",
            "&x&d&6&d&0&b&8希望主人能透過這些氣息復活牠們...",
        ),
    )
    init {
        val obj1 = ListenNPCObjective(this,"obj1",254,*mes1)
            .setInstruction("§7與 §e聖誕妖精 §7對話")
        val obj2 = GiveMultiItemObjective(this,"obj2",254,
            mapOf(
                "ACTIVITY:A003" to 8,//白雲銅
            )).setInstruction { "將 ${it.get("ACTIVITY:A003")} / 8 個 麋鹿的氣息 並交給聖誕精靈\n§8(若無法繳交物品，請將物品切換至副手繳交)" }

        val obj3 = ListenNPCObjective(this,"obj3",254,*mes2)
            .setInstruction("§7與 §e聖誕妖精 §7對話")

        pushObjective(obj1,obj2,obj3)

        addRewards(
            MoneyReward(100), //30錢幣
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


val quest = Daily_Activity_04();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



