package org.coral.server.game.module.item.proto;

import java.util.List;
import java.util.Map;

import org.coral.net.core.base.IProtocol;
import org.coral.server.game.data.proto.PBBag;
import org.coral.server.game.data.proto.PBDefine;
import org.coral.server.game.module.item.domain.IItem;

import com.google.protobuf.AbstractMessageLite.Builder;
import com.google.protobuf.Message;

public class AckRewardsResp implements IProtocol {

	private PBBag.AckRewards.Builder builder;

	public static AckRewardsResp newInstance() {
		return new AckRewardsResp();
	}

	public AckRewardsResp() {
		this.builder = PBBag.AckRewards.newBuilder();
		this.setType(1);// 默认弹窗
	}

	/*
	 * 弹窗类型, 1:弹窗,2:条状飘窗
	 */
	public AckRewardsResp setType(int value) {
		this.builder.setType(value);
		return this;
	}

	/**
	 * 获取奖励详细信息
	 * @param items
	 */
	public AckRewardsResp addAllRewards(List<IItem> items) {
		for (IItem item : items) {
			this.builder.addRewards(item.toProto());
		}
		return this;
	}
	
	/**
	 * 封装奖励简单信息
	 * 属性值只包含configId, number
	 * @param rewardMap
	 */
	public AckRewardsResp addAllRewards(Map<Integer, Integer> rewardMap) {
		PBBag.ItemInfo.Builder builder = null;
		for (Integer configId : rewardMap.keySet()) {
			builder = PBBag.ItemInfo.newBuilder();
			builder.setNum(rewardMap.get(configId));
			builder.setConfigId(configId);
			this.builder.addRewards(builder);
		}
		return this;
	}

	@Override
	public short protocol() {
		return PBDefine.PBProtocol.AckRewards_VALUE;
	}

	@Override
	public Builder<?, ?> getBuilder() {
		return builder;
	}

}
