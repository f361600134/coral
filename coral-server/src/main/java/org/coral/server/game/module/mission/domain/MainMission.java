package org.coral.server.game.module.mission.domain;

import org.coral.orm.core.annotation.Column;
import org.coral.server.game.module.base.MainMissionPo;
import org.coral.server.game.module.mission.type.MainMissionType;
import org.coral.server.game.module.mission.type.MissionTypeData;

/**
 * 主线任务对象, 所有主线任务存储在此
* @author Jeremy
*/
public class MainMission extends MainMissionPo {
	
	/**   
	 * serialVersionUID:变量描述.   
	 */ 
	private static final long serialVersionUID = 1L;
	
	/**
	 * 	任务对象
	 */
	@Column(PROP_MISSIONSTR)
	private MissionTypeData<MainMissionType> missionData;
	
	public MainMission() {

	}
	
	public MainMission(long playerId) {
		this.playerId = playerId;
	}

	public MissionTypeData<MainMissionType> getMissionData() {
		return missionData;
	}
	
	public static MainMission create(long playerId) {
		return new MainMission(playerId);
	}
	
	@Override
	public String toString() {
		return "MainMission [missionData=" + missionData + "]";
	}

//	public static void main(String[] args) {
//		MissionTypeData<MainMissionType> data = new MissionTypeData<MainMissionType>();
//		data.getMissionPojos().put(1, new MainMissionType(1));
//		data.getMissionPojos().put(2, new MainMissionType(2));
//		data.getMissionPojos().put(3, new MainMissionType(3));
//		
////		data.getMissionPojos().put(1001, new ArtifactMissionType(1001, 1));
////		data.getMissionPojos().put(1002, new ArtifactMissionType(1002, 2));
//
//		data.getFinishIds().add(1);
//		data.getFinishIds().add(1001);
//		
//		String str = JSON.toJSONString(data);
//		System.out.println(str);
//		
//		MissionTypeData<MainMissionType> missionData = JSON.parseObject(str, new TypeReference<MissionTypeData<MainMissionType>>() {});
//		System.out.println(missionData);
//		
//		TypeReference<MissionTypeData<MainMissionType>> type = new TypeReference<MissionTypeData<MainMissionType>>() {};
//		System.out.println(type.getType());
//		
//		ParameterizedTypeReference ref = new ParameterizedTypeReference<MissionTypeData<MainMissionType>>() {
//		};
//		System.out.println(ref.getType());
//		
//	}
	
}
