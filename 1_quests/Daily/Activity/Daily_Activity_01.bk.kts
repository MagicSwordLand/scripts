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


val questID = "daily_activity_01"

class Daily_Activity_01: TimeLimitQuest(questID,"§7[§d活動每日§7] §f遺失的聖誕麋鹿"){

    val playerIcon = "%squests_icon%";
    val elfIcon = IconFonts.getNPC(254);

    private val playerName = "§c● §f%player_displayname%§r:";
    private val elfNPC = "§c● §e聖誕妖精§r:";

    private val mes1 = arrayOf(
        *Utils.getMessage(elfIcon,
            "$elfNPC",
            "&x&d&6&d&0&b&8嗚....",
            "&x&d&6&d&0&b&8你等一下！",
            "&x&d&6&d&0&b&8能幫我一件事情嗎",
        ),
        *Utils.getMessage(elfIcon,
            "$elfNPC",
            "&x&d&6&d&0&b&8因為聖誕節的緣故",
            "&x&d&6&d&0&b&8我的主人外出好幾天都不會回家",
            "&x&d&6&d&0&b&8沒想到我把主人養的麋鹿都弄丟了",
        ),
        *Utils.getMessage(elfIcon,
            "$elfNPC",
            "&x&d&6&d&0&b&8能幫我把牠們都找回來嗎?",
            "&x&d&6&d&0&b&8但牠們逃跑的速度很快",
            "&x&d&6&d&0&b&8所以需要先把牠們打暈....",
        ),
    )
    private val mes2 = arrayOf(
        *Utils.getMessage(elfIcon,
            "$elfNPC",
            "&x&d&6&d&0&b&8太感謝你了！",
            "&x&d&6&d&0&b&8不過遺失的麋鹿比我想像的還多",
            "&x&d&6&d&0&b&8明天可能還要再麻煩你一次",
        ),
    )
    init {
        val obj1 = ListenNPCObjective(this,"obj1",254,*mes1)
            .setInstruction("§7與 §e聖誕妖精 §7對話")

        val obj2 = KillMultiMobsObjective(this,"obj2", mapOf(
            "活動_聖誕麋鹿" to 10))
            .setInstruction{ "§7打暈聖誕麋鹿 ${it.get("活動_聖誕麋鹿")}/10"}
        val obj3 = ListenNPCObjective(this,"obj3",254,*mes2)
            .setInstruction("§7與 §e聖誕妖精 §7對話")

        pushObjective(obj1,obj2,obj3)

        addRewards(
            MoneyReward(50), //30錢幣
            QuestExpReward(10), //15傭兵聲
            ClassExpReward(20),
            MessageReward("&a➯ &7道具: &c聖誕氣息"),
        )
        addEndHook({
            Utils.command("get ${it.name} ACTIVITY A004 1")
            //Utils.command("lp user ${it.name} permission unset player.daily_activity_02")
        })
    }
}


val quest = Daily_Activity_01();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



