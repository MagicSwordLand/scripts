@file:RequiredPlugins("AltSkill2", "Reactant")

import dev.reactant.reactant.extensions.translateChatColor
import io.github.clayclaw.altskill2.cache.InternalAbilityCache
import io.github.clayclaw.altskill2.dsl.*
import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import java.util.function.Function

AbilityDescriptionPattern.path = PathAbilityDescriptionEditor { builder, props ->
    buildList<String> {
        // builder.displayDescription.forEach { add("&f${it}") }
        (props["description"] as? List<String>)?.forEach { add("&f${it}") }
        add(" ")
        add("&e✦ 點我進入天賦路徑")
    }.map { it.translateChatColor() }
}

AbilityDescriptionPattern.main = MainAbilityDescriptionEditor { builder, props ->
    buildList<String> {

        val maxLevel = defaultExpHandler.maxLevel

        add("主要${builder.getPathNames()}天賦")
        add(" ")
        add("&b✦ 天賦說明")
        // builder.displayDescription.forEach { add("&f${it}") }
        (props["description"] as? List<String>)?.forEach { add("&f${it}") }
        add(" ")
        add("&b✦ 天賦進度")
        add("&6天賦等級: &f%altskill2_level_${builder.id}%/${maxLevel}")
        add("&6等級經驗: &f%altskill2_expbar_${builder.id}%")
    }
}

AbilityDescriptionPattern.addition = AdditionAbilityDescriptionEditor { builder, props ->
    buildList<String> {
        add("次要${builder.getMainAbilitiesNames()}天賦")
        add(" ")
        add("&b✦ 天賦說明")
        // builder.displayDescription.forEach { add("&f${it}") }
        (props["description"] as? List<String>)?.forEach { add("&f${it}") }
        add(" ")
        add("&b✦ 天賦進度")
        add("&6天賦等級: &f%altskill2_level_${builder.id}%/${defaultExpHandler.maxLevel}")
        add("&6等級經驗: &f%altskill2_expbar_${builder.id}%")
    }
}

fun MainAbilityBuilder.getPathNames(): String {
    return when(availableOnPaths.size) {
        0 -> "&c已停用"
        InternalAbilityCache.getAllPaths().size -> "通用"
        else -> availableOnPaths.filter { InternalAbilityCache.hasPath(it) }
            .map { InternalAbilityCache.getPath(it)!! }
            .joinToString("、") { it.displayName }
    }
}

fun AdditionAbilityBuilder.getMainAbilitiesNames(): String {
    return when(availableOnMainAbilities.size) {
        0 -> "&c已停用"
        InternalAbilityCache.getAllAbilities().size -> "通用"
        else -> availableOnMainAbilities.filter { InternalAbilityCache.hasAbility(it) }
            .map { InternalAbilityCache.getAbility(it)!! }
            .joinToString("、") { it.displayName }
    }
}
