import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.*;

/**
 * Created by ying.qiu on 2017/5/15.
 */
@Root
public class Testcase {

    @Element(data=true)
    private String node_order;
    @Element(data=true)
    private String externalid;
    @Element(data=true)
    private String version;
    @Element(data=true)
    private String summary;
    @Element(data=true)
    private String preconditions;
    @Element(data=true)
    private String execution_type;
    @Element(data=true)
    private String importance;
    @Attribute
    private String name;

    public String getInternalid() {
        return internalid;
    }

    public void setInternalid(String internalid) {
        this.internalid = internalid;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    @Attribute
    private String internalid;
    @ElementList
    private List<Step> steps;

    public Testcase(String node_order, String externalid, String version, String summary, String preconditions, String execution_type, String importance, List<Step> steps) {
        this.node_order = node_order;
        this.externalid = externalid;
        this.version = version;
        this.summary = summary;
        this.preconditions =preconditions;
        this.execution_type = execution_type;
        this.importance = importance;
        this.steps = steps;
    }
    public Testcase() {
    }
    public String getPreconditions() {
        return preconditions;
    }

    public void setPreconditions(String preconditions) {
        this.preconditions = preconditions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNode_order() {
        return node_order;
    }

    public void setNode_order(String node_order) {
        this.node_order = node_order;
    }

    public String getExternalid() {
        return externalid;
    }

    public void setExternalid(String externalid) {
        this.externalid = externalid;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getExecution_type() {
        return execution_type;
    }

    public void setExecution_type(String execution_type) {
        this.execution_type = execution_type;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }





}
