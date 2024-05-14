/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Views;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ricardo
 */
public class TableERPTest {
    TableERP tableERP1, tableERP2;
    
    public TableERPTest() {
        Object[][] data1 = new Object[][]
                {{1, 2, 3, 4, 5},
                {5, 6, 7, 8, 9},
                {"a", "b", "c", "d", "e"},
                {"e", "f", "g", "h", "f"}};
        String[] column1 = new String[] {"0", "1", "2", "3", "4"};
        tableERP1 = new TableERP(data1, column1);
        
        Object[][] data2 = new Object[][]
                            {{101, "user1", "a", "b", "c"},
                            {102, "user2", "b", "c", "d"},
                            {103, "user3", "c", "d", "e"}};
        String[] column2 = new String[]{"soCT", "username", "data1", "data2", "data3"};
        tableERP2 = new TableERP(data2, column2);
    }

    @Test
    public void testSetEditable() {
    }

    @Test
    public void testAddEditableColumn() {
    }

    @Test
    public void testSetSpecialValues() {
    }

    @Test
    public void testIsCellEditable() {
    }

    @Test
    public void testSua() {
    }

    @Test
    public void testFilter() {
        int[] columns = {1, 2};
        boolean remove = true;
        Object[][] result = tableERP1.filter(columns, remove);
        Object[][] expected = new Object[][]{{1, 4, 5},
                                            {5, 8, 9},
                                            {"a", "d", "e"},
                                            {"e", "h", "f"}};
        assertArrayEquals(expected, result);
    }
    
        @Test
    public void testFilter2() {
        int[] columns = {1, 2};
        boolean remove = false;

        Object[][] result2 = tableERP1.filter(columns, remove);
        Object[][] expected2 = new Object[][]{{2, 3},
                                            {6, 7},
                                            {"b", "c"},
                                            {"f", "g"}};
        assertArrayEquals(expected2, result2);
    }

    @Test
    public void testSetColumnType() {
    }

    @Test
    public void testGetColumnClass() {
    }

    @Test
    public void testCapNhatTongGia_3args() {
    }

    @Test
    public void testCapNhatTongGia_4args() {
    }

    @Test
    public void testSearchByCriteria() {
        String[] paramSearch = new String[]{"102", ""};
        int[] columSearch = new int[]{0, 1};
        String searchType = "match";
        Object[][] result = tableERP2.searchByCriteria(paramSearch, columSearch, searchType);
        Object[][] expected = new Object[][]{{102, "user2", "b", "c", "d"}};
        assertArrayEquals(expected, result);
    }
    
    @Test
    public void testSearchByCriteria2() {
        String[] paramSearch = new String[]{"", "user"};
        int[] columSearch = new int[]{0, 1};
        String searchType = "contain";
        Object[][] result = tableERP2.searchByCriteria(paramSearch, columSearch, searchType);
        Object[][] expected = new Object[][]{{101, "user1", "a", "b", "c"},
                                            {102, "user2", "b", "c", "d"},
                                            {103, "user3", "c", "d", "e"}};
        assertArrayEquals(expected, result);
    }

    @Test
    public void testSetDataSearch() {
    }

    @Test
    public void testExportObjData_int() {
        int row = 1;
        Object[] result = tableERP1.exportObjData(row);
        Object[] expected = new Object[]{5, 6, 7, 8, 9};
        assertArrayEquals(expected, result);
    }

    @Test
    public void testExportObjData_intArr() {
        int[] row = {2, 3};
        Object[][] result = tableERP1.exportObjData(row);
        Object[][] expected = new Object[][]{{"a", "b", "c", "d", "e"},
                                            {"e", "f", "g", "h", "f"}};
        assertArrayEquals(expected, result);
    }

    @Test
    public void testFilter_intArr_boolean() {
    }

    @Test
    public void testFilter_4args() {
        int[] rows = new int[] {1,2};
        boolean selRow = true;
        int[] colums = new int[] {2, 3, 4};
        boolean selCol = true;
        Object[][] result = tableERP2.filter(rows, selRow, colums, selCol);
        Object[][] exptected = new Object[][]{{"b", "c", "d"},
                                            {"c", "d", "e"}};
        assertArrayEquals(exptected, result);
    }

    @Test
    public void testSetData() {
    }

    @Test
    public void testAdd() {
        Object[][] listObjData = new Object[][]{{104, "d", "e", "f"},
                                                {105, "e", "f", "g"}};
        int[] col = new int[] {0, 2, 3};
        int[] tbColumn = new int[] {0, 3, 4};
        Object[][] result = tableERP2.add(tbColumn, listObjData, col);
        Object[][] expected =  new Object[][]
                            {{101, "user1", "a", "b", "c"},
                            {102, "user2", "b", "c", "d"},
                            {103, "user3", "c", "d", "e"},
                            {104, null, null, "e", "f"},
                            {105, null, null, "f", "g"}};
        assertArrayEquals(expected, result);
    }
    
}
