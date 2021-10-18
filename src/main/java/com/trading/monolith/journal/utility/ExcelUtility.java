// package com.trading.monolith.journal.utility;

// import java.io.IOException;
// import java.io.InputStream;
// import java.util.ArrayList;
// import java.util.Iterator;
// import java.util.List;

// import com.trading.monolith.journal.entity.TradeJournal;

// import org.apache.poi.sl.usermodel.Sheet;
// import org.apache.poi.ss.usermodel.Cell;
// import org.apache.poi.ss.usermodel.Row;
// import org.apache.poi.ss.usermodel.Workbook;
// import org.springframework.web.multipart.MultipartFile;

// public class ExcelUtility {
//     public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
//     static String[] HEADERs = { "DateTime", "Stock", "TradeType", "Entry", "Volume", "Stop Loss", "Planned Exit", "Trade Duration", "Psychology", "Trade Cost", "Exit", "Rating" };
//     static String SHEET = "Tutorials";

//     public static boolean hasExcelFormat(MultipartFile file) {

//         if (!TYPE.equals(file.getContentType())) {
//             return false;
//         }

//         return true;
//     }

//     public static List<TradeJournal> excelToJournals(InputStream is) {
//         try {
//             Workbook workbook = new CSVParser(is);

//             Sheet sheet = workbook.getSheet(SHEET);
//             Iterator<Row> rows = sheet.iterator();

//             List<TradeJournal> tutorials = new ArrayList<TradeJournal>();

//             int rowNumber = 0;
//             while (rows.hasNext()) {
//                 Row currentRow = rows.next();

//                 // skip header
//                 if (rowNumber == 0) {
//                     rowNumber++;
//                     continue;
//                 }

//                 Iterator<Cell> cellsInRow = currentRow.iterator();

//                 TradeJournal tutorial = new TradeJournal();

//                 int cellIdx = 0;
//                 while (cellsInRow.hasNext()) {
//                     Cell currentCell = cellsInRow.next();

//                     switch (cellIdx) {
//                         case 0:
//                             tutorial.setId((long) currentCell.getNumericCellValue());
//                             break;

//                         case 1:
//                             tutorial.setTitle(currentCell.getStringCellValue());
//                             break;

//                         case 2:
//                             tutorial.setDescription(currentCell.getStringCellValue());
//                             break;

//                         case 3:
//                             tutorial.setPublished(currentCell.getBooleanCellValue());
//                             break;

//                         default:
//                             break;
//                     }

//                     cellIdx++;
//                 }

//                 tutorials.add(tutorial);
//             }

//             workbook.close();

//             return tutorials;
//         } catch (IOException e) {
//             throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
//         }
//     }
// }
