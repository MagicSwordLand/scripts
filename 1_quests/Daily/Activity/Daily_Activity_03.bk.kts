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


val questID = "daily_activity_03"

class Daily_Activity_03: TimeLimitQuest(questID,"§7[§d活動每日§7] §f聖誕裝飾"){

    val playerIcon = "%squests_icon%";
    val elfIcon = IconFonts.getNPC(254);

    private val playerName = "§c● §f%player_displayname%§r:";
    private val elfNPC = "§c● §e聖誕妖精§r:";

    private val mes1 = arrayOf(
        *Utils.getMessage(elfIcon,
            "$elfNPC",
            "&x&d&6&d&0&b&8今年的聖誕節要怎麼裝飾呢~",
            "&x&d&6&d&0&b&8這位傭兵！",
            "&x&d&6&d&0&b&8能幫我一件事情嗎?",
        ),
        *Utils.getMessage(elfIcon,
            "$elfNPC",
            "&x&d&6&d&0&b&8你可以幫我收集一些材料嗎?",
            "&x&d&6&d&0&b&8我需要這些材料",
            "&x&d&6&d&0&b&8這樣才能開始製作聖誕節的裝飾~",
        ),
    )
    private val mes2 = arrayOf(
        *Utils.getMessage(elfIcon,
            "$elfNPC",
            "&x&d&6&d&0&b&8太感謝你了！",
            "&x&d&6&d&0&b&8我要來發揮我的創意了！",
        ),
    )
    init {
        val obj1 = ListenNPCObjective(this,"obj1",254,*mes1)
            .setInstruction("§7與 §e聖誕妖精 §7對話")

        val obj2 = GiveMultiItemObjective(this,"obj2",254,
            mapOf(
                "COLLECTMATERIAL:M009" to 16,//白雲銅
                "COLLECTMATERIAL:M002" to 20,//靈木
                "MATERIAL:M007" to 12,//靈木
            ))
            .setInstruction{"§7採收 ${it.get("COLLECTMATERIAL:M009")} / 16 個白雲銅、\n、${it.get("COLLECTMATERIAL:M002")} / 20 鱗鑌、\n ${it.get("MATERIAL:M007")} / 12 靈木 並交給 §e聖誕精靈"}
        val obj3 = ListenNPCObjective(this,"obj3",254,*mes2)
            .setInstruction("§7與 §e聖誕妖精 §7對話")

        pushObjective(obj1,obj2,obj3)

        addRewards(
            MoneyReward(40), //30錢幣
            QuestExpReward(10), //15傭兵聲
            ClassExpReward(35),
            MessageReward("&a➯ &7道具: &c聖誕氣息"),
        )
        addEndHook({
            Utils.command("get ${it.name} ACTIVITY A004 1")
            //Utils.command("lp user ${it.name} permission unset player.daily_activity_02")
        })
    }
}


val quest = Daily_Activity_03();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



