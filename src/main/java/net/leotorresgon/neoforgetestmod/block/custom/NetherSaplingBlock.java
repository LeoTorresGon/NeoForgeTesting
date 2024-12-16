package net.leotorresgon.neoforgetestmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;

public class NetherSaplingBlock extends SaplingBlock {
    public NetherSaplingBlock(TreeGrower treeGrower, Properties properties) {
        super(treeGrower, properties);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Block ground = level.getBlockState(pos.below()).getBlock();
        return ground == Blocks.SOUL_SOIL || ground == Blocks.SOUL_SAND;
    }
}
