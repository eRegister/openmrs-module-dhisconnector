package org.openmrs.module.dhisconnector;

import java.util.Date;
import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.openmrs.BaseOpenmrsObject;
import org.openmrs.User;

/**
 * Represents a general mapping between an OpenMRS report (contains indicators and dimensions)
 * against a DHIS2 dataset (contains dataelements and category combination options) as well as when
 * to run the mapping TODO add method to build dxf on service
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "dhisconnector_report_to_dataset")
public class ReportToDataSetMapping extends BaseOpenmrsObject {
	//TODO extend should fix copied stuff from BaseOpenmrsData when excluded, this works from platform version 1.11.6
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "creator")
	protected User creator;

	// @OneToMany(mappedBy="mapping")
	// private Set<MappingsRepush> mappingsrepush;

	@Column(name = "date_created", nullable = false)
	private Date dateCreated;

	@Column(name = "last_push_retry", nullable = true)
	private Date lastPushRetry;

	@Column(name = "retry_count", nullable = true)
	private Integer retryCount;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Column(name = "mapping", nullable = false)
	private String mapping;
	
	@Column(name = "last_run")
	private Date lastRun;
	
	public ReportToDataSetMapping() {
	}
	
	public ReportToDataSetMapping(String mapping) {
		setMapping(mapping);
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getMapping() {
		return mapping;
	}
	
	public void setMapping(String mapping) {
		this.mapping = mapping;
	}
	
	/**
	 * last datetime when the report was run and exported into DHIS2
	 */
	public Date getLastRun() {
		return lastRun;
	}
	
	public void setLastRun(Date lastRun) {
		this.lastRun = lastRun;
	}
	
	/**
	 * @see org.openmrs.Auditable#getCreator()
	 */
	public User getCreator() {
		return creator;
	}
	
	/**
	 * @see org.openmrs.Auditable#setCreator(org.openmrs.User)
	 */
	public void setCreator(User creator) {
		this.creator = creator;
	}


	/*public Set<MappingsRepush> getMappingsrepush() {
		return mappingsrepush;
	}*/

	/*public void setMappingsrepush(Set<MappingsRepush> mappingsrepush) {
		this.mappingsrepush = mappingsrepush;
	}*/
	
	/**
	 * @see org.openmrs.Auditable#getDateCreated()
	 */
	public Date getDateCreated() {
		return dateCreated;
	}

	public Date getLastPushRetry() {
		return lastPushRetry;
	}

	public void setLastPushRetry(Date lastPushRetry) {
		this.lastPushRetry = lastPushRetry;
	}

	public Integer getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(Integer retryCount) {
		this.retryCount = retryCount;
	}
	
	/**
	 * @see org.openmrs.Auditable#setDateCreated(java.util.Date)
	 */
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public enum ReportingPeriodType {
		Daily, Weekly, WeeklySunday, WeeklyWednesday, WeeklyThursday, WeeklySaturday, BiWeekly, Monthly, BiMonthly,
		Quarterly, SixMonthly, SixMonthlyApril, Yearly, FinancialApril, FinancialJuly, FinancialOct, FinancialNov
	}
}
