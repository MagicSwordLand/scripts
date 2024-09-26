@file:RequiredPlugins("HeroesPets","PlayerDataSync","HeroesSkills","HeroesPetUpgrading")
import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.heroespet.upgrading.models.UpgradeablePet
import net.brian.heroespet.upgrading.utils.PetExpGlobalWhiteList
import net.brian.heroespets.HeroesPets
import net.brian.heroespets.api.pets.HeroesPet
import net.brian.heroespets.api.pets.Pet
import net.brian.heroespets.api.pets.SpawnedPet
import net.brian.heroespets.bukkit.compatible.mythiclib.MythicStats
import net.brian.heroespets.bukkit.configs.configs.Language
import net.brian.heroespets.core.pets.skills.SudoOwnerSkill
import net.brian.heroespets.utils.SkullBuilder
import net.brian.playerdatasync.util.IridiumColorAPI
import net.kyori.adventure.title.Title
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class wizardelf: HeroesPet("wizardelf","智慧的巫師精靈"), UpgradeablePet {


    init {
        display = "智慧的巫師精靈"
        initTier = 3
        height = 1.75
        skill = SudoOwnerSkill("智慧的巫師精靈_雷霆領域",1000L);
    }

    override fun getEntityName(player: Player, pet: Pet): MutableList<String> {
        return arrayListOf(
            "§f${player.name} 的寵物",
            "§7Lv.${pet.level} 智慧的巫師精靈"
        )
    }

    override fun getIcon(player: Player?, pet: Pet): ItemStack {
        val itemStack = SkullBuilder.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjY4Yjk0MTJiY2QxNTYwYzQ2MjlhYzdjZjFkNmUyZDcxZGE2NGUxNzQzOGRlMGQ4OGM5NTIzYmMxMmRlZmM4ZSJ9fX0=")
        val itemMeta = itemStack.itemMeta;
        itemMeta.setDisplayName("§f智慧的巫師精靈 §7Lv.${pet.level}")
        val lore = buildList {
            add("")
            if(player != null) add("§7▪ 主人: &f${player.name}")
            add("§7▪ 品階: &6傑出")
            if(pet.nextLevelExp == -1){
                add(Language.MAX_LEVEL);
            }
            else add("§7▪ 經驗: &f${pet.exp}/${pet.nextLevelExp}")
            add("")
            add("§7§l富含眾多魔法知識的精靈")
            add("§7§l有著強大且傑出的魔力控制力")
            add("")
            add("&f&l雷霆領域 &7| &fCD: 30秒")
            add("&7使巫師精靈念出雷霆的咒語")
            add("&7主人所在的地方釋放出半徑7格領域")
            add("&7每秒造成領域中的敵人 最大魔力*0.3 傷害")
            add("")
            add("&7➤ + ${mgBuff(pet)} 攻擊魔傷")
            add("&7➤ + ${manaBuff(pet)} 最大魔力")

        }


        itemMeta.lore = IridiumColorAPI.process(lore);

        itemStack.itemMeta = itemMeta;
        return itemStack;
    }

    private fun mgBuff(pet: Pet): Double{
        return Math.floor(pet.level*1.25);
    }
    private fun manaBuff(pet: Pet): Double{
        return pet.level*1.5;
    }
    override fun onTick(pet: SpawnedPet, ticks: Long) {
    }
    override fun getWhiteLists(): MutableList<String> {
        val list = mutableListOf<String>();
        list.addAll(PetExpGlobalWhiteList.get());//全局通用果实，如果不要通用吃，就删掉这行
        return  list;
    }
    val modifierKey = "heroes-pet";
    override fun onSpawn(spawnedPet: SpawnedPet) {
        MythicStats.addModifier(spawnedPet.owner.uniqueId,"MG_WEAPON_DAMAGE",mgBuff(spawnedPet.pet),modifierKey)
        MythicStats.addModifier(spawnedPet.owner.uniqueId,"MAX_MANA",manaBuff(spawnedPet.pet),modifierKey)
    }

    override fun onDespawn(spawnedPet: SpawnedPet) {
        MythicStats.removeModifier(spawnedPet.owner.uniqueId,"MG_WEAPON_DAMAGE",modifierKey);
        MythicStats.removeModifier(spawnedPet.owner.uniqueId,"MAX_MANA",modifierKey);
    }


}

HeroesPets.getPlugin().petManager.register(wizardelf());