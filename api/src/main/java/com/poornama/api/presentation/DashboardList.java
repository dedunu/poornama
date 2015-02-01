package com.poornama.api.presentation;

public class DashboardList extends PlainDataTableGenerator {
    public String getTableHeader(String columnArray[]) {
        String tableString;
        tableString = getStartTableHeader();
        for (String columnName : columnArray) {
            tableString = tableString + "\t\t\t\t<th>" + columnName + "</th>\n";
        }
        tableString = tableString + "\t\t\t\t<th></th>\n";
        tableString = tableString + getEndTableHeader();
        return tableString;
    }

    public String getTableBodyRow(String dataArray[], String editUrl) {
        String tableString = "";
        tableString = tableString + "\t\t\t<tr>\n";
        for (String rowData : dataArray) {
            tableString = tableString + "\t\t\t\t<td>" + rowData + "</td>\n";
        }
        tableString = tableString + getEditButton(editUrl);
        tableString = tableString + "\t\t\t</tr>\n";
        return tableString;
    }

    protected String getEditButton(String editUrl) {
        String tableString;
        tableString = "\t\t\t\t<td><a href=\"" + editUrl + "\"><span class=\"glyphicon glyphicon-list\"></span></a></td>\n";
        return tableString;
    }

}
