/***************************************************************
 /"A service by which a client may conduct asynchronous dialogues 
 (message interchanges) with one or more other services. This 
 service is useful when many collaborating services are required 
 to satisfy a client request, and/or when significant delays are 
 involved is satisfying the request. This service was defined 
 under OWS 1.2 in support of SPS operations. WNS has broad 
 applicability in many such multi-service applications.
 
 Copyright (C) 2007 by 52�North Initiative for Geospatial 
 Open Source Software GmbH

 Author: Dennis Dahlmann, University of Muenster

 Contact: Andreas Wytzisk, 52�North Initiative for Geospatial 
 Open Source Software GmbH,  Martin-Luther-King-Weg 24,
 48155 Muenster, Germany, info@52north.org

 This program is free software; you can redistribute and/or  
 modify it under the terms of the GNU General Public License 
 version 2 as published by the Free Software Foundation.

 This program is distributed in the hope that it will be useful,  
 but WITHOUT ANY WARRANTY; without even the implied warranty of  
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the  
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License 
 along with this program (see gnu-gpl v2.txt); if not, write to  the 
 Free Software Foundation, Inc., 59 Temple Place - Suite 330,  
 Boston, MA 02111-1307, USA or visit the Free Software Foundation's  
 web page, http://www.fsf.org.

 Created on: 2006-07-28

 //Last changes on: 2007-03-15
 //Last changes by: Dennis Dahlmann

 ***************************************************************/

package org.n52.wns.communication;

import org.apache.log4j.Logger;
import org.n52.wns.WNSInitParamContainer;
import org.n52.wns.WNSServiceException;

/**
 * Factory class to load PhoneHandler
 * 
 * @author Dennis Dahlmann
 * @version 2.0
 */
public class PhoneHandlerFactory {

	private static Logger log = Logger.getLogger(PhoneHandlerFactory.class
			.getName());

	/**
	 * creates a new SMHandler depending on the configFile
	 * 
	 * @throws WNSServiceException
	 */
	public static PhoneHandler getInstance(
			org.x52North.wns.WNSConfigDocument.WNSConfig.RegisteredHandlers.PhoneHandler phoneHandler,
			String wns) throws WNSServiceException {
		PhoneHandler phone = null;

		try {
			phone = (PhoneHandler) Class.forName(phoneHandler.getProvider())
					.newInstance();
			phone.setProperties(phoneHandler, wns);
		} catch (Exception e) {
			log.fatal("Error during getInstance request: " + e.toString());
			WNSServiceException s = new WNSServiceException(
					WNSInitParamContainer.getInstance().getExceptionLevel());
			s.addCodedException(WNSServiceException.ExceptionCode.NoApplicable,
					null, "Error during getInstance request: " + e.toString());
			throw s;
		}

		return phone;
	}
}