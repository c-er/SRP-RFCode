package rfid.rfcode;

/**
 * Created by ushankar on 6/25/2015.
 */

/**
 * Represents an HTTP parameter to be passed in an HTTP request.
 */
public class HttpParameter {
    private String paramName;
    private String paramValue;

    /**
     * Constructs the HttpParameter object.
     * @param paramName name of the paramater
     * @param paramValue value of said parameter
     */
    public HttpParameter(String paramName, String paramValue) {
        this.paramName = paramName;
        this.paramValue = paramValue;
    }

    /**
     * Returns the name of the HTTP parameter.
     * @return the name of the parameter
     */
    public String getParamName() {
        return paramName;
    }

    /**
     * Sets the name of the HTTP parameter.
     * @param paramName the name of the parameter
     */
    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    /**
     * Returns the value of the HTTP parameter
     * @return the value of the parameter
     */
    public String getParamValue() {
        return paramValue;
    }

    /**
     * Sets the value of the HTTP parameter
     * @param paramValue the value of the parameter
     */
    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    /**
     * Returns a string that represents the object.
     * @return the string containing data representing the HTTP parameter
     */
    @Override
    public String toString() {
        return "&" + paramName + "=" + paramValue;
    }
}