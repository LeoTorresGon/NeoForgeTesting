package net.leotorresgon.neoforgetestmod.block.entity;

import net.leotorresgon.neoforgetestmod.item.ModItems;
import net.leotorresgon.neoforgetestmod.screen.SoulTunerMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SoulTunerBlockEntity extends BlockEntity implements MenuProvider {

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;

    private final ItemStackHandler itemStackHandler = new ItemStackHandler(2) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (level != null)
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_ALL);
        }
    };

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 78;


    public SoulTunerBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.SOUL_TUNER_BE.get(), pos, blockState);

        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index){
                    case 0 -> SoulTunerBlockEntity.this.progress;
                    case 1 -> SoulTunerBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index){
                    case 0 -> SoulTunerBlockEntity.this.progress = value;
                    case 1 -> SoulTunerBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    public void drops(){
        SimpleContainer inventory = new SimpleContainer(itemStackHandler.getSlots());
        for (int i = 0; i < itemStackHandler.getSlots(); i++){
            inventory.setItem(i, itemStackHandler.getStackInSlot(i));
        }
        Containers.dropContents(level, this.worldPosition, inventory);
    }

    @Override
    public void onLoad() {
        super.onLoad();
    }

    public ItemStackHandler getInventory(){
        return this.itemStackHandler;
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable("neoforgetestmod:block/soul_tuner");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new SoulTunerMenu(containerId, playerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("inventory", this.itemStackHandler.serializeNBT(registries));
        tag.putInt("soul_tuner_progress", progress);
        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        itemStackHandler.deserializeNBT(registries, tag.getCompound("inventory"));
        progress = tag.getInt("soul_tuner_progress");
        super.loadAdditional(tag, registries);
    }


    public void tick(Level level, BlockPos pos, BlockState state) {
        if(hasRecipe()){
            increaceCraftingProgress();
            setChanged(level, pos, state);


            if(hasProgressFinished()){
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }

    private void increaceCraftingProgress() {
        progress++;
    }

    private boolean hasProgressFinished() {
        return progress >= maxProgress;
    }

    private void craftItem() {
        ItemStack result = new ItemStack(ModItems.BLUE_ZIRCON.get(), 1);
        this.itemStackHandler.extractItem(INPUT_SLOT, 1, false);

        this.itemStackHandler.insertItem(OUTPUT_SLOT, new ItemStack(result.getItem()), false);
    }

    private void resetProgress() {
        progress = 0;
    }

    private boolean hasRecipe() {
        boolean hasCraftingItem = this.itemStackHandler.getStackInSlot(INPUT_SLOT).getItem() == ModItems.BLUE_ZIRCON.get();
        ItemStack result = new ItemStack(ModItems.BLUE_ZIRCON.get());
        return hasCraftingItem && canInsertItemIntoOutputSlot(result.getItem()) && canInsertAmountIntoOutputSlot(result.getCount()) ;
    }

    private boolean canInsertAmountIntoOutputSlot(int count){
        return this.itemStackHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count <= this.itemStackHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }

    private boolean canInsertItemIntoOutputSlot(Item item){
        return this.itemStackHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemStackHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }


}
