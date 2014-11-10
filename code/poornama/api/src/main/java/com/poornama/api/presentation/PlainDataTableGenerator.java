package com.poornama.api.presentation;

/**
 * Created by dedunu on 11/8/14.
 */
public class PlainDataTableGenerator extends DataTableGenerator {

    @Override
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
