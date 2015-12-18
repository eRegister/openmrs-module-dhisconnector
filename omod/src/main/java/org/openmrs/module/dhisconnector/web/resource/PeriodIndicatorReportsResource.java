package org.openmrs.module.dhisconnector.web.resource;

import org.openmrs.api.context.Context;
import org.openmrs.module.reporting.dataset.definition.CohortIndicatorDataSetDefinition;
import org.openmrs.module.reporting.report.definition.PeriodIndicatorReportDefinition;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.openmrs.module.reporting.report.definition.service.ReportDefinitionService;
import org.openmrs.module.dhisconnector.web.controller.DHISConnectorRestController;
import org.openmrs.module.webservices.rest.SimpleObject;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.api.Retrievable;
import org.openmrs.module.webservices.rest.web.resource.impl.DataDelegatingCrudResource;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.resource.impl.NeedsPaging;
import org.openmrs.module.webservices.rest.web.response.ResponseException;

import java.util.ArrayList;
import java.util.List;

@Resource(name = RestConstants.VERSION_1 + DHISConnectorRestController.DHISCONNECTOR_NAMESPACE + "/periodindicatorreports", supportedClass = PeriodIndicatorReportDefinition.class, supportedOpenmrsVersions = {"1.8.*", "1.9.*, 1.10.*, 1.11.*", "1.12.*", "2.0.*"})
public class PeriodIndicatorReportsResource extends DataDelegatingCrudResource implements Retrievable {
  @Override
  public Object getByUniqueId(String s) {
    return Context.getService(ReportDefinitionService.class).getDefinitionByUuid(s);
  }

  protected NeedsPaging<PeriodIndicatorReportDefinition> doGetAll(RequestContext context) {

    List<ReportDefinition> all = Context.getService(ReportDefinitionService.class).getAllDefinitions(false);

    List<PeriodIndicatorReportDefinition> pireports = new ArrayList<PeriodIndicatorReportDefinition>();

    for(ReportDefinition r : all) {
      if(r instanceof PeriodIndicatorReportDefinition) {
        pireports.add((PeriodIndicatorReportDefinition)r);
      }
    }

    return new NeedsPaging<PeriodIndicatorReportDefinition>(pireports, context);
  }

//  protected NeedsPaging<PeriodIndicatorReportDefinition> doGetAll(RequestContext context) {
//    return new NeedsPaging<PeriodIndicatorReportDefinition>(Context.getService(ReportDefinitionService.class).getAllDefinitions(false), context);
//  }

  @Override
  protected void delete(Object o, String s, RequestContext requestContext) throws ResponseException {
    // not supporting delete
  }

  @Override
  public Object newDelegate() {
    return new PeriodIndicatorReportDefinition();
  }

  @Override
  public Object save(Object o) {
    return null; // not supporting save
  }

  @Override
  public void purge(Object o, RequestContext requestContext) throws ResponseException {
    // not supporting purge
  }

  public SimpleObject getSimpleColumns(PeriodIndicatorReportDefinition pird) {
    SimpleObject simpleColumns = new SimpleObject();




    List<CohortIndicatorDataSetDefinition.CohortIndicatorAndDimensionColumn> cols = ((CohortIndicatorDataSetDefinition)(pird.getDataSetDefinitions().get("defaultDataSet")).getParameterizable()).getColumns();

    for(CohortIndicatorDataSetDefinition.CohortIndicatorAndDimensionColumn cd : cols) {
      simpleColumns.add(cd.getName(), cd.getDimensionOptions());
    }

    return simpleColumns;
  }

  public SimpleObject getSchema(PeriodIndicatorReportDefinition pird) {
    SimpleObject ret = new SimpleObject();
    ret.add("columns", getSimpleColumns(pird));
    return ret;
  }

  @Override
  public DelegatingResourceDescription getRepresentationDescription(Representation representation) {
    DelegatingResourceDescription description = new DelegatingResourceDescription();
    description.addProperty("uuid");
    description.addProperty("name");
    description.addProperty("schema", findMethod("getSchema"));
    return description;
  }
}
