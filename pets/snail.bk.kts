@file:RequiredPlugins("HeroesPets","PlayerDataSync","HeroesSkills","HeroesPetUpgrading")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.heroespet.upgrading.models.UpgradeablePet
import net.brian.heroespet.upgrading.utils.PetExpGlobalWhiteList
import net.brian.heroespets.HeroesPets
import net.brian.heroespets.api.pets.HeroesPet
import net.brian.heroespets.api.pets.Pet
import net.brian.heroespets.api.pets.SpawnedPet
import net.brian.heroespets.bukkit.compatible.mythiclib.MythicStats
import net.brian.heroespets.bukkit.compatible.mythicmobs.MythicPetSkill
import net.brian.heroespets.bukkit.configs.configs.Language
import net.brian.heroespets.core.pets.skills.SudoOwnerSkill
import net.brian.heroespets.utils.SkullBuilder
import net.brian.playerdatasync.util.IridiumColorAPI
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class snail: HeroesPet("snail","堅硬蝸牛"), UpgradeablePet {


    init {
        display = "堅硬蝸牛"
        initTier = 2
        height = 2.0
        skill = SudoOwnerSkill("堅硬蝸牛_縮殼",100000L);
    }

    override fun getEntityName(player: Player, pet: Pet): MutableList<String> {
        return arrayListOf(
            "§f${player.name} 的寵物",
            "§7Lv.${pet.level} 堅硬蝸牛"

        )
    }

    override fun getIcon(player: Player?, pet: Pet): ItemStack {
        val itemStack = SkullBuilder.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2EyMDhjM2ZjN2U1ZWI1MDllOTU3ZmM0MGE3ZmI2NTU3MGExMDJjN2E5OWU1ZmRmNWE4NDJiMzVhMGNjYWFiYyJ9fX0=")
        val itemMeta = itemStack.itemMeta;
        itemMeta.setDisplayName("§f堅硬蝸牛 §7Lv.${pet.level}")
        val lore = buildList {
            add("")
            if(player != null) add("§7▪ 主人: &f${player.name}")
            add("§7▪ 品階: &b稀有")
            if(pet.nextLevelExp == -1){
                add(Language.MAX_LEVEL);
            }
            else add("§7▪ 經驗: &f${pet.exp}/${pet.nextLevelExp}")
            add("")
            add("§7§l有著硬殼的蝸牛")
            add("")
            add("&f&l縮殼 &7| &fCD: 100秒")
            add("&7縮進堅硬的殼裡10秒")
            add("&7獲得失明及劇烈緩速")
            add("&7但每秒恢復5%血量")
            add("&7&n過程中需在地面及持續蹲下")
            add("")
            add("&7➤ + ${maxmana(pet)} 最大血量")
            add("&7➤ + ${regenmana(pet)} 回血")

        }

        itemMeta.lore = IridiumColorAPI.process(lore);

        itemStack.itemMeta = itemMeta;
        return itemStack;
    }

    private fun maxmana(pet: Pet): Double {
        return (pet.level*1.5);
    }
    private fun regenmana(pet: Pet): Double {
        return (pet.level*0.5);
    }
    override fun onTick(pet: SpawnedPet, ticks: Long) {
        // 每個 Tick 要做的事
        /*if(ticks % (60*20) == 0L){
            pet.owner.addPotionEffect(PotionEffect(PotionEffectType.SPEED,400,2));
        }*/
    }
    override fun getWhiteLists(): MutableList<String> {
        val list = mutableListOf<String>();
        list.addAll(PetExpGlobalWhiteList.get());//全局通用果实，如果不要通用吃，就删掉这行
        return  list;
    }
    val modifierKey = "heroes-pet";
    override fun onSpawn(spawnedPet: SpawnedPet) {
        MythicStats.addModifier(spawnedPet.owner.uniqueId,"MAX_HEALTH",maxmana(spawnedPet.pet),modifierKey)
        MythicStats.addModifier(spawnedPet.owner.uniqueId,"HEALTH_REGENERATION",regenmana(spawnedPet.pet),modifierKey)
    }

    override fun onDespawn(spawnedPet: SpawnedPet) {
        MythicStats.removeModifier(spawnedPet.owner.uniqueId,"MAX_HEALTH",modifierKey);
        MythicStats.removeModifier(spawnedPet.owner.uniqueId,"HEALTH_REGENERATION",modifierKey);
    }


}

HeroesPets.getPlugin().petManager.register(snail());