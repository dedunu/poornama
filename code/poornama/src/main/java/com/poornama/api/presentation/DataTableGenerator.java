package com.poornama.api.presentation;

/**
 * Created by dedunu on 11/6/14.
 */
public class DataTableGenerator {

    public String getStartTable() {
        String tableString;
        tableString = "<div class=\"table-responsive\">\n";
        tableString = tableString + "\t<table class=\"table table-hover\">\n";
        return tableString;
    }

    protected String getStartTableHeader() {
        String tableString;
        tableString = "\t\t<thead>\n";
        tableString = tableString + "\t\t\t<tr>\n";
        return tableString;
    }

    public String getTableHeader(String columnArray[]) {
        String tableString;
        tableString = getStartTableHeader();
        for (String columnName : columnArray) {
            tableString = tableString + "\t\t\t\t<th>" + columnName + "</th>\n";
        }
        tableString = tableString + "\t\t\t\t<th></th>\n";
        tableString = tableString + "\t\t\t\t<th></th>\n";
        tableString = tableString + getEndTableHeader();
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

    public String getTableBodyRow(String dataArray[], String editUrl, String deleteUrl) {
        String tableString = "";
        tableString = tableString + "\t\t\t<tr>\n";
        for (String rowData : dataArray) {
            tableString = tableString + "\t\t\t\t<td>" + rowData + "</td>\n";
        }
        tableString = tableString + getEditButton(editUrl);
        tableString = tableString + getDeleteButton(deleteUrl);
        tableString = tableString + "\t\t\t</tr>\n";
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

    protected String getEditButton(String editUrl) {
        String tableString;
        tableString = "\t\t\t\t<td><a href=\"" + editUrl + "\"><span class=\"glyphicon glyphicon-pencil\"></span></a></td>\n";
        return tableString;
    }

    protected String getDeleteButton(String deleteUrl) {
        String tableString;
        tableString = "\t\t\t\t<td><a href=\"" + deleteUrl + "\"><span class=\"glyphicon glyphicon-minus\" style=\"color:red\"></span></a></td>\n";
        return tableString;
    }
}
