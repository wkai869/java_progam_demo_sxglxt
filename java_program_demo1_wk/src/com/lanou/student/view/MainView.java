package com.lanou.student.view;

import com.lanou.handler.MainViewHandler;
import com.lanou.req.StudentRequest;
import com.lanou.res.TableDTO;
import com.lanou.service.StudentService;
import com.lanou.service.impl.StudentServiceImpl;
import com.lanou.student.view.ext.MainViewTable;
import com.lanou.student.view.ext.MainViewTableModel;
import com.lanou.util.DimensionUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Vector;

public class MainView extends JFrame {

    //北边的组件
    JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton addBtn = new JButton("增加");
    JButton updateBtn = new JButton("修改");
    JButton delBtn = new JButton("删除");
    JButton searchBtn = new JButton("查询");
    JTextField searchTxt = new JTextField(15);

    //南边的组件
    JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton preBtn = new JButton("上一页");
    JButton nextBtn = new JButton("下一页");
    MainViewTable mainViewTable = new MainViewTable();
    private int pageNow = 1; //当前第几页
    private int pageSize = 10; //一页显示多少条记录

    MainViewHandler mainViewHandler;

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public MainView() {
        setTitle("主界面 蓝鸥学生管理系统");
        Container contentPane = getContentPane();
        Rectangle bounds = DimensionUtil.getBounds();
        pageSize = Math.floorDiv(bounds.height,35);
        mainViewHandler = new MainViewHandler(this);

        //放置北边的组件
        layoutNorth(contentPane);

        //放置中间的组件JTable
        layoutCenter(contentPane);


        //放置南边的组件
        layoutSouth(contentPane);


        //给窗设置图标
        Image image = new ImageIcon(Objects.requireNonNull(MainView.class.getClassLoader().getResource("lanou.png"))).getImage();
        setIconImage(image); //设置图标

        //根据主屏幕大小设置窗口大小

        setBounds(bounds);
        //设置窗体完全充满整个屏幕可见大小
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);
    }

    private void layoutCenter(Container contentPane) {

        TableDTO tableDTO = getTableDTO();
        showPreNext(tableDTO.getTotalCount());
        MainViewTableModel mainViewTableModel = MainViewTableModel.assembleModel(tableDTO.getData());
        //把jtable与model关联
        mainViewTable.setModel(mainViewTableModel);
        mainViewTable.renderRule();
        JScrollPane jScrollPane = new JScrollPane(mainViewTable);
        contentPane.add(jScrollPane, BorderLayout.CENTER);
        showPreNext(tableDTO.getTotalCount());
    }

    /**
     * 查询表格信息（分页+条件）
     * @return
     */
    private TableDTO getTableDTO() {
        StudentService studentService = new StudentServiceImpl();
        StudentRequest request = new StudentRequest();
        request.setPageNow(pageNow);
        request.setPageSize(pageSize);
        request.setSearchKey(searchTxt.getText().trim());
        //DONE 二、分页条件查询
        TableDTO tableDTO = studentService.retrieveStudents(request);
        //TableDTO tableDTO = new TableDTO();
        /*Vector<Vector<Object>> temp = new Vector<>();
        Vector<Object> l1 = new Vector<>();
        Collections.addAll(l1,"001","小红","00a","西安",100,100,100,300);
        Vector<Object> l2 = new Vector<>();
        Collections.addAll(l2,"002","小明","00b","西安",100,100,100,300);
        Vector<Object> l3 = new Vector<>();
        Collections.addAll(l3,"003","小赵","00c","西安",100,100,100,300);
        Collections.addAll(temp,l1,l2,l3);
        tableDTO.setData(temp);
        tableDTO.setTotalCount(3);*/
        //以上为模拟数据，待删除

        Vector<Vector<Object>> data = tableDTO.getData();
        tableDTO.setData(data);
        return tableDTO;
    }

    private void layoutSouth(Container contentPane) {
        preBtn.addActionListener(mainViewHandler);
        nextBtn.addActionListener(mainViewHandler);
        southPanel.add(preBtn);
        southPanel.add(nextBtn);
        contentPane.add(southPanel, BorderLayout.SOUTH);
    }

    //设置上一页下一页是否可见
    public void showPreNext(int totalCount) {
        preBtn.setVisible(pageNow != 1);
        int pageCount;
        if (totalCount % pageSize == 0) {
            pageCount = totalCount / pageSize;
        } else {
            pageCount = totalCount / pageSize + 1;
        }
        nextBtn.setVisible(pageNow != pageCount);
    }

    private void layoutNorth(Container contentPane) {
        //放置北边得 组件
        //增减按钮事件监听
        addBtn.addActionListener(mainViewHandler);
        updateBtn.addActionListener(mainViewHandler);
        delBtn.addActionListener(mainViewHandler);
        searchBtn.addActionListener(mainViewHandler);

        northPanel.add(addBtn);
        northPanel.add(updateBtn);
        northPanel.add(delBtn);
        northPanel.add(searchTxt);
        northPanel.add(searchBtn);
        contentPane.add(northPanel, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        new MainView();
    }

    public void reloadTable() {
        TableDTO tableDTO = getTableDTO();
        MainViewTableModel.updateModel(tableDTO.getData());
        mainViewTable.renderRule();
        showPreNext(tableDTO.getTotalCount());
    }

    public int[] getSelectedStudentIds() {
        int[] selectedRows = mainViewTable.getSelectedRows();
        int[] ids = new int[selectedRows.length];
        for (int i = 0; i < selectedRows.length; i++) {
            int rowIndex = selectedRows[i];
            Object idObj = mainViewTable.getValueAt(rowIndex,0);
            ids[i] = Integer.parseInt(idObj.toString());
        }
        return ids;
    }
}
