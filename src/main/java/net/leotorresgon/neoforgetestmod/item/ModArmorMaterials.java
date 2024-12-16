package net.leotorresgon.neoforgetestmod.item;

import net.leotorresgon.neoforgetestmod.NeoForgeTestMod;
import net.leotorresgon.neoforgetestmod.util.MekanismDeferredHolder;
import net.leotorresgon.neoforgetestmod.util.MekanismDeferredRegister;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.EnumMap;

public class ModArmorMaterials {
    public static final MekanismDeferredRegister<ArmorMaterial> ARMOR_MATERIALS = new MekanismDeferredRegister<>(Registries.ARMOR_MATERIAL, NeoForgeTestMod.MOD_ID);

//    public static final Holder<ArmorMaterial> KITSUNE_MASKS = register("kitsune",
//            Util.make(new EnumMap<>(ArmorItem.Type.class), attribute -> {
//                attribute.put(ArmorItem.Type.HELMET, 5);
//            }), 16, 2f, 0f, ModItems.BLUE_ZIRCON, SoundEvents.ARMOR_EQUIP_GENERIC);

    public static final MekanismDeferredHolder<ArmorMaterial, ArmorMaterial> KITSUNE_MASKS = registerBaseSpecial("kitsune_mask");



    private static Holder<ArmorMaterial> register(String name, EnumMap<ArmorItem.Type, Integer> typeProtection,
                                                  int enchantability, float toughness, float knockbackResistance,
                                                  Supplier<Item> ingredientItem, Holder<SoundEvent> equipSound) {
        ResourceLocation location = ResourceLocation.fromNamespaceAndPath(NeoForgeTestMod.MOD_ID, name);
        Supplier<Ingredient> ingredient = () -> Ingredient.of(ingredientItem.get());

        List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(location));

        EnumMap<ArmorItem.Type, Integer> typeMap = new EnumMap<>(ArmorItem.Type.class);
        for (ArmorItem.Type type : ArmorItem.Type.values()) {
            typeMap.put(type, typeProtection.get(type));
        }

        return Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL, location,
                new ArmorMaterial(typeProtection, enchantability, equipSound, ingredient, layers, toughness, knockbackResistance));
    }

    private static MekanismDeferredHolder<ArmorMaterial, ArmorMaterial> registerBaseSpecial(String name){
        return ARMOR_MATERIALS.register(name, () -> new ArmorMaterial(Collections.emptyMap(), 0, SoundEvents.ARMOR_EQUIP_GENERIC,
                () -> Ingredient.EMPTY, Collections.emptyList(), 0, 0));
    }


}
