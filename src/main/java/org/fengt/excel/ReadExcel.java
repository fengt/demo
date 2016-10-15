package org.fengt.excel;

public class ReadExcel {
    public static void main(String[] args) throws Exception {  
//        InputStream is = new FileInputStream(new File("c://SummaryHSSF.xls"));  
//        //根据输入流创建Workbook对象  
//        Workbook wb = WorkbookFactory.create(is);  
//        //get到Sheet对象  
//        Sheet sheet = wb.getSheetAt(0);  
//        //这个必须用接口  
//        for(Row row : sheet){  
//            for(Cell cell : row){  
//                //cell.getCellType是获得cell里面保存的值的type  
//                //如Cell.CELL_TYPE_STRING  
//                switch(cell.getCellType()){  
//                    case Cell.CELL_TYPE_BOOLEAN:  
//                        //得到Boolean对象的方法  
//                        System.out.print(cell.getBooleanCellValue()+" ");  
//                        break;  
//                    case Cell.CELL_TYPE_NUMERIC:  
//                        //先看是否是日期格式  
//                        if(DateUtil.isCellDateFormatted(cell)){  
//                            //读取日期格式  
//                            System.out.print(cell.getDateCellValue()+" ");  
//                        }else{  
//                            //读取数字  
//                            System.out.print(cell.getNumericCellValue()+" ");  
//                        }  
//                        break;  
//                    case Cell.CELL_TYPE_FORMULA:  
//                        //读取公式  
//                        System.out.print(cell.getCellFormula()+" ");  
//                        break;  
//                    case Cell.CELL_TYPE_STRING:  
//                        //读取String  
//                        System.out.print(cell.getRichStringCellValue().toString()+" ");  
//                        break;                    
//                }  
//            }  
//            System.out.println("");  
//        }
        
        /**
         * 一种传统的读法
         */
        /*Sheet sheet = wb.getSheetAt(0);  
        for (Iterator rit = sheet.rowIterator(); rit.hasNext(); ) {  
            Row row = (Row)rit.next();  
            for (Iterator cit = row.cellIterator(); cit.hasNext(); ) {  
                Cell cell = (Cell)cit.next();  
                // Do something here  
            }  
        }  
        HSSFSheet sheet = wb.getSheetAt(0);  
        for (Iterator<HSSFRow> rit = (Iterator<HSSFRow>)sheet.rowIterator(); rit.hasNext(); ) {  
            HSSFRow row = rit.next();  
            for (Iterator<HSSFCell> cit = (Iterator<HSSFCell>)row.cellIterator(); cit.hasNext(); ) {  
                HSSFCell cell = cit.next();  
                // Do something here  
            }  
        }*/
    }  
}  
