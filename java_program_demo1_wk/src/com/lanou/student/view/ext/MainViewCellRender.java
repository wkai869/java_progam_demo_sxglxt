package com.lanou.student.view.ext;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class MainViewCellRender extends DefaultTableCellRenderer {
    //在每一行每一列显示之前都会调用
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        //设置隔行变色
        if(row % 2 == 0){
            setBackground(Color.LIGHT_GRAY); //偶数浅灰色
        }else{
            setBackground(Color.WHITE); //奇数白色
        }
        setHorizontalAlignment(DefaultTableCellRenderer.CENTER); //文字水平剧中

        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
