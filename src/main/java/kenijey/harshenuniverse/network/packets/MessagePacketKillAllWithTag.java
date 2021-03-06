package kenijey.harshenuniverse.network.packets;

import io.netty.buffer.ByteBuf;
import kenijey.harshenuniverse.base.BaseMessagePacket;
import kenijey.harshenuniverse.particle.ParticleItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
public class MessagePacketKillAllWithTag extends BaseMessagePacket<MessagePacketKillAllWithTag>{

	public MessagePacketKillAllWithTag() {
	}
	
	private String tag;
	
	public MessagePacketKillAllWithTag(String tag)
	{
		this.tag = tag;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {		
		tag = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {	
		ByteBufUtils.writeUTF8String(buf, tag);
	}

	@Override
	public void onReceived(MessagePacketKillAllWithTag message, EntityPlayer player) {
		 ParticleItem.killAllParticlesWithTag(message.tag);
	}

}

