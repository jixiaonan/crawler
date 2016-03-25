package utils;

import domain.Stock;
import jxl.format.Border;
import jxl.read.biff.BiffException;
import jxl.write.*;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.DateUtil;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by jixiaonan on 16/2/23.
 */
public class ExcelUtil {
    /**
     *
     * @param list 数据
     */
    public static File exportStock(List<Stock> list) throws Exception {
        //POIFSFileSystem fs;
        String templateName = "/template/DT_Template.xls";
        String exportPath = "tmpFile/template/";
        String fileName = "targetFile.xls";
        //生成导出表
        WritableWorkbook wwb = generateExportBook(templateName, exportPath, fileName);

        //选择模版中名称为预约导出表的Sheet
        WritableSheet wws = wwb.getSheet(0);
        //设置单元格格式
        WritableCellFormat cellFormat = cellFormat();

        //将提取的预约列表数据导入excel
        for (int r = 1; r <= list.size(); r++) {
            for(int n=0; n <= 10 ; n++){
                jxl.write.Label cell0 = new  jxl.write.Label(0, r, list.get(r-1).getSortNumber(), cellFormat);
                jxl.write.Label cell1 = new  jxl.write.Label(1, r, list.get(r-1).getCode(), cellFormat);
                jxl.write.Label cell2 = new  jxl.write.Label(2, r, list.get(r-1).getName(), cellFormat);
                jxl.write.Label cell3 = new  jxl.write.Label(3, r, list.get(r-1).getLink(), cellFormat);
                jxl.write.Label cell4 = new  jxl.write.Label(4, r, list.get(r-1).getTurnover(), cellFormat);
                jxl.write.Label cell5 = new  jxl.write.Label(5, r, list.get(r-1).getRankingFreq(), cellFormat);
                jxl.write.Label cell6 = new  jxl.write.Label(6, r, list.get(r-1).getBuyAmount(), cellFormat);
                jxl.write.Label cell7 = new  jxl.write.Label(7, r, list.get(r-1).getSellAmount(), cellFormat);
                jxl.write.Label cell8 = new  jxl.write.Label(8, r, list.get(r-1).getNetAmount(), cellFormat);
                jxl.write.Label cell9 = new  jxl.write.Label(9, r, list.get(r-1).getStatPeriod(), cellFormat);
                jxl.write.Label cell10 = new  jxl.write.Label(10, r, new SimpleDateFormat("yyyy-MM-dd HH:mm:sss").
                                                                    format(list.get(r-1).getAddDate()), cellFormat);
                wws.addCell(cell0);
                wws.addCell(cell1);
                wws.addCell(cell2);
                wws.addCell(cell3);
                wws.addCell(cell4);
                wws.addCell(cell5);
                wws.addCell(cell6);
                wws.addCell(cell7);
                wws.addCell(cell8);
                wws.addCell(cell9);
                wws.addCell(cell10);
            }
        }
        wwb.write();
        wwb.close();

        return new File(ExcelUtil.class.getResource("/") + exportPath + fileName);
    }

    /**
     * 生成导出表
     * @return
     * @throws Exception
     */
    private static WritableWorkbook generateExportBook(String templateName, String exportPath, String fileName) throws Exception{
        //第一步:选择模版文件
        URL url = ExcelUtil.class.getResource(templateName);
        InputStream stream = new FileInputStream( url.getPath() );
        jxl.Workbook wb = jxl.Workbook.getWorkbook(stream);

        //第二步:通过模版得到一个可写的Workbook:第一个参数是一个输出流对象,第二个参数代表了要读取的模版
        String filePath = ExcelUtil.class.getResource("/") + exportPath;
        File targetFile = new File( filePath );
        if ( !targetFile.exists() )
            targetFile.mkdirs();

        return jxl.Workbook.createWorkbook( new File(filePath + fileName), wb);
    }

    /**
     * 设置单元格格式
     * @return
     * @throws Exception
     */
    private static WritableCellFormat cellFormat() throws Exception{
        WritableFont font= new WritableFont(WritableFont.createFont("宋体"),10);
        WritableCellFormat cellFormat = new WritableCellFormat(font);
        cellFormat.setBorder(Border.ALL, jxl.format.BorderLineStyle.THIN); //Border是jxl.format.Border
        cellFormat.setAlignment(jxl.format.Alignment.CENTRE);//设置文本对其方式，左对齐还是右对齐

        return cellFormat;
    }
}
