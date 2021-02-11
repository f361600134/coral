package org.coral.server.game.module.mission.domain;

import org.apache.commons.lang3.StringUtils;
import org.coral.server.game.module.base.MainMissionPo;
import org.coral.server.game.module.mission.type.ArtifactMissionType;
import org.coral.server.game.module.mission.type.MainMissionType;
import org.coral.server.game.module.mission.type.MissionTypeData;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
* @author Jeremy
*/
public class MainMission extends MainMissionPo {
	
	/**   
	 * serialVersionUID:变量描述.   
	 */ 
	private static final long serialVersionUID = 1L;
	
	private MissionTypeData missionData;
	
	public MainMission() {

	}

	public MissionTypeData getMissionData() {
		return missionData;
	}
	
	@Override
	public String toString() {
		return "MainMission [missionData=" + missionData + "]";
	}

	@Override
	public void beforeSave() {
		this.setMissionStr(JSON.toJSONString(this.missionData, SerializerFeature.WriteClassName));
	}
	 
	/**
	 * 重写afterLoad, 不使用默认加载
	 */
	@Override
	public void afterLoad() {
		if (StringUtils.isBlank(getMissionStr())) {
			this.missionData = JSON.parseObject(getMissionStr(), MissionTypeData.class);
		}
	}
	
	
	public static void main(String[] args) {
		MissionTypeData data = new MissionTypeData();
		data.getMissionPojos().put(1, new MainMissionType(1));
		data.getMissionPojos().put(2, new MainMissionType(2));
		data.getMissionPojos().put(3, new MainMissionType(3));
		
		data.getMissionPojos().put(1001, new ArtifactMissionType(1001));
		data.getMissionPojos().put(1002, new ArtifactMissionType(1002));
		
		String str = JSON.toJSONString(data, SerializerFeature.WriteClassName);
		System.out.println(str);
		
		MissionTypeData missionData = JSON.parseObject(str, MissionTypeData.class);
		System.out.println(missionData);
		
	}
	
}
