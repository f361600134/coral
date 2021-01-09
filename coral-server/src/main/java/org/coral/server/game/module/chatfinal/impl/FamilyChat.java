package org.coral.server.game.module.chatfinal.impl;

import java.math.BigInteger;
import java.util.Collection;

import org.coral.server.game.helper.result.ErrorCode;
import org.coral.server.game.module.chatfinal.domain.ChatEnum;
import org.coral.server.utils.Pair;


/**
 * @author lijr
 */
public class FamilyChat extends AbstractChat {

	public FamilyChat() {
		super(ChatEnum.CH_FAMILY.getCh());
	}

	@Override
	public BigInteger getUniqueId(long senderId, long targetId) {
//		PlayerView playerView = PlayerManager.getInstance().getPlayerView(senderId);
//		long allianceId = playerView == null ? 0 : playerView.getAllianceId();
		long allianceId = 0L;
		BigInteger bigInteger = BigInteger.valueOf(getChannel()).shiftLeft(64)
				.add(BigInteger.valueOf(allianceId));
		return bigInteger;
	}

	@Override
	public Collection<Long> findReceiverIds(BigInteger uniqueId) {
//		Pair<Long, Long> pair = getOriginalIds(uniqueId);
//		Alliance alliance = AllianceManager.getInstance().getAlliance(pair.getRight());
//		if (alliance == null) {
//			return Collections.emptyList();
//		}
//		return alliance.getAllianceData().getMemberMap().keySet();
		return null;
	}
	
	@Override
	public ErrorCode checkChat(long senderId, BigInteger uniqueId) {
		ErrorCode errorCode = super.checkChat(senderId, uniqueId);
		if (!errorCode.isSuccess()) {
			return errorCode;
		}
		Pair<Long, Long> pair = getOriginalIds(uniqueId);
		final long allianceId = pair.getRight();
		if (allianceId <= 0) {
			return ErrorCode.CHAT_ALLIANCE_NOT_EXIST;
		}
		return ErrorCode.SUCCESS;
	}
	

}
