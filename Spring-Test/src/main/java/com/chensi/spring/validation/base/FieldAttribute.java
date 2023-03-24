package com.chensi.spring.validation.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * @author  chensi
 * @date  2023/3/23
 */
public class FieldAttribute implements Serializable {

	/**
	 * 字段名
	 */
	private String _fieldname = "";
	/**
	 * 字段别名
	 */
	private String _alias;
	/**
	 * 字段前缀
	 */
	private String _prefix = "";
	/**
	 * 字段类型
	 */
	private FieldType _type;
	/**
	 * 字段值
	 */
	private Object _value;
	/**
	 * 字段值
	 */
	private Object _value2;
	/**
	 * 值已改变
	 */
	private boolean _ischanged = false;
	/**
	 * 是否为排序字段，0 未设定，1 ASC，2 DESC
	 * 其中高6位为排序优先级，0最高，数值越大优先级越低
	 */
	private byte _Orderby = 0;
	/**
	 * 是否为查询返回字段
	 */
	private boolean _selectfield = false;
	/**
	 * 是否分组字段
	 */
	private boolean _groupfield = false;
	/**
	 * 连接查询的目标信息
	 */
	private Object _jionto = null;
	/**
	 * 只读字段
	 */
	private boolean _readonly = false;
	/**
	 * 设置判断条件
	 * Equals = 0, Greater = 1, Smaller = 2, NoEquals= 3, Between= 4, Like= 5, GreaterEquals = 6, SmallerEquals = 7
	 */
	private Relational _RelationalOptor = Relational.Equals;
	//private Relational _RelationalOptor = Relational.Like;


	public FieldAttribute() {
	}

	public FieldAttribute(String FieldName, Object value, FieldType type) {
		_fieldname = FieldName;
		_value = value;
		_ischanged = true;
		_type = type;
	}

	public FieldAttribute(String FieldName, FieldType type) {
		_fieldname = FieldName;
		_value = null;
		_type = type;
	}

	public FieldAttribute(String FieldName) {
		_fieldname = FieldName;
		_value = null;
		_type = FieldType.String;
	}

	public FieldAttribute(final FieldAttribute fd) {
		if (fd == null) {
			return;
		}
		_fieldname = fd.getFieldName();
		_value = fd.getValue();
		_value2 = fd.getValue2();
		_alias = fd.getAlias();
		_prefix = fd.getPrefix();
		_type = fd.getType();
		_RelationalOptor = fd.getRelationalOptor();
		_ischanged = fd.getIsChanged();
		_Orderby = fd.getOrderby();
		_selectfield = fd.getIsSelectField();
		_groupfield = fd.getGroupField();
		_jionto = fd.getJionTo();
		_readonly = fd.getReadOnly();
	}

	@Override
	public String toString() {
		if (_value == null) {
			return "";
		} else {
			return _value.toString();
		}
	}

	//add by cs at 2021/12/01
	public String toOrderString() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("_fieldname", this.getFieldName());
		jsonObject.put("_ischanged", this.getIsChanged());
		jsonObject.put("_Orderby", this.getOrderby());
		return '[' + jsonObject.toString() + ']';
	}

	//add by cs at 2022/10/27
	public static List<FieldAttribute> getFieldAttrList(String s) {
		if (StringUtils.isEmpty(s)) {
			return null;
		}
		List<FieldAttribute> fieldList = new ArrayList<>();
		JSONObject json = JSONObject.parseObject(s);
		for (Map.Entry<String, Object> entry : json.entrySet()) {
			String fieldname = entry.getKey();
			Object value = entry.getValue();
			FieldType type = FieldType.String;
			//get type
			if (value instanceof Number) {
				if (value instanceof Double) {
					type = FieldType.Double;
				}
				if (value instanceof Float) {
					type = FieldType.Float;
				}
			} else {
				type = FieldType.String;
			}
			FieldAttribute fieldAttribute = new FieldAttribute(fieldname, value, type);
			fieldList.add(fieldAttribute);
		}
		return fieldList;
	}


	// 2021.03.30 haochongqing Delete
//    @Override
//    public boolean equals(Object obj) {
//        if (_fieldname != null && _value != null && obj != null) {
//            FieldAttribute fieldObj = (FieldAttribute) obj;
//            return _value.equals(fieldObj.getValue()) && _fieldname.equals(fieldObj.getFieldName());
//        } else {
//            return false;
//        }
//    }

	public String getFieldName() {
		return _fieldname;
	}

	public void setFieldName(String value) {
		_fieldname = value;
	}

	public String getAlias() {
		return _alias;
	}

	public void setAlias(String value) {
		_alias = value;
	}

	public String getPrefix() {
		return _prefix;
	}

	public void setPrefix(String value) {
		_prefix = value;
	}

	public FieldType getType() {
		return _type;
	}

	public void setType(FieldType value) {
		_type = value;
	}

	public Object getValue() {
		return _value;
	}

	public void setValue(Object value) {
		if (value == null) {
			return;
		}
		if (!FormatValue(value, value)) {
			return;
		}
		if (_value == null || _value.toString() != value.toString()) {
			_ischanged = true;
		}
		_value = value;
	}

	public Object getValue2() {
		return _value2;
	}

	public void setValue2(Object value) {
		if (value == null) {
			return;
		}
		if (!FormatValue(value, value)) {
			return;
		}
		_value2 = value;
	}

	private boolean FormatValue(Object eleft, Object eright) {
		try {
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean getIsChanged() {
		return _ischanged;
	}

	public byte getOrderby() {
		return _Orderby;
	}

	public void setOrderby(byte value) {
		_Orderby = value;
	}

	public boolean getIsSelectField() {
		return _selectfield;
	}

	public void setIsSelectField(boolean value) {
		_selectfield = value;
	}

	public boolean getGroupField() {
		return _groupfield;
	}

	public void setGroupField(boolean value) {
		_groupfield = value;
	}

	public Object getJionTo() {
		return _jionto;
	}

	public void setJionTo(Object value) {
		_jionto = value;
	}

	public boolean getReadOnly() {
		return _readonly;
	}

	public void setReadOnly(boolean value) {
		_readonly = value;
	}

	public Relational getRelationalOptor() {
		return _RelationalOptor;
	}

	public void setRelationalOptor(Relational value) {
		_RelationalOptor = value;
	}

	public String ToString() {
		return JSON.toJSONString(this);
	}

	public static FieldAttribute Parse(String jsonVal) {
		return JSON.parseObject(jsonVal, FieldAttribute.class);
	}
}
