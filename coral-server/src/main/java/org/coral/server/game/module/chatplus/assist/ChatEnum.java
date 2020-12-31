package org.coral.server.game.module.chatplus.assist;

import java.math.BigInteger;

import org.coral.server.game.module.chatplus.impl.AbstractChat;
import org.coral.server.game.module.chatplus.impl.FamilyChat;
import org.coral.server.game.module.chatplus.impl.PrivateChat;
import org.coral.server.game.module.chatplus.impl.WorldChat;
import org.coral.server.utils.Pair;

/**
 * 聊天类枚举,不同频道,要区别处理
 * 当前聊天转发给所有在线玩家, 缓存的聊天上线获取所有
 * @auth Jeremy
 * @date 2019年5月10日下午2:04:37
 */
public enum ChatEnum {
	
	CH_NONE(0){//不存在的频道
		
	},
	CH_PROVINCE(1){ // 同省聊天
	},
	CH_SERVER(2){// 跨服聊天
	}, 
	CH_WORLD(3){// 世界聊天
		@Override
		public AbstractChat newInstance(long uniquId) {
			return new WorldChat(uniquId);
		}
	}, 
	CH_FAMILY(4) {// 工会聊天
		@Override
		public long getUniqueId(long playerId, long targetId) {
			//通过玩家获取到家族id
			return 0;
		}
		
		@Override
		public AbstractChat newInstance(long uniquId) {
			return new FamilyChat(uniquId);
		}
	},
	CH_PRIVATE(5) {// 私聊
		@Override
		public long getUniqueId(long playerId, long targetId) {
			//私聊,通过发送方的玩家id,和目标玩家id,组装成唯一id
	        if (playerId < targetId) {
	        	playerId ^= targetId;
	            targetId ^= playerId;
	            playerId ^= targetId;
	        }
	        BigInteger bigInteger = BigInteger.valueOf(playerId).shiftLeft(64).add(BigInteger.valueOf(targetId));
	        return bigInteger.longValue();
		}
		
		@Override
		public Pair<Long, Long> getSpecialId(long uniqueId) {
			BigInteger key = BigInteger.valueOf(uniqueId);
			BigInteger left = key.shiftRight(64);
	        long playerId = left.longValue();
	        long targetId = left.shiftLeft(64).xor(key).longValue();
			return Pair.of(playerId, targetId);
		}
		
		@Override
		public AbstractChat newInstance(long uniquId) {
			return new PrivateChat(uniquId);
		}
	},
	CH_SYSTEM(6) {// 系统频道
		
	};
	private int ch; // 频道

	/**
	 * 私有枚举构造器
	 * @param ch 频道
	 */
	private ChatEnum(int ch) {
		this.ch = ch;
	}

	public int getCh() {
		return ch;
	}
	
	/**
	 * 通过枚举类生成唯一标识
	 * 默认返回频道号, 因为频道号能作为唯一标识.
	 */
	public long getUniqueId(long playerId, long targetId) {
		return getCh();
	}
	
	/**
	 * 通过唯一标识,返回指定标识
	 */
	public Pair<Long, Long> getSpecialId(long uniqueId) {
		return Pair.of(Integer.valueOf(getCh()).longValue(), 0L);
	}
	
	public AbstractChat newInstance(long uniquId) {
		return null;
	}
	
	/**
	 * 获取聊天枚举
	 * @param ch 频道
	 * @return 聊天枚举
	 */
	public static ChatEnum getEnum(int ch) {
		for (ChatEnum chatEnum : values()) {
			if (ch == chatEnum.getCh()) {
				return chatEnum;
			}
		}
		return null;
	}
	
}
