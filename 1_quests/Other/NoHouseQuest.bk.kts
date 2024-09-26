@file:RequiredPlugins("ScriptedQuests","MMOItems","MythicLib")


import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.conditions.CanDoQuestCondition
import net.brian.scriptedquests.api.conditions.Condition
import net.brian.scriptedquests.api.conditions.FinishedQuestsCondition
import net.brian.scriptedquests.api.conditions.QuestLevelCondition
import net.brian.scriptedquests.api.quests.Quest
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.conversation.PlayerOption
import net.brian.scriptedquests.objectives.GiveItemObjective
import net.brian.scriptedquests.objectives.GiveMoneyObjective
import net.brian.scriptedquests.objectives.ListenTalkObjective
import net.brian.scriptedquests.objectives.MoveToObjective
import net.brian.scriptedquests.rewards.ClassExpReward
import net.brian.scriptedquests.rewards.MoneyReward
import net.brian.scriptedquests.rewards.QuestExpReward
import net.brian.scriptedquests.utils.Utils

val villagerIcon = IconFonts.getNPC(14);
val playerIcon = "%squests_icon%";
val lBrotherIcon = IconFonts.getNPC(99);
val greyIcon = IconFonts.getNPC(64);
val bBrotherIcon = IconFonts.getNPC(98);

val questID = "other_no_house"

val villager = "§c● §e明諾克德§r:"
val playerName = "§c● §f%player_displayname%§r:";
val lBrother = "§c● §e彼榕§r:"
val grey = "§c● §e葛雷§r:"
val bBrother = "§c● §e蘭博爾§r:"

val mes1 = arrayOf(
    *Utils.getMessage(villagerIcon,
        "$villager",
        "&x&d&6&d&0&b&8那麽請你出發吧，先從碼頭的兩兄弟開始！",
        "&x&d&6&d&0&b&8這根棍子可以幫助他們收集好運~",
    ),
    *Utils.getMessage(playerIcon,
        "$playerName",
        "§x§d§6§c§1§a§e買房靠這跟棍子能幹什麽...",
    ),
    *Utils.getMessage(villagerIcon,
        "$villager",
        "&x&d&6&d&0&b&8你拿給他們就知道了",
    )
)

val mes2 = arrayOf(
    *Utils.getMessage(lBrotherIcon,
        "$lBrother",
        "&x&d&6&d&0&b&8啊， 哥哥， 你那碗麵給我樓一口唄",
    ),
    *Utils.getMessage(bBrotherIcon,
        "$bBrother",
        "&x&d&6&d&0&b&8不行， 我的， 這碗麵超貴der",
        "&x&d&6&d&0&b&8想吃自己買， 小崽子",
    ),
    *Utils.getMessage(lBrotherIcon,
        "$lBrother",
        "&x&d&6&d&0&b&8嗤， 小氣",
    ),
    *Utils.getMessage(bBrotherIcon,
        "$bBrother",
        "&x&d&6&d&0&b&8就是不給， 這碗麵跟我姓了",
    ),
    *Utils.getMessage(playerIcon,
        "$playerName",
        "§x§d§6§c§1§a§e那...那個......我是村長派來的",
    ),
    *Utils.getMessage(bBrotherIcon,
        "$bBrother",
        "&x&d&6&d&0&b&8我靠這什麽啊！",
    ),
    *Utils.getMessage(lBrotherIcon,
        "$lBrother",
        "&x&d&6&d&0&b&8嚇死我了， 原來你站在旁邊這麽久， 不出聲！",
    ),
    *Utils.getMessage(playerIcon,
        "$playerName",
        "§c(一番討論過後) ",
    ),
    *Utils.getMessage(bBrotherIcon,
        "$bBrother",
        "&x&d&6&d&0&b&8也就是説你爲了維護諾亞村的房價",
        "&x&d&6&d&0&b&8從村長那裏來到了， 藏在貨櫃後面的我們",
    ),
    *Utils.getMessage(lBrotherIcon,
        "$lBrother",
        "&x&d&6&d&0&b&8還在我們搶面的時候...",
    ),
    *Utils.getMessage(playerIcon,
        "$playerName",
        "§x§d§6§c§1§a§e沒錯, 然後當我帶著笑容向你打招呼后",
        "§x§d§6§c§1§a§e你哥就差點把我打了一頓",
    ),
    *Utils.getMessage(bBrotherIcon,
        "$bBrother",
        "&x&d&6&d&0&b&8但是爲什麽站在那裏不出聲",
    ),
    *Utils.getMessage(playerIcon,
        "$playerName",
        "§x§d§6§c§1§a§e因爲我看你們在搶面, 搶得不亦樂乎",
    ),
    *Utils.getMessage(bBrotherIcon,
        "$bBrother",
        "&x&d&6&d&0&b&8不過爲什麽是你來找我們",
        "&x&d&6&d&0&b&8不是應該是一些美女職員之類的嗎?",
        "&x&d&6&d&0&b&8諾亞鎮到底是怎麽了, 是請不起嗎?",
    ),
    *Utils.getMessage(lBrotherIcon,
        "$lBrother",
        "&x&d&6&d&0&b&8那接下來你想怎麽樣...",
    ),
    *Utils.getMessage(playerIcon,
        "$playerName",
        "§x§d§6§c§1§a§e哦, 對了, 關於這個......給",
        "§x§d§6§c§1§a§e請收下思密達",
    ),
)

val mes3 = arrayOf(
    *Utils.getMessage(lBrotherIcon,
        "$lBrother",
        "&x&d&6&d&0&b&8怎麽拿了根棍棍, 啊難道是...",
    ),
    *Utils.getMessage(playerIcon,
        "$playerName",
        "§x§d§6§c§1§a§e額...沒錯, 村長說是買房棍(才沒)",
        "§x§d§6§c§1§a§e據説拿到了的你們知道怎麽用",
    ),
    *Utils.getMessage(lBrotherIcon,
        "$lBrother",
        "&x&d&6&d&0&b&8真假, 能買房就靠這根棍子",
        "&x&d&6&d&0&b&8這是什麽魔法棒嗎?",
    ),
    *Utils.getMessage(playerIcon,
        "$playerName",
        "§x§d§6§c§1§a§e額...就是魔法棒啊",
        "§x§d§6§c§1§a§e名字叫買房棍(才沒), 完全凑巧而已",
    ),
    *Utils.getMessage(lBrotherIcon,
        "$lBrother",
        "&x&d&6&d&0&b&8沒有關係啊?",
        "&x&d&6&d&0&b&8這是什麽魔法棒嗎?",
    ),
    *Utils.getMessage(playerIcon,
        "$playerName",
        "§x§d§6§c§1§a§e嗯, 完全凑巧而已",
    ),
    *Utils.getMessage(lBrotherIcon,
        "$lBrother",
        "&x&d&6&d&0&b&8那...它能幹什麽?",
    ),
    *Utils.getMessage(playerIcon,
        "$playerName",
        "§x§d§6§c§1§a§e你用這個變身成富二代去買房啊",
    ),
    *Utils.getMessage(bBrotherIcon,
        "$bBrother",
        "&x&d&6&d&0&b&8唉, 你看, 我就知道是這樣",
    ),
    *Utils.getMessage(lBrotherIcon,
        lBrother,
        "&x&d&6&d&0&b&8合理吖哥",
        "&x&d&6&d&0&b&8這樣我就能在銀牌傭兵那邊顯擺了",
    ),
    *Utils.getMessage(bBrotherIcon,
        bBrother,
        "&x&d&6&d&0&b&8你傻啊, 用得着顯擺嗎?",
        "&x&d&6&d&0&b&8我們都快銀牌了",
    ),
    *Utils.getMessage(lBrotherIcon,
        lBrother,
        "&x&d&6&d&0&b&8哦, 完全沒有問題",
        "&x&d&6&d&0&b&8完全沒有問題啊哥哥",
    ),
    *Utils.getMessage(bBrotherIcon,
        bBrother,
        "&x&d&6&d&0&b&8你幹嘛叠句...",
        "&x&d&6&d&0&b&8所以我要像超人那樣嗎, 我已經成年了...",
        "&x&d&6&d&0&b&8從年齡上來説我也就完全沒有機會做富二代了...",
    ),
    *Utils.getMessage(bBrotherIcon,
        bBrother,
        "&x&d&6&d&0&b&8可是我又在等這種機會",
        "&x&d&6&d&0&b&8所以我要像超人那樣嗎, 我已經成年了...",
        "&x&d&6&d&0&b&8而且要是被我媽發現我考不上銀牌傭兵",
    ),
    *Utils.getMessage(bBrotherIcon,
        bBrother,
        "&x&d&6&d&0&b&8在這裏發瘋變身",
        "&x&d&6&d&0&b&8俺媽不得把我弄死",
        "&x&d&6&d&0&b&8你大老遠跑來求我, 我不幹就是不給你面子對吧",
    ),
    *Utils.getMessage(playerIcon,
        "$playerName",
        "§x§d§6§c§1§a§e阿不, 真相是",
        "§x§d§6§c§1§a§e我隨便就接了一個任務跑來這裏的",
    ),
    *Utils.getMessage(bBrotherIcon,
        "$bBrother",
        "&x&d&6&d&0&b&8原來是這樣啊, 不給力了",
    ),
    *Utils.getMessage(playerIcon,
        "$playerName",
        "§x§d§6§c§1§a§e別讓村長聽到了",
        "§x§d§6§c§1§a§e我還指望他給我發獎勵呢",
        "§x§d§6§c§1§a§e所以現在怎樣",
    ),
    *Utils.getMessage(playerIcon,
        "$playerName",
        "§x§d§6§c§1§a§e所以現在怎樣",
        "§x§d§6§c§1§a§e你到底要不要變成房奴",
        "§x§d§6§c§1§a§e啊, 不對",
    ),
    *Utils.getMessage(playerIcon,
        "$playerName",
        "§x§d§6§c§1§a§e現在情況緊急, 快變身成富二代",
        "§x§d§6§c§1§a§e照亮這個烟霧彌漫的世界吧!",
    ),
    *Utils.getMessage(bBrotherIcon,
        "$bBrother",
        "&x&d&6&d&0&b&8可是這個棍子要怎麽用才會變身啊",
    ),
    *Utils.getMessage(lBrotherIcon,
        "$lBrother",
        "&x&d&6&d&0&b&8你真的有要變吖哥哥",
    ),
    *Utils.getMessage(bBrotherIcon,
        "$bBrother",
        "&x&d&6&d&0&b&8稍微試試吧",
    ),
    *Utils.getMessage(playerIcon,
        playerName,
        "§x§d§6§c§1§a§e你應該已經知道用法了",
        "§x§d§6§c§1§a§e當你拿到這個棍子的時候",
        "§x§d§6§c§1§a§e咒文就已經出現在了你的腦海裏",
    ),
    *Utils.getMessage(playerIcon,
        playerName,
        "§x§d§6§c§1§a§e請施加那BUFF吧",
    ),
    *Utils.getMessage(bBrotherIcon,
        "$bBrother",
        "&x&d&6&d&0&b&8啊, 我明白了, 那麽...",
        "&x&d&6&d&0&b&8富二代! 蘭博爾! 變成我!",
    ),
    *Utils.getMessage(playerIcon,
        "$playerName",
        "§x§d§6§c§1§a§e應該...完全錯誤吧",
    ),
    *Utils.getMessage(bBrotherIcon,
        "$bBrother",
        "&x&d&6&d&0&b&8我沒吃飽, 當然會這樣啊...",
        "&x&d&6&d&0&b&8你給我買一些零食我才繼續了",
        "&x&d&6&d&0&b&8不然你也回不去交任務",
    ),
    *Utils.getMessage(playerIcon,
        "$playerName",
        "§x§d§6§c§1§a§e......",
    ),
)

val mes4 = arrayOf(
    *Utils.getMessage(playerIcon,
        "$playerName",
        "§x§d§6§c§1§a§e這些夠了吧?",
    ),
    *Utils.getMessage(bBrotherIcon,
        "$bBrother",
        "&x&d&6&d&0&b&8不夠, 還有升為銀牌傭兵所需要的素材和錢錢",
    ),
    *Utils.getMessage(playerIcon,
        "$playerName",
        "§x§d§6§c§1§a§e你坑我呢!",
    ),
    *Utils.getMessage(bBrotherIcon,
        "$bBrother",
        "&x&d&6&d&0&b&8...好吧, 等我一下",
    )
)

val mes5 = arrayOf(
    *Utils.getMessage(bBrotherIcon,
        "$bBrother",
        "&x&d&6&d&0&b&8用銅牌的力量",
        "&x&d&6&d&0&b&8滿狀態變成富二代吧!",
    ),
    *Utils.getMessage(greyIcon,
        "$grey",
        "&x&d&6&d&0&b&8現在疫情嚴重, 請勿聚衆",
        "&x&d&6&d&0&b&8欸...你們在那邊嚷嚷著幹嘛...",
    ),
    *Utils.getMessage(bBrotherIcon,
        "$bBrother",
        "&x&d&6&d&0&b&8啊, 葛雷...啊",
        "&x&d&6&d&0&b&8不對, 我衣服會不會瞬間撕開...",
    ),
    *Utils.getMessage(playerIcon,
        "$playerName",
        "§x§d§6§c§1§a§e不會, 別擔心",
        "§x§d§6§c§1§a§e衣服會瞬間撕開的那個是你弟弟",
    ),
    *Utils.getMessage(lBrotherIcon,
        "$lBrother",
        "&x&d&6&d&0&b&8哈? 爲什麽是我?!",
    ),
    *Utils.getMessage(playerIcon,
        "$playerName",
        "§x§d§6§c§1§a§e這就是奇跡來臨的時刻",
    ),
    *Utils.getMessage(greyIcon,
        "$grey",
        "&x&d&6&d&0&b&8額...這也太不和諧了",
    ),
    *Utils.getMessage(bBrotherIcon,
        "$bBrother",
        "&x&d&6&d&0&b&8然後呢, 爲什麽我沒變成富二代...",
    ),
    *Utils.getMessage(playerIcon,
        "$playerName",
        "§x§d§6§c§1§a§e你要不斷地幫助有困難的人才能成爲富二代",
    ),
    *Utils.getMessage(bBrotherIcon,
        "$bBrother",
        "&x&d&6&d&0&b&8啊, 還得升級嗎? 這麽麻煩...",
    ),
    *Utils.getMessage(lBrotherIcon,
        "$lBrother",
        "&x&d&6&d&0&b&8額, 現在有一個肯定遇到了困難的人",
        "&x&d&6&d&0&b&8就是你們身邊的人",
    )
)

class NoHouseQuest:Quest(questID,"§7[§d特殊委託§7] §f房價危機"){

    init {
        val obj1 = ListenTalkObjective(this,"obj1",*mes1).disableWalk(true)
            .setInstruction("§7與 §e明諾克德 §7對話")
            .setEndProcess{
                Utils.command("get ${it.name} QUESTMATERIAL QM008 1")
                it.sendTitle("§7[§a獲得物品§7]","§f棍子")
                it.sendMessage("§7[§c提示§7] §f弄丟棍子後只能放棄任務重新接受")
            }

        val obj2 = MoveToObjective("obj2","2k",-318.0, 64.0, 489.0,1.0)
            .setInstruction("§7找到兩兄弟")
        val obj3 = ListenTalkObjective(this,"obj3",*mes2).disableWalk(false)
            .setInstruction("§7與兩兄弟對話")
            .setEndProcess{it.sendMessage("§7[§c提示§7] §7請把棍子交給 §e蘭博爾")}
        val obj4 = GiveItemObjective(this,"obj4",98,"QUESTMATERIAL:QM008", 1)
            .setInstruction("§7把棍子交給 §e蘭博爾")
        val obj5 = ListenTalkObjective(this,"obj5",*mes3)
            .setInstruction("§7與兩兄弟對話");
        val obj6 = GiveItemObjective(this,"obj6",98,"FOOD:F001" , 64)
            .setInstruction{data ->"交給蘭博爾 ${data.amount}/64 個麵包"}
        val obj7 = ListenTalkObjective(this,"obj7",*mes4)
            .setInstruction("§7與 §e蘭博爾 §7對話")
        val obj8 = GiveItemObjective(this,"obj8",98, "CONSUMABLE:C044",64).setInstruction("交給蘭伯特 64 個普通強化石")
            .setEndProcess{  }
        val obj9 = GiveMoneyObjective("obj9",98,20f)
            .setMessage("點擊此訊息支付蘭博爾20錢幣")
            .setInstruction("交給蘭伯特20錢幣")
        val obj10 = ListenTalkObjective(this,"obj10",*mes5)
            .disableWalk(false)
            .setInstruction("對話");

        pushObjective(obj1,obj2,obj3,obj4,obj5,obj6,obj7,obj8,obj9,obj10);
        addRewards(
            MoneyReward(50),
            QuestExpReward(30), //
            ClassExpReward(500),
        )
    }

    override fun getStartOption(message: String, canRedo: Boolean, vararg conditions: Condition): PlayerOption {
        return PlayerOption("§7[§d特殊委託§7] §f村長怎麼愁眉苦臉的?")
            .addConditions(
                CanDoQuestCondition(questID,false),
                QuestLevelCondition(1)
            )
            .setNpcResponse(
                NPCQuestion(
                    *Utils.getMessage(
                        villagerIcon, villager,
                        "現在諾亞村的物價，特別是房價，正在飛漲",
                        "由於房租的上漲，租房者的精神開始分裂",
                        "帶著死魚眼神，鷄頭白臉找房子的傭兵正在增加"
                    ),
                    *Utils.getMessage(
                        villagerIcon, villager,
                        "爲了讓這樣的人能夠找到適合的房子",
                        "能麻煩你出動嗎？"
                    )
                )
                    .addPlayerOptions(
                        super.getStartOption("[接受任務] 好啊!", canRedo, *conditions),
                        PlayerOption("[暫時拒絕] 蝦？").setNpcResponse(
                            *Utils.getMessage(villagerIcon, villager, "看來這人也精神分裂，靠不住了。")
                        )
                    )
            );
    }

}


val quest = NoHouseQuest();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}