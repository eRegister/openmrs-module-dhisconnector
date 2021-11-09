package org.openmrs.module.dhisconnector;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.openmrs.BaseOpenmrsObject;
import org.openmrs.Location;
import org.openmrs.User;

/**
 * Represents a mapping automation retry between an OpenMRS and DHIS2
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "dhisconnector_mappings_repush")
public class MappingsRepush extends BaseOpenmrsObject {

    @ManyToOne(optional = false)
    @JoinColumn(name = "creator")
    protected User creator;

    // @ManyToOne(optional = false)
    // @JoinColumn(name = "dhisconnector_mapping_id", referencedColumnName = "id", nullable = false)
    // protected ReportToDataSetMapping mapping;

    @Column(name = "date_created", nullable = false)
    private Date dateCreated;

    @Column(name = "date_repushed", nullable = false)
    private Date dateRepushed;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    public MappingsRepush() {
    }

    /*public MappingsRepush(ReportToDataSetMapping mapping) {
        setMapping(mapping);
    }*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @see org.openmrs.Auditable#getMapping()
     */
    /*public ReportToDataSetMapping getMapping() {
        return mapping;
    }*/

    /**
     * @see org.openmrs.Auditable#setMapping(ReportToDataSetMapping)
     */
    /*public void setMapping(ReportToDataSetMapping mapping) {
        this.mapping = mapping;
    }*/

    /**
     * @see org.openmrs.Auditable#getCreator()
     */
    public User getCreator() {
        return creator;
    }

    /**
     * @see org.openmrs.Auditable#setCreator(User)
     */
    public void setCreator(User creator) {
        this.creator = creator;
    }

    /**
     * @see org.openmrs.Auditable#getDateCreated()
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * @see org.openmrs.Auditable#setDateCreated(Date)
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * @see org.openmrs.Auditable#getDateRepushed()
     */
    public Date getDateRepushed() {
        return dateRepushed;
    }

    /**
     * @see org.openmrs.Auditable#setDateRepushed(Date)
     */
    public void setDateRepushed(Date dateRepushed) {
        this.dateRepushed = dateRepushed;
    }
}
