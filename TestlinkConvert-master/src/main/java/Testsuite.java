import org.simpleframework.xml.*;

import java.util.List;

/**
 * Created by ying.qiu on 2017/5/15.
 */
@Root
public class Testsuite {
    @Element(data = true)
    private String node_order;

    @Element(data = true)
    private String details;

    @Attribute
    private String name;

    public List<Testcase> getCaseList() {
        return caseList;
    }

    public void setCaseList(List<Testcase> caseList) {
        this.caseList = caseList;
    }

    @ElementList(inline = true)
    private List<Testcase> caseList;

    public Testsuite(String node_order, String details, String name, List<Testcase> caseList) {
        this.node_order = node_order;
        this.details = details;
        this.name = name;
        this.caseList = caseList;
    }

    public Testsuite() {
        this.node_order = node_order;
        this.details = details;
        this.name = name;
        this.caseList = caseList;
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

}
