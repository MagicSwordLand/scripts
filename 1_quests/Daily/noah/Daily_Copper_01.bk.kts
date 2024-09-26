@file:RequiredPlugins("ScriptedQuests")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.quests.Quest
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.objectives.GiveItemObjective
import net.brian.scriptedquests.objectives.ListenNPCObjective
import net.brian.scriptedquests.objectives.ListenTalkObjective
import net.brian.scriptedquests.quests.TimeLimitQuest
import net.brian.scriptedquests.rewards.ClassExpReward
import net.brian.scriptedquests.rewards.MoneyReward
import net.brian.scriptedquests.rewards.QuestExpReward
import net.brian.scriptedquests.utils.Utils

val questID = "daily_copper_01"

class Daily_Copper_01: TimeLimitQuest(questID,"§7[§a銅牌每日§7] §f葡萄園的尤莉"){
    private val playerName = "§c● §f%player_displayname%§r:";
    private val greyNPC = "§c● §e尤莉§r:";
    private val dayNPC = "§c● §e莉亞莫§r:";
    val npcIcon = IconFonts.getNPC(16);
    val npcIcon2 = IconFonts.getNPC(60);
    val playerIcon = "%squests_icon%";

    private val mes1 = arrayOf(
        *Utils.getMessage(npcIcon,
            "$dayNPC",
            "&x&d&6&d&0&b&8諾亞村葡萄園的尤莉正在找人幫忙採收葡萄",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "&x&d&6&c&1&a&e感覺不難，就交給我了",
        ),
        *Utils.getMessage(npcIcon,
            "$dayNPC",
            "&x&d&6&d&0&b&8那先去找尤莉，她會分配今天的工作",
        )
    )

    private val mes2 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "&x&d&6&c&1&a&e我是傭兵公會分配來幫忙的",
        ),
        *Utils.getMessage(npcIcon2,
            "$greyNPC",
            "&x&d&6&d&0&b&8阿！我等你很久了",
            "&x&d&6&d&0&b&8是這樣...這些葡萄都非常的高，我的腳又受傷了…",
            "&x&d&6&d&0&b&8因此需要你幫我採收這些葡萄",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "&x&d&6&c&1&a&e沒問題，就交給我了！",
        )
    )
    private val mes3 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "&x&d&6&c&1&a&e這樣夠嗎?",
        ),
        *Utils.getMessage(npcIcon2,
            "$greyNPC",
            "&x&d&6&d&0&b&8太棒了，這些就夠了，太感謝了！！",
            "&x&d&6&d&0&b&8這些是這次委託的獎勵",
        )
    )


    init {

        val obj1 = ListenTalkObjective(this,"obj1",*mes1)
            .setInstruction("§7與 §e莉亞莫 §7對話");

        val obj2 = ListenNPCObjective(this,"obj2",60,*mes2)
        obj2.setInstruction("§7與 §e尤莉 §7對話")

        val obj3 = GiveItemObjective(this,"obj3",60,"COLLECTMATERIAL:M031",15)
            .setInstruction{"§7採收 ${it.amount} / 15 個葡萄 並交給 §e尤莉"}

        val obj4 = ListenTalkObjective(this,"obj4",*mes3)
            .setInstruction("§7與 §e尤莉 §7對話");

        pushObjective(obj1,obj2,obj3,obj4);
        addRewards(
            MoneyReward(10), //30錢幣
            QuestExpReward(8), //15傭兵聲望
            ClassExpReward(100),
        )
        addEndHook({
            Utils.command("sq limit set ${it.name} 20")
        })
    }

}
val quest = Daily_Copper_01();
ScriptedQuests.getInstance().questManager.register(quest);
fun onDispose(){
    quest.unregister();
}