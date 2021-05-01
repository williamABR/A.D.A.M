package co.edu.javeriana.adam;

import net.bytebuddy.asm.Advice;

import java.time.LocalDateTime;
import java.io.FileOutputStream;
import java.io.File;
import java.util.*;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MyAdvice {
    //static long id = 0;
    public static Stack<String> trace = new Stack<>();
    public static Conexion graph = new Conexion();
    public static Boolean bandera = false;
    public static String nodoAnterior = null;
    public static LocalDateTime time = LocalDateTime.now();
    public static Boolean excelStatic = true;
    public static int enter = 0;

    @Advice.OnMethodEnter
    public static void enter(@Advice.Origin String method) {
        //id += 1;
        try {
            if(excelStatic){
                System.out.println("Ingrese la ruta del grafo del analisis estatico: ");
                Scanner enter = new Scanner(System.in);
                String fileRute = enter.nextLine ();
                File file = new File (fileRute);
                try{
                    graph.openBook(file);
                    excelStatic = false;
                }
                catch(Exception e){
                    System.out.println("Exception: " + e);
                }
            }

            String firma ="";
            StringTokenizer st1 = new StringTokenizer(method, " ");
            while (st1.hasMoreTokens()) {
                String aux = st1.nextToken();
                if(aux.contains("(")) {
                    StringTokenizer st2 = new StringTokenizer(aux, "(");
                    firma =  st2.nextToken();
                }
            }
            trace.add(firma);
            //System.out.println(trace.size()+"---enter   "+firma);
            if (trace.size()>2) {
                bandera = true;
                enter = trace.size();
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    @Advice.OnMethodExit
    public static void exit() {
        //id -= 1;
        try {
            String auxString = trace.pop();
            if(bandera) {
                if (!graph.verificarNodo(auxString)) {
                    graph.addNodo(auxString);
                }
                if(enter-trace.size()>1) {
                    graph.addArista(auxString, nodoAnterior, "", "");
                }
            }
            nodoAnterior = auxString;

            if(trace.empty()&&bandera) {
                System.out.println("Creando nuevo grafo...");
                if(java.time.Duration.between(time,LocalDateTime.now()).toMinutes()>0) {
                    System.out.println("Entramos...");

                    Workbook workbook = new XSSFWorkbook();

                    Sheet sheet = workbook.createSheet("Nodes");
                    Sheet sheet2 = workbook.createSheet("Aristas");

                    sheet.setColumnWidth(0, 1000);
                    sheet.setColumnWidth(1, 1000);

                    sheet.setColumnWidth(0, 1000);
                    sheet.setColumnWidth(1, 1000);
                    sheet.setColumnWidth(2, 1000);
                    sheet.setColumnWidth(3, 1000);

                    Row header = sheet.createRow(0);
                    Row header2 = sheet2.createRow(0);

                    CellStyle headerStyle = workbook.createCellStyle();
                    headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
                    headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

                    XSSFFont font = ((XSSFWorkbook) workbook).createFont();
                    font.setFontName("Arial");
                    font.setFontHeightInPoints((short) 14);
                    font.setBold(true);
                    headerStyle.setFont(font);

                    Cell headerCell = header.createCell(0);
                    headerCell.setCellValue("Nodes");
                    headerCell.setCellStyle(headerStyle);

                    headerCell = header2.createCell(0);
                    headerCell.setCellValue("source");
                    headerCell.setCellStyle(headerStyle);

                    headerCell = header2.createCell(1);
                    headerCell.setCellValue("Target");
                    headerCell.setCellStyle(headerStyle);

                    headerCell = header2.createCell(2);
                    headerCell.setCellValue("#Call");
                    headerCell.setCellStyle(headerStyle);

                    headerCell = header2.createCell(3);
                    headerCell.setCellValue("label");
                    headerCell.setCellStyle(headerStyle);

                    headerCell = header2.createCell(4);
                    headerCell.setCellValue("Type");
                    headerCell.setCellStyle(headerStyle);

                    CellStyle style = workbook.createCellStyle();
                    style.setWrapText(true);

                    int i = 2, j = 2;
                    for(Entry<String, HashMap<String, InfoArista>> entry : graph.getGraph().entrySet()) {
                        Row row = sheet.createRow(i);
                        Cell cell = row.createCell(0);
                        cell.setCellValue(entry.getKey());
                        cell.setCellStyle(style);
                        for(Map.Entry<String, InfoArista> entries : graph.getConexiones(entry.getKey()).entrySet()) {
                            Row row2 = sheet2.createRow(j);
                            Cell cell2 = row2.createCell(0);
                            cell2.setCellValue(entry.getKey());
                            cell2.setCellStyle(style);

                            cell2 = row2.createCell(1);
                            cell2.setCellValue(entries.getKey());
                            cell2.setCellStyle(style);

                            cell2 = row2.createCell(2);
                            cell2.setCellValue(String.valueOf(entries.getValue().getContador()));
                            cell2.setCellStyle(style);

                            cell2 = row2.createCell(3);
                            cell2.setCellValue(entries.getValue().getLabel());
                            cell2.setCellStyle(style);

                            cell2 = row2.createCell(4);
                            cell2.setCellValue(entries.getValue().getTipo());
                            cell2.setCellStyle(style);
                            j++;
                        }
                        i++;
                    }

                    File currDir = new File(".");
                    String path = currDir.getAbsolutePath();
                    String fileLocation = path.substring(0, path.length() - 1) + "Analisis_Dinamico.xlsx";

                    FileOutputStream outputStream = new FileOutputStream(fileLocation);
                    workbook.write(outputStream);
                    workbook.close();

                    time = LocalDateTime.now();

                }
                bandera = false;
            }
        } catch(Exception e) {
            //id = 1;
            System.out.println("Exception: " + e);
        }
    }
}

