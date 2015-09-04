package com.poornama.api.presentation;

/**
 * @author dedunu
 */
public class PlainDataTableGenerator{
    private String tableType = "table-hover";

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public String getStartTable() {
        String tableString;
        tableString = "<div class=\"table-responsive\">\n";
        tableString = tableString + "\t<table class=\"table " + getTableType()+ "\">\n";
        return tableString;
    }

    protected String getStartTableHeader() {
        String tableString;
        tableString = "\t\t<thead>\n";
        tableString = tableString + "\t\t\t<tr>\n";
        return tableString;
    }

    protected String getEndTableHeader() {
        String tableString;
        tableString = "\t\t\t</tr>\n";
        tableString = tableString + "\t\t</thead>\n";
        return tableString;
    }

    public String getStartTableBody() {
        String tableString;
        tableString = "\t\t<tbody>\n";
        return tableString;
    }

    public String getEndTableBody() {
        String tableString;
        tableString = "\t\t</tbody>\n";
        return tableString;
    }

    public String getEndTable() {
        String tableString;
        tableString = "\t</table>\n";
        tableString = tableString + "</div>\n";
        return tableString;
    }

    public String getTableHeader(String columnArray[]) {
        String tableString;
        tableString = getStartTableHeader();
        for (String columnName : columnArray) {
            tableString = tableString + "\t\t\t\t<th>" + columnName + "</th>\n";
        }
        tableString = tableString + getEndTableHeader();
        return tableString;
    }

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
