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




val questID = "main_ch2_01"

class MainCh2_01: Quest(questID,"§7[§c主線委託§7] §f第二章-商務需求"){


    val playerIcon = "%squests_icon%";
    val yodaIcon = IconFonts.getNPC(97);
    val mayleeIcon = IconFonts.getNPC(59);

    private val playerName = "§c● §f%player_displayname%§r:";
    private val yodaNPC = "§c● §e尤爾達§r:";
    private val mayleeNPC = "§c● §e梅里§r:";

    private val mes1 = arrayOf(
            *Utils.getMessage(mayleeIcon,
                    "$mayleeNPC",
                    "&x&d&6&d&0&b&8看來你已經做好準備了",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e當然！我可是進步了不少",
                    "§x§d§6§c§1§a§e之前提到的行商隊伍",
                    "§x§d§6§c§1§a§e該去哪裡找他們?",
            ),
            *Utils.getMessage(mayleeIcon,
                    "$mayleeNPC",
                    "&x&d&6&d&0&b&8他們應該還在港口旁的商店街",
                    "&x&d&6&d&0&b&8你可以在那裏找到他們",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e知道了！",
                    "§x§d§6§c§1§a§e那我就先告辭了",
            ),
    )

    private val mes2 = arrayOf(
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e請問....",
                    "§x§d§6§c§1§a§e你是行商隊長嗎?",
            ),
            *Utils.getMessage(yodaIcon,
                    "$yodaNPC",
                    "&x&d&6&d&0&b&8我是，找我有何貴幹?",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e聽說您最近在找傭兵一同前往",
                    "§x§d§6§c§1§a§e我想與您一同前往薩哈村",
            ),
            *Utils.getMessage(yodaIcon,
                    "$yodaNPC",
                    "§7(看了你的全身)",
                    "&x&d&6&d&0&b&8恩...過了！",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e這樣就過了?",
                    "§x§d§6§c§1§a§e怎麼感覺有點簡單...",
            ),
            *Utils.getMessage(yodaIcon,
                    "$yodaNPC",
                    "&x&d&6&d&0&b&8唉..沒辦法..",
                    "&x&d&6&d&0&b&8現在人數不足，許多隊員都出了不少包",
                    "&x&d&6&d&0&b&8目前還缺很多貨物沒補足...",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e有什麼是我能幫上忙的嗎?",
            ),
            *Utils.getMessage(yodaIcon,
                    "$yodaNPC",
                    "&x&d&6&d&0&b&8這樣吧！你的第一個任務",
                    "&x&d&6&d&0&b&8幫我蒐集一點受汙染的骸骨吧！",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e沒問題，這對我來說小事一件！",
            )
    )
    private val mes3 = arrayOf(
            *Utils.getMessage(yodaIcon,
                    "$yodaNPC",
                    "&x&d&6&d&0&b&8不錯啊！有點本事",
                    "&x&d&6&d&0&b&8沒想到這麼快就湊齊了",
                    "&x&d&6&d&0&b&8不過...貨物還缺了不少",
            ),
            *Utils.getMessage(yodaIcon,
                    "$yodaNPC",
                    "&x&d&6&d&0&b&8現在還需要...",
                    "&x&d&6&d&0&b&8一些毒氣林的毒氣酸奶",
                    "&x&d&6&d&0&b&8還有特有的植物惡沼花",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e毒氣林嗎...?",
                    "§x§d§6§c§1§a§e這可有點難度了",
                    "§x§d§6§c§1§a§e不過包在我身上！",
            )
    )
    private val mes4 = arrayOf(
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e尤爾達先生！你怎麼受傷了?",
            ),
            *Utils.getMessage(yodaIcon,
                    "$yodaNPC",
                    "&x&d&6&d&0&b&8該死的...",
                    "&x&d&6&d&0&b&8我被村莊外的一群盜賊襲擊了",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e怎麼會?",
                    "§x§d§6§c§1§a§e村莊內應該很安全才對",
            ),
            *Utils.getMessage(yodaIcon,
                    "$yodaNPC",
                    "&x&d&6&d&0&b&8我的確不是在村莊內受傷的",
                    "&x&d&6&d&0&b&8具體來說，我準備去毒氣林找我的老朋友幫忙",
                    "&x&d&6&d&0&b&8沒想到被盜賊洗劫了",
            ),
            *Utils.getMessage(yodaIcon,
                    "$yodaNPC",
                    "&x&d&6&d&0&b&8許多貨物都被帶走了...",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e放心吧！沒有保護好雇主是我的失職",
                    "§x§d§6§c§1§a§e接下來就交給我吧！",
            ),
    )

    private val mes5 = arrayOf(
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e奇怪...怎麼數量不對?",
                    "§x§d§6§c§1§a§e難不成剩下的在他們的首領手上?",
                    "§x§d§6§c§1§a§e沒辦法了...",
            ),
    )
    private val mes6 = arrayOf(
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e呼....",
                    "§x§d§6§c§1§a§e真是驚險",
                    "§x§d§6§c§1§a§e可以回去交差了！",
            ),
    )
    private val mes7 = arrayOf(
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e尤爾達先生，您還好吧?",
            ),
            *Utils.getMessage(yodaIcon,
                    "$yodaNPC",
                    "&x&d&6&d&0&b&8我沒事了",
                    "&x&d&6&d&0&b&8真的謝謝你能幫我找回那些東西",
                    "&x&d&6&d&0&b&8否則損失可是難以估計阿",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e接下來還有甚麼需要嗎?",
            ),
            *Utils.getMessage(yodaIcon,
                    "$yodaNPC",
                    "&x&d&6&d&0&b&8我想想…",
                    "&x&d&6&d&0&b&8你在毒氣林裡面有看到我的老朋友克雷利亞嗎?",
                    "&x&d&6&d&0&b&8他就住在毒氣林裡面的小屋",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e我是有看到一棟小屋",
                    "§x§d§6§c§1§a§e但不敢貿然闖入",
            ),
            *Utils.getMessage(yodaIcon,
                    "$yodaNPC",
                    "&x&d&6&d&0&b&8你能代我去和他打聲招呼嗎?",
                    "&x&d&6&d&0&b&8我發現我隨身的毒藥瓶沒了",
                    "&x&d&6&d&0&b&8這是我拿來對付盜賊所使用的",
            ),
            *Utils.getMessage(yodaIcon,
                    "$yodaNPC",
                    "&x&d&6&d&0&b&8替我向她索取一些",
                    "&x&d&6&d&0&b&8她的毒藥實在是太厲害了",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e好!我這就去",
            )
    )
    init {
        cancelAble = false
        val obj0 = ReachLevelObjective("obj0", 10)
                .setInstruction("§710等後將繼續任務");
        val world = Bukkit.getWorld("2k");
        val obj1 = ListenNPCObjective(this,"obj1",59,*mes1)
                .setInstruction("§7與 §e梅里 §7對話");
        val obj2 = ListenNPCObjective(this,"obj2",97,*mes2)
                .setInstruction("§7與 §e尤爾達§r §7對話");
        val obj3 = GiveItemObjective(this,"obj3",97,"MATERIAL:M003",20)
                .setInstruction{"§7將 §7${it.amount}/20 個受汙染的骸骨§8(彼遺古岸) §7交給 §7[行商商隊長] §e尤爾達§r"}
        val obj4 = ListenNPCObjective(this,"obj4",97,*mes3)
                .setInstruction("§7與 §e尤爾達§r §7對話");
        val obj5 = GiveMultiItemObjective(this,"obj5",97,
                mapOf(
                        "BOSSMATERIAL:M002" to 1,
                        "COLLECTMATERIAL:M033" to 10
                ))
                .setInstruction{"§7將 §7${it.get("COLLECTMATERIAL:M033")}/10 個惡沼花§8(毒氣林採集)§7、${it.get("BOSSMATERIAL:M002")}/1 個 §7毒氣酸奶§8(毒氣林boss) §7交給 §e尤爾達§r"}
        val obj6 = ListenNPCObjective(this,"obj6",97,*mes4)
                .setInstruction("§7與 §e尤爾達§r §7對話");
        val obj7 = KillMobsObjective(this,"obj7","盜賊據點_",20)
                .setInstruction { "擊殺 ${it.amount} / 20 個盜賊" };
        val obj8 = ListenTalkObjective(this,"obj8",*mes5)
                .setInstruction("§7盤點一下貨物");
        val obj9 = KillMobsObjective(this,"obj9","盜賊據點_盜賊首領",1)
                .setInstruction { "擊殺 ${it.amount} / 1 個盜賊首領" };
        val obj10 = ListenTalkObjective(this,"obj10",*mes6)
                .setInstruction("§7盤點一下貨物");
        val obj11 = ListenNPCObjective(this,"obj11",97,*mes7)
                .setInstruction("§7與 §e尤爾達§r §7對話");

        pushObjective(obj0,obj1,obj2,obj3,obj4,obj5,obj6,obj7,obj8,obj9,obj10,obj11)

        addRewards(
                MoneyReward(50), //30錢幣
                QuestExpReward(30), //15傭兵聲望
                ClassExpReward(200),
        )
        addEndHook({
            Utils.command("sq start ${it.name} main_ch2_02")
        })
    }
}


val quest = MainCh2_01();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



