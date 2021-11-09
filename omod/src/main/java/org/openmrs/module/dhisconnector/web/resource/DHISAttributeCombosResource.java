/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.dhisconnector.web.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.openmrs.api.context.Context;
import org.openmrs.module.dhisconnector.api.DHISConnectorService;
import org.openmrs.module.dhisconnector.api.model.DHISAttributeCombo;
import org.openmrs.module.dhisconnector.api.model.DHISAttributeOptionCombo;
import org.openmrs.module.dhisconnector.api.model.DHISCategoryCombo;
import org.openmrs.module.dhisconnector.api.model.DHISDataSet;
import org.openmrs.module.dhisconnector.web.controller.DHISConnectorRestController;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.api.Retrievable;
import org.openmrs.module.webservices.rest.web.resource.impl.DataDelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.resource.impl.NeedsPaging;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

@Resource(name = RestConstants.VERSION_1 + DHISConnectorRestController.DHISCONNECTOR_NAMESPACE
		+ "/dhisattributecombos", supportedClass = DHISCategoryCombo.class, supportedOpenmrsVersions = { "1.8.*",
		"1.9.*, 1.10.*, 1.11.*", "1.12.*", "2.*" })
public class DHISAttributeCombosResource extends DataDelegatingCrudResource implements Retrievable {

	public static final String CATEGORYCOMBOS_PATH = "/api/categoryCombos";

	private static final String AOC_FIELDS_PARAM = "?paging=false&fields=:identifiable,displayName,code,categoryOptionCombos[:identifiable,displayName,code],categories[:identifiable,displayName,code]&filter=dataDimensionType:eq:ATTRIBUTE";

	public static final String JSON_SUFFIX = ".json";

	@Override
	public DHISAttributeCombo getByUniqueId(String s) {
		ObjectMapper mapper = new ObjectMapper();

		String jsonResponse = Context.getService(DHISConnectorService.class)
				.getDataFromDHISEndpoint(CATEGORYCOMBOS_PATH + "/" + s + JSON_SUFFIX + AOC_FIELDS_PARAM);

		DHISAttributeCombo ret = null;

		try {
			mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			ret = mapper.readValue(jsonResponse, DHISAttributeCombo.class);
		}
		catch (Exception e) {
			log.error(e.getMessage());
		}

		return ret;
	}

	@Override
	protected void delete(Object o, String s, RequestContext requestContext) throws ResponseException {

	}

	@Override
	public Object newDelegate() {
		return null;
	}

	@Override
	public Object save(Object o) {
		return null;
	}

	@Override
	public void purge(Object o, RequestContext requestContext) throws ResponseException {

	}

	protected NeedsPaging<DHISAttributeCombo> doGetAll(RequestContext context) {
		List<DHISAttributeCombo> attributeCombos = new ArrayList<DHISAttributeCombo>();

		ObjectMapper mapper = new ObjectMapper();
		String jsonResponse = new String();
		JsonNode node;

		jsonResponse = Context.getService(DHISConnectorService.class)
				.getDataFromDHISEndpoint(CATEGORYCOMBOS_PATH + JSON_SUFFIX + AOC_FIELDS_PARAM);

		try {
			mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			if(StringUtils.isNotBlank(jsonResponse)) {
				node = mapper.readTree(jsonResponse);
				attributeCombos = Arrays.asList(mapper.readValue(node.get("categoryCombos").toString(), DHISAttributeCombo[].class));
			}
		}
		catch (Exception ex) {
			System.out.print(ex.getMessage());
		}

		return new NeedsPaging<DHISAttributeCombo>(attributeCombos, context);
	}

	@Override
	public DelegatingResourceDescription getRepresentationDescription(Representation representation) {
		DelegatingResourceDescription description = new DelegatingResourceDescription();
		description.addProperty("id");
		description.addProperty("name");
		description.addProperty("categoryOptionCombos");
		description.addProperty("categories");
		return description;
	}
}
