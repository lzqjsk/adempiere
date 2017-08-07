/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Copyright (C) 2003-2015 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpcya.com                                 *
 *****************************************************************************/
package org.spin.model;

import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.compiere.Adempiere;
import org.compiere.util.CLogger;


/**
 * Added for handle global translation
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/1000">
 * 		@see FR [ 1000 ] Add new feature for unique translation table</a>
 */
public class MADTranslation extends X_AD_Translation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8813618563395994813L;
	
	/**	Logger	*/
	private static CLogger	log	= CLogger.getCLogger (MADTranslation.class);

	public MADTranslation(Properties ctx, int AD_Translation_ID, String trxName) {
		super(ctx, AD_Translation_ID, trxName);
	}

	public MADTranslation(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * Validate if translation is supported
	 * @return
	 */
	public static boolean isSupported() {
		//	
		try {
			Date dbVersion = new SimpleDateFormat("yyyy-MM-dd").parse(Adempiere.DB_VERSION);
			Date validVersion = new SimpleDateFormat("yyyy-MM-dd").parse(DB_SUPPORTED_VERSION);
			//	Compare
			if(dbVersion != null
					&& validVersion != null) {
				return dbVersion.compareTo(validVersion) >= 0;
			}
		} catch (ParseException e) {
			log.severe("Not valid version " + e.getLocalizedMessage());
		}  
		//	
		return false;
	}
	
	/** Database Version as date    Compared with AD_System		*/
	private static String	DB_SUPPORTED_VERSION		= "2017-08-30";
}
