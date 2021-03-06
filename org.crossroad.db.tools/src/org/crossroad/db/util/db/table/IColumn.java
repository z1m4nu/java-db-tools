package org.crossroad.db.util.db.table;

public interface IColumn {
	String getName();
	String getJDBCTypeName();
	int getJDBCType();
	int getPosition();
	boolean isNulleable();
	String getDescription();
	int getSize();
	int getScale();
}
