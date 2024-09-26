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

val yankoIcon = IconFonts.getNPC(5);
val playerIcon = "%squests_icon%";
val questID = "other_rank_01"
fun command(line: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),line);
}

class Other_Rank_01: Quest(questID,"§7[§d特殊委託§7] §f晉升銀牌傭兵"){
    private val playerName = "§c● §f%player_displayname%§r:";
    private val yankoNPC = "§c● §e焱恪§r:";

    private val mes1 = arrayOf(
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e那個...",
                    "§x§d§6§c§1§a§e要怎樣才能成為銀牌傭兵?",
                    "§x§d§6§c§1§a§e我的聲望已經足夠了",
            ),
            *Utils.getMessage(yankoIcon,
                    "$yankoNPC",
                    "&x&d&6&d&0&b&8喔?",
                    "&x&d&6&d&0&b&8不錯啊小夥子",
                    "&x&d&6&d&0&b&8這麼快聲望就足夠了",
            ),
            *Utils.getMessage(yankoIcon,
                    "$yankoNPC",
                    "&x&d&6&d&0&b&8傭兵每次升階都需要完成規定的任務",
                    "&x&d&6&d&0&b&8為了證明你有足夠的經驗及實力",
                    "&x&d&6&d&0&b&8免得你們跑去送死",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e那晉升銀牌傭兵需要完成甚麼任務?",
            ),
            *Utils.getMessage(yankoIcon,
                    "$yankoNPC",
                    "&x&d&6&d&0&b&8你先證明自己戰鬥的實力吧",
            )
    )
    private val mes2 = arrayOf(
            *Utils.getMessage(yankoIcon,
                    "$yankoNPC",
                    "&x&d&6&d&0&b&8很好！",
                    "&x&d&6&d&0&b&8你已經有一定的戰鬥經驗了",
            ),
            *Utils.getMessage(yankoIcon,
                    "$yankoNPC",
                    "&x&d&6&d&0&b&8最後來確認一下",
                    "&x&d&6&d&0&b&8傭兵需要時常待在危險的環境",
                    "&x&d&6&d&0&b&8因此需要強大的裝備",
            ),
            *Utils.getMessage(yankoIcon,
                    "$yankoNPC",
                    "&x&d&6&d&0&b&8裝備則需要自己收集材料來打造",
                    "&x&d&6&d&0&b&8去收集一些素材給我吧",
            )
    )


    init {

        val obj1 = ListenTalkObjective(this,"obj1",*mes1)
                .setInstruction("§7與 §e焱格 §7對話");
        val obj2 = KillMultiMobsObjective(this,"obj2", mapOf(
                "毒氣林_蒐人" to 1,
                "盜賊據點_盜賊首領" to 1,
                "哥布林部落_哥布林王" to 1))
                .setInstruction{ "§7擊殺哥布林王§8(哥布林部落)§7 ${it.get("哥布林部落_哥布林王")}/1、§7擊殺蒐人§8(毒氣林)§7 ${it.get("毒氣林_蒐人")}/1、盜賊首領§8(盜賊據點)§7 ${it.get("盜賊據點_盜賊首領")}/1"}
        val obj3 = ListenNPCObjective(this,"obj3",5,*mes2)
        obj3.setInstruction("§7與 §e焱格 §7對話")
        val obj4 = GiveItemObjective(this,"obj4",5,"COLLECTMATERIAL:M001",200)
                .setInstruction{"採集 ${it.amount} / 200 個鑌礦 並交給 §e焱恪§r"}
        pushObjective(obj1,obj2,obj3,obj4);
        addRewards(
                MoneyReward(50),
                QuestExpReward(30), //
                ClassExpReward(500),
        )
        addEndHook({

            Utils.command("bbroadcast &7[&x&A&9&C&7&E&D階級&7] &7傭兵 &f${it.name} &7成功升階為 &f銀牌傭兵")
            Utils.command("lp user ${it.name} parent add lv2")
        })
    }
}

val quest = Other_Rank_01();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}