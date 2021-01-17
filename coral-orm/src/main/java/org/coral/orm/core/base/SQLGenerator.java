package org.coral.orm.core.base;

/**
 * sql生成器
 */
public class SQLGenerator {
	
	/**
	 * 查询所有
	 * @param tbName
	 * @return
	 */
	public static String selectAll(String tbName){
		return "SELECT * FROM ".concat(tbName).concat(";");
	}

	/**
	 * 根据主键, 索引查询.
	 * @param tbName
	 * @param idNames
	 * @return
	 */
	public static String select(String tbName, String ...idNames){
		StringBuilder sb = new StringBuilder("SELECT * FROM `").append(tbName).append("` WHERE ");
		sb.append(idNames[0]).append("=?");
		for (int i = 1; i < idNames.length; i++) {
			sb.append(" and ").append(idNames[i]).append("=?");
		}
		return sb.toString();
	}
	
	public static String deleteAll(String tbName){
		return "DELETE FROM ".concat(tbName).concat(";");
	}

	public static String delete(String tbName, String[] idNames){
		StringBuilder sb = new StringBuilder("DELETE FROM `").append(tbName).append("` WHERE ");
		sb.append(idNames[0]).append("=?");
		for (int i = 1; i < idNames.length; i++) {
			sb.append(" and ").append(idNames[i]).append("=?");
		}
		return sb.toString();
	}
	

	public static String update(String tbName, String[] colNames, String[] idNames){
		StringBuilder sb = new StringBuilder("UPDATE `").append(tbName).append("` SET ");
		sb.append(colNames[0]).append("=?");
		for (int i = 1; i < colNames.length; i++) {
			sb.append(",").append(colNames[i]).append("=?");
		}
		sb.append(" WHERE ");
		sb.append(idNames[0]).append("=?");
		for (int i = 1; i < idNames.length; i++) {
			sb.append(" and ").append(idNames[i]).append("=?");
		}
		return sb.toString();
	}

	public static String insert(String tbName, String[] colNames){
		StringBuilder sb = new StringBuilder("INSERT INTO `").append(tbName).append("`");
		StringBuilder cols = new StringBuilder();
		cols.append(colNames[0]);
		for (int i = 1; i < colNames.length; i++) {
			cols.append(",").append(colNames[i]);
		}
		sb.append(" (").append(cols).append(") VALUES (?");
		for (int i = 1; i < colNames.length; i++) {
			sb.append(",?");
		}
		sb.append(")");
		return sb.toString();
	}
	
	
	public static String replace(String tbName, String[] colNames){
		StringBuilder sb = new StringBuilder("REPLACE INTO `").append(tbName).append("`");
		StringBuilder cols = new StringBuilder();
		cols.append(colNames[0]);
		for (int i = 1; i < colNames.length; i++) {
			cols.append(",").append(colNames[i]);
		}
		sb.append(" (").append(cols).append(") VALUES (?");
		for (int i = 1; i < colNames.length; i++) {
			sb.append(",?");
		}
		sb.append(")");
		return sb.toString();
	}
	
}
