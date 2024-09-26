@file:RequiredPlugins("ScriptedQuests")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.quests.Quest
import net.brian.scriptedquests.compability.mythicmobs.KillMultiMobsObjective
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.objectives.GiveItemObjective
import net.brian.scriptedquests.objectives.GiveMultiItemObjective
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

val yankoIcon = IconFonts.getNPC(5);
val playerIcon = "%squests_icon%";
val questID = "other_rank_02"
fun command(line: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),line);
}

class Other_Rank_02: Quest(questID,"§7[§d特殊委託§7] §f晉升金牌傭兵"){
    private val playerName = "§c● §f%player_displayname%§r:";
    private val yankoNPC = "§c● §e焱恪§r:";

    private val mes1 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e焱恪！",
            "§x§d§6§c§1§a§e我已經準備好晉升金牌傭兵了",
        ),
        *Utils.getMessage(yankoIcon,
            "$yankoNPC",
            "&x&d&6&d&0&b&8恩?",
            "&x&d&6&d&0&b&8天啊！",
        ),
        *Utils.getMessage(yankoIcon,
            "$yankoNPC",
            "&x&d&6&d&0&b&8小子，看來你實力不錯",
            "&x&d&6&d&0&b&8但金牌傭兵可不是這麼簡單的",
            "&x&d&6&d&0&b&8他需要有能夠獨立的實力",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e放心！",
            "§x§d§6§c§1§a§e我已經做好準備了",
        ),
        *Utils.getMessage(yankoIcon,
            "$yankoNPC",
            "&x&d&6&d&0&b&8哈哈哈",
            "&x&d&6&d&0&b&8我已經開始期待了",
            "&x&d&6&d&0&b&8這是金牌傭兵需要完成的委託",
        )
    )
    private val mes2 = arrayOf(
        *Utils.getMessage(yankoIcon,
            "$yankoNPC",
            "&x&d&6&d&0&b&8沒想到，你真正成為金牌傭兵了",
            "&x&d&6&d&0&b&8你這個年紀成為金牌傭兵的人",
            "&x&d&6&d&0&b&8整個帝國都只有寥寥幾人而已",
        ),
        *Utils.getMessage(yankoIcon,
            "$yankoNPC",
            "&x&d&6&d&0&b&8上次這年紀成為金牌傭兵的人",
            "&x&d&6&d&0&b&8如今都已經是黑曜傭兵了",
            "&x&d&6&d&0&b&8成為帝國不可或缺的戰力之一",
        ),
        *Utils.getMessage(yankoIcon,
            "$yankoNPC",
            "&x&d&6&d&0&b&8我非常看好你！",
            "&x&d&6&d&0&b&8恭喜你成為金牌傭兵！",
        )
    )


    init {

        val obj1 = ListenTalkObjective(this,"obj1",*mes1)
            .setInstruction("§7與 §e焱格 §7對話");
        val obj2 = KillMultiMobsObjective(this,"obj2", mapOf(
            "石靈森林_元素魔像" to 1,
            "低語森林_死靈衛士" to 1,
            "艾靈長廊_艾靈守衛巨龍" to 1))
            .setInstruction{ "§7元素魔像§8(石靈森林)§7 ${it.get("石靈森林_元素魔像")}/1、§7擊殺死靈衛士§8(低語森林)§7 ${it.get("低語森林_死靈衛士")}/1、艾靈守衛巨龍§8(艾靈長廊)§7 ${it.get("艾靈長廊_艾靈守衛巨龍")}/1"}
        val obj3 = GiveMultiItemObjective(this,"obj3",5,
            mapOf(
            "COLLECTMATERIAL:M007" to 128,//生金礦
            "COLLECTMATERIAL:M002" to 128,//鱗鑌
            "COLLECTMATERIAL:M033" to 64,//惡沼花
            "COLLECTMATERIAL:M008" to 48,//蒼山金
            "COLLECTMATERIAL:M013" to 32,//黑曜石
            ))
            .setInstruction{"採集 ${it.get("COLLECTMATERIAL:M007")} / 128 個生金礦、${it.get("COLLECTMATERIAL:M002")} / 128 個鱗鑌、${it.get("COLLECTMATERIAL:M033")} / 64 個惡沼花、${it.get("COLLECTMATERIAL:M008")} / 48 個蒼山金、${it.get("COLLECTMATERIAL:M013")} / 32 個黑曜石 並交給 §e焱恪§r"}
        val obj4 = ListenTalkObjective(this,"obj4",*mes2)
            .setInstruction("§7與 §e焱格 §7對話");
        pushObjective(obj1,obj2,obj3,obj4);
        addRewards(
            MoneyReward(200),
            QuestExpReward(50), //
            ClassExpReward(1000),
            MessageReward("&a➯ &7系統: &7倉庫解鎖16格"),
        )
        addEndHook({

            Utils.command("bbroadcast &7[&x&A&9&C&7&E&D階級&7] &7傭兵 &f${it.name} &7成功升階為 &6金牌傭兵")
            Utils.command("storage addunlocked ${it.name} 16")
            Utils.command("lp user ${it.name} parent add lv3")
        })
    }
}

val quest = Other_Rank_02();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}