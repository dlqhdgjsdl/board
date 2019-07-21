package com.spring.board.vo;

public class ComCodeVo {
	private String codeType;
	private String codeId;
	private String codeName;
	private String creator;
	private String createTime;
	private String	modifier;
	private String modifierTime;
	
	
	public String getCodeType() {
		return codeType;
	}
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
	public String getCodeId() {
		return codeId;
	}
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public String getModifierTime() {
		return modifierTime;
	}
	public void setModifierTime(String modifierTime) {
		this.modifierTime = modifierTime;
	}
	public ComCodeVo(String codeType, String codeId, String codeName, String creator, String createTime,
			String modifier, String modifierTime) {
		super();
		this.codeType = codeType;
		this.codeId = codeId;
		this.codeName = codeName;
		this.creator = creator;
		this.createTime = createTime;
		this.modifier = modifier;
		this.modifierTime = modifierTime;
	}
	public ComCodeVo() {
		super();
	}
	@Override
	public String toString() {
		return "ComCodeVo [codeType=" + codeType + ", codeId=" + codeId + ", codeName=" + codeName + ", creator="
				+ creator + ", createTime=" + createTime + ", modifier=" + modifier + ", modifierTime=" + modifierTime
				+ "]";
	}
	
}