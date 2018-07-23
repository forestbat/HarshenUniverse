package kenijey.harshenuniverse.renderers.item;

import javax.vecmath.Vector3f;

import kenijey.harshenuniverse.base.BaseItemRenderer;
import kenijey.harshenuniverse.tileentity.TileEntityHarshenDisplayBlock;

public class RendererHarshenDisplayBlock extends BaseItemRenderer<TileEntityHarshenDisplayBlock>
{
	@Override
	protected float getMovementSpeed(TileEntityHarshenDisplayBlock te) {
		return 0;
	}

	@Override
	protected Vector3f movePos() {
		return new Vector3f(0.5f, 0.97f, 0.5f);
	}
}