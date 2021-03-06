/**
 * 
 */
package org.crossroad.db.util.db.table.impl;

import java.util.ArrayList;
import java.util.List;

import org.crossroad.db.util.db.table.IBaseIndex;
import org.crossroad.db.util.db.table.IColumnIndex;

/**
 * @author e.soden
 *
 */
public abstract class AbstractIndex implements IBaseIndex {
	private List<IColumnIndex> columns = new ArrayList<IColumnIndex>();
	private String name = null;
	/**
	 * 
	 */
	public AbstractIndex() {
	}

	/* (non-Javadoc)
	 * @see org.crossroad.db.util.db.table.IBaseIndex#getColumns()
	 */
	@Override
	public List<IColumnIndex> getColumns() {
		return this.columns;
	}

	/* (non-Javadoc)
	 * @see org.crossroad.db.util.db.table.IBaseIndex#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}

	public void addColumn(IColumnIndex column)
	{
		this.columns.add(column);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
}
