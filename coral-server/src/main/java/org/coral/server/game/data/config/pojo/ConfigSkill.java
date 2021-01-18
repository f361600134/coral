package org.coral.server.game.data.config.pojo;

import java.util.List;

public class ConfigSkill {

	private int ID;//技能id
	private String heroname;//武将名称
	private String name;//技能名称
	private String desc;//技能描述
	private int skillType;//技能类型
	private int camp;//阵营
	private List<Integer> targetSelect;//目标类型
	private int randNum;//随机次数
	private int targetNum;//目标数量
	private float damRate;//技能伤害系数
	private float careRate;//治疗系数
	private int effectCount;//生效次数
	private float effectCd;//生效间隔时间
	private float actionTime;//目标行动时间
	private float vibrationTime;//震屏时间
	private List<Integer> passiveSkills;//被动技能
	private String skillTypeName;//主公技能名
	private int UILabel;//主公技能底板
	private String icon;//技能图标
	private int animationId;//技能动画
	private String playtype;//施法音效ID
	private int mainType;//不可重复上阵

	public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    
	public String getHeroname(){
        return heroname;
    }
    public void setHeroname(String heroname){
        this.heroname = heroname;
    }
    
	public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    
	public String getDesc(){
        return desc;
    }
    public void setDesc(String desc){
        this.desc = desc;
    }
    
	public int getSkillType(){
        return skillType;
    }
    public void setSkillType(int skillType){
        this.skillType = skillType;
    }
    
	public int getCamp(){
        return camp;
    }
    public void setCamp(int camp){
        this.camp = camp;
    }
    
	public List<Integer> getTargetSelect(){
        return targetSelect;
    }
    public void setTargetSelect(List<Integer> targetSelect){
        this.targetSelect = targetSelect;
    }
    
	public int getRandNum(){
        return randNum;
    }
    public void setRandNum(int randNum){
        this.randNum = randNum;
    }
    
	public int getTargetNum(){
        return targetNum;
    }
    public void setTargetNum(int targetNum){
        this.targetNum = targetNum;
    }
    
	public float getDamRate(){
        return damRate;
    }
    public void setDamRate(float damRate){
        this.damRate = damRate;
    }
    
	public float getCareRate(){
        return careRate;
    }
    public void setCareRate(float careRate){
        this.careRate = careRate;
    }
    
	public int getEffectCount(){
        return effectCount;
    }
    public void setEffectCount(int effectCount){
        this.effectCount = effectCount;
    }
    
	public float getEffectCd(){
        return effectCd;
    }
    public void setEffectCd(float effectCd){
        this.effectCd = effectCd;
    }
    
	public float getActionTime(){
        return actionTime;
    }
    public void setActionTime(float actionTime){
        this.actionTime = actionTime;
    }
    
	public float getVibrationTime(){
        return vibrationTime;
    }
    public void setVibrationTime(float vibrationTime){
        this.vibrationTime = vibrationTime;
    }
    
	public List<Integer> getPassiveSkills(){
        return passiveSkills;
    }
    public void setPassiveSkills(List<Integer> passiveSkills){
        this.passiveSkills = passiveSkills;
    }
    
	public String getSkillTypeName(){
        return skillTypeName;
    }
    public void setSkillTypeName(String skillTypeName){
        this.skillTypeName = skillTypeName;
    }
    
	public int getUILabel(){
        return UILabel;
    }
    public void setUILabel(int UILabel){
        this.UILabel = UILabel;
    }
    
	public String getIcon(){
        return icon;
    }
    public void setIcon(String icon){
        this.icon = icon;
    }
    
	public int getAnimationId(){
        return animationId;
    }
    public void setAnimationId(int animationId){
        this.animationId = animationId;
    }
    
	public String getPlaytype(){
        return playtype;
    }
    public void setPlaytype(String playtype){
        this.playtype = playtype;
    }
    
	public int getMainType(){
        return mainType;
    }
    public void setMainType(int mainType){
        this.mainType = mainType;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
		
		
		this.parseExt();
    }
	
	
	/////////UserDefine Begin///////////
	private void parseExt(){
	}
	
	/////////UserDefine End/////////////
	
}
