package kenijey.harshenuniverse.base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.items.ItemStackHandler;

public class HandlerInventory implements IInventory
{

	protected final ItemStackHandler handler;
	private TileEntity te;
	
	public HandlerInventory(ItemStackHandler handler) {
		this.handler = handler;
	}
	
	@Override
	public String getName() {
		return "";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public int getSizeInventory() {
		return this.handler.getSlots();
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return handler.getStackInSlot(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		handler.getStackInSlot(index).shrink(count);
		return handler.getStackInSlot(index);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		ItemStack stack = handler.getStackInSlot(index);
		handler.setStackInSlot(index, ItemStack.EMPTY);
		return stack;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) 
	{
		handler.setStackInSlot(index, stack);
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return false;
	}

	@Override
	public void openInventory(EntityPlayer player) {
		
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		
	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
		
	}

	@Override
	public ITextComponent getDisplayName() {
		return null;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public void markDirty() {
		if(te != null)
			te.markDirty();
	}
	
	public HandlerInventory setTe(TileEntity te)
	{
		this.te = te;
		return this;
	}

}
