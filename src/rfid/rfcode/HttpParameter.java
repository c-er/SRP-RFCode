package rfid.rfcode;

/**
 * Created by ushankar on 6/25/2015.
 */
public class HttpParameter {
    private String paramName;
    private String paramValue;

    /**
     * @param paramName     name of the paramater  --  example: id,     tagid0
     * @param paramValue    value of said parameter -- example: EUCLID, RFCMII00001234
     */
    public HttpParameter(String paramName, String paramValue) {
        this.paramName = paramName;
        this.paramValue = paramValue;
    }

    // getters and setters
    public String getParamName() { return paramName; }
    public void setParamName(String paramName) {
        this.paramName = paramName;
    }
    public String getParamValue() {
        return paramValue;
    }
    public void setParamValue(String paramValue) { this.paramValue = paramValue; }

    /**
     * @return returns the parameters a command takes. Used in the for-each loop in HttpRequest.execute()
     */
    public String toString() {
        return "&" + paramName + "=" + paramValue;
    }
}