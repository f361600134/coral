package org.coral.server.game.data.config.pojo;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

public class ConfigBuffer {

	private int ID;//bufferId
	private String skillEffect;//技能特效
	private int tips;//战斗飘字
	private String desc;//BUFF描述
	private int godActiveCD;//神技激活
	private int godTriggerCD;//频率
	private int severClient;//服务器用
	private int triggerCondition;//触发条件
	private int functionType;//功能类型
	private int triggerRate;//触发概率%
	private int camp;//阵营
	private List<Integer> targetSelect;//目标选取
	private int targetNum;//目标数量
	private float duration;//持续时间
	private int jumpCd;//跳动间隔
	private int triggerNum;//可触发次数
	private int overlayNum;//可叠加层数
	private int propertyEffectType;//增减属性
	private List<List<Integer>> attValues;//自身属性
	private int effectType;//属性生效类型
	private int deBuff;//负面效果
	private float damScale;//直接伤害系数%
	private List<List<Integer>> extraDam;//属性伤害
	private List<List<Integer>> conditionSelect;//目标二次筛选
	private List<Integer> triggerValues;//自身血量触发
	private List<Integer> composeHeros;//触发武将
	private float addBuffer;//关联BUFFid
	private String icon;//技能图标

	public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    
	public String getSkillEffect(){
        return skillEffect;
    }
    public void setSkillEffect(String skillEffect){
        this.skillEffect = skillEffect;
    }
    
	public int getTips(){
        return tips;
    }
    public void setTips(int tips){
        this.tips = tips;
    }
    
	public String getDesc(){
        return desc;
    }
    public void setDesc(String desc){
        this.desc = desc;
    }
    
	public int getGodActiveCD(){
        return godActiveCD;
    }
    public void setGodActiveCD(int godActiveCD){
        this.godActiveCD = godActiveCD;
    }
    
	public int getGodTriggerCD(){
        return godTriggerCD;
    }
    public void setGodTriggerCD(int godTriggerCD){
        this.godTriggerCD = godTriggerCD;
    }
    
	public int getSeverClient(){
        return severClient;
    }
    public void setSeverClient(int severClient){
        this.severClient = severClient;
    }
    
	public int getTriggerCondition(){
        return triggerCondition;
    }
    public void setTriggerCondition(int triggerCondition){
        this.triggerCondition = triggerCondition;
    }
    
	public int getFunctionType(){
        return functionType;
    }
    public void setFunctionType(int functionType){
        this.functionType = functionType;
    }
    
	public int getTriggerRate(){
        return triggerRate;
    }
    public void setTriggerRate(int triggerRate){
        this.triggerRate = triggerRate;
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
    
	public int getTargetNum(){
        return targetNum;
    }
    public void setTargetNum(int targetNum){
        this.targetNum = targetNum;
    }
    
	public float getDuration(){
        return duration;
    }
    public void setDuration(float duration){
        this.duration = duration;
    }
    
	public int getJumpCd(){
        return jumpCd;
    }
    public void setJumpCd(int jumpCd){
        this.jumpCd = jumpCd;
    }
    
	public int getTriggerNum(){
        return triggerNum;
    }
    public void setTriggerNum(int triggerNum){
        this.triggerNum = triggerNum;
    }
    
	public int getOverlayNum(){
        return overlayNum;
    }
    public void setOverlayNum(int overlayNum){
        this.overlayNum = overlayNum;
    }
    
	public int getPropertyEffectType(){
        return propertyEffectType;
    }
    public void setPropertyEffectType(int propertyEffectType){
        this.propertyEffectType = propertyEffectType;
    }
    
	public List<List<Integer>> getAttValues(){
        return attValues;
    }
    public void setAttValues(List<List<Integer>> attValues){
        this.attValues = attValues;
    }
    
	public int getEffectType(){
        return effectType;
    }
    public void setEffectType(int effectType){
        this.effectType = effectType;
    }
    
	public int getDeBuff(){
        return deBuff;
    }
    public void setDeBuff(int deBuff){
        this.deBuff = deBuff;
    }
    
	public float getDamScale(){
        return damScale;
    }
    public void setDamScale(float damScale){
        this.damScale = damScale;
    }
    
	public List<List<Integer>> getExtraDam(){
        return extraDam;
    }
    public void setExtraDam(List<List<Integer>> extraDam){
        this.extraDam = extraDam;
    }
    
	public List<List<Integer>> getConditionSelect(){
        return conditionSelect;
    }
    public void setConditionSelect(List<List<Integer>> conditionSelect){
        this.conditionSelect = conditionSelect;
    }
    
	public List<Integer> getTriggerValues(){
        return triggerValues;
    }
    public void setTriggerValues(List<Integer> triggerValues){
        this.triggerValues = triggerValues;
    }
    
	public List<Integer> getComposeHeros(){
        return composeHeros;
    }
    public void setComposeHeros(List<Integer> composeHeros){
        this.composeHeros = composeHeros;
    }
    
	public float getAddBuffer(){
        return addBuffer;
    }
    public void setAddBuffer(float addBuffer){
        this.addBuffer = addBuffer;
    }
    
	public String getIcon(){
        return icon;
    }
    public void setIcon(String icon){
        this.icon = icon;
    }
    

	////////////////////// 特殊扩展 //////////////
	
	public void parse(){
		
		Map<Integer, Integer> attValuesTemps = Maps.newHashMap();
    	for(List<Integer> item : this.attValues)
    	{
    		int c = attValuesTemps.getOrDefault(item.get(0), 0);
    		c += item.get(1);
    		attValuesTemps.put(item.get(0), c);
    	}
    	attValuesMap = attValuesTemps;
			    
		Map<Integer, Integer> extraDamTemps = Maps.newHashMap();
    	for(List<Integer> item : this.extraDam)
    	{
    		int c = extraDamTemps.getOrDefault(item.get(0), 0);
    		c += item.get(1);
    		extraDamTemps.put(item.get(0), c);
    	}
    	extraDamMap = extraDamTemps;
			    
		Map<Integer, Integer> conditionSelectTemps = Maps.newHashMap();
    	for(List<Integer> item : this.conditionSelect)
    	{
    		int c = conditionSelectTemps.getOrDefault(item.get(0), 0);
    		c += item.get(1);
    		conditionSelectTemps.put(item.get(0), c);
    	}
    	conditionSelectMap = conditionSelectTemps;
			    
		
		
		this.parseExt();
    }
	
	//id_count ID数量
    private Map<Integer, Integer> attValuesMap = Maps.newHashMap();
    public Map<Integer, Integer> getAttValuesMap(){
    	return attValuesMap;
    }
    
    private Map<Integer, Integer> extraDamMap = Maps.newHashMap();
    public Map<Integer, Integer> getExtraDamMap(){
    	return extraDamMap;
    }
    
    private Map<Integer, Integer> conditionSelectMap = Maps.newHashMap();
    public Map<Integer, Integer> getConditionSelectMap(){
    	return conditionSelectMap;
    }
    
	
	/////////UserDefine Begin///////////
	private void parseExt(){
	}
	
	/////////UserDefine End/////////////
	
}
