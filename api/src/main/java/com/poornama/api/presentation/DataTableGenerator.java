package com.poornama.api.presentation;

/**
 * @author dedunu
 */
public class DataTableGenerator extends PlainDataTableGenerator {

    /**
     * Returns the table header with extra two columns
     *
     * @param columnArray String[]
     * @return a string with table header
     */
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

    /**
     * Returns table row string
     *
     * @param dataArray String[]
     * @param editUrl   String
     * @param deleteUrl String
     * @return table row string
     */
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

    /**
     * Return the editUrl text
     *
     * @param editUrl String
     * @return String
     */
    protected String getEditButton(String editUrl) {
        String tableString;
        tableString = "\t\t\t\t<td><a href=\"" + editUrl + "\"><span class=\"glyphicon glyphicon-pencil\"></span></a></td>\n";
        return tableString;
    }

    /**
     * Return the deleteUrl text
     *
     * @param deleteUrl String
     * @return String
     */
    protected String getDeleteButton(String deleteUrl) {
        String tableString;
        tableString = "\t\t\t\t<td><a href=\"" + deleteUrl + "\"><span class=\"glyphicon glyphicon-minus\" style=\"color:red\"></span></a></td>\n";
        return tableString;
    }

}
