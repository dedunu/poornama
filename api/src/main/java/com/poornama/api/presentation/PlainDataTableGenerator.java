package com.poornama.api.presentation;

/**
 * @author dedunu
 */
public class PlainDataTableGenerator{
    private String tableType = "table-hover";

    /**
     * Return the tableType
     *
     * @return String
     */
    public String getTableType() {
        return tableType;
    }

    /**
     * Set the tableType
     *
     * @param tableType String
     */
    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    /**
     * Returns the start of the table
     *
     * @return String
     */
    public String getStartTable() {
        String tableString;
        tableString = "<div class=\"table-responsive\">\n";
        tableString = tableString + "\t<table class=\"table " + getTableType()+ "\">\n";
        return tableString;
    }

    /**
     * Returns table header
     *
     * @return String
     */
    protected String getStartTableHeader() {
        String tableString;
        tableString = "\t\t<thead>\n";
        tableString = tableString + "\t\t\t<tr>\n";
        return tableString;
    }

    /**
     * Returns the end of the table header
     *
     * @return String
     */
    protected String getEndTableHeader() {
        String tableString;
        tableString = "\t\t\t</tr>\n";
        tableString = tableString + "\t\t</thead>\n";
        return tableString;
    }

    /**
     * Returns the table body start
     *
     * @return String
     */
    public String getStartTableBody() {
        String tableString;
        tableString = "\t\t<tbody>\n";
        return tableString;
    }

    /**
     * Returns end of the table body
     *
     * @return String
     */
    public String getEndTableBody() {
        String tableString;
        tableString = "\t\t</tbody>\n";
        return tableString;
    }

    /**
     * Returns the end of the table
     *
     * @return String
     */
    public String getEndTable() {
        String tableString;
        tableString = "\t</table>\n";
        tableString = tableString + "</div>\n";
        return tableString;
    }

    /**
     * Returns the table header for the given String array
     *
     * @param columnArray String[]
     * @return String
     */
    public String getTableHeader(String columnArray[]) {
        String tableString;
        tableString = getStartTableHeader();
        for (String columnName : columnArray) {
            tableString = tableString + "\t\t\t\t<th>" + columnName + "</th>\n";
        }
        tableString = tableString + getEndTableHeader();
        return tableString;
    }

    /**
     * Returns the table row string for the given String array
     *
     * @param dataArray String[]
     * @return String
     */
    public String getTableBodyRow(String dataArray[]) {
        String tableString = "";
        tableString = tableString + "\t\t\t<tr>\n";
        for (String rowData : dataArray) {
            tableString = tableString + "\t\t\t\t<td>" + rowData + "</td>\n";
        }
        tableString = tableString + "\t\t\t</tr>\n";
        return tableString;
    }
}
