@file:RequiredPlugins("ScriptedQuests","MMOItems","MythicLib")


import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.quests.Quest
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.objectives.*
import net.brian.scriptedquests.rewards.ClassExpReward
import net.brian.scriptedquests.rewards.CommandReward
import net.brian.scriptedquests.rewards.MoneyReward
import net.brian.scriptedquests.utils.Utils

val playerIcon = "%squests_icon%";
val NPC1Icon = IconFonts.getNPC(134);


val questID = "side_gold_is"

val playerName = "§c● §f%player_displayname%§r:";
val NPC1 = "§c● §e羅羅可§r:";

val mes1 = arrayOf(
    *Utils.getMessage(NPC1Icon,
        "$NPC1",
        "&x&d&6&d&0&b&8嘿，看來你就是新晉的金牌傭兵",
        "&x&d&6&d&0&b&8你可以享用傭兵公會分發的空島啦！",
    ),
    *Utils.getMessage(NPC1Icon,
        "$NPC1",
        "&x&d&6&d&0&b&8也就是說",
        "&x&d&6&d&0&b&8你能創建空島",
        "&x&d&6&d&0&b&8擁有自己的基地",
    ),
    *Utils.getMessage(NPC1Icon,
        "$NPC1",
        "&x&d&6&d&0&b&8還可以招募其他傭兵",
        "&x&d&6&d&0&b&8加入你的島嶼呢！",
    ),
    *Utils.getMessage(playerIcon,
        "$playerName",
        "§x§d§6§c§1§a§e太棒了！那我要怎麼做?",
    ),
    *Utils.getMessage(NPC1Icon,
        "$NPC1",
        "&x&d&6&d&0&b&8傳送到島嶼大廳",
        "&x&d&6&d&0&b&8打開面板回到島嶼就可以創建了",
    ),
    *Utils.getMessage(playerIcon,
        "$playerName",
        "§x§d§6§c§1§a§e哦哦！",
        "§x§d§6§c§1§a§e我這就去",
    ),
)
val mes2 = arrayOf(
    *Utils.getMessage(NPC1Icon,
        "$NPC1",
        "&x&d&6&d&0&b&8恭喜你可以擁有自己島嶼了！",
        "&x&d&6&d&0&b&8島嶼的更多指令請參考島嶼面板！",
    )
)
class Side_Gold_Is:Quest(questID,"§7[§b金牌支線§7] §c金牌傭兵的落腳處"){

    init {
        cancelAble = false;
        val obj1 = ListenNPCObjective(this,"obj1",134,*mes1)
            .setInstruction("§7與 §e羅羅可 §7對話")
            .setEndProcess{
                Utils.command("lp user ${it.name} permission set superior.island.create")
                Utils.command("server island ${it.name}")
                it.sendMessage("§7[§c提示§7] §f你已經可以創建島嶼了")
                ///lp user _brown_ permission set
            }

        val obj2 = ListenNPCObjective(this,"obj2",134,*mes2)
            .setInstruction("§7與 §e羅羅可 §7對話")

        pushObjective(obj1,obj2);
        addRewards(
            MoneyReward(100),
            ClassExpReward(30),
        )
    }

}


val quest = Side_Gold_Is();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}