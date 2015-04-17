/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package ch.welld.voxxed;

import java.lang.reflect.Method;
import java.util.Map;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import ch.welld.voxxed.be.BEConnector;
import ch.welld.voxxed.be.ConnUtility;
import ch.welld.voxxed.be.VoxxedFacade;
import ch.welld.voxxed.data.Octopus;
import ch.welld.voxxed.data.Squid;
import ch.welld.voxxed.data.VoxxedOperator;
import ch.welld.voxxed.interceptor.BeConnection;
import ch.welld.voxxed.interceptor.BeFacade;
import ch.welld.voxxed.interceptor.LogMethod;

@Singleton
@Lock(LockType.READ)
public class ExampleDAO {

	private static final Logger logger = LogUtility.getLogger(ExampleDAO.class);

	@Inject
	private BeTaskExecutor beTaskExecutor;

	@Inject
	private BeMethodExecutor beMethodExecutor;

	@Inject
	private BeFunctionExecutor beFunctionExecutor = new BeFunctionExecutor();

	@Inject
	private BeConnectorBean beConnector;

	private LoginManager loginManager = LoginManager.getInstance();

	public Squid findSquids(String token, Map<String, Object> criteria) {
		BEConnector connector = null;
		Squid result = null;
		// 1 Get the operator
		VoxxedOperator operator = loginManager.getOperator(token);
		if (operator == null) {
			logger.warn("invalid operator token " + token);
			return null;
		}
		try {
			// 2 estabilish connection and get remote service facade
			connector = ConnUtility.getBEConnector();
			VoxxedFacade service = ConnUtility.getVoxxedFacade(connector);
			// 3 The real action!
			result = service.findSquidByCriteria(operator, criteria);

		} catch (Exception e) {
			// 4 log and handle exception
			logger.error("Error in retrieving objectType1 for user" + token, e);
			result = null;
		} finally {
			// 5 release the connection
			ConnUtility.releaseBEConnector(connector);
		}
		// 6 audit the action (log)
		logger.info("findSquids executed with params " + criteria
				+ " and result " + result);
		return result;
	}

	public Octopus findOctopus(String token, Map<String, Object> criteria) {
		BEConnector connector = null;
		Octopus result = null;
		// 1 Get the operator
		VoxxedOperator operator = loginManager.getOperator(token);
		if (operator == null) {
			logger.warn("invalid operator token " + token);
			return null;
		}
		try {
			// 2 estabilish connection and get remote service facade
			connector = ConnUtility.getBEConnector();
			VoxxedFacade service = ConnUtility.getVoxxedFacade(connector);
			// 3 The real action!
			result = service.findOctopusByCriteria(operator, criteria);

		} catch (Exception e) {
			// 4 log and handle exception
			logger.error("Error in retrieving objectType1 for user" + token, e);
			result = null;
		} finally {
			// 5 release the connection
			ConnUtility.releaseBEConnector(connector);
		}
		// 6 audit the action (log)
		logger.info("findSquids executed with params " + criteria
				+ " and result " + result);
		return result;
	}

	public Octopus findOctopusbyId(String token, long id) {
		BEConnector connector = null;
		Octopus result = null;
		// 1 Get the operator
		VoxxedOperator operator = loginManager.getOperator(token);
		if (operator == null) {
			logger.warn("invalid operator token " + token);
			return null;
		}
		try {
			// 2 estabilish connection and get remote service facade
			connector = ConnUtility.getBEConnector();
			VoxxedFacade service = ConnUtility.getVoxxedFacade(connector);
			// 3 The real action!
			result = service.findOctopusById(operator, id);

		} catch (Exception e) {
			// 4 log and handle exception
			logger.error("Error in retrieving objectType1 for user" + token, e);
			result = null;
		} finally {
			// 5 release the connection
			ConnUtility.releaseBEConnector(connector);
		}
		// 6 audit the action (log)
		logger.info("findOctopusbyId executed with params " + id
				+ " and result " + result);
		return result;
	}

	public Squid getSquidsWithTask(String token,
			final Map<String, Object> criteria) {

		Squid result = beTaskExecutor.executeBeCall(new BeTask<Squid>(token) {

			@Override
			public Squid call() throws Exception {
				return this.getService().findSquidByCriteria(
						this.getOperator(), criteria);
			}

		});

		// 6 audit the action (log)
		logger.info("getSquidsWithTask executed with params " + criteria
				+ " and result " + result);
		return result;
	}

	@LogMethod
	public Squid getSquidsWithTaskAndLog(String token,
			final Map<String, Object> criteria) {

		return beTaskExecutor.executeBeCall(new BeTask<Squid>(token) {

			@Override
			public Squid call() throws Exception {
				return this.getService().findSquidByCriteria(
						this.getOperator(), criteria);
			}

		});
	}

	public Squid getSquidsWithFunction(String token,
			final Map<String, Object> criteria) {

		Squid result = beFunctionExecutor
				.executeFunction(new BeFunction<Squid>(token) {

					@Override
					public Squid execute(VoxxedOperator operator,
							VoxxedFacade service) {
						return service.findSquidByCriteria(operator, criteria);
					}
				});

		// 6 audit the action (log)
		logger.info("getSquidWithFunction executed with params " + criteria
				+ " and result " + result);
		return result;

	}

	@LogMethod
	public Squid getSquidsWithFunctionAndLog(String token,
			final Map<String, Object> criteria) {

		return beFunctionExecutor.executeFunction(new BeFunction<Squid>(token) {

			@Override
			public Squid execute(VoxxedOperator operator, VoxxedFacade service) {
				return service.findSquidByCriteria(operator, criteria);
			}
		});

	}

	public Squid getSquidsWithMethod(String token,
			final Map<String, Object> criteria) {
		Method method = null;
		try {
			method = VoxxedFacade.class.getMethod("findSquidByCriteria",
					new Class<?>[] { criteria.getClass() });
		} catch (NoSuchMethodException | SecurityException e) {
			logger.warn("Could not create method findSquidByCriteria", e);
			return null;
		}

		BeMethodCall call = new BeMethodCall(method, token,
				new Object[] { criteria });

		Object maybeSquid = beMethodExecutor.executeMethod(call);

		if (maybeSquid instanceof Squid) {
			return (Squid) maybeSquid;
		} else {
			logger.warn("Unexpected return object " + maybeSquid);
			return null;
		}

	}

	@LogMethod
	@BeConnection
	public Squid findSquids(@BeFacade VoxxedFacade service, String token,
			Map<String, Object> criteria) {
		Squid result = null;
		// 1 Get the operator
		VoxxedOperator operator = loginManager.getOperator(token);
		if (operator == null) {
			logger.warn("invalid operator token " + token);
			return null;
		}

		// 3 The real action!
		result = service.findSquidByCriteria(operator, criteria);

		// Exception and Connection close handling in interceptor.

		// Log handled in interceptor.
		return result;
	}

	@LogMethod
	@BeConnection
	public Squid findSquidsExcept(VoxxedFacade service, String token,
			Map<String, Object> criteria) {
		Squid result = null;
		// 1 Get the operator (exception handled in interceptor)
		VoxxedOperator operator = loginManager.getOperator(token);

		// 3 The real action!
		result = service.findSquidByCriteria(operator, criteria);

		// Exception and Connection close handling in interceptor.

		// Log handled in interceptor.
		return result;
	}

	@LogMethod
	public Squid findSquidsInjection(String token, Map<String, Object> criteria) {

		Squid result = null;
		// 1 Get the operator (exception handled in interceptor)
		VoxxedOperator operator = loginManager.getOperator(token);
		if (operator == null) {
			logger.warn("invalid operator token " + token);
			return null;
		}
		try {
			result = beConnector.getService().findSquidByCriteria(operator,
					criteria);

		} catch (Exception e) {
			logger.error("Error in retrieving objectType1 for user" + token, e);
			return null;
		}
		// Connection close handling in interceptor.

		// Log handled in interceptor.
		return result;
	}

}
