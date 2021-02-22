package org.coral.server.game.helper.result;

/**
 * 错误码(返回码)<br>
 * 需要生成excel提供给客户端读取
 * @author Jeremy
 */
public enum ErrorCode implements ModuleDefines {
    /**
     *	成功消息
     */
    SUCCESS(0, 0, "成功"),
    // ---------------公共-------------------
    UNKNOWN_ERROR(COMMON, 1, "未知异常"),
    CONFIG_NOT_EXISTS(COMMON, 2, "配置不存在"),
    CONFIG_PARAM_ERROR(COMMON, 3, "配置参数错误"),
    INVALID_PARAM(COMMON, 4, "无效参数"),
    RESOURCE_USE_NUM_ERROR(COMMON, 5, "资源使用数量错误，有些资源必须消耗或者增加必须是特定的数量"),
    RESOURCE_CANNOT_CONSUME(COMMON, 6, "该资源不能被消耗"),
    NO_GM_PERMISSION(COMMON, 7, "没有GM权限"),
    AMOUNT_NOT_ENOUGH(COMMON, 8, "数量不足"),// 对于非资源数据，数量不足的通用描述
    FORMULA_NOT_EXIST(COMMON, 9, "公式不存在"),
    CONTENT_EXIST_BAD_WORD(COMMON, 10, "输入的内容包含敏感字"),
    CONTENT_UNSUPPORT_OPERATION(COMMON, 11, "不支持的操作"),
    // ---------------账号-------------------
    INVALID_ACCOUNT_NAME(ACCOUNT, 1, "无效的账号名"),
    ACCOUNT_NOT_LOGIN(ACCOUNT, 2, "账号未登陆"),
    // ---------------登录-------------------
    
 // ---------------玩家-------------------
    INVALID_PLAYER_NAME(PLAYER, 1, "无效玩家名"),
    PLAYER_NAME_TOO_LENGTH(PLAYER, 2, "玩家名过长"),
    EXISTS_SAME_PLAYER_NAME(PLAYER, 3, "存在相同的玩家名"),
    PLAYER_NOT_EXISTS(PLAYER, 5, "玩家不存在"),
    FUNCTION_NOT_OPEN(PLAYER, 6, "功能未开启"),
    INVALID_COUNTRY_NAME(PLAYER, 7, "无效国家名"),
    COUNTRY_NAME_TOO_LENGTH(PLAYER, 8, "国家名过长"),
    EXISTS_SAME_COUNTRY_NAME(PLAYER, 9, "存在相同的国家名"),
    NAME_NO_CHANGE(PLAYER, 10, "名字没有变化"),
    PLAYER_LEVEL_LIMIT(PLAYER, 11, "玩家等级已满"),
    PLAYER_LEVEL_TOO_LOW(PLAYER, 12, "玩家等级过低"),
    PLAYER_EXP_NOT_ENOUGH(PLAYER, 13, "玩家经验不足"),
    PLAYER_RENAME_ERROR(PLAYER, 14, "一天只能更改一次名字"),

 // -----------------聊天---------------------

    CHAT_CD(CHAT, 1, "聊天太快"),
    CHAT_SILENCE(CHAT, 2, "已被禁言"),
    CHAT_CONDITION_LEVEL_NOT_REACH(CHAT, 3, "等级未达到当前频道聊天条件"),
    CHAT_MESSAGE_IS_EMPTY(CHAT, 4, "聊天信息为空"),
    CHAT_MESSAGE_TOO_LONG(CHAT, 5, "聊天信息太长"),
    CHAT_CHANNEL_NOT_EXISTS(CHAT, 6, "聊天频道不存在"),
    CHAT_REPORT_LIMIT(CHAT, 7, "举报过于频繁，请稍后再试"),
    CHAT_RECORD_NOT_EXIST(CHAT, 8, "聊天记录不存在"),
    CHAT_ALLIANCE_NOT_EXIST(CHAT, 9, "加入联盟才能聊天"),
    CHAT_FRIEND_NOT_EXIST(CHAT, 10, "聊天好友不存在或已不是好友，无法聊天"),
    CHAT_CONDITION_VIP_NOT_REACH(CHAT, 11, "VIP等级未达到当前频道聊天条件"),
    CHAT_SYSTEM_NOT_ALLOWED(CHAT, 12, "该频道禁止聊天"),
    CHAT_TIME_LIMIT(CHAT, 13, "剩余x秒后可聊天"),
    CHAT_TARGET_IS_SELF(CHAT, 14, "聊天目标不能是自己"),
    
    
    
    // -----------------任务---------------------
    MISSION_NOT_COMPLATE(MISSION, 1, "任务未完成"),
    MISSION_REWARDES(MISSION, 2, "任务奖励已领取"),
    
    ;
	
	
	
    private final int code;
    private final String desc;
    
    ErrorCode(int moduleId, int seq, String desc) {
        this.code = moduleId * 100 + seq;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }
    
    /*通常不需要此描述, 仅用于服务器*/
    public String getDesc() {
		return desc;
	}
    
    public boolean isSuccess() {
        return this.code == SUCCESS.getCode();
    }
}
