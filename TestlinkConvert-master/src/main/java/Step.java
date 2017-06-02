import org.simpleframework.xml.*;

/**
 * Created by ying.qiu on 2017/5/15.
 */
@Root
public class Step {
    @Element(data=true)
    private String step_number;

    @Element(data=true)
    private String actions;

    @Element(data=true)
    private String expectedresults;

    @Element(data=true)
    private String execution_type;

    public Step(String step_number, String actions, String expectedresults, String execution_type) {
        this.step_number = step_number;
        this.actions = actions;
        this.expectedresults = expectedresults;
        this.execution_type = execution_type;
    }
    public Step() {
        this.step_number = step_number;
        this.actions = actions;
        this.expectedresults = expectedresults;
        this.execution_type = execution_type;
    }

    public String getStep_number() {
        return step_number;
    }

    public void setStep_number(String step_number) {
        this.step_number = step_number;
    }

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }

    public String getExpectedresults() {
        return expectedresults;
    }

    public void setExpectedresults(String expectedresults) {
        this.expectedresults = expectedresults;
    }

    public String getExecution_type() {
        return execution_type;
    }

    public void setExecution_type(String execution_type) {
        this.execution_type = execution_type;
    }

   /* public Element getStepRxml()
    {
        Element step= new Element("step");

        Element stepNumber=new Element("step_number");
        stepNumber.appendChild(step_number);
        step.appendChild(stepNumber);

        Element eActions=new Element("actions");
        eActions.appendChild(actions);
        step.appendChild(eActions);

        Element eExpectedresults=new Element("expectedresults");
        eExpectedresults.appendChild(expectedresults);
        step.appendChild(eExpectedresults);

        Element eExecution_type=new Element("execution_type");
        eExecution_type.appendChild(execution_type);
        step.appendChild(eExecution_type);
        return step;
    }*/


}
