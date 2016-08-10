/*****************************************************************************
 * Copyright (c) 2016 CEA LIST and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   CEA LIST - Initial API and implementation
 *   
 *****************************************************************************/

package org.eclipse.papyrus.designer.languages.java.reverse.classesundertest;


/**
 * A little comment in the class.
 * A second line in Javadoc
 * 
 * @author dumoulin
 *
 */
public class SimpleClass {

	protected int x, y;
	
	/**
	 * Constructor.
	 *
	 */
	public SimpleClass() {
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * Constructor.
	 *
	 * @param x
	 * @param y
	 */
	public SimpleClass(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}


	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

}
