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

val questID = "side_gold_09"

class Side_Gold_09: Quest(questID,"§7[§b金牌支線§7] §f寵物治療劑的補給"){


    val playerIcon = "%squests_icon%";
    val NPC1Icon = IconFonts.getNPC(244);

    private val playerName = "§c● §f%player_displayname%§r:";
    private val NPC1 = "§c● §e奧爾多§r:";

    private val mes1 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8傭兵是你吖，好久不見！",
            "&x&d&6&d&0&b&8現在有點棘手的事",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e怎麼了？",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "§x§d§6§c§1§a§e傭兵們到比利亞達塔出征的事情",
            "§x§d§6§c§1§a§e一下子變得很頻繁。",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e發生什麼事了？",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8總之紫水晶山丘那邊好像發生什麼事了...",
            "&x&d&6&d&0&b&8很多紅砂幼鳥受傷回來的情況也很多。",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8多到治療劑都不夠的地步...",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8現在這些紅砂幼鳥的狀態病危...",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8商圈雖然要求首都補給，",
            "&x&d&6&d&0&b&8卻只能在這裡乾等。",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8應該要先製作急救處理用的治療劑。",
            "&x&d&6&d&0&b&8傭兵冒險家，請問可以幫我取得材料嗎？",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8請快點。時間不多了。",
        ),
    )

    private val mes2 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8謝謝你！雖然先採取了緊急措施，",
            "&x&d&6&d&0&b&8但這些傢伙真會撐... \n",
        ),
    )

    init {
        val world = Bukkit.getWorld("2k");
        val obj1 = ListenNPCObjective(this,"obj1",244,*mes1)
            .setInstruction("§7與 §e奧爾多 §7對話");
        val obj2 = GiveItemObjective(this,"obj2",244,"QUESTMATERIAL:M024",32)
            .setInstruction{"交給 §e奧爾多 §7${it.amount}/32 個寵物專用的治療劑 &8(於阿納斯塔城的藥水店製作)"};
        val obj3 = ListenNPCObjective(this,"obj3",244,*mes2)
            .setInstruction("§7與 §e奧爾多 §7對話");

        pushObjective(obj1,obj2,obj3)

        addRewards(
            MessageReward("&a➯ &7解鎖支線任務: &2斐格&8-&c出征前的準備"),            
            MoneyReward(300), //金牌 300~800
            QuestExpReward(120), //金牌 100~200
            ClassExpReward(400) //金牌 300~500
            )

        addEndHook({
            Utils.command("lp user ${it.name} permission set side_gold_05")
        })

    }
}


val quest = Side_Gold_09();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



