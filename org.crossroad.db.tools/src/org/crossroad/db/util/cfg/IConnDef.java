package org.crossroad.db.util.cfg;

public interface IConnDef {

	/**
	 * @return the driverName
	 */
	String getDriverId();

	/**
	 * @return the databaseName
	 */
	String getDatabaseName();

	/**
	 * @return the schemaName
	 */
	String getSchemaName();

	/**
	 * @return the serverName
	 */
	String getServerName();

	/**
	 * @return the serverPort
	 */
	int getServerPort();

	/**
	 * @return the userName
	 */
	String getUserName();

	/**
	 * @return the password
	 */
	String getPassword();
	
	String getId();
	

}