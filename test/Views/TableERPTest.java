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
    TableERP tableERP;
    
    public TableERPTest() {
        Object[][] data = new Object[][]
                {{1, 2, 3, 4},
                {5, 6, 7, 8},
                {"a", "b", "c", "d"},
                {"e", "f", "g", "h"}};
        String[] column = new String[] {"0", "1", "2", "3"};
        tableERP = new TableERP(data, column);
        tableERP.toString();
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
        Object[][] result = tableERP.filter(columns, remove);
        Object[][] expected = new Object[][]{{1, 4},
                                            {5, 8},
                                            {"a", "d"},
                                            {"e", "h"}};
        assertArrayEquals(expected, result);
    }
    
        @Test
    public void testFilter2() {
        int[] columns = {1, 2};
        boolean remove = false;

        Object[][] result2 = tableERP.filter(columns, remove);
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
    }

    @Test
    public void testSetDataSearch() {
    }
    
}
