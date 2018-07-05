package com.manager.jfdeng.creditmanagersystem.utils;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.widget.Toast;

import com.manager.jfdeng.creditmanagersystem.bean.ScoreDto;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 * Created by yf on 18-7-5.
 */

public class ExcelUtil {

    //内存地址
    public static String root = Environment.getExternalStorageDirectory()
            .getPath();

    public static File writeExcel(Context context, List<ScoreDto> exportOrder,
                                  String fileName) throws Exception {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)&&getAvailableStorage()>1000000) {
            Toast.makeText(context, "SD卡不可用", Toast.LENGTH_LONG).show();
            return null;
        }
        String[] title = { "序号", "学号", "姓名", "班级名称", "点名1", "点名2", "点名3", "点名4", "点名5","平时作业1","平时作业2","平时作业3","平时作业4","平时作业5","上机1","上机2","上机3","上机4","上机5","小计" };
        File file;
        File dir = new File(context.getExternalFilesDir(null).getPath());
        file = new File(dir, fileName + ".xls");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // 创建Excel工作表
        WritableWorkbook wwb;
        OutputStream os = new FileOutputStream(file);
        wwb = Workbook.createWorkbook(os);
        // 添加第一个工作表并设置第一个Sheet的名字
        WritableSheet sheet = wwb.createSheet("订单", 0);
        Label label;
        for (int i = 0; i < title.length; i++) {
            // Label(x,y,z) 代表单元格的第x+1列，第y+1行, 内容z
            // 在Label对象的子对象中指明单元格的位置和内容
            label = new Label(i, 0, title[i], getHeader());
            // 将定义好的单元格添加到工作表中
            sheet.addCell(label);
        }

        for (int i = 0; i < exportOrder.size(); i++) {
            ScoreDto dto = exportOrder.get(i);

            Label orderNum = new Label(0, i + 1, (i+1)+"");
            Label restaurant = new Label(1, i + 1, dto.getSno());
            Label nameLabel = new Label(2,i+1,dto.getName());
            Label address = new Label(3, i + 1, dto.getaClass());
            Label dianming1 = new Label(4, i + 1, dto.getDianming1());
            Label dianming2 = new Label(5, i + 1, dto.getDianming3());
            Label dianming3 = new Label(6, i + 1, dto.getDianming3());
            Label dianming4 = new Label(7, i + 1, dto.getDianming4());
            Label dianming5 = new Label(8, i + 1, dto.getDianming5());
            Label zuoye1 = new Label(9, i + 1, dto.getZuoye1());
            Label zuoye2 = new Label(10, i + 1, dto.getZuoye2());
            Label zuoye3 = new Label(11, i + 1, dto.getZuoye3());
            Label zuoye4 = new Label(12, i + 1, dto.getZuoye4());
            Label zuoye5 = new Label(13, i + 1, dto.getZuoye5());
            Label shangji1 = new Label(14, i + 1, dto.getShangji1());
            Label shangji2 = new Label(15, i + 1, dto.getShangji2());
            Label shangji3 = new Label(16, i + 1, dto.getShangji3());
            Label shangji4 = new Label(17, i + 1, dto.getShangji4());
            Label shangji5 = new Label(18, i + 1, dto.getShangji5());
            Label xiaoji = new Label(19, i + 1, getXiaoJi(dto)+"");

            sheet.addCell(orderNum);
            sheet.addCell(restaurant);
            sheet.addCell(nameLabel);
            sheet.addCell(address);
            sheet.addCell(dianming1);
            sheet.addCell(dianming2);
            sheet.addCell(dianming3);
            sheet.addCell(dianming4);
            sheet.addCell(dianming5);
            sheet.addCell(zuoye1);
            sheet.addCell(zuoye2);
            sheet.addCell(zuoye3);
            sheet.addCell(zuoye4);
            sheet.addCell(zuoye5);
            sheet.addCell(shangji1);
            sheet.addCell(shangji2);
            sheet.addCell(shangji3);
            sheet.addCell(shangji4);
            sheet.addCell(shangji5);
            sheet.addCell(xiaoji);
        }
        // 写入数据
        wwb.write();
        // 关闭文件
        wwb.close();
        return file;
    }

    private static float getXiaoJi(ScoreDto dto) {
        float dianming1 = Float.valueOf(dto.getDianming1());
        float dianming2 = Float.valueOf(dto.getDianming2());
        float dianming3 = Float.valueOf(dto.getDianming3());
        float dianming4 = Float.valueOf(dto.getDianming4());
        float dianming5 = Float.valueOf(dto.getDianming5());
        float zuoye1 = Float.valueOf(dto.getDianming1());
        float zuoye2 = Float.valueOf(dto.getDianming2());
        float zuoye3 = Float.valueOf(dto.getDianming3());
        float zuoye4 = Float.valueOf(dto.getDianming4());
        float zuoye5 = Float.valueOf(dto.getDianming5());
        float shangji1 = Float.valueOf(dto.getDianming1());
        float shangji2 = Float.valueOf(dto.getDianming2());
        float shangji3 = Float.valueOf(dto.getDianming3());
        float shangji4 = Float.valueOf(dto.getDianming4());
        float shangji5 = Float.valueOf(dto.getDianming5());
        float dianming = dianming1+dianming2+dianming3+dianming4+dianming5;
        float zuoye = zuoye1+zuoye2+zuoye3+zuoye4+zuoye5;
        float shangji = shangji1+shangji2+shangji3+shangji4+shangji5;
        float xiaoji = dianming/5.0f*0.2f+shangji/5.0f*0.5f+zuoye/5.0f*0.3f;
        return xiaoji;
    }

    public static WritableCellFormat getHeader() {
        WritableFont font = new WritableFont(WritableFont.TIMES, 10,
                WritableFont.BOLD);// 定义字体
        try {
            font.setColour(Colour.BLUE);// 蓝色字体
        } catch (WriteException e1) {
            e1.printStackTrace();
        }
        WritableCellFormat format = new WritableCellFormat(font);
        try {
            format.setAlignment(jxl.format.Alignment.CENTRE);// 左右居中
            format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 上下居中
            // format.setBorder(Border.ALL, BorderLineStyle.THIN,
            // Colour.BLACK);// 黑色边框
            // format.setBackground(Colour.YELLOW);// 黄色背景
        } catch (WriteException e) {
            e.printStackTrace();
        }
        return format;
    }

    /** 获取SD可用容量 */
    private static long getAvailableStorage() {

        StatFs statFs = new StatFs(root);
        long blockSize = statFs.getBlockSize();
        long availableBlocks = statFs.getAvailableBlocks();
        long availableSize = blockSize * availableBlocks;
        // Formatter.formatFileSize(context, availableSize);
        return availableSize;
    }

}
