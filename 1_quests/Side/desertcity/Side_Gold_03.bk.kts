@file:RequiredPlugins("ScriptedQuests","PlayerDataSync","MMOItems","MythicLib")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.quests.Quest
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.objectives.*
import net.brian.scriptedquests.rewards.*
import net.brian.scriptedquests.utils.Utils
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.World

val questID = "side_gold_03"

class Side_Gold_03: Quest(questID,"§7[§b金牌支線§7] §f月光下朦朧的心"){


    val playerIcon = "%squests_icon%";
    val NPC1Icon = IconFonts.getNPC(158);
    val NPC2Icon = IconFonts.getNPC(94);

    private val world: World = Bukkit.getWorld("2k")!!
    private val playerName = "§c● §f%player_displayname%§r:";
    private val NPC1 = "§c● §e魯柏格§r:";
    private val NPC2 = "§c● §e斯雅§r:";

    private val mes1 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8嘿，冒險者，有件事我想拜託你",
            "&x&d&6&d&0&b&8關於…委託",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e委託?我樂意之至",
            "§x§d§6§c§1§a§e有甚麼要求盡管提!",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8我最近需要大量的迷離岩石",
            "&x&d&6&d&0&b&8和紅沙粉末…",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e你要這兩個東西?",
            "§x§d§6§c§1§a§e這不是都挺簡單的嗎?",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8不…我要從眾多迷離岩石中，",
            "&x&d&6&d&0&b&8挑選出魔力含量高的幾顆",
            "&x&d&6&d&0&b&8所以不是所有迷離岩石都能用的，",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8但你帶來越多",
            "&x&d&6&d&0&b&8我就有機會挑出更多的高魔量魔石",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e你要這些作甚麼阿…?",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8這…",
            "&x&d&6&d&0&b&8我想為斯雅作一個特殊的護符給他",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e斯雅…?",
            "§x§d§6§c§1§a§e是前段時間剛來的那位貴族小姐嗎?",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e聽說他在城市裡可是赫赫有名阿",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8我…我和他曾經就認識了",
            "&x&d&6&d&0&b&8而他過些時日要去接受試煉",
            "&x&d&6&d&0&b&8我想為他做點甚麼…",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e這樣啊…沒問題",
            "§x§d§6§c§1§a§e你要多少魔石?我去為你準備",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8先…60顆吧，",
            "&x&d&6&d&0&b&8說不定你運氣很好呢~",
        ),
    )

    private val mes2 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8我鑑定一下這些石頭…",
            "&x&d&6&d&0&b&8請稍等一下…",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e這些石頭真的很難取得阿…",
            "§x§d§6§c§1§a§e話說阿",
            "§x§d§6§c§1§a§e你做那個護符真的有用嗎?",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8肯定有用的!",
            "&x&d&6&d&0&b&8畢竟那是部落祖傳的高級護符",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8倒是…這些石頭可能不太夠呢…",
            "&x&d&6&d&0&b&8你能再去替我收集一點嗎?",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8再來個20顆就好啦~",
            "&x&d&6&d&0&b&8拜託了！",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e20顆!?太要命啦…",
            "§x§d§6§c§1§a§e斯雅究竟是你得誰值得你如此付出阿…",
        ),
    )

    private val mes3 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8這些應該就夠了，",
            "&x&d&6&d&0&b&8高魔量岩石的產量可真是稀有",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e那能告訴我",
            "§x§d§6§c§1§a§e你和斯雅小姐的關係了嗎?",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8這個嘛…斯雅小姐曾是我的救命恩人",
            "&x&d&6&d&0&b&8也是我們部落的恩人",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8當初國家正值動亂",
            "&x&d&6&d&0&b&8無暇顧及我們",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8而當初正是斯雅小姐經過我們部落",
            "&x&d&6&d&0&b&8伸出手給予我們援助",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e沒想到斯雅小姐那麼熱心",
            "§x§d§6§c§1§a§e那他是你的救命恩人又是什麼事",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8這…等等",
            "&x&d&6&d&0&b&8這顆護符好像還缺了點甚麼…",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e怎麼話題就被…",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8還少了些枝椏來作魔力運輸…",
            "&x&d&6&d&0&b&8這附近也許只有異界樹樹根能夠做到",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8你能去幫我摘一些回來嗎?",
            "&x&d&6&d&0&b&8雖然聽起來挺難的…",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e你還知道啊!",
            "§x§d§6§c§1§a§e算了我這回就先幫你吧~",
        ),
    )

    private val mes4 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8沒想到還能再見到你",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e你這壓根就沒有要看見我回來的意思麻",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8好嘛好嘛",
            "&x&d&6&d&0&b&8等我完成後會好好報答你的!",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8給我一點時間喔，",
            "&x&d&6&d&0&b&8我這就能完成拉~",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8(設定等待30秒)",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8完成!",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e那你能告訴我",
            "§x§d§6§c§1§a§e你跟斯雅小姐之間的故事了嗎?",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8其實我一直都很喜歡斯雅",
            "&x&d&6&d&0&b&8當初也是",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8為了要愛他面前耍帥",
            "&x&d&6&d&0&b&8不小心就被魔物包圍了",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8在危難時刻是她爆發了力量",
            "&x&d&6&d&0&b&8才拯救了我",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e那斯雅小姐知道你對她的這份情嗎?",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8我想她並不知道吧",
            "&x&d&6&d&0&b&8所以她才會…",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e才會...?",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8沒什麼，對了冒險者",
            "&x&d&6&d&0&b&8能不能拜託你最後一件事?",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8我想拜託你",
            "&x&d&6&d&0&b&8替我將這一條護符轉交給斯雅。",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e為甚麼不自己給她?",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8因為…因為我還有事",
            "&x&d&6&d&0&b&8部落長老叫我了我得去看看。",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e哀…就喜歡叫我跑腿",
        ),
    )

    private val mes5 = arrayOf(
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8你是…噢!",
            "&x&d&6&d&0&b&8你就是這個城鎮赫赫有名的那位冒險家吧?",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8找我有甚麼事嗎?",
            "&x&d&6&d&0&b&8能幫上忙我都可以！",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e這個給你",
            "§x§d§6§c§1§a§e這是魯柏格請我給你的",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8魯柏格…是那個部落勇者吧",
            "&x&d&6&d&0&b&8沒想到她還記得我",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8她有要你轉達甚麼嗎?",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e我想想…",
            "§x§d§6§c§1§a§e她只有說希望你成功通過試煉",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e僅此而已",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8這樣啊…我想也是",
            "&x&d&6&d&0&b&8幫我轉達他，我下個月就要結婚了",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8但是是被父親安排的",
            "&x&d&6&d&0&b&8與鄰國貴族的婚約",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8希望他能來",
            "&x&d&6&d&0&b&8也能見證通過試煉的我",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e斯雅小姐",
            "§x§d§6§c§1§a§e你對魯柏格的印象如何?",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8非常好，很有趣的人",
            "&x&d&6&d&0&b&8雖然經常逞強自己",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8但都是為了別人",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e就跟斯雅小姐一樣",
            "§x§d§6§c§1§a§e喜歡幫助他人對吧",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8你說這…原來你也知道阿",
            "&x&d&6&d&0&b&8我四處遊歷， 就是在幫助那些部落罷了",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e您可真是勇敢阿",
            "§x§d§6§c§1§a§e斯雅小姐",
        ),
    )

    private val mes6 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8你回來啦，護符交給他了嗎?",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e當然，他還要我轉達你…",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8他要結婚了，對吧?",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e原來你已經知道了",
            "§x§d§6§c§1§a§e你是怎麼知道的?",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8貴族要結婚的消息",
            "&x&d&6&d&0&b&8早已傳遍大街小巷了",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8我們也早已接收到了",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e但他想讓我傳達的不僅僅如此",
            "§x§d§6§c§1§a§e他希望你能去參加他的婚禮",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8我…我不會去的",
            "&x&d&6&d&0&b&8即便當初我有那種勇氣",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8如今的我…依舊不敢",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e(難道…他們倆曾經相愛嗎?)",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e雖然我不清楚事情經過",
            "§x§d§6§c§1§a§e但…你真的有想過斯雅小姐的感受嗎?",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8那他有想過我的感受嗎…",
            "&x&d&6&d&0&b&8這份心意，我們彼此都知道就好",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8就像朦朧的月色",
            "&x&d&6&d&0&b&8朦朧的美",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8月是讓人想觸碰",
            "&x&d&6&d&0&b&8便月是難以觸擊",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8身分的差距",
            "&x&d&6&d&0&b&8足以讓我們欣賞最朦朧的彼此…",
        ),
    )


    init {
        val world = Bukkit.getWorld("2k");
        val obj1 = ListenNPCObjective(this,"obj1",192,*mes1)
            .setInstruction("§7與 §e魯柏格 §7對話");
        val obj2 = GiveMultiItemObjective(this,"obj2",192,
            mapOf(
                "MATERIAL:M016" to 60,
                "MATERIAL:M017" to 20
            ))
            .setInstruction{"§7將 §7${it.get("MATERIAL:M016")}/60 個迷離岩石§8(迷離山岩掉落物)§7、${it.get("MATERIAL:M017")}/20 個 §7紅沙粉末§8(紅砂山脈掉落) §7交給 §e魯柏格§r"};
        val obj3 = ListenNPCObjective(this,"obj3",192,*mes2)
            .setInstruction("§7與 §e魯柏格 §7對話");
        val obj4 = GiveItemObjective(this,"obj4",192,"MATERIAL:M016",20)
            .setInstruction{"§7將 §7${it.amount}/20 個迷離岩石§8(迷離山岩掉落物) §7交給 §7魯柏格§r"};
        val obj5 = ListenNPCObjective(this,"obj5",192,*mes3)
            .setInstruction("§7與 §e魯柏格 §7對話");
        val obj6 = GiveItemObjective(this,"obj6",192,"COLLECTMATERIAL:M034",8)
            .setInstruction{"§7將 §7${it.amount}/8 個異界樹樹根§8(紫晶山丘採集) §7交給 §7魯柏格§r"};
        val obj7 = ListenNPCObjective(this,"obj7",192,*mes4)
            .setInstruction("§7與 §e魯柏格 §7對話")
            .setEndProcess {
                it.sendTitle("§7[§a獲得物品§7]","§d啞紫色的水晶護符")
                it.sendMessage("若弄丟任務物品，請放棄任務，找魯柏格重新接取")
                Utils.command("get ${it.name} QUESTMATERIAL M025")};
        val obj8 = GiveItemObjective(this,"obj8",225,"QUESTMATERIAL:M025",1)
            .setInstruction{"§7將 §7${it.amount}/1 個啞紫色的水晶護符§8(任務物品) §7交給 §7斯雅§r"};
        val obj9 = ListenNPCObjective(this,"obj9",225,*mes5)
            .setInstruction("§7與 §e斯雅 §7對話");
        val obj10 = ListenNPCObjective(this,"obj10",192,*mes6)
            .setInstruction("§7與 §e魯柏格 §7對話");

        pushObjective(obj1,obj2,obj3,obj4,obj5,obj6,obj7,obj8,obj9,obj10)

        addRewards(
            MessageReward("&a➯ &7稱號: §7[§f§b如月光般朦朧的§f§7]"),
            MoneyReward(600), //金牌 300~800
            QuestExpReward(150), //金牌 100~200
            ClassExpReward(450) //金牌 300~500
        )
                    addEndHook({
                Utils.command("lp user ${it.name} permission set ps.suffix.89")
            })

    }
}


val quest = Side_Gold_03();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



