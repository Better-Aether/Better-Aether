package com.gildedgames.aether.common.entities.dungeon.labyrinth.boss;

import com.gildedgames.aether.api.capabilites.entity.boss.BossStage;
import com.gildedgames.aether.api.capabilites.entity.boss.IBossManager;

public class FirstStageSlider extends BossStage<EntitySlider>
{

	public FirstStageSlider()
	{

	}

	@Override protected boolean conditionsMet(EntitySlider entity, IBossManager<EntitySlider> manager)
	{
		return entity.getHealth() <= 400;
	}

	@Override protected void onStageBegin(final EntitySlider entity, IBossManager<EntitySlider> manager)
	{
		manager.addStageAction(new BreakFloorActionSlider());
	}

}
