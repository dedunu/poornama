package com.poornama.api.presentation;

import com.poornama.api.presentation.DataTableGenerator;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by dedunu on 11/8/14.
 */
@Ignore
public class DataTableGeneratorTest {

    @Ignore
    @Test
    public void runEyeBallTest() {
        DataTableGenerator dataTableGenerator = new DataTableGenerator();
        String table;
        String headerArray[] = new String[4];
        String rowArray[] = new String[4];

        headerArray[0] = "header1";
        headerArray[1] = "header2";
        headerArray[2] = "header3";
        headerArray[3] = "header4";

        rowArray[0] = "data1";
        rowArray[1] = "Dedunu Dhananjaya";
        rowArray[2] = "data3";
        rowArray[3] = "data4";

        table = dataTableGenerator.getStartTable();
        table = table + dataTableGenerator.getTableHeader(headerArray);
        table = table + dataTableGenerator.getStartTableBody();
        table = table + dataTableGenerator.getTableBodyRow(rowArray, "url1", "url2");
        table = table + dataTableGenerator.getTableBodyRow(rowArray, "url1", "url2");
        table = table + dataTableGenerator.getTableBodyRow(rowArray, "url1", "url2");
        table = table + dataTableGenerator.getTableBodyRow(rowArray, "url1", "url2");
        table = table + dataTableGenerator.getTableBodyRow(rowArray, "url1", "url2");
        table = table + dataTableGenerator.getTableBodyRow(rowArray, "url1", "url2");
        table = table + dataTableGenerator.getEndTableBody();
        table = table + dataTableGenerator.getEndTable();

        System.out.println(table);
    }
}
