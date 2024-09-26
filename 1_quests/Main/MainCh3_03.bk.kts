@file:RequiredPlugins("ScriptedQuests","PlayerDataSync","MMOItems","MythicLib")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.quests.Quest
import net.brian.scriptedquests.compability.classcore.ReachLevelObjective
import net.brian.scriptedquests.compability.mythicmobs.KillMobsObjective
import net.brian.scriptedquests.compability.mythicmobs.KillMultiMobsObjective
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.objectives.*
import net.brian.scriptedquests.rewards.*
import net.brian.scriptedquests.utils.Utils
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player




val questID = "main_ch3_03"

class MainCh3_03: Quest(questID,"§7[§c主線委託§7] §f第三章-部落英雄"){


    val playerIcon = "%squests_icon%";
    val calitmanIcon = IconFonts.getNPC(157);
    val alielIcon = IconFonts.getNPC(123);
    val alisterIcon = IconFonts.getNPC(129);
    val yodaIcon = IconFonts.getNPC(97);
    val crayliaIcon = IconFonts.getNPC(105);
    val ruburgIcon = IconFonts.getNPC(158);
    val ruviaIcon = IconFonts.getNPC(159);
    val sizIcon = IconFonts.getNPC(160);

    private val playerName = "§c● §f%player_displayname%§r:";
    private val calitmanNPC = "§c● §e卡里特曼§r:";
    private val alielNPC = "§c● §e艾黎爾§r:";
    private val alisterlNPC = "§c● §e亞里士德§r:";
    private val yodaNPC = "§c● §e尤爾達§r:";
    private val crayliaNPC ="§c● §e克雷利亞§r: ";
    private val ruburgNPC ="§c● §e 魯柏格§r: ";
    private val ruviaNPC ="§c● §e 魯維亞§r: ";
    private val sizNPC ="§c● §e 西斯§r: ";

    private val mes1 = arrayOf(
        *Utils.getMessage(ruburgIcon,
            "$ruburgNPC",
            "§x§d§6§c§1§a§e來人啊!誰能來…",
            "§x§d§6§c§1§a§e那個…這位傭兵小哥你能來幫幫我嗎?",
            "§x§d§6§c§1§a§e我們的部落…遭受攻擊了",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e發生甚麼事了?",
            "§x§d§6§c§1§a§e你先冷靜下來",
        ),
        *Utils.getMessage(ruburgIcon,
            "$ruburgNPC",
            "§x§d§6§c§1§a§e我先前離開部落去紅沙山脈另一端的礦山部落",
            "§x§d§6§c§1§a§e支援那裡的怪異事件",
            "§x§d§6§c§1§a§e一回到部落卻發現貌似遭受了侵襲…",
        ),
        *Utils.getMessage(ruburgIcon,
            "$ruburgNPC",
            "§x§d§6§c§1§a§e我的妹妹…他可能還在…",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e我先替你去看看吧 ",
            "§x§d§6§c§1§a§e啊!你身上的傷",
        ),
        *Utils.getMessage(ruburgIcon,
            "$ruburgNPC",
            "§x§d§6§c§1§a§e這沒什麼，我的妹妹比較重要",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e這樣啊…那麼你先在這養傷吧",
            "§x§d§6§c§1§a§e你的妹妹就交給我了 ",
        ),
    )
    private val mes2 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e這甚麼都沒有啊…",
            "§x§d§6§c§1§a§e倒是只有好多的怪異蜜蜂阿…",
        ),
    )
    private val mes3 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e…",
            "§x§d§6§c§1§a§e好像不小心被野人包圍了呢…",
        ),
    )
    private val mes4 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e真的是…這些蜜蜂跟野人究竟是甚麼關係阿",
            "§x§d§6§c§1§a§e不只一起行動還不會互相攻擊",
        ),
    )
    private val mes5 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e看來那位少年的妹妹並不在這呢",
            "§x§d§6§c§1§a§e會不會在其他地方",
        ),
    )
    private val mes6 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e看來只能回去找那位少年了",
            "§x§d§6§c§1§a§e希望他傷勢有好點",
        ),
    )
    private val mes7 = arrayOf(
        *Utils.getMessage(ruburgIcon,
            "$ruburgNPC",
            "§x§d§6§c§1§a§e傭兵小哥，你回來啦",
            "§x§d§6§c§1§a§e果然…沒有見到我妹妹嗎…",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e先別急著放棄，也許她還在附近而已呢",
            "§x§d§6§c§1§a§e你先讓我看看你的傷口",
        ),
        *Utils.getMessage(ruburgIcon,
            "$ruburgNPC",
            "§x§d§6§c§1§a§e沒事了，我妹妹在我出行前給了我祖傳的藥草",
            "§x§d§6§c§1§a§e療效非常的好，只是…副作用便是會難以行動",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e也許…他們提前離開部落了也不一定",
            "§x§d§6§c§1§a§e剛剛村子有條岔路貌似可以通往外面的道路",
        ),
    )
    private val mes8 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e前面有人!看起來像是他們部落的人",
            "§x§d§6§c§1§a§e去問問看吧",
        ),
    )
    private val mes9 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e請問…你們是旁邊部落的居民嗎?",
            "§x§d§6§c§1§a§e我受託要為一位..勇士尋找妹妹",
        ),
        *Utils.getMessage(sizIcon,
            "$sizNPC",
            "§x§d§6§c§1§a§e你要找的應該就是她了吧",
            "§x§d§6§c§1§a§e他們是我們村子唯一的兄妹",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e請問…她怎麼一直在發呆…",
            "§x§d§6§c§1§a§e是發生了甚麼事情嗎",
        ),
        *Utils.getMessage(sizIcon,
            "$sizNPC",
            "§x§d§6§c§1§a§e魯維亞她是村子裡的祭司",
            "§x§d§6§c§1§a§e在保護大家撤離時受到了",
            "§x§d§6§c§1§a§e受到守護靈砂岩巨龜的詛咒…",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e聽起來和那條巨龍一樣",
            "§x§d§6§c§1§a§e那該怎麼辦? ",
            "§x§d§6§c§1§a§e難道也得解決那隻巨龜才行嗎?",
        ),
        *Utils.getMessage(sizIcon,
            "$sizNPC",
            "§x§d§6§c§1§a§e沒錯…但她出現的時機都難以琢磨",
            "§x§d§6§c§1§a§e而且她體型碩大",
            "§x§d§6§c§1§a§e僅憑一己之力…也許很難攻破",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e交給我來試試吧",
            "§x§d§6§c§1§a§e正巧斬斷路上一切的危險 ",
        ),
    )
    private val mes10 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e如此一來就行了吧",
            "§x§d§6§c§1§a§e回去看看他們吧",
        ),
    )
    private val mes11 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e長老先生，魯維亞小姐有好點嗎?",
        ),
        *Utils.getMessage(sizIcon,
            "$sizNPC",
            "§x§d§6§c§1§a§e多虧了你，她現在已經沒事了",
        ),
        *Utils.getMessage(ruviaIcon,
            "$ruviaNPC",
            "§x§d§6§c§1§a§e謝謝你，恩人，關於我的哥哥",
            "§x§d§6§c§1§a§e抱歉還特別麻煩妳了",
            "§x§d§6§c§1§a§e這塊地一職以來受到靈龜的守護，如今…",
        ),
        *Utils.getMessage(sizIcon,
            "$sizNPC",
            "§x§d§6§c§1§a§e如今部落已無法居住，部落的住民們",
            "§x§d§6§c§1§a§e也早已奔逃四方",
            "§x§d§6§c§1§a§e我們今後又該何去何從呢",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e要不…與我一同前往阿納斯塔城吧",
            "§x§d§6§c§1§a§e比起漫無目的，能有頓溫飽才更重要呢",
        ),
        *Utils.getMessage(sizIcon,
            "$sizNPC",
            "§x§d§6§c§1§a§e這聽起來…",
        ),
        *Utils.getMessage(ruviaIcon,
            "$ruviaNPC",
            "§x§d§6§c§1§a§e聽起來真是太好啦!爺爺我們不用流浪了!",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e這麼說來…原來你們是…",
        ),
        *Utils.getMessage(sizIcon,
            "$sizNPC",
            "§x§d§6§c§1§a§e魯柏格那小子都沒跟你說嗎?",
            "§x§d§6§c§1§a§e真是的，但無論如何還是謝謝你",
            "§x§d§6§c§1§a§e未來，我們就在阿納斯塔城見吧",
        ),
    )

    init {
        cancelAble = false
        val world = Bukkit.getWorld("2k");
        val obj1 = ListenNPCObjective(this,"obj1",158,*mes1)
            .setInstruction("§7與 §e魯柏格 §7對話");
        val obj2Loc = Location(world,-132.29,78.0,395.65);
        val obj2 = MoveToObjective(this,"obj2", obj2Loc,5.0)
            .setInstruction ("§7前往座標 -132,78,395");
        val obj3 = ListenTalkObjective(this,"obj3",*mes2)
        val obj4 = KillMultiMobsObjective(this,"obj4", mapOf(
            "砂岩灘地_沙漠條蜂" to 15))
            .setInstruction{ "§7擊殺  ${it.get("砂岩灘地_沙漠條蜂")}/15 隻沙漠條蜂"}
        val obj5Loc = Location(world,-129.29,79.0,407.65);
        val obj5 = MoveToObjective(this,"obj5", obj5Loc,5.0)
            .setInstruction ("§7前往座標 -129,79,407");
        val obj6 = ListenTalkObjective(this,"obj6",*mes3)
        val obj7 = KillMultiMobsObjective(this,"obj7", mapOf(
            "砂岩灘地_野原人" to 15))
            .setInstruction{ "§7擊殺  ${it.get("砂岩灘地_野原人")}/15 隻野原人"}
        val obj8Loc = Location(world,-105.29,79.0,418.65);
        val obj8 = MoveToObjective(this,"obj8", obj8Loc,5.0)
            .setInstruction ("§7前往座標 -105,79,418");
        val obj9 = ListenTalkObjective(this,"obj9",*mes4)
        val obj10 = KillMultiMobsObjective(this,"obj10", mapOf(
            "砂岩灘地_沙漠條蜂" to 10,
            "砂岩灘地_野原人" to 10,))
            .setInstruction{ "§7擊殺  ${it.get("砂岩灘地_野原人")}/10 隻野原人、${it.get("砂岩灘地_沙漠條蜂")}/10 隻沙漠條蜂"}
        val obj11Loc = Location(world,-80.29,77.0,386.65);
        val obj11 = MoveToObjective(this,"obj11", obj11Loc,5.0)
            .setInstruction ("§7前往座標 -80,77,386");
        val obj12 = ListenTalkObjective(this,"obj12",*mes5)
        val obj13 = KillMultiMobsObjective(this,"obj13", mapOf(
            "砂岩灘地_沙漠條蜂" to 20,
            "砂岩灘地_野原人" to 20,))
            .setInstruction{ "§7擊殺  ${it.get("砂岩灘地_野原人")}/20 隻野原人、${it.get("砂岩灘地_沙漠條蜂")}/20 隻沙漠條蜂"}
        val obj14 = ListenTalkObjective(this,"obj14",*mes6)
        val obj15 = ListenNPCObjective(this,"obj15",158,*mes7)
            .setInstruction("§7與 §e魯柏格 §7對話");
        val obj16Loc = Location(world,-110.29,79.0,337.65);
        val obj16 = MoveToObjective(this,"obj16", obj16Loc,5.0)
            .setInstruction ("§7前往座標 -110,79,337");
        val obj17 = ListenTalkObjective(this,"obj17",*mes8)
        val obj18 = ListenNPCObjective(this,"obj18",160,*mes9)
            .setInstruction("§7與 §e西斯 §7對話");
        val obj19 = KillMultiMobsObjective(this,"obj19", mapOf(
            "砂岩灘地_砂岩巨龜" to 1,))
            .setInstruction{ "§7擊殺  ${it.get("砂岩灘地_砂岩巨龜")}/1 隻砂岩巨龜"}
        val obj20 = ListenTalkObjective(this,"obj20",*mes10)
        val obj21 = ListenNPCObjective(this,"obj21",160,*mes11)
            .setInstruction("§7與 §e西斯 §7對話");
        pushObjective(obj1,obj2,obj3,obj4,obj5,obj6,obj7,obj8,obj9,obj10,obj11,obj12,obj13,obj14,obj15,obj16,obj17,obj18,obj19,obj20,obj21)

        addRewards(
            MoneyReward(200), //錢幣
            QuestExpReward(120), //傭兵聲望
            ClassExpReward(800),
            MessageReward("&a➯ &7重生點解鎖: &7阿納斯塔城重生點"),
            MessageReward("&a➯ &7傳送點解鎖: &7阿納斯塔城")
        )
        addEndHook({
            Utils.command("sq start ${it.name} main_ch4_01")
            Utils.command("lp user ${it.name} permission set main03.done")
        } )
    }
}


val quest = MainCh3_03();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



