package net.leotorresgon.neoforgetestmod.item;

import net.leotorresgon.neoforgetestmod.NeoForgeTestMod;
import net.leotorresgon.neoforgetestmod.item.custom.*;
import net.leotorresgon.neoforgetestmod.sound.ModSounds;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(NeoForgeTestMod.MOD_ID);

    // NO PROPERTY ITEMS

    public static final DeferredItem<Item> BISMUTH = ITEMS.register("bismuth",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> RAW_BISMUTH = ITEMS.register("raw_bismuth",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> BLUE_ZIRCON = ITEMS.register("blue_zircon",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> CRYSTAL_BLADE = ITEMS.register("crystal_blade",
            () -> new Item(new Item.Properties()));

    // ITEMS WITH PROPERTIES

    public static final DeferredItem<Item> CHISEL = ITEMS.register("chisel",
            ()-> new ChiselItem(new Item.Properties().durability(32)));

    public static final DeferredItem<Item> DICE = ITEMS.register("dice",
            ()-> new DiceItem(new Item.Properties()));

    public static final DeferredItem<PurpleKitsuneMaskItem> KITSUNE_MASK = ITEMS.registerItem("kitsune_mask_purple", PurpleKitsuneMaskItem::new);

    // WEAPONS

    public static final DeferredItem<Item> WINDSPIRE = ITEMS.register("windspire",
            ()-> new RiptideItem(new Item.Properties().component(DataComponents.TOOL, TridentItem.createToolProperties()).attributes(RiptideItem.createAttributes())
                    .durability(2000).stacksTo(1)));

    public static final DeferredItem<Item> WARP = ITEMS.register("warp",
            ()-> new WarpItem(new Item.Properties().component(DataComponents.TOOL, TridentItem.createToolProperties()).attributes(RiptideItem.createAttributes())
                    .durability(2000).stacksTo(1)));

    public static final DeferredItem<Item> SHOOT_SWORD = ITEMS.register("shoot_sword",
            ()-> new ShootingSwordItem(Tiers.DIAMOND, new Item.Properties().attributes(SwordItem.createAttributes(Tiers.DIAMOND, 3, -2.4f))));

    public static final DeferredItem<Item> CRYSTAL_DAGGER = ITEMS.register("crystal_dagger",
            ()-> new DashDaggerItem(Tiers.DIAMOND, new Item.Properties().attributes(SwordItem.createAttributes(Tiers.DIAMOND, 2, -2.0f))));

    public static final DeferredItem<Item> WITHERTHORN = ITEMS.register("witherthorn",
            ()-> new WitherScytheItem(Tiers.NETHERITE, new Item.Properties().attributes(SwordItem.createAttributes(Tiers.NETHERITE, 5, -3.0f))
                    .stacksTo(1).fireResistant().rarity(Rarity.EPIC)));

    public static final DeferredItem<Item> SOULBOUND_CROSSBOW = ITEMS.register("soulbound_crossbow",
            () -> new SoulwoodCrossbowItem(new Item.Properties().fireResistant().durability(1500).rarity(Rarity.EPIC).stacksTo(1)));

    // MUSIC DISCS

    public static final DeferredItem<Item> TOT_MUSICA_MUSIC_DISC = ITEMS.register("tot_musica_music_disc",
            ()-> new Item(new Item.Properties().jukeboxPlayable(ModSounds.TOT_MUSICA_KEY).stacksTo(1)));


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
// .attributes(TridentItem.createAttributes())