package com.poornama.api.presentation;

/**
 * @author dedunu
 */
public class PrintDataTableGenerator  extends DataTableGenerator {

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
        tableString = tableString + "\t\t\t\t<th></th>\n";
        tableString = tableString + "\t\t\t\t<th></th>\n";
        tableString = tableString + "\t\t\t\t<th></th>\n";
        tableString = tableString + getEndTableHeader();
        return tableString;
    }

    /**
     * Returns the table row string for the given String array
     *
     * @param dataArray String[]
     * @return String
     */
    public String getTableBodyRow(String dataArray[],String printUrl, String editUrl, String deleteUrl) {
        String tableString = "";
        tableString = tableString + "\t\t\t<tr>\n";
        for (String rowData : dataArray) {
            tableString = tableString + "\t\t\t\t<td>" + rowData + "</td>\n";
        }
        tableString = tableString + getPrintButton(printUrl);
        tableString = tableString + getEditButton(editUrl);
        tableString = tableString + getDeleteButton(deleteUrl);
        tableString = tableString + "\t\t\t</tr>\n";
        return tableString;
    }

    /**
     * Return the print button as a String
     *
     * @param editUrl String
     * @return String
     */
    protected String getPrintButton(String editUrl) {
        String tableString;
        tableString = "\t\t\t\t<td><a href=\"" + editUrl + "\" target=\"_blank\"><span class=\"glyphicon glyphicon-print\"></span></a></td>\n";
        return tableString;
    }


}