@file:RequiredPlugins("ScriptedQuests")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.quests.Quest
import net.brian.scriptedquests.compability.mythicmobs.KillMultiMobsObjective
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.objectives.GiveItemObjective
import net.brian.scriptedquests.objectives.ListenNPCObjective
import net.brian.scriptedquests.objectives.ListenTalkObjective
import net.brian.scriptedquests.quests.TimeLimitQuest
import net.brian.scriptedquests.rewards.ClassExpReward
import net.brian.scriptedquests.rewards.MessageReward
import net.brian.scriptedquests.rewards.MoneyReward
import net.brian.scriptedquests.rewards.QuestExpReward
import net.brian.scriptedquests.utils.Utils
import org.bukkit.Bukkit
import org.bukkit.entity.Player

val yankoIcon = IconFonts.getNPC(55);
val playerIcon = "%squests_icon%";
val questID = "other_hs"
fun command(line: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),line);
}

class Other_Hs: Quest(questID,"§7[§d特殊委託§7] §f天賦技能"){
    private val playerName = "§c● §f%player_displayname%§r:";
    private val yankoNPC = "§c● §e赫巴斯§r:";

    private val mes1 = arrayOf(
        *Utils.getMessage(yankoIcon,
            "$yankoNPC",
            "&x&d&6&d&0&b&8小夥子，好久不見阿",
            "&x&d&6&d&0&b&8你的實力看起來強大了不少",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e是的，不過我總覺得我的實力遠遠不足阿...",
        ),
        *Utils.getMessage(yankoIcon,
            "$yankoNPC",
            "&x&d&6&d&0&b&8這樣啊，那你來我這就對了",
            "&x&d&6&d&0&b&8世界上總有許多變強的方法",
            "&x&d&6&d&0&b&8其中一個就是善用自身的天賦",
        ),
        *Utils.getMessage(yankoIcon,
            "$yankoNPC",
            "&x&d&6&d&0&b&8我相信你已經很清楚天賦可以帶給你的屬性加成",
            "&x&d&6&d&0&b&8這我們稱作天賦屬性，但還有一種力量",
            "&x&d&6&d&0&b&8被傭兵們稱作天賦技能",
        ),
        *Utils.getMessage(yankoIcon,
            "$yankoNPC",
            "&x&d&6&d&0&b&8天賦技能可以激發自身的潛力",
            "&x&d&6&d&0&b&8做出常人無法做到的動作及技巧",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e那我要怎麼樣才能學會天賦技能?",
        ),
        *Utils.getMessage(yankoIcon,
            "$yankoNPC",
            "&x&d&6&d&0&b&8你已經擁有使用天賦技能的實力了",
            "&x&d&6&d&0&b&8試試看使用/hs來解鎖自己想要使用的天賦技能吧！",
        ),
    )


    init {
        cancelAble = false;

        val obj1 = ListenNPCObjective(this,"obj1",55,*mes1)
            .setInstruction("§7與 §e赫巴斯 §7對話");

        pushObjective(obj1);
            //MessageReward("&a➯ &7錢幣: &f+50"),
        addRewards(
            MoneyReward(20), //30錢幣
            QuestExpReward(10), //15傭兵聲望
            MessageReward("&a➯ &7解鎖功能: &f天賦技能&7(/hs)"),
        )
        addEndHook({
            Utils.command("lp user ${it.name} permission set hs.player")
        })
    }
}

val quest = Other_Hs();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}