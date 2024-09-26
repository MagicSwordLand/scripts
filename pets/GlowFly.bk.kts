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

class GlowFly: HeroesPet("glow-fly","艾靈之夢"), UpgradeablePet {


    init {
        display = "艾靈之夢"
        initTier = 1
        skill = SudoOwnerSkill("艾靈之夢_艾靈夢境",50000L);
    }

    override fun getEntityName(player: Player, pet: Pet): MutableList<String> {
        return arrayListOf(
            "§f${player.name} 的寵物",
            "§7Lv.${pet.level} 艾靈之夢"

        )
    }

    override fun getIcon(player: Player?, pet: Pet): ItemStack {
        val itemStack = SkullBuilder.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWZkODA2ZGVmZGZkZjU5YjFmMjYwOWM4ZWUzNjQ2NjZkZTY2MTI3YTYyMzQxNWI1NDMwYzkzNThjNjAxZWY3YyJ9fX0=")
        val itemMeta = itemStack.itemMeta;
        itemMeta.setDisplayName("§f艾靈之夢 §7Lv.${pet.level}")
        val lore = buildList {
            add("")
            if(player != null) add("§7▪ 主人: &f${player.name}")
            add("§7▪ 品階: &f普通")
            if(pet.nextLevelExp == -1){
                add(Language.MAX_LEVEL);
            }
            else add("§7▪ 經驗: &f${pet.exp}/${pet.nextLevelExp}")
            add("")
            add("§7§l艾靈未污染前存在的螢火蟲精靈")
            add("§7§l象征著純潔和靈性")
            add("")
            add("&f&l艾靈夢境 &7| &fCD: 50秒")
            add("&7使主人陷入美好的夢境")
            add("&710秒內獲得40額外血量及緩速I")
            add("")
            add("&7➤ + ${expBuff(pet)} 最大血量")

        }

        itemMeta.lore = IridiumColorAPI.process(lore);

        itemStack.itemMeta = itemMeta;
        return itemStack;
    }

    private fun expBuff(pet: Pet): Double {
        return (2.5+(pet.level/5)*2.5);
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
        MythicStats.addModifier(spawnedPet.owner.uniqueId,"MAX_HEALTH",expBuff(spawnedPet.pet),modifierKey)
    }

    override fun onDespawn(spawnedPet: SpawnedPet) {
        MythicStats.removeModifier(spawnedPet.owner.uniqueId,"MAX_HEALTH",modifierKey);
    }


}

HeroesPets.getPlugin().petManager.register(GlowFly());