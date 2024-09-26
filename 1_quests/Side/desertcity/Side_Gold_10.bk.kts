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

val questID = "side_gold_10"

class Side_Gold_10: Quest(questID,"§7[§b金牌支線§7] §f海碧的奇怪咒術？"){


    val playerIcon = "%squests_icon%";
    val NPC1Icon = IconFonts.getNPC(186);

    private val playerName = "§c● §f%player_displayname%§r:";
    private val NPC1 = "§c● §e海碧§r:";

    private val mes1 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8你有聽說過在很遠的西邊...",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e怎麼了？",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "§x§d§6§c§1§a§e巴雷諾斯地區的貝爾利亞村莊，",
            "§x§d§6§c§1§a§e流傳下來的傳說嗎？",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e你說的是巫術嗎？",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8我是輔佐傳說中的克羅恩王的秘密祭司，",
            "&x&d&6&d&0&b&8他試圖以煉金術帶給這個世界很大的變化，",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8不過，最近我對他的研究沒什麼進展，",
            "&x&d&6&d&0&b&8不管怎麼查古書也沒辦法找到線索，",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8我覺得可能需要他的指引...",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8為了召喚他，",
            "&x&d&6&d&0&b&8需要進行秘密儀式，",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8不過也不是每個人都可以做到，",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8必須要有強韌的肉體",
            "&x&d&6&d&0&b&8和精神才可以的...",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8如果是像你這麼厲害的冒險家的話，",
            "&x&d&6&d&0&b&8應該可以辦得到，",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8如果可以的話，",
            "&x&d&6&d&0&b&8你願意幫我的忙嗎？",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8先把砂岩幼鳥的血",
            "&x&d&6&d&0&b&8沾在你的手上吧，",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8還有在古樹的樹底下",
            "&x&d&6&d&0&b&8沾滿那些血吧。",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8他不會隨便現身的，",
            "&x&d&6&d&0&b&8因為他是很龜毛的人。",
        ),
    )

    private val mes2 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e他讓我轉告你",
            "§x§d§6§c§1§a§e暫時沒有消息...",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8嗯... 這樣啊，",
            "&x&d&6&d&0&b&8他是這樣轉達的嗎？",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8我知道了，先進行研究吧，",
            "&x&d&6&d&0&b&8真的很謝謝你。",
        ),
    )


    init {
        val world = Bukkit.getWorld("2k");
        val obj1 = ListenNPCObjective(this,"obj1",186,*mes1)
            .setInstruction("§7與 §e海碧 §7對話");
        val obj2 = KillMultiMobsObjective(this,"obj2", mapOf("紅砂山脈_紅砂幼鳥" to 32))
            .setInstruction{ "§7擊殺  ${it.get("紅砂山脈_紅砂幼鳥")}/32 個紅砂山脈的紅砂幼鳥"};
        val obj3 = MoveToObjective(this,"obj3",Location(world,363.00, 76.00, -45.00),5.0)
            .setInstruction("§7前往古樹的樹底")
            .setEndProcess{
                it.sendMessage("§c")
                it.sendMessage("§7[§a任務提示§7] §c已經沾滿血液了，恍惚了一下，仿佛被什麼上了身")
                it.sendMessage("§7[§a任務提示§7] §c獲得了不明訊息，可以回去找海碧報告了")
                it.sendMessage("§c")}
        val obj4 = ListenNPCObjective(this,"obj4",186,*mes2)
            .setInstruction("§7與 §e海碧 §7對話");

        pushObjective(obj1,obj2,obj3,obj4)

        addRewards(
            MoneyReward(350), //金牌 300~800
            QuestExpReward(160), //金牌 100~200
            ClassExpReward(320) //金牌 300~500
        )
    }
}


val quest = Side_Gold_10();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



