import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

class MyCellRenderer extends DefaultTableCellRenderer
{
    ServiceUpdate sA = new ServiceUpdate();

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if(sA.isServiceIn((String) table.getValueAt(row, 0)))
        {
            cell.setBackground(Color.cyan);
            System.out.println("THIS HAS RUN #############################################################################");
        }
        return cell;
    }
}
