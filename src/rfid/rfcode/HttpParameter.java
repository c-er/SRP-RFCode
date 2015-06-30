package rfid.rfcode;

/**
 * Created by ushankar on 6/25/2015.
 */
public class HttpParameter {
    private String paramName;
    private String paramValue;

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public HttpParameter(String paramName, String paramValue) {
        this.paramName = paramName;
        this.paramValue = paramValue;
    }

    public String toString() {
        return "&" + paramName + "=" + paramValue;
    }
}