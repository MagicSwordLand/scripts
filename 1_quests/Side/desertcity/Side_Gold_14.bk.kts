@file:RequiredPlugins("ScriptedQuests","PlayerDataSync","MMOItems","MythicLib")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.quests.Quest
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.objectives.*
import net.brian.scriptedquests.rewards.*
import net.brian.scriptedquests.utils.Utils
import org.bukkit.Bukkit

val questID = "side_gold_14"

class Side_Gold_14: Quest(questID,"§7[§b金牌支線§7] §f精靈樹的誕生"){


    val playerIcon = "%squests_icon%";
    val NPC1Icon = IconFonts.getNPC(252);
    val NPC2Icon = IconFonts.getNPC(32);
    val NPC3Icon = IconFonts.getNPC(247);
    val NPC4Icon = IconFonts.getNPC(222);

    private val playerName = "§c● §f%player_displayname%§r:";
    private val NPC1 = "§c● §e碧波§r:";
    private val NPC2 = "§c● §e白莎§r:";
    private val NPC3 = "§c● §e馬萊§r:";
    private val NPC4 = "§c● §e布魯克斯§r:";

    private val mes1 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8我畢生的傑作就快要誕生了。",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "§x§d§6§c§1§a§e是至今為止從來沒有人看過，",
            "§x§d§6§c§1§a§e擁有神秘力量的作物。",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8是多了不起的作物？",
            "&x&d&6&d&0&b&8這裡無人不知，無人不曉。",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "§x§d§6§c§1§a§e我自己說很像在炫耀，",
            "§x§d§6§c§1§a§e你去問問別人吧。哈哈哈！",
        ),
    )

    private val mes2 = arrayOf(
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8精靈樹嗎？那個好像是最新的研發作物",
            "&x&d&6&d&0&b&8產自大名鼎鼎的波爾多山莊。",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8聽說那裡除了諾倫開發中的新作物以外，",
            "&x&d&6&d&0&b&8還有很多引以為傲的作物。",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "§x§d§6§c§1§a§e",
            "§x§d§6§c§1§a§e你去找那裡的山莊主人",
            "§x§d§6§c§1§a§e親自去問會比較清楚。",
        ),
    )

    private val mes3 = arrayOf(
        *Utils.getMessage(NPC3Icon,
            "$NPC3",
            "&x&d&6&d&0&b&8據說這東西吃的是誠心誠意",
            "&x&d&6&d&0&b&8你信嗎？",
        ),
        *Utils.getMessage(NPC3Icon,
            "$NPC3",
            "&x&d&6&d&0&b&8如果誠心誠意有用，",
            "&x&d&6&d&0&b&8我可能早就有個老婆了。",
        ),
    )

    private val mes4 = arrayOf(
        *Utils.getMessage(NPC4Icon,
            "$NPC4",
            "&x&d&6&d&0&b&8精靈樹是什麼，能吃嗎？",
            "&x&d&6&d&0&b&8在哪，帶我去看看！",
        ),
    )

    private val mes5 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8對我的精靈樹感興趣的外地人",
            "&x&d&6&d&0&b&8這麼快就聚在一起啦...很好！",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8如何？有了解一點我的精靈樹了嗎？",
            "&x&d&6&d&0&b&8雖然有點慚愧，",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8但其實我也不知道",
            "&x&d&6&d&0&b&8該怎麼使用這個作物。",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8雖然很多煉金術師因為有興趣而過來，",
            "&x&d&6&d&0&b&8但還沒完成，所以我也無法給出明確的答案。",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8可以肯定的是，",
            "&x&d&6&d&0&b&8擁有和到目前為止",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8我們看過的平凡植物完全不同的力量。",
        ),
    )

    private val mes6 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8說精靈樹是靠著",
            "&x&d&6&d&0&b&8真誠的祈禱長大的也不為過。",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8這阿納斯塔城環境良好，",
            "&x&d&6&d&0&b&8只要灑下種子就會茂密生長，",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8但光有這個不夠。還要澆水、施肥，",
            "&x&d&6&d&0&b&8給予最好的東西。",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8如何？",
            "&x&d&6&d&0&b&8就當作是投資我的精靈樹，幫我做事吧？",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e怎麼幫你？",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8重要的是誠意。",
            "&x&d&6&d&0&b&8希望他們好好長大的誠心誠意！",
        ),
    )

    private val mes7 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8哦哦，謝謝。",
            "&x&d&6&d&0&b&8等精靈樹完成了，我會好好感謝你的！",
        ),
    )


    init {
        val world = Bukkit.getWorld("2k");
        val obj1 = ListenNPCObjective(this,"obj1",252,*mes1)
            .setInstruction("§7與 §e碧波 §7對話");
        val obj2 = ListenNPCObjective(this,"obj2",32,*mes2)
            .setInstruction("§7與 §e白莎 §7對話");
        val obj3 = ListenNPCObjective(this,"obj3",247,*mes3)
            .setInstruction("§7與 §e馬萊 §7對話");
        val obj4 = ListenNPCObjective(this,"obj4",222,*mes4)
            .setInstruction("§7與 §e布魯克斯 §7對話");
        val obj5 = ListenNPCObjective(this,"obj5",252,*mes5)
            .setInstruction("§7與 §e碧波 §7對話");
        val obj6 = ListenNPCObjective(this,"obj6",252,*mes6)
            .setInstruction("§7與 §e碧波 §7對話");
        val obj7 = GiveItemObjective(this,"obj7",252,"QUESTMATERIAL:M029",3)
            .setInstruction{"§7將 §7${it.amount}/3 個 精靈樹旁的雜草§8(任務物品) §7交給 §e碧波 §r"};
        val obj8 = ListenNPCObjective(this,"obj8",252,*mes7)
            .setInstruction("§7與 §e碧波 §7對話");

        pushObjective(obj1,obj2,obj3,obj4,obj5,obj6,obj7,obj8)

        addRewards(
            MoneyReward(600), //金牌 300~800
            QuestExpReward(160), //金牌 100~200
            ClassExpReward(350) //金牌 300~500
        )
    }
}


val quest = Side_Gold_14();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



